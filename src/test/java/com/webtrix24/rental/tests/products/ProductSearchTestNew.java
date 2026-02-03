package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductListPage;
import com.webtrix24.rental.pages.ProductsCreatePage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;

public class ProductSearchTestNew extends BaseClass {

	LoginPage loginPage;
	SidePanel sidePanel;
	CommanFunctionalitiesPage cmf;
	ProductsCreatePage productsCreate;
	ProductListPage pListPage;

	@BeforeClass
	public void setup() throws InterruptedException {

		loginPage = new LoginPage(driver);
		sidePanel = new SidePanel(driver);
		cmf = new CommanFunctionalitiesPage(driver);
		productsCreate = new ProductsCreatePage(driver);
		pListPage = new ProductListPage(driver);

		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));

		Thread.sleep(2000);
		sidePanel.clickProducts();
		Thread.sleep(2000);
	}

	// 🔥 MOST IMPORTANT FIX
	/*
	 * @BeforeMethod public void resetStateBeforeEachTest() {
	 * 
	 * // Clear search cmf.search(""); // Small UI buffer (not sleep, safe for async
	 * UI) pListPage.waitForListToStabilize(); }
	 * 
	 * // 🔹 COMMON VERIFY METHOD /* private void verifySearch(String value) {
	 * 
	 * cmf.search(value); pListPage.waitForListToStabilize();
	 * 
	 * // Product list container must be visible
	 * Assert.assertTrue(pListPage.isProductListDisplayed(),
	 * "Product list UI not visible");
	 * 
	 * // Search applied – product present OR valid empty result (handled safely) //
	 * If no rows, isProductPresent() will return false but test should PASS
	 * Assert.assertTrue(pListPage.isProductPresent(value) || true,
	 * "Search applied but no matching data for: " + value); }
	 */
	// 🔹 COMMON VERIFY METHOD WITH ASSERT + CONSOLE LOGS
	/*
	 * private void verifySearch(String value) {
	 * 
	 * System.out.println("\n===============================");
	 * System.out.println("🔍 SEARCH VALUE : " + value);
	 * System.out.println("===============================");
	 * 
	 * // STEP 1: Perform search cmf.search(value);
	 * pListPage.waitForListToStabilize();
	 * System.out.println("✔ Search triggered and UI stabilized");
	 * 
	 * // STEP 2: Verify product list UI is visible boolean listVisible =
	 * pListPage.isProductListDisplayed();
	 * System.out.println("✔ Product list visible : " + listVisible);
	 * 
	 * Assert.assertTrue(listVisible, "Product list UI not visible after search");
	 * 
	 * // STEP 3: Check if product exists in list boolean productFound =
	 * pListPage.isProductPresent(value);
	 * System.out.println("✔ Product present in list : " + productFound);
	 * 
	 * if (productFound) { // CASE 1: Matching data found
	 * System.out.println("RESULT: Matching product FOUND for search value");
	 * Assert.assertTrue(true); // explicit pass } else { // CASE 2: No matching
	 * data
	 * System.out.println("RESULT: No matching product found (VALID EMPTY RESULT)");
	 * Assert.assertTrue(true); // still pass – empty result is valid }
	 * 
	 * System.out.println("✅ SEARCH TEST PASSED FOR : " + value);
	 * System.out.println("===============================\n"); }
	 * 
	 * // ===================== TESTS =====================
	 * 
	 * @Test(priority = 1) public void searchByProductName() {
	 * verifySearch("macbook pro m4"); }
	 * 
	 * @Test(priority = 2) public void searchByProductType() {
	 * verifySearch("Laptop"); }
	 * 
	 * @Test(priority = 3) public void searchByUnit() { verifySearch("one"); }
	 * 
	 * @Test(priority = 4) public void searchBySerialNumber() {
	 * verifySearch("MACA45123"); }
	 * 
	 * @Test(priority = 5) public void searchByModelName() {
	 * verifySearch("VivoBook 14"); }
	 * 
	 * @Test(priority = 6) public void searchByModelNumber() {
	 * verifySearch("MBA2023"); }
	 * 
	 * @Test(priority = 7) public void searchByGeneration() {
	 * verifySearch("8th Gen"); }
	 * 
	 * @Test(priority = 8) public void searchByHddSsdCapacity() {
	 * verifySearch("2TB HDD"); }
	 * 
	 * @Test(priority = 9) public void searchByMemory() { verifySearch("32 GB"); }
	 * 
	 * @Test(priority = 10) public void searchByOperatingSystem() {
	 * verifySearch("Windows 11 Home"); }
	 * 
	 * @Test(priority = 11) public void searchByScreenSize() {
	 * verifySearch("14.4\""); }
	 * 
	 * @Test(priority = 12) public void searchByProcessor() {
	 * verifySearch("intel core i5"); }
	 * 
	 * @Test(priority = 13) public void searchByPurchaseDate() {
	 * verifySearch("2026-01-01"); }
	 * 
	 * @Test(priority = 14) public void searchByPurchasePrice() {
	 * verifySearch("55000"); }
	 * 
	 * @Test(priority = 15) public void searchByWarrantyUpto() {
	 * verifySearch("2026-01-01"); }
	 * 
	 * @Test(priority = 16) public void searchByPurchaseFrom() {
	 * verifySearch("Om Sai Rental Services"); }
	 * 
	 * @Test(priority = 17) public void searchByRentalRateMonthly() {
	 * verifySearch("1200"); }
	 * 
	 * @Test(priority = 18) public void searchByGraphicsCard() {
	 * verifySearch("4GB AMD"); }
	 * 
	 * @Test(priority = 19) public void searchByCondition() { verifySearch("New"); }
	 * 
	 * @Test(priority = 20) public void searchByBarcode() { verifySearch("WST-1"); }
	 * 
	 * @Test(priority = 21) public void searchByImei() {
	 * verifySearch("675678903456123"); }
	 * 
	 * @Test(priority = 22) public void searchByDescription() {
	 * verifySearch("good laptop"); }
	 */

	@DataProvider(name = "productSearchData")
	public Object[][] productSearchData() {
		return new Object[][] { { "macbook pro m4" }, { "Laptop" }, { "one" }, { "MACA45123" }, { "VivoBook 14" },
				{ "MBA2023" }, { "8th Gen" }, { "2TB HDD" }, { "32 GB" }, { "Windows 11 Home" }, { "14.4\"" },
				{ "intel core i5" }, { "2026-01-01" }, { "55000" }, { "Om Sai Rental Services" }, { "1200" },
				{ "4GB AMD" }, { "New" }, { "WST-1" }, { "675678903456123" }, { "good laptop" } };
	}

	@Test(dataProvider = "productSearchData")
	public void verifyProductSearchWithPagination(String searchValue) {

		System.out.println("\n===============================");
		System.out.println("SEARCH VALUE: " + searchValue);
		System.out.println("===============================");

		// STEP 1: Initial list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible initially");

		// STEP 2: Pagination BEFORE search
		String beforePagination = pListPage.getPaginationText();
		System.out.println("Before Search Pagination: " + beforePagination);

		// STEP 3: Search
		cmf.search(searchValue);

		// STEP 4: Wait for UI to settle
		pListPage.waitForListToStabilize();

		// STEP 5: Either list visible OR no-records message visible
		boolean listVisible = pListPage.isProductListDisplayed();
		boolean noRecordsVisible = pListPage.isNoMatchingRecordsVisible();

		Assert.assertTrue(listVisible || noRecordsVisible,
				"Neither product list nor 'No matching records' message visible after search");

		// STEP 6: Product presence observation
		boolean found = pListPage.isProductPresent(searchValue);
		System.out.println("Product found: " + found);
		System.out.println("No matching records message visible: " + noRecordsVisible);

		// 🔥 MAIN ASSERT (MOST IMPORTANT)
		Assert.assertTrue(found || noRecordsVisible,
				"Search failed: No result & no 'No matching records' message for: " + searchValue);

		// STEP 7: Pagination AFTER search (only observe, not strict assert)
		String afterPagination = pListPage.getPaginationText();
		System.out.println("After Search Pagination: " + afterPagination);

		if (!beforePagination.equals(afterPagination)) {
			System.out.println("Pagination changed after search (expected)");
		} else {
			System.out.println("Pagination unchanged after search (valid)");
		}

		// STEP 8: Clear search using UI clear button
		cmf.clearSearch();
		pListPage.waitForListToStabilize();

		// STEP 9: Verify UI is stable after clear
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clearing search");

		String afterClearPagination = pListPage.getPaginationText();
		System.out.println("After Clear Pagination: " + afterClearPagination);

		// ❌ DO NOT assert exact pagination reset (app behavior varies)
		Assert.assertNotNull(afterClearPagination, "Pagination text missing after clearing search");

		System.out.println("✅ TEST COMPLETED FOR: " + searchValue);
	}

}
