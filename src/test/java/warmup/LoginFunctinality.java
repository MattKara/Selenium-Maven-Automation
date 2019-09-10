package warmup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginFunctinality {
	
	WebDriver driver;
	@BeforeClass // runs once for all tests
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(ConfigReader.getMyValue("URL"));
	}
	
	@Test
	public void login() {
		driver.findElement(By.name("email")).sendKeys(ConfigReader.getMyValue("User"));
		driver.findElement(By.name("passwd")).sendKeys(ConfigReader.getMyValue("Password"));
		driver.findElement(By.id("SubmitLogin")).click();
	}
}
