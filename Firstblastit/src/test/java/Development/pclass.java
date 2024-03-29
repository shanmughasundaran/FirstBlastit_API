package Development;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class pclass {
	
	public static String BaseURL = ("https://dev.firstblastit.com");
	public static String output = ("C:\\\\\\\\Users\\\\\\\\Sparkout QA\\\\\\\\git\\\\\\\\FirstBlastit_API\\\\\\\\Firstblastit\\\\\\\\Data\\\\\\\\API_Data.xlsx");
	
	//public static String file_location = "C:\\\\\\\\Users\\\\\\\\Sparkout QA\\\\\\\\git\\\\\\\\FirstBlastit_API\\\\\\\\Firstblastit\\\\\\\\Data\\\\\\\\API_Data.xlsx";

	XSSFWorkbook wb;
	XSSFSheet st;
	String responsebody;
	public boolean  validate;
	public 	String otp ;
	public String pritty;
	
	
@Test
	public void rough() throws IOException {
		
	
	//XSSFReader read = new XSSFReader("C:\\Users\\\\\\\\Sparkout QA\\\\\\\\eclipse-workspace\\\\\\\\Firstpageit_API\\\\\\\\Data\\\\\\\\API_Data.xlsx");
		
		BaseURL  = RestAssured.baseURI=(BaseURL);
		Header acceptHeader = new Header("Accept","application/json");
		Header contentTypeHeader = new Header ("Content-Type","application/json");
		
		ArrayList<Header> header = new ArrayList<>();
		header.add(contentTypeHeader);
		header.add(acceptHeader);
		
		Headers allheaders = new Headers(header);
		
			
		RequestSpecification request = RestAssured.given().headers(allheaders);
		
		FileInputStream fl = new FileInputStream("C:\\\\Users\\\\Sparkout QA\\\\git\\\\FirstBlastit_API\\\\Firstblastit\\\\Data\\\\API_Data.xlsx");
		

		System.out.println("Sheet path " + fl);
		wb = new XSSFWorkbook(fl);
		 st = wb.getSheet("Login");
	    System.out.println("sheet = "+ st);
		Iterator<Row> iterator  = st.rowIterator();
	   Row sheet = st.getRow(1);
	     String payload1 = sheet.getCell(2).getStringCellValue();
		 System.out.println("cell = "+ payload1);
		//request.headers("Content-Type","application/json");
		io.restassured.response.Response  Method  = request.body(payload1).redirects().follow(true).when().post("/api/register-phone");
		ResponseBody body = Method.body();
		responsebody = body.asString();
		int Response_status=Method.getStatusCode();
		String pritty = body.asPrettyString();
		System.out.println("Receiveed_Response ="+ pritty);
		 //Evaluating the Response
		JsonPath js = Method.jsonPath();
		// Get specific element from JSON document
		String Get_Authorization = js.get("body.token");
		otp = js.getString("body.otp");
		Assert.assertTrue(responsebody.contains("message"));
		String Response_Message = js.get("message");
		
		
		// Validate if the specific JSON element is equal to expected value
	    Assert.assertTrue(Response_Message.equals("Login successfully"));
	    Assert.assertTrue(responsebody.contains("otp"));
	    
	    Assert.assertTrue(responsebody.contains("authId"));
	    Assert.assertTrue(responsebody.contains("email"));
	    String Email = js.get("body.email");
	    System.out.println("printing :"+Email);
         String  Response_email = js.get("email");
         System.out.println("resulted:" +Response_email);
         validate = responsebody.contains("null");
			if(validate = responsebody.contains("null")){
	        System.out.println("Registered User");}
			else {
		  System.out.println("New User");}
 	//Write
		XSSFRow so= st.getRow(1);
		Cell cell1=so.createCell(5);
		 cell1.setCellValue(responsebody);
		 Cell cell2=so.createCell(4);
		cell2.setCellValue(Get_Authorization);
		//
		XSSFRow so1= st.getRow(1);
		Cell cell3=so.createCell(6);	
		cell3.setCellValue(otp);
		
		
		
		FileOutputStream fileOut = new FileOutputStream("C:\\\\Users\\\\Sparkout QA\\\\git\\\\FirstBlastit_API\\\\Firstblastit\\\\Data\\\\API_Data.xlsx");
		wb.write(fileOut);
		

		
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return validate;
	
	
		
		
		
		
		
		
		

	
}
}
