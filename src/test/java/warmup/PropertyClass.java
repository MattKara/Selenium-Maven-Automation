package warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

public class PropertyClass {

	public static void main(String[] args) {
		
		// properties class is sub class or HashTable class(one of map implementation)
		Properties prop = new Properties();
		prop.setProperty("Tyson", "20282");
		prop.setProperty("Fairfax", "20212");
		prop.setProperty("Centreville", "20120");
		prop.setProperty("City", "21304");
		prop.setProperty("Detroit", "48320");
		prop.setProperty("Fairfax", "20212");
		prop.setProperty("Centreville", "20120");
		prop.setProperty("City", "21304");
		
		System.out.println(prop);
		System.out.println(prop.getProperty("Tyson"));

		// add few items and iterate over it just like map earlier
		Set<Entry<Object, Object>> entries = prop.entrySet();

		for (Entry<Object, Object> each : entries) {
			System.out.print(each);
			System.out.println(prop);
		}

		

	}

}
