package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AGENTS")
public class Agent {
    @Id
    @Column(name = "AGENT_CODE")
    private String agentCode;

    @Column(name = "AGENT_NAME")
    private String agentName;

    @Column(name = "WORKING_AREA")
    private String workingArea;

    @Column(name = "COMMISSION")
    private double commission;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "AGENT_CODE")
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "AGENT_CODE")
    private Set<Customer> customers;

    public Agent(){}

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(String workingArea) {
        this.workingArea = workingArea;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Order> getOrders() { return orders; }

    public void setOrders(Set<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        return
                "agentCode " + agentCode +
                " agentName " + agentName +
                " workingArea " + workingArea +
                " commission " + commission +
                " phoneNumber " + phoneNumber +
                " country " + country;
    }
}
