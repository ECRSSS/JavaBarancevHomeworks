package nnglebanov.auto.model;

import java.time.LocalDate;
import java.time.Month;

public class ContactModel {
    private String firstName = "TestFirstName";
    private String middleName = "TestMiddleName";
    private String lastName = "TestLastName";
    private String nickName = "TestNickName";
    private String photoPath;
    private String title = "TestTitle";
    private String company = "TestCompany";
    private String address = "TestAddress";

    private String homePhone = "89990000000";
    private String mobilePhone = "89990000000";
    private String workPhone = "89990000000";
    private String fax = "89990000000";

    private String email1 = "Testmail1@mail.ru";
    private String email2 = "Testmail2@mail.ru";
    private String email3 = "Testmail3@mail.ru";
    private String homepage = "www.test-homepage.ru";
    private LocalDate birthday = LocalDate.of(1996, Month.FEBRUARY, 15);
    private LocalDate anniversary = LocalDate.of(1996, Month.FEBRUARY, 15);
    private String group="1";

    private String secondaryAddress = "TestAddress";
    private String home = "TestHome";
    private String notes = "TestNotes";

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getAnniversary() {
        return anniversary;
    }

    public String getGroup() {
        return group;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public String getHome() {
        return home;
    }

    public String getNotes() {
        return notes;
    }
}