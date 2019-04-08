package data;

import entities.Agent;
import org.testng.annotations.DataProvider;

public class HibernateDataProvider {

    @DataProvider
    public static Object[][] newAgent() {
        Agent agent = new Agent();
        agent.setAgentCode("acode");
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
    public static Object[][] innerJoin() {
        String hql = "from Agent as ag inner join ag.customers where ag.agentCode = 'A001'";
        return new Object[][]{
                {hql}
        };
    }

    @DataProvider
    public static Object[][] leftJoin() {
        String hql = "from Customer as cust left outer join cust.orders as ord where cust.customerName = 'Holmes'";
        return new Object[][]{
                {hql}
        };
    }

    @DataProvider
    public static Object[][] rightJoin() {
        String hql = "from Agent as ag right outer join ag.orders as ord where ag.agentCode = 'A001'";
        return new Object[][]{
                {hql}
        };
    }
}
