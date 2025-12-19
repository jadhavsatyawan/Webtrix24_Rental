package com.webtrix24.rental.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.webtrix24.rental.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	
	  protected WebDriver driver;

	    @BeforeMethod
	    public void setUp() {

	        String browser = ConfigReader.getProperty("browser");
	        String url = ConfigReader.getProperty("app.url");

	        if (browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	        }

	        driver.manage().window().maximize();
	        driver.get(url);
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

}
