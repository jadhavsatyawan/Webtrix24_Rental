package com.webtrix24.rental.tests.customer;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;
import com.webtrix24.rental.utils.TestDataGenerator;

//@Listeners(utilities.MyListener.class)
public class CustomerCreatePositiveTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage; // class-level variable

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		hr = new Header(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		sidePanel.clickCustomers();
		Thread.sleep(2000);
	}

	@BeforeMethod
	// Helper: Open Create Customer Form
	private void openCreateCustomerForm(Method method) {
		if (!method.getName().equals("verifyAssigneeAutoFilledWithLoggedInUser")) {
			cmf.clickCreateButton();
			Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

		}
	}

	/********************* POSITIVE TEST CASES ***********************************/

	@Test(priority = 1, description = "Verify Assignee field is auto-filled with logged-in user in Create Customer form")
	public void verifyAssigneeAutoFilledWithLoggedInUser() throws Exception {
		// Step 1: Get logged-in user name from header/profile
		hr.clickProfileDropdwon();

		String expectedUserName = hr.getLoggedInUserNameFromPopup();
		System.out.println("Username read from profile popup: " + expectedUserName);

		// Step 2: Open Create Customer form
		// openCreateCustomerForm();

		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

		// Step 3: Get auto-filled Assignee value
		String actualAssignee = customersPage.getAssigneeValue();
		System.out.println("Assignee value read from Customer form: " + actualAssignee);

		// Comparing values
		System.out.println("Expected Assignee: " + expectedUserName);
		System.out.println("Actual Assignee  : " + actualAssignee);

		// Step 4: Validation
		Assert.assertNotNull(actualAssignee, "Assignee field value is null");

		Assert.assertEquals(actualAssignee, expectedUserName, "Assignee field is not auto-filled with logged-in user");
	}

	@Test(priority = 2, description = "Verify customer can be created with only valid Customer Name", enabled = false)
	public void shouldCreateCustomerWithOnlyCustomerName() throws InterruptedException {

		// Step 2: Enter valid customer name
		// customersPage.enterCustomerName("Soham");
		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		// Step 3: Click Save and get toast
		String toastMsg = cmf.clickSaveAndGetToast();
		Thread.sleep(2000);

		Assert.assertNotNull(toastMsg, "Toast message was not captured!");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"), "Unexpected toast: " + toastMsg);
	}

	@Test(priority = 3, description = "Verify customer can be created when optional Source field is selected")
	public void shouldCreateCustomerWithOnlySelectingSource() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		customersPage.selectSource("Twitter");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 4, description = "Create  customer with valid Email")
	public void createEMailCustomer() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterEmail(TestDataGenerator.getUniqueIndianEmail());

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 5, description = "Create  customer with valid Mobile number")
	public void createMobileCustomer() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterMobileNumber(TestDataGenerator.getMobileNumber());

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 6, description = "Create  customer with valid Mobile number Same Whatsapp number")
	public void createCustomerMobileNumberWhatsaaNumberSame() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		String mobileNumber = TestDataGenerator.getMobileNumber();

		customersPage.enterMobileNumber(mobileNumber);
		customersPage.enterWhatsappNumber(mobileNumber);

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 7, description = "Create  customer with valid Mobile number Different Whatsapp number")
	public void createCustomerMobileNumberWhatsaaNumberDifferent() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		customersPage.enterMobileNumber(TestDataGenerator.getMobileNumber());

		customersPage.enterWhatsappNumber(TestDataGenerator.getMobileNumber());

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 8, description = "Verify Billing Name is saved same as Customer Name after successful save", enabled = false)
	public void verifyCustomerAndBillingNameSame_AfterSave() {

		// Faker called once
		String customerName = TestDataGenerator.getCustomerName();

		// Enter Customer Name
		customersPage.enterCustomerName(customerName);

		// Enter Billing Name (same as Customer Name)
		customersPage.enterBillingName(customerName);

		// Click Save & capture toast
		String toastMsg = cmf.clickSaveAndGetToast();

		// Verify save success
		Assert.assertNotNull(toastMsg, "Toast message not displayed after save");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
				"Customer record not saved successfully. Toast: " + toastMsg);

		System.out.println("Customer saved successfully with name: " + customerName);

		// OPTIONAL (Best practice)
		// If application redirects to view / details page,
		// verify Billing Name from VIEW mode (not input field)

		// String billingNameFromView = customersPage.getBillingNameFromView();
		// Assert.assertEquals(billingNameFromView, customerName,
		// "Billing Name mismatch in saved record");
	}

	@Test(priority = 9, description = "Verify Billing Name can be saved different from Customer Name after successful save")
	public void verifyCustomerAndBillingNameDifferent_AfterSave() {

		String customerName = TestDataGenerator.getCustomerName();
		String billingName = "Sam";

		customersPage.enterCustomerName(customerName);
		customersPage.enterBillingName(billingName);

		String toastMsg = cmf.clickSaveAndGetToast();

		Assert.assertNotNull(toastMsg);
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"));

		// REAL verification (view mode / grid / details)
		/*
		 * String savedBillingName = customersPage.getBillingNameFromView();
		 * Assert.assertEquals(savedBillingName, billingName,
		 * "Billing Name not saved correctly");
		 */

	}

	@Test(priority = 10, description = "Verify customer can be created with Customer Name and Billing Address")
	public void verifyCustomerWithBillingAddress() {

		String customerName = TestDataGenerator.getCustomerName();

		customersPage.enterCustomerName(customerName);
		customersPage.enterBillingAddress("Pune");

		String toastMsg = cmf.clickSaveAndGetToast();

		Assert.assertNotNull(toastMsg, "Toast message not displayed after save");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
				"Customer not saved successfully. Toast: " + toastMsg);

	}

	@Test(priority = 11, description = "Verify customer can be created with valid Zipcode")
	public void verifyCustomerWithZipcode() {

		// Test data
		String customerName = TestDataGenerator.getCustomerName();
		String zipcode = "411001"; // valid Indian Zipcode

		// Enter mandatory + zipcode
		customersPage.enterCustomerName(customerName);
		customersPage.enterZipcode(zipcode);

		// Save
		String toastMsg = cmf.clickSaveAndGetToast();

		// Validation
		Assert.assertNotNull(toastMsg, "Toast message not displayed after save");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
				"Customer not saved successfully. Toast: " + toastMsg);

		System.out.println("Customer created successfully with Name: " + customerName + " and Zipcode: " + zipcode);
	}

	@Test(priority = 12, description = "Create  customer with valid Aadhar number")
	public void createAadharCustomer() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterAadhaarNumber(TestDataGenerator.generateAadhaar());

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 13, description = "Create  customer with valid Pan number")
	public void createPanCustomer() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterPanNumber(TestDataGenerator.generatePAN());

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 14, description = "Verify customer can be created with valid Website URL")
	public void verifyCustomerWithWebsite() {

		// Test data
		String customerName = TestDataGenerator.getCustomerName();
		String websiteUrl = "https://www.example.com";

		// Enter mandatory + website
		customersPage.enterCustomerName(customerName);
		customersPage.enterWebsiteUrl(websiteUrl);

		// Save
		String toastMsg = cmf.clickSaveAndGetToast();

		// Validation
		Assert.assertNotNull(toastMsg, "Toast message not displayed after save");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"),
				"Customer not saved successfully. Toast: " + toastMsg);

		System.out.println("Customer created successfully with Name: " + customerName + " and Website: " + websiteUrl);
	}

	@Test(priority = 15, description = "Create GST customer with valid GST number")
	public void createGstCustomer() throws InterruptedException {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterGstNumber(TestDataGenerator.generateGST());
		customersPage.selectGstState("Maharashtra");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 16, description = "Create customer with valid GST State")
	public void createCustomerValidGstState() {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		customersPage.enterGstNumber(TestDataGenerator.generateGST());

		customersPage.selectGstState("Maharashtra");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"));
	}

	@Test(priority = 17, description = "Verify customer can be created with all valid inputs in Customer form")
	public void verifyValidCustomerFormInput() throws InterruptedException {

		// Step 2: All fields
		customersPage.fillCustomerForm("Suraj Patil", "FB", "patil276@gmail.com", "9987587485", "7758964875", "Suraj",
				"Pune", "411052", "967858963278", "ERTYU7485H", "https://www.sunsine.com", "27YUIOP7485T3ZP",
				"Maharashtra"

		);

		// Step 3: Click Save and get toast
		String toastMsg = cmf.clickSaveAndGetToast();
		Thread.sleep(2000);

		Assert.assertNotNull(toastMsg, "Toast message was not captured!");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"), "Unexpected toast: " + toastMsg);
	}

	@Test(priority = 18, description = "Verify Save & New clears all fields except default Assignee")
	public void verifySaveAndNewClearsFormExceptAssignee() throws InterruptedException {

		// Step 2: Fill valid customer data
		customersPage.fillCustomerForm("Raj Kulkarni", "Instagram", "Raj123@gmail.com", "9890574896", "9758632578",
				"Raj", "Pune", "411001", "895874859635", "ABCDE1234F", "https://www.test.com", "27ABCDE1234F1Z5",
				"Maharashtra");

		// Step 3: Click Save & New

		cmf.clickSaveNewAndGetToast();

		// Step 4: Verify new Create Customer form opened
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(),
				"Create Customer form not opened after Save & New");

		// Step 5: Verify ALL fields are empty
		Assert.assertTrue(customersPage.isCustomerNameEmpty(), "Customer Name not empty");
		Assert.assertTrue(customersPage.isSourceEmpty(), "Customer Source not empty");
		Assert.assertTrue(customersPage.isCustomerEmailEmpty(), "Customer Email not empty");
		Assert.assertTrue(customersPage.isMobileNumberEmpty(), "Mobile Number not empty");
		Assert.assertTrue(customersPage.isWhatsappNumberEmpty(), "Whatsapp Number not empty");
		Assert.assertTrue(customersPage.isBillingNameEmpty(), "Billing Name not empty");
		Assert.assertTrue(customersPage.isBillingAddressEmpty(), "Billing Address not empty");
		Assert.assertTrue(customersPage.isZipCodeEmpty(), "ZipCode not empty");
		Assert.assertTrue(customersPage.isAadharNumberEmpty(), "Aadhar Number not empty");
		Assert.assertTrue(customersPage.isPanNumberEmpty(), "Pan Number not empty");
		Assert.assertTrue(customersPage.isWebsiteEmpty(), "Website not empty");
		Assert.assertTrue(customersPage.isGstNumberEmpty(), "GST Number not empty");
		Assert.assertTrue(customersPage.isGstStateEmpty(), "GST State not empty");

		// Step 6: Verify ONLY Assignee is auto-filled
		String assignee = customersPage.getAssigneeValue();

		Assert.assertNotNull(assignee, "Assignee is null");
		Assert.assertFalse(assignee.trim().isEmpty(), "Assignee is empty");

		System.out.println("Save & New verified: All fields empty, Assignee auto-filled = " + assignee);
	}

	@Test(priority = 19, description = "Verify PAN number is automatically converted to uppercase on save")
	public void verifyPanNumberAutoConvertedToUppercase() {

		String lowerCasePan = "abcde1234f";

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());
		customersPage.enterPanNumber(lowerCasePan);

		cmf.ClickSaveButton();

		// Read value back from field (before or after save)
		String actualPan = customersPage.getPanInputValue();

		Assert.assertEquals(actualPan, lowerCasePan.toUpperCase(), "PAN number was not auto-converted to uppercase");
	}

	// ===== Customer Create with Valid Random Data===========

	@Test(priority = 17, groups = {
			"regeression" }, description = "Verify customer can be created with all valid inputs in Customer form")
	public void verifyValidCustomerForm() throws InterruptedException {

		// Step 2: All fields
		customersPage.fillCustomerForm(TestDataGenerator.getCustomerName(), "FB",
				TestDataGenerator.getUniqueIndianEmail(), TestDataGenerator.getMobileNumber(),

				TestDataGenerator.getMobileNumber(), TestDataGenerator.getCustomerName(), "Pune", "411052",
				TestDataGenerator.generateAadhaar(), TestDataGenerator.generatePAN(), "https://www.sunsine.com",
				TestDataGenerator.generateGST(), "Maharashtra"

		);

		// Step 3: Click Save and get toast
		String toastMsg = cmf.clickSaveAndGetToast();
		Thread.sleep(2000);

		Assert.assertNotNull(toastMsg, "Toast message was not captured!");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"), "Unexpected toast: " + toastMsg);
	}

}