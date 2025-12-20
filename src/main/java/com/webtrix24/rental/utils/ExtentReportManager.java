package com.webtrix24.rental.utils;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webtrix24.rental.core.BaseClass;



public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populate common info on the report 
	public ExtentTest test;  // creating test case entries in the report and update status of the test methods 
	
	String repName;
	
	public void onStart(ITestContext testContext)  
	{
		/* SimpleDateformat df = new SimpleDateformat("yyyy.MM.dd.HH.mm.ss");
		 Date dt = new Date(); 		
		 String currentdatetimestamp=df.format(dt);
		 
 */
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		   
		repName ="Test-Report-" + timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		
		sparkReporter.config().setDocumentTitle("Webtrix24 Rental Automation Report"); //Title of the report
		sparkReporter.config().setReportName("Webtrix24 Rental Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Webtrix24 Rental");
		extent.setSystemInfo("Module", "Customers");
		extent.setSystemInfo("Sub-Module", "Create Form");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To Display groups in report
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To Display groups in report
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
			
		} catch (Exception e1) {
			
			// TODO: handle exception
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) 
	{

		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//To Display groups in report
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
			
	}
	
	public void onFinish(ITestContext testContext) 
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try 
		{
			Desktop.getDesktop().browse(extentReport.toURI());  //Report Open on Browser Automatically
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//Immediately Send Generated Report Automatically Send your team
	/*	 try 
		{
           URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);	
           
           		//Create the email message
           		ImageHtmlEmail email = new ImageHtmlEmail();
           		email.setDataSourceResolver(new DataSourceUrlResolver(url));
           		email.setHostName("smtp.googlemail.com"); //This Will work only Gmail
           		email.setSmtpPort(465);  // This is also
           		email.setAuthenticator(new DefaultAuthenticator("jadhavsatyawan1999@gmail.com", "password"));
           		email.setSSLOnConnect(true);
           		email.setFrom("jadhavsatyawan1999@gmail.com"); //sender
           		email.setSubject("Test Results");   //Email Subject
           		email.setMsg("Please find Attached Report"); //Email Body
           		email.addTo("");//Recevier
           		email.attach(url,"extent report","please check report...");
           		email.send();//send the email
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} */
		

		
		
	}

}
