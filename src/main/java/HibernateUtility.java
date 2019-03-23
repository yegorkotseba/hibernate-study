import entities.Agent;
import entities.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    public static SessionFactory factory;

    private HibernateUtility() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration()
                    .addAnnotatedClass(Agent.class)
                    .addAnnotatedClass(Customer.class)
                    .buildSessionFactory();
        }
        return factory;
    }
}
