package com.webtrix24.rental.utils;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownUtil 
{
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
    
    
    
    
   /* public void selectFromSearchableDropdown(WebElement input, String valueToSelect) throws InterruptedException {
        // ✅ 1. Wait until any SweetAlert2 popup disappears
        try {
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            popupWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".swal2-container")));
            popupWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".swal2-html-container")));
            System.out.println("✅ All popups closed before interacting with dropdown.");
        } catch (Exception e) {
            System.out.println("⚠ No active popup found, proceeding...");
        }

        // ✅ 2. Wait until dropdown input is clickable
        WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(input));

        // ✅ 3. Use JS click to avoid page scroll or interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input1);
        Thread.sleep(500);

        // ✅ 4. Clear and enter value
        input1.sendKeys(Keys.CONTROL + "a");
        input1.sendKeys(Keys.DELETE);
        input1.sendKeys(valueToSelect);
        Thread.sleep(1200); // allow dropdown options to load

        // ✅ 5. Locate dropdown options
        By optionsLocator = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");
        List<WebElement> options = driver.findElements(optionsLocator);

        if (options.isEmpty()) {
            System.out.println("⚠ No options found initially, waiting...");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator));
            options = driver.findElements(optionsLocator);
        }

        boolean found = false;
        for (WebElement opt : options) {
            String text = opt.getText().trim();
            System.out.println("🔍 Found option: " + text);
            if (text.equalsIgnoreCase(valueToSelect)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opt);
                
                // ✅ Click via JS instead of normal click (avoids intercept)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
                System.out.println("✅ Selected: " + text);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("❌ Dropdown option not found: " + valueToSelect);
        }
    } */


    
    
      public void selectFromSearchableDropdown(WebElement input, String valueToSelect) throws InterruptedException {//By inputLocator
        WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(input));//inputLocator
        input1.click();
        Thread.sleep(500);

        input1.sendKeys(Keys.CONTROL + "a"); // clear old value
        input1.sendKeys(Keys.DELETE);
        input1.sendKeys(valueToSelect);
        Thread.sleep(1200); // allow suggestions to load

        // ✅ Updated XPath - handles <div><span> or plain <div> text, ignores empty items
        By optionsLocator = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");
        List<WebElement> options = driver.findElements(optionsLocator);

        if (options.isEmpty()) {
            System.out.println("⚠ No options found initially, waiting...");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator));
            options = driver.findElements(optionsLocator);
        }

        boolean found = false;
        for (WebElement opt : options) {
            String text = opt.getText().trim();
            System.out.println("🔍 Found option: " + text);
            if (text.equalsIgnoreCase(valueToSelect)) {
                js.executeScript("arguments[0].scrollIntoView(true);", opt);
                opt.click();
                System.out.println("✅ Selected: " + text);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("⚠ Retrying after scroll...");
            try {
                WebElement scrollableDiv = driver.findElement(By.xpath("//div[contains(@style,'overflow: auto')]"));
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollableDiv);
                Thread.sleep(800);
                List<WebElement> retryOptions = driver.findElements(optionsLocator);
                for (WebElement opt : retryOptions) {
                    String text = opt.getText().trim();
                    if (text.equalsIgnoreCase(valueToSelect)) {
                        js.executeScript("arguments[0].scrollIntoView(true);", opt);
                        opt.click();
                        System.out.println("✅ Selected after retry: " + text);
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("⚠ Scroll retry failed: " + e.getMessage());
            }
        }

        if (!found) {
            throw new RuntimeException("❌ Dropdown option not found: " + valueToSelect);
        } 
        
      }
        
      
      
      
      // ✅ Random Dropdown Selection
      public void selectRandomFromSearchableDropdown(WebElement input) throws InterruptedException {
          WebElement input1 = wait.until(ExpectedConditions.elementToBeClickable(input));
          input1.click();
          Thread.sleep(800);

          By optionsLocator = By.xpath("//div[contains(@class,'cursor-pointer')]//span[normalize-space(text())!='']");
          List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator));

          if (options.isEmpty()) {
              throw new RuntimeException("❌ No dropdown options found!");
          }

          Random random = new Random();
          int randomIndex = random.nextInt(options.size());
          WebElement randomOption = options.get(randomIndex);

          js.executeScript("arguments[0].scrollIntoView(true);", randomOption);
          String selectedText = randomOption.getText().trim();
          randomOption.click();

          System.out.println("🎲 Randomly selected dropdown value: " + selectedText);
      }
        
