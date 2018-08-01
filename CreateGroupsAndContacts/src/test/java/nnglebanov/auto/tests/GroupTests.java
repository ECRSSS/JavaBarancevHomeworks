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
        List<GroupModel> groupsBefore=app.getGroupHelper().getListOfGroups();
        GroupHelper gh=app.getGroupHelper();
        gh.addNewGroup(new GroupModel());
        List<GroupModel> groupsAfter=app.getGroupHelper().getListOfGroups();
        Assert.assertEquals(groupsAfter.size(),groupsBefore.size()+1);
        Assert.assertNotEquals(groupsBefore,groupsAfter);

    }
    @Test
    public void editGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        List<GroupModel> groupsBefore=app.getGroupHelper().getListOfGroups();
        groupHelper.editAloneGroup(new GroupModel());
        List<GroupModel> groupsAfter=app.getGroupHelper().getListOfGroups();
        Assert.assertEquals(groupsAfter.size(),groupsBefore.size());
        Assert.assertEquals(groupsBefore,groupsAfter);

    }
    @Test
    public void deleteGroupTest(){
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper=app.getGroupHelper();
        if(groupHelper.isGroupExists()==false){
            groupHelper.addNewGroup(new GroupModel());
        }
        groupHelper.deleteAllGroups();
        int groupsAfter=app.getGroupHelper().getListOfGroups().size();
        Assert.assertEquals(groupsAfter,0);
    }
}
