package AbleTrace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import AbleTrace.helper.Utility;


public class AbleTraceLoginPage {

	WebDriver driver;
	
	public AbleTraceLoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	By emailaddress = By.xpath("//Input[@placeholder='Email Id']");
	By password = By.xpath("//Input[@placeholder='Password']");	
	By signinbutton = By.xpath("//span[text()='SIGN IN']");
	
	public void loginToApplication(String email_details, String password_details) {
		
		Utility.waitForWebElement(driver, emailaddress).sendKeys(email_details);
		Utility.waitForWebElement(driver, password).sendKeys(password_details);
		Utility.waitForWebElement(driver, signinbutton).click();
			
	}	
	
}
