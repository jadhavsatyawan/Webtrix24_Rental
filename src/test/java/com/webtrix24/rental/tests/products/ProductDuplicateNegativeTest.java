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

public class ProductDuplicateNegativeTest extends BaseClass {

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

	@Test(priority = 1, description = "Verify error message when saving duplicated product without serial number", enabled = false)
	public void verifyDuplicateSaveWithoutSerialNumber() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list is not displayed");

		// STEP 2: Click Duplicate
		pListPage.clickDuplicateOnFirstProduct();

		// STEP 3: Wait for duplicate form
		productsCreate.waitForDuplicateFormFields();

		// STEP 4: DO NOT enter serial number

		// STEP 5: Click Save (NO toast expected)
		cmf.ClickSaveButton();

		// STEP 6: Verify field-level validation message
		String serialError = productsCreate.getProducSerailNumberValidationMessage();

		Assert.assertEquals(serialError, "Product Serial No is required.",
				"Serial number required validation message not displayed");

		// STEP 7: Form should still be open
		Assert.assertTrue(productsCreate.isCreateProductFormDisplayed(),
				"Form should remain open after validation error");
	}

	@Test(priority = 2, description = "Verify error when duplicating product with existing serial number")
	public void verifyDuplicateWithExistingSerialNumber() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list is not displayed");

		// STEP 2: Click Duplicate on any product
		pListPage.clickDuplicateOnFirstProduct();

		// STEP 3: Wait for duplicate form fields
		productsCreate.waitForDuplicateFormFields();

		// STEP 4: Enter EXISTING serial number (already present in system)
		String existingSerialNumber = "LPM-EDIT-04"; // <-- already saved product
		productsCreate.SetProductSerialNumberManual(existingSerialNumber);

		// STEP 5: Click Save
		String toastString = cmf.clickSaveAndGetToast();

		Assert.assertEquals(toastString, "Error: Product Serial No Already Exists!",
				"Duplicate serial number validation message not displayed");

		// STEP 7: Form should remain open
		Assert.assertTrue(productsCreate.isDuplicateProductFormDisplayed(),
				"Form should remain open after duplicate serial error");
	}

}
