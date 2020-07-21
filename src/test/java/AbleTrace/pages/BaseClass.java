package AbleTrace.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import AbleTrace.factories.BrowserFactory;
import AbleTrace.factories.ObjectProviderFactory;
import AbleTrace.helper.CheckPoint;

public class BaseClass {
	
	/*public LoginPage login;
	public LogoutPage logout;*/
	public WebDriver driver;
	
	
	
	 @BeforeMethod
	    public void methodSetUp() {
	        CheckPoint.clearHashMap();
	    }
	 
	@AfterMethod
	public void tearDownReport(ITestResult result) {
		
		
		if(result.getStatus()==ITestResult.SUCCESS) {
			System.out.println("Log:INFO:Test Executed : Test Result is Passed");
		}
		else if (result.getStatus()==ITestResult.FAILURE) {
			System.out.println("Log:INFO:Test Executed : Test Result is Failed");
				
			}
			
		else if(result.getStatus()==ITestResult.SKIP) {
			System.out.println("Log:INFO:Test Executed : Test Result is Skipped");
			
		}
		
				
		
	}

	
	@BeforeClass
	public void setUp(){
		
		System.out.println("Log:INFO: Setting up Browser and Application");			
		driver = BrowserFactory.getApplication(ObjectProviderFactory.getConfig().getValue("Browser"),
				ObjectProviderFactory.getConfig().getValue("QAEnv"));		
			
		System.out.println("Log:INFO: Browser and Application is ready");
		
		
	/*	login = PageFactory.initElements(driver, LoginPage.class);
		logout = PageFactory.initElements(driver, LogoutPage.class);
		*/

	}
	
	
	
	@AfterClass
	public void tearDown() {
		System.out.println("Log:INFO: Terminating Browser");
		BrowserFactory.closeApplication(driver);		
		System.out.println("Log:INFO: Browser Terminated");
		
	}

	
	

}
