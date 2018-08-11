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


    @BeforeMethod
    public void ensurePreconditions(){

        ContactHelper contactHelper=app.contact();
        if(contactHelper.isContactExists()==false) {
            app.nav().moveToAddNew();
            contactHelper.addContact(new ContactModel().withFirstName("Name").withAddress("Address")
                    .withFirstEmail("Email").withMobilePhoneNumber("89999"));
        }

    }
    @Test
    public void aCreateContactTest(){
        Contacts listBefore=app.contact().all();
        app.nav().moveToAddNew();
        ContactModel newContact=new ContactModel()
                .withFirstName("TestName")
                .withLastName("TestLastName")
                .withAddress("TestAdress")
                .withFirstEmail("TestMail")
                .withMobilePhoneNumber("899900000")
                .withId(listBefore.stream().mapToInt((g) -> g.getId()).max().getAsInt()+1);
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
    public void bDeleteContactsTest(){
        app.contact().deleteAll();
        int sizeOfListAfter=app.contact().all().size();
        assertThat(0,equalTo(sizeOfListAfter));
    }
    @Test
    public void cDeleteContactTest(){
        int sizeBefore=app.contact().all().size();
        app.contact().deleteByIndex(1);
        assertThat(sizeBefore-1,equalTo(app.contact().all().size()));
    }

    //Тест задания 11
    @Test
    public void dCheckContactData(){
        ContactModel modelBack=app.contact().getContactByIndex(1);
        app.nav().moveToContacts();
        ContactModel modelFront=app.contact().parseContact(1);

        assertThat(modelBack.getAllEmails(),equalTo(modelFront.getAllEmails()));
        assertThat(modelBack.getAllPhones(),equalTo(modelFront.getAllPhones()));
    }
}
