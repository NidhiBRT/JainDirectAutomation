package com.jaindirect.testCases;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jaindirect.pageObjects.AdminSansthaPOM;
import com.jaindirect.pageObjects.LogInPagePOM;
import com.jaindirect.pageObjects.MainPageReadPOM;
import com.jaindirect.pageObjects.OtPVerificationPOM;

public class TC005_JD_AddEventToSanstha extends BaseClass {
	@Test
	public void AddNewsAndDelete() throws IOException, InterruptedException, MessagingException {
	
	logger.info("Base URL is launched");
	LogInPagePOM lg= new LogInPagePOM(driver);
	lg.clickOnLogin();
	lg.SetEmail(userEmail);
	logger.info("User Email is provided");
	scroll(0, 700);
	 lg.ClickOnCheckBox();
	 lg.ClickOnGetSecurityCode();
	 logger.info("Sending the otp to userEmail");
	 
	//load the otp verification page
	 OtPVerificationPOM OV= new OtPVerificationPOM(driver);
	 String otp=getOtp();
	 System.out.println(otp);
	 OV.sendOTP(otp);
	 scroll(0, 700);
	 logger.info("Entering OTP");
	 OV.ClickOnVerify();
	String ztitle =driver.getTitle();
	System.out.println(ztitle);
	Assert.assertEquals(ztitle, "Dashboard - JainDirect");
	captureScreen(driver,"loginTest");
	
	MainPageReadPOM MPRP=new MainPageReadPOM(driver);
	MPRP.ClickOnAdminSanstha();
	logger.info("Sanstha admin page is loaded");
	String AdminSansthaHeader =driver.getTitle();
	Assert.assertEquals(AdminSansthaHeader, "Sanstha Dashboard - JainDirect");
	captureScreen(driver,"SansthaAdminDashboard");
	logger.info("Sanstha Dashboardpage is added successfully");
	
	AdminSansthaPOM ADMS=new AdminSansthaPOM(driver);
	ADMS.ClickOnFeed();
	logger.info("Naviagte to NewsFeed");
	 WebElement buttonVisible = ADMS.gepost();
      // Retrieve the class attribute
     String actualClass = buttonVisible.getAttribute("class");
    // Expected class
      String expectedClass = "create_post_btn ms-3 mb-1";
     // Assert that the actual class matches the expected class
      Assert.assertEquals(actualClass, expectedClass, "The button class is not as expected");
      captureScreen(driver,"SansthaAdminButtonVissible");

	}	
}
