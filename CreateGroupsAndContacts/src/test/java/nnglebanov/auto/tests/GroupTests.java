package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupTests extends TestBase {
    @Test
    public void createGroupTest(){
        app.getNavigationHelper().moveToGroups();
        int groupsBefore=app.getGroupHelper().getListOfContacts().size();
        GroupHelper gh=app.getGroupHelper();
        gh.addNewGroup(new GroupModel());
        int groupsAfter=app.getGroupHelper().getListOfContacts().size();
        Assert.assertEquals(groupsAfter,groupsBefore+1);

    }
    @Test
    public void editGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        int groupsBefore=app.getGroupHelper().getListOfContacts().size();
        groupHelper.editAloneGroup(new GroupModel("T2","T3","T4"));
        int groupsAfter=app.getGroupHelper().getListOfContacts().size();
        Assert.assertEquals(groupsAfter,groupsBefore);

    }
    @Test
    public void deleteGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        groupHelper.deleteAllGroups();
        int groupsAfter=app.getGroupHelper().getListOfContacts().size();
        Assert.assertEquals(groupsAfter,0);
    }
}
