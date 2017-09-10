class JsonObject : JsonValue() {
	var keys: MutableList<String> = mutableListOf()
	var values: MutableList<JsonValue> = mutableListOf()

	var sb: StringBuilder = StringBuilder()

	override fun toJsonString(): String {
		sb = StringBuilder()
		write(this.sb)
		return this.sb.toString()
	}


	override fun write(sb: StringBuilder) {
		writeObjectOpen(sb)

		var keysIterator = this.keys.iterator()
		var valuesIterator = this.values.iterator()

		if (keysIterator.hasNext()) {
			writeKey(keysIterator.next(), sb)
			writeMemberSeparator(sb)
			valuesIterator.next().write(sb)


			while (keysIterator.hasNext()) {
				writeObjectSeparator(sb)
				writeKey(keysIterator.next(), sb)
				writeMemberSeparator(sb)
				valuesIterator.next().write(sb)
			}
		}
		writeObjectClose(sb)
	}

	fun add(name: String, value: Any?): JsonObject {
		this.keys.add(name)
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

		return this.keys.equals(other) && this.values.equals(other)
	}

	fun get(name: String): JsonValue? {
		return getValue(name)
	}

	fun getValue(name: String): JsonValue? {
		var idx: Int = 0
		for (item: String in keys) {
			if (name.equals(item)) {
				return values.get(idx)
			}
			idx++
		}
		return null
	}
}