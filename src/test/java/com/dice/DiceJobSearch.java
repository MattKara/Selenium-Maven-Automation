package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {

		// Set up chrome driver path using boni garcia's tool
		WebDriverManager.chromedriver().setup(); // instead of from chrome or gecko driver, we can use this
		// invoke Selenium Webdriver
		WebDriver driver = new ChromeDriver();

		// full screen
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// step 1 launch browser and navigate to https://www.dice.com/. Expected: dice
		// home page should be displayed
		String url = "https://www.dice.com/";
		driver.get(url);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Find Jobs in Tech | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASSED. Dice homepage successfully loaded.");
		} else {
			System.out.println("Step FAILED. Dice homepage did not load successfully.");
			throw new RuntimeException("Step FAILED. Dice homepage did not load successfully.");
		}

		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();

		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		// ensure count is more than 0
		int countResult = Integer.parseInt(count.replace(",", ""));

		if (countResult > 0) {
			System.out.println(
					"Step PASS: Keyword: " + keyword + " search returned " + countResult + " results in " + location);
		} else {
			System.out.println(
					"Step FAIL: Keyword: " + keyword + " search returned " + countResult + " results in " + location);
		}

		driver.close();
		
		System.out.println("TEST COMPLETED - " + LocalDateTime.now());
	}

}
