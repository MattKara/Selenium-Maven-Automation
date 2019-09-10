package assertions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Statista {

	WebDriver driver;
	@BeforeClass // runs once for all tests
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void navigator() throws InterruptedException {
		driver.get("https://www.statista.com/");
		List<WebElement> navigators = driver.findElements(By.xpath("//div[@class='pageHeader__itemNavSmall pageHeader__itemNavSmall--en']/nav/ul/li"));
		
		Actions action = new Actions(driver);
		for(int i = 0 ; i < navigators.size(); i++) {
			action.moveToElement(navigators.get(i)).perform();
			Thread.sleep(2000);
		}
	}
}
