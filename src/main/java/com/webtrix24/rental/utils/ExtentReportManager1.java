package com.webtrix24.rental.utils;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webtrix24.rental.core.BaseClass;

public class ExtentReportManager1 implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of report
	public ExtentReports extent; // report object

	// IMPORTANT CHANGE (Thread-safe + static)
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	String repName;

	// @Override
	/*
	 * public void onStart(ITestContext testContext) {
	 * 
	 * String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
	 * Date()); repName = "Test-Report-" + timeStamp + ".html";
	 * 
	 * sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
	 * sparkReporter.config().setDocumentTitle("Webtrix24 Rental Automation Report"
	 * );
	 * sparkReporter.config().setReportName("Webtrix24 Rental Functional Testing");
	 * sparkReporter.config().setTheme(Theme.DARK);
	 * 
	 * extent = new ExtentReports(); extent.attachReporter(sparkReporter);
	 * 
	 * extent.setSystemInfo("Application", "Webtrix24 Rental");
	 * extent.setSystemInfo("Module", "Customers");
	 * extent.setSystemInfo("Sub-Module", "Create Form");
	 * extent.setSystemInfo("User Name", System.getProperty("user.name"));
	 * extent.setSystemInfo("Environment", "QA");
	 * 
	 * String os = testContext.getCurrentXmlTest().getParameter("os");
	 * extent.setSystemInfo("Operating System", os);
	 * 
	 * String browser = testContext.getCurrentXmlTest().getParameter("browser");
	 * extent.setSystemInfo("Browser", browser);
	 * 
	 * List<String> includedGroups =
	 * testContext.getCurrentXmlTest().getIncludedGroups(); if
	 * (!includedGroups.isEmpty()) { extent.setSystemInfo("Groups",
	 * includedGroups.toString()); } }
	 */
	@Override
	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";

		// ✅ CROSS-PLATFORM REPORT DIRECTORY
		String reportDir = System.getProperty("user.dir") + File.separator + "reports";

		// 🔥 THIS CREATES THE FOLDER (MOST IMPORTANT)
		new File(reportDir).mkdirs();

		sparkReporter = new ExtentSparkReporter(reportDir + File.separator + repName);

		sparkReporter.config().setDocumentTitle("Webtrix24 Rental Automation Report");
		sparkReporter.config().setReportName("Webtrix24 Rental Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "Webtrix24 Rental");
		extent.setSystemInfo("Module", "Customers");
		extent.setSystemInfo("Sub-Module", "Create Form");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}

	// ✅ CREATE TEST HERE (IMPORTANT)
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().assignCategory(result.getMethod().getGroups());
		test.get().log(Status.PASS, "Test executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, result.getThrowable());

		try {
			String imgPath = new BaseClass().captureScreen(result.getMethod().getMethodName());

			// 🔥 RELATIVE PATH FROM REPORT LOCATION
			String relativePath = "../Screenshots/" + new File(imgPath).getName();

			test.get().addScreenCaptureFromPath(relativePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().assignCategory(result.getMethod().getGroups());
		test.get().log(Status.SKIP, "Test skipped");
		test.get().log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
		// Only This Path Working on Windows
		// String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" +
		// repName;
		// Woring on Mac and Windows
		String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator
				+ repName;

		File extentReport = new File(pathOfExtentReport);

		try {
			String reportPath = extentReport.getAbsolutePath();

			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				Runtime.getRuntime().exec(new String[] { "open", reportPath });
			} else {
				Desktop.getDesktop().browse(extentReport.toURI());
			}

		} catch (Exception e) {
			System.out.println("Unable to auto-open report");
			System.out.println("Open manually: " + extentReport.getAbsolutePath());
		}

		/*
		 * try { if (Desktop.isDesktopSupported()) {
		 * Desktop.getDesktop().browse(extentReport.toURI()); } else {
		 * System.out.println("Desktop not supported. Open report manually: " +
		 * extentReport.getAbsolutePath()); } } catch (Exception e) {
		 * System.out.println("Unable to auto-open report.");
		 * System.out.println("Open manually: " + extentReport.getAbsolutePath()); }
		 */

	}

}
