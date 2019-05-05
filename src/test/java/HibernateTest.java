import data.DBDataProvider;
import data.HibernateDataProvider;
import entities.Agent;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DbSteps;

public class HibernateTest {

    @BeforeClass
    void createTables() {
        DBDataProvider.createTables();
    }

    @Test
    void getAllTables(){
        DbSteps.printAllAgents();
        DbSteps.printAllCustomers();
        DbSteps.printAllOrders();
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "newAgent")
    void insertAgent(Agent agent) {
        DbSteps.insertNewAgent(agent);
        DbSteps.printAllAgents();
        DbSteps.deleteAgentByCode(agent.getAgentCode());
        DbSteps.printAllAgents();
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "updateAgent")
    void updateAgent(String agentName, String agentCode) {
        DbSteps.updateAgent(agentName, agentCode);
        DbSteps.printAllAgents();
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "deleteAgent")
    void deleteAgent(String agentCode) {
        DbSteps.deleteAgentByCode(agentCode);
        DbSteps.printAllCustomers();
        DbSteps.printAllOrders();
        DbSteps.printAllAgents();
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "innerJoin")
    void innerJoin(String agentCode) {
        DbSteps.printAllAgents();
        DbSteps.printAllCustomers();
        DbSteps.innerJoin(agentCode);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "leftJoin")
    void leftOuterJoin(double orderAmount) {
        DbSteps.printAllCustomers();
        DbSteps.printAllOrders();
        DbSteps.leftOuterJoin(orderAmount);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "rightJoin")
    void rightOuterJoin(String description) {
        DbSteps.printAllAgents();
        DbSteps.printAllOrders();
        DbSteps.rightOuterJoin(description);
    }

    @Test(dataProviderClass = HibernateDataProvider.class, dataProvider = "count")
    void count(String request) {
        DbSteps.count(request);
    }
}
