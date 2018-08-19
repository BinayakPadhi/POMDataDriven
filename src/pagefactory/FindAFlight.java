package pagefactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindAFlight {
	WebDriver driver;
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath="//input[@name='tripType'][@value='oneway']")
	WebElement oneWayTrip;
	@FindBy(name="fromPort")
	WebElement departureFrom;
	@FindBy(name="toPort")
	WebElement arrivalTo;
	@FindBy(xpath="//input[@name='servClass'][@value='First']")
	WebElement classPreference;
	@FindBy(name="airline")
	WebElement airLinePreference;
	@FindBy(name="findFlights")
	WebElement findFlights;
	
	
	public FindAFlight(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnOneWayTrip()
	{
		logger.info("Clicking on 'OneWay' Trip Type");
		highlightElement(oneWayTrip);
		oneWayTrip.click();
		logger.info("Clicked on 'OneWay' Trip Type");		
	}
	public void selectDeprtureFrom(String flyFrom)
	{
		logger.info("Selecting '" + flyFrom + "' from 'Departure From' Dropddown");	
		highlightElement(departureFrom);
		Select Departure = new Select (departureFrom);
		Departure.selectByVisibleText(flyFrom);		
		logger.info("Selected '" + flyFrom + "' 'Departure From' Dropdown");
	}
	
	public void selectToPort(String flyTo)
	{
		logger.info("Selecting '" + flyTo + "' from 'Arrival To' dropdown");
		highlightElement(arrivalTo);
		Select flyto = new Select (arrivalTo);
		flyto.selectByVisibleText(flyTo);
		logger.info("Selected '" + flyTo + "' from 'Arrival To' dropdown");
	}
	public void selectClass()
	{
		logger.info("Clicking on 'ClassPreference' Radio Button");
		highlightElement(classPreference);
		classPreference.click();
		logger.info("Clicked on 'ClassPreference' Radio Button");

	}
	public void selectAirLinePreference(String airLine)
	{
		logger.info("Selecting '" + airLine + "' from 'Airline Preference' dropdown");
		highlightElement(airLinePreference);
		Select airlinePref = new Select (airLinePreference);
		airlinePref.selectByVisibleText(airLine);
		logger.info("Selected '" + airLine + "' from 'Airline Preference' dropdown");
	}
	
	public void clickOnfindFlights()
	{
		logger.info("Clicking on 'CONTINUE' Button");
		highlightElement(findFlights);
		findFlights.click();
		logger.info("Clicked on 'CONTINUE' Button");
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}
	
}
