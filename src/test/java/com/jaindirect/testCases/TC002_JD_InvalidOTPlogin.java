package com.jaindirect.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaindirect.pageObjects.LogInPagePOM;
import com.jaindirect.pageObjects.LoginPage;
import com.jaindirect.pageObjects.OtPVerificationPOM;
import com.jaindirect.utilities.XLUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002_JD_InvalidOTPlogin extends BaseClass
{

	@Test
	public void loginWithInvalidOTP() throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Base URL is launched");
		LogInPagePOM lg= new LogInPagePOM(driver);
		lg.clickOnLogin();
		lg.SetEmail(userEmail);
		logger.info("User Email is provided");
		scroll(0, 700);
		 lg.ClickOnCheckBox();
		 lg.ClickOnGetSecurityCode();
		 logger.info("Sending the otp to userEmail");
		//load the otp verification page.
		 OtPVerificationPOM OV= new OtPVerificationPOM(driver);
		 OV.sendInvalidOTP();
		 scroll(0, 700);
		 logger.info("Entering OTP");
		 OV.ClickOnVerify();
		 boolean res=driver.getPageSource().contains("Wrong Otp.");
			
			if(res==true)
			{
				Assert.assertTrue(true);
				
				logger.info("test case passed....");	
			}
			else
			{
				logger.info("test case failed....");
				captureScreen(driver,"Wrong OTP");
				Assert.assertTrue(false);
			}
		 
		 logger.info("Wrong OTP displyed");
		 
	}
}
		
		