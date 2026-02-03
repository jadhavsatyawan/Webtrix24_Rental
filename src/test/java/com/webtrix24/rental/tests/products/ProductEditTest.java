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

public class ProductEditTest extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage productsCreate;
	ProductListPage pListPage;
	String savedSerailNumner;

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

	@Test
	public void verifyProductEditFields() throws InterruptedException {

		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible");

		String existingSerial = "MACA45123";
		cmf.search(existingSerial);
		Assert.assertTrue(pListPage.isProductPresent(existingSerial), "Product not visible after search");

		// ================= EDIT 1 : Purchase Price =================
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		productsCreate.setPurchasePrice("65000");
		cmf.clickSaveAndGetToast();

		// ================= EDIT 2 : Rental Price =================
		cmf.search(existingSerial);
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		productsCreate.setRentalPrice("1200");
		cmf.clickSaveAndGetToast();

		// ================= EDIT 3 : IMEI NO =================
		cmf.search(existingSerial);
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		productsCreate.setIMEINo("5635462457874857");
		cmf.clickSaveAndGetToast();

		// ================= EDIT 4 : Product Name =================
		cmf.search(existingSerial);
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		productsCreate.selectGeneration("10th Gen");
		cmf.clickSaveAndGetToast();

		// ================= EDIT 5 : Description =================
		cmf.search(existingSerial);
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		productsCreate.setDesription("Product edited via automation test");
		cmf.clickSaveAndGetToast();
	}

}
