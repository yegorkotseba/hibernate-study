package steps;

import entities.Agent;
import entities.Customer;
import entities.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    public static void innerJoin(String request, SessionFactory sessionFactory) {
        System.out.println("-- joining tables --");
        Session session = sessionFactory.openSession();
        List<?> list = session.createQuery(request).list();
        for(int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            Agent agent = (Agent) row[0];
            Customer customer = (Customer) row[1];
            System.out.println("AgentCode: "+agent.getAgentCode()+", AgentName: "+ agent.getAgentName()+
                    ", CustomerName:"+ customer.getCustomerName()+", CustomerPhone:"+ customer.getPhoneNumber());
        }
        session.close();
    }

    public static void leftOuterJoin(String request, SessionFactory sessionFactory) {
        System.out.println("-- left join loading --");
        Session session = sessionFactory.openSession();
        List<?> list = session.createQuery(request).list();
        for(int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            Customer customer = (Customer) row[0];
            Order order = (Order) row[1];
            System.out.println("CustomerCode: "+customer.getCustomerCode() +", CustomerName: "+ customer.getCustomerName()+
                    ", Order number:"+ order.getOrderNumber()+", get order date:"+ order.getOrderDate());
        }
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

    public static void insertNewAgent(Agent agent, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.save(agent);
            tx.commit();
        }
        catch (HibernateException ex)
        {
            if(tx != null)
                tx.rollback();
        }
        finally
        {
            session.close();
        }
    }
}
