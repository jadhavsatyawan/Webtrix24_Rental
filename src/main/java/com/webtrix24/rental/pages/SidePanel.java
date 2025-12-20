package com.webtrix24.rental.pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtrix24.rental.base.BasePage;



public class SidePanel extends BasePage
{
	/************* Constructor ***************/

	public SidePanel(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	/*******************Locators*******************************/

	@FindBy(xpath = "//button//span[text()='Dashboard']")  ////a[@href='#dashboard']
	WebElement dashboard;
	
	@FindBy(xpath = "//a[@href='/customer']")  //li[@id='customer']
	WebElement customerModule;
	
	@FindBy(xpath = "//button//span[text()='Products']")//a[@href='#rental_product1
	WebElement rentalProduct;
	
	@FindBy(xpath = "//button//span[text()='Deliveries']")  //a[@href='#deliveries']
	WebElement deliveries;

	
	@FindBy(xpath = "//button//span[text()='Quotations']") 
	WebElement quotations;
	
	@FindBy(xpath = "//button//span[text()='Invoices']")     //a[@href='#rental_taxInvoice']
	WebElement invoices;
	
	@FindBy(xpath = "//button//span[text()='Receipts']")
	WebElement receipts;
	
	@FindBy(xpath = "//button//span[text()='Reports']")
	WebElement reports;
	
	@FindBy(xpath = "//a[@href='/pending-billing']")
	WebElement pendingBilling;
	
	@FindBy(xpath = "//a[@href='/CustomerReport']")
	WebElement customerReport;
	
	@FindBy(xpath = "//button//span[text()='Settings']")//li[@id='settings']
	WebElement settings;
	
	@FindBy(xpath = "//a[@href='/users']")
	WebElement users;
	
	@FindBy(xpath = "//a[@href='/modules']")
	WebElement modules;
	
	@FindBy(xpath = "//a[@href='/category']") //a[@href='#category']
	WebElement category;
	
	@FindBy(xpath = "//a[@href='/chargeserialnumber']")//a[@href='#app/207']
	WebElement chargerSerialNumber;
	


	
	/***************************Action Methods***********************************/
	
	public void clickDashboard()
	{
		dashboard.click();
		
	}
	
	public void clickCustomers()
	{
	    wait.until(ExpectedConditions.visibilityOf(customerModule));
	    wait.until(ExpectedConditions.elementToBeClickable(customerModule));
	    customerModule.click();
	}

	
	public void clickProducts()
	{
		rentalProduct.click();
		
	}
	
	public void clickDeliveries()
	{
		deliveries.click();
		
	}
	
	public void clickQuotations()
	{
		quotations.click();
		
	}
	
	public void clickInvoices()
	{
		invoices.click();
		
	}
	
	public void clickReceipts()
	{
		receipts.click();
		
	}
	
	public void clickReports()
	{
		reports.click();
		
	}
	
	public void clickPendingBilling()
	{
		pendingBilling.click();
		
	}
	
	public void clickCustomerReport()
	{
		customerReport.click();
		
	}
	
	public void clickSettings()
	{
		settings.click();
		
	}
	
	public void clickUsers()
	{
		users.click();
		
	}
	
	public void clickModules()
	{
		modules.click();
		
	}
	
	public void clickcategory()
	{
		category.click();
		
	}
	
	public void clickchargeserialnumber()
	{
		chargerSerialNumber.click();
		
	}

}
