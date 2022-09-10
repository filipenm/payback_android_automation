package payback.step_definitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class EntryPageStepDefinitions {

    private final By login = By.id("welcome_loginbutton");
    protected ApplicationManager app = ApplicationManager.get();

    @Given("I am at the Login page")
    public void i_am_at_the_login_page() {
        app.appium().tap(login);
    }
}
