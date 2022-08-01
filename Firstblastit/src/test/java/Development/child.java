package Development;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class child extends Rough  {
	@Test
	public void chilclass() throws FileNotFoundException {
		
		BaseURL  = RestAssured.baseURI=(BaseURL);
		RequestSpecification request = RestAssured.given();
		FileInputStream fl = new FileInputStream(output);
		System.out.println("Sheet path " + fl);
		
		
		
	}
}
