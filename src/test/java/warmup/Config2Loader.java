package warmup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Config2Loader {
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config2.properties");
			prop.load(fis);
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		String[] str = {"Chicken", "Beef", "Lamb", "Ghallaba", "Tikka", "Water", "Soda", "Coffee", "Tea"};
		System.out.println(prop);
			for(int i = 1; i <=str.length; i++) {
				System.out.print(prop);
				System.out.println(prop.getProperty(str[i]));
				System.out.println();
			}
	}

}
