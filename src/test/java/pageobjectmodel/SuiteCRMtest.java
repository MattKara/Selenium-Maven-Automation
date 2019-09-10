package pageobjectmodel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagessuitecrm.HomePage;
import pagessuitecrm.LoginPage;

public class SuiteCRMtest {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen(); 
	}
	
	@Test(priority=1)
	public void test1() {
		driver.get("http://softaculous.com/demos/SuiteCRM");
		driver.switchTo().frame("demobody");
		LoginPage lp = new LoginPage(driver);
		
		lp.login("admin", "pass");
		
		HomePage hp = new HomePage(driver);
		hp.textBox.sendKeys("Admin says hello to world!" + Keys.ENTER);
	}
	
}
