package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

public class ProductCreateNegativeTest extends BaseClass {

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

	@AfterMethod
	public void closeForm() {
		try {
			cmf.formCancalButton();
		} catch (Exception e) {
			// ignore
		}
	}

	@Test(priority = 1, description = "Verify error when Product Name is not entered")
	public void shouldShowErrorWhenProductNameIsMissing() throws InterruptedException {

		// Step 1: Do NOT enter product name

		productsCreate.selectProductType("Desktop");
		productsCreate.SetProductSerialNumberManual("DNVERT345");

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message
		String errorMsg = productsCreate.getProductNameValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("required"),
				"Product Name required validation not displayed");
	}

	@Test(priority = 2, description = "Verify error when Product Type is not entered")
	public void shouldShowErrorWhenProductTYpeIsMissing() throws InterruptedException {

		productsCreate.selectProduct("Dell Inspiron 15");
		productsCreate.SetProductSerialNumberManual("dtype1234");
		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message
		String errorMsg = productsCreate.getProducTypeValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("required"),
				"Product Type required validation not displayed");
	}

	@Test(priority = 3, description = "Verify error when Product Serail Number is not entered")
	public void shouldShowErrorWhenProductSeraialNumberIsMissing() throws InterruptedException {

		productsCreate.selectProduct("Asus");
		productsCreate.selectProductType("Desktop");

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify validation message
		String errorMsg = productsCreate.getProducSerailNumberValidationMessage();

		Assert.assertTrue(errorMsg.toLowerCase().contains("required"),
				"Product Serial Number required validation not displayed");
	}

	@Test(priority = 4, description = "Verify all required field validations when form is submitted empty")
	public void shouldShowAllRequiredErrorsWhenFormIsEmpty() {

		// Step 1: Do NOT fill any field

		// Step 2: Click Save
		cmf.ClickSaveButton();

		// Step 3: Verify all required error messages
		Assert.assertTrue(productsCreate.getProductNameValidationMessage().contains("required"),
				"Product Name required error not shown");

		Assert.assertTrue(productsCreate.getProducTypeValidationMessage().contains("required"),
				"Product Type required error not shown");

		Assert.assertTrue(productsCreate.getProducSerailNumberValidationMessage().contains("required"),
				"Product Serial Number required error not shown");
	}

	@Test(priority = 5, description = "Verify error when existing Product Serial Number is reused")
	public void shouldShowErrorForExistingProductSerialNumber() throws InterruptedException {

		productsCreate.selectProduct("Lenovo ThinkPad E14");
		productsCreate.selectProductType("Desktop");
		productsCreate.SetProductSerialNumberManual("LP-DEYJV");

		String toast = cmf.clickSaveAndGetToast();
		Assert.assertEquals(toast, "Error: Product Serial No Already Exists!",
				"Duplicate Product Serial Number error not displayed");
	}

	@Test(priority = 29, description = "Verify error on duplicate barcode")
	public void verifyDuplicateBarcodeError() throws InterruptedException {

		productsCreate.selectProduct("Laptop");
		productsCreate.selectProductType("Laptop");
		productsCreate.SetProductSerialNumberManual("BC-NEW-1");
		productsCreate.setBarcode("WST-1");

		String toast = cmf.clickSaveAndGetToast();

		Assert.assertTrue(toast.toLowerCase().contains("barcode"), "Duplicate barcode error not shown");
	}

}
