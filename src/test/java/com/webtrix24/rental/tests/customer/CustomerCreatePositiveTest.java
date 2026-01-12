package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomerListPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

//@Listeners(utilities.MyListener.class)
public class CustomerCreatePositiveTest extends BaseClass {
	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage; // class-level variable

	// Login & basic navigation – ONLY ONCE
	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		hr = new Header(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);

		// Login
		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		// Navigate to Customer module
		sidePanel.clickCustomers();
		Thread.sleep(2000);
	}

	// Helper: Open Create Customer Form
	private void openCreateCustomerForm() {
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

	}

	/********************* POSITIVE TEST CASES ***********************************/

	/*
	 * @Test(priority = 1, description =
	 * "Verify valid input in Customer Name field") public void
	 * verifyValidCustomerName() throws InterruptedException { // Step 1: Open
	 * Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter valid customer name
	 * customersPage.enterCustomerName("Soham");
	 * 
	 * // Step 3: Click Save and get toast String toastMsg =
	 * cmf.clickSaveAndGetToast(); Thread.sleep(2000);
	 * 
	 * Assert.assertNotNull(toastMsg, "Toast message was not captured!");
	 * Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
	 * "Unexpected toast: " + toastMsg); }
	 * 
	 * @Test(priority = 2, description =
	 * "Verify Assignee field is auto-filled with logged-in user in Create Customer form"
	 * ) public void verifyAssigneeAutoFilledWithLoggedInUser() throws
	 * InterruptedException {
	 * 
	 * // Step 1: Get logged-in user name from header/profile
	 * hr.clickProfileDropdwon();
	 * 
	 * String expectedUserName = hr.getLoggedInUserNameFromPopup();
	 * System.out.println("Username read from profile popup: " + expectedUserName);
	 * 
	 * // Step 2: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 3: Get auto-filled Assignee value String actualAssignee =
	 * customersPage.getAssigneeValue();
	 * System.out.println("Assignee value read from Customer form: " +
	 * actualAssignee);
	 * 
	 * // Comparing values System.out.println("Expected Assignee: " +
	 * expectedUserName); System.out.println("Actual Assignee  : " +
	 * actualAssignee);
	 * 
	 * // Step 4: Validation Assert.assertNotNull(actualAssignee,
	 * "Assignee field value is null");
	 * 
	 * Assert.assertEquals(actualAssignee, expectedUserName,
	 * "Assignee field is not auto-filled with logged-in user"); }
	 * 
	 * @Test(priority = 3, description =
	 * "Verify valid input fill in Customer Form field") public void
	 * verifyValidCustomerFormInput() throws InterruptedException { // Step 1: Open
	 * Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: All fields customersPage.fillCustomerForm("Suraj Patil", "FB",
	 * "patil276@gmail.com", "9987587485", "7758964875", "Suraj", "Pune", "411052",
	 * "967858963278", "ERTYU7485H", "https://www.sunsine.com", "27YUIOP7485T3ZP",
	 * "Maharashtra"
	 * 
	 * );
	 * 
	 * // Step 3: Click Save and get toast String toastMsg =
	 * cmf.clickSaveAndGetToast(); Thread.sleep(2000);
	 * 
	 * Assert.assertNotNull(toastMsg, "Toast message was not captured!");
	 * Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
	 * "Unexpected toast: " + toastMsg); }
	 * 
	 * @Test(priority = 4, description =
	 * "Verify fill all filds Save & New clears all fields except default Assignee")
	 * public void verifySaveAndNewClearsFormExceptAssignee() throws
	 * InterruptedException { // Step 1: Open Create Customer form
	 * openCreateCustomerForm();
	 * 
	 * // Step 2: Fill valid customer data
	 * customersPage.fillCustomerForm("Raj Kulkarni", "Instagram",
	 * "Raj123@gmail.com", "9890574896", "9758632578", "Raj", "Pune", "411001",
	 * "895874859635", "ABCDE1234F", "https://www.test.com", "27ABCDE1234F1Z5",
	 * "Maharashtra");
	 * 
	 * // Step 3: Click Save & New
	 * 
	 * cmf.clickSaveNewAndGetToast();
	 * 
	 * // Step 4: Verify new Create Customer form opened
	 * Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(),
	 * "Create Customer form not opened after Save & New");
	 * 
	 * // Step 5: Verify ALL fields are empty
	 * Assert.assertTrue(customersPage.isCustomerNameEmpty(),
	 * "Customer Name not empty"); Assert.assertTrue(customersPage.isSourceEmpty(),
	 * "Customer Source not empty");
	 * Assert.assertTrue(customersPage.isCustomerEmailEmpty(),
	 * "Customer Email not empty");
	 * Assert.assertTrue(customersPage.isMobileNumberEmpty(),
	 * "Mobile Number not empty");
	 * Assert.assertTrue(customersPage.isWhatsappNumberEmpty(),
	 * "Whatsapp Number not empty");
	 * Assert.assertTrue(customersPage.isBillingNameEmpty(),
	 * "Billing Name not empty");
	 * Assert.assertTrue(customersPage.isBillingAddressEmpty(),
	 * "Billing Address not empty");
	 * Assert.assertTrue(customersPage.isZipCodeEmpty(), "ZipCode not empty");
	 * Assert.assertTrue(customersPage.isAadharNumberEmpty(),
	 * "Aadhar Number not empty");
	 * Assert.assertTrue(customersPage.isPanNumberEmpty(), "Pan Number not empty");
	 * Assert.assertTrue(customersPage.isWebsiteEmpty(), "Website not empty");
	 * Assert.assertTrue(customersPage.isGstNumberEmpty(), "GST Number not empty");
	 * Assert.assertTrue(customersPage.isGstStateEmpty(), "GST State not empty");
	 * 
	 * // Step 6: Verify ONLY Assignee is auto-filled String assignee =
	 * customersPage.getAssigneeValue();
	 * 
	 * Assert.assertNotNull(assignee, "Assignee is null");
	 * Assert.assertFalse(assignee.trim().isEmpty(), "Assignee is empty");
	 * 
	 * System.out.
	 * println("Save & New verified: All fields empty, Assignee auto-filled = " +
	 * assignee); }
	 * 
	 * @Test(priority = 5, description =
	 * "Verify Customer Email field accepts valid email") public void
	 * verifyCustomerEmailAcceptsValidEmail() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid Email Test");
	 * customersPage.enterMobileNumber("9876543210");
	 * 
	 * // Step 3: Enter valid email String validEmail = "valid.user@testmail.com";
	 * customersPage.enterEmail(validEmail);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid email. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid email: " +
	 * validEmail); }
	 * 
	 * @Test(priority = 12, description =
	 * "Verify Mobile Number accepts valid 10-digit number") public void
	 * verifyMobileNumberAcceptsValidTenDigitNumber() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid Mobile Test");
	 * 
	 * // Step 3: Enter valid 10-digit mobile number String validMobile =
	 * "9876543210"; customersPage.enterMobileNumber(validMobile);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid mobile number. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid mobile number: " +
	 * validMobile); }
	 * 
	 * @Test(priority = 13, description = "Verify PAN accepts valid PAN number")
	 * public void verifyPanAcceptsValidPanNumber() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid PAN Test");
	 * 
	 * // Step 3: Enter valid PAN number String validPan = "ABCDE1234F"; // Valid
	 * PAN format customersPage.enterPanNumber(validPan);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid PAN number. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid PAN number: " +
	 * validPan); }
	 * 
	 * @Test(priority = 14, description =
	 * "Verify Aadhaar accepts valid Aadhaar number") public void
	 * verifyAadhaarAcceptsValidAadhaarNumber() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid Aadhaar Test");
	 * 
	 * // Step 3: Enter valid Aadhaar number (12 digits, not starting with 0 or 1)
	 * String validAadhaar = "345678901234";
	 * customersPage.enterAadhaarNumber(validAadhaar);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid Aadhaar number. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid Aadhaar number: "
	 * + validAadhaar); }
	 * 
	 * @Test(priority = 15, description = "Verify GST accepts valid GST number")
	 * public void verifyGstAcceptsValidGstNumber() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid GST Test");
	 * 
	 * // Step 3: Enter valid GST number // Format: 22ABCDE1234F1Z5 String validGst
	 * = "22ABCDE1234F1Z5"; customersPage.enterGstNumber(validGst);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid GST number. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid GST number: " +
	 * validGst); }
	 * 
	 * @Test(priority = 16, description = "Verify Website accepts valid URL") public
	 * void verifyWebsiteAcceptsValidUrl() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid Website Test");
	 * 
	 * // Step 3: Enter valid website URL String validWebsite =
	 * "https://www.example.com"; customersPage.enterWebsiteUrl(validWebsite);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid Website URL. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid Website URL: " +
	 * validWebsite); }
	 * 
	 * @Test(priority = 17, description =
	 * "Verify Zipcode accepts valid 6-digit number") public void
	 * verifyZipcodeAcceptsValidSixDigitNumber() {
	 * 
	 * // Step 1: Open Create Customer form openCreateCustomerForm();
	 * 
	 * // Step 2: Enter mandatory fields
	 * customersPage.enterCustomerName("Valid Zipcode Test");
	 * 
	 * // Step 3: Enter valid 6-digit zipcode String validZipcode = "411001";
	 * customersPage.enterZipcode(validZipcode);
	 * 
	 * // Step 4: Click Save cmf.ClickSaveButton();
	 * 
	 * // Step 5: Verify success toast message String toastMsg =
	 * cmf.clickSaveAndGetToast();
	 * 
	 * boolean isSavedSuccessfully = toastMsg != null &&
	 * toastMsg.toLowerCase().contains("success");
	 * 
	 * Assert.assertTrue(isSavedSuccessfully,
	 * "Customer not saved with valid Zipcode. Actual toast: " + toastMsg);
	 * 
	 * System.out.println("Customer saved successfully with valid Zipcode: " +
	 * validZipcode); }
	 * 
	 * @Test(priority = 13, description =
	 * "Verify PAN Number auto converts to uppercase") public void
	 * verifyPanAutoUppercase() {
	 * 
	 * // Step 1: Enter PAN in lowercase customersPage.enterPanNumber("abcde1234f");
	 * 
	 * // Step 2: Read actual value from input field String actualPan =
	 * customersPage.getPanInputValue();
	 * 
	 * // Step 3: Verify auto uppercase Assert.assertEquals(actualPan, "ABCDE1234F",
	 * "PAN did not auto convert to uppercase");
	 * 
	 * System.out.println("PAN auto uppercase behavior verified"); }
	 */
	@Test(priority = 11, description = "Verify Lead Source dropdown value is selectable, saved and retained after reopening")
	public void verifyLeadSourceDropdownSelectionAndPersistence() throws InterruptedException {

		// Step 1: Open Create Customer form
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

		// Step 2: Fill mandatory fields
		String customerName = "Varad";
		customersPage.enterCustomerName(customerName);
		customersPage.enterMobileNumber("9876543210");
		customersPage.enterEmail("va@test.com");

		// Step 3: Select Lead Source from dropdown
		String expectedLeadSource = "Instagram";
		customersPage.selectLeadSource(expectedLeadSource);

		// Step 4: Verify selected value in form (before save)
		String actualLeadSource = customersPage.getSelectedLeadSource();
		Assert.assertEquals(actualLeadSource, expectedLeadSource, "Selected Lead Source value not displayed correctly");

		// Step 5: Save customer (❗ ONLY ONE SAVE METHOD)
		String toastMsg = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"), "Customer not saved successfully");

		// Step 6: Open customer from List View
		CustomerListPage custListPage = new CustomerListPage(driver);
		// custListPage.clickEditByCustomerName(customerName);
		custListPage.waitForListToLoad();
		custListPage.clickEditByCustomerEmail("va@test.com");

		// Step 7: Verify Lead Source value after reopen
		String leadSourceAfterReopen = customersPage.getSelectedLeadSource();

		Assert.assertEquals(leadSourceAfterReopen, expectedLeadSource,
				"Lead Source value not retained after reopening");
	}

}