/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serbinmatosh
 */
public class Account {
    private int client_id;
    private String accountNumber;
    private String pin;
    private int empId;
    private long balance;
    private Date DOC;
    
    public Account(){}
    
    public Account(int client_id, String accountNumber, String pin, int empId, long balance, Date DOC) {
        this.client_id = client_id;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.empId = empId;
        this.balance = balance;
        this.DOC = DOC;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getDOC() {
        Date date = DOC;
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String doc = df.format(date);
        return doc;
    }

    public void setDOC(Date DOC) {
        this.DOC = DOC;
    }

    @Override
    public String toString() {
        return "Account{" + "client_id=" + client_id + ", accountNumber=" + accountNumber +
                ", pin=" + pin + ", empId=" + empId + ", balance=" + balance + ", DOC=" + DOC + '}';
    }
    
    public Account resultSetAccount(ResultSet rs) {
        try {
            return new Account(rs.getInt("id"), rs.getString("accountNumber"),
                    rs.getString("PIN"), rs.getInt("emp_id"),
                    rs.getLong("balance"), rs.getDate("DOC"));
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
