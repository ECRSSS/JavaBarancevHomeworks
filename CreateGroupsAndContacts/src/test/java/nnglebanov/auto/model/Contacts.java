package nnglebanov.auto.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactModel> {

    private HashSet<ContactModel> delegate;

    public Contacts() {
        this.delegate = new HashSet<ContactModel>();
    }

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactModel>(contacts.delegate);
    }

    public Contacts(Collection<ContactModel> contacts) {this.delegate=new HashSet<>(contacts);}

    @Override
    protected Set delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactModel contactModel) {
        Contacts contacts = new Contacts(this);
        contacts.add(contactModel);
        return contacts;
    }

    public Contacts without(ContactModel contactModel) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contactModel);
        return contacts;
    }
}
