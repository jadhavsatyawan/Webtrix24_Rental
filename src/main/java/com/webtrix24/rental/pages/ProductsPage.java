package com.webtrix24.rental.pages;

import java.security.PublicKey;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.DropdownUtil;
import com.webtrix24.rental.utils.RandomGenerationUtils;
import com.webtrix24.rental.utils.SearchandselectproductDropdownUtility;


public class ProductsPage extends BasePage
{
	SearchandselectproductDropdownUtility searselectProduct;
	DropdownUtil  dropdownUtil;
	CommanFunctionalitiesPage cmf;
	RandomGenerationUtils randomGenerationUtils;
	
	// CalendarUtil calendarUtil; 
	//Constructor

	public ProductsPage(WebDriver driver) 
	{
		
		super(driver);
		// TODO Auto-generated constructor stub
		 
		dropdownUtil = new DropdownUtil(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		randomGenerationUtils = new RandomGenerationUtils(driver);
		
		//calendarUtil = new CalendarUtil(driver);
		
		searselectProduct= new SearchandselectproductDropdownUtility(driver);
	}
	
	//Locators
	
	
	@FindBy (xpath = "//h2[contains(text(),'Create Product')]")
	WebElement productFormTitle;
//	
@FindBy(xpath = "//input[@id='product_name']")
	WebElement productName;
	
	
//Locator for product dropdown options (common class)
//@FindBy(xpath = "//div[contains(@class,'cursor-pointer')]//span")
//java.util.List<WebElement> productOptions;


//Locator for dropdown options
//By dropdownOptions = By.xpath("//div[contains(@class,'cursor-pointer')]//span");
//By dropdownOptions = By.xpath("//div[@class='max-h-60 border bg-white shadow-lg']//div[contains(@class,'cursor-pointer')]//span");
By dropdownOptions = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");


	
	@FindBy(xpath = "//input[@id='product_type']")
	WebElement productType;
	
	@FindBy(xpath = "//input[@id='unit']")
	WebElement unit;
	
	@FindBy(xpath = "//label[text()='Product Serial No']/following-sibling::input")
	WebElement productSerialNumber;
	
	@FindBy(xpath = "//input[@id='model_name']")
	WebElement modelName;
	
	@FindBy(xpath = "//input[@id='model_number']")
	WebElement modelNumber;
	
	@FindBy(xpath = "//input[@id='generation']")
	WebElement generation;
	
	@FindBy(xpath = "//input[@id='hdd_capacity']")
	WebElement hddCapacity;
	
	@FindBy(xpath = "//input[@id='memory']")
	WebElement memory;
	
	@FindBy(xpath = "//input[@id='operating_system']")
	WebElement  operatingSystem; 
	
	@FindBy(xpath = "//input[@id='screensize']")
	WebElement   screensize;  
	
	@FindBy(xpath = "//input[@id='processor']")
	WebElement    processor;     
	
	@FindBy(xpath = "//label[text()='Purchase Date']/following-sibling::div//input")
	WebElement    PurchaseDate;
	
	@FindBy(xpath = "//label[text()='Warranty Up To']/following-sibling::div//input")
	WebElement    WarrantyUpTo;
	
	@FindBy(xpath = "//label[text()='Purchase Price']/following-sibling::input")
	WebElement    PurchasePrice;
	
	@FindBy(xpath = "//input[@inputmode='decimal']")
	WebElement    rentalRateMonthly;
	
	@FindBy(xpath = "//input[@value='yes' and @name='with_tax']")
	WebElement    withGstYes;
	
	@FindBy(xpath = "//label[text()='GST(%)']/following-sibling::input[@inputmode='decimal']")
	WebElement    enterGstAmount;
	
	@FindBy(xpath = "//input[@value='no' and @name='with_tax']")
	WebElement    GstNo;
	
	@FindBy(xpath = "//label[text()='Condition']/following-sibling::select")
	WebElement    conditiondropdown;  ////option[@value='new']
	
	@FindBy(xpath = "//label[text()='Barcode']/following-sibling::input")
	WebElement    barcode;
	
	@FindBy(xpath = "//div[@role='textbox']")
	WebElement    description;
	
	
	@FindBy(xpath = "//button[@class='flex items-center gap-1 px-3 py-1 border rounded-md text-sm border-gray-200 text-indigo-600 hover:bg-indigo-50']")
	WebElement printBarcode;
	
	
	@FindBy(xpath = "//button[@class='flex items-center justify-center gap-2 px-4 py-2 w-3xs bg-gray-600 text-white rounded-sm shadow hover:bg-blue-400 transition']")
	WebElement productRecentActivity;
	
	@FindBy(xpath = "//input[@class='product-search__input']")
	WebElement productSearchInput;   //div[@class='product-search__input-container css-19bb58m']
	
	//Action Methods
	
	
	public boolean isCreateProductFormDisplayed() 
	{
        try {
            return productFormTitle.isDisplayed();   //Access directly — PageFactory initialized it
        } catch (Exception e) {
            return false;
        }
	}
	
	
	// Method: Click on Product Name field to open dropdown
    public void clickProductNameField() 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productName));
        productName.click();
    }
    
    
    
    
  /*  public void selectProductFromDropdown(String productToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Click on product name input
        wait.until(ExpectedConditions.elementToBeClickable(productName)).click();

        // Step 2: Optionally refresh list if visible
      /*  try {
            WebElement refreshBtn = driver.findElement(By.xpath("//button[contains(text(),'Refresh List')]"));
            if (refreshBtn.isDisplayed()) {
                refreshBtn.click();
                System.out.println("🔄 Refreshed product list");
                Thread.sleep(2000); // allow refresh
            }
        } catch (Exception e) {
            System.out.println("⚠ Refresh button not found — skipping");
        }*/

        // Step 3: Wait for dropdown options to load after refresh
      //  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));

        // Step 4: Select desired product
  //      List<WebElement> options = driver.findElements(dropdownOptions);
    //    boolean found = false;

      //  for (WebElement option : options) {
     //       String optionText = option.getText().trim();
           // System.out.println("🔍 Found option: " + optionText);

          //  if (optionText.equalsIgnoreCase(productToSelect)) {
              //  js.executeScript("arguments[0].scrollIntoView(true);", option);
               // option.click();
               // found = true;
               // System.out.println("✅ Selected product: " + productToSelect);
               // break;
          //  }
      //  }

     //   if (!found) {
         //   throw new RuntimeException("❌ Product not found in dropdown: " + productToSelect);
       // }
  //  } */


    
    	


    // Method: Get selected product text (verify selected value)
  public String getSelectedProductValue() {
     return productName.getAttribute("value").trim();
   }

  public void clickProductTypeField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(productType));
     productType.click();
  }
  

  public void clickProductUnitField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(unit));
      unit.click();
  }
  
  public void SetProductSerialNumber(String serilaNum)
  {
	  productSerialNumber.click();
      productSerialNumber.sendKeys(serilaNum);
  }
  
  public void clickModelNameField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(modelName));
      modelName.click();
  }
  
  public void clickModelNumField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(modelNumber));
      modelNumber.click();
  }
  
  public void clickGenerationField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(generation));
      generation.click();
  }
  
  
  public void clickHddCpacityField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(hddCapacity));
      hddCapacity.click();
  }
  
  
  public void clickMemoryField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(memory));
      memory.click();
  }
  
  public void clickOperatingSystemField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(operatingSystem));
      operatingSystem.click();
  }
  
  public void clickScreenSizeField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(screensize));
      screensize.click();
  }
  
  public void clickProcessorField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(processor));
      processor.click();
  }
  
  
 /* public void selectProductType(String productTypeSelect) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
      JavascriptExecutor js = (JavascriptExecutor) driver;

      // Step 1: Click on product name input
      wait.until(ExpectedConditions.elementToBeClickable(productType)).click();

      // Step 2: Optionally refresh list if visible
      try {
          WebElement refreshBtn = driver.findElement(By.xpath("//button[contains(text(),'Refresh List')]"));
          if (refreshBtn.isDisplayed()) {
              refreshBtn.click();
              System.out.println("🔄 Refreshed product list");
              Thread.sleep(2000); // allow refresh
          }
      } catch (Exception e) {
          System.out.println("⚠ Refresh button not found — skipping");
      }

      // Step 3: Wait for dropdown options to load after refresh
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));

      // Step 4: Select desired product
      List<WebElement> options = driver.findElements(dropdownOptions);
      boolean found = false;

      for (WebElement option : options) {
          String optionText = option.getText().trim();
          System.out.println("🔍 Found option: " + optionText);

          if (optionText.equalsIgnoreCase(productTypeSelect)) {
              js.executeScript("arguments[0].scrollIntoView(true);", option);
              option.click();
              found = true;
              System.out.println("✅ Selected product: " + productTypeSelect);
              break;
          }
      }

      if (!found) {
          throw new RuntimeException("❌ Product not found in dropdown: " + productTypeSelect);
      }
  } */

  
 /* public void clickUnitField() 
  {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.elementToBeClickable(unit));
     unit.click();
  }
  
  
  public void selectUnit(String selectUnit) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
      JavascriptExecutor js = (JavascriptExecutor) driver;

      // Step 1: Click on product name input
      wait.until(ExpectedConditions.elementToBeClickable(unit)).click();

      // Step 2: Optionally refresh list if visible
      try {
          WebElement refreshBtn = driver.findElement(By.xpath("//button[contains(text(),'Refresh List')]"));
          if (refreshBtn.isDisplayed()) {
              refreshBtn.click();
              System.out.println("🔄 Refreshed product list");
              Thread.sleep(2000); // allow refresh
          }
      } catch (Exception e) {
          System.out.println("⚠ Refresh button not found — skipping");
      }

      // Step 3: Wait for dropdown options to load after refresh
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownOptions));

      // Step 4: Select desired product
      List<WebElement> options = driver.findElements(dropdownOptions);
      boolean found = false;

      for (WebElement option : options) {
          String optionText = option.getText().trim();
          System.out.println("🔍 Found option: " + optionText);

          if (optionText.equalsIgnoreCase(selectUnit)) {
              js.executeScript("arguments[0].scrollIntoView(true);", option);
              option.click();
              found = true;
              System.out.println("✅ Selected product: " + selectUnit);
              break;
          }
      }

      if (!found) {
          throw new RuntimeException("❌ Product not found in dropdown: " + selectUnit);
      }
  }
*/
	
	
	
	
	
 // By productNameInput = By.xpath("//input[@id='product_name']");

  public void selectProduct(String productName) throws InterruptedException {
      dropdownUtil.selectFromSearchableDropdown(this.productName,productName);//productNameInput
  }
  
 // By productTYpeInput = By.xpath("//input[@id='product_type']");

 public void selectProductType(String productType) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.productType, productType);//productTYpeInput
 }
 

	
 
 public void selectProductUnit(String productUnit) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.unit, productUnit);//productTYpeInput
 }
 

 public void selectModelName(String modelNm) throws InterruptedException {   
     dropdownUtil.selectFromSearchableDropdown(this.modelName, modelNm);//productTYpeInput
 }
 
 public void selectModelNum(String modelNum) throws InterruptedException {    
     dropdownUtil.selectFromSearchableDropdown(this.modelNumber, modelNum);//productTYpeInput
 }
 
 
 public void selectGeneration(String generation) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.generation, generation);//productTYpeInput
 }
 
 public void selectHDDCapacity(String hddCapacity) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.hddCapacity, hddCapacity);//productTYpeInput
 }
 
 public void selectMemory(String memory) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.memory, memory);//productTYpeInput
 }
 
 public void selectOperatingSystem(String op) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.operatingSystem, op);//productTYpeInput
 }
 
 public void selectScreenSize(String screen) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.screensize, screen);//productTYpeInput
 }
 
 public void selectProcessor(String processor) throws InterruptedException {
     dropdownUtil.selectFromSearchableDropdown(this.processor, processor);//productTYpeInput
 }
 
 
 // Action Method
