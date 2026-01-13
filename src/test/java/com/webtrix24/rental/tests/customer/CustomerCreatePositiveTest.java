package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
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
	// @Test(priority = 1, description = "Verify valid input in Customer Name
	// field")
	public void verifyValidCustomerName() throws InterruptedException {
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		// Step 2: Enter valid customer name
		customersPage.enterCustomerName("Soham");

		// Step 3: Click Save and get toast
		String toastMsg = cmf.clickSaveAndGetToast();
		Thread.sleep(2000);

		Assert.assertNotNull(toastMsg, "Toast message was not captured!");
		Assert.assertTrue(toastMsg.toLowerCase().contains("success"), "Unexpected toast: " + toastMsg);
	}

	// @Test(priority = 2, description = "Verify Assignee field is auto-filled with
	// logged-in user in Create Customer form")
	public void verifyAssigneeAutoFilledWithLoggedInUser() throws Exception {
		// Step 1: Get logged-in user name from header/profile
		hr.clickProfileDropdwon();

		String expectedUserName = hr.getLoggedInUserNameFromPopup();
		System.out.println("Username read from profile popup: " + expectedUserName);

		// Step 2: Open Create Customer form
		openCreateCustomerForm();

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

	@Test(priority = 1, description = "Verify valid input fill in Customer Form field")
	public void verifyValidCustomerFormInput() throws InterruptedException {
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

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

	// @Test(priority = 1, description = "Verify Save & New clears all fields except
	// default Assignee")
	public void verifySaveAndNewClearsFormExceptAssignee() throws InterruptedException {
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

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

}