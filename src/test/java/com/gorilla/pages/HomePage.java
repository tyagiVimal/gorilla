package com.gorilla.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath = "//input[@id='edit-search-block-form--2']")
	private WebElement inputBox;

	@FindBy(xpath = "//*[@id='search-block-form']//button[@class='btn btn-danger']")
	private WebElement searchBtn;

	public void searchTopic() {
		inputBox.clear();
		inputBox.sendKeys("Selenium");
		searchBtn.click();
	}

}
