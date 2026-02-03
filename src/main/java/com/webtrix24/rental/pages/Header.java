package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;

public class Header extends BasePage {

	/********************** Constructor ***************************************/

	public Header(WebDriver driver) {
		super(driver);

	}

	/************************* Locators **********************************/

	/*
	 * @FindBy(xpath = "//header//select[option[text()='GST']]") WebElement
	 * gstCompanyDropdown;
	 * 
	 * /*@FindBy(xpath = "//header//select[option[text()='NON GST']]") WebElement
	 * tNon_GSTCompanyDropdown;
	 */
	@FindBy(xpath = "//header//select")
	WebElement gstCompanyDropdown;

	// @FindBy(xpath = "//button[@class='flex items-center gap-1 cursor-pointer']")
	// WebElement dropdwon;

	@FindBy(xpath = "//button[.//svg]")
	WebElement dropdwon;

	// button[@class="flex items-center gap-1 cursor-pointer"]
	// For Specefic username
	@FindBy(xpath = "//div[@class='text-gray-900 font-semibold text-base' and text()='Satyawan']")
	WebElement popupUserName;

	// For Dynamic username
	@FindBy(xpath = "//div[contains(@class,'font-semibold') and contains(@class,'text-base')]")
	WebElement dynamicUserName;

	/*************************** Actions method **********************/

	public void selectGSTCompany() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(gstCompanyDropdown));

		Select select = new Select(dropdown);
		select.selectByVisibleText("GST");
	}

	public void selectNonGSTCompany() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(gstCompanyDropdown));

		Select select = new Select(dropdown);
		select.selectByVisibleText("NON GST");
	}

	public void clickProfileDropdwon() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement dropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'relative')]//button")));

		dropdown.click();
	}

	public String getLoggedInUserNameFromPopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dynamicUserName));
		return dynamicUserName.getText().trim();
	}

}
