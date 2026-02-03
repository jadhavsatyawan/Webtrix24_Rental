package com.webtrix24.rental.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtrix24.rental.base.BasePage;

public class CustomerListPage extends BasePage {

	/*************** Constructor **********************/

	public CustomerListPage(WebDriver driver) {
		super(driver);

	}

	/****************** Locators ************/// All customer rows (each row is a flex group)

	@FindBy(xpath = "//div[contains(@class,'group') and .//button[@title='Customer Edit']]")
	private List<WebElement> customerRows;

	// Header row columns (Customer Name, Email, Mobile etc.)
	@FindBy(xpath = "//div[contains(@class,'bg-gray-100')]/div[contains(@class,'group')]")
	private List<WebElement> tableHeaders;

	// Update Customer form title
	@FindBy(xpath = "//h2[contains(text(),'Update Customer')]")
	private WebElement updateCustomerTitle;

	// Customer Report button (inside row – hover)
	private By customerReportBtn = By.xpath(".//button[@title='Customer Rental Report']");

	/****************** Private Helpers *********************/

	/** Find column index dynamically by header name */
	private int getColumnIndexByHeader(String headerName) {
		for (int i = 0; i < tableHeaders.size(); i++) {
			String text = tableHeaders.get(i).getText().trim();
			if (text.equalsIgnoreCase(headerName)) {
				return i;
			}
		}
		throw new RuntimeException("Column not found: " + headerName);
	}

	/** Get cell text from a row using column index */
	private String getCellText(WebElement row, int columnIndex) {
		List<WebElement> cells = row.findElements(By.xpath("./div"));
		if (columnIndex >= cells.size()) {
			return "";
		}
		return cells.get(columnIndex).getText().trim();
	}

	/****************** Action Methods ************/

	public void openFirstCustomerEditForm() {
		wait.until(driver -> customerRows.size() > 0);

		WebElement firstRow = customerRows.get(0);
		new Actions(driver).moveToElement(firstRow).perform();

		WebElement editBtn = firstRow.findElement(By.xpath(".//button[@title='Customer Edit']"));
		wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
	}

	public boolean isUpdateCustomerFormOpened() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(updateCustomerTitle)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void openFirstCustomerReport() {
		wait.until(driver -> customerRows.size() > 0);

		WebElement firstRow = customerRows.get(0);
		new Actions(driver).moveToElement(firstRow).pause(400).perform();

		WebElement reportBtn = firstRow.findElement(customerReportBtn);
		wait.until(ExpectedConditions.elementToBeClickable(reportBtn)).click();
	}

	/****************** Search Validations *********************/

	public boolean isAnyCustomerDisplayed() {
		try {
			wait.until(driver -> customerRows.size() > 0);
			return customerRows.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	/** Validate search by CUSTOMER NAME */
	public boolean areAllDisplayedCustomersMatchingName(String name) {
		String expected = name.toLowerCase();
		int nameColIndex = getColumnIndexByHeader("Customer Name");

		for (WebElement row : customerRows) {
			String actual = getCellText(row, nameColIndex).toLowerCase();
			if (!actual.contains(expected)) {
				return false;
			}
		}
		return true;
	}

	/** Validate search by EMAIL */
	public boolean areAllDisplayedCustomersMatchingEmail(String email) {
		String expected = email.toLowerCase();
		int emailColIndex = getColumnIndexByHeader("Customer Email");

		for (WebElement row : customerRows) {
			String actual = getCellText(row, emailColIndex).toLowerCase();
			if (!actual.contains(expected)) {
				return false;
			}
		}
		return true;
	}

	/** Wait till search debounce + API load completes **/
	public void waitForSearchToSettle() {
		wait.until(driver -> {
			try {
				WebElement footer = driver.findElement(By.xpath("//div[contains(text(),'Showing')]"));
				return footer.isDisplayed();
			} catch (Exception e) {
				return false;
			}
		});
	}

}