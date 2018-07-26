package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.GroupModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {
    protected GroupHelper(WebDriver driver) {
        super(driver);
    }

    private void fillGroup(GroupModel gm) {
        type(By.cssSelector("input[name='group_name']"), gm.getGroupName());
        type(By.cssSelector("textarea[name='group_header']"), gm.getGroupHeader());
        type(By.cssSelector("textarea[name='group_footer']"), gm.getGroupFooter());
    }

    private void acceptRefreshingData() {
        click(By.cssSelector("input[type='submit']"));
        click(By.cssSelector("div.msgbox a"));
    }

    public void addNewGroup(GroupModel gm) {
        click(By.cssSelector("input[value='New group']:first-child"));
        fillGroup(gm);
        acceptRefreshingData();
    }

    public void selectGroupByName(String groupName) {
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group input"));
        for (WebElement element : elements) {
            if (element.getText().equals(groupName)) ;
            element.click();
        }
    }

    public void selectAllGroups() {
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group input"));
        for (WebElement element : elements) {
            element.click();
        }
    }

    public void deleteAllGroups() {
        selectAllGroups();
        click(By.cssSelector("input:nth-child(2)"));
    }

    public void editAloneGroup(GroupModel gm) {
        click(By.cssSelector("input:nth-child(3)"));
        fillGroup(gm);
        acceptRefreshingData();
    }


}