/* public void setProductSerialNumber() {
     // Generate random serial number
     String serialNum = randomGenerationUtils.randomAlphaNumeric(8);
     productSerialNumber.sendKeys(serialNum);
     System.out.println("Generated Product Serial Number: " + serialNum);
 }*/
 
 

 public void setProductSerialNumber(String baseSerial) {
     // Generate unique serial number (base + random)
     String uniqueSerial = randomGenerationUtils.generateUniqueSerial(baseSerial, 5);
     productSerialNumber.clear();
     productSerialNumber.sendKeys(uniqueSerial);
     System.out.println("Generated Product Serial Number: " + uniqueSerial);
 }
 
//Getter (optional – for verification)
 public String getEnteredProductSerialNumber() {
     return productSerialNumber.getAttribute("value");
 }
 
 
 public void selectPurchaseDate(String date) {
	 PurchaseDate.click();
     //calendarUtil.selectDateByInput(date);
 }
 
 public void setPurchasePrice(String price) {
	 PurchasePrice.click();
	 PurchasePrice.sendKeys(price);
 }
 
 public void selectWarrantyUpTo(String date) {
	 WarrantyUpTo.click();
     //calendarUtil.selectDateByInput(date);
 }
 
 public void setRentalPrice(String rentalprice) {
	 rentalRateMonthly.click();
	 rentalRateMonthly.sendKeys(rentalprice);
 }
 
 
 // Reusable method to select dropdown value
 public void selectCondition(String conditionValue) {
     Select select = new Select(conditiondropdown);
     select.selectByVisibleText(conditionValue); // "New", "Used", etc.
 }
 
 public void setDesription(String desc) {
	 description.click();
	 description.sendKeys(desc);
 }
 
 public void selectWithGSTYes(String gst) {
	withGstYes.click();
    //GstNo.click();
    enterGstAmount.click();
    enterGstAmount.sendKeys(gst);
 }
 
 
	@FindBy(xpath = "//button//span[@class=\"text-md\" and text()='Product Recent Activity']")
	WebElement ProductRecentActivity;
  
  @FindBy(xpath = "//input[contains(@class,'product-search__input')]")
  WebElement productSearchBox;

  @FindBy(xpath = "//div[contains(@class,'product-search__menu-list')]//div[@role='option']")
  List<WebElement> ProdropdownOptions;
  
  By ProdropdownOptionsList = By.xpath("//div[contains(@class,'product-search__menu-list')]//div[@role='option']");



