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

public class ProductTezcarktUploadTest2 extends BaseClass {
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

	@Test
	public void bulkUploadProducts() throws Exception {

		String path = System.getProperty("user.dir") + "/testdata/Report_products.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);

		int rowCount = xlutil.getRowCount("Sheet1");

		int successCount = 0;
		int failCount = 0;

		java.util.List<Integer> failedRows = new java.util.ArrayList<>();

		for (int i = 1; i <= rowCount; i++) {

			System.out.println("========== Processing Row : " + i + " ==========");

			try {

				// ===== Skip Already DONE Rows =====
				String status = xlutil.getCellData("Sheet1", i, 19);
				if ("DONE".equalsIgnoreCase(status)) {
					System.out.println("Row " + i + " already processed. Skipping...");
					continue;
				}

				// ===== Mandatory Fields =====
				String productName = xlutil.getCellData("Sheet1", i, 0);
				String productType = xlutil.getCellData("Sheet1", i, 1);
				String unit = xlutil.getCellData("Sheet1", i, 2);
				String serialNo = xlutil.getCellData("Sheet1", i, 3);

				if (productName == null || productName.trim().isEmpty() || productType == null
						|| productType.trim().isEmpty() || serialNo == null || serialNo.trim().isEmpty()) {

					System.out.println("Skipping row " + i + " - Mandatory data missing");
					xlutil.setCellData("Sheet1", i, 19, "SKIPPED");
					continue;
				}

				// ===== Optional Fields =====
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
				String rentalPrice = xlutil.getCellData("Sheet1", i, 15);
				String graphics = xlutil.getCellData("Sheet1", i, 16);
				String condition = xlutil.getCellData("Sheet1", i, 17);
				String imei = xlutil.getCellData("Sheet1", i, 18);

				// ===== Open Form =====
				cmf.clickCreateButton();
				Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(), "Create Product form not opened");

				// ===== Fill Form =====
				productsCreate.fillProductFormAndSave(productName, productType, unit, serialNo, modelName, modelNumber,
						generation, hdd, memory, os, screen, processor, purchaseDate, purchasePrice, warrantyDate,
						rentalPrice, graphics, condition, imei);

				// ===== Save =====
				cmf.clickSaveAndGetToast();
				Thread.sleep(1500);

				System.out.println("Row " + i + " submitted successfully");

				xlutil.setCellData("Sheet1", i, 19, "DONE");

				successCount++;

			} catch (Exception e) {

				System.out.println("Row " + i + " FAILED: " + e.getMessage());

				xlutil.setCellData("Sheet1", i, 19, "FAILED");

				failedRows.add(i);
				failCount++;

				driver.navigate().refresh();
				Thread.sleep(2000);
			}
		}

