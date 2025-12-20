package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;

public class QuotationsPage extends BasePage 
{

	public QuotationsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	//Locator
	
	//Locators
		@FindBy(xpath = "//input[@placeholder='Auto Generated']")
		WebElement deliveryNumber;
		
		@FindBy(xpath = "//input[@id='customer']")
		WebElement customerDropdown;
		
		@FindBy(xpath = "//input[@placeholder='Select a date']")
		WebElement quotationDate;
			
		
		@FindBy(xpath = "//div[@class='product-search__control css-1xtaatr-control']")
		WebElement productSeacrhBox;
		
		@FindBy(xpath = "//input[@name='invoiceLineQty']")
		WebElement invoiceLineQty;
		
		@FindBy(xpath = "//input[@name='invoiceLineRate']")
		WebElement invoiceLineRate;
		

		@FindBy(xpath = "//input[@name='invoiceLineAmount']")
		WebElement invoiceLineAmount; //Total
		
		@FindBy(xpath = "//textarea[@name='invoiceLineNarr']")
		WebElement description;
		
		@FindBy(xpath = "//input[@id='charger_number']")
		WebElement chargerNumber;
		
		@FindBy(xpath = "//input[@placeholder='Select Accessories']")
		WebElement accessories;
		
		@FindBy(xpath = "//select[@name='billing_type']")
		WebElement billingType;         //(//select[@name='billing_type']/option)[2]
		
		@FindBy(xpath = "//button[@type='button' and text()='+ Add More Product']")
		WebElement addMoreProduct;
		
		@FindBy(xpath = "//div[@contenteditable='true' and @role='textbox']")
		WebElement termsAndConditions;
		
		
		
		@FindBy(xpath = "//button[@type='button' and text()='Create Quotation Challan']")
		WebElement createQuotationChallan;
		
		   
		////div[@id='swal2-html-container' and text()='Row 1: Product not selected.'] //Customer Not Selected
		
		@FindBy(xpath = "//button[@type='button' and text()='OK']")
		WebElement okButton;
		
		@FindBy(xpath = "//div[@id='swal2-html-container' and text()='Customer Not Selected']")
		WebElement customerNotSelected;   
		
		@FindBy(xpath = "//div[@role='alert' and text()=Error while creating quotation. (Internal server error. Please contact support.)")
		WebElement deliveryDateEmpty;   
		
		@FindBy(xpath = "//div[@role='alert' and text()='Error while creating quotation. (You cannot save future date Quotation.)']")
		WebElement cannotSaveFutureDate;
		
		@FindBy(xpath = "//div[@role='alert' and text()='Error while creating quotation. (You cannot save back dated Quotation.)")
		WebElement cannotSavePastDate;
		
		@FindBy(xpath = "//button[@class='Toastify__close-button Toastify__close-button--light']")
		WebElement closeButtonToastMessages;
		
		
		@FindBy(xpath = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success' and text()='Quotation Challan Created']")
		WebElement createQuotation;
		
		////Sapn[text()='IGST(%) ']/following-sibling::input[@type='number']
		@FindBy(xpath = "//div[@class='flex items-center justify-between']/child::span[text()='IGST(%) ']/input[@type='number']")
		WebElement enterIGST;
		
		@FindBy(xpath = "//div[@class='flex items-center justify-between']/child::span[text()='CGST(%) ']/input[@type='number']")
		WebElement enterCGST;
		

		@FindBy(xpath = "//div[@class='flex items-center justify-between']/child::span[text()='SGST(%) ']/input[@type='number']")
		WebElement enterSGST;
}
