package stepDefinition;

import org.openqa.selenium.support.PageFactory;
import AbleTrace.factories.BrowserFactory;
import AbleTrace.factories.ObjectProviderFactory;
import AbleTrace.helper.CheckPoint;
import AbleTrace.helper.Utility;
import AbleTrace.pages.AbleTraceLoginPage;
import AbleTrace.pages.AbleTraceLogoutPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import testRunner.CucumberRunner;




public class LoginSteps extends CucumberRunner {
	
	AbleTraceLoginPage login;
	AbleTraceLogoutPage logout;
	
	@Given("^I Navigate to the login page$")
    public void i_navigate_to_the_login_page() throws Throwable {
		 System.out.println("Log:INFO: Setting up Browser and Application");			
			driver = BrowserFactory.getApplication(ObjectProviderFactory.getConfig().getValue("Browser"),
					ObjectProviderFactory.getConfig().getValue("QAEnv"));	
			System.out.println("Log:INFO: Browser and Application is ready");
			
		login=PageFactory.initElements(driver, AbleTraceLoginPage.class);
		logout = PageFactory.initElements(driver, AbleTraceLogoutPage.class);
					
		boolean PartialTextResult=Utility.getPartialTextMatchFromCurrentURL(driver,"login");		
		CheckPoint.markFinal("verifyBeforeLoginDetails", PartialTextResult, "URL Verification in the Selected Text is Done");
        
    }

	@When("^I Enter username,password and click on Sign-In$")
    public void i_enter_usernamepassword_and_click_on_signin() throws Throwable {
		login.loginToApplication(ObjectProviderFactory.getExcel().getCellData("ableTrace", 1, 0), 
					ObjectProviderFactory.getExcel().getCellData("ableTrace", 1, 1));
			Thread.sleep(10000);
       
    }

	@Then("^I navigate to home page and Click logout and ok button$")
    public void i_navigate_to_home_page_and_click_logout_and_ok_button() throws Throwable {
        boolean PartialTextResult=Utility.getPartialTextMatchFromCurrentURL(driver,"SuperAdmin");
		CheckPoint.mark("verifyLoginUserDetails", PartialTextResult, "URL Verification in the Selected Text is Done ");
		
		boolean TitleResult=Utility.verifyTitle(driver,"Traceabilities");		
		CheckPoint.markFinal("verifyLoginUserDetails", TitleResult, "Title Verification Done");
		
		logout.logoutfromApplication();
		
		System.out.println("Log:INFO: Terminating Browser");
		BrowserFactory.closeApplication(driver);		
		System.out.println("Log:INFO: Browser Terminated");
    }
}
