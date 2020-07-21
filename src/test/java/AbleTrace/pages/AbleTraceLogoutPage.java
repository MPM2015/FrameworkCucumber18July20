package AbleTrace.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import AbleTrace.helper.Utility;

//This is a page for logout

public class AbleTraceLogoutPage {

	WebDriver driver;

	public AbleTraceLogoutPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	By logoutText = By.xpath("//*[contains(text(),' Logout ')]");
	

	public void logoutfromApplication() {
		
		Utility.waitForWebElement(driver, logoutText).click();
		Utility.acceptAlert(driver);	
		

	}

}
