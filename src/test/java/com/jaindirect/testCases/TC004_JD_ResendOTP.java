package com.jaindirect.testCases;

import java.io.IOException;
import java.time.Duration;

import javax.mail.MessagingException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jaindirect.pageObjects.LogInPagePOM;
import com.jaindirect.pageObjects.OtPVerificationPOM;

public class TC004_JD_ResendOTP extends BaseClass {
	@Test
	public void resendOTP() throws InterruptedException, IOException, MessagingException
	{
        logger.info("Base URL is launched");
		LogInPagePOM lg= new LogInPagePOM(driver);
		lg.clickOnLogin();
		lg.SetEmail(userEmail);
		logger.info("User Email is provided");
		scroll(0, 700);
		 lg.ClickOnCheckBox();
		 lg.ClickOnGetSecurityCode();
		 logger.info("Sending the otp to userEmail");
		 OtPVerificationPOM OV= new OtPVerificationPOM(driver);
		 scroll(0, 700);
		 OV.Resendotp();
		 logger.info("Verifing the pop message");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Vue-Toastification__toast-body")));

	     // Retrieve and validate the notification message
	        String actualMessage = notification.getText();
	        String expectedMessage = "Otp sent successfully !";
   
	    Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match expected value.");
	    captureScreen(driver,"OTP is sent");
	    logger.info("Verified pop message");
	    
		 String otp=getOtp();
		 System.out.println(otp);
		 OV.sendOTP(otp);
		 logger.info("Entering OTP");
		 scroll(0, 800);
		 OV.ClickOnVerify();
		 
		 logger.info("opening home page");
		 String ztitle =driver.getTitle();
		System.out.println(ztitle);
		Assert.assertEquals(ztitle, "Dashboard - JainDirect");
		captureScreen(driver,"loginTest");
	        
	}
}
