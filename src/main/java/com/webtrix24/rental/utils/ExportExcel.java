package com.webtrix24.rental.utils;

import java.io.File;

public class ExportExcel {

	public static boolean isExcelDownloaded() throws InterruptedException {

		Thread.sleep(5000); // allow download

		String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
		File folder = new File(downloadPath);

		File[] files = folder.listFiles();
		if (files == null)
			return false;

		for (File file : files) {
			if (file.getName().endsWith(".xlsx") && file.length() > 0) {
				System.out.println("Downloaded file: " + file.getName());
				return true;
			}
		}
		return false;
	}

}
