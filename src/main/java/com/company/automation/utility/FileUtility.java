
package com.company.automation.utility;

import java.io.File;

public class FileUtility {

	public static void deleteFile(String fileName) {
		try {
			File file = new File(System.getProperty("user.dir").split("target")[0] + "\\" + fileName);
			if (file.delete()) {
				System.out.println("Deletion Of File :: " + file.getName() + " is Successfull");
			} else {
				System.out.println("Deletion Of File :: " + file.getName() + " is Failed");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
