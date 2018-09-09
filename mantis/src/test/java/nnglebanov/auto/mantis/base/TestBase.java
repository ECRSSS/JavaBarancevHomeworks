package nnglebanov.auto.mantis.base;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectBindingStub;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import nnglebanov.auto.mantis.applicationmanager.ApplicationManager;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.json.JSONException;
import org.json.JSONObject;
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

    private boolean isIssueOpenMantis(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.20/api/soap/mantisconnect.php"));
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
        if (!issue.getStatus().getName().equals("closed") && !issue.getStatus().getName().equals("resolved"))
            return true;
        else
            return false;
    }

    private boolean isIssueOpenBugify(int id) throws IOException, JSONException {
        Executor executor=Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
        Content content=executor.execute(Request.Get("http://bugify.stqa.ru/api/issues/"+id+".json")).returnContent();
        JSONObject json=new JSONObject(content.asString());
        JSONObject issue=(JSONObject) json.getJSONArray("issues").get(0);
        String status=issue.get("state_name").toString();
        if(!status.equals("Resolved") && !status.equals("Closed"))
            return true;
        else
            return false;
    }

    public void skipIfNotFixedBugify(int issueId) throws IOException, JSONException {
        if (isIssueOpenBugify(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public void skipIfNotFixedMantis(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpenMantis(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
