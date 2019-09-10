package warmup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static void main(String[] args) {
		// 1. path
		String path = "/Users/murat/eclipse-workspace/selenium-maven-automation/webordersconfig.properties";
		//file
		try {
			FileInputStream file = new FileInputStream(path);
			Properties pr = new Properties();
			pr.load(file);
			
		} catch(IOException e) {
			System.out.println("File is not found");
			e.printStackTrace();
	    }
		
		//file reader

	}

}
