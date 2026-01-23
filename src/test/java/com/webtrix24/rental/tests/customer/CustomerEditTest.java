package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomerListPage;
import com.webtrix24.rental.pages.CustomerReportPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class CustomerEditTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage; // class-level variable
	CustomerListPage clP;
	CustomerReportPage crp;

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		hr = new Header(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);
		clP = new CustomerListPage(driver);
		crp = new CustomerReportPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		sidePanel.clickCustomers();
		Thread.sleep(2000);
	}

	@Test(priority = 2, description = "Verify user can edit customer details successfully")
	public void shouldEditCustomerSuccessfully() {

		// 1. Open Edit form
		clP.openFirstCustomerEditForm();
		Assert.assertTrue(clP.isUpdateCustomerFormOpened(), "Update Customer form not opened");

		// 2. Update fields
		String updatedName = "Rahul Patil";
		customersPage.enterCustomerName(updatedName);
		customersPage.enterBillingName("Rahul Enterprises");

		// 3. Save
		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.toLowerCase().contains("success"), "Update success toast not shown");

		// NEW (CORRECT VALIDATION)
		clP.waitForSearchToSettle();

		Assert.assertTrue(clP.isAnyCustomerDisplayed(), "No customer records displayed after update");

		Assert.assertTrue(clP.areAllDisplayedCustomersMatchingName(updatedName),
				"Updated customer name not reflected correctly in list");
	}

	@Test(description = "Verify customer report opens from customer list", enabled = false)
	public void shouldOpenCustomerReportFromList() {

		clP.openFirstCustomerReport();

		Assert.assertTrue(crp.isCustomerReportOpened(), "Customer Equipment Report page not opened");
	}

}
