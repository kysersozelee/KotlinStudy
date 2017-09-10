class JsonNumber(value: Any) : JsonValue() {

	val value: Any = value

	override fun toJsonString(): String {
		return value.toString()
	}

	override fun write(sb: StringBuilder) {
		sb.append(this.value.toString())
	}

	override fun isNumber(): Boolean {
		return true
	}

	override fun asInt(): Int {
		val result: Int? = this.value.toString().toIntOrNull()
		if (null == result) throw Exception("is not Integer") else return result;
	}

	override fun asFloat(): Float {
		val result: Float? = this.value.toString().toFloatOrNull()
		if (null == result) throw Exception("is not Float") else return result;
	}


	override fun asDouble(): Double {
		val result: Double? = this.value.toString().toDoubleOrNull()
		if (null == result) throw Exception("is not Double") else return result;
	}

	override fun equals(other: Any?): Boolean {
		if (null == other || other !is JsonNumber) return false

		return this.value.equals(other)
	}
}