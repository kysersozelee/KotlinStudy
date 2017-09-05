class JsonWriter {
	var string: String = ""

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

	fun write(str: String?) {
		string += str
	}

	fun writeLiteral(value: Boolean) {
		write(value.toString())
	}

	fun writeString(str: String?) {
		if (null != str) {
			write("\"")
			write(str)
			write("\"")
		} else {
			write("null")
		}
	}

	fun writeNumber(number: String?) {
		write(number)
	}

	fun writeArrayOpen() {
		write("[")
	}

	fun writeArrayClose() {
		write("]")
	}


	fun writeArraySeparator() {
		write(",")
	}

	fun writeObjectOpen() {
		write("{")
	}

	fun writeObjectClose() {
		write("}")
	}

	fun writeMemberSeparator() {
		write(":")
	}

	fun writeObjectSeparator() {
		write(",")
	}

	fun writeJsonString(string: String) {
		val length = string.length
		var start = 0
		var idx = 0
		while (idx < length) {
			var replacement: CharArray? = getReplacementChars(string.get(idx))
			if (null != replacement) {
				write(string.substring(start, idx - start))
				write(replacement.toString())
				start = idx + 1
			}
			idx++
		}
		write(string.substring(start, length - start))
	}

	fun writeMemberName(name: String) {
		write("\"")
		writeJsonString(name)
		write("\"")
	}

	fun getReplacementChars(ch: Char): CharArray? {
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