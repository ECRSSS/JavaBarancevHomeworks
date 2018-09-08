package nnglebanov.auto.mantis.base;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectBindingStub;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import nnglebanov.auto.mantis.applicationmanager.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("chrome");

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();

    }

    @AfterSuite
    public void stop() {
        app.stop();
    }

    private boolean isIssueOpen(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.20/api/soap/mantisconnect.php"));
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
        if (!issue.getStatus().getName().equals("closed") && !issue.getStatus().getName().equals("resolved"))
            return true;
        else
            return false;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
