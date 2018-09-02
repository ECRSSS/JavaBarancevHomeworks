package nnglebanov.auto.mantis.applicationmanager;

import nnglebanov.auto.mantis.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {


    private final SessionFactory sessionFactory;

    public DBHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    public List<UserModel> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserModel> result = session.createQuery( "from UserModel" ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
