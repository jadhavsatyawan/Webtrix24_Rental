package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;
public class PendingBillingReportPage extends BasePage
{

	public PendingBillingReportPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locator
	@FindBy(xpath = "//input[@id='customer']")
	WebElement customerDropdown;
	
	@FindBy(xpath = "//input[@id='product_type']")
	WebElement type;
	
	@FindBy(xpath = "//button[@class='border px-3 py-1 mt-5 rounded text-sm text-gray-700 hover:bg-gray-50' and text()='Reset']")
	WebElement resetButton;
	
	@FindBy(xpath = "//button[text()=' Create Invoice']")
	WebElement createInvoiceButton;
	
	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextButton;
	
	@FindBy(xpath = "//button[text()='Previous']")
	WebElement previousButton;

}
