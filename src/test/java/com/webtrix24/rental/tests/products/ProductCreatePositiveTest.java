package com.webtrix24.rental.tests.products;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.CommanFunctionalitiesPage;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.ProductsPage;
import com.webtrix24.rental.pages.SidePanel;
import com.webtrix24.rental.utils.ConfigReader;



public class ProductCreatePositiveTest extends BaseClass{
	
	LoginPage loginPage;
	 SidePanel sidePanel;
	 CommanFunctionalitiesPage cmf;
	 ProductsPage productsPage; //  class-level variable

	 // 🔹 Login & basic navigation – ONLY ONCE
   @BeforeClass
   public void setUpCustomerCreate() throws InterruptedException 
   {

       loginPage = new LoginPage(driver);
       sidePanel = new SidePanel(driver);
       cmf = new CommanFunctionalitiesPage(driver);
       productsPage = new ProductsPage(driver);
       // Login
       loginPage.login(
           ConfigReader.getProperty("app.username"),
           ConfigReader.getProperty("app.password")
           );
       Thread.sleep(2000);

       // Navigate to Product module
       sidePanel.clickProducts();
       Thread.sleep(2000);
   }
   
  //  Helper: Open Create Product Form
   private void openCreateProductForm() 
   {
       cmf.clickCreateButton();
       Assert.assertTrue(productsPage.isCreateProductFormDisplayed(),"Create Product form not opened");
   }
   
   @Test(priority = 1, description = "Verify product selection from dropdown in Create Product form")
   public void verifyProductSelectionFromDropdown() throws InterruptedException 
   {
   	
  

       // Step 1: Click Create button
       cmf.clickCreateButton();
  

       // Step 2: Click Product Name field
       productsPage.clickProductNameField();
   

       // Step 3: Select value from dropdown
      productsPage.selectProduct("Lava Magnum Laptop");
      // productsPage.selectProductFromDropdown("MAC");
       

       // Step 4: Verify selection
       String selectedValue = productsPage.getSelectedProductValue();
       Assert.assertEquals(selectedValue, "Lava Magnum Laptop", "❌ Product selection failed!");
      System.out.println("✅ Product selected successfully: " + selectedValue);
   }
   
   

}
