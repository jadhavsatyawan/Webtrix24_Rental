package com.webtrix24.rental.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDownUtilNew {

	public static void selectFromDropdown(WebDriver driver, WebElement dropdownInput, String valueToSelect) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1️⃣ Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(dropdownInput)).click();

		// 2️⃣ Type to filter list
		dropdownInput.clear();
		dropdownInput.sendKeys(valueToSelect);

		// 3️⃣ Wait for matching option (REAL option div)
		By optionLocator = By.xpath(
				"//div[@role='option']//span[normalize-space()='" + valueToSelect + "']/ancestor::div[@role='option']");

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		// 4️⃣ REAL mouse click (this updates React state)
		option.click();

		// 5️⃣ Verify value is locked in input
		wait.until(d -> valueToSelect.equals(dropdownInput.getAttribute("value")));
	}

}
