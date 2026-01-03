package com.webtrix24.rental.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;


public class ProductListPage extends BasePage 
{
	
	/***************************** Constructor **************************************************/

	public ProductListPage(WebDriver driver) 
	{
		super(driver);
		
	}
		
	/***************************** Locators **************************************************/
	
	//Product Recent Activity
	@FindBy(xpath = "//button//span[@class=\"text-md\" and text()='Product Recent Activity']")
	WebElement ProductRecentActivity;
	
	@FindBy(xpath = "//input[contains(@class,'product-search__input')]")
	WebElement productSearchBox;

	@FindBy(xpath = "//div[contains(@class,'product-search__menu-list')]//div[@role='option']")
	List<WebElement> ProdropdownOptions;
  
	By ProdropdownOptionsList = By.xpath("//div[contains(@class,'product-search__menu-list')]//div[@role='option']");
	
	@FindBy(xpath = "//button[@class='flex items-center gap-1 px-3 py-1 border rounded-md text-sm border-gray-200 text-indigo-600 hover:bg-indigo-50']")
	WebElement printBarcode;

    private By addProductBtn = By.xpath("//button[contains(.,'Add Product')]");
    private By searchInput = By.xpath("//input[@placeholder='Search']");
    private By searchBtn = By.xpath("//button[contains(.,'Search')]");
    
    // Table Locators
    private By tableRows = By.xpath("//table//tbody//tr");
    private By productNameCol = By.xpath("//table//tbody//tr/td[2]"); 
    private By editButtons = By.xpath("//table//tbody//tr//button[contains(.,'Edit')]");
    private By deleteButtons = By.xpath("//table//tbody//tr//button[contains(.,'Delete')]");

    // Pagination
    private By nextPageBtn = By.xpath("//button[contains(.,'Next')]");
    private By prevPageBtn = By.xpath("//button[contains(.,'Previous')]");

	/***************************** Action Methods **************************************************/
    
    //Search & Select Product By Serial Number in Product Recent Activity
    public void selectProductBySerial(String serialNumber) throws InterruptedException 
    {
    		ProductRecentActivity.click();
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    JavascriptExecutor js = (JavascriptExecutor) driver;

    	    // Step 1️⃣: Clear and Type in search box
    	    productSearchBox.clear();
    	    productSearchBox.sendKeys(serialNumber);
    	    System.out.println("Searching for: " + serialNumber);
    	    
    	    Thread.sleep(1000);
    	    productSearchBox.sendKeys(Keys.ARROW_DOWN);   // Move to first result
    	    productSearchBox.sendKeys(Keys.ENTER);        // Press Enter to select it
    }
    
    //Random Product Search & Select in Product Recent Activity  
    public void selectRandomProduct() 
    {
     try 
     {
    	 ProductRecentActivity.click();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         Random random = new Random();

         // Step 1️: Click and type a random character to trigger the dropdown list
         productSearchBox.click();
         String randomChar = String.valueOf((char) ('A' + random.nextInt(26))); // Random A–Z letter
         productSearchBox.sendKeys(randomChar);
         System.out.println("Typed random letter: " + randomChar);

         // Step 2️: Wait for dropdown options to appear
         wait.until(ExpectedConditions.visibilityOfAllElements(ProdropdownOptions));

         // Step 3️: Get all available options
         List<WebElement> options = ProdropdownOptions;
         System.out.println("Total options found: " + options.size());

         // Step 4️: Randomly pick one option
         if (!options.isEmpty()) 
         {
             int randomIndex = random.nextInt(options.size());
             WebElement selectedOption = options.get(randomIndex);
             js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
             String selectedText = selectedOption.getText().trim();
             selectedOption.click();
             System.out.println("Randomly Selected Product: " + selectedText);
         } 
         else 
         {
             throw new RuntimeException("No options found in dropdown after typing: " + randomChar);
         }

     } 
     catch (Exception e) 
     {
         System.out.println("Exception in selectRandomProduct: " + e.getMessage());
         throw new RuntimeException(e);
     }
     
    }
    
    public void clickPrintBarcode()
    {
    	printBarcode.click();
    }

    /** Click Add Product Button */
    public void clickAddProduct() 
    {
        driver.findElement(addProductBtn).click();
    }

    /** Search Product */
    public void searchProduct(String productName) 
    {
        WebElement ele = driver.findElement(searchInput);
        ele.clear();
        ele.sendKeys(productName);
        driver.findElement(searchBtn).click();
    }

    /** Get Total Products Count in Table */
    public int getProductCount() 
    {
        return driver.findElements(tableRows).size();
    }

    /** Get product names from table */
    public List<WebElement> getProductNameColumn() 
    {
        return driver.findElements(productNameCol);
    }

    /** Edit product by row index */
    public void clickEditByRow(int row) 
    {
        driver.findElements(editButtons).get(row).click();
    }

    /** Delete product by row index */
    public void clickDeleteByRow(int row) 
    {
        driver.findElements(deleteButtons).get(row).click();
    }

    /** Click Next */
    public void clickNextPage() 
    {
        driver.findElement(nextPageBtn).click();
    }

    /** Click Previous */
    public void clickPrevPage() 
    {
        driver.findElement(prevPageBtn).click();
    }

    /** Apply Filter (Filter Component Utility) */
    public void applyFilter(String columnName, String filterType, String value) 
    {
        //FilterComponent filter = new FilterComponent(driver);
        ////filter.applyFilter(columnName, filterType, value);
    }

    /** Check if product exists in table */
    public boolean isProductPresent(String productName) 
    {
        List<WebElement> products = getProductNameColumn();
        for (WebElement p : products) 
        {
            if (p.getText().equalsIgnoreCase(productName)) 
            {
                return true;
            }
        }
        return false;
    }

}
