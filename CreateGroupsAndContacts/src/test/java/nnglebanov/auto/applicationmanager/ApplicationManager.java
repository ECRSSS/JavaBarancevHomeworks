package nnglebanov.auto.applicationmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver driver;

    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    private ContactHelper contactHelper;

    public void init(){
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        groupHelper=new GroupHelper(driver);
        navigationHelper=new NavigationHelper(driver);
        contactHelper=new ContactHelper(driver);
        sessionHelper=new SessionHelper(driver);
        driver.get("http://localhost/addressbook/");
        sessionHelper.login();
    }
    public void stop() {
        driver.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
