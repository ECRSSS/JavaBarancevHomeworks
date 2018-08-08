package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.GroupModel;
import nnglebanov.auto.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {
    protected GroupHelper(WebDriver driver) {
        super(driver);
    }

    private void fillGroup(GroupModel gm) {
        if(gm.getGroupName()!=null)
        type(By.cssSelector("input[name='group_name']"), gm.getGroupName());
        if(gm.getGroupHeader()!=null)
        type(By.cssSelector("textarea[name='group_header']"), gm.getGroupHeader());
        if(gm.getGroupFooter()!=null)
        type(By.cssSelector("textarea[name='group_footer']"), gm.getGroupFooter());
    }

    private void acceptRefreshingData() {
        click(By.cssSelector("input[type='submit']"));
        click(By.cssSelector("div.msgbox a"));
    }

    private void acceptAfterDelete(){
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

    private void selectGroupByIndex(int index){
        click(By.cssSelector("span.group:nth-child("+(index+4)+") > input[type='checkbox']"));
    }

    public void deleteAllGroups() {
        selectAllGroups();
        click(By.cssSelector("input:nth-child(2)"));
        acceptAfterDelete();
    }
    public void deleteGroupByIndex(int index){
        selectGroupByIndex(1);
        click(By.cssSelector("input:nth-child(2)"));
        acceptAfterDelete();
    }

    public void editAloneGroup(GroupModel gm) {
        selectAllGroups();
        click(By.cssSelector("input:nth-child(3)"));
        fillGroup(gm);
        acceptRefreshingData();
    }

    public boolean isGroupExists(){
        if(driver.findElements(By.cssSelector("span.group")).size()>0) {
            return true;
        }else {return false;}
    }

    public Groups all(){
        List<WebElement> contactElements=driver.findElements(By.cssSelector("span.group"));
        Groups groups=new Groups();
        for(WebElement element : contactElements){
            String groupName=element.getText();
            int id=Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
            groups=groups.withAdded(new GroupModel(groupName,id));
        }
        return groups;
    }


}
