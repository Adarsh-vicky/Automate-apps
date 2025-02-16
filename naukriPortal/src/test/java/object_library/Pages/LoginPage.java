package object_library.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class LoginPage extends AppBasePage {

    private final WebDriver driver;

    private final By loginButtonBy = By.xpath("//a[@title='Jobseeker Login']");
    private final By emailInputBy = By.xpath("//input[contains(@placeholder,'Email')]");
    private final By passwordInputBy = By.xpath("//input[contains(@placeholder,'password')]");
    private final By submitButtonBy = By.xpath("//button[text()='Login']");
    private final By googleSignInBy= By.xpath("//span[text()='Sign in with Google']");
    private final By emailFieldBy = By.xpath("//input[@type='email']"); // Google email input
    private final By nextButtonBy = By.xpath("//span[text()='Next']");
    private final By passwordFieldBy = By.xpath("//input[@type='password']");
    private final By cookie= By.xpath("//span[text()='Got it']");
    private final By naukriLogo= By.xpath("//div[@class='nI-gNb-header__wrapper']//a[contains(@alt,'Naukri Logo')]");

    // Constructor
    public LoginPage() {
        super(); // Calls AppBasePage constructor (which initializes WebDriver)
        this.driver = getDriver(); // Assigns WebDriver
        System.out.println("LoginPage Constructor: WebDriver assigned.");
    }

    public void launchApp() throws InterruptedException {
        System.out.println("Launching Naukri application...");
        driver.get("https://www.naukri.com/");
        Thread.sleep(3000);
        try {
            clickOn(cookie);
            System.out.println("Accept cookie");
        } catch (NoSuchElementException e) {
            System.out.println("No cookies popup found, continuing...");
        }
    }

    public void AppLogin(String email, String key) {
        System.out.println("Clicking login button to open the drawer...");
        clickOn(loginButtonBy);
        System.out.println("Finding email input field dynamically...");
        sendKeyIn(emailInputBy,email);
        System.out.println("Finding password input field dynamically...");
        sendKeyIn(passwordInputBy,key);
        System.out.println("Finding and clicking the login button dynamically...");
        clickOn(submitButtonBy);
    }

    public void GoogleLogin(String email, String key) {
        System.out.println("Clicking login button to open the drawer...");
        getWait().until(ExpectedConditions.elementToBeClickable(driver.findElement(loginButtonBy))).click();
        System.out.println("Clicking sign in using google");
        clickOn(googleSignInBy);
        System.out.println("Waiting for Google login pop-up...");
        switchToNewWindow();  // Switch to Google login window

        System.out.println("Entering Google email...");
        sendKeyIn(emailFieldBy,email);
        clickOn(nextButtonBy);

        System.out.println("Entering Google password...");
        sendKeyIn(passwordFieldBy, key);
        clickOn(nextButtonBy);

        System.out.println("Switching back to Naukri...");
        switchToMainWindow(); // Switch back to Naukri
        getWait().until(ExpectedConditions.visibilityOfElementLocated(naukriLogo));
        System.out.println("Logged in to Naukri..."+ getDriver().getCurrentUrl());
    }
}
