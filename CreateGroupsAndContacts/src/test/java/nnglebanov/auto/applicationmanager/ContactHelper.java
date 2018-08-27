package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.Contacts;
import nnglebanov.auto.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {
    protected ContactHelper(WebDriver driver) {
        super(driver);
    }

    private ContactModel getContact(){
        ContactModel cm=new ContactModel();
        cm.withFirstName(getValue(By.name("firstname")));
        cm.withLastName(getValue(By.name("lastname")));
        cm.withFirstEmail(getValue(By.name("email")));
        cm.withSecondEmail(getValue(By.name("email2")));
        cm.withThirdEmail(getValue(By.name("email3")));
        cm.withAddress(getValue(By.name("address")));
        cm.withHomePhone(getValue(By.name("home")));
        cm.withMobilePhoneNumber(getValue(By.name("mobile")));
        cm.withWorkPhone(getValue(By.name("work")));

        String phones=new String();
        cm.withUncheckedAllEmails(cm.getEmail1()+cm.getEmail2()+cm.getEmail3());

        if(!cm.getHomePhone().equals("")){
            phones+=phoneFilter(cm.getHomePhone());
        }
        if(!cm.getMobilePhone().equals("")){
            if(!cm.getHomePhone().equals("")) {
                phones += "\n";
            }
            phones+=phoneFilter(cm.getMobilePhone());
        }
        if(!cm.getMobilePhone().equals("")) {
            if(!cm.getMobilePhone().equals("")) {
                phones += "\n";
            }
            phones+=phoneFilter(cm.getWorkPhone());
        }
        cm.withUncheckedAllPhones(phones);
        return cm;
    }
    private String phoneFilter(String phone){
        phone=phone.replace("(","");
        phone=phone.replace(")","");
        phone=phone.replace("+","");
        return phone;
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


    public void clickToSelectByIndex(int index) {
        List<WebElement> elements=driver.findElements(By.cssSelector("tr[name='entry']"));
        elements.get(index).findElement(By.cssSelector("input[name='selected[]']")).click();
    }

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

    public ContactModel getContactByIndex(int index){
        WebElement element=driver.findElement(By.xpath("//tr[@name='entry']["+index+"]"));
        element.findElement(By.xpath("//td[8]//a")).click();
        return getContact();
    }

    public ContactModel parseContact(int index){

        WebElement element=driver.findElement(By.xpath("//tr[@name='entry']["+index+"]"));
        String lastName=element.findElement(By.xpath("//td[2]")).getText();
        String firstName=element.findElement(By.xpath("//td[3]")).getText();
        String address=element.findElement(By.xpath("//td[4]")).getText();
        List<WebElement> emails=element.findElements(By.xpath("//td[5]//a"));
        String phones=element.findElement(By.xpath("//td[6]")).getText();

        String allPhones= phones;
        String allEmails=emails.stream().map(e->e.getText()).collect(Collectors.joining());
        System.out.println(allPhones);
        System.out.println(allEmails);

        ContactModel cm=new ContactModel();
        cm.withLastName(lastName);
        cm.withFirstName(firstName);
        cm.withAddress(address);
        cm.withUncheckedAllPhones(allPhones);
        cm.withUncheckedAllEmails(allEmails);
        return cm;
    }

    public void addToGroup(){
        click(By.cssSelector("input[name='add']"));
    }

    public void selectGroupsView(String name){
        Select select=new Select(driver.findElement(By.cssSelector("select[name='group']")));
        select.selectByVisibleText(name);
    }

    public void clickRemoveGroup(){
        click(By.cssSelector("input[name='remove'"));
    }


}
