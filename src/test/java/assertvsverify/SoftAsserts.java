package assertvsverify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	
	
	
	@Test
	public void test1() {
		SoftAssert softAssert = new SoftAssert();
		int i = 100;
		int j = 200;
		
		System.out.println("First Assertion: ");
		softAssert.assertEquals(i, j, "First Assertion: Fail");
		
		System.out.println("Second Assertion: ");
		softAssert.assertNotEquals(i, j, "Second Assertion: Fail");
		
		System.out.println("Third Assertion");
		softAssert.assertTrue(i > j, "Third Assertion: Fail");
		
		softAssert.assertAll();
	}
}
