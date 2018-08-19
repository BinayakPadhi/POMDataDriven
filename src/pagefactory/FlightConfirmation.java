package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmation {
	
	WebDriver driver;
	
public FlightConfirmation(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}


public boolean verifyConfirmationTextOnPage(String expText){
	return driver.getPageSource().contains(expText);
}

}
