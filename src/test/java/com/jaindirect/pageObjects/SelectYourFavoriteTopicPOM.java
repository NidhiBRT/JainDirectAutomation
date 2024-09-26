package com.jaindirect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectYourFavoriteTopicPOM {
	
	 private WebDriver driver;
	    // Constructor
	    public SelectYourFavoriteTopicPOM(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

 @FindBy(xpath = "//label[@for='card5' and contains(@class, 'radio_card2')]")
	    WebElement SelectFavorite;

 @FindBy(xpath ="//button[@type='button' and contains(@class, 'btn section_topic_btn mt-3')]") 
		WebElement SaveAndCountinue;  
 
	    //action class
	    public void ClickOnFavorite() {
	    	SelectFavorite.click();
	    }
	    public void ClickOnCountinue() {
	    	SaveAndCountinue.click();
	    }


}
