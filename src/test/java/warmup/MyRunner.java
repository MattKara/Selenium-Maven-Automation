package warmup;

public class MyRunner {
	
	public static void main(String[] args) {
		//String url = configloader,getmyvalue("a key in here";
		//driver.get(url);
		
		System.out.println(ConfigReader.getMyValue("DC"));
		System.out.println(ConfigReader.getMyValue("url"));
	}
}
