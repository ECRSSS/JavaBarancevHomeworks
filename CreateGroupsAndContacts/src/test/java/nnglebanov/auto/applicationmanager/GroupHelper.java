package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.GroupModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<GroupModel> getListOfGroups(){
        List<WebElement> contactElements=driver.findElements(By.cssSelector("span.group"));
        List<GroupModel> groups=new ArrayList<>();
        for(WebElement element : contactElements){
            String groupName=element.getText();
            int id=Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
            groups.add(new GroupModel(groupName,id));
        }
        Collections.sort(groups,((o1, o2) -> Integer.compare(o1.getId(),o2.getId())));
        return groups;
    }


}
