import com.beust.klaxon.Parser

fun main(args: Array<String>) {
	var jobj = JsonObject()
	jobj.add("천정필", 29)
	jobj.add("심현우", 30)
	println("json array serialize 		==> ${jobj.toJsonString()}")

	var jobj3 = JsonObject()
	jobj3.add("이동우", 99)


	var jobj2 = JsonObject()
	jobj2.add("idiots", jobj)
	jobj2.add("genius", jobj3)
	jobj2.add("null test", null)

	println("nested json object serialize    ==> ${jobj2.toJsonString()}")


	var jarr = JsonArray()
	jarr.add(123)
	jarr.add("asd")
	jarr.add(0.323)
	jarr.add(true)
	println("json array serialize 		==> ${jarr.toJsonString()}")

	var parser: JsonParser = JsonParser()
	var parsedJobj: JsonObject = parser.parse(StringBuilder(jobj2.toJsonString())) as JsonObject
	var geniusJobj: JsonObject? = parsedJobj.get("genius")?.asObject()


	var parsedJarr: JsonArray = parser.parse(StringBuilder(jarr.toJsonString())) as JsonArray


	println("json object deserialize 	input jsonstr : ${jobj2.toJsonString()}	parsedJobj : ${parsedJobj.toJsonString()}	genius : ==> ${geniusJobj?.toJsonString()}")


}