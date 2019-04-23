package com.gorilla.webDriver;

import com.gorilla.automation.property.PropertyManager;

public class BasePage extends DriverFactory {
	private String url = PropertyManager.getResourceBundle().getString("BASE_URL");

	public void openURL() {
		getDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void tearDown() {
		driver.quit();
	}

}
