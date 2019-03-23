import data.DBDataProvider;
import org.hibernate.SessionFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DbSteps;

public class HibernateTest {

    public static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @BeforeClass
    void prepareData() {
        DBDataProvider.createTables();
    }

    @Test
    void getAllTables(){
        DbSteps.loadAgents(sessionFactory);
        DbSteps.loadCustomers(sessionFactory);
    }

    @AfterClass
    void closeConnection(){
        sessionFactory.close();
    }
}
