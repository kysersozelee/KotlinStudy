class JsonParser {
	var currentOffset: Int = 0
	var index: Int = 0
	var nestingLevel: Int = 0
	var buffer: CharArray = charArrayOf()

	fun parse(jsonString: String): JsonValue {
		init(jsonString)
		var firstChar: Char = readChar()
		when (firstChar) {
			'{' -> {
				this.nestingLevel++
				var result = JsonObject()
				var name = readName()
				var data = readData()
				result.add(name, data)
				while (this.index < buffer.size && readEndChar()) {
					name = readName()
					data = readData()
					result.add(name, data)
				}
				if (this.nestingLevel > 0 && this.index < buffer.size) {
					for (i in 0 until this.nestingLevel) {
						var end = readChar()
						if ('}'.equals(end)) {
							this.nestingLevel--
							continue
						} else {
							throw Exception("invalid parse")
						}
					}
				} else if (this.nestingLevel > 0) {
					throw Exception("invalid parse")
				}
				return result
			}
			'[' -> {
				var result = JsonArray()
				return result
			}
		}
		throw Exception("invalid string")
	}

	private fun readEndChar(): Boolean {
		var lastChar: Char = this.buffer.get(this.index)
		when (lastChar) {
			',' -> {
				readChar()
				return true
			}

			'}' -> {
				readChar()
				this.nestingLevel--
				if (0 == this.nestingLevel && this.index != this.buffer.size) {
					throw Exception("invalid string${this.buffer.size}")
				} else if (this.index < this.buffer.size) {
					readChar()
					return true
				} else {
					return false
				}
			}

			else -> {

				throw Exception("parse error")
			}
		}
	}

	private fun readData(): Any? {
		var firstChar: Char = this.buffer.get(this.index)
		when (firstChar) {
		//string
			'\"' -> {
				return readString()
			}
		//json array
			'[' -> {

			}
		//json object
			'{' -> {
				this.nestingLevel++
				return readJsonObject()
			}
		//number
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
				return readNumber()
			}
		//null
			'n' -> {
				return readNull()
			}
		//literal
			't', 'f' -> {
				return readLiteral()
			}
		}
		throw Exception("Parse Error")
	}

	private fun readJsonObject(): JsonObject {
		var result = JsonObject()
		readChar()
		var name = readName()
		var data = readData()
		result.add(name, data)
		if ('}'.equals(buffer.get(this.index))) {
			readChar()
			this.nestingLevel--
		}
		return result
	}

	private fun readLiteral(): Boolean {
		var str: String = readChar().toString()
		str += readChar().toString()
		str += readChar().toString()
		str += readChar().toString()

		if (str.equals("true")) {
			return true
		}
		str += readChar().toString()
		if (str.equals("false")) {
			return false
		}
		throw Exception("invalid literal")
	}

	private fun readNull(): Any? {
		var str: String = readChar().toString()
		str += readChar().toString()
		str += readChar().toString()
		str += readChar().toString()

		if (!str.equals("null")) {
			throw Exception("invalid null")
		}
		return null
	}

	private fun readNumber(): Any {
		var str: String = ""
		var isDigit: Boolean = false
		do {
			var c = readChar()
			str += c
			if (c.equals(".")) {
				isDigit = true
			}
		} while (!this.buffer.get(this.index).equals(',') && !this.buffer.get(this.index).equals('}'))

		if (!isDigit) {
			var a: Int? = str.toIntOrNull()
			if (null == a) throw Exception("invalid number") else return a
		} else {
			var a: Double? = str.toDoubleOrNull()
			if (null == a) throw Exception("invalid number") else return a
		}
	}

	private fun readString(): String {
		if (!readChar().equals('\"')) {
			throw Exception("")
		}
		var str: String = readChar().toString()
		do {
			str += readChar()
		} while (!buffer.get(this.index).equals('\"'))
		readChar()
		return str
	}


	private fun readName(): String {
		if (!readChar().equals('\"')) {
			throw Exception("")
		}
		var name: String = readChar().toString()
		do {
			name += readChar()
		} while (!this.buffer.get(this.index).equals('\"'))
		readChar()
		if (!readChar().equals(':')) {
			throw Exception("")
		}
		return name
	}

	private fun init(jsonString: String) {
		this.buffer = jsonString.toCharArray()
		this.currentOffset = 0
		this.index = 0
		this.nestingLevel = 0
	}


	private fun readChar(): Char {
		return this.buffer.get(this.index++)
	}
}