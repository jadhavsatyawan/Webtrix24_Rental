package com.webtrix24.rental.core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener
{
	

	@Override
	public void onTestStart(ITestResult result)
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		System.out.println("Test Exceution Start Before Every Method");
	}
	
	@Override
	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
		System.out.println("Test Excecution Start....");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println("Test Passed...");

	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		System.out.println("Test Failed...");

	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
		System.out.println("Test Skipped...");

	}
	
	@Override
	public void onFinish(ITestContext context) 
	{
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		System.out.println("All Test Method Excuted...");

	}
}
