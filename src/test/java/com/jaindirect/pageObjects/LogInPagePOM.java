package com.jaindirect.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jaindirect.utilities.CommonUtils;
public class LogInPagePOM extends CommonUtils {

	@FindBy(xpath = "//a[contains(@class, 'nav-link') and contains(text(), 'Login')]")
	private WebElement logInButton;

	// locators
	@FindBy(xpath = "//a[contains(@class, 'nav-link') and contains(text(), 'Login')]")
	WebElement LogIn;

	@FindBy(xpath = "//input[@id='login_number']")
	WebElement userNumber;

	@FindBy(xpath = "//input[@id='login_email']")
	WebElement EmailID;

	@FindBy(xpath = "//input[@id='exampleCheck1']")
	WebElement checkBox;

	@FindBy(xpath = "//Button[@class='btn form-control mt-4 log_button']")
	WebElement GetSecurityCode;

	// Action Class
	public void ClickONLogInButton() {
		click(LogIn);
	}

	public void SetUserNumber(String User) {
		sendKeys(userNumber, User);
	}

	public void SetEmail(String userEmail) {
		sendKeys(EmailID, userEmail);
	}

	public void ClickOnCheckBox() {
		click(checkBox);
	}

	public void ClickOnGetSecurityCode() {
		click(GetSecurityCode);
	}

	WebDriverWait wait;

	public LogInPagePOM(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogInButton() {
		return logInButton;
	}

	public void clickOnLogin() {
		click(getLogInButton());
	}
}
