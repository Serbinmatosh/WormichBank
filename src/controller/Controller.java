/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.User;
import java.util.Date;
import model.Account;
import model.Transaction;
/**
 *
 * @author serbinmatosh
 */
public class Controller {
    private String url ="jdbc:mysql://localhost:3306/Test0128";
    private String dbUser = "root";
    private String pass = "Tiranosaurus1";
    private static Controller instance;
    private Date currentDate = java.sql.Date.valueOf(java.time.LocalDate.now());

    
    
    public Controller() {}
    
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
            return instance;
        } 
        return instance;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }
    // Methods for creating Users, Clients, and Accounts
    // Method addClient calls method addAccount inside of it
    
    public User addEmployee (User user) {
        User userAdded = null;
        
        String query_insert = "INSERT INTO Employees "
                        + "(username, password, firstName, lastName, email, DOB, sex, emp_authorization, photo )\n"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String query_check = "SELECT * FROM Employees WHERE username = ?";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getLastName());
                    ps.setString(5, user.getEmail());
                    ps.setString(6, user.getDob());
                    ps.setString(7, user.getSex());
                    ps.setString(8, user.getAuthorization());
                    ps.setBytes(9, user.getPhoto());

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setString(1, user.getUsername());                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            userAdded = new User().resultSetUser(rs);
                        return user;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return userAdded;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userAdded;
    }
    
    public Client addClient (Client client) {
        Account account = null;
        Client clientSend = null;           
            
        int maxId = maxId("Clients", "id");
       
        String query_insert = "INSERT INTO Clients "
                        + "(firstName, lastName, email, DOB, street, city, zip, sex, photo)\n"
                        + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        String query_check = "SELECT * FROM Clients WHERE id > ? ;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);

                    ps.setString(1, client.getFirstName());
                    ps.setString(2, client.getLastName());
                    ps.setString(3, client.getEmail());
                    ps.setString(4, client.getDob());
                    ps.setString(5, client.getStreet());
                    ps.setString(6, client.getCity());
                    ps.setString(7, client.getZip());
                    ps.setString(8, client.getSex());
                    ps.setBytes(9, client.getClientPhoto());
                    
                    int rezultat = ps.executeUpdate();

                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, maxId);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            account = addAccount(rs.getInt("id"), client.getAccount().getAccountNumber(),
                                                    client.getAccount().getPin(), client.getAccount().getEmpId(),
                                                    client.getAccount().getBalance());
                            if(account != null) {
                                clientSend = new Client().resultSetClient(rs, account);
                                return clientSend;
                            }    
                        }

                        con.rollback();
                        return clientSend;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientSend;
    }
    
    public Account addAccount (int id, String accountNumber, String PIN,
                                    int emp_id, long balance) {
        Account account = null;
        
        // Parsing formatting


        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DoC = df.format(currentDate);

        String query_insert = "INSERT INTO Accounts "
                        + "(id, accountNumber, PIN, emp_id, balance, DOC)\n"
                        + "VALUES(?, ?, ?, ?, ?, ?)";
        
        String query_check = "SELECT * FROM Accounts WHERE id = ?";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setInt(1, id);
                    ps.setString(2, accountNumber);
                    ps.setString(3, PIN);
                    ps.setInt(4, emp_id);
                    ps.setLong(5, 0);
                    ps.setString(6, DoC);

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, id);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            account = new Account().resultSetAccount(rs);
                        return account;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return account;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
    
    public User login(String username, String password) {
        User user = null;

        String query = "SELECT * from Employees WHERE username='"+username+"' and password='"+password+"';";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        user = new User().resultSetUser(rs);
                        return user;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public Client accessClient(String accountNumber, String PIN) {
        Client client = null;
        Account account = null;
        
        String query = "SELECT * from clientaccounts WHERE accountNumber='"+accountNumber+"' and pin='"+PIN+"';";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        account = new Account().resultSetAccount(rs);
                        client = new Client().resultSetClient(rs, account);
                        return client;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }
    
    // method maxId that checks for the max Id number in the given table
    // this method is used for adding a new row and checking if its truly added
    
    public Integer maxId(String tableName, String id) {
        Integer lastId = null;
        
        String query = "SELECT MAX("+id+") last_id FROM "+tableName+";";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        lastId = rs.getInt("last_id");
                        return lastId;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }
    
    // this Method is used to check if accountNumbers are available or taken
    // aka is they exist
    
    public boolean checkForAccount(String accountNumber) {
        String query = "SELECT * from Accounts WHERE accountNumber='"+accountNumber+"'";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        return true;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // this method checks if the Username is available/ if it exists
    public boolean checkUsername(String username) {
        String query = "SELECT * from Employees WHERE username='"+username+"'";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        return true;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public User updateEmployee (User user) {
        User userUpdate = null;


        String query_insert = "UPDATE Employees \n" +
                            "SET username = ?, password = ?, firstName = ?, lastName = ?,"+
                            "email = ?, DOB = ?, sex = ?, emp_authorization = ?, photo = ? \n" +
                            "WHERE id = ?";
        
        String query_check = "SELECT * FROM Employees WHERE username = ?";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getLastName());
                    ps.setString(5, user.getEmail());
                    ps.setString(6, user.getDob());
                    ps.setString(7, user.getSex());
                    ps.setInt(8, Integer.parseInt(user.getAuthorization()));
                    ps.setBytes(9, user.getPhoto());
                    ps.setInt(10, user.getId());
                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setString(1, user.getUsername());                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            user = new User().resultSetUser(rs);
                        return user;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return user;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Client updateClient (Client client) {
        Client clientSend = null;

        String query_insert = "UPDATE Clients \n" +
                            "SET firstName = ?, lastName = ?, email = ?, DOB = ?,"+
                            "street = ?, city = ?, zip = ?, sex = ?, photo = ? \n" +
                            "WHERE id = ?";
        
        String query_check = "SELECT * FROM CLients WHERE id = ?";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, client.getFirstName());
                    ps.setString(2, client.getLastName());
                    ps.setString(3, client.getEmail());
                    ps.setString(4, client.getDob());
                    ps.setString(5, client.getStreet());
                    ps.setString(6, client.getCity());
                    ps.setString(7, client.getZip());
                    ps.setString(8, client.getSex());
                    ps.setBytes(9, client.getClientPhoto());
                    ps.setInt(10, client.getId());
                            
                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, client.getId());                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();                            
                            Account account = updateAccount(client.getAccount());
                            clientSend = new Client().resultSetClient(rs, account);
                        return client;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return clientSend;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientSend;
    }
    
    public Account updateAccount(Account account) {
        Account updatedAccount = null;
        
        String query_insert = "UPDATE Accounts \n" +
                            "SET PIN = ? \n" +
                            "WHERE id = ?";
        
        String query_check = "SELECT * FROM Accounts WHERE id = ?";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, account.getPin());
                    ps.setInt(2, account.getClient_id());

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, account.getClient_id());                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();                            
                            updatedAccount = new Account().resultSetAccount(rs);
                        return updatedAccount;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return updatedAccount;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedAccount;
    }
    
    public Client depositMoney(String accountNumber, String depositAmount, int EmpId) {
        Client client = null;
        
        String query_deposit = "UPDATE Accounts a\n" +
                                "SET balance = a.balance + ?" +
                                "WHERE accountNumber = ?;";
        
        String query_check = "SELECT * FROM clientaccounts WHERE accountNumber =?;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_deposit);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, depositAmount);
                    ps.setString(2, accountNumber);
                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setString(1, accountNumber);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            Account account = new Account().resultSetAccount(rs);
                            client = new Client().resultSetClient(rs, account);
                            insertDepositTransaction(client, EmpId, depositAmount);
                        return client;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return client;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;  
    }
    
    public Client withdrawMoney(String accountNumber, String withdrawAmount, int empId) {
        Client client = null;

        boolean gotsMoney = hasEnough(accountNumber, withdrawAmount);
        if (!gotsMoney) {
            return client;
        }        
        String query_withdraw = "UPDATE Accounts a\n" +
                                "SET balance = a.balance - ?" +
                                "WHERE accountNumber = ?;";
        
        String query_check = "SELECT * FROM clientaccounts WHERE accountNumber =?;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_withdraw);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setString(1, withdrawAmount);
                    ps.setString(2, accountNumber);
                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setString(1, accountNumber);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            Account account = new Account().resultSetAccount(rs);
                            client = new Client().resultSetClient(rs, account);
                            insertWitdrawalTransaction(client, empId, withdrawAmount);
                        return client;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return client;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;  
    }
    
    public User accessUser(int id) {
        User user = null;
        
        String query = "SELECT * from Employees WHERE id ="+id+";";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        user = new User().resultSetUser(rs);
                        return user;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public boolean hasEnough(String accountNumber, String withdrawAmount) {
        Client client = accessByAcctNum(accountNumber);
        if(client == null) {
            return false;
        }  
        long currentBalance = client.getAccount().getBalance();
        long withdrawAmt = Long.parseLong(withdrawAmount);
        
        if(withdrawAmt > currentBalance) {     
            return false;
        }
        return true;
    }

    public Account accessAccount(int id) {
        Account account = null;
        String query = "SELECT * FROM Accounts WHERE id='"+id+"';";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        account = new Account().resultSetAccount(rs);
                        return account;
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
    public Client accessByAcctNum(String accountNumber) {
        Client client = null;
        Account account = null;
        String query = "SELECT * FROM clientaccounts WHERE accountNumber='"+accountNumber+"';";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        account = new Account().resultSetAccount(rs);
                        client = new Client().resultSetClient(rs, account);
                        return client;
                    }
            return client;
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
}

    public Client transferMoney(String toAccount, String fromAccount, String transferAmount, int empId) {
        Client client = withdrawMoney(fromAccount, transferAmount, empId);
        depositMoney(toAccount, transferAmount, empId);
        insertTransferTransaction(client, empId, transferAmount, toAccount);
        return client;
    }

    public boolean requestMoney(int empId, int clientId, String fromAccount, String toAccount, String requestAmount) {
        boolean sent = hasEnough(toAccount, requestAmount);
        if(!sent) {
            return false;
        } 
   
        int maxId = maxId("Transactions", "trans_id");
        // Parsing formatting
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DOT = df.format(currentDate);
        int amount = Integer.parseInt(requestAmount);
        
        String query_insert = "INSERT INTO Transactions "
                        + "(emp_id, client_id, transactionType, fromAccount, toAccount, amount, DOT, status)\n"
                        + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        String query_check = "SELECT * FROM Transactions WHERE trans_id > ? ;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                   
                    ps.setInt(1, empId);
                    ps.setInt(2, clientId);
                    ps.setString(3, "Request");
                    ps.setString(4, fromAccount);
                    ps.setString(5, toAccount);
                    ps.setInt(6, amount);
                    ps.setString(7, DOT);
                    ps.setString(8, "Pending");
                    
                    int rezultat = ps.executeUpdate();

                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, maxId);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();
                            
                            return true;
                        }    
                        
                        con.rollback();

                        return false;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
                
    public ArrayList<Transaction> allTransactions(String accountNumber) {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        Transaction transaction;
        String query = "SELECT emp_id, client_id, transactionType, fromAccount, toAccount, amount, DOT, status, trans_id FROM Transactions t \n" +
                            "where toAccount = '"+accountNumber+"' OR fromAccount = '"+accountNumber+"' ORDER BY trans_id DESC;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();) {   
                    ResultSet rs = statement.executeQuery(query);
                    
                    while(rs.next()) {
                        transaction = new Transaction ( rs.getInt("emp_id"), rs.getInt("client_id"), 
                                                        rs.getString("transactionType"), rs.getString("fromAccount"),
                                                        rs.getString("toAccount"), rs.getInt("amount"), rs.getDate("DOT"),
                                                        rs.getString("status"), rs.getInt("trans_id"));
                        allTransactions.add(transaction); 
                    }
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTransactions;
    }
    
    public int numOfRequestsPending(String accountNumber) {
        int count;
        Transaction transaction;
        
        String query = "SELECT count(r.trans_id) as count from requestreceived r \n" +
                            "where r.toAccount = '"+accountNumber+"' AND r.status = 'Pending' ;";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        count = rs.getInt("count");
                        
                        return count;
                        
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count = 0;
    }
    
    public boolean insertDepositTransaction(Client client, int empId, String depositAmount) {
        boolean success = true;
        
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DOT = df.format(currentDate);
        int maxId = maxId("Transactions", "trans_id");
        String query_insert = "INSERT INTO Transactions "
                        + "(emp_id, client_id, transactionType, fromAccount, toAccount, amount, DOT, status )\n"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        String query_check = "SELECT * FROM Transactions WHERE trans_id > ? ;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setInt(1, empId);
                    ps.setInt(2, client.getId());
                    ps.setString(3, "Deposit");
                    ps.setString(4, "");
                    ps.setString(5, client.getAccount().getAccountNumber());
                    ps.setString(6, depositAmount);
                    ps.setString(7, DOT);
                    ps.setString(8, "Approved");

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, maxId);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();    
                        return success;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return false;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;        
    }

    public boolean insertWitdrawalTransaction(Client client, int empId, String withdrawAmount) {
        boolean success = true;
        
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DOT = df.format(currentDate);
        int maxId = maxId("Transactions", "trans_id");
        
        String query_insert = "INSERT INTO Transactions "
                        + "(emp_id, client_id, transactionType, fromAccount, toAccount, amount, DOT, status )\n"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        String query_check = "SELECT * FROM Transactions WHERE trans_id > ? ;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setInt(1, empId);
                    ps.setInt(2, client.getId());
                    ps.setString(3, "Withdrawal");
                    ps.setString(4, client.getAccount().getAccountNumber());
                    ps.setString(5, "");
                    ps.setString(6, withdrawAmount);
                    ps.setString(7, DOT);
                    ps.setString(8, "Approved");

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, maxId);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();    
                        return success;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return false;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success; 
    }
    
    public boolean insertTransferTransaction(Client client, int empId, String transferAmount, String toAccount) {
        boolean success = true;
        
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String DOT = df.format(currentDate);
        int maxId = maxId("Transactions", "trans_id");
        
        String query_insert = "INSERT INTO Transactions "
                        + "(emp_id, client_id, transactionType, fromAccount, toAccount, amount, DOT, status )\n"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        String query_check = "SELECT * FROM Transactions WHERE trans_id > ? ;";
        
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_insert);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    ps.setInt(1, empId);
                    ps.setInt(2, client.getId());
                    ps.setString(3, "Transfer");
                    ps.setString(4, client.getAccount().getAccountNumber());
                    ps.setString(5, toAccount);
                    ps.setString(6, transferAmount);
                    ps.setString(7, DOT);
                    ps.setString(8, "Approved");

                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);
                        ps_check.setInt(1, maxId);                        
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {                           
                            con.commit();    
                        return success;
                        }
                        // END : provera da li je red dodate
                        con.rollback();
                        return false;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success; 
    }
    
    public String getFirstandlastName(String username) {
        String fullName = "";
        
        String query = "SELECT firstName, lastName from Employees e \n" +
                            "where e.username = '"+username+"';";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        
                        fullName = rs.getString("firstName") + " "+rs.getString("lastname");
                        return fullName;
                        
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fullName;
    }
    
    public int getOpenedAccounts(int userId) {
        int count;
        Transaction transaction;
        
        String query = "SELECT count(a.emp_id) as count from Accounts a \n" +
                            "where a.emp_id = "+userId+" AND MONTH(a.DOC) = MONTH(CURRENT_DATE());";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        count = rs.getInt("count");
                        
                        return count;
                        
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count = 0;
    }
    
    public int getNumOfTransactions(int userId) {
        int count;
        Transaction transaction;
        
        String query = "SELECT count(t.emp_id) as count from Transactions t \n" +
                            "where t.emp_id = "+userId+" AND MONTH(t.DOT) = MONTH(CURRENT_DATE());";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                Statement statement = con.createStatement();
                ){
                    
                    ResultSet rs = statement.executeQuery(query);
                    
                    if(rs.next()) {
                        count = rs.getInt("count");
                        
                        return count;
                        
                    }
            
        } catch (SQLException ex) { 
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count = 0;
    }
    
    public Transaction updateTransaction(int transId, String status) {
        Transaction transaction = null;
        
        String query_deposit = "UPDATE Transactions t\n" +
                                "SET t.status = '"+status+"' \n"+
                                "WHERE t.trans_id = "+transId;

        String query_check = "SELECT * FROM Transactions WHERE trans_id ="+transId+" AND status = '"+status+"';";
        try(Connection con = DriverManager.getConnection(url, dbUser, pass);
                PreparedStatement ps = con.prepareStatement(query_deposit);
                ) {
                        // BEGIN :  dodavanje novog reda
                    con.setAutoCommit(false);
                    
                    int rezultat = ps.executeUpdate();
                        // END: dodavanje novog reda
                        
                        // BEGIN : provera da li je red dodat
                        PreparedStatement ps_check = con.prepareStatement(query_check);                    
                        ResultSet rs = ps_check.executeQuery();
                        
                        if(rs.next()) {
                        con.commit();
                        transaction = new Transaction (rs.getInt("trans_id"), rs.getDate("DOT"),
                                                      rs.getString("transactionType"),
                                                      rs.getString("fromAccount"), rs.getString("toAccount"),
                                                      rs.getInt("amount"), rs.getString("status"));
                        return transaction;
                    }
                        // END : provera da li je red dodate
                        con.rollback();
                        return transaction;                    
        }   catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transaction;  
    }
}
