package nnglebanov.auto.mantis.applicationmanager;

import nnglebanov.auto.mantis.model.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionHelper extends HelperBase{
    public ActionHelper(WebDriver driver){super(driver);}

    public ActionHelper login(){
        driver.get("http://localhost/mantisbt-1.2.20/login_page.php");
        type(By.cssSelector("input[name='username']"),"administrator");
        type(By.cssSelector("input[name='password']"),"root");
        click(By.cssSelector("input.button"));
        return this;
    }
    public ActionHelper goToManageUsers() {
        click(By.cssSelector(".bracket-link:first-child a"));
        return this;
    }
    public ActionHelper toUser(UserModel user){
        click(By.xpath("//a[text()[contains(.,'"+user.getUsername()+"')]]"));
        return this;
    }
    public void resetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }
    public void fillNewCredentials(String link,String newPassword){
        driver.get(link);
        type(By.cssSelector("input[name='password']"),newPassword);
        type(By.cssSelector("input[name='password_confirm']"),newPassword);
        click(By.cssSelector("input.button"));
    }
}
