package com.jaindirect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jaindirect.utilities.CommonUtils;

public class AdminSansthaPOM extends CommonUtils {
	
	public AdminSansthaPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[@id='nav-dashboard-news-tab']")
			WebElement NewsFeed;
	@FindBy(xpath="//span[@class='sansthadashboard_tabs' and text()='Preview']")
	WebElement preview;
	@FindBy(xpath="//button[@class='create_post_btn ms-3 mb-1']")
	WebElement post;
	@FindBy(xpath="//button[@class='dashboard_news_btn']")
	WebElement AddNewsButton;


public void ClickOnFeed() {
	click(NewsFeed);
}
public void ClickOnPreview() {
	click(preview);
}
public WebElement gepost() {
	post.isDisplayed();
	return post;
	
}
public void ClickOnAddNews() {
	click(AddNewsButton);
}
}
