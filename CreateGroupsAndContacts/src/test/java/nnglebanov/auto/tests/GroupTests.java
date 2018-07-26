package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import org.testng.annotations.Test;

public class GroupTests extends TestBase {
    @Test
    public void aCreateGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper gh=app.getGroupHelper();
        gh.addNewGroup(new GroupModel());
    }
    @Test
    public void aEditGroupTest(){
        app.getNavigationHelper().moveToGroups();
        app.getGroupHelper().editAloneGroup(new GroupModel("T2","T3","T4"));
    }
    @Test
    public void bDeleteGroupTest(){
        app.getNavigationHelper().moveToGroups();
        app.getGroupHelper().deleteAllGroups();
    }
}
