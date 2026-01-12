package com.webtrix24.rental.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtrix24.rental.base.BasePage;

public class CustomerListPage extends BasePage {

	/*************** Constructor **********************/

	public CustomerListPage(WebDriver driver) {
		super(driver);

	}

	/****************** Locators ************/

	// All customer rows
	@FindBy(xpath = "//div[contains(@class,'flex border-b')]")
	List<WebElement> customerRows;

	// Customer name column
	@FindBy(xpath = "//div[@title and span]")
	List<WebElement> customerNameList;

	/****************** Action Methods ************/

	// Check customer name present in list
	public boolean isCustomerNamePresent(String expectedName) {
		wait.until(ExpectedConditions.visibilityOfAllElements(customerNameList));

		for (WebElement name : customerNameList) {
			if (name.getText().trim().equalsIgnoreCase(expectedName)) {
				return true;
			}
		}
		return false;
	}

	public void waitForListToLoad() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'border-b')]")));
	}

	public void clickEditByCustomerName(String customerName) {

		By editBtnXpath = By.xpath("//span[normalize-space()='" + customerName + "']"
				+ "/ancestor::div[contains(@class,'group')]" + "//button[@title='Customer Edit']");

		WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editBtnXpath));
		editBtn.click();
	}

	public void clickEditByCustomerEmail(String email) {

		By editBtn = By.xpath("//div[normalize-space()='" + email + "']" + "/ancestor::div[contains(@class,'border-b')]"
				+ "//button[@title='Customer Edit']");

		WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
		edit.click();
	}

	/**
	 * Open latest (top) customer from list
	 */
	public void openLatestCustomer() {

		wait.until(ExpectedConditions.visibilityOfAllElements(customerRows));

		WebElement firstRowEditBtn = customerRows.get(0).findElement(By.xpath(".//button[@title='Customer Edit']"));

		wait.until(ExpectedConditions.elementToBeClickable(firstRowEditBtn));
		firstRowEditBtn.click();
	}

}
