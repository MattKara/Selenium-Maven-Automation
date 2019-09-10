package warmup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			prop.load(fis);
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("fairfax"));
	}

}
