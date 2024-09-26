package com.jaindirect.pageObjects;

import javax.mail.SendFailedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jaindirect.utilities.CommonUtils;

public class SansthasMainPagePOM extends CommonUtils {

	@FindBy(xpath = "//input[@type='text' and @placeholder='Enter your search keyword here...']")
	WebElement SearchSanstha;

	@FindBy(xpath = "//button[contains(@class, 'form-control') and contains(@class, 'searchbutton') and text()='Search']")
	WebElement Search;

	@FindBy(xpath = "//img[contains(@class, 'exloreorglocimg')]")
	WebElement ClickOnSanstha;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement LogOut;
	

	public SansthasMainPagePOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void EnterSanstaName() {
		sendKeys(SearchSanstha,"JainEducation");
	}

	public void ClickOnSearch() {
		click(Search);
	}

	public void ClickOnSanstha() {
		click(ClickOnSanstha);
	}
	public void ClickOnLogout() {
		click(LogOut);
	}

}
