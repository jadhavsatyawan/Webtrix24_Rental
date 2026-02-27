package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductsCreatePage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class ProductExportTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	Header hr;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage createPage; // class-level variable

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		hr = new Header(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		createPage = new ProductsCreatePage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		sidePanel.clickProducts();
		Thread.sleep(2000);
	}

	@Test(description = "Verify Export PDF opens Products data in new tab")
	public void shouldExportProductsListAsPDF() {

		boolean isPdfOpened = cmf.clickExportPDF("/API/reports");

		Assert.assertTrue(isPdfOpened, "Products Export PDF not opened or incorrect API URL");
	}

}
