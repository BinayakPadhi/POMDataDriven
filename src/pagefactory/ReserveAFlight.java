package pagefactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import freemarker.log.Logger;

public class ReserveAFlight {
	WebDriver driver;
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	@FindBy(name="reserveFlights")
	WebElement reserveFlights;
	
	
	public ReserveAFlight(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnReserveFlights(){
		logger.info("Clicking on 'CONTINUE' button");
		highlightElement(reserveFlights);
		reserveFlights.click();
		logger.info("Clicked on 'CONTINUE' button");
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}

}
