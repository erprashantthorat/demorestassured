package com.RestAssured.Basic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	
	public String id;
	@Test(dataProvider="BookData")
	public void addBook(String isbn,String aisle)
	{
	
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(Payload.addBook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js =ReUsableMethods.rawToJson(response);
		id= js.get("ID");
		System.out.println(id);
		//deleteBook
		
	}
	
	@Test
	public void dBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		 given().header("Content-Type","application/json").body("{\r\n"
		 		+ " \"ID\":\""+id+"\"\r\n"
		 		+ "}").
		 when().delete("/Library/DeleteBook.php").
		 then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	
	@DataProvider(name="BookData")
	public Object[][] getData()
	{
		Object[][] data = {{"sec","1764"},{"tii","0146"},{"tsi","1373"},{"tsbw","3350"}};
		return data;
		
		
	}
	
}
