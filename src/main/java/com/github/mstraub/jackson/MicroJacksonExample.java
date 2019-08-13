package com.github.mstraub.jackson;
//package com.github.jackson;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.github.sproute.routeformat.Vehicle;
//import com.github.sproute.routeformat.Vehicle.VehicleType;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
//import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
//
//public class MicroJacksonExample {
//
//	public static final String schemaFile = "/tmp/routeformat-schema.json";
//	public static final String exampleFile = "/tmp/routeformat-example.json";
//	
//	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//		MicroJacksonExample main = new MicroJacksonExample();
//		main.writeExampleJson();
//		main.readExampleJson();
//		main.writeSchema();
//	}
//
//	public void writeExampleJson() throws JsonGenerationException, JsonMappingException, IOException {
////		Vehicle root = Vehicle.builder().withArrivalTime("2015-01-01T10:15:30+01:00").build();
//		Vehicle root = Vehicle.builder().withType(VehicleType.SHARED_CAR).withElectric(true).withId("W-12345Z").build();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.registerModule(new Jdk8Module());
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//		
//		System.out.println(mapper.writeValueAsString(root));
//		mapper.writeValue(new File(exampleFile), root);
//	}
//	
//	public void readExampleJson() throws JsonParseException, JsonMappingException, IOException {
//		// variant 1 - data binding
//		ObjectMapper mapper = new ObjectMapper();
//		Vehicle root = mapper.readValue(new File(exampleFile), Vehicle.class);
//	}
//	
//	public void writeSchema() throws JsonGenerationException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
//		mapper.acceptJsonFormatVisitor(mapper.constructType(Vehicle.class), visitor);
//		JsonSchema jsonSchema = visitor.finalSchema();
//		jsonSchema.set$schema("http://json-schema.org/draft-04/schema#");
//		
//		System.out.println(mapper.writeValueAsString(jsonSchema));
//		mapper.writeValue(new File(schemaFile), jsonSchema);
//	}
//	
//}
