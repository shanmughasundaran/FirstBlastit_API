package Development;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class New_User extends Rough {
	XSSFWorkbook wb;
	XSSFSheet st;
	
	
	@Test(enabled=true)
	public void OTP_Verification() throws IOException {
		
		//Rough rough=new Rough();
		
		RestAssured.baseURI=("https://dev.firstblastit.com");
		Header acceptHeader = new Header("Accept","application/json");
		Header contentTypeHeader = new Header ("Content-Type","application/json");
		
		
		ArrayList<Header> header = new ArrayList<>();
		header.add(contentTypeHeader);
		header.add(acceptHeader);
		
		Headers allheaders = new Headers(header);
		
		
		//RequestSpecification request = RestAssured.given().headers(allheaders);
		
		RequestSpecification request = RestAssured.given().headers(allheaders);
		FileInputStream fl = new FileInputStream(output);
		System.out.println("Sheet path " + fl);
		wb = new XSSFWorkbook(fl);
		 st = wb.getSheet("NewUser");
	    System.out.println("sheet = "+ st);
//		Iterator<Row> iterator  = st.rowIterator();
	   Row sheet = st.getRow(1);
	   st = wb.getSheet("Login");
	   //Getting Authorization id from parent 
	   Row Get_parent_sheetdetails_auth = st.getRow(1);
	   String get_parent_sheet_auth = Get_parent_sheetdetails_auth.getCell(4).getStringCellValue();
	 //Getting otp id from parent 
	   Row Get_parent_sheetdetails_otp = st.getRow(1);
	   String get_parent_sheet_otp = Get_parent_sheetdetails_otp.getCell(6).getStringCellValue();
	   System.out.println("Got:"+ get_parent_sheet_otp);
	   //Getting paylod from new sheet
	   st = wb.getSheet("NewUser");
	   Row get_reg_payload = st.getRow(1);
	   
	   String get_reg_payload_cell = get_reg_payload.getCell(2).getStringCellValue();
    
	     System.out.println("cell value :" +get_reg_payload_cell);
	     //appending the otp into payload
	     
	     int indexToInsert= 13;
	 	StringBuilder s1= new StringBuilder(get_reg_payload_cell);
	 	
	 	StringBuilder s2= new StringBuilder(get_parent_sheet_otp);

	 s1.insert(indexToInsert, s2.toString());
	 String raw_payload= s1.toString();
	
//	String some =  main.getClass();
	
	 	
	 	//System.out.println(main);
	 	
//	String otp = "{\r\n"
//			+ "    \"otp\": 14153,\r\n"
//			+ "    \"type\": 1,\r\n"
//			+ "    \"phone\":7708926252\r\n"
//			+ "}";

		
		
	 	request.header("Authorization","Bearer " +get_parent_sheet_auth);
		io.restassured.response.Response  Method  = request.log().all().body(raw_payload).when().post("/api/verify-otp");
////		io.restassured.response.Response  Method   =request.body(s1).given().log().all().contentType("application/x-www-form-urlencoded; charset=UTF-8")
////        .redirects().follow(false).redirects().max(100).body(s1).when().post("/api/verify-otp");

		System.out.println(Method.getStatusCode());
		ResponseBody body = Method.body();
		responsebody = body.asString();
		int Response_status=Method.getStatusCode();
		String pritty = body.asPrettyString();
		System.out.println("Receiveed_Response ="+ pritty);
	}
	
		
		
	}



