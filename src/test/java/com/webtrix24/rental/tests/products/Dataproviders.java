package com.webtrix24.rental.tests.products;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.webtrix24.rental.utils.ExcelUtility;

public class Dataproviders {

	// DataProvider 1
	@DataProvider(name = "ProductData")
	public String[][] getData() throws IOException {
		// Taking Excel FIle From testData
		String path = ".\\testData\\Product Data.xlsx";

		// Creating an Object For XLUtility
		ExcelUtility xlutill = new ExcelUtility(path);

		int totalrows = xlutill.getRowCount("sheet1");
		int totalcols = xlutill.getCellCount("sheet1", 1);

		// Created for two dimension array which can store
		String loginData[][] = new String[totalrows][totalcols];

		// Read the data from excel storing in two dimensional array
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++)// 0 i is rows j is col
			{
				loginData[i - 1][j] = xlutill.getCellData("sheet1", i, j);// 1,0

			}
		}
		return loginData; // returning two dimension array
	}

}
