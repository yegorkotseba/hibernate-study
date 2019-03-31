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
}
