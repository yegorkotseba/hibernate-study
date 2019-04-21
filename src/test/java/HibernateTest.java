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

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "updateAgent")
    void updateAgent(String agentName, String agentCode) {
        DbSteps.updateAgent(agentName, agentCode, sessionFactory);
        DbSteps.printAllAgents(sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "deleteAgent")
    void deleteAgent(String agentCode) {
        DbSteps.deleteAgent(agentCode, sessionFactory);
        DbSteps.printAllOrders(sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "innerJoin")
    void innerJoin(String request) {
        DbSteps.innerJoin(request, sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "leftJoin")
    void leftOuterJoin(String request) {
        DbSteps.leftOuterJoin(request, sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "rightJoin")
    void rightOuterJoin(String request) {
        DbSteps.rightOuterJoin(request, sessionFactory);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "count")
    void count(String request) {
        DbSteps.count(request, sessionFactory);
    }

    @AfterClass
    void closeConnection(){
        sessionFactory.close();
    }
}
