package steps;

import entities.Agent;
import entities.Customer;
import entities.Order;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import session.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DbSteps {

    public static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final String SPACE = " ";

    public static void printAllAgents() {
        System.out.println("-- printing agents --");
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(Agent.class);
        List<Agent> agents = crit.list();
        agents.forEach((agent) -> System.out.println(agent.toString()));
        session.close();
    }

    public static void printAllCustomers() {
        System.out.println("-- printing customers --");
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(Customer.class);
        List<Customer> customers = crit.list();
        customers.forEach((customer) -> System.out.println(customer.toString()));
        session.close();
    }

    public static void printAllOrders() {
        System.out.println("-- printing orders --");
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(Order.class);
        List<Order> orders = crit.list();
        orders.forEach((order) -> System.out.println(order.toString()));
        session.close();
    }

    public static void innerJoin(String agentCode) {
        System.out.println("-- joining tables --");
        Session session = sessionFactory.openSession();

        List<Customer> customers = new ArrayList<>();
        Criteria criteria = session.createCriteria(Agent.class);
        Criteria nestedCriteria = criteria.createCriteria("customers", JoinType.INNER_JOIN);
        nestedCriteria.add(Restrictions.eq("agentCode", agentCode));
        List<Agent> agents = criteria.list();
        customers.addAll(agents.get(0).getCustomers());
        customers.forEach(customer -> {
            System.out.println("Customer: " + customer.toString() + "\n" + "Related Agent: " + agents.get(0).toString());
        });
        session.close();
    }

    public static void leftOuterJoin(double orderAmount) {
        System.out.println("-- left join loading --");
        Session session = sessionFactory.openSession();
        List<Order> orders = new ArrayList<>();
        Criteria criteria = session.createCriteria(Customer.class);
        Criteria nestedCriteria = criteria.createCriteria("orders", JoinType.LEFT_OUTER_JOIN);
        nestedCriteria.add(Restrictions.eq("orderAmount", orderAmount));
        List<Customer> customers = criteria.list();
        customers.forEach(customer -> {
            orders.addAll(customer.getOrders());
            orders.forEach(order -> {
                System.out.println("Order: " + order.toString());
            });
            System.out.println("Related Customer: " + customer.toString());
        });
        session.close();
    }

    public static void rightOuterJoin(String description) {
        System.out.println("-- right join loading --");
        Session session = sessionFactory.openSession();
        List<Order> orders = new ArrayList<>();
        Criteria criteria = session.createCriteria(Agent.class);
        Criteria nestedCriteria = criteria.createCriteria("orders", JoinType.RIGHT_OUTER_JOIN);
        nestedCriteria.add(Restrictions.eq("orderDescription", description));
        List<Agent> agents = criteria.list();
        agents.forEach(ag -> {
            orders.addAll(ag.getOrders());
            orders.forEach(order -> {
                System.out.println("Order: " + order.toString());
            });
            System.out.println("Related Agent: " + ag.toString());
        });
        session.close();
    }

    public static void count(String request) {
        System.out.println("-- count loading --");
        Session session = sessionFactory.openSession();
        List result = session.createSQLQuery(request).list();
        System.out.println(result.toString());
        session.close();
    }

    public static void insertNewAgent(Agent agent) {
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

    public static void updateAgent(String agentName, String agentCode) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Agent agent = (Agent) session.load(Agent.class, agentCode);
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

    public static void deleteOrdersByAgentCode(String agentCode) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Order.class);
            crit.add(Restrictions.eq("agentCode",agentCode));
            List<Order> orders = crit.list();
            orders.forEach(order -> session.delete(order));
            orders.forEach((order) -> System.out.println("Deleted orders " + order.toString()));
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

    public static void deleteCustomersByAgentCode(String agentCode) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Customer.class);
            crit.add(Restrictions.eq("agentCode",agentCode));
            List<Customer> customers = crit.list();
            customers.forEach(customer -> session.delete(customer));
            customers.forEach((customer) -> System.out.println("Deleted customers " + customer.toString()));
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

    public static void deleteAgentsByAgentCode(String agentCode) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Agent.class);
            crit.add(Restrictions.eq("agentCode",agentCode));
            List<Agent> agents = crit.list();
            agents.forEach(agent -> session.delete(agent));
            agents.forEach((agent) -> System.out.println("Deleted agents " + agent.toString()));
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

    public static void deleteAgentByCode(String agentCode) {
        deleteOrdersByAgentCode(agentCode);
        deleteCustomersByAgentCode(agentCode);
        deleteAgentsByAgentCode(agentCode);
    }
}
