package nnglebanov.auto.tests;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.GroupModel;
import org.hibernate.Hibernate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests extends TestBase {
    @Test
    public void addToGroup() throws InterruptedException {

        ContactModel contact=app.db().contacts().stream().filter(o->o.getGroups().size()==0).findFirst().get();
        app.contact().clickToSelectById(contact.getId());
        GroupModel group=app.db().groups().stream().findFirst().get();
        Hibernate.initialize(group.getContacts());
        app.contact().addToGroup(group.getGroupName());
        assertThat(app.contact().getGroupNameFromDetailsPage(contact.getId()).equals(group.getGroupName())
                ,equalTo(true));
    }
}
