package com.jaindirect.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jaindirect.pageObjects.LogInPagePOM;
import com.jaindirect.pageObjects.MainPageReadPOM;
import com.jaindirect.pageObjects.OtPVerificationPOM;
import com.jaindirect.pageObjects.SansthasMainPagePOM;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TC001_JDLoginWithEmail extends BaseClass {
	 
		@Test
		public void LogIn()  throws MessagingException, IOException, InterruptedException
		{   
		      // Initialize WebDriver
            logger.info("Base URL is launched");
			LogInPagePOM lg= new LogInPagePOM(driver);
			lg.clickOnLogin();
			lg.SetEmail(userEmail);
			logger.info("User Email is provided");
			//driver.findElement(By.id("login_email")).sendKeys("testbluerose2@gmail.com");
			scroll(0, 700);
			 //Thread.sleep(10000);
			 lg.ClickOnCheckBox();
			 //Thread.sleep(5000);
			 lg.ClickOnGetSecurityCode();
			 logger.info("Sending the otp to userEmail");
			 ////Thread.sleep(10000);
			 
			//load the otp verification page.
			 
			 OtPVerificationPOM OV= new OtPVerificationPOM(driver);
			 String otp=getOtp();
			 System.out.println(otp);
			 OV.sendOTP(otp);
			 logger.info("Entering OTP");
			 scroll(0, 800);
			 ////Thread.sleep(10000);
			OV.ClickOnVerify();	

			System.out.println("***********************");	
		String ztitle =driver.getTitle();
		System.out.println(ztitle);
		Assert.assertEquals(ztitle, "Dashboard - JainDirect");
		captureScreen(driver,"loginTest");
			
		MainPageReadPOM MPRP= new MainPageReadPOM(driver);
		MPRP.ClickOnSansta(); 
		logger.info("Load the Sanstha main page");
		      
		if(driver.getTitle().equals("Sansthas - JainDirect"))
			{
			 Assert.assertTrue(true);
			logger.info("sanstha page loaded sucessfully");
				}
		else
		 {
			
			Assert.assertTrue(false);
			captureScreen(driver,"SansthaPage");
			logger.info("Santhas page load failure");
				}
		 SansthasMainPagePOM SMP=new SansthasMainPagePOM(driver);
		 SMP.EnterSanstaName(); 
		 SMP.ClickOnSearch();
		 Thread.sleep(10000);
		 SMP.ClickOnSanstha();
		 logger.info("validation started....");
		 Thread.sleep(10000);
		 boolean res=driver.getPageSource().contains("JainEducation");
			
		if(res==true)
			{
				Assert.assertTrue(true);
				captureScreen(driver,"Sansthadetails");
				logger.info("test case passed....");
			}
			else
			{
				logger.info("test case failed....");
				captureScreen(driver,"Sansthadetails");
				Assert.assertTrue(false);
			}
			SMP.ClickOnLogout();
			logger.info("LogOut From the application");
		}	
		
}
		     
