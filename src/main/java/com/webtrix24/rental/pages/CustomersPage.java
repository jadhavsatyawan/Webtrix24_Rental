package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.AddressUtil;
import com.webtrix24.rental.utils.DropdownUtil;
import com.webtrix24.rental.utils.ToastUtil;

public class CustomersPage extends BasePage {
	ToastUtil toastUtil; // ✅ Declare
	DropdownUtil dropdownUtil;
	AddressUtil addressUtil;

	/********************** Constructor ***************************************/

	public CustomersPage(WebDriver driver) {
		super(driver);

		toastUtil = new ToastUtil(driver); // ✅ Initialize once
		dropdownUtil = new DropdownUtil(driver);

	}

	/************************* Locators **********************************/

	@FindBy(xpath = "//h2[contains(text(),'Create Customer')]")
	WebElement customerFormTitle;

	@FindBy(xpath = "//label[text()='Customer Name']/following-sibling::input")
	WebElement customerName;

	@FindBy(xpath = "//div[contains(text(),'Customer Name is required.')]")
	WebElement customerNameErrorMessage;

	@FindBy(xpath = "//div[contains(text(),'Special characters are not allowed.')]")
	WebElement customerNamevalidationMsg;// Special characters are not allowed.

	@FindBy(xpath = "//input[@id='assignee']")
	WebElement assignee;
	By dropdownOptions = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");

	@FindBy(xpath = "//input[@id='lead_source']")
	WebElement leadSource;

	@FindBy(xpath = "//label[text()='Customer Email']/following-sibling::input")
	WebElement CustomerEmail;

	@FindBy(xpath = "//div[contains(text(),'Invaild email address.')]")
	WebElement emailValidationMessage;

	@FindBy(xpath = "(//input[@name='phone'])[1]")
	WebElement mobileNumber;

	@FindBy(xpath = "//p[contains(text(),'Enter 6-15 digits')]")
	WebElement mobileValidationMessage;

	@FindBy(xpath = "(//input[@name='phone'])[2]")
	WebElement whatsAppNumber;

	@FindBy(xpath = "//label[text()='Billing Name']/following-sibling::input")
	WebElement billingName;

	@FindBy(xpath = "//label[text()='Billing Address']/following-sibling::input")
	WebElement billingAddress;

	@FindBy(xpath = "//label[text()='Zipcode']/following-sibling::input")
	WebElement zipCode;

	@FindBy(xpath = "//div[contains(text(),'Invaild Zip Code.')]") // Invaild Zip Code.
	WebElement zipCodeValidaionMsg;

	@FindBy(xpath = "//label[text()='Aadhar Number']/following-sibling::input")
	WebElement adharNumber;

	@FindBy(xpath = "//div[contains(text(),'Invaild Aadhar Number.')]")
	WebElement aadharNumValidaionMsg;

	@FindBy(xpath = "//label[text()='PAN No.']/following-sibling::input")
	WebElement panNo;

	@FindBy(xpath = "//div[contains(text(),'Invaild PAN Number.')]")
	WebElement panValidationMsg;

	@FindBy(xpath = "//label[text()='Website']/following-sibling::input")
	WebElement website;

	@FindBy(xpath = "")
	WebElement websiteValidationMsg;

	@FindBy(xpath = "//label[text()='GST No.']/following-sibling::input")
	WebElement gstNo;

	@FindBy(xpath = "//div[contains(text(),'nvaild GST Number.')]")
	WebElement gstNoValidationMsg;

	@FindBy(xpath = "//input[@id='gst_state']")
	WebElement gststateDropdown;

	@FindBy(xpath = "(//div[@class='cursor-pointer px-4 py-2 hover:bg-gray-100 flex items-start justify-between text-sm'])[1]")
	WebElement SelectState;

	@FindBy(xpath = "//div[@class='Toastify__toast-container Toastify__toast-container--top-right']/div[@role='alert' and contains(text(),'Saved successfully')]")
	WebElement succesMessage;

	/********************* Actions method *******************/

	public boolean isCreateCustomerFormDisplayed() {
		try {
			return customerFormTitle.isDisplayed(); // Access directly — PageFactory initialized it
		} catch (Exception e) {
			return false;
		}
	}

	// Method to get toast message text
	public String getSuccessToastMessage() {
		try {
			return succesMessage.getText().trim();
		} catch (Exception e) {
			return "";
		}

	}

	// Method: enter customer name for Sepcefic name
	public void enterCustomerName(String name) {
		customerName.clear();
		customerName.sendKeys(name);
	}