//Product Serial Search & Select
public void selectProductBySerial(String serialNumber) throws InterruptedException {
 //  try {
	   
	   ProductRecentActivity.click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       JavascriptExecutor js = (JavascriptExecutor) driver;

       // Step 1️⃣: Clear and Type in search box
       productSearchBox.clear();
       productSearchBox.sendKeys(serialNumber);
       System.out.println("🔍 Searching for: " + serialNumber);
       Thread.sleep(1000);
       productSearchBox.sendKeys(Keys.ARROW_DOWN);   // Move to first result
       productSearchBox.sendKeys(Keys.ENTER);        // Press Enter to select it
       
/*
       // Step 2️⃣: Wait for dropdown to appear
       wait.until(ExpectedConditions.visibilityOfAllElements(ProdropdownOptions));

       // Step 3️⃣: Loop through list and select matching serial
       boolean found = false;
       for (WebElement option : ProdropdownOptions) {
           String text = option.getText().trim();
           System.out.println("📋 Option: " + text);

           if (text.contains(serialNumber)) {
               js.executeScript("arguments[0].scrollIntoView(true);", option);
               option.click();
               System.out.println("✅ Selected Product: " + serialNumber);
               found = true;
               break;
           }
       }

       // Step 4️⃣: Validation
       if (!found) {
           throw new RuntimeException("❌ Product not found with Serial Number: " + serialNumber);
       }

   } catch (Exception e) {
       System.out.println("⚠ Exception in selectProductBySerial: " + e.getMessage());
       throw new RuntimeException(e);
   }
}

          
        */ 
	
   }




