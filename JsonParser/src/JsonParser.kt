package Json

import kotlin.io.*

//json parser 가 상태 값을 갖지 않도록 하기 위해 Parser 클래스 분리
class JsonParser {
	fun parse(reader: String): Any? {
		val parser: Parser = Parser(reader)
		return parser.getResult()
	}
}


//파싱 하고 있는 string에 대한 상태를 갖고 있으며 파싱 메소드들을 포함
class Parser(message: String) {
	var index = 1

	fun isSpace(c: Char): Boolean {
		return c == ' ' || c == '\r' || c == '\n' || c == '\t'
	}

	private val reader = message.reader().buffered()
	private var next: Char?

	init {
		val c = reader.read()
		next = if (c == -1) null else c.toChar()
	}

	private fun nextChar(): Char {
		if (isDone()) throw IllegalStateException("Cannot get next char: EOF reached")
		val c = next!!
		next = reader.read().let { if (it == -1) null else it.toChar() }
		index++
		return c
	}

	private fun peekChar(): Char {
		if (isDone()) throw IllegalStateException("Cannot peek next char: EOF reached")
		return next!!
	}

	fun isDone(): Boolean = next == null


	private fun readString(): String {
		var str: String = nextChar().toString()
		do {
			str += nextChar()
		} while (!next!!.equals('\"'))
		return str
	}

	private fun readNumber(): Any {
		var str: String = ""
		var isDigit: Boolean = false
		do {
			var c = nextChar()
			str += c
			if (c.toChar().equals('.')) {
				isDigit = true
			}
		} while (!next!!.equals(',') && !next!!.equals('}') && !next!!.equals(']'))

		if (!isDigit) {
			var a: Int? = str.toIntOrNull()
			if (null == a) throw Exception("invalid number") else return a
		} else {
			var a: Double? = str.toDoubleOrNull()
			if (null == a) throw Exception("invalid number") else return a
		}
	}


	private fun readNull(): Any? {
		var str: String = nextChar().toString()
		str += nextChar()
		str += nextChar()
		str += nextChar()

		if (!str.toLowerCase().equals("null")) {
			throw Exception("invalid null")
		}
		return null
	}


	private fun readJsonArray(): JsonArray {
		var result = JsonArray()
		var leftBrace = nextChar()
		do {
			val value = readValue()
			result.add(value)
			if (",".equals(next.toString())) {
				nextChar()
			}
		} while (!next!!.equals(']'))
		var rightBrace = nextChar()

		if (leftBrace.equals("[") || rightBrace.equals("]")) {
			throw Exception("Invalid string")
		}

		return result
	}

	private fun readJsonObject(): JsonObject {
		var result = JsonObject()

		var leftBrace = nextChar()

		do {
			var leftBracket = nextChar()

			var key = readString()
			var rightBracket = nextChar()
			var collon = nextChar()

			if (leftBracket.equals("\"") || rightBracket.equals("\"") || collon.equals(":")) {
				throw Exception("Invalid string")
			}

			var value = readValue()
			result.add(key, value)
			if (",".equals(next.toString())) {
				nextChar()
			}
		} while (!next!!.equals('}'))

		var rightBrace = nextChar()


		if (leftBrace.equals("{") || rightBrace.equals("}")) {
			throw Exception("Invalid string")
		}

		return result
	}

	private fun readValue(): Any? {
		var c: Char = peekChar()
		when (c) {
		//string
			'\"' -> {
				var leftBrace = nextChar()
				var value = readString()
				var rightBrace = nextChar()

				if (leftBrace.equals("\"") || rightBrace.equals("\"")) {
					throw Exception("Invalid string")
				}
				return value
			}
		//json array
			'[' -> {
				return readJsonArray()
			}
		//json object
			'{' -> {
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

	private fun readLiteral(): Boolean {
		var str: String = nextChar().toString()
		str += nextChar()
		str += nextChar()
		str += nextChar()

		if ("true".equals(str.toLowerCase())) {
			return true
		} else if ("fals".equals(str.toLowerCase()) && "e".equals(peekChar())) {
			nextChar()
			return false
		} else {
			throw Exception("invalid literal")
		}
	}

	fun getResult(): Any? {
		if ('{'.equals(next)) {
			return readJsonObject()
		} else if ('['.equals(next)) {
			return readJsonArray()
		}
		return null
	}
}
