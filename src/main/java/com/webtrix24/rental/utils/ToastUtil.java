package com.webtrix24.rental.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToastUtil {

	WebDriver driver;

	public ToastUtil(WebDriver driver) {
		this.driver = driver;
	}

	// ✅ Reusable method to capture any toast message text
	public String captureToastMessage() {
		By toastLocator = By.xpath("//div[@role='alert']"); // common for all toasts

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String message = null;

		try {
			WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
			message = toast.getText().trim();
			System.out.println("Captured Toast: " + message);

		} catch (org.openqa.selenium.TimeoutException e) { // ✅ Fully qualified class name
			System.out.println(" Toast not displayed within 10 seconds!");
		} catch (Exception e) { // ✅ Generic backup
			System.out.println(" Error capturing toast: " + e.getMessage());
		}

		return message;
	}

	public void waitForToastToDisappear() {
		By toastLocator = By.xpath("//div[@role='alert']");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
			System.out.println("Toast disappeared");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Toast did not disappear within time");
		}
	}

	public void waitForUIStability() {
		try {
			Thread.sleep(600); // small controlled pause
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
