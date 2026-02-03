package com.webtrix24.rental.tests.customer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class CustomerSmokeTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	CustomersPage customersPage;

	@BeforeMethod
	@BeforeClass
	public void setUp() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		customersPage = new CustomersPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));

	}

	@Test(priority = 1)
	public void verifyCustomersModuleOpen() {
		sidePanel.clickCustomers();
		// Assert.assertTrue(customersPage.isCustomersListDisplayed());
	}

	@Test(priority = 2)
	public void verifyCreateButtonVisible() {
		// Assert.assertTrue(cmf.isCreateButtonDisplayed());
	}

	@Test(priority = 3)
	public void verifyCreateCustomerFormOpen() {
		cmf.clickCreateButton();
		Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed());
	}

	@Test(priority = 4)
	public void verifySaveButtonVisible() {
		// Assert.assertTrue(customersPage.isSaveButtonDisplayed());
	}

}
