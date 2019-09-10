package webtables;

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

public class WebOrders {
	String url = "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?";
	static WebDriver driver;

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(url);
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	@Test
	public void getWebElements() {
		List<WebElement> names = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr"));
			//System.out.println(driver.findElement(By.xpath("//table[@class='SampleTable']//tr[1]/th[2]")).getText());
		for(int rowNum= 2; rowNum <= names.size(); rowNum++) {
			String xpath = "//table[@class='SampleTable']//tr["+rowNum+"]/td[2]";
			String tdData = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Name: " + tdData);
		}
	}
	
	public static void getDataFromTable(String header) {
		String xpathHeader = "";
		switch(header) {
		case "Name":
			xpathHeader ="//table[@class='SampleTable']//tr/td[2]";
			break;
		case "Product":
			xpathHeader ="//table[@class='SampleTable']//tr/td[3]";
			break;
		case "Date":
			xpathHeader = "//table[@class='SampleTable']//tr/td[5]";
			break;
		case "Street":
			xpathHeader = "//table[@class='SampleTable']//tr/td[6]";
			break;
		case "City":
			xpathHeader = "//table[@class='SampleTable']//tr/td[7]";
			break;
		default:
			xpathHeader = "//table[@class='SampleTable']//tr/td[2]";
		}
		
		List<WebElement> data = driver.findElements(By.xpath(xpathHeader));
		for(WebElement d: data) {
			System.out.println(header + ": " + d.getText());
		}
	}
	
	public void deleteRow(String name) {
		String xpathName = "//table[@class='SampleTable']//tr/td[2]"; 

		List<WebElement> data = driver.findElements(By.xpath(xpathName));
		for(WebElement d: data) {
			if(d.getText()==name) {
				driver.findElement(By.xpath("//input[@id='ctl00_MainContent_orderGrid_ctl02_OrderSelector']")).click();
				driver.findElement(By.xpath("//input[@id='ctl00_MainContent_btnDelete']")).click();
			}
		}
		driver.findElement(By.xpath("//input[@id='ctl00_MainContent_orderGrid_ctl02_OrderSelector']")).click();
		driver.findElement(By.xpath("//input[@id='ctl00_MainContent_btnDelete']")).click();
	}
	
	public static boolean checkName(String name) {
		String xpathName = "//table[@class='SampleTable']//tr/td[2]";
		List<WebElement> data = driver.findElements(By.xpath(xpathName));
			for(WebElement d : data) {
				if(d.getText().equalsIgnoreCase(name))
					return true;
			}
			return false;
	}	
	
	@Test
	public void getData() {
		getDataFromTable("Street");
		getDataFromTable("Name");
		getDataFromTable("City");
	}
	
	@Test
	public void deleteData() {
		deleteRow("Paul Brown");
		deleteRow("Mark Smith");
		deleteRow("Steve Johns");
		deleteRow("Charles Dodgeson");
		deleteRow("Susan McLaren");
		deleteRow("Bob Feather");
		System.out.println("Check name is displayed: " + checkName("Paul Brown"));	
	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
}
