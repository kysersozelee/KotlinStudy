package Json

class JsonArray constructor() : JsonValue() {
	var values: MutableList<JsonValue> = mutableListOf()

	constructor(jsonArray: JsonArray) : this() {
		this.values = jsonArray.values
	}

	var sb: StringBuilder = StringBuilder()
	override fun toJsonString(): String {
		sb = StringBuilder()
		write(this.sb)
		return this.sb.toString()
	}

	override fun write(sb: StringBuilder) {
		writeArrayOpen(sb)

		var valuesIterator = this.values.iterator()
		if (valuesIterator.hasNext()) {
			valuesIterator.next().write(sb)
			while (valuesIterator.hasNext()) {
				writeArraySeparator(sb)
				valuesIterator.next().write(sb)
			}
		}
		writeArrayClose(sb)
	}

	override fun isArray(): Boolean {
		return true;
	}

	override fun asArray(): JsonArray {
		return this;
	}

	fun add(value: Any?): JsonArray {
		when (value) {
			is Int -> add(value)
			is Long -> add(value)
			is Double -> add(value)
			is Float -> add(value)
			is String -> add(value)
			is Boolean -> add(value)
			is JsonValue -> add(value)
		}

		return this
	}

	fun get(index: Int): Any? {
		return this.values.get(index)
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