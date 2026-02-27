package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.ToastUtil;

public class CommanFunctionalitiesPage extends BasePage {
	ToastUtil toastUtil; // ✅ Declare

	/************* Constructor ***************/

	public CommanFunctionalitiesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		toastUtil = new ToastUtil(driver); // ✅ Initialize once
	}

	/******************* Locators *******************************/

	@FindBy(xpath = "//button[text()='Create']")
	WebElement createBtn;

	@FindBy(xpath = "//input[@placeholder='Search...' or @type='text']")
	WebElement searchBox;

	@FindBy(xpath = "//button[@aria-label='Clear search']")
	WebElement clearsearchBox;

	@FindBy(xpath = "//button[@class='p-2 bg-primary text-white rounded']")
	WebElement savedFilter;

	@FindBy(xpath = "//button[@class='p-2 ml-2 bg-gray-100 rounded-md hover:bg-gray-200']")
	WebElement refreshIcon;

	@FindBy(xpath = "//button[@class='p-2 bg-gray-100 rounded']")
	WebElement filters;

	@FindBy(xpath = "//button[@title='Export PDF']")
	WebElement exportPDF;

	@FindBy(xpath = "//button[@title='Export Excel']")
	WebElement exportExcel;

	@FindBy(xpath = "//button[text()='Refresh List']")
	WebElement refreshDropdownList;

	@FindBy(xpath = "//button[contains(text(),'+ Add New')]")
	WebElement AddNewDropdown;

	@FindBy(xpath = "//button[@class='bg-blue-600 text-white px-4 py-1 rounded disabled:opacity-50 disabled:cursor-not-allowed' and text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//button[@class='border border-gray-300 px-4 py-1 rounded disabled:opacity-50 disabled:cursor-not-allowed' and text()='Save & New']")
	WebElement saveNewBtn;

	@FindBy(xpath = "//button[@class='absolute right-3 top-3 z-20 rounded-full p-1 text-gray-500 hover:bg-gray-100 hover:text-gray-800']")
	WebElement cancalAddNewBtn;

	@FindBy(xpath = "//button[@class='absolute -left-3 rounded-full bg-white w-[28px] h-[28px] shadow-lg flex items-center justify-center']")
	WebElement formCancalIcon;

	@FindBy(xpath = "//button[@title='Manage Columns']")
	WebElement manageColumns;

	/*************************** Action Methods ***********************************/

	public void clickCreateButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait until previous toast/loader disappears
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
				"//div[contains(@class,'toast') or contains(@class,'notification') or contains(@class,'loading')]")));

		WebElement createButton = driver
				.findElement(By.xpath("//button[contains(.,'Create') or contains(.,'+ Create')]"));

		wait.until(ExpectedConditions.elementToBeClickable(createButton));

		// Scroll to button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");

		// Try normal click
		try {
			createButton.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			System.out.println("Normal click intercepted, performing JS click instead.");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
		}
	}

	public void ClickSaveButton() {
		saveBtn.click();
	}

	// Click save and return toast message
	public String clickSaveAndGetToast() {
		saveBtn.click();

		String message = toastUtil.captureToastMessage(); // wait till visible

		toastUtil.waitForToastToDisappear(); // 🔥 NEW (important)
		toastUtil.waitForUIStability(); // 🔥 small buffer

		return message;
	}

	public String clickSaveNewAndGetToast() {
		saveNewBtn.click();
		return toastUtil.captureToastMessage(); // ✅ Use reusable util here
	}

	public void formCancalButton() {
		formCancalIcon.click();

	}

	public void clickrefreshDropdown() {
		refreshDropdownList.click();

	}

	/************** Export PDF **********************/

	// Only Specific Module Export PDF Open In New tab and Switch Back To Parent Tab

	/*
	 * public boolean clickExportPDF() {
	 * 
	 * // Step 0: Wait till Export PDF button clickable
	 * wait.until(ExpectedConditions.elementToBeClickable(exportPDF));
	 * 
	 * String parentWindow = driver.getWindowHandle(); int parentTabCount =
	 * driver.getWindowHandles().size();
	 * 
	 * // Step 1: Click Export PDF exportPDF.click();
	 * 
	 * // Step 2: Wait for new tab wait.until(d -> d.getWindowHandles().size() >
	 * parentTabCount);
	 * 
	 * // Step 3: Switch to PDF tab String pdfWindow = null; for (String win :
	 * driver.getWindowHandles()) { if (!win.equals(parentWindow)) { pdfWindow =
	 * win; break; } }
	 * 
	 * driver.switchTo().window(pdfWindow);
	 * 
	 * // Step 4: Verify PDF opened (URL based – reliable) WebDriverWait pdfWait =
	 * new WebDriverWait(driver, Duration.ofSeconds(10));
	 * pdfWait.until(ExpectedConditions.urlContains("/customer/download"));
	 * 
	 * boolean isPdfOpened = driver.getCurrentUrl().contains("/customer/download");
	 * 
	 * // Optional visual hold try { Thread.sleep(9000); } catch
	 * (InterruptedException e) { }
	 * 
	 * // Step 5: Close PDF tab driver.close();
	 * 
	 * // Step 6: Switch back to parent window
	 * driver.switchTo().window(parentWindow);
	 * 
	 * // Step 7: Confirm we are back on Customers page
	 * wait.until(ExpectedConditions.urlContains("/customer"));
	 * 
	 * return isPdfOpened; }
	 */

	// Reusable Method For All Modules Export PDF Open In New tab and Switch Back To
	// Parent Tab
	public boolean clickExportPDF(String expectedUrlPart) {

		wait.until(ExpectedConditions.elementToBeClickable(exportPDF));

		String parentWindow = driver.getWindowHandle();
		int tabsBefore = driver.getWindowHandles().size();

		exportPDF.click();

		wait.until(d -> d.getWindowHandles().size() > tabsBefore);

		for (String win : driver.getWindowHandles()) {
			if (!win.equals(parentWindow)) {
				driver.switchTo().window(win);
				break;
			}
		}

		WebDriverWait pdfWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		pdfWait.until(ExpectedConditions.urlContains(expectedUrlPart));

		boolean isOpened = driver.getCurrentUrl().contains(expectedUrlPart);

		// 🔥 HOLD PDF TAB FOR VISUAL CONFIRMATION
		try {
			Thread.sleep(8000); // ⏳ 5 seconds – adjust as needed
		} catch (InterruptedException e) {
		}

		driver.close();
		driver.switchTo().window(parentWindow);

		return isOpened;
	}

	// Reusable Method for all modules PDF Open In New tab and Stay There

	public boolean clickExportPDFAndStay(String expectedUrlPart) {

		wait.until(ExpectedConditions.elementToBeClickable(exportPDF));

		String parentWindow = driver.getWindowHandle();
		int tabsBefore = driver.getWindowHandles().size();

		exportPDF.click();

		// ✅ WAIT FOR NEW TAB (No lambda)
		wait.until(ExpectedConditions.numberOfWindowsToBe(tabsBefore + 1));

		for (String win : driver.getWindowHandles()) {
			if (!win.equals(parentWindow)) {
				driver.switchTo().window(win);
				break;
			}
		}

		WebDriverWait pdfWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		pdfWait.until(ExpectedConditions.urlContains(expectedUrlPart));

		return driver.getCurrentUrl().contains(expectedUrlPart);
	}

	/************** Export Excel *******************/
	public void clickExportExcel() {
		wait.until(ExpectedConditions.elementToBeClickable(exportExcel));
		exportExcel.click();
	}

	/*
	 * public void search(String se) {
	 * wait.until(ExpectedConditions.visibilityOf(searchBox));
	 * wait.until(ExpectedConditions.elementToBeClickable(searchBox));
	 * 
	 * // searchBox.clear(); searchBox.sendKeys(se); }
	 */

	/*
	 * public void clearSearch() {
	 * 
	 * // Wait till clear button visible
	 * wait.until(ExpectedConditions.visibilityOf(clearsearchBox));
	 * 
	 * // Try normal click try {
	 * wait.until(ExpectedConditions.elementToBeClickable(clearsearchBox));
	 * clearsearchBox.click(); } catch (Exception e) { // Fallback to JS click
	 * (React UI fix) ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].click();", clearsearchBox); }
	 * 
	 * // 🔥 VERY IMPORTANT: wait till search box is EMPTY wait.until(driver ->
	 * searchBox.getAttribute("value").isEmpty()); }
	 */

	public void search(String value) {

		wait.until(ExpectedConditions.visibilityOf(searchBox));
		wait.until(ExpectedConditions.elementToBeClickable(searchBox));

		// 🔥 IMPORTANT: always clear first
		clearSearchInternal();

		searchBox.sendKeys(value);

		System.out.println("🔍 Search value entered: " + value);
	}

	public void clearSearch() {

		wait.until(ExpectedConditions.visibilityOf(searchBox));

		// Try clicking UI clear (X)
		try {
			if (clearsearchBox.isDisplayed()) {
				wait.until(ExpectedConditions.elementToBeClickable(clearsearchBox));
				clearsearchBox.click();
			}
		} catch (Exception e) {
			// ignore – fallback below
		}

		// Safety clear (cross-platform)
		clearSearchInternal();

		System.out.println("❌ Search cleared");
	}

	private void clearSearchInternal() {

		searchBox.click();

		// Keyboard clear (Mac + Windows)
		searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		searchBox.sendKeys(Keys.BACK_SPACE);

		// React-safe JS clear
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input'));", searchBox);

		// Confirm empty
		wait.until(driver -> {
			String value = searchBox.getAttribute("value");
			return value == null || value.trim().isEmpty();
		});
	}

}
