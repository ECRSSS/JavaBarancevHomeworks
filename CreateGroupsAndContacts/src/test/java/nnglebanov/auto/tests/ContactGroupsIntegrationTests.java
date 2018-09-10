package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.applicationmanager.GroupHelper;
import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.GroupModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupsIntegrationTests extends TestBase {
    private ContactModel contact;
    private GroupModel group;

    @BeforeClass
    public void ensurePreconditions() {
        app.nav().moveToContacts();
        app.contact().deleteAll();
        app.nav().moveToGroups();
        app.group().deleteAllGroups();
        app.nav().moveToContacts();
        ContactHelper contactHelper = app.contact();
        app.nav().moveToAddNew();
        contactHelper.addContact(new ContactModel().withFirstName("IntegrationContact").withAddress("Address")
                .withFirstEmail("Email").withSecondEmail("Email2").withMobilePhoneNumber("89999").withWorkPhone("89777"));
        app.nav().moveToGroups();
        GroupHelper groupHelper = app.group();
        groupHelper.addNewGroup(new GroupModel().withName("IntegrationGroup"));
    }

    @Test
    public void aMoveAndDeleteFromGroup() throws InterruptedException {
        contact=app.db().contacts().iterator().next();
        group=app.db().groups().iterator().next();
        app.nav().moveToContacts();
        app.contact().clickToSelectByIndex(0);
        app.contact().addToGroup(group.getGroupName());
        assertThat(app.db().contacts()
                .iterator().next().getGroups()
                .contains(group),equalTo(true));
        app.nav().moveToContacts();
        app.contact().selectGroupsView(group.getGroupName());
        app.contact().clickToSelectByIndex(0);
        app.contact().clickRemoveGroup();
        contact=app.db().contacts().iterator().next();
        assertThat(contact.getGroups().size(),equalTo(0));


    }
    @Test void bDeleteFromGroup(){
    }
}
