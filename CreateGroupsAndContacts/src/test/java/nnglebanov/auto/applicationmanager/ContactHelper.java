package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.Contacts;
import nnglebanov.auto.utils.OtherUtils;
import nnglebanov.auto.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactHelper extends HelperBase {
    protected ContactHelper(WebDriver driver) {
        super(driver);
    }

    private void fillContact(ContactModel cm) {
        type(By.name("firstname"), cm.getFirstName());
        type(By.name("middlename"), cm.getMiddleName());
        type(By.name("lastname"), cm.getLastName());
        type(By.name("nickname"), cm.getNickName());
        //type(By.name("photo"),cm.getPhotoPath());
        type(By.name("title"), cm.getTitle());
        type(By.name("company"), cm.getCompany());
        type(By.name("address"), cm.getAddress());

        type(By.name("home"), cm.getHome());
        type(By.name("mobile"), cm.getMobilePhone());
        type(By.name("work"), cm.getWorkPhone());
        type(By.name("fax"), cm.getFax());

        type(By.name("email"), cm.getEmail1());
        type(By.name("email2"), cm.getEmail2());
        type(By.name("email3"), cm.getEmail3());
        type(By.name("homepage"), cm.getHomepage());
        //birthday
        //selectByValue(By.name("bday"), Integer.toString(cm.getBirthday().getDayOfMonth()));
        //selectByValue(By.name("bmonth"), OtherUtils.monthToValue(cm.getBirthday().getMonth().toString()));
        //type(By.name("byear"), Integer.toString(cm.getBirthday().getYear()));
        // anniversary
        //selectByValue(By.name("aday"), Integer.toString(cm.getAnniversary().getDayOfMonth()));
        //selectByText(By.name("amonth"), OtherUtils.monthToValue(cm.getAnniversary().getMonth().toString()));
        //type(By.name("ayear"), Integer.toString(cm.getAnniversary().getYear()));

        type(By.name("address2"), cm.getSecondaryAddress());
        type(By.name("phone2"), cm.getHome());
        type(By.name("notes"), cm.getNotes());
    }


    private void submitContact() {
        click(By.name("submit"));
        WaitUtils.waitForUrl("http://localhost/addressbook/", driver);
    }

    private void updateContact() {
        click(By.name("update"));
        WaitUtils.waitForUrl("http://localhost/addressbook/", driver);
    }

    private void clickOnDelete() {
        click(By.cssSelector("input[value='Delete']"));
        driver.switchTo().alert().accept();
    }

    private void clickToSelectAllCheckbox() {
        click(By.id("MassCB"));
    }

    private void clickToSelectByIndex(int index) {click(By.cssSelector("tr[name='entry']:nth-child("+(index+1)+") input[name='selected[]']"));}

    private void clickOnEditLinkByIndex(int index) {
        List<WebElement> elements = driver.findElements(By.xpath("//img[@title='Edit']//.."));
        elements.get(index).click();
    }

    public void addContact(ContactModel contactModel) {
        fillContact(contactModel);
        submitContact();
    }

    public void deleteByIndex(int index) {
        clickToSelectByIndex(index);
        clickOnDelete();
    }

    public void deleteAll() {
        clickToSelectAllCheckbox();
        clickOnDelete();
    }

    public void editContact(int index,ContactModel groupModel) {
        clickOnEditLinkByIndex(index);
        fillContact(groupModel);
        updateContact();
    }

    public boolean isContactExists(){
        if(driver.findElements(By.cssSelector("tr[name='entry']")).size()>0) {
            return true;
        }else {return false;}
    }

    public Contacts all(){
        List<WebElement> contactElements=driver.findElements(By.cssSelector("tr[name='entry']"));
        Contacts contacts=new Contacts();
        for(WebElement element : contactElements){
            String lastName=element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName=element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String address=element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String email1=element.findElement(By.cssSelector("td:nth-child(5)")).getText();
            String phone=element.findElement(By.cssSelector("td:nth-child(6)")).getText();
            int id=Integer.parseInt(element.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value"));
            contacts.add(new ContactModel(firstName,lastName,address)
                    .withId(id)
                    .withFirstEmail(email1)
                    .withMobilePhoneNumber(phone));
        }
        return contacts;
    }

}
