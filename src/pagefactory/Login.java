package pagefactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	WebDriver driver;
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	@FindBy(name="userName")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="login")
	WebElement signIn;
	
	/* initElements method is being used 
	 * for initializing the web elements */
	public Login(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String username){
		logger.info("Entering '"+username+"' in the 'UserName' edit field");
		highlightElement(userName);
		userName.clear();
		userName.sendKeys(username);
		logger.info("Entered '"+username+"' in the 'UserName' edit field");
	}
	
	public void setPassword(String pwd){
		logger.info("Entering '"+pwd+"' in the 'Password' edit field");
		highlightElement(password);
		password.clear();
		password.sendKeys(pwd);
		logger.info("Entered '"+pwd+"' in the 'Password' edit field");
	}
	
	public void clickOnSignIn(){
		logger.info("Clicking on 'Sign-In' Button");
		highlightElement(signIn);
		signIn.click();
		logger.info("Clicked on 'Sign-In' Button");
	}
	
	
	public void loginToFR(String username, String pwd){
		// Enter UserName
		setUserName(username);
		// Enter Password
		setPassword(pwd);
		// Click on 'Sign-In' button
		clickOnSignIn();		
	}
	
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}

}
