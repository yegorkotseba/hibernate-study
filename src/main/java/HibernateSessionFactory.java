import entities.Agent;
import entities.Customer;
import entities.Order;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    public static SessionFactory factory;

    private HibernateSessionFactory() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration()
                    .addAnnotatedClass(Agent.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Order.class)
                    .buildSessionFactory();
        }
        return factory;
    }
}
