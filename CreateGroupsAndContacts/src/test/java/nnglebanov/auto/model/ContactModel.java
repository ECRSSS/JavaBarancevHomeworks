package nnglebanov.auto.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "addressbook")
public class ContactModel {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupModel> groups=new HashSet<>();
    @Id
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Transient
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Transient
    private String nickName;
    @Transient
    private String photoPath;
    @Transient
    private String title;
    @Transient
    private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String fax;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Transient
    private String homepage;
    @Transient
    private LocalDate birthday;
    @Transient
    private LocalDate anniversary;


    @Column(name = "address2")
    @Type(type = "text")
    private String secondaryAddress;
    @Transient
    private String home;
    @Transient
    private String notes;

    public ContactModel withId(int id) {
        this.id = id;
        return this;
    }

    public Groups getGroups(){
        return new Groups(groups);
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

        return Objects.hash(id, firstName, middleName, lastName, nickName, photoPath, title, company, address, homePhone, mobilePhone, workPhone, fax, email1, email2, email3, homepage, birthday, anniversary, secondaryAddress, home, notes);
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String calculateAllPhones(){
       return Arrays.asList(this.getHomePhone(),this.getMobilePhone(),this.getWorkPhone())
                .stream().filter(s->!s.equals("")).collect(Collectors.joining("\n"));
    }
    public String calculateAllEmails(){
        String emails="";
        if(email1!=null){emails+=email1;}
        if(email2!=null){emails+=email2;}
        if(email3!=null){emails+=email3;}
        return emails;
    }

    @Transient
    private String allPhones;
    @Transient
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
