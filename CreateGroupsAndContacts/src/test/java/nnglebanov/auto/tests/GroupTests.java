package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import nnglebanov.auto.model.Groups;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupTests extends TestBase {
    Comparator<GroupModel> GROUP_COMPARATOR=(GroupModel g1,GroupModel g2)->Integer.compare(g1.getId(),g2.getId());

    @BeforeMethod
    public void ensurePreconditions() {
        app.nav().moveToGroups();
        GroupHelper groupHelper = app.group();
        if (groupHelper.isGroupExists() == false) {
            groupHelper.addNewGroup(new GroupModel());
        }
    }

    @Test
    public void createGroupTest() {
        Groups groupsBefore = app.group().all();
        GroupModel groupModel=new GroupModel().withName("Test").withHeader("Header").withFooter("Footer")
                .withId(groupsBefore.stream().mapToInt((g) -> g.getId()).max().getAsInt()+1);
        app.group().addNewGroup(groupModel);
        Groups groupsAfter = app.group().all();
        assertThat(groupsAfter,equalTo(groupsBefore.withAdded(groupModel)));

    }

    @Test
    public void editGroupTest() {
        Groups groupsBefore = app.group().all();
        GroupModel newGroup=new GroupModel().withName("EditedName").withHeader("EditedHeader")
                .withFooter("EditedFooter").withId(groupsBefore.iterator().next().getId());
        app.group().editAloneGroup(newGroup);
        Groups groupsAfter = app.group().all();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size()));
        assertThat(groupsBefore.without(groupsBefore.iterator().next()).withAdded(newGroup),equalTo(groupsAfter));

    }

    @Test
    public void deleteGroupsTest() {
        app.group().deleteAllGroups();
        assertThat(0,equalTo(app.group().all().size()));
    }

    @Test
    public void deleteGroupTest(){
        int sizeBefore=app.group().all().size();
        app.group().deleteGroupByIndex(1);
        assertThat(sizeBefore-1,equalTo(app.group().all().size()));
    }
}
