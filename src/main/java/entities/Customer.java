package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "CUST_CODE")
    private String customerCode;

    @Column(name = "CUST_NAME")
    private String customerName;

    @Column(name = "CUST_CITY")
    private String customerCity;

    @Column(name = "WORKING_AREA")
    private String workingArea;

    @Column(name = "CUST_COUNTRY")
    private String customerCountry;

    @Column(name = "GRADE")
    private double grade;

    @Column(name = "OPENING_AMT")
    private double openingAmt;

    @Column(name = "RECEIVE_AMT")
    private double receiveAmt;

    @Column(name = "PAYMENT_AMT")
    private double paymentAmt;

    @Column(name = "OUTSTANDING_AMT")
    private double outstandingAmt;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "AGENT_CODE")
    private String agentCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUST_CODE")
    private Set<Order> orders;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(String workingArea) {
        this.workingArea = workingArea;
    }

    public String getCustomerCountry() { return customerCountry; }

    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getOpeningAmt() {
        return openingAmt;
    }

    public void setOpeningAmt(double openingAmt) {
        this.openingAmt = openingAmt;
    }

    public double getReceiveAmt() {
        return receiveAmt;
    }

    public void setReceiveAmt(double receiveAmt) {
        this.receiveAmt = receiveAmt;
    }

    public double getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(double paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public double getOutstandingAmt() {
        return outstandingAmt;
    }

    public void setOutstandingAmt(double outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAgentCode() { return agentCode; }

    public void setAgentCode(String agentCode) { this.agentCode = agentCode; }

    public Set<Order> getOrders() { return orders; }

    public void setOrders(Set<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        return
                "customerCode " + customerCode +
                " customerName " + customerName +
                " customerCity " + customerCity +
                " workingArea" + workingArea +
                " customerCountry" + customerCountry +
                " grade" + grade +
                " openingAmt" + openingAmt +
                " receiveAmt" + receiveAmt +
                " paymentAmt" + paymentAmt +
                " outstandingAmt" + outstandingAmt +
                " phoneNumbers" + phoneNumber +
                " agentCode" + agentCode;
    }
}
