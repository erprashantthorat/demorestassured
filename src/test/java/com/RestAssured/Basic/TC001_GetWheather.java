package com.RestAssured.Basic;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GetWheather {

	@Test
	void getWhatherDetail() {
		// Specify Base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	//Specify Request object
	RequestSpecification httprequest = RestAssured.given();
	//Specify Response object
	Response response =	httprequest.request(Method.GET,"/Rahuri");
	// displaying response inn console
	String responseBody = response.getBody().asString();
	System.out.println("Response Body:"+ responseBody);
	
	//Status Code validation 
	
	int statusCode = response.getStatusCode();
	System.out.println("Status Code: "+statusCode);
	
	String statusLine = response.getStatusLine();
	System.out.println("Status Line:"+statusLine);
	Assert.assertEquals(statusCode, 200);
	//Assert.assertEquals(statusLine, "https")
}
}
