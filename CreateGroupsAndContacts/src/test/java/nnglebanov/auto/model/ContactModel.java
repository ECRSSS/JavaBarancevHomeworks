package nnglebanov.auto.model;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class ContactModel {

    public ContactModel withId(int id) {
        this.id = id;
        return this;
    }

    public ContactModel() {
        firstName = "";
        lastName = "";
        address = "";
    }

    public ContactModel(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ContactModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' + "}"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactModel that = (ContactModel) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, middleName, lastName, nickName, photoPath, title, company, address, homePhone, mobilePhone, workPhone, fax, email1, email2, email3, homepage, birthday, anniversary, group, secondaryAddress, home, notes);
    }

    private int id;
    @Expose
    private String firstName;
    private String middleName;
    @Expose
    private String lastName;
    private String nickName;
    private String photoPath;
    private String title;
    private String company;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String fax;

    @Expose
    private String email1;
    private String email2;
    private String email3;
    private String homepage;
    private LocalDate birthday;
    private LocalDate anniversary;
    private String group;

    private String secondaryAddress;
    private String home;
    private String notes;

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    private String allPhones;
    private String allEmails;

    public ContactModel withUncheckedAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactModel withUncheckedAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactModel withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactModel withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactModel withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactModel withFirstEmail(String email1) {
        this.email1 = email1;
        return this;
    }


    public ContactModel withSecondEmail(String number) {
        this.email2 = number;
        return this;
    }

    public ContactModel withThirdEmail(String number) {
        this.email3 = number;
        return this;
    }

    public ContactModel withMobilePhoneNumber(String number) {
        this.mobilePhone = number;
        return this;
    }

    public ContactModel withHomePhone(String number) {
        this.homePhone = number;
        return this;
    }

    public ContactModel withWorkPhone(String number) {
        this.workPhone = number;
        return this;
    }


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
