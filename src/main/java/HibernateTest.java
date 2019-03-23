import org.h2.tools.RunScript;
import org.hibernate.SessionFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HibernateTest {

    public static final SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    @BeforeClass
    void prepareData() throws Exception{
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "","");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE AGENTS");
        stmt.executeUpdate("DROP TABLE CUSTOMER");
        stmt.executeUpdate("DROP TABLE ORDERS");
        RunScript.execute(conn, new FileReader("src/main/resources/database.sql"));
        conn.close();
    }

    @Test
    void getAllTables(){

        DbService.loadAgents(sessionFactory);
        DbService.loadCustomers(sessionFactory);
    }

    @AfterClass
    void closeConnection(){
        sessionFactory.close();
    }
}
