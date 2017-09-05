abstract class JsonValue {


	abstract fun write(writer : JsonWriter) : String
	open fun isNumber(): Boolean {
		return false
	}

	open fun isArray(): Boolean {
		return false
	}

	open fun isString(): Boolean {
		return false
	}

	open fun isTrue(): Boolean {
		return false
	}

	open fun isFalse(): Boolean {
		return false
	}

	open fun isNull(): Boolean {
		return false
	}

	open fun asInt(): Int {
		throw Exception("Not a number")
	}

	open fun asLong(): Long {
		throw Exception("Not a number")
	}

	open fun asFloat(): Float {
		throw Exception("Not a number")
	}

	open fun asDouble(): Double {
		throw Exception("Not a number")
	}

	open fun asString(): String {
		throw Exception("Not a String")
	}

	open fun asBollean(): Boolean {
		throw Exception("Not a Boolean")
	}

	open fun asArray(): JsonArray {
		throw Exception("Not an Array")
	}

	open fun asObject(): JsonObject {
		throw Exception("Not an Object")
	}


}