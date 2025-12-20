package com.webtrix24.rental.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterComponent
{
	
	 private WebDriver driver;
	    private WebDriverWait wait;

	    public FilterComponent(WebDriver driver) 
	    {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    // ===========================
	    // 1. Open a specific filter field
	    // ===========================
	    public void openFilterField(String fieldName) {
	        String fieldXpath = "//div[contains(@class,'divide-y')]//span[normalize-space()='"
	                             + fieldName + "']";

	        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath(fieldXpath)
	        ));
	        field.click();
	    }

	    // ===========================
	    // 2. Select condition (Equal To, Not Equal To, Is Empty...)
	    // ===========================
	    public void selectCondition(String conditionText) {

	        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class,'absolute')]//select")
	        ));

	        Select select = new Select(dropdown);
	        select.selectByVisibleText(conditionText);
	    }

	    // ===========================
	    // 3. Select checkbox (In inventory, On rent, Damaged, Sold...)
	    // ===========================
	    public void selectValue(String checkboxLabel) {

	        String checkboxXpath =
	                "//div[contains(@class,'absolute')]//label[normalize-space()='" 
	                + checkboxLabel + "']/input";

	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath(checkboxXpath)
	        ));

	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }

	    // ===========================
	    // 4. Click Apply button
	    // ===========================
	    public void clickApply() {
	        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[contains(@class,'absolute')]//button[contains(text(),'Apply')]")
	        ));
	        applyBtn.click();
	    }

	    // ===========================
	    // 5. Click Cancel button (if needed)
	    // ===========================
	    public void clickCancel() {
	        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[contains(@class,'absolute')]//button[contains(text(),'Cancel')]")
	        ));
	        cancelBtn.click();
	    }

}
