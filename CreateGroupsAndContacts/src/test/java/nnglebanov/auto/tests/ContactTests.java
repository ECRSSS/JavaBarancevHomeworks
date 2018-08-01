package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.model.ContactModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactTests extends TestBase {
    @Test
    public void aCreateContactTest(){
        List<ContactModel> listBefore=app.getContactHelper().getListOfContacts();
        app.getNavigationHelper().moveToAddNew();
        app.getContactHelper().addContact(new ContactModel());
        List<ContactModel> listAfter=app.getContactHelper().getListOfContacts();
        Assert.assertEquals(listAfter.size(),listBefore.size()+1);
        Assert.assertNotEquals(listAfter,listBefore);
    }
    @Test
    public void aEditContactTest(){

        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }
        List<ContactModel> listBefore=app.getContactHelper().getListOfContacts();
        contactHelper.editContact(0,new ContactModel());
        List<ContactModel> listAfter=app.getContactHelper().getListOfContacts();
        Assert.assertEquals(listAfter,listBefore);
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
