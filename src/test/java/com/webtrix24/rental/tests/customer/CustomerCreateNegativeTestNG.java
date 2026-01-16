package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;
import com.webtrix24.rental.utils.TestDataGenerator;

public class CustomerCreateNegativeTestNG extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage;

	// ================= SETUP =================

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));

		Thread.sleep(2000);
		sidePanel.clickCustomers();
		Thread.sleep(2000);
	}

	@BeforeMethod
	public void openCreateCustomerForm() {
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");
	}

	@AfterMethod
	public void closeForm() {
		try {
			cmf.formCancalButton();
		} catch (Exception e) {
			// ignore
		}
	}

	// ================= CUSTOMER NAME =================

	@Test(priority = 1, description = "Verify Customer Name mandatory validation")
	public void verifyCustomerNameMandatory() {

		cmf.ClickSaveButton();

		String actual = customersPage.getCustomerNameValidationMessage();

		Assert.assertEquals(actual, "Customer Name is required.");

		System.out.println("Invalid Customer Name validation verified");
	}

	@DataProvider(name = "invalidCustomerNames")
	public Object[][] invalidCustomerNames() {
		return new Object[][] { { "12345" }, { "@#$%^" }, { "John@123" } };
	}

	@Ignore
	@Test(priority = 2, dataProvider = "invalidCustomerNames", description = "Verify invalid Customer Name formats")
	public void verifyInvalidCustomerName(String invalidName) {

		customersPage.enterCustomerName(invalidName);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getCustomerNameErrorMessage();

		Assert.assertTrue(errorMsg.contains("Special characters are not allowed."),
				"Invalid Customer Name accepted: " + invalidName);
		System.out.println("Validation verified for invalid input: " + invalidName);
	}

	// ================= EMAIL =================

	@DataProvider(name = "invalidEmails")
	public Object[][] invalidEmails() {
		return new Object[][] { { "@gmail.com" }, { "john@" }, { "john@gmail" }, { "john@gmail..com" },
				{ "jo hn@gmail.com" } };
	}

	@Ignore
	@Test(priority = 3, dataProvider = "invalidEmails", description = "Verify invalid Email formats")
	public void verifyInvalidEmail(String invalidEmail) {

		customersPage.enterCustomerName("Test User");
		customersPage.enterEmail(invalidEmail);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getEmailValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("Invaild email address."),
				"Invalid Email accepted: " + invalidEmail);
		System.out.println("Invalid email rejected successfully: " + invalidEmail);

	}

	// ================= MOBILE NUMBER =================

	@DataProvider(name = "invalidMobileNumbers")
	public Object[][] invalidMobileNumbers() {
		return new Object[][] { { "98765" }, { "987654321098" }, { "98AB543210" }, { "98@#543210" }, { "0000000000" } };
	}

	@Ignore
	@Test(priority = 4, dataProvider = "invalidMobileNumbers", description = "Verify invalid Mobile Number formats")
	public void verifyInvalidMobile(String invalidMobile) {

		customersPage.enterCustomerName("Mobile Test");
		customersPage.enterMobileNumber(invalidMobile);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getMobileValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"), "Invalid Mobile accepted: " + invalidMobile);

		System.out.println("Invalid mobile rejected successfully: " + invalidMobile);

	}

	// ================= AADHAAR =================

	@DataProvider(name = "invalidAadhaarNumbers")
	public Object[][] invalidAadhaarNumbers() {
		return new Object[][] { { "1234567890" }, { "1234ABCD9012" }, { "000000000000" } };
	}

	@Ignore
	@Test(priority = 5, dataProvider = "invalidAadhaarNumbers", description = "Verify invalid Aadhaar Number")
	public void verifyInvalidAadhaar(String invalidAadhaar) {

		customersPage.enterCustomerName("Aadhaar Test");
		customersPage.enterAadhaarNumber(invalidAadhaar);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getAadhaarValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("Invaild Aadhar Number."),
				"Invalid Aadhaar accepted: " + invalidAadhaar);

		System.out.println("Invalid Aadhaar rejected successfully: " + invalidAadhaar);
	}

	// ================= PAN =================

	@DataProvider(name = "invalidPanNumbers")
	public Object[][] invalidPanNumbers() {
		return new Object[][] { { "ABCDE1234" }, { "abcde1234f" }, { "AB@DE1234F" } };
	}

	@Ignore
	@Test(priority = 6, dataProvider = "invalidPanNumbers", description = "Verify invalid PAN Number")
	public void verifyInvalidPan(String invalidPan) {

		customersPage.enterCustomerName("PAN Test");
		customersPage.enterPanNumber(invalidPan);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getPanValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("Invaild PAN Number."),
				"Invalid PAN accepted: " + invalidPan);
		System.out.println("Invalid PAN rejected successfully: " + invalidPan);

	}

	@Test(priority = -7, description = "Verify error is shown when valid PAN is entered in lowercase")
	public void verifyLowercasePanIsRejected() {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		// Valid PAN but lowercase
		customersPage.enterPanNumber("abcde1234f");

		cmf.ClickSaveButton();

		String errorMsg = customersPage.getPanValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"),
				"Application accepted lowercase PAN without auto-conversion");
	}

	// ================= GST Number =================

	@DataProvider(name = "invalidGstNumbers")
	public Object[][] invalidGstNumbers() {
		return new Object[][] { { "22ABCDE1234F1Z" }, { "22abcde1234f1z5" }, { "22ABCDE1234F1@5" } };
	}

	@Ignore
	@Test(priority = 7, dataProvider = "invalidGstNumbers", description = "Verify invalid GST Number")
	public void verifyInvalidGst(String invalidGst) {

		customersPage.enterCustomerName("GST Test");
		customersPage.enterGstNumber(invalidGst);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getGstValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("Invaild GST Number."),
				"Invalid GST accepted: " + invalidGst);
		System.out.println("Invalid GST rejected successfully: " + invalidGst);

	}

	@Test(priority = -7, description = "Verify error is shown when valid GST Number is entered in lowercase")
	public void verifyLowercaseGstIsRejected() {

		customersPage.enterCustomerName(TestDataGenerator.getCustomerName());

		// Valid PAN but lowercase
		customersPage.enterGstNumber("27tyghv1234r2zt");

		cmf.ClickSaveButton();

		String errorMsg = customersPage.getGstValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"),
				"Application accepted lowercase GST without auto-conversion");
	}

	// ================= WEBSITE =================

	@DataProvider(name = "invalidWebsites")
	public Object[][] invalidWebsites() {
		return new Object[][] { { "example" }, { "http://" }, { "htp://site.com" } };
	}

	@Ignore
	@Test(priority = 8, dataProvider = "invalidWebsites", description = "Verify invalid Website URL")
	public void verifyInvalidWebsite(String invalidWebsite) {

		customersPage.enterCustomerName("Website Test");
		customersPage.enterWebsiteUrl(invalidWebsite);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getWebsiteValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("valid"), "Invalid Website accepted: " + invalidWebsite);
		System.out.println("Invalid website URL rejected successfully: " + invalidWebsite);

	}

	// ================= ZIPCODE =================

	@DataProvider(name = "invalidZipcodes")
	public Object[][] invalidZipcodes() {
		return new Object[][] { { "12345" }, { "12AB56" }, { "000000" } };
	}

	@Ignore
	@Test(priority = 9, dataProvider = "invalidZipcodes", description = "Verify invalid Zipcode")
	public void verifyInvalidZipcode(String invalidZip) {

		customersPage.enterCustomerName("Zip Test");
		customersPage.enterZipcode(invalidZip);
		cmf.ClickSaveButton();

		String errorMsg = customersPage.getZipcodeValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("Invaild Zip Code."),
				"Invalid Zipcode accepted: " + invalidZip);
		System.out.println("Invalid Zipcode rejected successfully: " + invalidZip);

	}

	// ================= DUPLICATE EMAIL =================
	@Ignore
	@Test(priority = 10, description = "Verify duplicate email is not allowed")
	public void verifyDuplicateEmail() {

		customersPage.enterCustomerName("Duplicate User");
		customersPage.enterMobileNumber("9876543210");
		customersPage.enterEmail("patil276@gmail.com");

		cmf.ClickSaveButton();

		String toastMsg = cmf.clickSaveAndGetToast();

		Assert.assertEquals(toastMsg, "Error: Email Already Exists.");

		System.out.println("Duplicate email validation verified successfully");

	}
}
