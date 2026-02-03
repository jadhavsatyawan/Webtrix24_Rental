package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductListPage;
import com.webtrix24.rental.pages.ProductsCreatePage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class ProductCreatePositiveTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage productsCreate;
	ProductListPage pListPage;

	// Login & basic navigation – ONLY ONCE
	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

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

	// Helper: Open Create Product Form
	@BeforeMethod
	private void openCreateProductForm() {
		cmf.clickCreateButton();
		Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(), "Create Product form not opened");
	}

	@Test(priority = 1, description = "Verify product selection from dropdown in Create Product form")
	public void verifyProductSelectionFromDropdown() throws InterruptedException {

		// Step 2: Click Product Name field
		productsCreate.clickProductNameField();

		// Step 3: Select value from dropdown
		productsCreate.selectProduct("Lava Magnum Laptop");

		// Step 4: Verify selection
		String selectedValue = productsCreate.getSelectedProductValue();
		AssertJUnit.assertEquals(selectedValue, "Lava Magnum Laptop", "Product selection failed!");
		System.out.println("Product selected successfully: " + selectedValue);
	}

	@Test(priority = 2, description = "Verify product Type selection from dropdown in Create Product form")
	public void verifyProductTypeSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickProductTypeField();

		productsCreate.selectProductType("Laptop");

	}

	@Test(priority = 3, description = "Verify product unit  selection from dropdown in Create Product form")
	public void verifyProductunitSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickProductTypeField();

		productsCreate.selectProductUnit("Per Set"); // Per Set, single

	}
	/*
	 * @Test(priority = 6, description =
	 * "Verify product Serial Number is entered in the field") public void
	 * verifyProductSerailNumEnteredField() throws InterruptedException {
	 * 
	 * // Step 1: Generate and enter random serial number
	 * productsPage.setProductSerialNumber();
	 * 
	 * // Step 2: (Optional) Verify the serial number is actually entered String
	 * enteredSerial = productsPage.getEnteredProductSerialNumber();
	 * System.out.println("Entered Product Serial Number: " + enteredSerial);
	 * 
	 * Assert.assertFalse(enteredSerial.isEmpty(),
	 * "Serial number field should not be empty"); }
	 */

	@Test(priority = 4, description = "Verify product Serial Number is entered in the field")
	public void verifyProductSerailNumEnteredField() throws InterruptedException {

		// Step 1: Give your base serial prefix
		String baseSerial = "LP-";

		// Step 2: Call method (it will auto-add unique random part)
		productsCreate.setProductSerialNumber(baseSerial);

		// Step 3: Verify
		String enteredSerial = productsCreate.getEnteredProductSerialNumber();
		System.out.println("Final Serial Number Entered: " + enteredSerial);

		Assert.assertTrue(enteredSerial.startsWith(baseSerial), "Serial number should start with base prefix");
	}

	@Test(priority = 5, description = "Verify product Model Name selection from dropdown in Create Product form")
	public void verifyProductModelNameSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickModelNameField();

		productsCreate.selectModelName("LG 24MP60G");// OptiPlex 3080
	}

	@Test(priority = 6, description = "Verify product Model Number selection from dropdown in Create Product form")
	public void verifyProductModelNumberSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickModelNumField();

		productsCreate.selectModelNum("A2338");// A2338,A340
	}

	@Test(priority = 7, description = "Verify product Generation selection from dropdown in Create Product form")
	public void verifyProductGenerationSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickGenerationField();

		productsCreate.selectGeneration("5th Gen");// 8th Gen
	}

	@Test(priority = 8, description = "Verify product HDD Capacityselection from dropdown in Create Product form")
	public void verifyProductHDDCapacitySelectionFromDropdown() throws InterruptedException {

		productsCreate.clickHddCpacityField();

		productsCreate.selectHDDCapacity("512GB SSD");// 512GB SSD,2TB HDD
	}

	@Test(priority = 9, description = "Verify product Memory selection from dropdown in Create Product form")
	public void verifyProductMemorySelectionFromDropdown() throws InterruptedException {

		productsCreate.clickMemoryField();

		productsCreate.selectMemory("8 GB");// 8 GB,16 GB
	}

	@Test(priority = 10, description = "Verify product Operating System selection from dropdown in Create Product form")
	public void verifyProductOperatingSystemSelectionFromDropdown() throws InterruptedException {
		productsCreate.clickOperatingSystemField();

		productsCreate.selectOperatingSystem("win 11");// win 11,Mac OS
	}

	@Test(priority = 11, description = "Verify product Screen Size selection from dropdown in Create Product form")
	public void verifyProductScreenSizeSelectionFromDropdown() throws InterruptedException {

		productsCreate.clickScreenSizeField();

		productsCreate.selectScreenSize("14.3");
	}

	@Test(priority = 12, description = "Verify product Processor selection from dropdown in Create Product form")
	public void verifyProductProcessorSelectionFromDropdown() throws InterruptedException {
		productsCreate.clickProcessorField();

		productsCreate.selectProcessor("intel core i5");// intel core i5,M2
	}

	@Test(priority = 13, description = "Verify product Purchase Date  selection from Calender in Create Product form")
	public void verifyPurchaseDateSelection() {

		// Select specific date (example: 1 Sept 2025)
		productsCreate.selectPurchaseDate("2025-09-03");

		// OR you can use current date as well
		// productsPage.selectPurchaseDate(LocalDate.now().toString()); // "2025-10-29"
	}

	@Test(priority = 14, description = "Verify product Purchase Price Enter in Field  in Create Product form")
	public void verifyPurchasePriceEntred() {
		productsCreate.setPurchasePrice("78945");

	}

	@Test(priority = 15, description = "Verify product Warranty Up To Date  selection from Calender in Create Product form")
	public void verifyWarrantyUpToDateSelection() {

		// Select specific date (example: 1 Sept 2025)
		productsCreate.selectWarrantyUpTo("01-11-2026");

		// OR you can use current date as well
		// productsPage.selectPurchaseDate(LocalDate.now().toString()); // "2025-10-29"
	}

	@Test(priority = 16, description = "Verify vendor selection from dropdown in Create Product form")
	public void verifyProductVendorSelectionFromDropdown() throws InterruptedException {

		// Step 1: Click on Vendor (Purchase / Rented From) field
		productsCreate.clickPurchaseRentedFrom();

		// Step 2: Select Vendor from dropdown
		productsCreate.selectVendor("Kaveri Technology");

	}

	@Test(priority = 17, description = "Verify product Rental Rate Monthly  Enter in Field  in Create Product form")
	public void verifyRentalRateMonthlyEntred() {
		productsCreate.setRentalPrice("1400");

	}

	/*
	 * This Field is Removed From Product Create Form
	 * 
	 * @Test(priority = 17, description =
	 * "Verify product with Gst Selction Yes and Enter Gst Amount in Field in Create Product form"
	 * ) public void verifySelectGstYestEnterGstAmount() {
	 * productsCreate.selectWithGSTYes("18");
	 * 
	 * }
	 */

	@Test(priority = 18, description = "Verify Graphics Card selection from dropdown in Create Product form")
	public void verifyProductVGraphicsCardFromDropdown() throws InterruptedException {

		// Step 1: Click on Vendor (Purchase / Rented From) field
		productsCreate.clickGraphicsCard();

		// Step 2: Select Vendor from dropdown
		productsCreate.selectGraphicsCard("4GB AMD");

	}

	@Test(priority = 19, description = "Verify Condition Selction Dropdown  in Create Product form")
	public void verifyConditionDropdown() {

		productsCreate.selectCondition("Refurbished");
	}

	@Test(priority = 20, description = "Verify Barcode is Enter Manuallay in Field  in Create Product form")
	public void verifyBarcodeEnterManually() {
		productsCreate.setBarcode("WS-1234");

	}

	@Test(priority = 21, description = "Verify IMEI No.  is Enter  in Field  in Create Product form")
	public void verifyIMEINo() {
		productsCreate.setIMEINo("234536476476488");

	}

	@Test(priority = 22, description = "Verify Description  text  Enter in Field  in Create Product form")
	public void verifyDescription() throws InterruptedException {
		productsCreate.setDesription("New");
		cmf.clickSaveAndGetToast();
		Thread.sleep(2000);

	}

	@Test(priority = 23, description = "Verify user can cancel product creation without saving", groups = {
			"cancalform" })
	public void verifyCancelProductCreation() {

		// cmf.clickCreateButton();
		// Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(), "Create
		// Product form not opened");

		// Click Cancel
		cmf.formCancalButton();
		// Verify form closed
		Assert.assertFalse(productsCreate.isCreateProductFormDisplayed(),
				"Create Product form should be closed after Cancel");

		// Verify user is still on Products page
		Assert.assertTrue(pListPage.isProductsRecentActivityButtonVisible(),
				"User should remain on Products module after Cancel");
	}

	@Test(priority = 10, description = "Verify barcode is auto-generated when not entered manually")
	public void shouldAutoGenerateBarcodeWhenNotEntered() throws InterruptedException {

		productsCreate.selectProduct("Sony Vaio Laptop");
		productsCreate.selectProductType("laptop");
		productsCreate.setProductSerialNumber("AUTO-BC-08");

		// Do NOT enter barcode

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertTrue(toast.contains("Saved"), "Product not saved successfully");

		// Reopen product (assume method exists)
		pListPage.openLatestProductForEdit();

		String barcodeValue = productsCreate.getBarcodeValue();

		Assert.assertFalse(barcodeValue.isEmpty(), "Barcode was not auto-generated");
		System.out.println("Barcode Number is " + barcodeValue);
	}

	// ================= Products Form FIll All Fields and Click Save Button

	@Test(priority = 24, description = "Verify product can be created with all valid data")
	public void verifyCreateProductWithAllValidData() throws InterruptedException {

		productsCreate.fillProductFormAndSave("Lava Magnum Laptop", "Laptop", "Per Set", "LG 24MP60G", "A2338",
				"5th Gen", "512GB SSD", "8 GB", "win 11", "14.3", "intel core i5", "2025-09-03", "78945", "2026-11-01",
				"Kaveri Technology", "1400", "4GB AMD", "Refurbished", "WS-1234", "234536476476488", "New Laptop");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertEquals(toast, "Saved successfully");
	}

	// ================= Products Form FIll All Fields and Click Save&New Button

	@Test(priority = 24, description = "Verify product can be created with all valid data")
	public void verifySaveAndNewClearsForm() throws InterruptedException {

		productsCreate.fillProductFormAndSave("Lava Magnum Laptop", "Laptop", "Per Set", "LG 24MP60G", "A2338",
				"5th Gen", "512GB SSD", "8 GB", "win 11", "14.3", "intel core i5", "2025-09-03", "78945", "2026-11-01",
				"Kaveri Technology", "1400", "4GB AMD", "Refurbished", "WS-1234", "234536476476488", "New Laptop");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertEquals(toast, "Saved successfully");
	}

	@Test(priority = 30, description = "Verify product can be edited successfully")
	public void verifyEditProduct() {

		pListPage.openLatestProductForEdit();

		productsCreate.setPurchasePrice("99999");
		String toast = cmf.clickSaveAndGetToast();

		Assert.assertEquals(toast, "Updated successfully");
	}

}
