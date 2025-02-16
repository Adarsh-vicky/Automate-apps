package object_library.bdd_steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import object_library.Pages.LoginPage;

public class LoginStepdef {

    LoginPage loginPage;

    public LoginStepdef() {
    loginPage= new LoginPage();
    }

    @Given("I launch naukri application")
    public void iLaunchNaukriApplication() throws InterruptedException {
        loginPage.launchApp();
    }

    @Given("I have logged into Naukri app with my credentials with email {string} and password {string}")
    public void iHaveLoggedIntoNaukriAppWithMyCredentialsWithEmailAndPassword(String email, String password) {
        loginPage.AppLogin(email, password);
    }


    @And("I have logged into Naukri app with my Google credentials with email {string} and password {string}")
    public void iHaveLoggedIntoNaukriAppWithMyGoogleCredentialsWithEmailAndPassword(String email, String password) {
        loginPage.GoogleLogin(email,password);
    }

    @After
    public void tearDown() {
        loginPage.closeBrowser();
        }
}
