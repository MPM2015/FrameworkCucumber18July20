package testRunner;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbleTrace.helper.CheckPoint;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;



@CucumberOptions(
		plugin= {"pretty", "html:target/cucumber"},
		features= {"FeatureFile"},
		glue= {"stepDefinition"},
		monochrome = true,
		dryRun = false,
		strict = true		
		)

public class CucumberRunner {
	
	public TestNGCucumberRunner testNGCucumberRunner;
	public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());    
       	       
    }
			

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
    	testNGCucumberRunner.finish();
    	  	
      }
    
   
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

}
