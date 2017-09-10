abstract class JsonValue {

	val CONTROL_CHARACTERS_END: Int = 0x001f
	val QUOT_CHARS: CharArray = charArrayOf('\\', '"')
	val BS_CHARS: CharArray = charArrayOf('\\', '\\')
	val LF_CHARS: CharArray = charArrayOf('\\', 'n')
	val CR_CHARS: CharArray = charArrayOf('\\', 'r')
	val TAB_CHARS: CharArray = charArrayOf('\\', 't')

	var UNICODE_2028_CHARS: CharArray = charArrayOf('\\', 'u', '2', '0', '2', '8')
	var UNICODE_2029_CHARS: CharArray = charArrayOf('\\', 'u', '2', '0', '2', '9')
	var HEX_DIGITS: CharArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f')

	abstract fun write(sb: StringBuilder)
	abstract fun toJsonString(): String


	open fun isNumber(): Boolean {
		return false
	}

	open fun isArray(): Boolean {
		return false
	}

	open fun isString(): Boolean {
		return false
	}

	open fun isTrue(): Boolean {
		return false
	}

	open fun isFalse(): Boolean {
		return false
	}

	open fun isNull(): Boolean {
		return false
	}

	open fun asInt(): Int {
		throw Exception("Not a number")
	}

	open fun asLong(): Long {
		throw Exception("Not a number")
	}

	open fun asFloat(): Float {
		throw Exception("Not a number")
	}

	open fun asDouble(): Double {
		throw Exception("Not a number")
	}

	open fun asString(): String {
		throw Exception("Not a String")
	}

	open fun asBollean(): Boolean {
		throw Exception("Not a Boolean")
	}

	open fun asArray(): JsonArray {
		throw Exception("Not an Array")
	}

	open fun asObject(): JsonObject {
		throw Exception("Not an Object")
	}


	protected fun writeArrayOpen(sb: StringBuilder) {
		sb.append("[")
	}

	protected fun writeArrayClose(sb: StringBuilder) {
		sb.append("]")
	}


	protected fun writeArraySeparator(sb: StringBuilder) {
		sb.append(",")
	}

	protected fun writeObjectOpen(sb: StringBuilder) {
		sb.append("{")
	}

	protected fun writeObjectClose(sb: StringBuilder) {
		sb.append("}")
	}

	protected fun writeMemberSeparator(sb: StringBuilder) {
		sb.append(":")
	}

	protected fun writeObjectSeparator(sb: StringBuilder) {
		sb.append(",")
	}

	protected fun writeJsonString(string: String, sb: StringBuilder) {
		val length = string.length
		var start = 0
		var idx = 0
		while (idx < length) {
			var replacement: CharArray? = getReplacementChars(string.get(idx))
			if (null != replacement) {
				sb.append(string.substring(start, idx - start))
				sb.append(replacement.toString())
				start = idx + 1
			}
			idx++
		}
		sb.append(string.substring(start, length - start))
	}

	protected fun writeKey(name: String, sb: StringBuilder) {
		sb.append("\"")
		writeJsonString(name, sb)
		sb.append("\"")
	}

	protected fun getReplacementChars(ch: Char): CharArray? {
		if (ch > '\\') {
			if (ch < '\u2028' || ch > '\u2029') {
				// The lower range contains 'a' .. 'z'. Only 2 checks required.
				return null;
			}
			return if (ch == '\u2028') UNICODE_2028_CHARS else UNICODE_2029_CHARS;
		}
		if (ch == '\\') {
			return BS_CHARS;
		}
		if (ch > '"') {
			// This range contains '0' .. '9' and 'A' .. 'Z'. Need 3 checks to get here.
			return null;
		}
		if (ch == '"') {
			return QUOT_CHARS;
		}
		if (ch.toInt() > CONTROL_CHARACTERS_END) {
			return null;
		}
		if (ch == '\n') {
			return LF_CHARS;
		}
		if (ch == '\r') {
			return CR_CHARS;
		}
		if (ch == '\t') {
			return TAB_CHARS;
		}

		return charArrayOf('\\', 'u', '0', '0', HEX_DIGITS.get((ch.toInt() shr 4) and 0x000f), HEX_DIGITS.get(ch.toInt() and 0x000f));
	}
}