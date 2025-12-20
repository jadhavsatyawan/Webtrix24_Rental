package com.webtrix24.rental.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DatePickerUtil 
{
	 WebDriver driver;

	    public DatePickerUtil(WebDriver driver) {
	        this.driver = driver;
	    }

	    /**
	     * Select date in React Datepicker using format YYYY-MM-DD
	     * Example: 2025-10-30
	     */
	    public void selectDate(By dateInputLocator, String dateString) {
	        try {
	            // Step 1️⃣: Locate input
	            WebElement dateInput = driver.findElement(dateInputLocator);
	            
	            // Step 2️⃣: Use JavaScript to set value directly
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].value = arguments[1];", dateInput, dateString);
	            
	            // Step 3️⃣: Trigger change event (important for React apps)
	            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", dateInput);

	            System.out.println("✅ Date set successfully: " + dateString);

	        } catch (Exception e) {
	            System.out.println("❌ Failed to set date: " + e.getMessage());
	        }
	    }

}
