package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtrix24.rental.base.BasePage;

public class LoginPage extends BasePage 
{

	public LoginPage(WebDriver driver) 
	{
		super(driver);
	
		// TODO Auto-generated constructor stub
	}
	
	    /*****************Locators**********************************/
	
		@FindBy(xpath = "//input[@name='username']")
		WebElement username;
		
		@FindBy(xpath = "//input[@name='password']")
		WebElement password;
		
		@FindBy(linkText = "Forgot Password?")
		WebElement forgotPassword;
		
		@FindBy(xpath = "//button[@type='submit']")
		WebElement signin;
		
		@FindBy(linkText = "Register")
		WebElement register;
		
		
		//p[text()='Password is required']
		//p[text()='Username is required']
		//div[@role='alert' and text()='No account was found with that email/username. Please check your input or sign up for an account.']
		//div[@role='alert' and text()='Invalid User name or Password.']
		
		/************ Small helper (inside same class) ************/
	    private void waitFor(WebElement element) 
	    {
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    /************ Actions 
	     * @throws InterruptedException ************/

	    public void setUserName(String um) throws InterruptedException
	    {
	        waitFor(username);
	        username.clear();
	        username.sendKeys(um);
	        Thread.sleep(1000);
	    }
	    
	    public String getLoggedInUserName() {
	        return username.getText().trim();
	    }


	    public void setPassword(String pass) throws InterruptedException 
	    {
	        waitFor(password);
	        password.clear();
	        password.sendKeys(pass);
	        Thread.sleep(1000);
	    }

	    public void clickSignIn() 
	    {
	        wait.until(ExpectedConditions.elementToBeClickable(signin));
	        signin.click();
	    }

	    public void login(String um, String pass) throws InterruptedException 
	    {
	        setUserName(um);
	        setPassword(pass);
	        clickSignIn();
	        Thread.sleep(2000);
	    }
	

}
