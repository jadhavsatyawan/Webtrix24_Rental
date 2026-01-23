package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;
import com.webtrix24.rental.utils.ExtentReportManager1;

public class CustomerCreateNegativeExtentTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage;

	// ================= SETUP =================

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
	public void openCreateCustomerForm() {
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");
	}

	@AfterMethod
	public void closeFormAfterEachTest() {
		try {
			cmf.formCancalButton();
		} catch (Exception e) {
			// ignore
		}
	}

	/// ================= CUSTOMER NAME is Empty =================

	@Test(priority = 1, description = "Verify mandatory field validation for Customer Name")
	public void verifyCustomerNameMandatoryValidation() {

		ExtentReportManager1.test.get().info("Verifying mandatory validation for Customer Name");

		cmf.ClickSaveButton();

		String actual = customersPage.getCustomerNameValidationMessage();

		Assert.assertEquals(actual, "Customer Name is required.", "Mandatory validation message missing");

		ExtentReportManager1.test.get().pass("Mandatory validation displayed correctly");
	}

	/// ================= CUSTOMER NAME Invalid Formats =================
	@DataProvider(name = "invalidCustomerNames")
	public Object[][] invalidCustomerNames() {
		return new Object[][] { { "12345" }, { "@#$%^" }, { "John@123" } };
	}

	@Test(priority = 2, dataProvider = "invalidCustomerNames", description = "Verify invalid Customer Name inputs")
	public void verifyInvalidCustomerNameWithMultipleData(String invalidName) {

		ExtentReportManager1.test.get().info("Testing invalid Customer Name: " + invalidName);

		customersPage.enterCustomerName(invalidName);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String msg = customersPage.getCustomerNameErrorMessage();
			isErrorDisplayed = msg.contains("Special characters are not allowed.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Customer Name rejected " + invalidName);
		} else {
			Assert.fail("BUG | Invalid Customer Name ACCEPTED  " + invalidName);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Customer Name ACCEPTED " + invalidName);
	}

	/// ================= EMAIL =================

	@DataProvider(name = "invalidEmails")
	public Object[][] invalidEmails() {
		return new Object[][] { { "@gmail.com" }, { ".john@gmail.com" }, { "john.@gmail.com" }, { "jo..hn@gmail.com" },
				{ "jo#hn@gmail.com" }, { "jo hn@gmail.com" }, { "john@" }, { "john@gmail" }, { "john@.gmail.com" },
				{ "john@gmail." }, { "john@gmail..com" }, { "john@gmail.c" } };
	}

	@Test(priority = 3, dataProvider = "invalidEmails", description = "Verify Customer Email rejects invalid formats")
	public void verifyCustomerEmailRejectsInvalidEmail(String invalidEmail) {

		ExtentReportManager1.test.get().info("Testing invalid Email: " + invalidEmail);

		customersPage.enterCustomerName("Ganesh");
		customersPage.enterEmail(invalidEmail);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String msg = customersPage.getEmailValidationMessage();
			isErrorDisplayed = msg.toLowerCase().contains("Invaild email address.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Email rejected  " + invalidEmail);
		} else {
			Assert.fail("BUG | Invalid Email ACCEPTED  " + invalidEmail);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Email ACCEPTED " + invalidEmail);
	}

	/// ================= Mobile Number =================
	@DataProvider(name = "invalidMobileNumbers")
	public Object[][] invalidMobileNumbers() {
		return new Object[][] {

				{ "98765" }, // less than 10 digits
				{ "987654321098" }, // more than 10 digits
				{ "98AB543210" }, // alphabets
				{ "98@#543210" }, // special characters
				{ "98 76543210" }, // spaces
				{ "0000000000" }, // all zeros
				{ "+919876543210" } // country code (if not allowed)
		};
	}

	// @Test(priority = 4, dataProvider = "invalidMobileNumbers", description =
	// "Verify Customer Mobile Number rejects invalid formats")
	public void verifyCustomerMobileRejectsInvalidFormat(String invalidMobile) {

		ExtentReportManager1.test.get().info("Testing invalid Mobile Number: " + invalidMobile);

		openCreateCustomerForm();
		customersPage.enterMobileNumber(invalidMobile);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String actualMsg = customersPage.getMobileValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("invalid");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Mobile Number rejected  " + invalidMobile);
		} else {
			Assert.fail("BUG | Invalid Mobile Number ACCEPTED  " + invalidMobile);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Mobile Number ACCEPTED " + invalidMobile);
	}

	/// ================= Aadhaar Number =================
	@DataProvider(name = "invalidAadhaarNumbers")
	public Object[][] invalidAadhaarNumbers() {
		return new Object[][] {

				{ "1234567890" }, // less than 12 digits
				{ "1234567890123" }, // more than 12 digits
				{ "1234ABCD9012" }, // alphabets
				{ "1234@#789012" }, // special characters
				{ "1234 5678 9012" }, // spaces
				{ "000000000000" }, // all zeros
				{ "012345678901" }, // starts with 0
				{ "112345678901" } // starts with 1
		};

	}

	// @Test(priority = 5, dataProvider = "invalidAadhaarNumbers", description =
	// "Verify Aadhaar Number rejects invalid format")
	public void verifyAadhaarRejectsInvalidFormat(String invalidAadhaar) {

		ExtentReportManager1.test.get().info("Testing invalid Aadhaar Number: " + invalidAadhaar);

		openCreateCustomerForm();
		customersPage.enterCustomerName("Swastik Aadhaar");
		customersPage.enterAadhaarNumber(invalidAadhaar);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String actualMsg = customersPage.getAadhaarValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild Aadhar Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Aadhaar rejected  " + invalidAadhaar);
		} else {
			Assert.fail("BUG | Invalid Aadhaar ACCEPTED " + invalidAadhaar);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Aadhaar ACCEPTED " + invalidAadhaar);
	}

	/// ================= PAN Numbers =================
	@DataProvider(name = "invalidPanNumbers")
	public Object[][] invalidPanNumbers() {
		return new Object[][] {

				{ "ABCDE1234" }, // less than 10 chars
				{ "ABCDE1234FA" }, // more than 10 chars
				{ "abcde1234f" }, // lowercase
				{ "AB12E1234F" }, // digits in first 5
				{ "ABCDE12A4F" }, // alphabet in digit section
				{ "ABCDE12345" }, // last char not alphabet
				{ "AB@DE1234F" }, // special character
				{ "ABCDE 1234F" } // space
		};
	}

	// @Test(priority = 6, dataProvider = "invalidPanNumbers", description = "Verify
	// PAN Number rejects invalid format")
	public void verifyPanRejectsInvalidFormat(String invalidPan) {

		ExtentReportManager1.test.get().info("Testing invalid PAN Number: " + invalidPan);

		openCreateCustomerForm();
		customersPage.enterCustomerName("Rutuja");
		customersPage.enterPanNumber(invalidPan);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String actualMsg = customersPage.getPanValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild PAN Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid PAN rejected  " + invalidPan);
		} else {
			Assert.fail("BUG | Invalid PAN ACCEPTED " + invalidPan);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid PAN ACCEPTED " + invalidPan);
	}

	/// ================= GST Numbers =================
	@DataProvider(name = "invalidGstNumbers")
	public Object[][] invalidGstNumbers() {
		return new Object[][] {

				{ "22ABCDE1234F1Z" }, // less than 15 chars
				{ "22ABCDE1234F1Z55" }, // more than 15 chars
				{ "00ABCDE1234F1Z5" }, // invalid state code
				{ "22AB12E1234F1Z5" }, // invalid PAN part
				{ "22abcde1234f1z5" }, // lowercase
				{ "22ABCDE1234F1@5" }, // special char
				{ "22ABCDE1234F115" }, // missing Z
				{ "22ABCDE 1234F1Z5" } // space
		};
	}

	// @Test(priority = 7, dataProvider = "invalidGstNumbers", description = "Verify
	// GST Number rejects invalid GST format")
	public void verifyGstRejectsInvalidFormat(String invalidGst) {

		ExtentReportManager1.test.get().info("Testing invalid GST Number: " + invalidGst);

		openCreateCustomerForm();
		customersPage.enterCustomerName("Rahul GST");
		customersPage.enterGstNumber(invalidGst);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String actualMsg = customersPage.getGstValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild GST Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid GST rejected  " + invalidGst);
		} else {
			Assert.fail("BUG | Invalid GST ACCEPTED  " + invalidGst);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid GST ACCEPTED " + invalidGst);
	}

	/// ================= Website =================
	@DataProvider(name = "invalidWebsiteUrls")
	public Object[][] invalidWebsiteUrls() {
		return new Object[][] {

				{ "www.example.com" }, // missing protocol
				{ "http://" }, // only protocol
				{ "htp://example.com" }, // invalid protocol
				{ "http://exa mple.com" }, // space
				{ "http://exa@mple.com" }, // special character
				{ "http://.com" }, // no domain
				{ "http://example.c" }, // invalid TLD
				{ "http://example..com" }, // double dots
				{ "example" } // plain text
		};
	}

	// @Test(priority = 8, dataProvider = "invalidWebsiteUrls", description =
	// "Verify Website URL rejects invalid format")
	public void verifyWebsiteRejectsInvalidUrl(String invalidUrl) {

		ExtentReportManager1.test.get().info("Testing invalid Website URL: " + invalidUrl);

		openCreateCustomerForm();
		customersPage.enterCustomerName("Ganesh");
		customersPage.enterWebsiteUrl(invalidUrl);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String actualMsg = customersPage.getWebsiteValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("valid");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Website rejected  " + invalidUrl);
		} else {
			Assert.fail("BUG | Invalid Website ACCEPTED  " + invalidUrl);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Website ACCEPTED " + invalidUrl);
	}

	/// ================= ZIPCODE =================

	@DataProvider(name = "invalidZipcodes")
	public Object[][] invalidZipcodes() {
		return new Object[][] { { "12345" }, { "1234567" }, { "12AB56" }, { "12@#56" }, { "123 456" }, { "000000" },
				{ " 123456 " } };
	}

	// @Test(priority = 9, dataProvider = "invalidZipcodes", description = "Verify
	// Zipcode rejects invalid input")
	public void verifyZipcodeRejectsInvalidInput(String invalidZipcode) {

		ExtentReportManager1.test.get().info("Testing invalid Zipcode: " + invalidZipcode);

		customersPage.enterCustomerName("Prajakta");
		customersPage.enterZipcode(invalidZipcode);
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;

		try {
			String msg = customersPage.getZipcodeValidationMessage();
			isErrorDisplayed = msg.toLowerCase().contains("invalid");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			ExtentReportManager1.test.get().pass("PASS | Invalid Zipcode rejected " + invalidZipcode);
		} else {
			Assert.fail("BUG | Invalid Zipcode ACCEPTED " + invalidZipcode);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Zipcode ACCEPTED " + invalidZipcode);
	}

	/// ================= DUPLICATE EMAIL =================

	// @Test(priority = 10, description = "Verify duplicate email is not allowed
	// while saving customer")
	public void verifyDuplicateEmailWhileSavingCustomer() {

		ExtentReportManager1.test.get().info("Verifying duplicate email validation");

		customersPage.enterCustomerName("Test Duplicate Email");
		customersPage.enterMobileNumber("9876543210");

		String duplicateEmail = "patil276@gmail.com";
		customersPage.enterEmail(duplicateEmail);

		cmf.ClickSaveButton();

		String toastMsg = cmf.clickSaveAndGetToast();

		boolean isDuplicateError = toastMsg != null && toastMsg.trim().equalsIgnoreCase("Error: Email Already Exists.");

		if (isDuplicateError) {
			ExtentReportManager1.test.get().pass("PASS | Duplicate email rejected " + duplicateEmail);
		} else {
			Assert.fail("BUG | Duplicate email ACCEPTED " + duplicateEmail + " | Toast: " + toastMsg);
		}

		Assert.assertTrue(isDuplicateError, "BUG: Duplicate email allowed. Toast: " + toastMsg);
	}
}
