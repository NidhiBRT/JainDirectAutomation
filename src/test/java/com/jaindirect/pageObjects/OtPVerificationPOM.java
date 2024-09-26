package com.jaindirect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jaindirect.utilities.CommonUtils;

public class OtPVerificationPOM extends CommonUtils{
   
	@FindBy(id = "otp")  // Prefer using a unique ID instead of XPath
    private WebElement enterSecurityCode;

    @FindBy(xpath = "//button[@type='submit' and @class='btn form-control mt-4 log_button']")  
    private WebElement SubmitOTP;
	
    @FindBy(xpath="//p[contains(text(), 'Wrong Otp.')]")
    WebElement WrongOTP;
    
    @FindBy(xpath="//a[contains(@class, 'resend_link') and contains(@class, 'mx-2')]")
    WebElement ResendOtp;
    
    
    public OtPVerificationPOM(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
public void Resendotp() {
	click(ResendOtp);
	
}
    public void sendOTP(String otp) {
    	sendKeys(enterSecurityCode, otp);
    }
    public String WrongOTPText() {
    	return WrongOTP.getText();
    }
    
    public void sendInvalidOTP() {
    	sendKeys(enterSecurityCode, "1200");
    }


	public void ClickOnVerify() {
		click(SubmitOTP);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
