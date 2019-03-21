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

        /*ResultSet rs = stmt.executeQuery("SELECT * FROM AGENTS");
        while(rs.next()){
            System.out.println("Agentcode:"+rs.getString("AGENT_CODE") + " " + rs.getString("COMMISSION"));
        }*/
        conn.close();


//        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(entities.Agent.class).addResource("entities.Agent.hbm.xml")
//                .buildSessionFactory();

        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        try {
//            persist(sessionFactory);
//            load(sessionFactory);
            load2(sessionFactory);
            load3(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void load(SessionFactory sessionFactory) {
        System.out.println("-- loading persons --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Person> persons = session.createQuery("FROM Person").list();
        persons.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private static void load2(SessionFactory sessionFactory) {
        System.out.println("-- loading agents --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Agent> agents = session.createQuery("FROM entities.Agent").list();
        agents.forEach((x) -> System.out.println(x.getAgentCode() + " " + x.getAgentName() + " " +
                x.getWorkingArea() + " " + x.getCommission() + " " + x.getCountry() + " " + x.getPhoneNumber()));
        session.close();
    }

    private static void load3(SessionFactory sessionFactory) {
        System.out.println("-- loading customers --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Customer> customers = session.createQuery("FROM entities.Customer").list();
        customers.forEach((x) -> System.out.println(x.getCustomerCode() + " " + x.getCustomerCity()));
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        Person p1 = new Person("John", 12);
        Person p2 = new Person("Tina", 13);
        System.out.println("-- persisting persons --");
        System.out.printf("- %s%n- %s%n", p1, p2);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(p1);
        session.save(p2);
        session.getTransaction().commit();
    }

    private static void persist2(SessionFactory sessionFactory) {
        Agent agent = new Agent( "someCode");
        System.out.println("-- persisting agents --");
        System.out.printf("- %s%n - ", agent);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(agent);
        session.getTransaction().commit();
    }
}
