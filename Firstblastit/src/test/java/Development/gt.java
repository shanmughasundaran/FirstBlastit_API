package Development;

public class gt {
//	
	
		
public static void main(String[] args) {
	int indexToInsert= 13;
	StringBuilder s1= new StringBuilder("{\r\n"
			+ "    \"otp\": ,\r\n"
			+ "    \"type\": 1,\r\n"
			+ "    \"phone\":7708926252\r\n"
			+ "}");
	StringBuilder s2= new StringBuilder("9800");

	s1.insert(indexToInsert, s2);
	
	System.out.println(s1);
	

}
}
//	
//	 String strObj = "{\r\n"
//	 		+ "    \"otp\": 39456,\r\n"
//	 		+ "    \"type\": 1,\r\n"
//	 		+ "    \"phone\":7708926252\r\n"
//	 		+ "}";
	
//	public static void main(String[] args) {
//		
//		// TODO Auto-generated method stub
		

	


