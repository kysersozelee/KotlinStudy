class JsonObject : JsonValue() {
	var names: MutableList<String> = mutableListOf()
	var values: MutableList<JsonValue> = mutableListOf()

	override fun write(writer: JsonWriter): String {
		writer.writeObjectOpen()

		var namesIterator = this.names.iterator()
		var valuesIterator = this.values.iterator()

		if (namesIterator.hasNext()) {
			writer.writeMemberName(namesIterator.next())
			writer.writeMemberSeparator()
			valuesIterator.next().write(writer)


			while (namesIterator.hasNext()) {
				writer.writeObjectSeparator()
				writer.writeMemberName(namesIterator.next())
				writer.writeMemberSeparator()
				valuesIterator.next().write(writer)
			}
		}
		writer.writeObjectClose()
		return writer.string
	}

	fun add(name: String, value: Any?): JsonObject {
		this.names.add(name)
		if (value is Int || value is Float || value is Double) {
			this.values.add(JsonNumber(value))
		} else if (null == value) {
			this.values.add(JsonString(value))
		} else if (value is String) {
			this.values.add(JsonString(value.toString()))
		} else if (value is JsonObject) {
			this.values.add(value)
		} else if (value is Boolean) {
			this.values.add(JsonLiteral(value))
		} else if (value is JsonArray) {
			this.values.add(JsonArray(value))
		} else {
			throw Exception("Invalid type of value")
		}

		return this
	}

	override fun asObject(): JsonObject {
		return this
	}

	override fun equals(other: Any?): Boolean {
		if (null == other || other !is JsonObject) return false

		return this.names.equals(other) && this.values.equals(other)
	}

	fun get(name: String): JsonValue? {
		return getValue(name)
	}

	fun getValue(name: String): JsonValue? {
		var idx: Int = 0
		for (item: String in names) {
			if (name.equals(item)) {
				return values.get(idx)
			}
			idx++
		}
		return null
	}
}