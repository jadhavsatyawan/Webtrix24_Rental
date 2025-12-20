package com.webtrix24.rental.utils;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchandselectproductDropdownUtility 
{
	WebDriver driver;

    public SearchandselectproductDropdownUtility(WebDriver driver) 
    {
        this.driver = driver;
    }
    

    /**
     * Handles searchable dropdowns dynamically
     * @param driver - WebDriver instance
     * @param dropdownInput - WebElement of the dropdown input field
     * @param searchValue - Value to type in dropdown (can be "random" for random selection)
     * @param resultListLocator - By locator for the suggestion list elements
     */

    
    
    
    public static void selectFromSearchableDropdown(WebDriver driver, WebElement dropdownInput, String searchValue, By resultListLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on dropdown
        dropdownInput.click();

        // Step 2: Type search text (to trigger suggestions)
        if (!searchValue.equalsIgnoreCase("random")) {
            dropdownInput.sendKeys(searchValue);
        } else {
            dropdownInput.sendKeys("a"); // ✅ trigger the dropdown list
        }

        // Step 3: Wait for results
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultListLocator));

        // Step 4: Choose option
        WebElement selectedElement;
        if (searchValue.equalsIgnoreCase("random")) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(results.size());
            selectedElement = results.get(randomIndex);
        } else {
            selectedElement = results.get(0);
        }

        // Step 5: Click and log
        selectedElement.click();
        System.out.println("✅ Selected from dropdown: " + selectedElement.getText());
    }
    
    
    
    public static String selectProductDC(WebDriver driver, WebElement dropdownInput, String searchValue, By resultListLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        dropdownInput.click();

        if (!searchValue.equalsIgnoreCase("random")) {
            dropdownInput.sendKeys(searchValue);
        } else {
            dropdownInput.sendKeys("a"); // open dropdown list
        }

        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultListLocator));

        WebElement selectedElement = null;
        Random rand = new Random();

        if (searchValue.equalsIgnoreCase("random")) {
            // find random item which is selectable
            for (int i = 0; i < results.size(); i++) {
                WebElement element = results.get(rand.nextInt(results.size()));
                String classAttr = element.getAttribute("class");
                if (!classAttr.contains("cursor-not-allowed") && !classAttr.contains("opacity-40")) {
                    selectedElement = element;
                    break;
                }
            }
            if (selectedElement == null) {
                return "⚠ No available product found.";
            }
        } else {
            selectedElement = results.get(0);
        }

        try {
            selectedElement.click();
            String text = selectedElement.getText().trim();
            return "✅ Selected: " + text;
        } catch (Exception e) {
            try {
                String reason = selectedElement.findElement(By.xpath(".//div[contains(@class,'text-red-400')]")).getText();
                return "❌ Not selectable: " + reason;
            } catch (Exception ex) {
                return "❌ Not selectable: unknown reason";
            }
        }
    }
/*public static void selectFromSearchableDropdown(WebDriver driver, WebElement dropdownInput, String searchValue, By resultListLocator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Step 1: Click on dropdown to activate it
    dropdownInput.click();

    // Step 2: Type search text (if not random)
    if (!searchValue.equalsIgnoreCase("random")) {
        dropdownInput.sendKeys(searchValue);
    } else {
        dropdownInput.sendKeys(" "); // to trigger list open
    }

    // Step 3: Wait for results to appear
    List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultListLocator));

    // Step 4: Handle selection type
    WebElement selectedElement;
    if (searchValue.equalsIgnoreCase("random")) {
        // Random selection
        Random rand = new Random();
        int randomIndex = rand.nextInt(results.size());
        selectedElement = results.get(randomIndex);
    } else {
        // If specific name or serial number provided — pick first match
        selectedElement = results.get(0);
    }

    // Step 5: Click the selected element
    selectedElement.click();

    // Optional log
    System.out.println("✅ Selected from dropdown: " + selectedElement.getText());
}  */

}