	public String getCustomerNameValidationMessage() {
		try {
			return customerNameErrorMessage.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getCustomerNameErrorMessage() {
		try {
			return customerNamevalidationMsg.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getAssigneeValue() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(assignee, "value"));
		return assignee.getAttribute("value").trim();

	}

	private void selectDropdownOption(WebElement dropdownInput, String value) {

		// ️Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(dropdownInput));
		dropdownInput.click();

		// Type text (to filter list)
		dropdownInput.clear();
		dropdownInput.sendKeys(value);

		// WAIT for exact option
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@role='option']//span[contains(normalize-space(),'" + value + "')]")));

		// ACTUAL SELECTION (MOST IMPORTANT)
		option.click();

		// Click outside to close dropdown (manual behaviour)
		customerName.click();

		// Ensure value is really set
		wait.until(ExpectedConditions.attributeToBeNotEmpty(dropdownInput, "value"));
	}

	public void clickLeadSourceField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(leadSource));
		leadSource.click();
	}

	public void selectSource(String sou) {
		selectDropdownOption(leadSource, sou);
	}

	public void selectLeadSource(String value) {

		// 1. Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(leadSource)).click();

		// 2. Type value
		leadSource.clear();
		leadSource.sendKeys(value);

		// 3. Wait for option SPAN
		By optionSpanBy = By.xpath("//div[@role='option']//span[normalize-space()='" + value + "']");

		WebElement optionSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(optionSpanBy));

		// 4. REAL mouse click (important)
		new org.openqa.selenium.interactions.Actions(driver).moveToElement(optionSpan)
				.pause(java.time.Duration.ofMillis(150)).click().build().perform();

		// 5. FORCE CLOSE dropdown (MOST IMPORTANT)
		leadSource.sendKeys(Keys.ESCAPE);

		// 6. Wait till dropdown disappears
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='listbox']")));

		// 7. Verify value present in input ONLY
		wait.until(ExpectedConditions.attributeContains(leadSource, "value", value));
	}

	public String getSelectedLeadSource() {
		return leadSource.getAttribute("value");
	}

	public void enterEmail(String email) {
		CustomerEmail.clear();
		CustomerEmail.sendKeys(email);
	}

	public String getEmailValidationMessage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(emailValidationMessage)).getText();
	}

	public void enterMobileNumber(String mobile) {
		mobileNumber.clear();
		mobileNumber.sendKeys(mobile);
	}

	public void enterWhatsappNumber(String whatsApp) {
		whatsAppNumber.clear();
		whatsAppNumber.sendKeys(whatsApp);
	}

	public String getMobileValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(mobileValidationMessage)).getText();
	}

	public void enterBillingName(String billName) {
		billingName.clear();
		billingName.sendKeys(billName);
	}

	public String getBillingName() {
		return billingName.getAttribute("value");
	}

	public void enterBillingAddress(String billingAdd) {
		billingAddress.clear();
		billingAddress.sendKeys(billingAdd);
	}

	public String getBillingAddress() {
		return billingAddress.getAttribute("value");
	}

	public void enterZipcode(String zipcode) {
		zipCode.clear();
		zipCode.sendKeys(zipcode);

	}

	public String getZipcodeValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(zipCodeValidaionMsg)).getText();
	}

	public void enterAadhaarNumber(String aadhaar) {
		adharNumber.clear();
		adharNumber.sendKeys(aadhaar);
	}

	public String getAadhaarValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(aadharNumValidaionMsg)).getText();
	}

	public void enterPanNumber(String pan) {
		panNo.clear();
		panNo.sendKeys(pan);
	}

	public String getPanValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(panValidationMsg)).getText();
	}

	public String getPanInputValue() {
		return panNo.getAttribute("value");
	}

	public void enterGstNumber(String gst) {
		gstNo.clear();
		gstNo.sendKeys(gst);
	}

	public String getGstValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(gstNoValidationMsg)).getText();
	}

	public void enterWebsiteUrl(String url) {
		website.clear();
		website.sendKeys(url);
	}

	public String getWebsiteValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(websiteValidationMsg)).getText();
	}

	public void selectGstState(String stateName) {
		selectDropdownOption(gststateDropdown, stateName);
	}

	/*********** Fill Customer Form With All Fields **********/
	public void fillCustomerForm(String name, String source, String email, String phone, String wm, String billNm,
			String addr, String zip, String adharnum, String panNum, String web, String gstnum, String gstStateSelect)
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Customer Name
		wait.until(ExpectedConditions.visibilityOf(customerName)).clear();
		customerName.sendKeys(name);

		// Lead Source
		// wait.until(ExpectedConditions.elementToBeClickable(leadSource)).click();

		// leadSource.sendKeys(source);
		// leadSource.sendKeys(Keys.ENTER);
		leadSource.click();
		// selectDropdownOption(source);
		selectDropdownOption(leadSource, source);

		// Email
		wait.until(ExpectedConditions.visibilityOf(CustomerEmail)).clear();
		CustomerEmail.sendKeys(email);

		// Mobile
		wait.until(ExpectedConditions.visibilityOf(mobileNumber)).clear();
		mobileNumber.sendKeys(phone);

		// WhatsApp
		whatsAppNumber.clear();
		whatsAppNumber.sendKeys(wm);

		// Billing Name
		billingName.clear();
		billingName.sendKeys(billNm);

		// Billing Address
		billingAddress.clear();
		billingAddress.sendKeys(addr);

		// Zip Code
		zipCode.clear();
		zipCode.sendKeys(zip);

		// Aadhaar
		adharNumber.clear();
		adharNumber.sendKeys(adharnum);

		// PAN
		panNo.clear();
		panNo.sendKeys(panNum);

		// Website
		website.clear();
		website.sendKeys(web);

		// GST Number
		gstNo.clear();
		gstNo.sendKeys(gstnum);

		// GST State Dropdown
		// wait.until(ExpectedConditions.elementToBeClickable(gststateDropdown)).click();
		gststateDropdown.click();

		// gststateDropdown.sendKeys(gstStateSelect+Keys.ENTER);
		// selectDropdownOption(gstStateSelect);
		selectDropdownOption(gststateDropdown, gstStateSelect);

	}

	public void selectGSTStateByName(String stateName) throws InterruptedException {
		// try {
		gststateDropdown.click(); // Open dropdown
		Thread.sleep(2000); // Wait for options to load

		// cmf.refreshDropdwonList();
//           Thread.sleep(2000); 
//           System.out.println("Asnf " );
		// 1️⃣ Get state code from AddressUtil
		// String stateCode = AddressUtil.getGSTCode(stateName); // e.g., "27"

		// 2️⃣ Prepare expected value in dropdown format
		String expectedValue = stateName.trim(); // "27-Maharashtra"
		System.out.println("🔎 Selecting GST State: " + expectedValue);
		gststateDropdown.sendKeys(expectedValue);
		SelectState.click();

		System.out.println("GST State selected successfully: " + expectedValue);
		// }
// 	     catch (Exception e) {
// 	        System.out.println("❌ Failed to select GST state: " + e.getMessage());
// 	        e.printStackTrace();
// 	        throw new RuntimeException("Failed to select GST state: " + stateName);
// 	    }
	}

	/******** Clicking Save & New Button Check All Fields are Empty *********/
	public boolean isCustomerNameEmpty() {
		return customerName.getAttribute("value").trim().isEmpty();
	}

	public boolean isSourceEmpty() {
		return leadSource.getAttribute("value").trim().isEmpty();
	}

	public boolean isCustomerEmailEmpty() {
		return CustomerEmail.getAttribute("value").trim().isEmpty();
	}

	public boolean isMobileNumberEmpty() {
		return mobileNumber.getAttribute("value").trim().isEmpty();
	}

	public boolean isWhatsappNumberEmpty() {
		return whatsAppNumber.getAttribute("value").trim().isEmpty();
	}

	public boolean isBillingNameEmpty() {
		return billingName.getAttribute("value").trim().isEmpty();
	}

	public boolean isBillingAddressEmpty() {
		return billingAddress.getAttribute("value").trim().isEmpty();
	}

	public boolean isZipCodeEmpty() {
		return zipCode.getAttribute("value").trim().isEmpty();
	}

	public boolean isAadharNumberEmpty() {
		return adharNumber.getAttribute("value").trim().isEmpty();
	}

	public boolean isPanNumberEmpty() {
		return panNo.getAttribute("value").trim().isEmpty();
	}

	public boolean isWebsiteEmpty() {
		return customerName.getAttribute("value").trim().isEmpty();
	}

	public boolean isGstNumberEmpty() {
		return gstNo.getAttribute("value").trim().isEmpty();
	}

	public boolean isGstStateEmpty() {
		return gststateDropdown.getAttribute("value").trim().isEmpty();
	}

}
