package com.gorilla.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gorilla.automation.property.PropertyManager;

public class DriverFactory {

	static String driverLocation;
	public static WebDriver driver;

	public static void getDriver() {
		String browser = PropertyManager.getResourceBundle().getString("BROWSER");
		switch (browser.toUpperCase()) {
		case "CHROME":
			driverLocation = "./src/test/resources/lib/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverLocation);
			driver = new ChromeDriver();
			break;
		case "IE":

			break;
		case "FIREFOX":

			break;

		default:
			break;
		}

	}

	public static void main(String args[]) {
		getDriver();
	}
}
