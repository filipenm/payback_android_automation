package payback;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import payback.core.ApplicationManager;

public class TestBase {
    protected ApplicationManager app = ApplicationManager.get();

    private static final By login = By.id("welcome_loginbutton");

    @Before
    public void beforeSuite() {
        app.config().init();
        app.config().runDriver();
        app.config().installAndLaunchApp();
        app.waits().waitForVisibility(login);
    }

    @After
    public void afterClass() {
        app.config().closeApp();
        app.config().stopDriver();
    }
}
