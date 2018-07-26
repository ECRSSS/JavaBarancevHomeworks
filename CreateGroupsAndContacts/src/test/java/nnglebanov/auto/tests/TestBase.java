package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase {

    protected final ApplicationManager app = new ApplicationManager("chrome");

    @BeforeMethod
    public void setUp() {
        app.init();

    }

    @AfterMethod
    public void stop() {
        app.stop();
    }

}
