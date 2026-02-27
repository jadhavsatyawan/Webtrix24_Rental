package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductListPage;
import com.webtrix24.rental.pages.ProductsCreatePage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;
import com.webtrix24.rental.utils.ExcelUtility;

public class ProductBulkUploadTest extends BaseClass {
	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage productsCreate;
	ProductListPage pListPage;

	// Login & basic navigation – ONLY ONCE
	@BeforeClass
	public void setUpProductCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		productsCreate = new ProductsCreatePage(driver);
		pListPage = new ProductListPage(driver);
		// Login
		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);

		// Navigate to Product module
		sidePanel.clickProducts();
		Thread.sleep(2000);
	}

	// MAIN BULK UPLOAD TEST
	@Test
	public void bulkUploadProducts() throws Exception {

		String path = System.getProperty("user.dir") + "/testdata/Product Data.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);

		for (int i = 1;; i++) {

			System.out.println("========== Processing Row : " + i + " ==========");

			// Read serial first (End of Excel detection)
			String serialNo = xlutil.getCellData("Sheet1", i, 3);

			if (serialNo == null || serialNo.trim().isEmpty()) {
				System.out.println("No more data found. Stopping execution at row: " + i);
				break;
			}

			// Open create form
			cmf.clickCreateButton();
			Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(), "Create Product form not opened");

			// ===== Read Excel Columns =====
			String productName = xlutil.getCellData("Sheet1", i, 0);
			String productType = xlutil.getCellData("Sheet1", i, 1);
			String unit = xlutil.getCellData("Sheet1", i, 2);
			String modelName = xlutil.getCellData("Sheet1", i, 4);
			String modelNumber = xlutil.getCellData("Sheet1", i, 5);
			String generation = xlutil.getCellData("Sheet1", i, 6);
			String hdd = xlutil.getCellData("Sheet1", i, 7);
			String memory = xlutil.getCellData("Sheet1", i, 8);
			String os = xlutil.getCellData("Sheet1", i, 9);
			String screen = xlutil.getCellData("Sheet1", i, 10);
			String processor = xlutil.getCellData("Sheet1", i, 11);
			String purchaseDate = xlutil.getCellData("Sheet1", i, 12);
			String purchasePrice = xlutil.getCellData("Sheet1", i, 13);
			String warrantyDate = xlutil.getCellData("Sheet1", i, 14);
			String vendor = xlutil.getCellData("Sheet1", i, 15);
			String rentalPrice = xlutil.getCellData("Sheet1", i, 16);
			String graphics = xlutil.getCellData("Sheet1", i, 17);
			String condition = xlutil.getCellData("Sheet1", i, 18);

			String imei = xlutil.getCellData("Sheet1", i, 19);
			String description = xlutil.getCellData("Sheet1", i, 20);
			// ===== Fill Form =====
			productsCreate.fillProductFormWthoutBarcodeAndSave(productName, productType, unit, modelName, modelNumber,
					generation, hdd, memory, os, screen, processor, purchaseDate, purchasePrice, warrantyDate, vendor,
					rentalPrice, graphics, condition, imei, description);

			// ===== Save =====
			cmf.clickSaveAndGetToast();

			Thread.sleep(2000);
			System.out.println("Row " + i + " submitted successfully");
		}

		// Logout – once
		/*
		 * @AfterClass public void tearDown() { driver.quit(); }
		 */
	}
}