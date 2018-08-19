package testcases;


import java.io.IOException;
import java.net.InetAddress;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import excelInputAndOutput.ExcelReader;
import pagefactory.BookAFlight;
import pagefactory.FindAFlight;
import pagefactory.FlightConfirmation;
import pagefactory.Login;
import pagefactory.ReserveAFlight;
import utility.Constant;

public class ExecuteTestThroughTestNG {
	public static WebDriver driver;
	ExcelReader reader = new ExcelReader();
	ExtentReports extent;
	ExtentTest test;
	ExtentHtmlReporter htmlReporter;
	
	@SuppressWarnings("deprecation")
	@Parameters(value="browser")
	@BeforeTest
	public void setUp(String browserType) throws IOException {
		htmlReporter = new ExtentHtmlReporter(Constant.reportPath);
		extent = new ExtentReports();
		
		extent.setSystemInfo("Project Name", "Flight Reservation");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "Production");
		InetAddress myHost = InetAddress.getLocalHost();
		extent.setSystemInfo("Host Name", myHost.getHostName());
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		htmlReporter.config().setDocumentTitle("Test Execution Report : Flight Reservation");
		htmlReporter.config().setReportName("Regression Cycle");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent.attachReporter(htmlReporter);
		
		if(browserType.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPath);
			driver = new ChromeDriver();
		}else if(browserType.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", Constant.firefoxDriverPath);
			driver = new FirefoxDriver();
		}else{
			System.setProperty("webdriver.ie.driver", Constant.ieDriverPath);
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capability.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://newtours.demoaut.com");
			driver = new InternetExplorerDriver(capability);
		}
	}

	
	@Test(priority=0)
	public void launchFlightReservationApplication() throws IOException{
		test = extent.createTest("launchFlightReservationApplication");
		String URL = reader.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 0);
		String expTitle = reader.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 1);
		// Launch Flight Reservation Application
		driver.get(URL);
		// Assert Page Title
		Assert.assertEquals(driver.getTitle(), expTitle);
		test.log(Status.PASS, MarkupHelper.createLabel("Flight Reservation Application got invoked successfully...", ExtentColor.GREEN));
		}
	
	@Test(priority=1)
	public void loginToFlightReservation() throws IOException{
		test = extent.createTest("loginToFlightReservation");
		String username = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 0);
		String pwd = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 1);
		String expTitle = reader.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 2);
		
		Login loginToFR = new Login(driver);
		
		loginToFR.loginToFR(username, pwd);
		
		Assert.assertEquals(driver.getTitle(), expTitle);
		test.log(Status.PASS, MarkupHelper.createLabel("Flight Reservation Login Successful...", ExtentColor.GREEN));
	}
	
	@Test(priority=2)
	public void bookATicket() throws IOException{
		test = extent.createTest("bookATicket");
		String flyFrom = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 0);
		String flyTo= reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 1);
		String airlinePreference = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 2);
		String firstname = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 3);
		String lastname = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 4);
		String ccNumber = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 5);
		String expText = reader.getCellData(Constant.filePath, Constant.fileName, "BookATicket", 1, 6);
		
		FindAFlight findaflight = new FindAFlight(driver);
		ReserveAFlight reserveaflight = new ReserveAFlight(driver);
		BookAFlight bookaflight = new BookAFlight(driver);
		FlightConfirmation flightconfirmation = new FlightConfirmation(driver);
		
		// Select Trip Type
		findaflight.clickOnOneWayTrip();
		
		// Select Fly From
		findaflight.selectDeprtureFrom(flyFrom);
		
		// Select Fly To
		findaflight.selectToPort(flyTo);
		
		// Select Class preference
		findaflight.selectClass();
		
		// Select Airline Preference
		findaflight.selectAirLinePreference(airlinePreference);
		
		// Click on 'Find Flight'
		findaflight.clickOnfindFlights();
		
		// Click on 'Reserve Flights'
		reserveaflight.clickOnReserveFlights();
		
		// Enter First Name
		bookaflight.enterFirstName(firstname);
		
		// Enter LastName
		bookaflight.enterLastName(lastname);
		
		// Enter Credit Card Number
		bookaflight.enterCreditCardNumber(ccNumber);
		
		// Click on 'Buy Flights'
		bookaflight.clickOnBuyFlights();
		
		
		// Verify Confirmation Text
		Assert.assertTrue(flightconfirmation.verifyConfirmationTextOnPage(expText));
		test.log(Status.PASS, MarkupHelper.createLabel("Ticket has been booked successfully...", ExtentColor.GREEN));	
	}
	
	@Test(priority=3)
	public void testFail(){
		test = extent.createTest("testFail");
		Assert.assertTrue(false);
	}
	
	@Test(priority=4)
	public void testSkip(){
		test = extent.createTest("testSkip");
		throw new SkipException("Not ready to be executed");
	}
	@AfterMethod
	public void generateReport(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" : got failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" : got failed due to "+result.getThrowable(), ExtentColor.RED));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" : got skipped", ExtentColor.YELLOW));
		}
		
	}
	
	@AfterTest
	public void tesrDown(){
		driver.quit();
		extent.flush();
	}
}
