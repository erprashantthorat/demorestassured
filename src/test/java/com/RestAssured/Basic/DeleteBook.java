package com.RestAssured.Basic;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DeleteBook {

	@Test
	public void deleteBook()
	{
		String response= RestAssured.baseURI="http://216.10.245.166";
		given().header("Content-Type","application/json").body("");
		when().delete("/Library/Deletebook.php").
		then().log().all().assertThat().extract().asString();
		JsonPath js = ReUsableMethods.rawToJson(response);
		String resp =js.get("msg");
		System.out.println(resp);
	}
}
