package nnglebanov.auto.mantis.tests;

import nnglebanov.auto.mantis.applicationmanager.HttpSession;
import nnglebanov.auto.mantis.base.TestBase;
import nnglebanov.auto.mantis.model.MailMessage;
import nnglebanov.auto.mantis.model.UserModel;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

public class ResetTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void resetPasswordTest() throws IOException, MessagingException, JSONException {
        skipIfNotFixedBugify(268);
        app.action().login();
        app.action().goToManage();
        UserModel user=app.db().users().stream().filter(o->!o.getEmail().equals("root@localhost")).findFirst().get();
        app.action().goToManageUsers();
        app.action().toUser(user).resetPassword();
        String link=app.mail().getConfirmationLink();
        String newPassword="0000";
        app.action().fillNewCredentials(link,newPassword);
        HttpSession session=app.newSession();
        Assert.assertTrue(session.login(user.getUsername(),newPassword));
        Assert.assertTrue(session.isLoggedInAs(user.getUsername()));
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
