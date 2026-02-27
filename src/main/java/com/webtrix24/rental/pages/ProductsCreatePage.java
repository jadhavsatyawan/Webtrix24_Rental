package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
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

	/************** Constructor *************/

	public ProductsCreatePage(WebDriver driver) {

		super(driver);

		dropdownUtil = new DropdownUtil(driver);
		cmf = new CommanFunctionalitiesPage(driver);

		searselectProduct = new SearchandselectproductDropdownUtility(driver);
		calendarUtil = new CalendarUtil(driver);

	}

	/************* Locators *********************/

	@FindBy(xpath = "//h2[contains(text(),'Create Product')]")
	WebElement productCreateFormTitle;

	@FindBy(xpath = "//h2[contains(text(),'Duplicate Product')]")
	WebElement duplicateFormTitle;

	@FindBy(xpath = "//h2[contains(text(),'Update Product')]")
	WebElement UpdateFormTitle;

	@FindBy(xpath = "//input[@id='product_name']")
	WebElement productName;

	@FindBy(xpath = "//div[contains(text(),'Product / Service Name is required.')]")
	WebElement productNameRequiredError;

	@FindBy(xpath = "//input[@id='product_type']")
	WebElement productType;

	@FindBy(xpath = "//div[contains(text(),'Product Type is required.')]")
	WebElement productTypeRequiredError;

	@FindBy(xpath = "//input[@id='unit']")
	WebElement unit;

	@FindBy(xpath = "// label[text()='Product Serial No']/following-sibling::input")
	WebElement productSerialNumber;

	@FindBy(xpath = "//div[contains(text(),'Product Serial No is required.')]")
	WebElement serailNumberRequiredError;

	// Duplicate serail number totast messages Error: Product Serial No Already
	// Exists!

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

	@FindBy(xpath = "//label[text()='Purchase Price']/following-sibling::input")
	WebElement PurchasePrice;

	@FindBy(xpath = "//label[text()='Warranty Up To']/following-sibling::div//input")
	WebElement WarrantyUpTo;

	@FindBy(xpath = "//input[@id='purchase_from']")
	WebElement PurchaseRentedFrom;

	@FindBy(xpath = "//input[@inputmode='decimal']")
	WebElement rentalRateMonthly;

	@FindBy(xpath = "//input[@value='yes' and @name='with_tax']")
	WebElement withGstYes;

	@FindBy(xpath = "//label[text()='GST(%)']/following-sibling::input[@inputmode='decimal']")
	WebElement enterGstAmount;

	@FindBy(xpath = "//input[@value='no' and @name='with_tax']")
	WebElement GstNo;

	@FindBy(xpath = "//input[@id='graphics_card']")
	WebElement graphicsCard;

	@FindBy(xpath = "//label[text()='Condition']/following-sibling::select")
	WebElement conditiondropdown; //// option[@value='new']

	@FindBy(xpath = "//label[text()='Barcode']/following-sibling::input")
	WebElement barcode;

	// Duplicate Barcode Toast Message
	// Error: System Error : Duplicate barcode for this company. Please contact
	// support team.

	@FindBy(xpath = "//label[text()='IMEI No.']/following-sibling::input")
	WebElement IMEINo;

	@FindBy(xpath = "//div[@role='textbox']")
	WebElement description;

	/*************** Action Methods *******************/

	public boolean isCreateProductFormDisplayed() {
		try {
			return productCreateFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isUpdateProductFormDisplayed() {
		try {
			return UpdateFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDuplicateProductFormDisplayed() {
		try {
			return duplicateFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	// Method: Click on Product Name field to open dropdown
	public void clickProductNameField() {
		wait.until(ExpectedConditions.elementToBeClickable(productName));
		productName.click();
	}

	// Method: select product name in dropdown
	public void selectProduct(String productName) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.productName, productName);// productNameInput
	}

	// Method: Get selected product text (verify selected value)
	public String getSelectedProductValue() {
		return productName.getAttribute("value").trim();
	}

	public String getProductNameValidationMessage() {

		return productNameRequiredError.getText().trim();
	}

	public void clickProductTypeField() {
		wait.until(ExpectedConditions.elementToBeClickable(productType));
		productType.click();
	}

	public void selectProductType(String productType) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.productType, productType);// productTYpeInput
	}

	public String getProducTypeValidationMessage() {

		return productTypeRequiredError.getText().trim();
	}

	public void clickProductUnitField() {
		wait.until(ExpectedConditions.elementToBeClickable(unit));
		unit.click();

	}

	public void selectProductUnit(String productUnit) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.unit, productUnit);// productTYpeInput
	}

	public String getSelectedunit() {
		return unit.getAttribute("value").trim();
	}

	public void clearDropdownByKeyboard(WebElement dropdownInput) {

		wait.until(ExpectedConditions.elementToBeClickable(dropdownInput));

		dropdownInput.click();
		dropdownInput.sendKeys(Keys.CONTROL + "a");
		dropdownInput.sendKeys(Keys.DELETE);

		// React state settle होण्यासाठी
		wait.until(ExpectedConditions.attributeToBe(dropdownInput, "value", ""));
	}

	public void SetProductSerialNumberManual(String serilaNum) {
		productSerialNumber.click();
		productSerialNumber.sendKeys(serilaNum);
	}

	public void setProductSerialNumber(String baseSerial) {
		// Generate unique serial number (base + random)
		String uniqueSerial = RandomGenerationUtils.generateUniqueSerial(baseSerial, 5);
		productSerialNumber.clear();
		productSerialNumber.sendKeys(uniqueSerial);
		System.out.println("Generated Product Serial Number: " + uniqueSerial);
	}

	// Getter (optional – for verification)
	public String getEnteredProductSerialNumber() {
		waitForDuplicateFormFields();
		return productSerialNumber.getAttribute("value").trim();
	}

	public void waitForDuplicateFormFields() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// wait till serial number input is PRESENT & VISIBLE
		wait.until(ExpectedConditions.visibilityOf(productSerialNumber));
	}

	public String getProducSerailNumberValidationMessage() {

		return serailNumberRequiredError.getText().trim();
	}

	public void clickModelNameField() {
		wait.until(ExpectedConditions.elementToBeClickable(modelName));
		modelName.click();
	}

	public void selectModelName(String modelNm) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.modelName, modelNm);
	}

	public String getEnteredProductModelNameString() {
		waitForDuplicateFormFields();
		return modelName.getAttribute("value").trim();
	}

	public void clickModelNumField() {
		wait.until(ExpectedConditions.elementToBeClickable(modelNumber));
		modelNumber.click();
	}

	public void selectModelNum(String modelNum) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.modelNumber, modelNum);
	}

	public void clickGenerationField() {
		wait.until(ExpectedConditions.elementToBeClickable(generation));
		generation.click();
	}

	public void selectGeneration(String generation) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.generation, generation);
	}

	public void clickHddCpacityField() {
		wait.until(ExpectedConditions.elementToBeClickable(hddCapacity));
		hddCapacity.click();
	}

	public void selectHDDCapacity(String hddCapacity) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.hddCapacity, hddCapacity);
	}

	public void clickMemoryField() {
		wait.until(ExpectedConditions.elementToBeClickable(memory));
		memory.click();
	}

	public void selectMemory(String memory) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.memory, memory);
	}

	public void clickOperatingSystemField() {
		wait.until(ExpectedConditions.elementToBeClickable(operatingSystem));
		operatingSystem.click();
	}

	public void selectOperatingSystem(String op) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.operatingSystem, op);
	}

	public void clickScreenSizeField() {
		wait.until(ExpectedConditions.elementToBeClickable(screensize));
		screensize.click();
	}

	public void selectScreenSize(String screen) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.screensize, screen);
	}

	public void clickProcessorField() {
		wait.until(ExpectedConditions.elementToBeClickable(processor));
		processor.click();
	}

	public void selectProcessor(String processor) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.processor, processor);
	}

	public void selectPurchaseDate(String date) {
		PurchaseDate.click();
		calendarUtil.selectDateByInput(date);
	}

	public void setPurchasePrice(String price) {
		PurchasePrice.click();
		PurchasePrice.clear();
		PurchasePrice.sendKeys(price);
	}

	public void selectWarrantyUpTo(String date) {
		WarrantyUpTo.click();
		calendarUtil.selectDateByInput(date);
	}

	public void clickPurchaseRentedFrom() {
		wait.until(ExpectedConditions.elementToBeClickable(PurchaseRentedFrom));
		PurchaseRentedFrom.click();
	}

	public void selectVendor(String vendor) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.PurchaseRentedFrom, vendor);
	}

	public void setRentalPrice(String rentalprice) {
		rentalRateMonthly.click();
		rentalRateMonthly.sendKeys(rentalprice);
	}

	public void clickGraphicsCard() {
		wait.until(ExpectedConditions.elementToBeClickable(graphicsCard));
		graphicsCard.click();
	}

	public void selectGraphicsCard(String Graphics) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.graphicsCard, Graphics);
	}

	// Reusable method to select dropdown value
	public void selectCondition(String conditionValue) {
		Select select = new Select(conditiondropdown);
		select.selectByVisibleText(conditionValue); // "New", "Used", etc.
	}

	public void setBarcode(String bCode) {
		barcode.click();
		barcode.sendKeys(bCode);
	}

	// Get Barcode value from input field
	public String getBarcodeValue() {
		waitForDuplicateFormFields(); // same wait works
		return barcode.getAttribute("value").trim();
	}

	public void setIMEINo(String imei) {
		IMEINo.click();
		IMEINo.sendKeys(imei);
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

	/***************** Fill Product Form All Field and Save ****************/

	public void fillProductFormAndSave(String productName, String productType, String unit, String modelName,
			String modelNumber, String generation, String hdd, String memory, String os, String screen,
			String processor, String purchaseDate, String purchasePrice, String warrantyDate, String vendor,
			String rentalPrice, String graphics, String condition, String barcode, String imei, String description)
			throws InterruptedException {

		clickProductNameField();
		selectProduct(productName);

		clickProductTypeField();
		selectProductType(productType);

		clickProductUnitField();
		selectProductUnit(unit);

		setProductSerialNumber("LP-");

		clickModelNameField();
		selectModelName(modelName);

		clickModelNumField();
		selectModelNum(modelNumber);

		clickGenerationField();
		selectGeneration(generation);

		clickHddCpacityField();
		selectHDDCapacity(hdd);

		clickMemoryField();
		selectMemory(memory);

		clickOperatingSystemField();
		selectOperatingSystem(os);

		clickScreenSizeField();
		selectScreenSize(screen);

		clickProcessorField();
		selectProcessor(processor);

		selectPurchaseDate(purchaseDate);
		setPurchasePrice(purchasePrice);

		selectWarrantyUpTo(warrantyDate);

		clickPurchaseRentedFrom();
		selectVendor(vendor);

		setRentalPrice(rentalPrice);

		clickGraphicsCard();
		selectGraphicsCard(graphics);

		selectCondition(condition);

		setBarcode(barcode);
		setIMEINo(imei);

		setDesription(description);
	}

	/************** Fill Product Form Without Barcode and Save ********************/

	public void fillProductFormWthoutBarcodeAndSave(String productName, String productType, String unit,
			String modelName, String modelNumber, String generation, String hdd, String memory, String os,
			String screen, String processor, String purchaseDate, String purchasePrice, String warrantyDate,
			String vendor, String rentalPrice, String graphics, String condition, String imei, String description)
			throws InterruptedException {

		clickProductNameField();
		selectProduct(productName);

		clickProductTypeField();
		selectProductType(productType);

		clickProductUnitField();
		selectProductUnit(unit);

		setProductSerialNumber("LP-");

		clickModelNameField();
		selectModelName(modelName);

		clickModelNumField();
		selectModelNum(modelNumber);

		clickGenerationField();
		selectGeneration(generation);

		clickHddCpacityField();
		selectHDDCapacity(hdd);

		clickMemoryField();
		selectMemory(memory);

		clickOperatingSystemField();
		selectOperatingSystem(os);

		clickScreenSizeField();
		selectScreenSize(screen);

		clickProcessorField();
		selectProcessor(processor);

		selectPurchaseDate(purchaseDate);
		setPurchasePrice(purchasePrice);

		selectWarrantyUpTo(warrantyDate);

		clickPurchaseRentedFrom();
		selectVendor(vendor);

		setRentalPrice(rentalPrice);

		clickGraphicsCard();
		selectGraphicsCard(graphics);

		selectCondition(condition);
		setIMEINo(imei);

		setDesription(description);
	}

	/*
	 * public void fillProductFormAndSave(String productName, String productType,
	 * String unit, String modelName, String modelNumber, String generation, String
	 * hdd, String memory, String os, String screen, String processor, String
	 * purchaseDate, String purchasePrice, String warrantyDate, String rentalPrice,
	 * String graphics, String condition, String imei) throws InterruptedException {
	 * 
	 * clickProductNameField(); selectProduct(productName);
	 * 
	 * clickProductTypeField(); selectProductType(productType);
	 * 
	 * clickProductUnitField(); selectProductUnit(unit);
	 * 
	 * setProductSerialNumber("LP-");
	 * 
	 * clickModelNameField(); selectModelName(modelName);
	 * 
	 * clickModelNumField(); selectModelNum(modelNumber);
	 * 
	 * clickGenerationField(); selectGeneration(generation);
	 * 
	 * clickHddCpacityField(); selectHDDCapacity(hdd);
	 * 
	 * clickMemoryField(); selectMemory(memory);
	 * 
	 * clickOperatingSystemField(); selectOperatingSystem(os);
	 * 
	 * clickScreenSizeField(); selectScreenSize(screen);
	 * 
	 * clickProcessorField(); selectProcessor(processor);
	 * 
	 * selectPurchaseDate(purchaseDate); setPurchasePrice(purchasePrice);
	 * 
	 * selectWarrantyUpTo(warrantyDate);
	 * 
	 * setRentalPrice(rentalPrice);
	 * 
	 * clickGraphicsCard(); selectGraphicsCard(graphics);
	 * 
	 * selectCondition(condition); setIMEINo(imei);
	 * 
	 * }
	 */
	private void selectDropdownByExactText(WebElement dropdownElement, String value) {

		if (value == null || value.trim().isEmpty()) {
			return;
		}

		Select select = new Select(dropdownElement);
		boolean found = false;

		for (WebElement option : select.getOptions()) {
			if (option.getText().trim().equalsIgnoreCase(value.trim())) {
				option.click();
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Value not found in dropdown: " + value);
		}
	}

	public void fillProductFormAndSave(String productName, String productType, String unit, String serialnum,
			String modelName, String modelNumber, String generation, String hdd, String memory, String os,
			String screen, String processor, String purchaseDate, String purchasePrice, String warrantyDate,
			String rentalPrice, String graphics, String condition, String imei) throws InterruptedException {

		// ===== Mandatory Fields =====
		if (productName != null && !productName.trim().isEmpty()) {
			clickProductNameField();
			dropdownUtil.selectFromSearchableDropdownExact(this.productName, productName);
		}

		if (productType != null && !productType.trim().isEmpty()) {
			clickProductTypeField();
			dropdownUtil.selectFromSearchableDropdownExact(this.productType, productType);
		}

		if (unit != null && !unit.trim().isEmpty()) {
			clickProductUnitField();
			dropdownUtil.selectFromSearchableDropdownExact(this.unit, unit);

		}

		// Serial always generate (mandatory handled before calling this method)
		SetProductSerialNumberManual(serialnum);

		// ===== Optional Fields (Safe Handling) =====

		if (modelName != null && !modelName.trim().isEmpty()) {
			clickModelNameField();
			dropdownUtil.selectFromSearchableDropdownExact(this.modelName, modelName);
		}

		if (modelNumber != null && !modelNumber.trim().isEmpty()) {
			clickModelNumField();
			dropdownUtil.selectFromSearchableDropdownExact(this.modelNumber, modelNumber);
		}

		if (generation != null && !generation.trim().isEmpty()) {
			clickGenerationField();
			dropdownUtil.selectFromSearchableDropdownExact(this.generation, generation);
		}

		if (hdd != null && !hdd.trim().isEmpty()) {
			clickHddCpacityField();
			dropdownUtil.selectFromSearchableDropdownExact(this.hddCapacity, hdd);
		}

		if (memory != null && !memory.trim().isEmpty()) {
			clickMemoryField();
			dropdownUtil.selectFromSearchableDropdownExact(this.memory, memory);
		}

		if (os != null && !os.trim().isEmpty()) {
			clickOperatingSystemField();
			dropdownUtil.selectFromSearchableDropdownExact(this.operatingSystem, os);
		}

		if (screen != null && !screen.trim().isEmpty()) {
			clickScreenSizeField();
			dropdownUtil.selectFromSearchableDropdownExact(this.screensize, screen);
		}

		/*
		 * if (processor != null && !processor.trim().isEmpty()) {
		 * clickProcessorField(); selectProcessor(processor); }
		 */

		if (processor != null && !processor.trim().isEmpty()) {
			clickProcessorField();
			dropdownUtil.selectFromSearchableDropdownExact(this.processor, processor);
		}

		if (purchaseDate != null && !purchaseDate.trim().isEmpty()) {
			PurchaseDate.click();
			calendarUtil.selectDateByInput(purchaseDate);
		}

		if (purchasePrice != null && !purchasePrice.trim().isEmpty()) {
			setPurchasePrice(purchasePrice);
		}

		if (warrantyDate != null && !warrantyDate.trim().isEmpty()) {
			WarrantyUpTo.click();
			calendarUtil.selectDateByInput(warrantyDate);

		}

		if (rentalPrice != null && !rentalPrice.trim().isEmpty()) {
			setRentalPrice(rentalPrice);

		}

		if (graphics != null && !graphics.trim().isEmpty()) {
			clickGraphicsCard();
			dropdownUtil.selectFromSearchableDropdownExact(this.graphicsCard, graphics);
		}

		// Condition - Skip if blank or "New" (default selected)
		if (condition != null && !condition.trim().isEmpty() && !condition.trim().equalsIgnoreCase("New")) {
			dropdownUtil.selectFromSearchableDropdownExact(this.conditiondropdown, condition);
		}

		if (imei != null && !imei.trim().isEmpty()) {

			setIMEINo(imei);
		}
	}
}
