package com.webtrix24.rental.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownUtil {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	JavascriptExecutor js;

	public DropdownUtil(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
	}

	/** Generic method to select value from React searchable dropdown **/

	/*
	 * public void selectFromSearchableDropdown(WebElement input, String
	 * valueToSelect) {
	 * 
	 * WebElement inputBox =
	 * wait.until(ExpectedConditions.elementToBeClickable(input)); inputBox.click();
	 * 
	 * inputBox.sendKeys(Keys.CONTROL + "a"); inputBox.sendKeys(Keys.DELETE);
	 * inputBox.sendKeys(valueToSelect);
	 * 
	 * By optionsLocator =
	 * By.xpath("//div[@role='option'][.//span[normalize-space()!='']]");
	 * 
	 * // WAIT for React dropdown render
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator))
	 * ;
	 * 
	 * List<WebElement> options = driver.findElements(optionsLocator);
	 * 
	 * for (WebElement option : options) {
	 * 
	 * String text = option.getText().trim();
	 * 
	 * // if (text.equalsIgnoreCase(valueToSelect)) { if
	 * (text.toLowerCase().contains(valueToSelect.toLowerCase())) {
	 * 
	 * js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	 * 
	 * actions.moveToElement(option).pause(Duration.ofMillis(200)).click().perform()
	 * ;
	 * 
	 * inputBox.sendKeys(Keys.TAB); // blur
	 * 
	 * // confirm dropdown closed (state committed)
	 * wait.until(ExpectedConditions.attributeToBe(inputBox, "aria-expanded",
	 * "false"));
	 * 
	 * System.out.println("Dropdown selected properly: " + text); return; } }
	 * 
	 * throw new RuntimeException("Dropdown value not found: " + valueToSelect); }
	 */

	/*
	 * public void selectFromSearchableDropdown(WebElement input, String
	 * valueToSelect) {
	 * 
	 * WebElement inputBox =
	 * wait.until(ExpectedConditions.elementToBeClickable(input)); inputBox.click();
	 * 
	 * // ✅ FIX: Clear existing selected value using BACKSPACE (React-safe) String
	 * existingValue = inputBox.getAttribute("value"); if (existingValue != null &&
	 * !existingValue.isEmpty()) { for (int i = 0; i < existingValue.length(); i++)
	 * { inputBox.sendKeys(Keys.BACK_SPACE); } }
	 * 
	 * // Type new value inputBox.sendKeys(valueToSelect);
	 * 
	 * By optionsLocator =
	 * By.xpath("//div[@role='option'][.//span[normalize-space()!='']]");
	 * 
	 * // WAIT for React dropdown render
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator))
	 * ;
	 * 
	 * List<WebElement> options = driver.findElements(optionsLocator);
	 * 
	 * for (WebElement option : options) {
	 * 
	 * String text = option.getText().trim();
	 * 
	 * if (text.toLowerCase().contains(valueToSelect.toLowerCase())) {
	 * 
	 * js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	 * 
	 * actions.moveToElement(option).pause(Duration.ofMillis(200)).click().perform()
	 * ;
	 * 
	 * inputBox.sendKeys(Keys.TAB); // blur
	 * 
	 * // confirm dropdown closed (state committed)
	 * wait.until(ExpectedConditions.attributeToBe(inputBox, "aria-expanded",
	 * "false"));
	 * 
	 * System.out.println("Dropdown selected properly: " + text); return; } }
	 * 
	 * throw new RuntimeException("Dropdown value not found: " + valueToSelect); }
	 */

	/*
	 * public void selectFromSearchableDropdown(WebElement input, String
	 * valueToSelect) {
	 * 
	 * // 1️⃣ Focus field (DO NOT clear, DO NOT type) WebElement inputBox =
	 * wait.until(ExpectedConditions.elementToBeClickable(input)); inputBox.click();
	 * 
	 * // 2️⃣ Wait for dropdown options By optionsLocator =
	 * By.xpath("//div[@role='option'][.//span[normalize-space()!='']]");
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator))
	 * ;
	 * 
	 * List<WebElement> options = driver.findElements(optionsLocator);
	 * 
	 * // 3️⃣ Click matching option (this REPLACES old value) for (WebElement option
	 * : options) {
	 * 
	 * String text = option.getText().trim();
	 * 
	 * if (text.equalsIgnoreCase(valueToSelect)) {
	 * 
	 * js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	 * actions.moveToElement(option).pause(Duration.ofMillis(200)).click().perform()
	 * ;
	 * 
	 * System.out.println("Dropdown selected properly: " + text); return; } }
	 * 
	 * throw new RuntimeException("Dropdown value not found: " + valueToSelect); }
	 */
	public void selectFromSearchableDropdown(WebElement input, String valueToSelect) {

		// 1️⃣ Click field (focus)
		WebElement inputBox = wait.until(ExpectedConditions.elementToBeClickable(input));
		inputBox.click();

		// 2️⃣ If value already selected → click X (clear button)
		try {
			WebElement clearBtn = inputBox.findElement(By.xpath("./following-sibling::button"));
			clearBtn.click();

			// wait until value is cleared
			wait.until(ExpectedConditions.attributeToBe(inputBox, "value", ""));
		} catch (Exception e) {
			// No clear button → field already empty
		}

		// 3️⃣ Type value to trigger dropdown filtering (manual behaviour)
		inputBox.sendKeys(valueToSelect);

		// 4️⃣ Wait for dropdown options
		By optionsLocator = By.xpath("//div[@role='option']");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

		List<WebElement> options = driver.findElements(optionsLocator);

		// 5️⃣ Select matching option from list
		for (WebElement option : options) {
			String text = option.getText().trim();

			if (text.equalsIgnoreCase(valueToSelect)) {
				js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
				actions.moveToElement(option).pause(Duration.ofMillis(150)).click().perform();

				System.out.println("Dropdown value selected: " + text);
				return;
			}
		}

		throw new RuntimeException("Dropdown value not found: " + valueToSelect);
	}

}
