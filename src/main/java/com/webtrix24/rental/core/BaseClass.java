package com.webtrix24.rental.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import com.webtrix24.rental.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver;

	@BeforeClass
	public void setUp() throws InterruptedException {

		String browser = ConfigReader.getProperty("browser");
		String url = ConfigReader.getProperty("app.url");

		if (browser == null) {
			throw new RuntimeException("Browser value is NULL in config.properties");
		}

		browser = browser.trim().toLowerCase();

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Unsupported browser: [" + browser + "]");
		}

		driver.manage().window().maximize();
		driver.get(url);
	}

	public static WebDriver getDriver() {
		return driver;
	}
	/*
	 * public String captureScreen(String tname) throws IOException { String
	 * timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	 * 
	 * TakesScreenshot takesScreenshot =(TakesScreenshot) driver; File sourceFile =
	 * takesScreenshot.getScreenshotAs(OutputType.FILE);
	 * 
	 * String targetFilePath =
	 * System.getProperty("user.dir")+"\\Screenshots\\"+ tname + "_"+ timeStamp + "
	 * .png"; File targetFile = new File(targetFilePath);
	 * 
	 * sourceFile.renameTo(targetFile);
	 * 
	 * return targetFilePath; }
	 */

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) BaseClass.getDriver();
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetDir = System.getProperty("user.dir") + File.separator + "Screenshots";
		new File(targetDir).mkdirs(); // auto create folder

		String targetFilePath = targetDir + File.separator + tname + "_" + timeStamp + ".png";

		FileUtils.copyFile(sourceFile, new File(targetFilePath));

		return targetFilePath;
	}

	// @AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
