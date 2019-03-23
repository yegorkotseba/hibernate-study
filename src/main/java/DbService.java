import entities.Agent;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DbService {

    public static void loadAgents(SessionFactory sessionFactory) {
        System.out.println("-- loading agents --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Agent> agents = session.createQuery("FROM entities.Agent").list();
        agents.forEach((x) -> System.out.println(x.getAgentCode() + " " + x.getAgentName() + " " +
                x.getWorkingArea() + " " + x.getCommission() + " " + x.getCountry() + " " + x.getPhoneNumber()));
        session.close();
    }

    public static void loadCustomers(SessionFactory sessionFactory) {
        System.out.println("-- loading customers --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Customer> customers = session.createQuery("FROM entities.Customer").list();
        customers.forEach((x) -> System.out.println(x.getCustomerCode() + " " + x.getCustomerCity()));
        session.close();
    }
}