/*public void selectRandomProduct() throws InterruptedException {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1️⃣: Click the product field
        ProductRecentActivity.click();
        Thread.sleep(500);

        // Step 2️⃣: Type random letter to trigger list
        char randomChar = (char) ('a' + new Random().nextInt(26));
        productSearchBox.clear();
        productSearchBox.sendKeys(String.valueOf(randomChar));
        System.out.println("🔤 Typed random character: " + randomChar);
        Thread.sleep(1500); // wait for dropdown to appear

        // Step 3️⃣: Fetch all possible elements via JS (even if not inspectable)
        List<WebElement> dropdowns = (List<WebElement>) js.executeScript(
            "return Array.from(document.querySelectorAll('div,li,span,p'))" +
            ".filter(el => el.innerText && el.offsetParent !== null && el.innerText.length > 0)"
        );

        if (dropdowns.isEmpty()) {
            throw new RuntimeException("❌ No visible dropdown elements found.");
        }

        // Step 4️⃣: Randomly pick one visible option
        Random random = new Random();
        WebElement randomElement = dropdowns.get(random.nextInt(dropdowns.size()));

        // Step 5️⃣: Scroll + Click via JS
        js.executeScript("arguments[0].scrollIntoView(true);", randomElement);
        js.executeScript("arguments[0].click();", randomElement);

        System.out.println("🎯 Randomly selected (JS click): " + randomElement.getText());

    } catch (Exception e) {
        System.out.println("⚠ Exception in selectRandomProduct (JS mode): " + e.getMessage());
        throw new RuntimeException(e);
    }
}  */


