package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	ProductsPage productsPage;
	String userId = "Tester";
	String password = "test";

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?");
		loginPage = new WebOrdersLoginPage(driver);
	}

	@Test(description = "Verify labels and tab links are displayed")
	public void labelsVerification() {
		assertEquals(driver.getTitle(), "Web Orders Login", "Login Page is not displayed. Application is down.");
		// loginPage.username.sendKeys(userId);
		// loginPage.password.sendKeys(password);
		// loginPage.loginButton.click();

		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);

		assertTrue(allOrdersPage.webOrders.isDisplayed(), "Web Orders is not displayed!");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List of All Orders is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "View All Orders is not displayed!");
		assertTrue(allOrdersPage.ViewAllProducts.isDisplayed(), "View All Products is not displayed!");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "Order tab is not displayed!");
	}

	@Test(description = "Verify default products and prices")
	public void availableProducts() {
		assertEquals(driver.getTitle(), "Web Orders Login", "Login Page is not displayed. Application is down.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.ViewAllProducts.click();
		productsPage = new ProductsPage(driver);
		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList();

		// productsPage.productsNames.forEach(elem -> actProducts.add(elem.getText()));

		for (WebElement prod : productsPage.productsNames) {
			actProducts.add(prod.getText());
			System.out.println(prod.getText());
		}
		
		assertEquals(actProducts, expProducts);
		
		// product prices 
		List<String> expPrices = Arrays.asList("$100", "$80", "$20");
		List<String> actPrices = new ArrayList();

		for (WebElement prod : productsPage.productsPrices) {
			actPrices.add(prod.getText());
			System.out.println(prod.getText());
		}

		assertEquals(actPrices, expPrices);
		
		//product discount
		List<String> expDiscounts = Arrays.asList("8%", "15%", "10%");
		List<String> actDiscounts = new ArrayList();

		for (WebElement prod : productsPage.productsDiscounts) {
			actDiscounts.add(prod.getText());
			System.out.println(prod.getText());
		}

		assertEquals(actDiscounts, expDiscounts);
		
	}

	// logout after each test
	@AfterMethod
	public void logout() {
		allOrdersPage.logout();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
