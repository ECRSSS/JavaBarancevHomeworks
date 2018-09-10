package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("chrome");

    @BeforeSuite
    public void setUp() throws IOException {
        app.initRemote();

    }

    @AfterSuite
    public void stop() {
        app.stop();
    }

}
