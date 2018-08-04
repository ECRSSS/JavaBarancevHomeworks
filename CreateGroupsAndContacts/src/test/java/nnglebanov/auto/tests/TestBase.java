package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("chrome");

    @BeforeSuite
    public void setUp() {
        app.init();

    }

    @AfterSuite
    public void stop() {
        app.stop();
    }

}
