package payback.core.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper extends BaseHelper {
    private String propertiesFilePath;

    public PropertiesHelper() {
    }

    public PropertiesHelper(String path) {
        propertiesFilePath = path;
    }

    public void loadFile(String propertiesFilePath) {
        app.log().debug(propertiesFilePath);
        this.propertiesFilePath = propertiesFilePath;
    }

    private String load(String propertyName) {
        String propertyValue = null;
        try {
            if (propertyName != null) {
                Properties properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream(propertiesFilePath);
                properties.load(fileInputStream);
                fileInputStream.close();
                propertyValue = properties.getProperty(propertyName);
            } else
                throw new NullPointerException();
        } catch (IOException | NullPointerException e) {
            app.log().warn("Cannot load a property with name \"" + propertyName + "\" from file \"" + propertiesFilePath + "\"", e);
        }
        return propertyValue;
    }

    private String loadAsResource(String propertyName) {
        String propertyValue = null;
        try {
            if (propertyName != null) {
                Properties properties = new Properties();
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classloader.getResourceAsStream(propertiesFilePath);
                properties.load(inputStream);
                propertyValue = properties.getProperty(propertyName);
            } else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException e) {
            app.log().warn("Cannot load a property with name \"" + propertyName + "\" from file \"" + propertiesFilePath + "\"", e);
        }
        return propertyValue;
    }

    public String getPropertyValue(String propertyName) {
        String propertyValue = null;
        try {
            propertyValue = load(propertyName);
            if (propertyValue != null && propertyValue.isEmpty()) {
                throw new NullPointerException();
            }
            app.log().debug(propertyName, propertyValue);
        } catch (NullPointerException e) {
            app.log().warn("Cannot load a property with name \"" + propertyName + "\" from file \"" + propertiesFilePath + "\"", e);
        }
        return propertyValue;
    }

    public String getPropertyValueAsResource(String propertyName) {
        String propertyValue = null;
        try {
            propertyValue = loadAsResource(propertyName);
            if (propertyValue != null && propertyValue.isEmpty()) {
                throw new NullPointerException();
            }
            app.log().debug(propertyName, propertyValue);
        } catch (NullPointerException e) {
            app.log().warn("Cannot load a property with name \"" + propertyName + "\" from file \"" + propertiesFilePath + "\"", e);
        }
        return propertyValue;
    }

}
