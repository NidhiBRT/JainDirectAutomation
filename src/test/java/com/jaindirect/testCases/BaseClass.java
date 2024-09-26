package com.jaindirect.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jaindirect.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationURL();
	public String userEmail = readconfig.getUsername();
	public static WebDriver driver;

	protected static Logger logger = null;

	@SuppressWarnings("deprecation")
	@BeforeClass
	@Parameters({ "browser" })

	public void setup(String browser) {
		logger = Logger.getLogger("JainDIrect");
		PropertyConfigurator.configure("Log4j.properties");

		if (browser.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		 driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(baseURL);
		
	}

//	
//	@AfterClass
//	public void tearDown()
//	{
//		driver.quit();
//	}
//	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

	/*
	 * public String getOtp() throws MessagingException, IOException {
	 * 
	 * // Set up email properties Properties properties = new Properties();
	 * properties.put("mail.store.protocol", "imaps");
	 * properties.put("mail.imap.host", "imap.gmail.com"); // Replace with your
	 * email provider's IMAP host properties.put("mail.imap.port", "993");
	 * 
	 * // Establish a mail session Session session =
	 * Session.getInstance(properties); Store store = session.getStore();
	 * store.connect("imap.gmail.com", "testbluerose2@gmail.com",
	 * "zhud cwjy gjvi cwuo"); Folder inbox = store.getFolder("INBOX");
	 * inbox.open(Folder.READ_ONLY);
	 * 
	 * // Fetch messages Message[] messages = inbox.getMessages(); String otp =
	 * null; try { for (int i = messages.length - 1; i >= 0; i--) { Message message
	 * = messages[i]; if (message.getSubject().contains("Send Otp Mail")) { String
	 * content = getTextFromMessage(message); otp = extractOtpFromContent(content);
	 * System.out.println("OTP: " + otp); break; } } } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return otp; }
	 */

	public String getOtp() throws MessagingException, IOException {
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Set up email properties
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.host", "imap.gmail.com"); // Replace with your email provider's IMAP host
		properties.put("mail.imap.port", "993");

		// Establish a mail session
		Session session = Session.getInstance(properties);
		Store store = session.getStore();
		store.connect("imap.gmail.com", "testbluerose2@gmail.com", "zhud cwjy gjvi cwuo");
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// Fetch messages
		Message[] messages = inbox.getMessages();
		String otp = null;
		try {
			for (int i = messages.length - 1; i >= 0; i--) {
				Message message = messages[i];
				if (message.getSubject().contains("Send Otp Mail")) {
					String content = getTextFromMessage(message);
					otp = extractOtpFromContent(content);
					System.out.println("OTP: " + otp);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return otp;
	}

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		Object content = message.getContent();
		if (content instanceof String) {
			// Simple text email
			return (String) content;
		} else if (content instanceof Multipart) {
			// Multipart email
			Multipart multipart = (Multipart) content;
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					return (String) bodyPart.getContent();
				}
			}
		}
		return "";
	}

	private static String extractOtpFromContent(String content) {
		// Simple regex to find a 4-digit OTP
		String otpPattern = "\\b\\d{4}\\b";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(otpPattern);
		java.util.regex.Matcher matcher = pattern.matcher(content);
		return matcher.find() ? matcher.group() : "OTP not found";
	}

	public void scroll(int x, int y) throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ");");
	}
}
