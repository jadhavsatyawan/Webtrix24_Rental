package com.webtrix24.rental.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReactCalendarUtil 
{
	 WebDriver driver;

	    public ReactCalendarUtil(WebDriver driver) {
	        this.driver = driver;
	    }

	    // ✅ Select Random Date (Past/Future/Current)
	    public void selectRandomDate(By dateInput, String type) throws InterruptedException {
	        driver.findElement(dateInput).click();

	        LocalDate today = LocalDate.now();
	        LocalDate targetDate;

	        int randomDays = (int) (Math.random() * 10) + 1; // random 1–10 days
	        switch (type.toLowerCase()) {
	            case "past":
	                targetDate = today.minusDays(randomDays);
	                break;
	            case "future":
	                targetDate = today.plusDays(randomDays);
	                break;
	            default:
	                targetDate = today;
	                break;
	        }

	        selectMonthYearAndDay(targetDate);
	        System.out.println("✅ Selected random " + type + " date: " + targetDate);
	    }

	    // ✅ Select Specific Date (yyyy-MM-dd)
	    public void selectSpecificDate(By dateInput, String date) throws InterruptedException {
	        driver.findElement(dateInput).click();

	        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        selectMonthYearAndDay(targetDate);
	        System.out.println("✅ Selected specific date: " + targetDate);
	    }

	    private void selectMonthYearAndDay(LocalDate targetDate) throws InterruptedException {
	        try {
	            WebElement monthDropdown = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
	            WebElement yearDropdown = driver.findElement(By.cssSelector(".react-datepicker__year-select"));

	            monthDropdown.sendKeys(String.valueOf(targetDate.getMonth()));
	            yearDropdown.sendKeys(String.valueOf(targetDate.getYear()));
	        } catch (Exception e) {
	            // Handle case where datepicker doesn't have dropdowns
	        }

	        Thread.sleep(500);
	        selectDay(targetDate.getDayOfMonth());
	    }

	    private void selectDay(int day) {
	        List<WebElement> allDays = driver.findElements(By.cssSelector(".react-datepicker__day"));
	        for (WebElement d : allDays) {
	            if (d.getText().trim().equals(String.valueOf(day)) && d.isEnabled()) {
	                d.click();
	                break;
	            }
	        }
	    }

}
