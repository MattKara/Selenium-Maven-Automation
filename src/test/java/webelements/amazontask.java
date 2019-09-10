package webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class amazontask {

	WebDriver driver;
	@BeforeClass // runs once for all tests
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void getAllProducts() {
		driver.get("https://www.amazon.com/s?k=wooden+spoons&crid=HGR0PN15O3U5&sprefix=wooden+spo%2Caps%2C154&ref=nb_sb_ss_i_1_10");
		
		List<WebElement> descriptions = driver.findElements(By.xpath("//h2"));
		for (WebElement item : descriptions) {
			System.out.println(item.getText());
		}
		
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='a-price']"));
		for(WebElement spoon : prices)   {
			System.out.println(spoon.getText());
		}
		
		List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='a-section a-spacing-medium']"));
		System.out.println("WholeItems size: " + wholeItems);
		for(int i = 0 ; i < wholeItems.size(); i++) {
			if(wholeItems.get(i).getText().isEmpty()) continue;
			String desXpath = "(//div[@class='a-section a-spacing-medium'])[" + (i+1) + "]//h2";
			String priceXpath = "(//div[@class='a-section a-spacing-medium'])[" + (i+1) + "]//span[@class='a-price']";
			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("-----------------------------");
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
