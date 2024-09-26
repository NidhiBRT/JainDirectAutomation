package com.jaindirect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectCategoriesPOM {
	  private WebDriver driver;
	    // Constructor
	    public SelectCategoriesPOM(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(xpath="//button[@id='saveButton']")  // Prefer using a unique ID instead of XPath
	    private WebElement SubmitCat;

	   

	    // Action Class
	    public void submitCategories(String otp) {
	    	SubmitCat.click();
	    }


}
