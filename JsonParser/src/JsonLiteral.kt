package Json

class JsonLiteral(boolean: Boolean) : JsonValue() {

	val value: Boolean = boolean

	override fun toJsonString(): String {
		return value.toString()
	}

	override fun write(sb: StringBuilder) {
		sb.append(value.toString())
	}

	override fun isTrue(): Boolean {
		return value
	}

	override fun isFalse(): Boolean {
		return value
	}

	override fun equals(other: Any?): Boolean {
		if (null == other || other !is JsonLiteral) return false

		return this.value.equals(other)
	}
}