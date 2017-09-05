class JsonString(string: String?) : JsonValue() {

	val string: String? = string

	override fun write(writer: JsonWriter): String {
		writer.writeString(this.string)
		return writer.string
	}

	override fun isString(): Boolean {
		return true
	}

	override fun isNull(): Boolean {
		if (null == string) return true else return false
	}

	override fun asString(): String {
		if (null == this.string) throw Exception("invalid String") else return this.string
	}


	override fun equals(other: Any?): Boolean {
		if (null == other || other !is JsonString) return false

		val result = this.string?.equals(other)
		if (null == result) return false else return result
	}
}