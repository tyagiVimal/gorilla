
package com.company.automation.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class ClipBoardUtility {

	public static void printName(String str) {
		System.out.println(str);
	}

	/**
	 * This Method will set content in System Clipboard
	 * 
	 * @param String : Content to be set
	 * 
	 */

	public static void setContent(String Content) {
		StringSelection selection = new StringSelection(Content);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}

	/**
	 * This Method will return clipboard Content.
	 * 
	 * @return Clip Board Content
	 * 
	 */

	public static String getContent() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = "";
		try {
			result = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get Clipboard Data." + e.getMessage());
		}
		return result;
	}
}