package com.webtrix24.rental.tests.customer;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.CustomersPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;



//@Listeners(utilities.MyListener.class)
public class CustomerCreatePositiveTest extends BaseClass
{
	 LoginPage loginPage;
	 SidePanel sidePanel;
	 CommanFunctionalitiesPage cmf;
	 CustomersPage customersPage; //  class-level variable

	 // 🔹 Login & basic navigation – ONLY ONCE
    @BeforeClass
    public void setUpCustomerCreate() throws InterruptedException 
    {

        loginPage = new LoginPage(driver);
        sidePanel = new SidePanel(driver);
        cmf = new CommanFunctionalitiesPage(driver);
        customersPage = new CustomersPage(driver);

        // Login
        loginPage.login(
            ConfigReader.getProperty("app.username"),
            ConfigReader.getProperty("app.password")
            );
        Thread.sleep(2000);

        // Navigate to Customer module
        sidePanel.clickCustomers();
        Thread.sleep(2000);
    }
    
   //  Helper: Open Create Customer Form
    private void openCreateCustomerForm() 
    {
        cmf.clickCreateButton();
        Assert.assertTrue(customersPage.isCreateCustomerFormDisplayed(),"Create Customer form not opened");
    }
    
    
    /********************* POSITIVE TEST CASES ***********************************/
    
    @Test(priority = 1, description = "Verify valid input in Customer Name field")
    public void verifyValidCustomerNameInput() throws InterruptedException 
    {
        // Step 1: Open Create Customer form
        openCreateCustomerForm();

        // Step 2: Enter valid customer name & other required fields
        customersPage.fillCustomerForm(
            "Samar patil",
            "samar@gmail.com",
            "9987587485"
        );

        // Step 3: Click Save and get toast
        String toastMsg = cmf.clickSaveAndGetToast();
        Thread.sleep(2000);

        Assert.assertNotNull(toastMsg, "Toast message was not captured!");
        Assert.assertTrue(
            toastMsg.toLowerCase().contains("success"),
            "Unexpected toast: " + toastMsg
        );
    }

}