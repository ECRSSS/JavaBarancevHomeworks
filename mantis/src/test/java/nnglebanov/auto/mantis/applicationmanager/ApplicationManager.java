package nnglebanov.auto.mantis.applicationmanager;

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
    private WebDriver driver;

    private MailHelper mail;
    public MailHelper mail(){
        return mail;
    }
    private DBHelper db;
    public DBHelper db(){return db;}
    private ActionHelper action;
    public ActionHelper action(){return action;}

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
        mail=new MailHelper(this);
        db=new DBHelper();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File("src/test/resources/" + target + ".properties")));

        if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        action=new ActionHelper(driver);
    }

    public void stop() {
        driver.quit();
    }
    public HttpSession newSession(){
        return new HttpSession(this);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
