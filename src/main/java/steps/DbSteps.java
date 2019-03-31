package steps;

import entities.Agent;
import entities.Customer;
import entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DbSteps {

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

    public static void loadOrders(SessionFactory sessionFactory) {
        System.out.println("-- loading orders --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Order> orders = session.createQuery("FROM entities.Order").list();
        orders.forEach((x) -> System.out.println(x.getOrderNumber() + " " + x.getOrderDescription()));
        session.close();
    }
}
