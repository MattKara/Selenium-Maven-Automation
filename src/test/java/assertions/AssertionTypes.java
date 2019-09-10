package assertions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionTypes {
	
	@Test
	public void test1() {
		String expectedName = "James";
		int expectedAge = 28;
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("Ronaldo", expectedName, "Player's name is not matching");
		soft.assertEquals(29, expectedAge, "Player's age is not matching");
		System.out.println("Ending the test");
		soft.assertAll();
	}
	
	@Test
	public void w() {
		SoftAssert soft = new SoftAssert();
		System.out.println("second test method starting");
		String s = "Hi";
		soft.assertEquals("Hi", s);
		soft.assertAll();
	}

}
