package com.webtrix24.rental.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.DropdownUtil;
import com.webtrix24.rental.utils.RandomGenerationUtils;
import com.webtrix24.rental.utils.SearchandselectproductDropdownUtility;

public class DeliveriesPage extends BasePage {
	DropdownUtil dropdownUtil;
	// ReactCalendarUtil reactCalendarUtil;
	RandomGenerationUtils randomGenerationUtils;
	// DatePickerUtil datePickerUtil;
	SearchandselectproductDropdownUtility searselectProduct;

	/************* Constructor ***************/

	public DeliveriesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		dropdownUtil = new DropdownUtil(driver);
		// reactCalendarUtil = new ReactCalendarUtil(driver);
		randomGenerationUtils = new RandomGenerationUtils(driver);
		// datePickerUtil = new DatePickerUtil(driver);

		searselectProduct = new SearchandselectproductDropdownUtility(driver);
	}

	/***************************** Locators *******************************/

	By dateField = By.xpath("//input[@placeholder='Select a date']");
	By dispatchDateField = By
			.xpath("//label[text()='Dispatch Date']/following-sibling::div[@class='react-datepicker-wrapper']");//// input[@id='dispatchDate']

	// xpath for next day (//div[contains(@class,
	// 'react-datepicker__day--keyboard-selected') and contains(@class,
	// 'react-datepicker__day--today')]/following-sibling::div)[1]
	// xpath for todays date //div[contains(@class,
	// 'react-datepicker__day--keyboard-selected') and contains(@class,
	// 'react-datepicker__day--today')]

	@FindBy(xpath = "(//div[contains(@class, 'react-datepicker__day--keyboard-selected') and contains(@class, 'react-datepicker__day--today')]/following-sibling::div)[1]")
	WebElement Tommarow;

	@FindBy(xpath = "//div[contains(@class, 'react-datepicker__day--keyboard-selected') and contains(@class, 'react-datepicker__day--today')]")
	WebElement Today;

	@FindBy(xpath = "//h1[contains(text(),'Create Delivery Challan')]")
	WebElement deliveryFormTitle;

	@FindBy(xpath = "//input[@placeholder='Auto Generated']")
	WebElement deliveryNumber;

	@FindBy(xpath = "//input[@id='customer']")
	WebElement customerDropdown;

	@FindBy(xpath = "//input[@placeholder='Select a date']")
	WebElement deliveryDate;

	@FindBy(xpath = "//label[@class='block text-gray-500 text-sm mb-1']/following-sibling::select")
	WebElement status;

	@FindBy(xpath = "//button[@type='button'  and text()='Additional Order Details ']")
	WebElement additionalOrderDetails;

	@FindBy(xpath = "//label[text()='Dispatch Date']/following-sibling::div[@class='react-datepicker-wrapper']")
	WebElement dispatchDate;

	// By dateField = By.xpath("//input[@placeholder='Select a date']");

	By disDate = By.xpath("//label[text()='Dispatch Date']/following-sibling::div[@class='react-datepicker-wrapper']");

	@FindBy(xpath = "//input[@name='dispatchDocNo']")
	WebElement dispatchDocNo;

	@FindBy(xpath = "//input[@name='dispatch_through']")
	WebElement dispatchThrough;

	@FindBy(xpath = "//label[text()='Buyers Order No./ PO No.']/following-sibling::input")
	WebElement buyersOrderNo;

	@FindBy(xpath = "//label[text()='Order Date / PO Date']/following-sibling::div[@class='react-datepicker-wrapper']")
	WebElement orderDate;

	@FindBy(xpath = "//label[text()='Destination']/following-sibling::input")
	WebElement destination;

	@FindBy(xpath = "//label[text()='Reference Note']/following-sibling::input")
	WebElement referenceNote;

	@FindBy(xpath = "//label[text()='Supplier Reference']/following-sibling::input")
	WebElement supplierReference;

	@FindBy(xpath = "//label[text()='Other  Reference']/following-sibling::input")
	WebElement OtherReference;

	@FindBy(xpath = "//input[contains(@class,'product-search__input')]") // Full
																			// Box-xpath://div[@class='product-search__control
																			// css-1xtaatr-control']
	WebElement productSearchBox;

	By productListLocator = By.xpath("//div[contains(@class,'product-search__menu-list')]//div[@role='option']");

	@FindBy(xpath = "//input[@name='invoiceLineQty']")
	WebElement invoiceLineQty;

	@FindBy(xpath = "//input[@name='invoiceLineRate']")
	WebElement invoiceLineRate;

	@FindBy(xpath = "//input[@name='invoiceLineAmount']")
	WebElement invoiceLineAmount; // Total

	@FindBy(xpath = "//textarea[@name='invoiceLineNarr']")
	WebElement description;

	@FindBy(xpath = "//input[@id='charger_number']")
	WebElement chargerNumber;

	@FindBy(xpath = "//input[@placeholder='Select Accessories']")
	WebElement accessories;

	@FindBy(xpath = "//select[@name='billing_type']")
	WebElement billingType; // (//select[@name='billing_type']/option)[2]

	@FindBy(xpath = "//button[@type='button' and text()='+ Add More Product']")
	WebElement addMoreProduct;

	@FindBy(xpath = "//div[@contenteditable='true' and @role='textbox']")
	WebElement termsAndConditions;

	@FindBy(xpath = "//button[@type='button' and text()='Save Draft']")
	WebElement saveDraft;

	@FindBy(xpath = "//button[@type='button' and text()='Create Delivery Challan']")
	WebElement createDeliveryChallan;

	@FindBy(xpath = "//button[@type='button' and text()='OK']")
	WebElement okButton;

	/*****************************
	 * Deliveries Validation & Error Messages
	 **********************************************************************/

	@FindBy(xpath = "//div[@id='swal2-html-container' and text()='Customer Not Selected']")
	WebElement customerNotSelected;

	@FindBy(xpath = "//div[@role='alert' and text()='Error while creating invoice. (Internal server error. Please contact support.)']")
	WebElement deliveryDateEmpty;

	@FindBy(xpath = "//div[@role='alert' and text()='Error while creating invoice. (You cannot save future dated Delivery Challan.)']")
	WebElement cannotSaveFutureDate;

	@FindBy(xpath = "//div[@role='alert' and text()='Error while creating invoice. (You cannot save back dated Delivery Challan.)']")
	WebElement cannotSavePastDate;

	@FindBy(xpath = "//button[@class='Toastify__close-button Toastify__close-button--light']")
	WebElement closeButtonToastMessages;

	@FindBy(xpath = "//div[@id='swal2-html-container' and text()='Once generated, the delivery cannot be edited.']")
	WebElement deliveryConfirmationMessage;

	@FindBy(xpath = "//button[@type='button' and text()='Yes, generate it!']")
	WebElement yesGenerateButton;

	@FindBy(xpath = "//button[@type='button' and text()='No']")
	WebElement noButton;

	@FindBy(xpath = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success' and text()='Delviery Challan saved as draft']")
	WebElement saveDraftMessage;

	/*************************** Action Methods ***********************************/

	public boolean isCreateDeliveryFormDisplayed() {
		try {
			return deliveryFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	public void clickCustomerNameDropdwon() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(customerDropdown));
		customerDropdown.click();
	}

	public void selectCustomer(String custName) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.customerDropdown, custName);
	}

	public String getSelectedCustomerValue() {
		return customerDropdown.getAttribute("value").trim();

	}

	public void selectBookingDate(String dateInYYYYMMDD) {
		// datePickerUtil.selectDate(disDate, dateInYYYYMMDD);
	}

	/*
	 * public void setDeliveryDate(LocalDate date) throws InterruptedException {
	 * deliveryDate.click(); reactCalendarUtil.selectDate(date); }
	 */

	// Reusable method to select dropdown value
	public void selectStatus(String statusValue) {
		Select select = new Select(status);
		select.selectByVisibleText(statusValue); // "New", "Replacement", etc.
	}

	// Random selection
	/*
	 * public void selectDeliveryDate(String type) throws InterruptedException {
	 * reactCalendarUtil.selectRandomDate(dispatchDateField, type); }
	 */

	public void selectDispatchDate(String type) throws InterruptedException {
		// reactCalendarUtil.selectRandomDate(dispatchDateField, type);
	}

	// Specific date selection
	/*
	 * public void selectDeliveryDateByValue(String date) throws
	 * InterruptedException { datePicker.selectSpecificDate(deliveryDateField,
	 * date); }
	 * 
	 * public void selectDispatchDateByValue(String date) throws
	 * InterruptedException { datePicker.selectSpecificDate(dispatchDateField,
	 * date); }
	 */

	public void clickAdditionalOrderDetails() {
		additionalOrderDetails.click();

	}

	/*
	 * public void selectDispatchDate(String date) { dispatchDate.click(); //
	 * calendarUtil.selectDateByInput(date); }
	 */

	public void setDispatchDate() throws InterruptedException // (LocalDate date)
	{
		dispatchDate.click();

		Today.click();

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".react-datepicker")));
		 * 
		 * reactCalendarUtil.selectDate(date);
		 * 
		 */
	}

	public void setDispatchDocNumber(String baseSerial) {
		dispatchDocNo.click();
		// Generate unique serial number (base + random)
		String uniqueSerial = randomGenerationUtils.generateUniqueNumeric(baseSerial, 5);

		dispatchDocNo.clear();
		dispatchDocNo.sendKeys(uniqueSerial);
		System.out.println("Generated Dispatch Doc Number: " + uniqueSerial);
	}

	// Getter (optional – for verification)
	public String getEntereddispatchDocNumber() {
		return dispatchDocNo.getAttribute("value");
	}

	public void setDispatchThrough(String dispThrough) {
		dispatchThrough.click();
		dispatchThrough.sendKeys(dispThrough);
	}

	public void setBuyersOrderNumber(String baseSerial) {
		buyersOrderNo.click();
		// Generate unique serial number (base + random)
		String uniqueSerial = randomGenerationUtils.generateUniqueNumeric(baseSerial, 5);

		buyersOrderNo.clear();
		buyersOrderNo.sendKeys(uniqueSerial);
		System.out.println("Generated Buyers Order Number: " + uniqueSerial);
	}

	// Getter (optional – for verification)
	public String getEnteredBuyersOrderNumber() {
		return buyersOrderNo.getAttribute("value");
	}

	public void selectOrderDate(String date) {
		orderDate.click();
		// calendarUtil.selectDateByInput(date);
	}

	public void setDestination(String desti) {
		destination.click();
		destination.sendKeys(desti);
	}

	public void setReferenceNote(String refnote) {
		referenceNote.click();
		referenceNote.sendKeys(refnote);
	}

	public void setSupplierReference(String supRef) {
		supplierReference.click();
		supplierReference.sendKeys(supRef);
	}

	public void setOtherReference(String otherRef) {
		OtherReference.click();
		OtherReference.sendKeys(otherRef);
	}

	@FindBy(xpath = "//div[@class='flex items-center justify-between mb-3']//span[contains(text(),'Product Details')]")
	WebElement productDetailsSection;

	// Product Serial Search & Select
	public void selectProductBySerial(String serialNumber) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Step 1️⃣: Scroll to "Product Details" section before searching
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", productDetailsSection);
		Thread.sleep(1500); // give some time for scroll animation

		// Step 2️⃣: Click on product search box
		wait.until(ExpectedConditions.elementToBeClickable(productSearchBox));
		productSearchBox.click();

		// Step 3️⃣: Type serial number and select from dropdown
		productSearchBox.clear();
		productSearchBox.sendKeys(serialNumber);
		System.out.println("🔍 Searching for: " + serialNumber);
		Thread.sleep(1000);
		productSearchBox.sendKeys(Keys.ARROW_DOWN);
		productSearchBox.sendKeys(Keys.ENTER);

		System.out.println("✅ Product selected successfully: " + serialNumber);
	}

	public void clickChargerSerialNoDropdwon() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(chargerNumber));
		chargerNumber.click();
	}

	public void selectChargerSerialNumber(String chargerNumber) throws InterruptedException {
		dropdownUtil.selectFromSearchableDropdown(this.chargerNumber, chargerNumber);
	}

	@FindBy(xpath = "//div[@style[contains(.,'position: relative')]]//span[@class='whitespace-normal break-words']")
	List<WebElement> accessoriesList;

	@FindBy(xpath = "//body") // Used to click outside
	WebElement bodyElement;

	@FindBy(xpath = "//div[@class='app-main' or @class='page-content' or @id='root']")
	WebElement outsideArea; // use safe container to click outside

	public void selectAccessories(List<String> accessoriesToSelect) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Step 1️⃣: Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(accessories));
		js.executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", accessories);
		accessories.click();
		Thread.sleep(800);

		// Step 2️⃣: Wait for options
		wait.until(ExpectedConditions.visibilityOfAllElements(accessoriesList));

		// Step 3️⃣: Select each accessory
		for (String accessory : accessoriesToSelect) {
			boolean found = false;
			for (WebElement opt : accessoriesList) {
				String text = opt.getText().trim();
				if (text.equalsIgnoreCase(accessory.trim())) {
					js.executeScript("arguments[0].scrollIntoView(true);", opt);
					opt.click();
					System.out.println("✅ Selected: " + accessory);
					Thread.sleep(400);
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("⚠️ Not found: " + accessory);
			}
		}

		// Step 4️⃣: Click outside to close dropdown
		js.executeScript("arguments[0].click();", outsideArea);
		Thread.sleep(800);
		System.out.println("🎯 Accessories dropdown closed successfully.");
	}

	// Reusable method to select dropdown value
	public void selectBillingType(String billType) {
		Select select = new Select(billingType);
		select.selectByVisibleText(billType); // "Day Wise", "Monthly", "Contract Period"
	}

	@FindBy(xpath = "//label[text()='Terms And Conditions']")
	WebElement termsConditionSection;

	public void setTermsAndConditions(String Terms) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Step 1️⃣: Scroll to terms Condition Section
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", termsConditionSection);
		Thread.sleep(1500); // give some time for scroll animation

		termsAndConditions.click();
		termsAndConditions.sendKeys(Terms);
	}

	public void clickCreateDeliveryChallan() throws InterruptedException {
		createDeliveryChallan.click();
		Thread.sleep(1000);

		yesGenerateButton.click();
		Thread.sleep(1000);

	}

	// 🔹 Methods
	public String selectProduct(String searchValue) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Step 1️⃣: Scroll to "Product Details" section before searching
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", productDetailsSection);
		Thread.sleep(1500); // give some time for scroll animation
		return SearchandselectproductDropdownUtility.selectProductDC(driver, productSearchBox, searchValue,
				productListLocator);
	}

}
