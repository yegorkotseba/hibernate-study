package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORD_NUM")
    private double orderNumber;

    @Column(name = "ORD_AMOUNT")
    private double orderAmount;

    @Column(name = "ADVANCE_AMOUNT")
    private double advanceAmount;

    @Column(name = "ORD_DATE")
    private Date orderDate;

    @Column(name = "CUST_CODE")
    private String customerCode;

    @Column(name = "AGENT_CODE")
    private String agentCode;

    @Column(name = "ORD_DESCRIPTION")
    private String orderDescription;

    public double getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(double orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    @Override
    public String toString() {
        return
                "orderNumber " + orderNumber +
                " orderAmount " + orderAmount +
                " advanceAmount " + advanceAmount +
                " orderDate " + orderDate +
                " customerCode " + customerCode +
                " agentCode " + agentCode +
                " orderDescription " + orderDescription;
    }
}
