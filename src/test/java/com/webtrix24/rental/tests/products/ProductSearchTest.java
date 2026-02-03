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

public class ProductSearchTest extends BaseClass {

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

	@Test(priority = 1, description = "Verify Search Product By Name")
	public void verifySearchByProductNameAndPagination() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Product Name
		String productName = "macbook pro m4";
		cmf.search(productName);

		// STEP 4: Verify product rows present after search
		Assert.assertTrue(pListPage.isProductPresent(productName), "No product found for search value: " + productName);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination text should change
		Assert.assertNotEquals(afterSearchText, beforeSearchText, "Pagination count did not change after search");

		// STEP 7: Verify pagination works with search applied
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			// Just confirm list is still loaded after page change
			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Optional: verify still filtered
			Assert.assertTrue(pListPage.isProductPresent(productName), "Search filter lost after pagination");

			// STEP 8: Capture pagination AFTER search
			String afterClickNext = pListPage.getPaginationText();
			System.out.println("After Click Next Page: " + afterClickNext);

			// STEP 9: Pagination text should change
			Assert.assertNotEquals(afterSearchText, afterClickNext, "Pagination count did not change after search");

		}
	}

	@Test(priority = 2, description = "Verify Search Product By Type")
	public void verifySearchByProductTypeAndPagination() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Product Type
		String Type = "Laptop";
		cmf.search(Type);

		// STEP 4: Verify product rows present after search
		Assert.assertTrue(pListPage.isProductPresent(Type), "No product found for search value: " + Type);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination text should change
		Assert.assertNotEquals(afterSearchText, beforeSearchText, "Pagination count did not change after search");

		// STEP 7: Verify pagination works with search applied
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			// Just confirm list is still loaded after page change
			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Optional: verify still filtered
			Assert.assertTrue(pListPage.isProductPresent(Type), "Search filter lost after pagination");

			// STEP 8: Capture pagination AFTER search
			String afterClickNext = pListPage.getPaginationText();
			System.out.println("After Click Next Page: " + afterClickNext);

			// STEP 9: Pagination text should change
			Assert.assertNotEquals(afterSearchText, afterClickNext, "Pagination count did not change after search");

		}
	}

	@Test(priority = 3, description = "Verify Search Product by Unit ")
	public void verifySearchProductByUnit() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Unit
		String Unit = "one";
		cmf.search(Unit);

		// STEP 4: Verify product with Unit is present
		Assert.assertTrue(pListPage.isProductPresent(Unit), "Product not found for Unit search: " + Unit);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText, "Pagination count did not change after Unit search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(Unit), "Unit  filter lost after pagination");
		}
	}

	@Test(priority = 4, description = "Verify Search Product by Searial number")
	public void verifySearchBySerialNumber() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Serial Number
		String serialNumber = "MACA45123"; // ✅ existing serial
		cmf.search(serialNumber);

		// STEP 4: Verify product with serial is present
		Assert.assertTrue(pListPage.isProductPresent(serialNumber),
				"Product not found for Serial Number search: " + serialNumber);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Serial Number search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(serialNumber), "Serial number filter lost after pagination");
		}
	}

	@Test(priority = 5, description = "Verify Search Product by Model Name ")
	public void verifySearchProductByModelName() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Model Name
		String ModelName = "VivoBook 14";
		cmf.search(ModelName);

		// STEP 4: Verify product with Model Name is present
		Assert.assertTrue(pListPage.isProductPresent(ModelName),
				"Product not found for Model Name  search: " + ModelName);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Model Name search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(ModelName), "Model Name filter lost after pagination");
		}
	}

	@Test(priority = 6, description = "Verify Search Product by Model Number ")
	public void verifySearchProductByModelNumber() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Model Number
		String ModelNumber = "MBA2023";
		cmf.search(ModelNumber);

		// STEP 4: Verify product with Model Number is present
		Assert.assertTrue(pListPage.isProductPresent(ModelNumber),
				"Product not found for Model Number  search: " + ModelNumber);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after  Model Number search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(ModelNumber), " Model Number filter lost after pagination");
		}
	}

	@Test(priority = 7, description = "Verify Search Product by Generation ")
	public void verifySearchProductByGeneration() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Model Number
		String Generation = "8th Gen";
		cmf.search(Generation);

		// STEP 4: Verify product with Generation is present
		Assert.assertTrue(pListPage.isProductPresent(Generation),
				"Product not found for Generation search: " + Generation);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Generation search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(Generation), "Generation filter lost after pagination");
		}
	}

	@Test(priority = 8, description = "Verify Search Product by HDD/SSD Capacity ")
	public void verifySearchProductByHDD_SSDCapacity() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by HDD/SSD Capacity
		String HDDSSDCapacity = "2TB HDD";
		cmf.search(HDDSSDCapacity);

		// STEP 4: Verify product with HDD_SSD Capacity is present
		Assert.assertTrue(pListPage.isProductPresent(HDDSSDCapacity),
				"Product not found for HDD_SSD Capacity search: " + HDDSSDCapacity);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after HDD_SSD Capacity search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(HDDSSDCapacity),
					"HDD_SSD Capacity filter lost after pagination");
		}
	}

	@Test(priority = 9, description = "Verify Search Product by Memory")
	public void verifySearchProductByMemory() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Memory
		String Memory = "32 GB";
		cmf.search(Memory);

		// STEP 4: Verify product with Memory is present
		Assert.assertTrue(pListPage.isProductPresent(Memory), "Product not found for Memory search: " + Memory);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Memory search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(Memory), "Memory filter lost after pagination");
		}
	}

	@Test(priority = 10, description = "Verify Search Product by Operating System")
	public void verifySearchProductByOperatingSystem() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Operating System
		String OperatingSystem = "Windows 11 Home";
		cmf.search(OperatingSystem);

		// STEP 4: Verify product with Operating System is present
		Assert.assertTrue(pListPage.isProductPresent(OperatingSystem),
				"Product not found for Operating System search: " + OperatingSystem);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after OperatingS ystem search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(OperatingSystem),
					"Operating System filter lost after pagination");
		}
	}

	@Test(priority = 11, description = "Verify Search Product by Screen Size")
	public void verifySearchProductByScreenSize() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Screen Size
		String ScreenSize = "14.4\"";
		cmf.search(ScreenSize);

		// STEP 4: Verify product with Screen Size is present
		Assert.assertTrue(pListPage.isProductPresent(ScreenSize),
				"Product not found for Screen Size search: " + ScreenSize);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Screen Size search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(ScreenSize), "Screen Size filter lost after pagination");
		}
	}

	@Test(priority = 12, description = "Verify Search Product by Processor")
	public void verifySearchProductByProcessor() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Processor
		String Processor = "intel core i5";
		cmf.search(Processor);

		// STEP 4: Verify product with Processor is present
		Assert.assertTrue(pListPage.isProductPresent(Processor),
				"Product not found for Processor search: " + Processor);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Processor search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(Processor), "Processor filter lost after pagination");
		}
	}

	@Test(priority = 13, description = "Verify Search Product by Purchase Date")
	public void verifySearchProductByPurchaseDate() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Purchase Date
		String PurchaseDate = "2026-01-01";
		cmf.search(PurchaseDate);

		// STEP 4: Verify product with Purchase Date is present
		Assert.assertTrue(pListPage.isProductPresent(PurchaseDate),
				"Product not found for Purchase Date search: " + PurchaseDate);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Purchase Date search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(PurchaseDate), "Purchase Date filter lost after pagination");
		}
	}

	@Test(priority = 14, description = "Verify Search Product by Purchase Price")
	public void verifySearchProductByPurchasePrice() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Purchase Date
		String PurchasePrice = "55000";
		cmf.search(PurchasePrice);

		// STEP 4: Verify product with Purchase Price is present
		Assert.assertTrue(pListPage.isProductPresent(PurchasePrice),
				"Product not found for Purchase Price search: " + PurchasePrice);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Purchase Price search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(PurchasePrice), "Purchase Price filter lost after pagination");
		}
	}

	@Test(priority = 15, description = "Verify Search Product by Warranty Up To")
	public void verifySearchProductByWarrantyUpTo() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Warranty Up To
		String WarrantyUpTo = "2026-01-01";
		cmf.search(WarrantyUpTo);

		// STEP 4: Verify product with Warranty Up To is present
		Assert.assertTrue(pListPage.isProductPresent(WarrantyUpTo),
				"Product not found for Warranty Up To search: " + WarrantyUpTo);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Warranty Up To search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(WarrantyUpTo), "Warranty Up To filter lost after pagination");
		}
	}

	@Test(priority = 16, description = "Verify Search Product by Purchase_Rented From")
	public void verifySearchProductByPurchaseRentedFrom() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Purchase Rented From

		String purchaseFrom = "Om Sai Rental Services";
		cmf.search(purchaseFrom);

		// STEP 4: Verify product with purchase From To is present
		Assert.assertTrue(pListPage.isProductPresent(purchaseFrom),
				"Product not found for purchase From To search: " + purchaseFrom);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after purchase From Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(purchaseFrom), "purchase From filter lost after pagination");
		}
	}

	@Test(priority = 17, description = "Verify Search Product by Rental Rate Monthly")
	public void verifySearchProductByRentalRateMonthly() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Rental Rate Monthly
		String RentalRate = "1200";
		cmf.search(RentalRate);

		// STEP 4: Verify product with Rental Rate is present
		Assert.assertTrue(pListPage.isProductPresent(RentalRate),
				"Product not found for Rental Rate search: " + RentalRate);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Rental Rate Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(RentalRate), "Rental Rate filter lost after pagination");
		}
	}

	@Test(priority = 18, description = "Verify Search Product by Graphics Card")
	public void verifySearchProductByGraphicsCard() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Graphics Card
		String GraphicsCard = "4GB AMD";
		cmf.search(GraphicsCard);

		// STEP 4: Verify product with Graphics Card is present
		Assert.assertTrue(pListPage.isProductPresent(GraphicsCard),
				"Product not found for Graphics Card search: " + GraphicsCard);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Graphics Card Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(GraphicsCard), "Graphics Card filter lost after pagination");
		}
	}

	@Test(priority = 19, description = "Verify Search Product by Condition")
	public void verifySearchProductByCondition() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Condition
		String Condition = "New";
		cmf.search(Condition);

		// STEP 4: Verify product with Condition is present
		Assert.assertTrue(pListPage.isProductPresent(Condition),
				"Product not found for Condition search: " + Condition);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after Condition Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(Condition), "Condition filter lost after pagination");
		}
	}

	@Test(priority = 20, description = "Verify Search Product by Barcode")
	public void verifySearchProductByBarcode() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by Barcode
		String barcode = "WST-1";
		cmf.search(barcode);

		// STEP 4: Verify product with barcode is present
		Assert.assertTrue(pListPage.isProductPresent(barcode), "Product not found for barcode search: " + barcode);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after barcode Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(barcode), "barcode filter lost after pagination");
		}
	}

	@Test(priority = 21, description = "Verify Search Product by  IMEI No.")
	public void verifySearchProductByIMEINo() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by IMEI No.
		String iMEINo = "675678903456123";
		cmf.search(iMEINo);

		// STEP 4: Verify product with iMEINo is present
		Assert.assertTrue(pListPage.isProductPresent(iMEINo), "Product not found for iMEINo search: " + iMEINo);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after iMEINo Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(iMEINo), "barcode filter lost after pagination");
		}
	}

	@Test(priority = 22, description = "Verify Search Product by  Description.")
	public void verifySearchProductByDescription() {

		// STEP 1: Product list visible
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not displayed");

		// STEP 2: Capture pagination BEFORE search
		String beforeSearchText = pListPage.getPaginationText();
		System.out.println("Before Search: " + beforeSearchText);

		// STEP 3: Search by IMEI No.
		String description = "good laptop";
		cmf.search(description);

		// STEP 4: Verify product with description is present
		Assert.assertTrue(pListPage.isProductPresent(description),
				"Product not found for description search: " + description);

		// STEP 5: Capture pagination AFTER search
		String afterSearchText = pListPage.getPaginationText();
		System.out.println("After Search: " + afterSearchText);

		// STEP 6: Pagination should reflect filtered result
		Assert.assertNotEquals(afterSearchText, beforeSearchText,
				"Pagination count did not change after description Search");

		// STEP 7: Pagination + search consistency
		if (pListPage.isNextPageEnabled()) {

			pListPage.clickNextPage();

			Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible after clicking Next page");

			// Serial search should still be applied
			Assert.assertTrue(pListPage.isProductPresent(description), "description filter lost after pagination");
		}
	}

	// Search and Edit

	@Test(priority = 1, description = "Verify model name can be updated using search and edit", enabled = false)
	public void verifyUpdateModelNameViaSearch() throws InterruptedException {

		// STEP 1
		Assert.assertTrue(pListPage.isProductListDisplayed(), "Product list not visible");

		// STEP 2
		String existingSerial = "MACA45123";
		cmf.search(existingSerial);

		Assert.assertTrue(pListPage.isProductPresent(existingSerial), "Product not visible after search");

		// STEP 3
		pListPage.openProductBySerialNumber(existingSerial);

		// STEP 4
		productsCreate.waitForDuplicateFormFields();

		// STEP 5
		String updatedModelName = "MacBook Pro"; // AIR M1
		productsCreate.selectModelName(updatedModelName);

		// STEP 6
		String toast = cmf.clickSaveAndGetToast();
		Assert.assertEquals(toast, "Saved successfully");

		// STEP 7
		cmf.search(existingSerial);
		pListPage.openProductBySerialNumber(existingSerial);
		productsCreate.waitForDuplicateFormFields();

		// STEP 8
		String actualModelName = productsCreate.getEnteredProductModelNameString();
		Assert.assertEquals(actualModelName, updatedModelName);
	}
}