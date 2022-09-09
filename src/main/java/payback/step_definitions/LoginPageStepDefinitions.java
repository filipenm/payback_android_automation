package payback.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class LoginPageStepDefinitions {

    protected ApplicationManager app = ApplicationManager.get();

    private final By numberField = By.id("txtLoginCheckCardNumber");
    private final By proceed = By.id("progressbutton_btn");
    private final By passwordField = By.id("login_password_field");
    private final By finishLogin = By.id("progressbutton_btn");

    @When("I log in with account number {string} and password {string}")
    public void i_log_in_with_account_number_and_password(String accountNumber, String password) {
        app.log().info("Account number = " + accountNumber);

        app.waits().waitForVisibility(numberField);
        app.appium().type(numberField, accountNumber);
        app.appium().tap(proceed);

        app.waits().waitForVisibility(passwordField);
        app.appium().type(passwordField, password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        app.log().info();
        app.appium().tap(finishLogin);
    }
}
