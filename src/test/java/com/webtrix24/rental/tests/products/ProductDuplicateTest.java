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

public class ProductDuplicateTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage productsCreate;
	ProductListPage pListPage;
	String savedSerailNumner;

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

	@Test(priority = 1, description = "Verify duplicate product opens create product form", enabled = false)
	public void verifyDuplicateProductOpensCreateForm() {

		pListPage.clickDuplicateOnFirstProduct();

		Assert.assertTrue(productsCreate.isDuplicateProductFormDisplayed(),
				"Duplicate Product form not opened after clicking Duplicate");
	}

	@Test(priority = 2, description = "Verify product can be duplicated with new serial number and barcode is empty", enabled = false)
	public void verifyDuplicateProductWithNewSerialNumber() {

		// Click duplicate
		pListPage.clickDuplicateOnFirstProduct();

		// Wait till duplicate form fields are visible
		productsCreate.waitForDuplicateFormFields();

		// Serial number should be empty
		String serialValue = productsCreate.getEnteredProductSerialNumber();
		Assert.assertTrue(serialValue.isEmpty(), "Serial number field is not empty for duplicated product");

		// Barcode should be empty
		String barcodeValue = productsCreate.getBarcodeValue();
		Assert.assertTrue(barcodeValue.isEmpty(), "Barcode field is not empty for duplicated product");
		savedSerailNumner = "SLP";
		// Enter NEW UNIQUE serial number
		productsCreate.SetProductSerialNumberManual(savedSerailNumner);

		// Save
		String toast = cmf.clickSaveAndGetToast();

		// Verify success
		Assert.assertEquals(toast, "Saved successfully", "Duplicated product not saved successfully");
	}

	@Test(priority = 3, dependsOnMethods = "verifyDuplicateProductWithNewSerialNumber", description = "Verify duplicated product details are correctly saved and visible in edit form", enabled = false)
	public void verifyDuplicatedProductDetailsInEditForm() {

		// Step 1: Ensure product list is displayed
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list is not displayed");

		// Step 2: Open duplicated product in Edit mode using serial
		pListPage.openProductBySerialNumber(savedSerailNumner);

		// Step 3: Wait for Edit form to load properly
		productsCreate.waitForDuplicateFormFields();

		// Step 4: Verify Serial Number is same as saved
		String actualSerial = productsCreate.getEnteredProductSerialNumber();
		Assert.assertTrue(actualSerial.startsWith(savedSerailNumner),
				"Product serial number not matching. Found: " + actualSerial);

		// Step 5: Verify Barcode is auto-generated (not empty)
		String barcode = productsCreate.getBarcodeValue();
		Assert.assertFalse(barcode.isEmpty(), "Barcode was not auto-generated after saving duplicated product");

		// Optional (recommended UI sanity check)
		Assert.assertTrue(productsCreate.isUpdateProductFormDisplayed(), "Edit Product form is not displayed");
	}

	@Test(priority = 4, description = "Verify barcode is auto-generated when saving duplicated product without barcode", enabled = false)
	public void verifyAutoGenerateBarcodeOnDuplicateSave() {

		// Click duplicate
		pListPage.clickDuplicateOnFirstProduct();

		// Wait till duplicate form fully loads
		productsCreate.waitForDuplicateFormFields();

		// Ensure barcode is empty before save
		Assert.assertTrue(productsCreate.getBarcodeValue().isEmpty(),
				"Barcode field is not empty before saving duplicated product");

		// Enter new unique serial number
		productsCreate.setProductSerialNumber("DUP-AUTO-");

		// Save product
		String toast = cmf.clickSaveAndGetToast();
		Assert.assertEquals(toast, "Saved successfully", "Product not saved successfully");

		// Reopen latest product (or by serial if you want strict check)
		pListPage.openLatestProductForEdit();

		// Verify barcode is auto-generated
		String barcode = productsCreate.getBarcodeValue();
		Assert.assertFalse(barcode.isEmpty(), "Barcode was not auto-generated after save");

		System.out.println("Auto-generated Barcode: " + barcode);
	}

	@Test(priority = 5, description = "Verify duplicated product can be saved with manual barcode", enabled = false)
	public void verifyDuplicateProductWithManualBarcode() {

		// Step 1: Click Duplicate on first product
		pListPage.clickDuplicateOnFirstProduct();

		// Step 2: Wait till duplicate form fields load
		productsCreate.waitForDuplicateFormFields();

		// Step 3: Enter NEW serial number (manual)
		String manualSerial = "DUP-MAN-001";
		productsCreate.SetProductSerialNumberManual(manualSerial);

		// Step 4: Enter MANUAL barcode
		String manualBarcode = "BAR-998877";
		productsCreate.setBarcode(manualBarcode);

		// Step 5: Save product
		String toast = cmf.clickSaveAndGetToast();

		// Step 6: Verify save success
		Assert.assertEquals(toast, "Saved successfully", "Product not saved successfully with manual barcode");
	}

	@Test(priority = 6, description = "Verify clicking Cancel on duplicate product closes form and returns to list view", enabled = false)
	public void verifyDuplicateProductCancelButton() {

		// Step 1: Click Duplicate on first product
		pListPage.clickDuplicateOnFirstProduct();

		// Step 2: Wait till duplicate form loads
		productsCreate.waitForDuplicateFormFields();

		// Step 3: Click Cancel button
		cmf.formCancalButton();

		// Step 4: Verify product list is displayed again
		Assert.assertTrue(pListPage.isProductListDisplayed(),
				"Product list not displayed after clicking Cancel on duplicate form");
	}

	/*
	 * @Test(priority = 7, description =
	 * "Verify duplicate product save, edit unit and verify persistence") public
	 * void verifyDuplicateSaveEditAndVerify() throws InterruptedException {
	 * 
	 * // STEP 1 Assert.assertTrue(pListPage.isProductListDisplayed(),
	 * "Product list is not displayed");
	 * 
	 * // STEP 2 pListPage.clickDuplicateOnFirstProduct();
	 * 
	 * // STEP 3 productsCreate.waitForDuplicateFormFields();
	 * 
	 * // STEP 4 String savedSerialNumber = "LPM-EDIT-06";
	 * productsCreate.SetProductSerialNumberManual(savedSerialNumber);
	 * 
	 * // STEP 5 String toastAfterDuplicateSave = cmf.clickSaveAndGetToast();
	 * Assert.assertEquals(toastAfterDuplicateSave, "Saved successfully");
	 * 
	 * // STEP 6 Assert.assertTrue(pListPage.isProductPresent(savedSerialNumber));
	 * 
	 * // STEP 7 pListPage.openProductBySerialNumber(savedSerialNumber);
	 * 
	 * // STEP 8 productsCreate.waitForDuplicateFormFields();
	 * 
	 * // STEP 9 (FIXED – THIS IS THE MAIN POINT) String updatedUnit = "One";
	 * 
	 * productsCreate.clickProductUnitField(); // ensure visible + focus
	 * productsCreate.selectProductUnit(updatedUnit); // clear + type + select
	 * (DropdownUtil handles it)
	 * 
	 * // STEP 10 String toastAfterEditSave = cmf.clickSaveAndGetToast();
	 * Assert.assertEquals(toastAfterEditSave, "Saved successfully");
	 * 
	 * // STEP 11 pListPage.openProductBySerialNumber(savedSerialNumber);
	 * productsCreate.waitForDuplicateFormFields();
	 * 
	 * // STEP 12 String actualUnit = productsCreate.getSelectedunit();
	 * Assert.assertEquals(actualUnit, updatedUnit); }
	 */

}
