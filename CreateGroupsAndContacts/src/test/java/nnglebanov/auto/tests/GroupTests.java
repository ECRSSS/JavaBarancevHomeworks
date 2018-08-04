package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupTests extends TestBase {
    Comparator<GroupModel> GROUP_COMPARATOR=(GroupModel g1,GroupModel g2)->Integer.compare(g1.getId(),g2.getId());

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().moveToGroups();
        GroupHelper groupHelper = app.getGroupHelper();
        if (groupHelper.isGroupExists() == false) {
            groupHelper.addNewGroup(new GroupModel());
        }
    }

    @Test
    public void createGroupTest() {
        List<GroupModel> groupsBefore = app.getGroupHelper().getListOfGroups();
        GroupHelper gh = app.getGroupHelper();
        GroupModel groupModel=new GroupModel();
        gh.addNewGroup(groupModel);
        List<GroupModel> groupsAfter = app.getGroupHelper().getListOfGroups();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
        groupsBefore.add(groupModel);
        Assert.assertEquals(groupsBefore, groupsAfter);

    }

    @Test
    public void editGroupTest() {
        GroupHelper groupHelper = app.getGroupHelper();
        List<GroupModel> groupsBefore = app.getGroupHelper().getListOfGroups();
        GroupModel newGroup=new GroupModel("T1",null,null);
        groupHelper.editAloneGroup(newGroup);
        List<GroupModel> groupsAfter = app.getGroupHelper().getListOfGroups();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.remove(groupsBefore.size()-1);
        groupsBefore.add(newGroup);
        groupsAfter.sort(GROUP_COMPARATOR);
        groupsBefore.sort(GROUP_COMPARATOR);
        Assert.assertEquals(groupsBefore,groupsAfter);

    }

    @Test
    public void deleteGroupTest() {
        GroupHelper groupHelper = app.getGroupHelper();
        groupHelper.deleteAllGroups();
        int groupsAfter = app.getGroupHelper().getListOfGroups().size();
        Assert.assertEquals(groupsAfter, 0);
    }
}
