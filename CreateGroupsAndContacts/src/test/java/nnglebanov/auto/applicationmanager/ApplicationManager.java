package nnglebanov.auto.applicationmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private String browser;
    private final Properties properties;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties=new Properties();
    }

    private WebDriver driver;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;

    public DbHelper db(){return dbHelper;}

    public ContactHelper contact() {
        return contactHelper;
    }

    private ContactHelper contactHelper;


    public void init() throws IOException {
        dbHelper=new DbHelper();
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File("src/test/resources/"+target+".properties")));

        if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper = new SessionHelper(driver);
        driver.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.login"),properties.getProperty("web.password"));
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper nav() {
        return navigationHelper;
    }

    public SessionHelper session() {
        return sessionHelper;
    }
}
