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
        agents.forEach((agent) -> System.out.println(agent.toString()));
        session.close();
    }

    public static void printAllCustomers(SessionFactory sessionFactory) {
        System.out.println("-- printing customers --");
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("FROM entities.Customer").list();
        customers.forEach((customer) -> System.out.println(customer.toString()));
        session.close();
    }

    public static void printAllOrders(SessionFactory sessionFactory) {
        System.out.println("-- printing orders --");
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("FROM entities.Order").list();
        orders.forEach((order) -> System.out.println(order.toString()));
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
            System.out.println(agent.toString() + SPACE + "\n" + customer.toString());
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
            System.out.println(customer.toString() + SPACE + "\n" + order.toString());
        }
        session.close();
    }

    public static void rightOuterJoin(String request, SessionFactory sessionFactory) {
        System.out.println("-- right join loading --");
        Session session = sessionFactory.openSession();
        List<?> list = session.createQuery(request).list();
        for(int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            Agent agent = (Agent) row[0];
            Order order = (Order) row[1];
            System.out.println(agent.toString() + SPACE + "\n" + order.toString());
        }
        session.close();
    }

    public static void count(String request, SessionFactory sessionFactory) {
        System.out.println("-- count loading --");
        Session session = sessionFactory.openSession();
        List result = session.createSQLQuery(request).list();
        System.out.println(result.toString());
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

    public static void updateAgent(String agentName, String agentCode, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Agent agent = session.load(Agent.class, agentCode);
            agent.setAgentName(agentName);
            session.update(agent);
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

    public static void deleteAgent(String agentCode, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            List<Order> orders = session.createQuery("from Order where agentCode = '" + agentCode + "'").list();
            orders.forEach(order -> session.delete(order));
            List<Order> orders1 = session.createQuery("from Order where agentCode = '" + agentCode + "'").list();
            orders1.forEach(order -> System.out.println("Orders " + order.toString()));
            tx.commit();
        }
        catch (HibernateException ex)
        {
            if (tx != null)
                tx.rollback();
        }
        finally
        {
            session.close();
        }
    }
}
