import entities.Agent;
import entities.Customer;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class ExampleMain {

    public static void main(String[] args) throws Exception{

        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE AGENTS");
        stmt.executeUpdate("DROP TABLE CUSTOMER");
        stmt.executeUpdate("DROP TABLE ORDERS");
        RunScript.execute(conn, new FileReader("src/main/resources/database.sql"));
        conn.close();

        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        try {
            loadAgents(sessionFactory);
            loadCustomers(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void loadAgents(SessionFactory sessionFactory) {
        System.out.println("-- loading agents --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Agent> agents = session.createQuery("FROM entities.Agent").list();
        agents.forEach((x) -> System.out.println(x.getAgentCode() + " " + x.getAgentName() + " " +
                x.getWorkingArea() + " " + x.getCommission() + " " + x.getCountry() + " " + x.getPhoneNumber()));
        session.close();
    }

    private static void loadCustomers(SessionFactory sessionFactory) {
        System.out.println("-- loading customers --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Customer> customers = session.createQuery("FROM entities.Customer").list();
        customers.forEach((x) -> System.out.println(x.getCustomerCode() + " " + x.getCustomerCity()));
        session.close();
    }

}
