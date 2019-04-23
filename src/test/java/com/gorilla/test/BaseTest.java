package com.gorilla.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gorilla.pages.HomePage;
import com.gorilla.webDriver.BasePage;

public class BaseTest extends BasePage {
	HomePage home = new HomePage();

	@BeforeMethod

	public void setUpURL() {
		openURL();
	}

	@Test
	public void search() {
		home.searchTopic();
	}

	@AfterMethod

	public void close() {
		tearDown();
	}
}