		System.out.println("========= BULK EXECUTION SUMMARY =========");
		System.out.println("Total Records : " + rowCount);
		System.out.println("Success Count : " + successCount);
		System.out.println("Fail Count    : " + failCount);
		System.out.println("Failed Rows   : " + failedRows);
		System.out.println("==========================================");
	}

	/*
	 * @Test public void bulkUploadProducts() throws Exception {
	 * 
	 * String path = System.getProperty("user.dir") +
	 * "/testdata/Report_products.xlsx"; ExcelUtility xlutil = new
	 * ExcelUtility(path);
	 * 
	 * int rowCount = xlutil.getRowCount("Sheet1");
	 * 
	 * for (int i = 1; i <= rowCount; i++) {
	 * 
	 * System.out.println("========== Processing Row : " + i + " ==========");
	 * 
	 * try {
	 * 
	 * // ===== Mandatory Fields ===== String productName =
	 * xlutil.getCellData("Sheet1", i, 0); String productType =
	 * xlutil.getCellData("Sheet1", i, 1); String unit =
	 * xlutil.getCellData("Sheet1", i, 2); String serialNo =
	 * xlutil.getCellData("Sheet1", i, 3);
	 * 
	 * // Skip if mandatory missing if (productName == null ||
	 * productName.trim().isEmpty() || productType == null ||
	 * productType.trim().isEmpty() || serialNo == null ||
	 * serialNo.trim().isEmpty()) {
	 * 
	 * System.out.println("Skipping row " + i + " - Mandatory data missing");
	 * continue; }
	 * 
	 * // ===== Optional Fields ===== String modelName =
	 * xlutil.getCellData("Sheet1", i, 4); String modelNumber =
	 * xlutil.getCellData("Sheet1", i, 5); String generation =
	 * xlutil.getCellData("Sheet1", i, 6); String hdd = xlutil.getCellData("Sheet1",
	 * i, 7); String memory = xlutil.getCellData("Sheet1", i, 8); String os =
	 * xlutil.getCellData("Sheet1", i, 9); String screen =
	 * xlutil.getCellData("Sheet1", i, 10); String processor =
	 * xlutil.getCellData("Sheet1", i, 11); String purchaseDate =
	 * xlutil.getCellData("Sheet1", i, 12); String purchasePrice =
	 * xlutil.getCellData("Sheet1", i, 13); String warrantyDate =
	 * xlutil.getCellData("Sheet1", i, 14); String rentalPrice =
	 * xlutil.getCellData("Sheet1", i, 15); String graphics =
	 * xlutil.getCellData("Sheet1", i, 16); String condition =
	 * xlutil.getCellData("Sheet1", i, 17); String imei =
	 * xlutil.getCellData("Sheet1", i, 18);
	 * 
	 * // ===== Open Create Form ===== cmf.clickCreateButton();
	 * Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(),
	 * "Create Product form not opened");
	 * 
	 * // ===== Fill Form ===== productsCreate.fillProductFormAndSave(productName,
	 * productType, unit, serialNo, modelName, modelNumber, generation, hdd, memory,
	 * os, screen, processor, purchaseDate, purchasePrice, warrantyDate,
	 * rentalPrice, graphics, condition, imei);
	 * 
	 * // ===== Save ===== cmf.clickSaveAndGetToast();
	 * 
	 * Thread.sleep(1500);
	 * 
	 * System.out.println("Row " + i + " submitted successfully");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("Row " + i + " FAILED: " + e.getMessage());
	 * e.printStackTrace();
	 * 
	 * // Try to recover - go back to product list page driver.navigate().refresh();
	 * Thread.sleep(2000); } }
	 * 
	 * System.out.println("========= BULK UPLOAD COMPLETED ========="); }
	 */
	// MAIN BULK UPLOAD TEST
	/*
	 * @Test public void bulkUploadProducts() throws Exception {
	 * 
	 * String path = System.getProperty("user.dir") +
	 * "/testdata/Report_products.xlsx"; ExcelUtility xlutil = new
	 * ExcelUtility(path);
	 * 
	 * for (int i = 1;; i++) {
	 * 
	 * System.out.println("========== Processing Row : " + i + " ==========");
	 * 
	 * // Read serial first (End of Excel detection) String serialNo =
	 * xlutil.getCellData("Sheet1", i, 3);
	 * 
	 * if (serialNo == null || serialNo.trim().isEmpty()) {
	 * System.out.println("No more data found. Stopping execution at row: " + i);
	 * break; }
	 * 
	 * // Open create form cmf.clickCreateButton();
	 * Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(),
	 * "Create Product form not opened");
	 * 
	 * // ===== Read Excel Columns ===== String productName =
	 * xlutil.getCellData("Sheet1", i, 0); String productType =
	 * xlutil.getCellData("Sheet1", i, 1); String unit =
	 * xlutil.getCellData("Sheet1", i, 2); String modelName =
	 * xlutil.getCellData("Sheet1", i, 4); String modelNumber =
	 * xlutil.getCellData("Sheet1", i, 5); String generation =
	 * xlutil.getCellData("Sheet1", i, 6); String hdd = xlutil.getCellData("Sheet1",
	 * i, 7); String memory = xlutil.getCellData("Sheet1", i, 8); String os =
	 * xlutil.getCellData("Sheet1", i, 9); String screen =
	 * xlutil.getCellData("Sheet1", i, 10); String processor =
	 * xlutil.getCellData("Sheet1", i, 11); String purchaseDate =
	 * xlutil.getCellData("Sheet1", i, 12); String purchasePrice =
	 * xlutil.getCellData("Sheet1", i, 13); String warrantyDate =
	 * xlutil.getCellData("Sheet1", i, 14); String rentalPrice =
	 * xlutil.getCellData("Sheet1", i, 15); String graphics =
	 * xlutil.getCellData("Sheet1", i, 16); String condition =
	 * xlutil.getCellData("Sheet1", i, 17);
	 * 
	 * String imei = xlutil.getCellData("Sheet1", i, 18); // ===== Fill Form =====
	 * productsCreate.fillProductFormAndSave(productName, productType, unit,
	 * modelName, modelNumber, generation, hdd, memory, os, screen, processor,
	 * purchaseDate, purchasePrice, warrantyDate, rentalPrice, graphics, condition,
	 * imei);
	 * 
	 * // ===== Save ===== cmf.clickSaveAndGetToast();
	 * 
	 * Thread.sleep(2000); System.out.println("Row " + i +
	 * " submitted successfully"); }
	 */

	// Logout – once
	/*
	 * @AfterClass public void tearDown() { driver.quit(); }
	 */
}
