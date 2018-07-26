package nnglebanov.auto.tests;

import nnglebanov.auto.model.ContactModel;
import org.testng.annotations.Test;

public class ContactTests extends TestBase {
    @Test
    public void aCreateContactTest(){
        app.getNavigationHelper().moveToAddNew();
        app.getContactHelper().addContact(new ContactModel());
    }
    @Test
    public void aEditContactTest(){
        app.getContactHelper().editContact(0,new ContactModel());
    }
    @Test
    public void bDeleteContactTest(){
        app.getContactHelper().deleteAllContacts();
    }
}
