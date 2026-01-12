package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.CalendarUtil;
import com.webtrix24.rental.utils.DropdownUtil;
import com.webtrix24.rental.utils.RandomGenerationUtils;
import com.webtrix24.rental.utils.SearchandselectproductDropdownUtility;

public class ProductsCreatePage extends BasePage {
	SearchandselectproductDropdownUtility searselectProduct;
	DropdownUtil dropdownUtil;
	CommanFunctionalitiesPage cmf;
	RandomGenerationUtils randomGenerationUtils;
	CalendarUtil calendarUtil;

	/*****************************
	 * Constructor
	 **************************************************/

	public ProductsCreatePage(WebDriver driver) {

		super(driver);

		dropdownUtil = new DropdownUtil(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		randomGenerationUtils = new RandomGenerationUtils(driver);
		searselectProduct = new SearchandselectproductDropdownUtility(driver);
		calendarUtil = new CalendarUtil(driver);

	}

	/*****************************
	 * Locators
	 **************************************************/

	@FindBy(xpath = "//h2[contains(text(),'Create Product')]")
	WebElement productFormTitle;

	@FindBy(xpath = "//input[@id='product_name']")
	WebElement productName;

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
	WebElement operatingSystem;

	@FindBy(xpath = "//input[@id='screensize']")
	WebElement screensize;

	@FindBy(xpath = "//input[@id='processor']")
	WebElement processor;

	@FindBy(xpath = "//label[text()='Purchase Date']/following-sibling::div//input")
	WebElement PurchaseDate;

	@FindBy(xpath = "//label[text()='Warranty Up To']/following-sibling::div//input")
	WebElement WarrantyUpTo;

	@FindBy(xpath = "//label[text()='Purchase Price']/following-sibling::input")
	WebElement PurchasePrice;

	@FindBy(xpath = "//input[@inputmode='decimal']")
	WebElement rentalRateMonthly;

	@FindBy(xpath = "//input[@value='yes' and @name='with_tax']")
	WebElement withGstYes;

	@FindBy(xpath = "//label[text()='GST(%)']/following-sibling::input[@inputmode='decimal']")
	WebElement enterGstAmount;

	@FindBy(xpath = "//input[@value='no' and @name='with_tax']")
	WebElement GstNo;

	@FindBy(xpath = "//label[text()='Condition']/following-sibling::select")
	WebElement conditiondropdown; //// option[@value='new']

	@FindBy(xpath = "//label[text()='Barcode']/following-sibling::input")
	WebElement barcode;

	@FindBy(xpath = "//div[@role='textbox']")
	WebElement description;

	/*****************************
	 * Action Methods
	 **************************************************/

	public boolean isCreateProductFormDisplayed() {
		try {
			return productFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	// Method: Click on Product Name field to open dropdown
	public void clickProductNameField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(productName));
		productName.click();
	}

	// Method: Get selected product text (verify selected value)
	public String getSelectedProductValue() {
		return productName.getAttribute("value").trim();
	}

	// Method: select product name in dropdown
	public void selectProduct(String productName) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.productName, productName);// productNameInput
	}

	public void clickProductTypeField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(productType));
		productType.click();
	}

	public void selectProductType(String productType) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.productType, productType);// productTYpeInput
	}

	public void clickProductUnitField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(unit));
		unit.click();
	}

	public void selectProductUnit(String productUnit) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.unit, productUnit);// productTYpeInput
	}

	public void SetProductSerialNumber(String serilaNum) {
		productSerialNumber.click();
		productSerialNumber.sendKeys(serilaNum);
	}

	public void clickModelNameField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(modelName));
		modelName.click();
	}

	public void selectModelName(String modelNm) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.modelName, modelNm);
	}

	public void clickModelNumField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(modelNumber));
		modelNumber.click();
	}

	public void selectModelNum(String modelNum) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.modelNumber, modelNum);
	}

	public void clickGenerationField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(generation));
		generation.click();
	}

	public void selectGeneration(String generation) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.generation, generation);
	}

	public void clickHddCpacityField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(hddCapacity));
		hddCapacity.click();
	}

	public void selectHDDCapacity(String hddCapacity) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.hddCapacity, hddCapacity);
	}

	public void clickMemoryField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(memory));
		memory.click();
	}

	public void selectMemory(String memory) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.memory, memory);
	}

	public void clickOperatingSystemField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(operatingSystem));
		operatingSystem.click();
	}

	public void selectOperatingSystem(String op) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.operatingSystem, op);
	}

	public void clickScreenSizeField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(screensize));
		screensize.click();
	}

	public void selectScreenSize(String screen) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.screensize, screen);
	}

	public void clickProcessorField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(processor));
		processor.click();
	}

	public void selectProcessor(String processor) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.processor, processor);
	}

	public void setProductSerialNumber(String baseSerial) {
		// Generate unique serial number (base + random)
		String uniqueSerial = randomGenerationUtils.generateUniqueSerial(baseSerial, 5);
		productSerialNumber.clear();
		productSerialNumber.sendKeys(uniqueSerial);
		System.out.println("Generated Product Serial Number: " + uniqueSerial);
	}

	// Getter (optional – for verification)
	public String getEnteredProductSerialNumber() {
		return productSerialNumber.getAttribute("value");
	}

	public void selectPurchaseDate(String date) {
		PurchaseDate.click();
		calendarUtil.selectDateByInput(date);
	}

	public void setPurchasePrice(String price) {
		PurchasePrice.click();
		PurchasePrice.sendKeys(price);
	}

	public void selectWarrantyUpTo(String date) {
		WarrantyUpTo.click();
		calendarUtil.selectDateByInput(date);
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
		// GstNo.click();
		enterGstAmount.click();
		enterGstAmount.sendKeys(gst);
	}

}
