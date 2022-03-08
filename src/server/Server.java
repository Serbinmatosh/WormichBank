package server;

import controller.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Client;
import model.Transaction;
import model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author serbinmatosh
 */
public class Server {

    ServerSocket serverSocket;
    HashMap employeeHash = new HashMap();
    HashMap chatHash = new HashMap();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    ServerFrame frame = new ServerFrame();

    public Server() {
        try {
            serverSocket = new ServerSocket(2089);
            new EmployeeAccept().start();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }


    class EmployeeAccept extends Thread {
        
        @Override
        public void run() {
            while (true) {
                try {
                    Socket employeeSocket = serverSocket.accept();
                    DataOutputStream dout = new DataOutputStream(employeeSocket.getOutputStream());
                    DataInputStream din = new DataInputStream(employeeSocket.getInputStream());
                    
                    String username = din.readUTF();
                    
                    
                    if (!username.contains(" ")) {
                        // ovde kontroler treba da pozove i da vidi da li postoji u bazi
                        String password = din.readUTF();
                        User user = Controller.getInstance().login(username, password);
                        if(user != null) {
                            if (employeeHash.containsKey(username)) {
                                dout.writeUTF("You Are Alredy Registered....!!");
                            } else {
                                employeeHash.put(username, employeeSocket);
                                frame.write(username + " is Logged In! " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                                dout.writeUTF("Good");
                                sendUser(user, employeeSocket);
                                new HandleAction(employeeSocket, username).start();
                            }
                        } else {
                            dout.writeUTF("Error!");
                        }
                    } else {
                        if (chatHash.containsKey(username)) {
                            dout.writeUTF("You Are Alredy Registered....!!");
                        } else {
                            chatHash.put(username, employeeSocket);
                            frame.write(username + " is Online! " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                            dout.writeUTF("");
                            //-----------------------------------
                            Set k = chatHash.keySet();
                            Iterator itr = k.iterator();
                            while (itr.hasNext()) {
                                String key = (String) itr.next();
                                
                                    if (!key.equalsIgnoreCase(username)) {
                                        try {
                                            new DataOutputStream(((Socket) chatHash.get(key))
                                                    .getOutputStream()).writeUTF(username + " is Online!");
                                        } catch (Exception e) {
                                            chatHash.remove(key);
                                            frame.write(key + " left the Chat!" + " \n");
                                            new PrepareChatList().start();
                                        }
                                    }
                            }
                            new ChatRead(employeeSocket, username).start();
                            new PrepareChatList().start();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    class ChatRead extends Thread {

        Socket s;
        String fullName;

        ChatRead(Socket s, String fullName) {
            this.s = s;
            this.fullName = fullName;
        }

        @Override
        public void run() {
            try {
                while (!chatHash.isEmpty()) {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (i.equals("mkoihgteazdcvgyhujb096785542AXTY")) {//prepoznavanje za remove
                        chatHash.remove(fullName);
                        String msgForRemove = fullName + ": Left The Chat! \n";
                        frame.write(fullName + " Left The Chat! " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                        new PrepareChatList().start();
                        //kada smo smakli klijenta opet pokrecemo PrepareClientList()
                        //potom svim korisnicima saljemo novu listu klijenata bez uklonjenog korisnika
                        Set k = chatHash.keySet();

                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(fullName)) {
                                try {
                                    new DataOutputStream(((Socket) chatHash.get(key)).getOutputStream()).writeUTF(msgForRemove);
                                } catch (Exception e) {
                                    chatHash.remove(key);
                                    frame.write(key + " Left The Chat!\n");
                                    new PrepareChatList().start();
                                }
                            }
                        }
//                        s.close();
                    } else if (i.contains("#4344554@@@@@67667@@")) {//prepoznavanje za slanje poruke odredjenoj osobi
                        //Ovde pravimo substring koji krece od 20 karaktera da bi preskocili #4344554@@@@@67667@@
                        //Zatim preko StringTokenizera ubacujemo u njega , pa ga odvajemo sa : na dva tokena
                        //prvi token ce sadrzati ID onoga ko salje drugi id sadrzi kome se salje 
                        //i je poruka koja se salje
                        i = i.substring(20);
                        StringTokenizer st = new StringTokenizer(i, ":");
                        // prvi token je username onoga kome se salje
                        String usernamekomeSeSalje = st.nextToken();
                        // drugi je poruka koja se tom usernamu salje
                        i = st.nextToken();
                        try {
                            new DataOutputStream(((Socket) chatHash.get(usernamekomeSeSalje))
                                    .getOutputStream())
                                    .writeUTF(fullName + " to You: " + i);
                        } catch (Exception e) {
                            chatHash.remove(usernamekomeSeSalje);
                            frame.write(usernamekomeSeSalje + " Left The Chat!\n");
                            new PrepareChatList().start();
                        }
                    } else {//ovde je slanje svim korisnika nema potrebe za tekstom prepoznavanja
                        Set k = chatHash.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(fullName)) {
                                try {
                                    new DataOutputStream(((Socket) chatHash.get(key))
                                            .getOutputStream()).writeUTF(fullName + " to ALL: " + i);
                                } catch (Exception e) {
                                    chatHash.remove(key);
                                    frame.write(key + " Left The Chat!\n");
                                    new PrepareChatList().start();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    class PrepareChatList extends Thread {

        @Override
        public void run() {
            try {
                String ids = "";
                Set k = chatHash.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    //ovde pravimo spisak keyeva
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }

                //da bi imao spisak aktivnih korisnika
                //kod ispod sluzi za punjenje UL preko klase  class Read u MyClient i njene metode run()
                itr = k.iterator();

                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) chatHash.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);
                    } catch (Exception e) {
                        chatHash.remove(key);
                        frame.write(key + " Left The Chat!\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class HandleAction extends Thread {

        Socket s;
        String username;

        HandleAction(Socket s, String username) {
            this.s = s;
            this.username = username;
        }

        @Override
        public void run() {
            try {
                while (!employeeHash.isEmpty()) {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    switch (i) {
                        case "mkoihgteazdcvgyhujb096785542skrt": // Exiting Protocol
                            employeeHash.remove(username);
                            frame.write(username + " Logged Out! " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                            break;
                        case "#1111##!?19890031@$@":
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            performAccessClientShort(username, s);
                            break;
                        case "0###11?+1234@#$%9**!": // Create an Account Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            checkExistence(s);
                            String proceeds = new DataInputStream(s.getInputStream()).readUTF();
                            if(proceeds.equals("go")) {
                                performInsertClient(username, s);
                            } else {
                              break;  
                            }    
                        case "NN34jso#$%sjifr!!3zz": // Authorize Employee Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            checkUsername(s);
                            String proceed = new DataInputStream(s.getInputStream()).readUTF();
                            if(proceed.equals("go")) {
                              performInsertEmployee(username, s);
                              break;
                            } else {
                              break;  
                            }   
                        case "Na487&!?M00#$%!!-@#%": // Update Employee Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            checkUsername(s);
                            String proceed2 = new DataInputStream(s.getInputStream()).readUTF();
                            if(proceed2.equals("go")) {
                              performUpdateEmployee(username, s);
                              break;
                            } else {
                              break;  
                            }
                        case "04%23!@4hnx??#$%&ss*": // Monetary Transaction Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            performTransaction(username, s);
                            break;
                        case "hfk2@$%%@?!?!?#$%ss3": // Update Client Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            performUpdateClient(username, s);
                            break;
                        case "#%$oal!04m2js$%&??sn": // Load Transactions Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            getTransactions(username, s);
                            break;
                        case "2gs%%f98!@!@$!@fhuhi": // user Performance Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            getPerformance(username, s);
                            break;
                        case "&&5vh34m*&%?nd2!!??x": // Request Handling Protocol
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            handleRequests(username, s);
                            break;
                        case "394ns#$%^&&&!?shu3x?": // Retrive Updated User
                            new DataOutputStream(s.getOutputStream()).writeUTF(i);
                            retrieveUser(username, s);
                            break;
                    }
                }
            } catch (Exception e) {
                employeeHash.remove(username);
                frame.write(username + " Logged Out! " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                frame.write("User Disconnected\n");
            }
        } 
    }
    
    public void retrieveUser(String username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            int extra = din.readInt(); // ?? where is this coming from
            int id = din.readInt();
            User user = Controller.getInstance().accessUser(id);
            sendUser(user, s);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleRequests(String username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String scopeOfAction = din.readUTF();
            int transId = din.readInt();
            int userId = din.readInt();
            if(scopeOfAction.equalsIgnoreCase("Deny")) {
                Transaction t = Controller.getInstance().updateTransaction(transId, "Denied");
                    if(t != null) {
                        dout2.writeUTF("Updated");
                        frame.write(username +" Denied Transaction with ID: "+transId+" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                        dout2.writeUTF("UpdateTransactions");
                    } else {
                        dout2.writeUTF("NotUpdated");
                    }
            } else if(scopeOfAction.equalsIgnoreCase("Approve")) {
                    Transaction t = Controller.getInstance().updateTransaction(transId, "Approved");
                        if(t != null) {
                            Client client = Controller.getInstance().transferMoney(t.getFromAccount(), t.getToAccount(), Integer.toString(t.getAmount()), userId);
                            if (client != null) {
                                dout2.writeUTF("Updated");                                
                                frame.write(username +" Approved Transaction with ID: "+transId+" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                                Set k = employeeHash.keySet();
                                Iterator itr = k.iterator();
                                while (itr.hasNext()) {
                                            String key = (String) itr.next();

                                                if (!key.equalsIgnoreCase(username)) {
                                                    try {
                                                        new DataOutputStream(((Socket) employeeHash.get(key))
                                                                .getOutputStream()).writeUTF("UpdateTransactions");
                                                    } catch (Exception e) {
                                                        e.getMessage();
                                                    }
                                                }
                                        }
                                dout2.writeUTF("UpdateTransactions");
                            } else {
                                frame.write("Something Went Terribly Wrong\n");
                            }
                        } else {
                            dout2.writeUTF("NotUpdated");
                        }
                }
            }catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } 

    }
    
    public void getPerformance(String Username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            int userId = din.readInt();
            int numOfAcctOpen = Controller.getInstance().getOpenedAccounts(userId);
            int numOfTransactions = Controller.getInstance().getNumOfTransactions(userId);
            dout2.writeInt(numOfAcctOpen);
            dout2.writeInt(numOfTransactions);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void getTransactions(String username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String accountNumber = din.readUTF();
            ArrayList<Transaction> allTransactions = Controller.getInstance().allTransactions(accountNumber);
            int length = allTransactions.size();
            dout2.writeInt(length);
            sendArray(allTransactions,s);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendArray(ArrayList<Transaction> trans,  Socket s) {
        for (int i = 0; i < trans.size(); i++) {
            sendTransaction(trans.get(i),s,trans.size());
        }
    }

    public void sendTransaction(Transaction t,  Socket s, int length) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
                dout2.writeInt(t.getEmpId());
                dout2.writeInt(t.getClientId());
                dout2.writeUTF(t.getTransType());
                dout2.writeUTF(t.getFromAccount());
                dout2.writeUTF(t.getToAccount());
                dout2.writeInt(t.getAmount());
                dout2.writeUTF(t.getDOT());
                dout2.writeUTF(t.getStatus());
                dout2.writeInt(t.getId());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkExistence(Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String accountNumber = din.readUTF();
            String scopeOfAction = din.readUTF();
            boolean exists = Controller.getInstance().checkForAccount(accountNumber);
            if(exists) {
                dout2.writeUTF("exists");
            } else if (!exists && (scopeOfAction.equals("TransferCheck") || scopeOfAction.equals("RequestCheck"))) {
                dout2.writeUTF("noAccount");
            }else if(!exists && scopeOfAction.equals("ForReal")) {
                dout2.writeUTF("continue");
            }  else if(!exists && scopeOfAction.equals("JustCheck")) {
                dout2.writeUTF("nonexistent");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkUsername(Socket s) {
            try {
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
                String username = din.readUTF();
                String scopeOfAction = din.readUTF();
                boolean exists = Controller.getInstance().checkUsername(username);
                if(exists) {
                    dout2.writeUTF("exists");
                } else if(!exists && scopeOfAction.equals("ForReal")) {
                    dout2.writeUTF("continue");
                } else if(!exists && scopeOfAction.equals("JustCheck")) {
                    dout2.writeUTF("nonexistent");
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void performTransaction(String username, Socket s) {     
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            String scopeOfAction = din.readUTF();
            switch (scopeOfAction) {
            case "Deposit":
                Deposit(username, din, s);
                break;
            case "Withdrawal":
                Withdrawal(username, din, s);
                break;
            case "Transfer":
                Transfer(username, din, s);
                break;
            case "TransferCheck":
                checkExistence(s);
                break;
            case "RequestCheck":
                checkExistence(s);
                break;
            case "Request":
                Request(username, din, s);
                break;
        }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Deposit(String username, DataInputStream din, Socket s) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String accountNumber = din.readUTF();
            String depositAmount = din.readUTF();
            int empId = din.readInt();
            Client client = Controller.getInstance().depositMoney(accountNumber, depositAmount, empId);
            sendAccount(client, s);
            sendClient(client, s);
            frame.write(username +" Deposited $"+depositAmount+" Into Account Number: "+accountNumber +" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            dout2.writeUTF("UpdateTransactions");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Withdrawal(String username, DataInputStream din, Socket s) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String accountNumber = din.readUTF();
            String withdrawalAmount = din.readUTF();
            int empId = din.readInt();
            Client client = Controller.getInstance().withdrawMoney(accountNumber, withdrawalAmount, empId);
            sendAccount(client, s);
            sendClient(client, s);
            frame.write(username +" Withdrew $"+withdrawalAmount+" From Account Number: "+accountNumber +" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            dout2.writeUTF("UpdateTransactions");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Transfer(String username, DataInputStream din, Socket s) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            checkExistence(s);
            String proceed = din.readUTF();
            if(proceed.equals("go")) {
                String toAccount = din.readUTF();
                String fromAccount = din.readUTF();
                String transferAmount = din.readUTF();
                int empId = din.readInt();
                Client client = Controller.getInstance().transferMoney(toAccount, fromAccount, transferAmount, empId);
                sendAccount(client, s);
                sendClient(client, s);
                frame.write(username +" Transfered $"+transferAmount+" From Account Number: "+fromAccount +" To Account Number: "+toAccount+" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                dout2.writeUTF("UpdateTransactions");
            } else if(proceed.equals("stop")) {
                dout2.writeUTF("stopped");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Request(String username, DataInputStream din, Socket s) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            checkExistence(s);
            String proceed = din.readUTF();
            if(proceed.equals("go")) {
                String toAccount = din.readUTF();
                String fromAccount = din.readUTF();
                String requestAmount = din.readUTF();
                int empId = din.readInt();
                int clientId = din.readInt();
                boolean requested = Controller.getInstance().requestMoney(empId, clientId, fromAccount, toAccount, requestAmount);
                if(requested) {
                    dout2.writeUTF("requested");
                    frame.write(username +" Sent a Request for $"+requestAmount+" From Account Number: "+fromAccount +" To Account Number: "+toAccount+" - "+ dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                    Set k = employeeHash.keySet();
                    Iterator itr = k.iterator();
                    while (itr.hasNext()) {
                                String key = (String) itr.next();
                                
                                    if (!key.equalsIgnoreCase(username)) {
                                        try {
                                            new DataOutputStream(((Socket) employeeHash.get(key))
                                                    .getOutputStream()).writeUTF("UpdateTransactions");
                                        } catch (Exception e) {
                                            frame.write("exception opa\n");
                                        }
                                    }
                            }
                    dout2.writeUTF("UpdateTransactions");
                } else {
                    dout2.writeUTF("notRequested");
                }
                
            } else if(proceed.equals("stop")) {
                dout2.writeUTF("stopped");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void performInsertClient(String username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            Client clientReceived = receiveClient(s);
            Client client = Controller.getInstance().addClient(clientReceived);
            if(client!=null) {
                dout2.writeUTF("Client-Added");
                frame.write(username + " has created "+ client.getFirstName()+" "
                    +client.getLastName()+"'s account - " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            } else {
                dout2.writeUTF("Failed-To-Add-Client");
                frame.write(username + " failed to create an account " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private void performUpdateClient(String username, Socket s) {
        try {
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
                Client clientReceived = receiveClient(s);
                Client client = Controller.getInstance().updateClient(clientReceived);
                if(client!=null) {
                    dout2.writeUTF("Client-Updated");
                    frame.write(username + " has updated "+ client.getFirstName()+" "
                        +client.getLastName()+"'s Information - " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                } else {
                    dout2.writeUTF("Failed-To-Update-Client");
                    frame.write(username + " failed to update account information " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }
    
    public Client receiveClient(Socket s){ 
        Client client = null;
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            int id = din.readInt();
            String firstName = din.readUTF();
            String lastName = din.readUTF();
            String email = din.readUTF();
            String dob = din.readUTF();
            Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            String street = din.readUTF();
            String city = din.readUTF();
            String zip = din.readUTF();
            String sex = din.readUTF();
            String accountNumber = din.readUTF();
            String pin = din.readUTF();
            int empId = din.readInt();
            long balance = din.readLong();
            Account account = new Account(id, accountNumber, pin, empId, balance, DOB);
            client = new Client(id, firstName, lastName, email, DOB, street, city, zip, sex, null, account);
            return client;           
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }
    
    private void performInsertEmployee(String username, Socket s) {
            try {
                DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
                User user = readUser(s);
                User userAdd = Controller.getInstance().addEmployee(user);
                if(userAdd!=null) {
                    dout2.writeUTF("Success");
                    frame.write(username + " has Authorized "+ user.getFirstName()+" "
                        +user.getLastName()+"'s Employee Account - " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                } else {
                    dout2.writeUTF("Fail");
                    frame.write(username + " failed to Authorize an Employee " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void performUpdateEmployee(String username, Socket s) {
        try {
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            User user = readUser(s);
            User userAdd = Controller.getInstance().updateEmployee(user);
            if(userAdd!=null) {
                dout2.writeUTF("Success");
                frame.write(username + " Updated Their Info - " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            } else {
                dout2.writeUTF("Fail");
                frame.write(username + " Failed to Update Their Info " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void performAccessClientShort(String username, Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(s.getOutputStream());
            String accountNumber = din.readUTF();
            String pin = din.readUTF();
            String scope = din.readUTF();
            Client client = Controller.getInstance().accessClient(accountNumber, pin);
            if(client != null) {
                dout2.writeUTF("success");
                sendAccount(client,s);
                sendClient(client,s);
                if(scope.equals("AccessAll")) {
                    frame.write(username + " has accessed "+ client.getFirstName()+" "
                            +client.getLastName()+"'s account - " + dateFormat.format(new Timestamp(System.currentTimeMillis())) + " \n");
                } else if(scope.equals("AccessUpdate")) {
                    
                }
            } else {
                dout2.writeUTF("fail");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User readUser(Socket s) {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            int id = din.readInt();
            String username1 = din.readUTF();
            String password = din.readUTF();
            String firstName = din.readUTF();
            String lastName = din.readUTF();
            String email = din.readUTF();
            String dob = din.readUTF();
            Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            String sex = din.readUTF();
            String authorization = din.readUTF();
            int length = din.readInt();
            if(length>0) {
                byte[] photo = new byte[length];
                din.readFully(photo, 0, photo.length);
                return new User(id, username1, password, firstName, lastName, email, DOB, sex, authorization, photo);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    private void sendUser(User user, Socket socket) throws IOException {
        DataOutputStream dout2 = new DataOutputStream(socket.getOutputStream());
        dout2.writeInt(user.getId());
        dout2.writeUTF(user.getUsername());
        dout2.writeUTF(user.getPassword());
        dout2.writeUTF(user.getFirstName());
        dout2.writeUTF(user.getLastName());
        dout2.writeUTF(user.getEmail());
        dout2.writeUTF(user.getDob());
        dout2.writeUTF(user.getSex());
        dout2.writeUTF(user.getAuthorization());
        if(user.getPhoto() == null) {
            dout2.writeInt(0);
            dout2.writeUTF("");
        } else {
            dout2.writeInt(user.getPhoto().length);
            dout2.write(user.getPhoto());
        }
    }
    
    private void sendAccount(Client client, Socket socket) throws IOException {
        DataOutputStream dout2 = new DataOutputStream(socket.getOutputStream());
        dout2.writeInt(client.getAccount().getClient_id());
        dout2.writeUTF(client.getAccount().getAccountNumber());
        dout2.writeUTF(client.getAccount().getPin());
        dout2.writeInt(client.getAccount().getEmpId());
        dout2.writeLong(client.getAccount().getBalance());
        dout2.writeUTF(client.getAccount().getDOC());

    }
    
    private void sendClient(Client client, Socket socket) throws IOException {
        DataOutputStream dout2 = new DataOutputStream(socket.getOutputStream());
        dout2.writeInt(client.getId());
        dout2.writeUTF(client.getFirstName());
        dout2.writeUTF(client.getLastName());
        dout2.writeUTF(client.getEmail());
        dout2.writeUTF(client.getDob());
        dout2.writeUTF(client.getStreet());
        dout2.writeUTF(client.getCity());
        dout2.writeUTF(client.getZip());
        dout2.writeUTF(client.getSex());
        if(client.getClientPhoto() == null) {
            dout2.writeInt(0);
            dout2.writeUTF("");
        } else {
            dout2.writeInt(client.getClientPhoto().length);
            dout2.write(client.getClientPhoto());
        }
    }
       
    

}
