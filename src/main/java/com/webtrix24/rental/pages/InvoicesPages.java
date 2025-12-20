package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;



public class InvoicesPages extends BasePage
{

	public InvoicesPages(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	//Locator
	
	@FindBy(xpath = "//button[@class=' mr-4 hover:underline text-blue-500' and text()='Select All']")
	WebElement  selectAllButton;
	
	@FindBy(xpath = "//button[@class='text-gray-400 hover:text-red-500 hover:underline' and text()='Clear Selection']")
	WebElement  clearSelection;
	
	@FindBy(xpath = "//input[@id='is_gst_billing' and @type='checkbox']")
	WebElement  gstCheckBox;
	
	@FindBy(xpath = "//button[text()=' Generate Invoice']")
	WebElement  generateInvoice;
	
	@FindBy(xpath = "//input[@id='customer']")
	WebElement customerDropdown;
	
	@FindBy(xpath = "//button[@class='absolute right-3 top-1/2 -translate-y-1/2 text-xs text-gray-500 hover:text-red-500' and text()='×']")
	WebElement dropdownOptionCloseButton;
	
	@FindBy(xpath = "//button[@title='Log Full/Partial Payments']")
	WebElement logFullPartialPayments;
	
//	@FindBy(xpath = "//button[@title='Log Full/Partial Payments']")
//	WebElement logFullPartialPayments;

}
