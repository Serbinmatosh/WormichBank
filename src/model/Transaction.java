/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author serbinmatosh
 */
public class Transaction {
    private int empId;
    private int clientId;
    private String transType;
    private String fromAccount;
    private String toAccount;
    private int amount;
    private Date DOT;
    private String status;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(int empId, int clientId, String transType, String fromAccount, String toAccount, int amount, Date DOT, String status, int id) {
        this.empId = empId;
        this.clientId = clientId;
        this.transType = transType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.DOT = DOT;
        this.status = status;
        this.id = id;
    }

    public Transaction(Date DOT, String transType, String fromAccount, String toAccount, int amount, String status) {
        this.DOT = DOT;
        this.transType = transType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
        
    }
    
    public Transaction(int id, Date DOT, String transType, String fromAccount, String toAccount, int amount, String status) {
        this.id = id;
        this.DOT = DOT;
        this.transType = transType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
        
    }
    
    

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }



    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDOT() {
        Date date = DOT;
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String dot = df.format(date);
        return dot;
    }

    public void setDOT(Date DOT) {
        this.DOT = DOT;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" + "empId=" + empId + ", clientId=" + clientId + ", transType=" + transType + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + ", DOT=" + DOT + ", status=" + status + ", id=" + id + '}';
    }

    

    
    
}
