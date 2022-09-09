package payback.core.helpers;

import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.By;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class LogHelper extends BaseHelper {
    private static final int LOG_DEPTH = 4;
    private static final String LOG_CONFIG_FILE_NAME = "log4j2.xml";
    private final org.apache.logging.log4j.core.Logger logger;

    public LogHelper() {
        loadConfigFile();
        logger = (Logger) LogManager.getLogger(getCallerClassPath());
    }

    private void loadConfigFile() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            File file = ResourceUtils.getFile("classpath:" + LOG_CONFIG_FILE_NAME);
            context.setConfigLocation(file.toURI());
        } catch (FileNotFoundException e) {
            throw new ApplicationManagerError("Config file '" + LOG_CONFIG_FILE_NAME + "' was not found in resource folders");
        }
    }

    private String parseLocator(Object object) {
        String locator = null;
        if (object instanceof MobileElement) {
            locator = object.toString();
            try {
                if (locator.contains("->"))
                    locator = "[By." + locator.split("->")[1];
                else if (locator.contains("Located by"))
                    locator = "{" + locator.split("\\(\\{")[1].replace("})", "}");
            } catch (ArrayIndexOutOfBoundsException e) {
                // do nothing
            }
            return locator;
        } else if (object instanceof By) {
            locator = object.toString();
            try {
                locator = locator.split("Located by ")[1];
                locator = "{" + locator + "}";
            } catch (ArrayIndexOutOfBoundsException e) {
                // do nothing
            }
            return locator;
        } else {
            return object.toString();
        }
    }

    private String parseParameters(Object... parameters) {
        StringBuilder log = new StringBuilder();
        if (parameters != null && parameters.length > 0) {
            if (parameters.length == 1) {
                // Value
                if (parameters[0] != null)
                    log.append(parseLocator(parameters[0]));
                else
                    log.append("null");
            } else if (parameters.length == 2) {
                // Parameter and value
                if (parameters[0] != null)
                    log.append(parseLocator(parameters[0]));
                else
                    log.append("null");
                log.append(" = ");
                if (parameters[1] != null)
                    log.append(parseLocator(parameters[1]));
                else
                    log.append("null");
            } else {
                // Set of values
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] != null) {
                        log.append(parameters[i].toString());
                        if (i < (parameters.length - 1))
                            log.append(", ");
                    } else if (i < (parameters.length - 1))
                        log.append("null, ");
                    else
                        log.append("null");
                }
            }
        }
        String message = getLineNumber() + " - " + getCallerMethodName();
        if (!log.toString().isEmpty())
            message += " => " + log;
        return message;
    }

    public void debug(Object... message) {
        logger.debug(parseParameters(message));
    }

    public void info(Object... message) {
        logger.info(parseParameters(message));
    }

    public void warn(String message, Exception exception) {
        String warningMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        warningMessage += message;
        logger.warn(warningMessage);
        exception.printStackTrace();
    }

    public void warn(String message) {
        String warningMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        warningMessage += message;
        logger.warn(warningMessage);
    }

    public void error(String message, Exception exception) {
        String errorMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        errorMessage += message;
        logger.error(errorMessage);
        exception.printStackTrace();
        throw new ApplicationManagerError(exception.getMessage());
    }

    public void error(String message) {
        logger.error(getLineNumber() + " - " + getCallerMethodName() + " => " + message);
        throw new ApplicationManagerError(message);
    }

    private String getCallerMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[LOG_DEPTH];
        return stackTraceElement.getMethodName();
    }

    private int getLineNumber() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[LOG_DEPTH];
        return stackTraceElement.getLineNumber();
    }

    private String getCallerClassPath() {
        return Thread.currentThread().getStackTrace()[LOG_DEPTH].getClassName();
    }
}
