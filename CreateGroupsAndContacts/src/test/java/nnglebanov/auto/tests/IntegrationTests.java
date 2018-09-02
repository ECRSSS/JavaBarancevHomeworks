package nnglebanov.auto.tests;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.GroupModel;
import org.hibernate.Hibernate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests extends TestBase {
    private void prepareContact(){
        app.nav().moveToAddNew();
        app.contact().addContact(new ContactModel());
        app.nav().moveToContacts();
    }
    private void prepareGroup(){
        app.nav().moveToGroups();
        app.group().addNewGroup(new GroupModel());
        app.nav().moveToContacts();
    }
    private void prepareContactToRemove(){
        app.nav().moveToAddNew();
        app.contact().addContact(new ContactModel());
        app.nav().moveToContacts();
        ContactModel contactModel=app.db().contacts().stream().filter(o->o.getGroups().size()==0).findFirst().get();
        app.contact().clickToSelectById(contactModel.getId());
        GroupModel groupModel=app.db().groups().stream().findFirst().get();
        app.contact().addToGroup(groupModel.getGroupName());
        app.nav().moveToContacts();
    }
    @Test
    public void addToGroup() throws InterruptedException {
        if(app.db().contacts().stream().filter(o->o.getGroups().size()==0).toArray().length==0)
        {prepareContact();}
        if(app.db().groups().size()==0)
        {prepareGroup();}
        ContactModel contact=app.db().contacts().stream().filter(o->o.getGroups().size()==0).findFirst().get();
        app.contact().clickToSelectById(contact.getId());
        GroupModel group=app.db().groups().stream().findFirst().get();
        app.contact().addToGroup(group.getGroupName());

        int id=contact.getId();
        contact=app.db().contacts().stream().filter(o->o.getId()==id).findFirst().get();
        assertThat(contact.getGroups().contains(group),equalTo(true));
    }
    @Test
    public void removeFromGroup(){
        if(app.db().groups().size()==0)
        {prepareGroup();}
        if(app.db().contacts().stream().filter(o->o.getGroups().size()==1).toArray().length==0)
        {prepareContactToRemove();}
        GroupModel groupModel=app.db().groups().stream().filter(o->o.getContacts().size()>0).findFirst().get();
        ContactModel contactModel=groupModel.getContacts().stream().findFirst().get();
        app.contact().selectGroupsView(groupModel.getGroupName());
        app.contact().clickToSelectById(contactModel.getId());
        app.contact().clickToRemoveFromGroup();
        GroupModel groupModel1=app.db().groups().stream().filter(o->o.getId()==groupModel.getId()).findFirst().get();
        assertThat(groupModel1.getContacts().contains(contactModel),equalTo(false));

    }
}
