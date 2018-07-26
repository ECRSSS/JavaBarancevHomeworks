package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import org.testng.annotations.Test;

public class GroupTests extends TestBase {
    @Test
    public void createGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper gh=app.getGroupHelper();
        gh.addNewGroup(new GroupModel());
    }
    @Test
    public void editGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        groupHelper.editAloneGroup(new GroupModel("T2","T3","T4"));
    }
    @Test
    public void deleteGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        groupHelper.deleteAllGroups();
    }
}
