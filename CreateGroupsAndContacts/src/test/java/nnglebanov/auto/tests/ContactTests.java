package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.model.ContactModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTests extends TestBase {
    @Test
    public void aCreateContactTest(){
        int sizeOfListBefore=app.getContactHelper().getListOfContacts().size();
        app.getNavigationHelper().moveToAddNew();
        app.getContactHelper().addContact(new ContactModel());
        int sizeOfListAfter=app.getContactHelper().getListOfContacts().size();
        Assert.assertEquals(sizeOfListAfter,sizeOfListBefore+1);
    }
    @Test
    public void aEditContactTest(){

        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }
        int sizeOfListBefore=app.getContactHelper().getListOfContacts().size();
        contactHelper.editContact(0,new ContactModel());
        int sizeOfListAfter=app.getContactHelper().getListOfContacts().size();
        Assert.assertEquals(sizeOfListAfter,sizeOfListBefore);
    }
    @Test
    public void bDeleteContactTest(){
        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }
        app.getContactHelper().deleteAllContacts();
        int sizeOfListAfter=app.getContactHelper().getListOfContacts().size();
        Assert.assertEquals(sizeOfListAfter,0);
    }
}
