package data;

import entities.Agent;
import org.testng.annotations.DataProvider;

public class HibernateDataProvider {

    @DataProvider
    public static Object[][] newAgent() {
        Agent agent = new Agent();
        agent.setAgentCode("acode3");
        agent.setAgentName("aname");
        agent.setWorkingArea("warea");
        agent.setCommission(2.5);
        agent.setPhoneNumber("1234567");
        agent.setCountry("UA");
        return new Object[][]{
                {agent}
        };
    }

    @DataProvider
    public static Object[][] updateAgent() {
        String name = "Update_Agent_Name21";
        String code = "A001";
        return new Object[][]{
                {name, code}
        };
    }

    @DataProvider
    public static Object[][] deleteAgent() {
        String code = "A012";
        return new Object[][]{
                {code}
        };
    }

    @DataProvider
    public static Object[][] innerJoin() {
        String agentCode = "A005";
        return new Object[][]{
                {agentCode}
        };
    }

    @DataProvider
    public static Object[][] leftJoin() {
        double orderAmount = 1000.00;
        return new Object[][]{
                {orderAmount}
        };
    }

    @DataProvider
    public static Object[][] rightJoin() {
//        String hql = "from Agent as ag right outer join ag.orders as ord where ag.agentCode = 'A001'";
        String description = "dos";
        return new Object[][]{
                {description}
        };
    }

    @DataProvider
    public static Object[][] count() {
        String sql = "SELECT COUNT(ORD_AMOUNT) FROM ORDERS";
        return new Object[][]{
                {sql}
        };
    }
}
