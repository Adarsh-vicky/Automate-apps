package object_library.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NaukriMainPage extends AppBasePage{

    public By profileHeader= By.xpath("//img[@alt='naukri user profile img']");
    public By updateProfile= By.xpath("//a[text()='View & Update Profile']");
    public By editBasicDetails= By.xpath("//*[contains(@data-ga-track,'Basic Details')]");
    public By selectIndia= By.xpath("//input[@id='location-country-india']//following-sibling::label");
    public By selectCurrentLocation= By.id("locationSugg");
    public By selectMyLocation= By.xpath("//label[text()='Popular locations']/../ul//li");
    public By clickSave= By.xpath("//*[@id='saveBasicDetailsBtn']");
    public By myLocation= By.xpath("//span[@name='Location']");
    public By last_updated= By.xpath("//span[text()='Profile last updated -  ']//span");

    public By bannerName(String name){
        return By.xpath("//div[@class='nI-gNb-drawer__expand']//div[@title='"+ name +"']");
    }

    public String getProfileTitle(String name) {
        By profile=By.xpath("//div[text()='" + name + "']");
        return driver.findElement(profile).getText();
    }
    private final WebDriver driver;

    public NaukriMainPage(){
        super();
        this.driver = getDriver(); // Assigns WebDriver
        System.out.println("Naukri Main Page Constructor: WebDriver assigned.");
    }

    public void validateProfile(String name) throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(name,getProfileTitle(name));
    }

    public void clickProfileHeader(String name){
        clickOn(profileHeader);
        waitToDisplay(bannerName(name));
    }

    public void clickUpdateProfile(){
        clickOn(updateProfile);
    }

    public void selectLocation(String location) throws InterruptedException {
        clickOn(editBasicDetails);
        Thread.sleep(5000);
        clickJS(selectIndia);
        driver.findElement(selectCurrentLocation).clear();
        Thread.sleep(3000);
        selectFromList(selectMyLocation, location);
        clickOn(clickSave);
        Thread.sleep(5000);
    }

    public void validateMyLocation(String location){
        String updated_loc= getText(myLocation);

        if (updated_loc != null && !updated_loc.isEmpty()) {
            String[] parts = updated_loc.split(",");
            String firstElement = (parts.length > 0) ? parts[0] : "";
            System.out.println("Updated Location: " + firstElement);
            Assert.assertEquals(firstElement,location);
            System.out.println("Location is updated");
        }

        else
        {
            throw new RuntimeException("Incorrect Updated as:"+ updated_loc+", Please check and update is manually");
        }
    }

    public void validateLastUpdate(String update){
        String last_update= getText(last_updated);
        if(last_update != null && !last_update.isEmpty()){
            Assert.assertEquals(last_update,update);
            System.out.println("Last Updated is : Today");
        }
        else{
            throw new RuntimeException("Naukri profile failed to update, try again......");
        }
    }
}
