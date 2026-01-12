package com.webtrix24.rental.tests.dashboard;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.DashboardPage;
import com.webtrix24.rental.pages.DeliveriesPage;
import com.webtrix24.rental.pages.InvoicesPages;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductsCreatePage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class DashboardPositiveTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	DashboardPage dashboardPage;
	ProductsCreatePage productsCreatePage;
	DeliveriesPage deliveriesPage;
	InvoicesPages invoicesPages;

	@BeforeClass
	public void setUpForDashboard() throws Exception {
		loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);
		sidePanel = new SidePanel(driver);
		dashboardPage = new DashboardPage(driver);
		productsCreatePage = new ProductsCreatePage(driver);
		deliveriesPage = new DeliveriesPage(driver);
		invoicesPages = new InvoicesPages(driver);
	}

	@Test
	public void checkDashboard() throws Exception {
		dashboardPage.clickOnInventoryView();
		Thread.sleep(5000);
		dashboardPage.clickOnQuickLinks();
		dashboardPage.addProduct();
		productsCreatePage.clickProductNameField();
		productsCreatePage.selectProduct("Mac Air");
		productsCreatePage.selectProductType("Laptop");
	}
}
