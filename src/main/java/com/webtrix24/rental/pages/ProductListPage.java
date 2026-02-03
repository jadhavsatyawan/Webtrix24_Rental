package com.webtrix24.rental.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;

public class ProductListPage extends BasePage {

	/******* Constructor ***********/

	public ProductListPage(WebDriver driver) {
		super(driver);

	}

	/************** Locators ****************/

	// Product Recent Activity
	@FindBy(xpath = "//button//span[@class='text-md' and text()='Product Recent Activity']")
	WebElement ProductRecentActivity;

	@FindBy(xpath = "//input[contains(@class,'product-search__input')]")
	WebElement productSearchBox;

	@FindBy(xpath = "//div[contains(@class,'product-search__menu-list')]//div[@role='option']")
	List<WebElement> ProdropdownOptions;

	By ProdropdownOptionsList = By.xpath("//div[contains(@class,'product-search__menu-list')]//div[@role='option']");

	@FindBy(xpath = "//button[@class='flex items-center gap-1 px-3 py-1 border rounded-md text-sm border-gray-200 text-indigo-600 hover:bg-indigo-50']")
	WebElement printBarcode;

	/**************** List View ***************/
	/*
	 * @FindBy(xpath =
	 * "//div[contains(@class,'group') and .//button[@title='Edit']]") private
	 * List<WebElement> productRows;
	 */

	// Header row columns (Product Name, Product Type)
	@FindBy(xpath = "//div[contains(@class,'bg-gray-100')]/div[contains(@class,'group')]")
	private List<WebElement> tableHeaders;
	// Each product row

	/*
	 * @FindBy(xpath =
	 * "//div[contains(@class,'flex border-b') and contains(@class,'group')]")
	 * private List<WebElement> productRows;
	 */
	private By productRows = By.xpath("//div[contains(@class,'flex border-b') and contains(@class,'group')]");

//Row wise
	private By duplicateBtnInRow = By.xpath(".//button[@title='Duplicate this product']");

	private By productReportBtnInRow = By.xpath(".//a[@title='Product Report']");
	private By editBtnInRow = By.xpath(".//button[@title='Edit']");

	// Pagination
	private By paginationText = By.xpath("//div[contains(@class,'text-gray-600') and contains(.,'Showing')]");

	private By nextPageBtn = By.xpath("//button[contains(.,'Next')]");
	private By prevPageBtn = By.xpath("//button[contains(.,'Previous')]");

	/************** Action Methods *****************/