//Product Serial Search & Random Select
public void selectRandomProduct() {
 try {
	 ProductRecentActivity.click();
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     JavascriptExecutor js = (JavascriptExecutor) driver;
     Random random = new Random();

     // Step 1️⃣: Click and type a random character to trigger the dropdown list
     productSearchBox.click();
     String randomChar = String.valueOf((char) ('A' + random.nextInt(26))); // Random A–Z letter
     productSearchBox.sendKeys(randomChar);
     System.out.println("🔤 Typed random letter: " + randomChar);

     // Step 2️⃣: Wait for dropdown options to appear
     wait.until(ExpectedConditions.visibilityOfAllElements(ProdropdownOptions));

     // Step 3️⃣: Get all available options
     List<WebElement> options = ProdropdownOptions;
     System.out.println("📋 Total options found: " + options.size());

     // Step 4️⃣: Randomly pick one option
     if (!options.isEmpty()) {
         int randomIndex = random.nextInt(options.size());
         WebElement selectedOption = options.get(randomIndex);
         js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
         String selectedText = selectedOption.getText().trim();
         selectedOption.click();
         System.out.println("✅ Randomly Selected Product: " + selectedText);
     } else {
         throw new RuntimeException("❌ No options found in dropdown after typing: " + randomChar);
     }

 } catch (Exception e) {
     System.out.println("⚠ Exception in selectRandomProduct: " + e.getMessage());
     throw new RuntimeException(e);
 }
}


// ✅ Universal method call (using DropdownUtility)
public void selectProductSearch(String searchValue) {
	ProductRecentActivity.click();
    //searselectProduct.selectFromSearchableDropdown(driver, productSearchBox, searchValue, ProdropdownOptionsList);
    SearchandselectproductDropdownUtility.selectFromSearchableDropdown(driver, productSearchBox, searchValue, ProdropdownOptionsList);

}

}

