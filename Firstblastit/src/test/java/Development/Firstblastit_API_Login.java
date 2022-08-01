


	package Development;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Iterator;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.ResponseBody;
	import io.restassured.specification.RequestSpecification;

	public class Firstblastit_API_Login {
		FileInputStream fl;
		XSSFWorkbook wb;
		XSSFSheet st;
		
		@Test(priority=1,enabled=true)
		public void login() throws IOException {
			
		
		//XSSFReader read = new XSSFReader("C:\\Users\\\\\\\\Sparkout QA\\\\\\\\eclipse-workspace\\\\\\\\Firstpageit_API\\\\\\\\Data\\\\\\\\API_Data.xlsx");
			
			RestAssured.baseURI=("https://dev.firstblastit.com");
			RequestSpecification request = RestAssured.given();
			fl = new FileInputStream("C:\\Users\\Sparkout QA\\git\\FirstBlastit_API\\Firstblastit\\Data\\API_Data.xlsx");
			System.out.println("Sheet path " + fl);
			wb = new XSSFWorkbook(fl);
			 st = wb.getSheet("Login");
		    System.out.println("sheet = "+ st);
			Iterator<Row> iterator  = st.rowIterator();
		   Row sheet = st.getRow(1);
		     String payload1 = sheet.getCell(2).getStringCellValue();
			 System.out.println("cell = "+ payload1);
			request.headers("Content-Type","application/json");
			io.restassured.response.Response  Method  = request.body(payload1).when().post("/api/register-phone");
			ResponseBody body = Method.body();
			String responsebody = body.asString();
			int Response_status=Method.getStatusCode();
			String pritty = body.asPrettyString();
			System.out.println("Receiveed_Response ="+ pritty);
			 //Evaluating the Response
			JsonPath js = Method.jsonPath();
			// Get specific element from JSON document
			String Get_Authorization = js.get("body.token");
			Assert.assertTrue(responsebody.contains("message"));
			String Response_Message = js.get("message");
			// Validate if the specific JSON element is equal to expected value
		    Assert.assertTrue(Response_Message.equals("Login successfully"));
		    Assert.assertTrue(responsebody.contains("otp"));
		    Assert.assertTrue(responsebody.contains("authId"));
		    Assert.assertTrue(responsebody.contains("email"));
	        String   Response_email = js.get("email");
	        System.out.println("resulted:" +Response_email);
	        boolean  onllj = responsebody.contains("null");
			if(onllj = responsebody.contains("null")){
	        System.out.println("Registered User");}
			else {
		  System.out.println("New User");}
	 	//Write
			XSSFRow so= st.getRow(1);
			Cell cell1=so.createCell(5);
			 cell1.setCellValue(responsebody);
			 Cell cell2=so.createCell(4);
			cell2.setCellValue(Get_Authorization);
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Sparkout QA\\git\\FirstBlastit_API\\Firstblastit\\Data\\API_Data.xlsx");
			wb.write(fileOut);
			//fileOut.close();
		}
	

}