	public String getPaginationText() {

		// wait for table rows first (search effect)
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));

		By paginationText = By.xpath("//div[contains(@class,'text-gray-600') and contains(.,'Showing')]");

		WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement pagination = localWait.until(ExpectedConditions.presenceOfElementLocated(paginationText));

		return pagination.getText().trim();
	}

	public boolean isNextPageEnabled() {
		WebElement nextBtn = driver.findElement(nextPageBtn);
		return nextBtn.isEnabled();
	}

	public void clickNextPage() {
		WebElement nextBtn = driver.findElement(nextPageBtn);
		wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
		nextBtn.click();

		// wait till table reload
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));
	}

	public boolean isProductsRecentActivityButtonVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.visibilityOf(ProductRecentActivity)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Search & Select Product By Serial Number in Product Recent Activity
	public void selectProductBySerial(String serialNumber) throws InterruptedException {
		ProductRecentActivity.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Step 1: Clear and Type in search box
		productSearchBox.clear();
		productSearchBox.sendKeys(serialNumber);
		System.out.println("Searching for: " + serialNumber);

		Thread.sleep(1000);
		productSearchBox.sendKeys(Keys.ARROW_DOWN); // Move to first result
		productSearchBox.sendKeys(Keys.ENTER); // Press Enter to select it
	}

	// Random Product Search & Select in Product Recent Activity
	public void selectRandomProduct() {
		try {
			ProductRecentActivity.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Random random = new Random();

			// Step 1️: Click and type a random character to trigger the dropdown list
			productSearchBox.click();
			String randomChar = String.valueOf((char) ('A' + random.nextInt(26))); // Random A–Z letter
			productSearchBox.sendKeys(randomChar);
			System.out.println("Typed random letter: " + randomChar);

			// Step 2️: Wait for dropdown options to appear
			wait.until(ExpectedConditions.visibilityOfAllElements(ProdropdownOptions));

			// Step 3️: Get all available options
			List<WebElement> options = ProdropdownOptions;
			System.out.println("Total options found: " + options.size());

			// Step 4️: Randomly pick one option
			if (!options.isEmpty()) {
				int randomIndex = random.nextInt(options.size());
				WebElement selectedOption = options.get(randomIndex);
				js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
				String selectedText = selectedOption.getText().trim();
				selectedOption.click();
				System.out.println("Randomly Selected Product: " + selectedText);
			} else {
				throw new RuntimeException("No options found in dropdown after typing: " + randomChar);
			}

		} catch (Exception e) {
			System.out.println("Exception in selectRandomProduct: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	public void clickPrintBarcode() {
		printBarcode.click();
	}

	/*************** List View Action Methods **************************/

	private By noRecordsMessage = By.xpath("//div[text()='No matching records']");

	public boolean isNoMatchingRecordsVisible() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// After form cancal and Save Verify
	/*
	 * public boolean isProductListDisplayed() { try { return
	 * !driver.findElements(productRows).isEmpty(); } catch (Exception e) { return
	 * false; } }
	 */
	public boolean isProductListDisplayed() {
		try {
			// table header visible = list page loaded
			return !tableHeaders.isEmpty();
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public boolean isProductPresent(String value) {
	 * 
	 * List<WebElement> rows = driver.findElements(productRows);
	 * 
	 * return rows.stream().anyMatch(row -> row.getText().contains(value)); }
	 */

	public boolean isProductPresent(String value) {

		List<WebElement> rows = driver.findElements(productRows);

		if (rows.isEmpty()) {
			return false; // valid empty search result
		}

		for (WebElement row : rows) {
			try {
				if (row.getText().contains(value)) {
					return true;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				return isProductPresent(value);
			}
		}
		return false;
	}

	public void waitForListToStabilize() {
		// Wait till either rows OR pagination text appears
		wait.until(ExpectedConditions.or(ExpectedConditions.presenceOfElementLocated(productRows),
				ExpectedConditions.presenceOfElementLocated(paginationText)));
	}

	public void hoverOnRow(WebElement row) {
		Actions actions = new Actions(driver);
		actions.moveToElement(row).perform();
	}

	/*
	 * public void openProductBySerialNumber(String serialNumber) {
	 * 
	 * List<WebElement> rows = driver.findElements(productRows);
	 * 
	 * for (WebElement row : rows) { if (row.getText().contains(serialNumber)) {
	 * row.findElement(editBtnInRow).click(); return; } } throw new
	 * RuntimeException("Product with serial not found: " + serialNumber); }
	 */

	public void openProductBySerialNumber(String serialNumber) {

		// ✅ WAIT till table rows are reloaded after search
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));

		List<WebElement> rows = driver.findElements(productRows);

		for (WebElement row : rows) {
			try {
				if (row.getText().contains(serialNumber)) {
					row.findElement(editBtnInRow).click();
					return;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				// DOM refreshed again – retry logic
			}
		}
		throw new RuntimeException("Product with serial not found: " + serialNumber);
	}

	public void openLatestProductForEdit() {

		List<WebElement> rows = driver.findElements(productRows);

		if (rows.isEmpty()) {
			throw new RuntimeException("No products found in list view");
		}

		rows.get(0).findElement(editBtnInRow).click();
	}

	public void searchProduct(String value) {
		productSearchBox.clear();
		productSearchBox.sendKeys(value);
		productSearchBox.sendKeys(Keys.ENTER);
	}

	public void openProductReportOfFirstProduct() {

		List<WebElement> rows = driver.findElements(productRows);

		if (rows.isEmpty()) {
			throw new RuntimeException("No products found in list view");
		}

		WebElement firstRow = rows.get(0);
		hoverOnRow(firstRow);

		firstRow.findElement(productReportBtnInRow).click();
	}

	public void clickDuplicateOnFirstProduct() {

		// Always re-fetch rows
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));

		List<WebElement> rows = driver.findElements(productRows);

		if (rows.isEmpty()) {
			throw new RuntimeException("No products found in list view");
		}

		WebElement firstRow = rows.get(0);

		// Hover (buttons appear only after hover)
		Actions actions = new Actions(driver);
		actions.moveToElement(firstRow).perform();

		// Re-locate duplicate button INSIDE row
		By duplicateBtn = By.xpath(".//button[@title='Duplicate this product']");

		wait.until(ExpectedConditions.elementToBeClickable(firstRow.findElement(duplicateBtn)));
		firstRow.findElement(duplicateBtn).click();
	}

	public void openDuplicateProductBySerial(String serialNumber) {

		List<WebElement> rows = driver.findElements(productRows);

		for (WebElement row : rows) {
			if (row.getText().contains(serialNumber)) {
				hoverOnRow(row);
				row.findElement(duplicateBtnInRow).click();
				return;
			}
		}
		throw new RuntimeException("Product with serial not found: " + serialNumber);
	}

	/*
	 * public void openProductBySerialContains(String serialPartial) {
	 * 
	 * wait.until(ExpectedConditions.visibilityOfAllElements(productRows));
	 * 
	 * for (WebElement row : productRows) { if
	 * (row.getText().contains(serialPartial)) {
	 * 
	 * hoverOnRow(row); row.findElement(editBtnInRow).click(); return; } }
	 * 
	 * throw new RuntimeException("Product with serial containing '" + serialPartial
	 * + "' not found in list"); }
	 */

	public void openProductReportByProductId(String productId) {

		WebElement reportLink = driver.findElement(By.xpath("//a[@href='/product-report/" + productId + "']"));
		reportLink.click();
	}

}
