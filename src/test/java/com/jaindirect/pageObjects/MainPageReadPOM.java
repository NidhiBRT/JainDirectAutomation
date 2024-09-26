package com.jaindirect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jaindirect.utilities.CommonUtils;

public class MainPageReadPOM extends CommonUtils {
	
	@FindBy(xpath = "//img[@src='https://cdn.jaindirect.org/31517/IMG20240810120413.jpg']")
	WebElement ClickOnImage;
	
	@FindBy(xpath = "//a[contains(@class,'nav-link') and (@href='sanstha')]")
	WebElement Sanstha;
	
	@FindBy(xpath="//span[contains(@class,'manage_my_sanstha_label') and text()='JainEducation']")
	WebElement AdminSanstha;

	public MainPageReadPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void ClickOnImageHome() {
		click(ClickOnImage);
	}

	public void ClickOnSansta() {
		click(Sanstha);
	}
	public void ClickOnAdminSanstha() {
		click(AdminSanstha);
	} 

}
