class JsonString(string: String?) : JsonValue() {

	val string: String? = string

	override fun toJsonString(): String {
		return if(null == this.string) return "" else return this.string
	}


	override fun write(sb: StringBuilder) {
		if (null != string) {
			sb.append("\"").append(string).append("\"")
		} else {
			sb.append("null")
		}
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