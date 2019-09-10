package webtables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {
	String url = "file:///Users/murat/eclipse-workspace/selenium-maven-automation/src/test/java/webtables/webtable.html";
	WebDriver driver;

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void readScores() {
		driver.get(url);
		//read whole webtable data
		WebElement table = driver.findElement(By.tagName("table"));
		System.out.println(table.getText());
		
		//find out how many rows in the table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
		System.out.println("number of data rows: " + rows.size());
		
		//print all table headers one by one
		List<WebElement> headers = driver.findElements(By.xpath("//table[@id='worldcup']/thead//th"));
		
		List<String> expHeaders = Arrays.asList("Team1", "Score", "Team2");
		List<String> actHeaders = new ArrayList();
		
		for(WebElement header: headers) {
			actHeaders.add(header.getText());
		}
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actHeaders, expHeaders);
		
		//Write xpath and find element gettext --> needs to print Egypt
		String egyptPath = "//table[@id='worldcup']/tbody/tr[3]/td[3]";		
		//System.out.println(driver.findElement(By.xpath(egyptPath)).getText());
		soft.assertEquals(driver.findElement(By.xpath(egyptPath)).getText(), "Egypt");
		
		//loop it and print all data
		//get numbers of rows and columns and then nested loop
		int rowsCount = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th")).size();
		
		
		System.out.println("=============================");
		for(int rowNum=1; rowNum <= rowsCount; rowNum++) {
			for(int colNum=1; colNum <= colsCount; colNum++) {
				String xpath = "//table[@id='worldcup']/tbody/tr[" + rowNum +"]/td[" + colNum + "]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.print(tdData + "  \t");
			}
			System.out.println();
		}
		
		soft.assertAll();
	}
	
	@Test
	public void applicantsData() {
		driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
		printTableData("reportTab");
	}
	
	public void printTableData(String id) {
		int rowsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/thead/tr/th")).size();
		
		
		System.out.println("=============================");
		for(int rowNum=1; rowNum <= rowsCount; rowNum++) {
			for(int colNum=1; colNum <= colsCount; colNum++) {
				String xpath = "//table[@id='"+id+"']/tbody/tr[" + rowNum +"]/td[" + colNum + "]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.print(tdData + "---");
			}
			System.out.println();
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
	
}
