import data.DBDataProvider;
import data.HibernateDataProvider;
import entities.Agent;
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
        DbSteps.printAllAgents(sessionFactory);
        DbSteps.printAllCustomers(sessionFactory);
        DbSteps.printAllOrders(sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "newAgent")
    void insertAgent(Agent agent) {
        DbSteps.insertNewAgent(agent, sessionFactory);
        DbSteps.printAllAgents(sessionFactory);
    }

    @AfterClass
    void closeConnection(){
        sessionFactory.close();
    }
}
