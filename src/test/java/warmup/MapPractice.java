package warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapPractice {
	public static void main(String[] args) {
		
		 
		/*
		 *  HashMap can have 1 null key and many null value
		 * HashTable can not have any null key and null value
		 * HashMap is not synchronized
		 * HashTable is synchronized
		 *
		 */
		Map<String, String> price = new HashMap();
		
		price.put("coke", "$2");
		price.put("candy", "$1.5");
		price.put("cookie", "$4");
		price.put("icecream", "$5");
		price.put("chocolate", "$2.3");
		
		Set<Entry<String, String>> entries = price.entrySet();
		
		for(Entry<String, String> each : entries) {
			System.out.print(each.getKey() + " ");
			System.out.println(each.getValue());
			System.out.println();
		}
		
		System.out.println(price);
	}
}
