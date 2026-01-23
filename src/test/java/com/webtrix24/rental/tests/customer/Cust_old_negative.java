package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class Cust_old_negative extends BaseClass {

	// EXTENT REPORT VARIABLES (ADD HERE)
	protected ExtentReports extent;
	protected ExtentTest extentTest;

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
	@BeforeMethod
	private void openCreateCustomerForm() {
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

	}

	@AfterMethod
	public void closeFormAfterEachTest() {
		try {
			cmf.formCancalButton();
		} catch (Exception e) {
			System.out.println("Cancel button not visible or form already closed");
		}
	}

	/********************* NEGATIVE TEST CASES ***********************************/

	@Test(priority = 1, description = "Verify mandatory field validation for Customer Name")
	public void verifyCustomerNameMandatoryValidation() {
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message
		String actualValidation = customersPage.getCustomerNameValidationMessage();

		Assert.assertEquals(actualValidation, "Customer Name is required.",
				"Mandatory validation message not displayed for Customer Name");

		System.out.println("Customer Name mandatory validation verified successfully");
	}

	// @Test(priority = 2, description = "Verify invalid input in Customer Name
	// field") //Single Input
	public void verifyInvalidCustomerName() {
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		// Step 2: Enter invalid Customer Name
		customersPage.enterCustomerName("John@123");

		// Step 3: Click Save
		cmf.ClickSaveButton();

		// Step 4: Verify validation message
		String actualMsg = customersPage.getCustomerNameErrorMessage();

		Assert.assertEquals(actualMsg, "Special characters are not allowed.",
				"Invalid Customer Name validation failed");

		System.out.println("Invalid Customer Name validation verified");
	}

	@DataProvider(name = "invalidCustomerNames")
	public Object[][] invalidCustomerNames() {
		return new Object[][] { { "12345" }, { "@#$%^" }, { "John@123" } };
	}

	@Test(priority = 2, dataProvider = "invalidCustomerNames", description = "Verify invalid Customer Name inputs")
	public void verifyInvalidCustomerNameWithMultipleData(String invalidName) {
		System.out.println("Testing invalid Customer Name: " + invalidName);

		extentTest = extent.createTest("Verify Customer Name rejects invalid Name Format",
				"Module: Customer → Create Customer | " + "Field: Customer Name | "
						+ "Expected: Invalid Name should be rejected");

		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		// Step 2: Enter invalid Customer Name
		customersPage.enterCustomerName(invalidName);

		// Step 3: Click Save
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {

			actualMsg = customersPage.getCustomerNameErrorMessage();

			isErrorDisplayed = actualMsg.contains("Special characters are not allowed.");

		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid Customer Name rejected " + invalidName);
		} else {
			Assert.fail("BUG | Invalid Customer Name ACCEPTED " + invalidName);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Customer Name ACCEPTED " + invalidName);

		System.out.println("Validation verified for invalid input: " + invalidName);
	}

	@DataProvider(name = "invalidEmails")
	public Object[][] invalidEmails() {
		return new Object[][] {

				// Local part issues
				{ "@gmail.com" }, { ".john@gmail.com" }, { "john.@gmail.com" }, { "jo..hn@gmail.com" },
				{ "jo#hn@gmail.com" }, { "jo hn@gmail.com" },

				// Domain part issues
				{ "john@" }, { "john@gmail" }, { "john@.gmail.com" }, { "john@gmail." }, { "john@gmail..com" },
				{ "john@gmail.c" } };
	}

	@Test(priority = 3, dataProvider = "invalidEmails", description = "Verify Customer Email rejects invalid email formats")
	public void verifyCustomerEmailRejectsInvalidEmail(String invalidEmail) {
		System.out.println("Testing invalid Email: " + invalidEmail);

		extentTest = extent.createTest("Verify Customer Email rejects invalid Email",
				"Module: Customer → Create Customer | " + "Field: Customer Email | "
						+ "Expected: Invalid Email should be rejected");

		// Step 1: Open Create Customer form
		openCreateCustomerForm();
		customersPage.enterCustomerName("Ganesh");
		// Step 1: Enter invalid email
		customersPage.enterEmail(invalidEmail);

		// Step 2: Click Save
		cmf.ClickSaveButton();

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getEmailValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild email address.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid Customer Email rejected  " + invalidEmail);
		} else {
			Assert.fail("BUG | Invalid Customer Email ACCEPTED " + invalidEmail);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Email ACCEPTED " + invalidEmail);

		System.out.println("Invalid email rejected successfully: " + invalidEmail);

	}

	// DataProvider – Invalid Mobile Numbers
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

	@Test(priority = 4, dataProvider = "invalidMobileNumbers", description = "Verify Customer Mobile Number rejects invalid formats")
	public void verifyCustomerMobileRejectsInvalidFormat(String invalidMobile) {

		extentTest = extent.createTest("Verify Mobile Number rejects invalid Mobile Number Formats",
				"Module: Customer → Create Customer | " + "Field: Mobile Number | "
						+ "Expected: Invalid Mobile Number should be rejected");

		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		// Step 1: Enter invalid mobile number
		customersPage.enterMobileNumber(invalidMobile);

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message
		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getMobileValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("invalid Mobile");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid Mobile Number rejected  " + invalidMobile);
		} else {
			Assert.fail("BUG | Invalid Mobile Number ACCEPTED " + invalidMobile);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Mobile Number ACCEPTED " + invalidMobile);

		System.out.println("Invalid mobile rejected successfully: " + invalidMobile);

	}

	// DataProvider – Invalid Aadhaar Numbers
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
		extentTest = extent.createTest("Verify Aadhar Number rejects invalid Aadhar Number Formats",
				"Module: Customer → Create Customer | " + "Field: Aadhar Number | "
						+ "Expected: Invalid Aadhar Number should be rejected");

		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		customersPage.enterCustomerName("Swastik Addhaar");

		// Step 1: Enter invalid Aadhaar number
		customersPage.enterAadhaarNumber(invalidAadhaar);

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getAadhaarValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild Aadhar Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid Aadhar Number rejected  " + invalidAadhaar);
		} else {
			Assert.fail("BUG | Invalid Aadhar Number ACCEPTED " + invalidAadhaar);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Aadhar Number ACCEPTED " + invalidAadhaar);

		System.out.println("Invalid Aadhaar rejected successfully: " + invalidAadhaar);
	}

	// DataProvider – Invalid PAN Numbers
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
		extentTest = extent.createTest("Verify PAN Number rejects invalid PAN Number Formats",
				"Module: Customer → Create Customer | " + "Field: PAN No. | "
						+ "Expected: Invalid PAN Number should be rejected");

		openCreateCustomerForm();

		customersPage.enterCustomerName("Rtuja");
		// Step 1: Enter invalid PAN
		customersPage.enterPanNumber(invalidPan);

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getPanValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild PAN Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid PAN Number rejected  " + invalidPan);
		} else {
			Assert.fail("BUG | Invalid PAN Number ACCEPTED " + invalidPan);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid PAN Number ACCEPTED " + invalidPan);

		System.out.println("Invalid PAN rejected successfully: " + invalidPan);
	}

	// DataProvider – Invalid GST Numbers
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

	@Test(priority = 7, dataProvider = "invalidGstNumbers", description = "Verify GST Numberrejects invalid GST format")
	public void verifyGstRejectsInvalidFormat(String invalidGst) {
		extentTest = extent.createTest("Verify GST Number rejects invalid GST Number Formats",
				"Module: Customer → Create Customer | " + "Field: GST No. | "
						+ "Expected: Invalid GST Number should be rejected");
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		customersPage.enterCustomerName("Rahul");

		// Step 2: Enter invalid GST number
		customersPage.enterGstNumber(invalidGst);

		// Step 3: Click Save
		cmf.ClickSaveButton();

		// Step 4: Verify validation message

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getGstValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild GST Number.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid GST Number rejected  " + invalidGst);
		} else {
			Assert.fail("BUG | Invalid GST Number ACCEPTED " + invalidGst);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid GST Number ACCEPTED " + invalidGst);

		System.out.println("Invalid GST rejected successfully: " + invalidGst);

	}

	// DataProvider – Invalid Website URLs
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

	@Test(priority = 8, dataProvider = "invalidWebsiteUrls", description = "Verify Website URL rejects invalid format")
	public void verifyWebsiteRejectsInvalidUrl(String invalidUrl) {

		extentTest = extent.createTest("Verify Website rejects invalid Website Formats",
				"Module: Customer → Create Customer | " + "Field: Website  | "
						+ "Expected: Invalid Website should be rejected");
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		customersPage.enterCustomerName("Ganesh");
		// Step 1: Enter invalid website URL
		customersPage.enterWebsiteUrl(invalidUrl);

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message

		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getWebsiteValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("valid website");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid website URL rejected  " + invalidUrl);
		} else {
			Assert.fail("BUG | Invalid website URL ACCEPTED " + invalidUrl);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid website URL ACCEPTED " + invalidUrl);

		System.out.println("Invalid website URL rejected successfully: " + invalidUrl);

	}

	// DataProvider – Invalid Zipcodes
	@DataProvider(name = "invalidZipcodes")
	public Object[][] invalidZipcodes() {
		return new Object[][] {

				{ "12345" }, // less than 6 digits
				{ "1234567" }, // more than 6 digits
				{ "12AB56" }, // alphabets
				{ "12@#56" }, // special characters
				{ "123 456" }, // space
				{ "000000" }, // all zeros
				{ " 123456 " } // leading/trailing spaces
		};
	}

	@Test(priority = 9, dataProvider = "invalidZipcodes", description = "Verify Zipcode rejects invalid input")
	public void verifyZipcodeRejectsInvalidInput(String invalidZipcode) {
		extentTest = extent.createTest("Verify Zipcode rejects invalid Zipcode Formats",
				"Module: Customer → Create Customer | " + "Field: Zipcode  | "
						+ "Expected: Invalid Zipcode should be rejected");
		// Step 1: Open Create Customer form
		openCreateCustomerForm();

		customersPage.enterCustomerName("Sumit");

		// Step 2: Enter invalid zipcode
		customersPage.enterZipcode(invalidZipcode);

		// Step 3: Click Save
		cmf.ClickSaveButton();

		// Step 4: Verify validation message
		boolean isErrorDisplayed = false;
		String actualMsg = "";

		try {
			actualMsg = customersPage.getZipcodeValidationMessage();
			isErrorDisplayed = actualMsg.toLowerCase().contains("Invaild Zip Code.");
		} catch (Exception e) {
			isErrorDisplayed = false;
		}

		if (isErrorDisplayed) {
			extentTest.pass("PASS | Invalid Zipcode rejected  " + invalidZipcode);
		} else {
			Assert.fail("BUG | Invalid Zipcode ACCEPTED " + invalidZipcode);
		}

		Assert.assertTrue(isErrorDisplayed, "BUG: Invalid Zipcode ACCEPTED " + invalidZipcode);

		System.out.println("Invalid Zipcode rejected successfully: " + invalidZipcode);
	}

	@Test(priority = 10, description = "Verify duplicate email is not allowed while saving customer")
	public void verifyDuplicateEmailWhileSavingCustomer() {

		// Extent Report Test + Proper Description
		extentTest = extent.createTest("Verify duplicate Customer Email is not allowed",
				"Module: Customer → Create Customer | " + "Field: Customer Email | "
						+ "Scenario: Duplicate email should not be allowed | "
						+ "Expected: Error toast 'Email Already Exists'");

		// Step 1: Open Create Customer form
		openCreateCustomerForm();
		extentTest.info("Create Customer form opened");

		// Step 2: Fill mandatory fields
		customersPage.enterCustomerName("Test Duplicate Email");
		customersPage.enterMobileNumber("9876543210");

		extentTest.info("Entered Customer Name and Mobile Number");

		// Step 3: Enter duplicate email (already exists)
		String duplicateEmail = "patil276@gmail.com";
		customersPage.enterEmail(duplicateEmail);
		extentTest.info("Entered duplicate email: " + duplicateEmail);

		// Step 4: Click Save
		cmf.ClickSaveButton();
		extentTest.info("Clicked Save button");

		// Step 5: Capture toast message
		String toastMsg = cmf.clickSaveAndGetToast();
		extentTest.info("Toast message displayed: " + toastMsg);

		// Step 6: Verify toast message
		boolean isDuplicateEmailError = toastMsg != null
				&& toastMsg.trim().equalsIgnoreCase("Error: Email Already Exists.");

		if (isDuplicateEmailError) {
			extentTest.pass("PASS | Duplicate email correctly rejected " + duplicateEmail);
		} else {
			Assert.fail("BUG  | Duplicate email ACCEPTED " + duplicateEmail + " | Actual Toast: " + toastMsg);
		}

		Assert.assertTrue(isDuplicateEmailError, "BUG: Duplicate email allowed. Actual toast: " + toastMsg);

		System.out.println("Duplicate email validation verified successfully");
	}

}
