package payback.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import payback.core.ApplicationManager;

public class LoginPageStepDefinitions {
    protected ApplicationManager app = ApplicationManager.get();

    @When("I fill the account email text box with value {string}")
    public void i_fill_the_account_box_with_value(String email) {
        app.log().info("Email = " + email);
    }

    @And("I fill the password text box with value {string}")
    public void i_fill_the_password_box_with_value(String password) {
        app.log().info("Password = " + password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        app.log().info();
    }
}
