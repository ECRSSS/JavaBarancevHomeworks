package nnglebanov.auto.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactsFromJson() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json="";
        String line=reader.readLine();
        while (line!=null){
            json+=line;
            line=reader.readLine();
        }
        Gson gson=new Gson();
        List<ContactModel> contacts=gson.fromJson(json,new TypeToken<List<ContactModel>>(){}.getType());
        return contacts.stream().map(g->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "contactsFromJson")
    public void aCreateContactsWithProvider(ContactModel contactModel){
        app.nav().moveToAddNew();
        app.contact().addContact(contactModel);
    }

    @BeforeMethod
    public void ensurePreconditions(){

        ContactHelper contactHelper=app.contact();
        if(app.db().contacts().size()==0) {
            app.nav().moveToAddNew();
            contactHelper.addContact(new ContactModel().withFirstName("Name").withAddress("Address")
                    .withFirstEmail("Email").withSecondEmail("Email2").withMobilePhoneNumber("89999").withWorkPhone("89777"));
        }

    }
    @Test
    public void aCreateContactTest(){
        Contacts listBefore=app.db().contacts();
        app.nav().moveToAddNew();
        ContactModel newContact=new ContactModel()
                .withFirstName("TestName")
                .withLastName("TestLastName")
                .withAddress("TestAdress")
                .withFirstEmail("TestMail")
                .withMobilePhoneNumber("899900000")
                .withId(listBefore.stream().mapToInt((g) -> g.getId()).max().getAsInt()+1);
        app.contact().addContact(newContact);
        Contacts listAfter=app.db().contacts();
        assertThat(listAfter.size(),equalTo(listBefore.size()+1));
        assertThat(listAfter,equalTo(listBefore.withAdded(newContact)));
    }
    @Test
    public void aEditContactTest() throws InterruptedException {
        Contacts listBefore=app.db().contacts();
        ContactModel newContact=new ContactModel().withId(listBefore.iterator().next().getId());
        app.contact().editContact(0,newContact);
        Thread.sleep(1000);
        Contacts listAfter=app.db().contacts();
        assertThat(listAfter,equalTo(listBefore.without(listBefore.iterator().next()).withAdded(newContact)));
    }
    @Test
    public void bDeleteContactsTest() throws InterruptedException {
        app.contact().deleteAll();
        Thread.sleep(2000);
        int sizeOfListAfter=app.db().contacts().size();
        assertThat(0,equalTo(sizeOfListAfter));
    }
    //Добавлена проверка состояния списка
    @Test
    public void cDeleteContactTest() throws InterruptedException {
        int sizeBefore=app.db().contacts().size();
        Contacts contactsBefore = app.db().contacts();
        ContactModel contactToDelete=app.contact().getContactByIndex(1).withId(contactsBefore.iterator().next().getId());
        app.nav().moveToContacts();
        app.contact().deleteByIndex(0);
        Thread.sleep(2000);
        Contacts contactsAfter=app.db().contacts();
        contactsBefore=contactsBefore.without(contactToDelete);
        assertThat(contactsAfter,equalTo(contactsBefore));
        assertThat(sizeBefore-1,equalTo(app.contact().all().size()));
    }

    //Тест задания 11
    @Test
    public void dCheckContactData(){
        ContactModel modelBack=app.db().contacts().iterator().next();
        app.nav().moveToContacts();
        ContactModel modelFront=app.contact().parseContact(1);

        assertThat(modelBack.calculateAllEmails(),equalTo(modelFront.getAllEmails()));
        assertThat(modelBack.calculateAllPhones(),equalTo(modelFront.getAllPhones()));
    }
}
