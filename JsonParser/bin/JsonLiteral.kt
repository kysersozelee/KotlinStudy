class JsonLiteral(boolean: Boolean) : JsonValue() {

	val value: Boolean = boolean

	override fun write(writer: JsonWriter): String {
		writer.writeLiteral(value)
		return writer.string
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