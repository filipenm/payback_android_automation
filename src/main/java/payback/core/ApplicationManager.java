package payback.core;

import payback.core.config.Configuration;
import payback.core.helpers.*;

public class ApplicationManager {
    private final static ApplicationManager INSTANCE = new ApplicationManager();
    private Configuration configuration;
    private AppiumDriverHelper appiumDriverHelper;
    private PropertiesHelper propertiesHelper;
    private AndroidHelper androidHelper;
    private WaitHelper waitHelper;


    public static ApplicationManager get() {
        return INSTANCE;
    }

    public Configuration config() {
        if (configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    public LogHelper log() {
        return new LogHelper();
    }

    public AppiumDriverHelper appium() {
        if (appiumDriverHelper == null)
            appiumDriverHelper = new AppiumDriverHelper();
        return appiumDriverHelper;
    }

    public PropertiesHelper properties() {
        if (propertiesHelper == null)
            propertiesHelper = new PropertiesHelper();
        return propertiesHelper;
    }

    public AndroidHelper android() {
        if (androidHelper == null)
            androidHelper = new AndroidHelper();
        return androidHelper;
    }

    public WaitHelper waits() {
        if (waitHelper == null)
            waitHelper = new WaitHelper();
        return waitHelper;
    }
}
