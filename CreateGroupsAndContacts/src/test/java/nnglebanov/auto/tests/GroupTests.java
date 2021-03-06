package nnglebanov.auto.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.GroupModel;
import nnglebanov.auto.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> groupsFromJson() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json="";
        String line=reader.readLine();
        while (line!=null){
            json+=line;
            line=reader.readLine();
        }
        Gson gson=new Gson();
        List<GroupModel> groups=gson.fromJson(json,new TypeToken<List<GroupModel>>(){}.getType());
        return groups.stream().map(g->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.nav().moveToGroups();
        GroupHelper groupHelper = app.group();
        if (app.db().groups().size()==0) {
            groupHelper.addNewGroup(new GroupModel());
        }
    }
    @Test(dataProvider = "groupsFromJson")
    public void createGroupTestWithProvider(GroupModel groupModel) {
        app.group().addNewGroup(groupModel);
    }

    @Test
    public void createGroupTest() {
        Groups groupsBefore = app.db().groups();
        GroupModel groupModel = new GroupModel().withName("Test").withHeader("Header").withFooter("Footer")
                .withId(groupsBefore.stream().mapToInt((g) -> g.getId()).max().getAsInt() + 1);
        app.group().addNewGroup(groupModel);
        Groups groupsAfter = app.db().groups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(groupModel)));

    }

    @Test
    public void editGroupTest() {
        Groups groupsBefore = app.db().groups();
        GroupModel newGroup = new GroupModel().withName("EditedName").withHeader("EditedHeader")
                .withFooter("EditedFooter").withId(groupsBefore.iterator().next().getId());
        app.group().editAloneGroup(newGroup);
        Groups groupsAfter = app.db().groups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size()));
        assertThat(groupsBefore.without(groupsBefore.iterator().next()).withAdded(newGroup), equalTo(groupsAfter));

    }

    @Test
    public void deleteGroupsTest() {
        app.group().deleteAllGroups();
        assertThat(0, equalTo(app.db().groups().size()));
    }

    //Добавлена проверка состояния списка
    @Test
    public void deleteGroupTest() {
        int sizeBefore = app.db().groups().size();
        Groups groupsBefore=app.db().groups();
        GroupModel groupToDelete=app.group().getGroupByIndex(0);
        app.nav().moveToGroups();
        app.group().deleteGroupByIndex(0);
        Groups groupsAfter=app.db().groups();
        groupsBefore=groupsBefore.without(groupToDelete);
        assertThat(sizeBefore - 1, equalTo(app.group().all().size()));
        assertThat(groupsAfter,equalTo(groupsBefore));
    }
}
