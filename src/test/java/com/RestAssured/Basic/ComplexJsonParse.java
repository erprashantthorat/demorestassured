package com.RestAssured.Basic;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String args[]) {
		JsonPath js = new JsonPath(Payload.coursePrice());

		// Print no of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		//Print purchase amount
	int totalAmount=	js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	//
	//print title of first course
	String courseName=js.getString("courses[2].title");
	System.out.println(courseName);
	// print all course title and respective ourse prices
	System.out.println("CourseName"+"  "+ "CoursePrice");
		for(int i=0;i<count;i++)
		{
			String name =js.get("courses["+i+"].title");
			int price =js.getInt("courses["+i+"].price");
			System.out.println(name+"          "+ price);
		}
	
		int sum=0;
		//print no copies sold RPA
		for(int i=0;i<count;i++)
		{
			String name =js.get("courses["+i+"].title");
		  if(name.equals("RPA"))
		  {
			  int copycount = js.getInt("courses["+i+"].copies");
			  System.out.println("Copy of RPA: "+ copycount);
			  break;
		  }
		}
		
		//verify all courses purchased sum 
	
		for(int i=0;i<count;i++)
		{
			int prices = js.getInt("courses["+i+"].price");
			int no = js.getInt("courses["+i+"].copies");
			int amount = prices*no;
			sum=sum+amount;
			 
		}
	int purchaseamt =js.getInt("dashboard.purchaseAmount");
		System.out.println(	sum);
	  Assert.assertEquals(sum, purchaseamt);
	
		
	}	
	
	
}
