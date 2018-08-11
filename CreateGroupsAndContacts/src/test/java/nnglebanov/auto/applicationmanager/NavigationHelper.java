package nnglebanov.auto.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    protected NavigationHelper(WebDriver driver) {
        super(driver);
    }


    public void moveToGroups() {
        click(By.cssSelector("ul>li.admin>a"));
    }

    public void moveToAddNew() {
        click(By.cssSelector("div#nav>ul>li:nth-child(2)"));
    }

    public void moveToContacts() {
        click(By.cssSelector("div#nav>ul>li:first-child"));
    }
}
