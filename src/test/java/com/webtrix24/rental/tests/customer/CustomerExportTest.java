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
import com.webtrix24.rental.utils.ExportExcel;

public class CustomerExportTest extends BaseClass {

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

	@Test(description = "Verify Export PDF opens customer data in new tab", enabled = false)
	public void shouldExportCustomerListAsPDF() {

		boolean isPdfOpened = cmf.clickExportPDF();

		Assert.assertTrue(isPdfOpened, "PDF content not loaded in new tab");

	}

	@Test(description = "Verify customer data can be exported as Excel")
	public void shouldExportCustomerListAsExcel() throws InterruptedException {
		cmf.clickExportExcel();
		boolean isDownloaded = ExportExcel.isExcelDownloaded();

		Assert.assertTrue(isDownloaded, "Excel file not downloaded");

	}

	@Test(description = "Verify export PDF works after applying filter", enabled = false)
	public void shouldExportFilteredCustomerListAsPDF() {

	}

}
