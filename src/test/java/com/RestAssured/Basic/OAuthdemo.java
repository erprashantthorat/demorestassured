package com.RestAssured.Basic;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import files.CodeTrimmer;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;

public class OAuthdemo {

	public static void main(String[] args) {
		
		/*
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo."
		 * +
		 * "email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&"
		 * +
		 * "response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdds"
		 * ); driver.findElement(By.cssSelector("[type='email']")).sendKeys(
		 * "thoratprashant75@gmail.com");
		 * driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		 * driver.findElement(By.cssSelector("[type='password']")).sendKeys("Phoenix@92"
		 * ); driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		 */
		CodeTrimmer ct  = new CodeTrimmer();
		String curUrl ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdds&code=4%2F0AWgavdeU6Er8JSohCGXuAWlMx7BFLjY25Embpivgc2fOX2jZHNdnJZEhM1C6qVuKvcPRhA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		String code = ct.codetrim(curUrl);
		
		
		
		
		String accessTokenRes = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accessTokenRes);
		JsonPath js = new JsonPath(accessTokenRes);
		
	String accessToken= 	js.getString("access_token");
		System.out.println(accessToken);
		String response = given().queryParams("access_token",accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}

}
