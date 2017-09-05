fun main(args: Array<String>) {
	var writer: JsonWriter = JsonWriter()
	var jobj = JsonObject()
	jobj.add("천정필", 29)
	jobj.add("심현우", 30)
	jobj.write(writer)
	println("json array serialize 		==> ${writer.string}")

	var jobj3 = JsonObject()
	jobj3.add("이동우", 99)


	var writer2: JsonWriter = JsonWriter()
	var jobj2 = JsonObject()
	jobj2.add("idiots", jobj)
	jobj2.add("genius", jobj3)
	jobj2.add("null test", null)
	jobj2.write(writer2)
	println("nested json object serialize    ==> ${writer2.string}")


	var writer4: JsonWriter = JsonWriter()
	var jarr = JsonArray()
	jarr.add(123)
	jarr.add("asd")
	jarr.add(0.323)
	jarr.add(true)
	jarr.write(writer4)
	println("json array serialize 		==> ${writer4.string}")

	var parser: JsonParser = JsonParser()
	var parsedJobj: JsonObject = parser.parse(writer2.string).asObject()
	var genius: JsonObject? = parsedJobj.get("genius")?.asObject()
	var writer5: JsonWriter = JsonWriter()
	var writer6: JsonWriter = JsonWriter()

		println("json object deserialize 	jsonstr : ${writer2.string}	parsedJobj : ${parsedJobj.write(writer6)}	genius : ==> ${genius?.write(writer5)}")
	
}