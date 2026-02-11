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
import com.webtrix24.rental.utils.ExcelUtility;

public class CustomerBulkUploadTest extends BaseClass {

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

	@Test
	public void bulkUploadCustomers() throws Exception {

		String path = System.getProperty("user.dir") + "/testdata/Customer_Data.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);

		for (int i = 1;; i++) {

			System.out.println("========== Processing Customer Row : " + i + " ==========");

			// Mandatory field check (Customer Name)
			String customerName = xlutil.getCellData("Sheet1", i, 0);

			if (customerName == null || customerName.trim().isEmpty()) {
				System.out.println("No more customer data. Stopping at row: " + i);
				break;
			}

			// Open create form
			cmf.clickCreateButton();
			Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(), "Create Customer form not opened");

			// ===== Read Optional Fields =====
			String source = xlutil.getCellData("Sheet1", i, 1);
			String email = xlutil.getCellData("Sheet1", i, 2);
			String mobile = xlutil.getCellData("Sheet1", i, 3);
			String whatsapp = xlutil.getCellData("Sheet1", i, 4);
			String billingName = xlutil.getCellData("Sheet1", i, 5);
			String address = xlutil.getCellData("Sheet1", i, 6);
			String zipcode = xlutil.getCellData("Sheet1", i, 7);
			String aadhar = xlutil.getCellData("Sheet1", i, 8);
			String pan = xlutil.getCellData("Sheet1", i, 9);
			String website = xlutil.getCellData("Sheet1", i, 10);
			String gst = xlutil.getCellData("Sheet1", i, 11);
			String gstState = xlutil.getCellData("Sheet1", i, 12);

			// ===== Fill Form =====
			customersPage.fillCustomerFormSafely(customerName, source, email, mobile, whatsapp, billingName, address,
					zipcode, aadhar, pan, website, gst, gstState);

			// Save
			cmf.clickSaveAndGetToast();

			Thread.sleep(1500);
			System.out.println("Customer Row " + i + " submitted successfully");
		}
	}
}
