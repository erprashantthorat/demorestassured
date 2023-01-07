package com.RestAssured.Basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReUsableMethods;

import static io.restassured.RestAssured.*;

public class BasicAPI {
	
	public static void main(String args[]) throws IOException
	{
		
		// validate add pace api is working as expected
		// given --> all input details 
		//when --> submit api
		//then-->validate the resource
		//content of the file into string --> convert into bytes--> byte data to string 
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes((Paths.get("C:\\Users\\Prashant\\eclipse-workspace\\RestAssuredProject\\src\\test\\java\\files\\Addplace.json")))))
		
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);// for parsing json
		String placeid =js.getString("place_id");
		String Address= "81 Hill view Street";
		System.out.println(placeid);
		// add place update with new address--> get place to validate if new address is present in response
		
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"+
				"\"place_id\":\""+placeid+"\",\r\n"+
				"\"address\":\""+Address+"\",\r\n"+
				"\"key\":\"qaclick123\"\r\n"+
						"}").
			when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	 
	//Get Place
String getPlaceResponse =	given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id",placeid).when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1 =ReUsableMethods.rawToJson(getPlaceResponse);
	String actualaddress = js1.getString("address");
	System.out.println(actualaddress);
	Assert.assertEquals(actualaddress, actualaddress);
	
	}
	

	
	

}
