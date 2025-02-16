package object_library.bdd_steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import object_library.Pages.NaukriMainPage;

public class naukriMainStepDef {

    NaukriMainPage naukriMainPage= new NaukriMainPage();

    @Given("Validate my naukri page is displayed as {string}")
    public void naukriLandingPage(String name) throws InterruptedException {
        System.out.println("Entered 2nd step definition");
        naukriMainPage.validateProfile(name);
    }


    @Then("click on my profile header {string}")
    public void clickOnMyProfileHeader(String name) {
        naukriMainPage.clickProfileHeader(name);
    }


    @Then("click on update profile")
    public void clickOnUpdateProfile() {
        naukriMainPage.clickUpdateProfile();
    }


    @And("click on edit profile pencil")
    public void clickOnEditProfilePencil() {

    }

    @And("Select my location as {string} and save it")
    public void selectMyLocationAsAndSaveIt(String location) throws InterruptedException {
        naukriMainPage.selectLocation(location);
    }

    @And("Verify the location updated as {string}")
    public void verifyTheLocationUpdatedAs(String location) {
        naukriMainPage.validateMyLocation(location);
    }

    @And("verify last updated shows as {string}")
    public void verifyLastUpdatedShowsAs(String update) {
        naukriMainPage.validateLastUpdate(update);
    }
}
