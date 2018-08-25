package nnglebanov.auto.tests;

import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.GroupModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HibernateTests {
    private SessionFactory sessionFactory;

    @BeforeMethod
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }


    @Test
    public void groupsTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupModel> result = session.createQuery( "from GroupModel" ).list();
        for ( GroupModel model : result ) {
            System.out.println(model);
        }
        session.getTransaction().commit();
        session.close();
    }
    @Test
    public void contactsTest(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactModel> result = session.createQuery( "from ContactModel where deprecated='0000-00-00'" ).list();
        for ( ContactModel model : result ) {
            System.out.println(model);
        }
        session.getTransaction().commit();
        session.close();
    }
}