//    public void selectFromSearchableDropdown(By inputLocator, String valueToSelect) throws InterruptedException {
//    	
//    	
//        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
//        input.click();
//        Thread.sleep(500);
//
//        input.sendKeys(valueToSelect);
//        Thread.sleep(1000);
//
//        By optionsLocator = By.xpath("//div[contains(@class,'cursor-pointer')]/span");
//        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));
//
//        boolean found = false;
//        for (WebElement opt : options) {
//            String text = opt.getText().trim();
//            if (text.equalsIgnoreCase(valueToSelect)) {
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opt);
//                opt.click();
//                System.out.println("✅ Selected: " + text);
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            // Scroll to bottom and retry
//            WebElement scrollableDiv = driver.findElement(By.xpath("//div[contains(@style,'overflow: auto')]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollableDiv);
//            Thread.sleep(800);
//            selectFromSearchableDropdown(inputLocator, valueToSelect); // retry
//        }
//    }
//    
//    
//    
    
    
    
    
  /*  public void selectFromSearchableDropdown(By inputLocator, String valueToSelect) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.click();
        Thread.sleep(500);

        input.sendKeys(valueToSelect);
        Thread.sleep(1000);

        // ✅ Modified XPath: ensures only visible non-empty options are captured
        By optionsLocator = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

        boolean found = false;
        for (WebElement opt : options) {
            String text = opt.getText().trim();
            System.out.println("🔍 Found option: " + text);
            if (text.equalsIgnoreCase(valueToSelect)) {
                js.executeScript("arguments[0].scrollIntoView(true);", opt);
                opt.click();
                System.out.println("✅ Selected: " + text);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("⚠ Retrying selection after scrolling...");
            try {
                WebElement scrollableDiv = driver.findElement(By.xpath("//div[contains(@style,'overflow: auto')]"));
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollableDiv);
                Thread.sleep(800);
                List<WebElement> retryOptions = driver.findElements(optionsLocator);
                for (WebElement opt : retryOptions) {
                    String text = opt.getText().trim();
                    if (text.equalsIgnoreCase(valueToSelect)) {
                        js.executeScript("arguments[0].scrollIntoView(true);", opt);
                        opt.click();
                        System.out.println("✅ Selected after retry: " + text);
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("⚠ Scroll retry failed: " + e.getMessage());
            }
        }

        if (!found) {
            throw new RuntimeException("❌ Dropdown option not found: " + valueToSelect);
        }
    }
}
  */
    
    
    /**
     * Generic method to select a value from a searchable dropdown.
     * Supports optional refresh button inside dropdown.
     * 
     * @param inputLocator Locator for dropdown input box
     * @param dropdownOptionsLocator Locator for dropdown options
     * @param valueToSelect Text to select
     * @param refreshButtonXpath Optional refresh button (pass null if not needed)
     */
  /*  public void selectFromDropdown(By inputLocator, By dropdownOptionsLocator, String valueToSelect, String refreshButtonXpath) {
        try {
            // 1️⃣ Click on dropdown input
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            input.click();
            Thread.sleep(500);

            // 2️⃣ Optional refresh
            if (refreshButtonXpath != null) {
                try {
                    WebElement refreshBtn = driver.findElement(By.xpath(refreshButtonXpath));
                    if (refreshBtn.isDisplayed()) {
                        refreshBtn.click();
                        System.out.println("🔄 Refreshed dropdown list");
                        Thread.sleep(1500);
                    }
                } catch (Exception e) {
                    System.out.println("⚠ Refresh button not found — skipping");
                }
            }

            // 3️⃣ Wait for options to appear
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptionsLocator));

            // 4️⃣ Find and click desired option
            boolean found = false;
            for (WebElement option : options) {
                String text = option.getText().trim();
                System.out.println("🔍 Found option: " + text);
                if (text.equalsIgnoreCase(valueToSelect)) {
                    js.executeScript("arguments[0].scrollIntoView(true);", option);
                    option.click();
                    System.out.println("✅ Selected: " + text);
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new RuntimeException("❌ Option not found in dropdown: " + valueToSelect);
            }

        } catch (Exception e) {
            throw new RuntimeException("⚠ Dropdown selection failed for: " + valueToSelect + " | " + e.getMessage());
        }
    }*/


}

