package steps;

import entities.Agent;
import entities.Customer;
import entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DbSteps {

    private static final String SPACE = " ";

    public static void printAllAgents(SessionFactory sessionFactory) {
        System.out.println("-- printing agents --");
        Session session = sessionFactory.openSession();
        List<Agent> agents = session.createQuery("FROM entities.Agent").list();
        agents.forEach((x) -> System.out.println(x.getAgentCode() + SPACE + x.getAgentName() + SPACE +
                x.getWorkingArea() + SPACE + x.getCommission() + SPACE + x.getCountry() + SPACE + x.getPhoneNumber()));
        session.close();
    }

    public static void printAllCustomers(SessionFactory sessionFactory) {
        System.out.println("-- printing customers --");
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("FROM entities.Customer").list();
        customers.forEach((x) -> System.out.println(x.getCustomerCode() + SPACE +
                x.getCustomerName() + SPACE +
                x.getCustomerCity() + SPACE +
                x.getWorkingArea() + SPACE +
                x.getCustomerCountry() + SPACE +
                x.getGrade() + SPACE +
                x.getOpeningAmt() + SPACE +
                x.getReceiveAmt() + SPACE +
                x.getPaymentAmt() + SPACE +
                x.getOutstandingAmt() + SPACE +
                x.getPhoneNumber() + SPACE +
                x.getAgentCode()));
        session.close();
    }

    public static void printAllOrders(SessionFactory sessionFactory) {
        System.out.println("-- printing orders --");
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("FROM entities.Order").list();
        orders.forEach((x) -> System.out.println(x.getOrderNumber() + SPACE +
                x.getOrderAmount() + SPACE +
                x.getAdvanceAmount() + SPACE +
                x.getOrderDate() + SPACE +
                x.getCustomerCode() + SPACE +
                x.getAgentCode() + SPACE +
                x.getOrderDescription()));
        session.close();
    }
}
