package com.jaindirect.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.jaindirect.testCases.BaseClass;

public class CommonUtils extends BaseClass {

	public void LogIn(WebElement ele) {
		// Wait for the page to fully load
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waitForAngular(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
		
        wait.until((ExpectedCondition<Boolean>) wd -> {
            return (Boolean) js.executeScript(
                "return window.angular === undefined || angular.element(document).injector().get('$http').pendingRequests.length === 0"
            );
        });
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	public void click(WebElement ele) {
		waitForAngular(driver);
		ele.click();
	}
	
	public void sendKeys(WebElement ele,String text) {
		waitForAngular(driver);
		ele.clear();
		ele.sendKeys(text);
	}

}
