package pagefactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAFlight {
	
	WebDriver driver;
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	@FindBy(name="passFirst0")
	WebElement firstName;
	@FindBy(name="passLast0")
	WebElement lastName;
	@FindBy(name="creditnumber")
	WebElement crediNumber;
	@FindBy(name="buyFlights")
	WebElement buyFlights;
	
	
	public BookAFlight(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterFirstName(String firstname){
		logger.info("Entering '"+firstName+"' in the 'First Name' edit field.");
		highlightElement(firstName);
		firstName.sendKeys(firstname);
		logger.info("Entered '"+firstName+"' in the 'First Name' edit field.");
	}
	
	public void enterLastName(String lastname){
		logger.info("Entering '"+lastname+"' in the 'Last Name' edit field.");
		highlightElement(lastName);
		lastName.sendKeys(lastname);
		logger.info("Entered '"+lastname+"' in the 'Last Name' edit field.");
	}
	
	
	public void enterCreditCardNumber(String creditnumber){
		logger.info("Entering '"+creditnumber+"' in the 'Credit Number' edit field.");
		highlightElement(crediNumber);
		crediNumber.sendKeys(creditnumber);
		logger.info("Entered '"+creditnumber+"' in the 'Credit Number' edit field.");
	}
	
	
	public void clickOnBuyFlights(){
		logger.info("Clicking on 'SECURE PURCHASE' button");
		highlightElement(buyFlights);
		buyFlights.click();
		logger.info("Clicked on 'SECURE PURCHASE' button");
		
	}
	
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}

}
