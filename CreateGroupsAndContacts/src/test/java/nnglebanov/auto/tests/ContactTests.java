package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
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
        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }
        contactHelper.editContact(0,new ContactModel());
    }
    @Test
    public void bDeleteContactTest(){
        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }
        app.getContactHelper().deleteAllContacts();
    }
}
