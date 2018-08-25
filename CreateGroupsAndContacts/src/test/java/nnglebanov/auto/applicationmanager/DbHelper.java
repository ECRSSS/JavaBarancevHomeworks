package nnglebanov.auto.applicationmanager;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.Contacts;
import nnglebanov.auto.model.GroupModel;
import nnglebanov.auto.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {


    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupModel> result = session.createQuery( "from GroupModel" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }
    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactModel> result = session.createQuery( "from ContactModel where deprecated='0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
}
