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

public class CustomerSearchTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage; // class-level variable
	CustomerListPage clp;

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		hr = new Header(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);
		clp = new CustomerListPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		sidePanel.clickCustomers();
		Thread.sleep(2000);
	}

	@Test(priority = 1, description = "Verify search works with full valid customer name", enabled = false)
	public void shouldSearchCustomerWithFullValidName() {

		// Test data (existing customer name)
		String customerName = "Rahul Patil";

		// Step 1: Enter full customer name in search box
		cmf.search(customerName);

		// Step 2: Wait for list to refresh (handled inside page method / explicit wait)

		// Step 3: Verify only matching record is shown
		clp.waitForSearchToSettle();

		Assert.assertTrue(clp.isAnyCustomerDisplayed(), "No customer displayed after searching with full name");

		Assert.assertTrue(clp.areAllDisplayedCustomersMatchingName(customerName),
				"Displayed customers do not match searched full name");

	}

	@Test(priority = 2, description = "Verify customer list is filtered when searching with partial customer name", enabled = false)
	public void shouldSearchCustomerWithPartialName() {

		cmf.search("Sum");

		// ⭐ IMPORTANT wait
		clp.waitForSearchToSettle();
		// मगच verification
		Assert.assertTrue(clp.isAnyCustomerDisplayed(), "No customer records displayed for partial search");

		Assert.assertTrue(clp.areAllDisplayedCustomersMatchingName("Sum"),
				"Some customer records do not match partial search text");
	}

	@Test(description = "Verify customer list is filtered when searching with valid email ID", enabled = false)
	public void shouldSearchCustomerWithValidEmail() {

		String email = "patil276@gmail.com"; // existing valid email

		// Step 1: Search by email
		cmf.search(email);

		// Step 2: Wait till search completes (debounce + API)
		clp.waitForSearchToSettle();

		// Step 3: Verify at least one record is shown
		Assert.assertTrue(clp.isAnyCustomerDisplayed(), "No customer records displayed for valid email search");
	}

	@Test(description = "Verify customer list is filtered when searching with valid mobile number")
	public void shouldSearchCustomerWithValidMobileNumber() {

		String mobileNumber = "919987587485"; // existing valid mobile number

		// Step 1: Search by mobile number
		cmf.search(mobileNumber);

		// Step 2: Wait till search completes (debounce + API)
		clp.waitForSearchToSettle();

		// Step 3: Verify at least one record is shown
		Assert.assertTrue(clp.isAnyCustomerDisplayed(), "No customer records displayed for valid mobile number search");
	}

	/*
	 * Customer Edit Test Class Executed add test mehods CustomerList page added
	 * Locators action mehods testng-regression.xml file created CustomerSearchTest
	 * Class added test mehod and Executed CusotmerCreate old, Positive, Neagtive,
	 * Product Asserjunoiit is added Autoamticalyy Removed all test Classes
	 */

}
