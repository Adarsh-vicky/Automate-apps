package object_library.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class AppBasePage {

    public By chatBot= By.xpath("//*[@class='chatbot_DrawerContentWrapper']");
    public By closeChatBot=By.xpath("//*[@class='crossIcon chatBot chatBot-ic-cross']");

    protected static WebDriver driver;  // Static WebDriver to maintain session
    protected static WebDriverWait wait;  // Global wait instance

    // Constructor to initialize WebDriver & WebDriverWait only once
    public AppBasePage() {
        if (driver == null) {  // Prevent multiple instances
            System.out.println("Initializing WebDriver in Base Class...");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection
            options.addArguments("--disable-extensions"); // Prevent Google security extensions from blocking login
            options.addArguments("--start-maximized"); // Ensure login window is visible
            options.addArguments("--ignore-certificate-errors");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Set Explicit Wait
            System.out.println("WebDriver initialized successfully.");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;  // Provide global WebDriverWait instance
    }

    public WebElement waitToDisplay(By element){
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        //System.out.println("Element is displayed:"+ driver.findElement(element).getText());
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset after closing
            wait = null;  // Reset wait
            System.out.println("Browser closed.");
        }
    }

    public void switchToNewWindow(){
        String mainWindow= driver.getWindowHandle();
        Set<String> windows= driver.getWindowHandles();
        for (String window: windows){
            if(!window.equalsIgnoreCase(mainWindow)){
                driver.switchTo().window(window);
                System.out.println("Switched to New window");
                break;
            }
        }
    }

    public void switchToMainWindow(){
        String mainWindow= driver.getWindowHandles().iterator().next();
        driver.switchTo().window(mainWindow);
        System.out.println("Switched back to Naukri Main window.");
    }

    public void clickOn(By by){
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public void sendKeyIn(By by, String key){
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(key);
    }

    public void closeChatBot(){
        try{
            if (driver.findElement(chatBot).isDisplayed()){
                clickOn(closeChatBot);
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("No cookies popup found, continuing...");
        }
    }

    public void selectFromList(By element, String location){
        List<WebElement> loc= driver.findElements(element);
        for(WebElement ele: loc){
            if(ele.getText().equalsIgnoreCase(location)){
                ele.click();
            }
            else {
                System.out.println("Wrong location selection- "+ ele.getText());
            }
        }
    }

    public String getText(By loc){
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(loc));
        return element.getText();
    }

    public void clickJS(By loc){
        WebElement element = driver.findElement(loc); // Change locator as needed
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Click using JavaScriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
