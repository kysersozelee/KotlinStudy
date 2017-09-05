class JsonArray constructor() : JsonValue() {
	var values: MutableList<JsonValue> = mutableListOf()

	constructor(jsonArray: JsonArray) : this() {
		this.values = jsonArray.values
	}

	override fun write(writer: JsonWriter): String {
		writer.writeArrayOpen()

		var valuesIterator = this.values.iterator()
		if (valuesIterator.hasNext()) {
			valuesIterator.next().write(writer)
			while (valuesIterator.hasNext()) {
				writer.writeArraySeparator()
				valuesIterator.next().write(writer)
			}
		}
		writer.writeArrayClose()

		return writer.string
	}

	override fun isArray(): Boolean {
		return true;
	}

	override fun asArray(): JsonArray {
		return this;
	}

	fun add(value: Int): JsonArray {
		this.values.add(JsonNumber(value))
		return this
	}

	fun add(value: Long): JsonArray {
		this.values.add(JsonNumber(value))
		return this
	}

	fun add(value: Double): JsonArray {
		this.values.add(JsonNumber(value))
		return this
	}

	fun add(value: Float): JsonArray {
		this.values.add(JsonNumber(value))
		return this
	}

	fun add(value: Boolean): JsonArray {
		this.values.add(JsonLiteral(value))
		return this
	}

	fun add(value: JsonValue): JsonArray {
		this.values.add(value)
		return this
	}

	fun add(value: String): JsonArray {
		this.values.add(JsonString(value))
		return this
	}


	override fun equals(other: Any?): Boolean {
		if (null == other || other !is JsonArray) return false

		return this.values.equals(other)
	}
}