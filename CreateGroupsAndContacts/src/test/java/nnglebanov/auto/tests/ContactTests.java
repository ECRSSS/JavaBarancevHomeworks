package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactTests extends TestBase {

    Comparator<ContactModel> CONTACT_COMPARATOR=(g1,g2)->g1.getFirstName().compareTo(g2.getFirstName());

    @BeforeMethod
    public void ensurePreconditions(){

        ContactHelper contactHelper=app.contact();
        if(contactHelper.isContactExists()==false) {
            app.nav().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }

    }
    @Test
    public void aCreateContactTest(){
        Contacts listBefore=app.contact().all();
        app.nav().moveToAddNew();
        ContactModel newContact=new ContactModel().withId(listBefore.stream().mapToInt((g) -> g.getId()).max().getAsInt()+1);
        app.contact().addContact(newContact);
        Contacts listAfter=app.contact().all();
        assertThat(listAfter.size(),equalTo(listBefore.size()+1));
        assertThat(listAfter,equalTo(listBefore.withAdded(newContact)));
    }
    @Test
    public void aEditContactTest(){
        Contacts listBefore=app.contact().all();
        ContactModel newContact=new ContactModel().withId(listBefore.iterator().next().getId());
        app.contact().editContact(0,newContact);
        Contacts listAfter=app.contact().all();
        assertThat(listAfter,equalTo(listBefore.without(listBefore.iterator().next()).withAdded(newContact)));
    }
    @Test
    public void bDeleteContactTest(){
        app.contact().deleteAll();
        int sizeOfListAfter=app.contact().all().size();
        assertThat(0,equalTo(sizeOfListAfter));
    }
}
