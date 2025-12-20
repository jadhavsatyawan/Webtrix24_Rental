package com.webtrix24.rental.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webtrix24.rental.base.BasePage;




public class CustomersPage extends BasePage
{
	 //ToastUtil toastUtil;  // ✅ Declare
	 //DropdownUtil  dropdownUtil;
	// AddressUtil addressUtil;
	
	/********************** Constructor***************************************/
	public CustomersPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
		//toastUtil = new ToastUtil(driver);  // ✅ Initialize once
		//dropdownUtil = new DropdownUtil(driver);
		//addressUtil = new utilities.AddressUtil()
		
	}
	
	/*************************Locators**********************************/
	
	@FindBy (xpath = "//h2[contains(text(),'Create Customer')]")
	WebElement customerFormTitle;
	
	@FindBy(xpath = "//label[text()='Customer Name']/following-sibling::input")
	WebElement customerName;
	
	@FindBy(xpath = "//div[contains(text(),'Customer Name is required.')]")
	WebElement customerNameErrorMessage;
	
	@FindBy(xpath = "//input[@id='assignee']")
	WebElement assignee;
	By dropdownOptions = By.xpath("//div[contains(@class,'cursor-pointer')]//*[normalize-space(text())!='']");
	
	@FindBy(xpath = "//input[@id='lead_source']")
	WebElement leadSource;
	
	@FindBy(xpath = "//label[text()='Customer Email']/following-sibling::input")
	WebElement CustomerEmail;
	
	@FindBy(xpath = "(//input[@name='phone'])[1]")
	WebElement mobileNumber;
	
	@FindBy(xpath = "(//input[@name='phone'])[2]")
	WebElement whatsAppNumber;
	
	@FindBy(xpath = "//label[text()='Billing Name']/following-sibling::input")
	WebElement billingName;
	
	@FindBy(xpath = "//label[text()='Billing Address']/following-sibling::input")
	WebElement billingAddress;
	
	@FindBy(xpath = "//label[text()='Zipcode']/following-sibling::input")
	WebElement zipCode;
	
	@FindBy(xpath = "//label[text()='Adhar Number']/following-sibling::input")
	WebElement adharNumber;
	
	@FindBy(xpath = "//label[text()='PAN No.']/following-sibling::input")
	WebElement panNo;
	
	@FindBy(xpath = "//label[text()='Website']/following-sibling::input")
	WebElement website;
	

	@FindBy(xpath = "//label[text()='GST No.']/following-sibling::input")
	WebElement gstNo;
	
	@FindBy(xpath = "//input[@id='gst_state']")
	WebElement gststateDropdown;
	
	@FindBy(xpath = "(//div[@class='cursor-pointer px-4 py-2 hover:bg-gray-100 flex items-start justify-between text-sm'])[1]")
	WebElement SelectState;
	
	@FindBy(xpath = "//div[@class='Toastify__toast-container Toastify__toast-container--top-right']/div[@role='alert' and contains(text(),'Saved successfully')]")
	WebElement succesMessage;
	 @FindBy(xpath = "//button[contains(text(),'Save')]")
	    WebElement saveButton;
	
	
	
	
	/***************************Actions methods************************************************/
	
	public boolean isCreateCustomerFormDisplayed() 
	{
        try {
            return customerFormTitle.isDisplayed();   //Access directly — PageFactory initialized it
        } catch (Exception e) {
            return false;
        }
	}
	
	public void fillCustomerForm(String name,String email, String phone) 
	{
		
	    customerName.clear();
        customerName.sendKeys(name);

	    CustomerEmail.clear();
	    CustomerEmail.sendKeys(email);

	    mobileNumber.clear();
	    mobileNumber.sendKeys(phone);
	    
	   // assignee.click();
	}
	
	public String getCustomerNameValidationMessage() 
	{
	    try
	    {
	        return customerNameErrorMessage.getText();
	    } 
	    catch (Exception e) 
	    {
	        return "";
	    }
	}
	
	// Method to get toast message text
	public String getSuccessToastMessage()
	{
	    try 
	    {
	        return succesMessage.getText().trim();
	    } 
	    catch (Exception e) 
	    {
	        return "";
	    }
	
	}
	
	// Method: enter customer name for Sepcefic name
	public void enterCustomerName(String name) 
	{
		customerName.clear();
		customerName.sendKeys(name);
	}

	// Method: Click on Product Name field to open dropdown
    public void clickLeadSourceField() 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(leadSource));
        leadSource.click();
    }
    public void selectleadSource(String leadSource) throws InterruptedException 
    {
        //dropdownUtil.selectFromSearchableDropdown(this.leadSource, leadSource);
    }
    
    
    
    public void selectGSTStateByName(String stateName) throws InterruptedException 
    {
 	   // try {
 	        gststateDropdown.click();  // Open dropdown
 	        Thread.sleep(2000);        // Wait for options to load
 	    
 	       // cmf.refreshDropdwonList();
//           Thread.sleep(2000); 
//           System.out.println("Asnf " );
 	        // 1️⃣ Get state code from AddressUtil
 	        //String stateCode = AddressUtil.getGSTCode(stateName); // e.g., "27"

 	        // 2️⃣ Prepare expected value in dropdown format
 	        String expectedValue =  stateName.trim(); // "27-Maharashtra"
 	        System.out.println("🔎 Selecting GST State: " + expectedValue);
 	        gststateDropdown.sendKeys(expectedValue);
 	        SelectState.click();

 	        System.out.println("✅ GST State selected successfully: " + expectedValue);
 	   // }
// 	     catch (Exception e) {
// 	        System.out.println("❌ Failed to select GST state: " + e.getMessage());
// 	        e.printStackTrace();
// 	        throw new RuntimeException("Failed to select GST state: " + stateName);
// 	    }
 	}
	
	  

}
