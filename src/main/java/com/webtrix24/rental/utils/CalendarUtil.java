package com.webtrix24.rental.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarUtil {
	

	    private WebDriver driver;

	    public CalendarUtil(WebDriver driver) {
	        this.driver = driver;
	    }

	    // 📅 Select Current Date
	    public void selectCurrentDate() {
	        WebElement today = driver.findElement(By.xpath(
	            "//div[contains(@class,'react-datepicker__day--today') and not(contains(@class,'--disabled'))]"
	        ));
	        today.click();
	    }

	    // 📅 Select Date by String (Example: "01-09-2025" or "2025-09-01")
	    public void selectDateByInput(String dateString) {
	        // Accepts formats like "dd-MM-yyyy" or "yyyy-MM-dd"
	        DateTimeFormatter formatter;

	        if (dateString.contains("-")) {
	            if (dateString.indexOf('-') == 2)
	                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            else
	                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        } else {
	            throw new IllegalArgumentException("Please provide date in valid format: dd-MM-yyyy or yyyy-MM-dd");
	        }

	        LocalDate targetDate = LocalDate.parse(dateString, formatter);
	        selectDate(targetDate);
	    }

	    // 📅 Select Date (Past or Future)
	    private void selectDate(LocalDate targetDate) {
	        String monthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
	                + " " + targetDate.getYear();
	        String day = String.valueOf(targetDate.getDayOfMonth());

	        // 🔄 Navigate to correct month-year
	        while (true) {
	            String displayedMonthYear = driver.findElement(By.cssSelector(".react-datepicker__current-month")).getText();
	            if (displayedMonthYear.equalsIgnoreCase(monthYear)) break;

	            // Click next or previous as needed
	            String[] parts = displayedMonthYear.split(" ");
	            LocalDate displayedDate = LocalDate.parse("01-" + parts[0].substring(0, 3) + "-" + parts[1],
	                    DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH));

	            if (displayedDate.isBefore(targetDate)) {
	                driver.findElement(By.cssSelector(".react-datepicker__navigation--next")).click();
	            } else {
	                driver.findElement(By.cssSelector(".react-datepicker__navigation--previous")).click();
	            }
	        }

	        // 🗓 Click the day
	        WebElement dayElement = driver.findElement(By.xpath(
	            "//div[contains(@class,'react-datepicker__day') and text()='" + day + "']" +
	            "[not(contains(@class,'--disabled'))]"
	        ));
	        dayElement.click();
	    }
	}



