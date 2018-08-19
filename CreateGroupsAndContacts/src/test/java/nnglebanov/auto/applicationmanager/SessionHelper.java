package nnglebanov.auto.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    private WebDriver driver;

    protected SessionHelper(WebDriver driver) { super(driver);}


    public void login(String login,String password){
        type(By.cssSelector("input[name='user']"),login);
        type(By.cssSelector("input[name='pass']"),password);
        click(By.cssSelector("input[type='submit']"));
    }
}
