package nnglebanov.auto.tests;

import nnglebanov.auto.applicationmanager.ContactHelper;
import nnglebanov.auto.model.ContactModel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactTests extends TestBase {

    Comparator<ContactModel> CONTACT_COMPARATOR=(g1,g2)->g1.getFirstName().compareTo(g2.getFirstName());

    @BeforeMethod
    public void ensurePreconditions(){

        ContactHelper contactHelper=app.getContactHelper();
        if(contactHelper.isContactExists()==false) {
            app.getNavigationHelper().moveToAddNew();
            contactHelper.addContact(new ContactModel());
        }

    }
    @Test
    public void aCreateContactTest(){
        List<ContactModel> listBefore=app.getContactHelper().getListOfContacts();
        app.getNavigationHelper().moveToAddNew();
        ContactModel newContact=new ContactModel();
        app.getContactHelper().addContact(newContact);
        List<ContactModel> listAfter=app.getContactHelper().getListOfContacts();
        Assert.assertEquals(listAfter.size(),listBefore.size()+1);
        listBefore.add(newContact);
        listAfter.sort(CONTACT_COMPARATOR);
        listBefore.sort(CONTACT_COMPARATOR);
        Assert.assertEquals(listAfter,listBefore);
    }
    @Test
    public void aEditContactTest(){
        ContactHelper contactHelper=app.getContactHelper();
        List<ContactModel> listBefore=app.getContactHelper().getListOfContacts();
        ContactModel newContact=new ContactModel();
        contactHelper.editContact(0,newContact);
        List<ContactModel> listAfter=app.getContactHelper().getListOfContacts();
        listBefore.remove(listBefore.size()-1);
        listBefore.add(newContact);
        listAfter.sort(CONTACT_COMPARATOR);
        listBefore.sort(CONTACT_COMPARATOR);
        Assert.assertEquals(listAfter,listBefore);
    }
    @Test
    public void bDeleteContactTest(){
        app.getContactHelper().deleteAllContacts();
        int sizeOfListAfter=app.getContactHelper().getListOfContacts().size();
        Assert.assertEquals(sizeOfListAfter,0);
    }
}
