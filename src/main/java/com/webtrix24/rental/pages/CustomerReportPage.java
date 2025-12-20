package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;
import com.webtrix24.rental.utils.DropdownUtil;



public class CustomerReportPage extends BasePage
{

	DropdownUtil  dropdownUtil;
	
	/************* Constructor ***************/
	
	public CustomerReportPage(WebDriver driver)
	{
		
		super(driver);
		// TODO Auto-generated constructor stub
		
		dropdownUtil = new DropdownUtil(driver);
		
		
	}
	
	/*******************Locators*******************************/
	
	@FindBy(xpath = "//input[@id='customer']")
	WebElement customerDropdown;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchProduct;
	
	
	@FindBy(xpath = "//div[contains(@class,'max-w-5xl')]//div[.//div[text()='Asset Type']]//select")
	WebElement assetType;
	
	@FindBy(xpath = "//div[contains(@class,'max-w-5xl')]//div[.//div[text()='Status']]//select")
	WebElement status;
	
	@FindBy(xpath = "//div[contains(@class,'max-w-5xl')]//div[.//div[text()='Date From']]//input[@placeholder='Select a date']")
	WebElement dateFrom;
	
	@FindBy(xpath = "//div[contains(@class,'max-w-5xl')]//div[.//div[text()='Date To']]//input[@placeholder='Select a date']")
	WebElement dateTo;
	
	@FindBy(xpath = "//div[contains(@class,'max-w-5xl')]//div[.//div[text()='Billing Type']]//select")
	WebElement BillingType;
	
	@FindBy(xpath = "//button[@data-report_type='excel' and text()='⬇️ Export Excel']")
	WebElement exportExcel;
	
	@FindBy(xpath = "//button[@data-report_type='pdf' and text()='⬇️ Export PDF']")
	WebElement exportPDF;
	
	@FindBy(xpath = "( //button[text()=' Return '])[1]")
	WebElement returnButton;
	
	@FindBy(xpath = "//input[@placeholder='Enter GRN document number']")
	WebElement enterGRN;
	
	
	
	@FindBy(xpath = "//input[@type='date']")
	WebElement returnDate;
	
	@FindBy(xpath = "//input[@id='reason']")
	WebElement reason;
	
	@FindBy(xpath = "//div[@role='alert' and text()='Return Date date cannot be in the past and future.']")
	WebElement returnDateErrorMessage;
	
	@FindBy(xpath = "//textarea[@placeholder='Add any additional notes about the return...']")
	WebElement additionalNoteReturn;
	
	@FindBy(xpath = "//button[@class='px-4 py-2 text-sm rounded-md border border-gray-300 text-gray-700 hover:bg-gray-100' and text()='Cancel ']")
	WebElement cancelButton;
	
	@FindBy(xpath = "//button[@type='submit' and @class='px-4 py-2 text-sm rounded-md bg-blue-600 text-white hover:bg-blue-700']")
	WebElement saveButton;
	
	@FindBy(xpath = "( //button[text()=' Upgrade '])[1]")
	WebElement upgradeButton;
	
	@FindBy(xpath = "//input[@id='hdd_capacity']")
	WebElement hddCapacity;
	
	@FindBy(xpath = "//input[@id='memory']")
	WebElement memory;
	
	@FindBy(xpath = "//input[@id='operating_system']")
	WebElement operating_system;
	
	@FindBy(xpath = "//select[@id='charges_apply_from']")
	WebElement chargesApplyFrom;
	
	@FindBy(xpath = "//textarea[@placeholder='Add any additional notes about the upgrade...']")
	WebElement additionalNoteUpgrade;
	
	@FindBy(xpath = "//span[text()='View upgrade details']")
	WebElement viewUpgradeDetails;
	
	@FindBy(linkText = "Contact Support")
	WebElement contactSupport;
	
	
	
	/***************************Action Methods***********************************/
	
	  public void clickCustomerField() 
	  {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(customerDropdown));
	        customerDropdown.click();
	  }
	  public void selectCustName(String custName) throws InterruptedException 
	  {
		     dropdownUtil.selectFromSearchableDropdown(this.customerDropdown, custName);//productTYpeInput
		 
	  }
	  
	  // 🔹 Method to select random customer
	  public void selectRandomCustomer() throws InterruptedException 
	  {
	        dropdownUtil.selectRandomFromSearchableDropdown(customerDropdown);
	  }
	  
	  public void scrollToContactSupport() throws InterruptedException 
	  {
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", contactSupport);
		    Thread.sleep(2000);
	  }
	  
	  public void clickReturnButton() 
	  {
		  returnButton.click();
		
	  }
	  
	  public void setGRNNumber(String grnNum) 
	  {
		  enterGRN.click();
		  enterGRN.sendKeys(grnNum);
		
	  }
	  
	  public void clickReason() 
	  {
		  reason.click();
		
	  }
	  
	  public void selectReturnReason(String reason) throws InterruptedException 
	  {
		     dropdownUtil.selectFromSearchableDropdown(this.reason, reason);
		 
	  }
	  
	  public void writeAdditionalNote(String returnNote) 
	  {
		additionalNoteReturn.click();
		additionalNoteReturn.sendKeys(returnNote);
	
	  }
	  
	  public void clickCancalButton() 
	  {
		  cancelButton.click();
		
	}

}
