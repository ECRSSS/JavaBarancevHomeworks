package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected final ApplicationManager app=new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();

    }
    @AfterMethod
    public void stop() {
        app.stop();
    }

}
