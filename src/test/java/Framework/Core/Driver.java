package Framework.Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    private Driver() {      // Leaving empty to prevent Driver class initialization
    }

    /*
     * In simple words a static synchronized method will lock the class instead of the object,
     * and it will lock the class because the keyword static means: "class instead of instance".
     * The keyword synchronized means that only one thread can access the method at a time.
     * And static synchronized mean:
     */
    public synchronized static WebDriver getDriver(String browser) {
        // String browser ==>  By default, it comes from xml file to test base class, from test base it comes here
        // if the value is specified in xml, it will be used otherwise we get it from properties file
        if (driver == null) {             // first we check if the value from xml file is null or not
            browser = browser == null ? String.valueOf(ConfigurationReader.getProperty("browser")) : browser;

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeHeadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefoxHeadless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;

                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Mac OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Windows OS which doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                case "remote_chrome":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setPlatform(Platform.ANY);
                    desiredCapabilities.setBrowserName("chrome");
                    try {
                        driver = new RemoteWebDriver(new URL("http://54.152.227.253:4444/wd/hub"), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Illegal browser type!");
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {

        return getDriver(null);
    }

    public  static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
