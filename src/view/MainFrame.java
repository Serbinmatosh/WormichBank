package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Account;
import model.Client;
import model.Transaction;
import model.User;

public class MainFrame extends javax.swing.JFrame {

    User user;
    String fullName;
    Socket socket;
    Socket socketMain;
    Client client;
    Timer timer = new Timer();
    Calendar calendar;
    SimpleDateFormat timeFormat;
    String time;
    SimpleDateFormat dateFormat;
    String date;
    SimpleDateFormat dayFormat;
    String day;
    String scopeOfAction = "";
    boolean available;
    String username;
    String clientIDFromList = "";
    ArrayList<Transaction> transactions;

    DataInputStream dinChat;
    DataOutputStream doutChat;
    DataInputStream dinMain;
    DataOutputStream doutMain;
    DefaultListModel dlm;
    byte [] photo = null;

    public MainFrame(User user, Socket socketMain) throws IOException {
        this.user = user;
        this.socketMain = socketMain;

        this.doutMain = new DataOutputStream(socketMain.getOutputStream());
        this.dinMain = new DataInputStream(socketMain.getInputStream());

        try {

            initComponents();
            setVisible(true);
            setLocationRelativeTo(null);
            lblCover.setVisible(false);
            lblCover1.setVisible(false);
            window(pnlHome);
            username = user.getUsername();
            fullName = user.getFirstName() + " " + user.getLastName();
            photo = user.getPhoto();
            setProfile(user);
            getUserStats();

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    UIManager.put("ScrollBar.width", 10);
                    scrollerSet();
                    timer.schedule(new Alert(), 0, 200);
                    new Clock().start();
                    new HandleActions().start();
                }
            });

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    class Clock extends Thread {

        @Override
        public void run() {
            while (true) {
                timeFormat = new SimpleDateFormat("HH:mm:ss");
                time = timeFormat.format(Calendar.getInstance().getTime());
                lblTime.setText(time);
                dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                date = dateFormat.format(Calendar.getInstance().getTime());
                lblDate.setText(date);
                dayFormat = new SimpleDateFormat("EEEEE");
                day = dayFormat.format(Calendar.getInstance().getTime());
                lblDay.setText(day);
            }
        }
    }

    class Alert extends TimerTask {

        @Override
        public void run() {
        if(client != null && transactions != null) {
            getRequestsToTransactions();
        }
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlNavigation = new javax.swing.JPanel();
        lblCover = new javax.swing.JLabel();
        lblCover1 = new javax.swing.JLabel();
        pnlNavHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlNavAccounts = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlNavBanking = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlNavAuthorize = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlNavSettings = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pnlTurnOff = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnlUserProfile = new javax.swing.JPanel();
        lblUserImage = new javax.swing.JLabel();
        lblUserIconFNskrt = new javax.swing.JLabel();
        pnlNavChat = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblClientId = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlHome = new javax.swing.JPanel();
        lblHomeWelcome = new javax.swing.JLabel();
        lblNumAcctOpen = new javax.swing.JLabel();
        lblNumTransPerf = new javax.swing.JLabel();
        lblRewardLevel = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        pnlAcct = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtZip = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtStreet = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtPIN = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        txtAcctSearch = new javax.swing.JTextField();
        btnCreateAcct = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        cbSex = new javax.swing.JComboBox<>();
        lblBtnCAMessage = new javax.swing.JLabel();
        lblbackaccount = new javax.swing.JLabel();
        pnlBankingPIN = new javax.swing.JPanel();
        txtEnterPIN = new javax.swing.JTextField();
        txtEnterAcctNum = new javax.swing.JTextField();
        btnResetPIN = new javax.swing.JButton();
        btnConfirmPIN = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        pnlBanking = new javax.swing.JPanel();
        pnlClientNav = new javax.swing.JPanel();
        pnlNavAlerts = new javax.swing.JPanel();
        lblAlertNumber = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        pnlClientProfile = new javax.swing.JPanel();
        lblFemaleClient = new javax.swing.JLabel();
        lblMaleClient = new javax.swing.JLabel();
        lblClientName = new javax.swing.JLabel();
        lblAccountBalance = new javax.swing.JLabel();
        lblCurrentBalance = new javax.swing.JLabel();
        lblAcctNumTxt = new javax.swing.JLabel();
        lblClientAcctNum = new javax.swing.JLabel();
        pnlNavServices = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pnlNavHistory = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pnlCliNavSettings = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlClientWindow = new javax.swing.JPanel();
        pnlServices = new javax.swing.JPanel();
        txtDeposit = new javax.swing.JTextField();
        txtWithdraw = new javax.swing.JTextField();
        txtTransfer = new javax.swing.JTextField();
        txtRequest = new javax.swing.JTextField();
        txtTransferAccount = new javax.swing.JTextField();
        txtRequestAccount = new javax.swing.JTextField();
        btnResetDeposit = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        btnResetWithdraw = new javax.swing.JButton();
        btnWithdraw = new javax.swing.JButton();
        btnTransfer = new javax.swing.JButton();
        btnResetTransfer = new javax.swing.JButton();
        btnRequest = new javax.swing.JButton();
        btnResetRequest = new javax.swing.JButton();
        btnCheckReqAcct = new javax.swing.JButton();
        btnCheckTransferAcct = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        pnlHistory = new javax.swing.JPanel();
        lblTblAllTransactions = new javax.swing.JLabel();
        lblTblDeposits = new javax.swing.JLabel();
        lblTblWithdrawals = new javax.swing.JLabel();
        lblTblTransfers = new javax.swing.JLabel();
        scrAllTrans = new javax.swing.JScrollPane();
        tblAll = new javax.swing.JTable();
        lblBig = new javax.swing.JLabel();
        scrDep = new javax.swing.JScrollPane();
        tblDep = new javax.swing.JTable();
        scrWith = new javax.swing.JScrollPane();
        tblWith = new javax.swing.JTable();
        scrTran = new javax.swing.JScrollPane();
        tblTran = new javax.swing.JTable();
        pnlClientSettings = new javax.swing.JPanel();
        btnResetCS = new javax.swing.JButton();
        btnUpdateClient = new javax.swing.JButton();
        txtEmail1 = new javax.swing.JTextField();
        txtPostalCode1 = new javax.swing.JTextField();
        txtFirstName1 = new javax.swing.JTextField();
        txtLastName1 = new javax.swing.JTextField();
        txtDOB1 = new javax.swing.JTextField();
        txtStreet1 = new javax.swing.JTextField();
        txtCity1 = new javax.swing.JTextField();
        cbSex1 = new javax.swing.JComboBox<>();
        txtPIN2 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        pnlAlerts = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblReq = new javax.swing.JTable();
        txtTransId = new javax.swing.JTextField();
        btnApproveRequest = new javax.swing.JButton();
        btnRejectRequest = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pnlAuthorize = new javax.swing.JPanel();
        btnResetEmployee = new javax.swing.JButton();
        btnAuthorize = new javax.swing.JButton();
        txtAuthFN = new javax.swing.JTextField();
        txtAuthLN = new javax.swing.JTextField();
        txtAuthEmail = new javax.swing.JTextField();
        txtAuthDOB = new javax.swing.JTextField();
        txtAuthUsername = new javax.swing.JTextField();
        txtAuthPassword = new javax.swing.JTextField();
        cbSexUserAuth = new javax.swing.JComboBox<>();
        cbAuthorizationSet = new javax.swing.JComboBox<>();
        btnCheckUsername1 = new javax.swing.JButton();
        btnAuthImage = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblAuthImage = new javax.swing.JLabel();
        pnlSettings = new javax.swing.JPanel();
        btnCheckUsername = new javax.swing.JButton();
        btnResetSettings = new javax.swing.JButton();
        btnSaveSettings = new javax.swing.JButton();
        txtUserFirstName = new javax.swing.JTextField();
        txtUserLastName = new javax.swing.JTextField();
        txtUserEmail = new javax.swing.JTextField();
        txtUserDOB = new javax.swing.JTextField();
        txtUserUsername = new javax.swing.JTextField();
        txtUserPassword = new javax.swing.JTextField();
        cbUserSex = new javax.swing.JComboBox<>();
        lblUpdateImage = new javax.swing.JLabel();
        btnUpdateImage = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pnlChat = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMsgArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtUserSendMsg = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        OnlineUsersList = new javax.swing.JList<>();
        btnSendMessage = new javax.swing.JButton();
        btnGoOnline = new javax.swing.JButton();
        btnGoOffline = new javax.swing.JButton();
        btnSelectAll = new javax.swing.JButton();
        lblSuccess = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1000, 770));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 770));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlNavigation.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavigation.setBounds(new java.awt.Rectangle(0, 0, 180, 750));
        pnlNavigation.setMaximumSize(new java.awt.Dimension(180, 750));
        pnlNavigation.setMinimumSize(new java.awt.Dimension(180, 750));
        pnlNavigation.setPreferredSize(new java.awt.Dimension(180, 750));
        pnlNavigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCover.setBackground(new java.awt.Color(0, 0, 0));
        lblCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/emptyCover.png"))); // NOI18N
        lblCover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCoverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCoverMouseEntered(evt);
            }
        });
        pnlNavigation.add(lblCover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 180, 520));

        lblCover1.setBackground(new java.awt.Color(0, 0, 0));
        lblCover1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/emptyCover.png"))); // NOI18N
        lblCover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCover1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCover1MouseEntered(evt);
            }
        });
        pnlNavigation.add(lblCover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 180, 380));

        pnlNavHome.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavHomeMouseExited(evt);
            }
        });
        pnlNavHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(240, 240, 240));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/home.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        pnlNavHome.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel2.setBackground(new java.awt.Color(51, 102, 255));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        pnlNavHome.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel3.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Home");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        pnlNavHome.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 180, 50));

        pnlNavAccounts.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavAccountsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavAccountsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavAccountsMouseExited(evt);
            }
        });
        pnlNavAccounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(240, 240, 240));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/client.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        pnlNavAccounts.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel5.setBackground(new java.awt.Color(51, 102, 255));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        pnlNavAccounts.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel6.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Accounts");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        pnlNavAccounts.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 180, 50));

        pnlNavBanking.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavBanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavBankingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavBankingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavBankingMouseExited(evt);
            }
        });
        pnlNavBanking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(240, 240, 240));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/banking.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        pnlNavBanking.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel8.setBackground(new java.awt.Color(51, 102, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        pnlNavBanking.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel9.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Banking");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        pnlNavBanking.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavBanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 180, 50));

        pnlNavAuthorize.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavAuthorize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavAuthorizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavAuthorizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavAuthorizeMouseExited(evt);
            }
        });
        pnlNavAuthorize.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(240, 240, 240));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/key.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        pnlNavAuthorize.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel11.setBackground(new java.awt.Color(51, 102, 255));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        pnlNavAuthorize.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel12.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Authorize");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        pnlNavAuthorize.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavAuthorize, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 180, 50));

        pnlNavSettings.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavSettingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavSettingsMouseExited(evt);
            }
        });
        pnlNavSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(240, 240, 240));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/settings.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        pnlNavSettings.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel17.setBackground(new java.awt.Color(51, 102, 255));
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        pnlNavSettings.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel18.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Settings");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        pnlNavSettings.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 180, 50));

        pnlTurnOff.setBackground(new java.awt.Color(42, 39, 41));
        pnlTurnOff.setMaximumSize(new java.awt.Dimension(180, 50));
        pnlTurnOff.setMinimumSize(new java.awt.Dimension(180, 50));
        pnlTurnOff.setPreferredSize(new java.awt.Dimension(180, 50));
        pnlTurnOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTurnOffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlTurnOffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlTurnOffMouseExited(evt);
            }
        });
        pnlTurnOff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(240, 240, 240));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/closing.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel19MouseExited(evt);
            }
        });
        pnlTurnOff.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 30, 30));

        pnlNavigation.add(pnlTurnOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 180, 50));

        pnlUserProfile.setBackground(new java.awt.Color(42, 39, 41));
        pnlUserProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUserImage.setBackground(new java.awt.Color(42, 39, 41));
        pnlUserProfile.add(lblUserImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 140, 140));

        lblUserIconFNskrt.setBackground(new java.awt.Color(255, 255, 255));
        lblUserIconFNskrt.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblUserIconFNskrt.setForeground(new java.awt.Color(204, 204, 204));
        lblUserIconFNskrt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlUserProfile.add(lblUserIconFNskrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 180, -1));

        pnlNavigation.add(pnlUserProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 210));

        pnlNavChat.setBackground(new java.awt.Color(42, 39, 41));
        pnlNavChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavChatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavChatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlNavChatMouseExited(evt);
            }
        });
        pnlNavChat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setBackground(new java.awt.Color(240, 240, 240));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/chat2.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel28MouseExited(evt);
            }
        });
        pnlNavChat.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 30, 30));

        jLabel31.setBackground(new java.awt.Color(51, 102, 255));
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel31MouseExited(evt);
            }
        });
        pnlNavChat.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jLabel32.setFont(new java.awt.Font("Noto Sans Kannada", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 204, 204));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Chat");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel32MouseExited(evt);
            }
        });
        pnlNavChat.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 17, -1, -1));

        pnlNavigation.add(pnlNavChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 180, 50));

        lblClientId.setForeground(new java.awt.Color(42, 39, 41));
        pnlNavigation.add(lblClientId, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 90, 30));

        jPanel1.add(pnlNavigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 750));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlMain.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlMain.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlMain.setSize(new java.awt.Dimension(820, 750));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHome.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlHome.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlHome.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHomeWelcome.setFont(new java.awt.Font("Roboto", 0, 50)); // NOI18N
        lblHomeWelcome.setForeground(new java.awt.Color(42, 39, 41));
        pnlHome.add(lblHomeWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 80));

        lblNumAcctOpen.setFont(new java.awt.Font("Roboto", 0, 90)); // NOI18N
        lblNumAcctOpen.setForeground(new java.awt.Color(255, 204, 204));
        lblNumAcctOpen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlHome.add(lblNumAcctOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 180, 80));

        lblNumTransPerf.setFont(new java.awt.Font("Roboto", 0, 90)); // NOI18N
        lblNumTransPerf.setForeground(new java.awt.Color(255, 204, 204));
        lblNumTransPerf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlHome.add(lblNumTransPerf, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 180, 80));

        lblRewardLevel.setFont(new java.awt.Font("Roboto", 0, 110)); // NOI18N
        lblRewardLevel.setForeground(new java.awt.Color(255, 204, 204));
        lblRewardLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlHome.add(lblRewardLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 600, 110));

        lblTime.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("22:01:22");
        pnlHome.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 200, 80));

        lblDate.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblDate.setForeground(new java.awt.Color(42, 39, 41));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlHome.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 250, 50));

        lblDay.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblDay.setForeground(new java.awt.Color(42, 39, 41));
        lblDay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlHome.add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 250, 30));

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/Dashboard.png"))); // NOI18N
        pnlHome.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlMain.add(pnlHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlAcct.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlAcct.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlAcct.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlAcct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setToolTipText("");
        txtEmail.setBorder(null);
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtEmail.setMaximumSize(new java.awt.Dimension(64, 22));
        pnlAcct.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 216, 295, 33));

        txtZip.setBackground(new java.awt.Color(255, 255, 255));
        txtZip.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        txtZip.setForeground(new java.awt.Color(0, 0, 0));
        txtZip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtZip.setBorder(null);
        txtZip.setMaximumSize(new java.awt.Dimension(115, 30));
        txtZip.setMinimumSize(new java.awt.Dimension(115, 30));
        txtZip.setPreferredSize(new java.awt.Dimension(115, 30));
        txtZip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtZipKeyTyped(evt);
            }
        });
        pnlAcct.add(txtZip, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 426, 148, 33));

        txtFirstName.setBackground(new java.awt.Color(255, 255, 255));
        txtFirstName.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtFirstName.setForeground(new java.awt.Color(0, 0, 0));
        txtFirstName.setToolTipText("");
        txtFirstName.setBorder(null);
        txtFirstName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFirstName.setMaximumSize(new java.awt.Dimension(64, 22));
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyTyped(evt);
            }
        });
        pnlAcct.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 111, 295, 33));

        txtLastName.setBackground(new java.awt.Color(255, 255, 255));
        txtLastName.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtLastName.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName.setToolTipText("");
        txtLastName.setBorder(null);
        txtLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLastName.setMaximumSize(new java.awt.Dimension(64, 22));
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastNameKeyTyped(evt);
            }
        });
        pnlAcct.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 161, 295, 33));

        txtStreet.setBackground(new java.awt.Color(255, 255, 255));
        txtStreet.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtStreet.setForeground(new java.awt.Color(0, 0, 0));
        txtStreet.setBorder(null);
        txtStreet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStreetKeyTyped(evt);
            }
        });
        pnlAcct.add(txtStreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 321, 295, 33));

        txtCity.setBackground(new java.awt.Color(255, 255, 255));
        txtCity.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtCity.setForeground(new java.awt.Color(0, 0, 0));
        txtCity.setBorder(null);
        txtCity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCityKeyTyped(evt);
            }
        });
        pnlAcct.add(txtCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 371, 295, 33));

        txtPIN.setBackground(new java.awt.Color(255, 255, 255));
        txtPIN.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        txtPIN.setForeground(new java.awt.Color(0, 0, 0));
        txtPIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPIN.setBorder(null);
        txtPIN.setMaximumSize(new java.awt.Dimension(115, 30));
        txtPIN.setMinimumSize(new java.awt.Dimension(115, 30));
        txtPIN.setPreferredSize(new java.awt.Dimension(115, 30));
        txtPIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPINKeyTyped(evt);
            }
        });
        pnlAcct.add(txtPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 532, 148, 33));

        txtDOB.setBackground(new java.awt.Color(255, 255, 255));
        txtDOB.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtDOB.setForeground(new java.awt.Color(0, 0, 0));
        txtDOB.setBorder(null);
        txtDOB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDOBKeyTyped(evt);
            }
        });
        pnlAcct.add(txtDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 266, 295, 33));

        txtAcctSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtAcctSearch.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtAcctSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtAcctSearch.setBorder(null);
        txtAcctSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAcctSearchActionPerformed(evt);
            }
        });
        txtAcctSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAcctSearchKeyTyped(evt);
            }
        });
        pnlAcct.add(txtAcctSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 591, 295, 33));

        btnCreateAcct.setBackground(new java.awt.Color(42, 39, 41));
        btnCreateAcct.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnCreateAcct.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateAcct.setText("Create Account");
        btnCreateAcct.setBorderPainted(false);
        btnCreateAcct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAcctActionPerformed(evt);
            }
        });
        pnlAcct.add(btnCreateAcct, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 690, 160, 37));

        btnReset.setBackground(new java.awt.Color(42, 39, 41));
        btnReset.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.setBorderPainted(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlAcct.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 690, 120, 37));

        btnSearch.setBackground(new java.awt.Color(42, 39, 41));
        btnSearch.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setBorderPainted(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlAcct.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 589, 120, 37));

        cbSex.setBackground(new java.awt.Color(42, 39, 41));
        cbSex.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbSex.setForeground(new java.awt.Color(255, 255, 255));
        cbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "M", "F" }));
        cbSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexActionPerformed(evt);
            }
        });
        pnlAcct.add(cbSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 479, 70, 30));

        lblBtnCAMessage.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblBtnCAMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlAcct.add(lblBtnCAMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 820, 27));

        lblbackaccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/createAccount.png"))); // NOI18N
        lblbackaccount.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblbackaccountMouseMoved(evt);
            }
        });
        pnlAcct.add(lblbackaccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlMain.add(pnlAcct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlBankingPIN.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlBankingPIN.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlBankingPINMouseMoved(evt);
            }
        });
        pnlBankingPIN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEnterPIN.setBackground(new java.awt.Color(255, 255, 255));
        txtEnterPIN.setFont(new java.awt.Font("Roboto", 0, 70)); // NOI18N
        txtEnterPIN.setForeground(new java.awt.Color(0, 0, 0));
        txtEnterPIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEnterPIN.setBorder(null);
        txtEnterPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnterPINActionPerformed(evt);
            }
        });
        txtEnterPIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnterPINKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEnterPINKeyTyped(evt);
            }
        });
        pnlBankingPIN.add(txtEnterPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 347, 188, 66));

        txtEnterAcctNum.setBackground(new java.awt.Color(255, 255, 255));
        txtEnterAcctNum.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        txtEnterAcctNum.setForeground(new java.awt.Color(0, 0, 0));
        txtEnterAcctNum.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEnterAcctNum.setBorder(null);
        txtEnterAcctNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnterAcctNumActionPerformed(evt);
            }
        });
        txtEnterAcctNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEnterAcctNumKeyTyped(evt);
            }
        });
        pnlBankingPIN.add(txtEnterAcctNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 253, 347, 64));

        btnResetPIN.setBackground(new java.awt.Color(42, 39, 41));
        btnResetPIN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnResetPIN.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPIN.setText("Reset");
        btnResetPIN.setBorderPainted(false);
        btnResetPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPINActionPerformed(evt);
            }
        });
        pnlBankingPIN.add(btnResetPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 120, 37));

        btnConfirmPIN.setBackground(new java.awt.Color(42, 39, 41));
        btnConfirmPIN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnConfirmPIN.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmPIN.setText("Confirm");
        btnConfirmPIN.setBorderPainted(false);
        btnConfirmPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmPINActionPerformed(evt);
            }
        });
        pnlBankingPIN.add(btnConfirmPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 120, 37));

        jLabel46.setFont(new java.awt.Font("Roboto", 0, 50)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBankingPIN.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 820, 70));

        jLabel47.setFont(new java.awt.Font("Roboto", 0, 30)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBankingPIN.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 820, 30));

        jLabel50.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(200, 11, 11));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBankingPIN.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 820, 50));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/PIN Banking.png"))); // NOI18N
        jLabel33.setToolTipText("");
        jLabel33.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel33MouseMoved(evt);
            }
        });
        pnlBankingPIN.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlMain.add(pnlBankingPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlBanking.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlBanking.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlBanking.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlBanking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlClientNav.setForeground(new java.awt.Color(0, 0, 0));
        pnlClientNav.setMaximumSize(new java.awt.Dimension(180, 750));
        pnlClientNav.setMinimumSize(new java.awt.Dimension(180, 750));
        pnlClientNav.setOpaque(false);
        pnlClientNav.setPreferredSize(new java.awt.Dimension(180, 750));
        pnlClientNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlNavAlerts.setMaximumSize(new java.awt.Dimension(180, 40));
        pnlNavAlerts.setMinimumSize(new java.awt.Dimension(180, 40));
        pnlNavAlerts.setOpaque(false);
        pnlNavAlerts.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlNavAlerts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavAlertsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlNavAlertsMouseEntered(evt);
            }
        });
        pnlNavAlerts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAlertNumber.setFont(new java.awt.Font("Noto Sans Kannada", 1, 12)); // NOI18N
        lblAlertNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblAlertNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlertNumber.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblAlertNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlertNumberMouseClicked(evt);
            }
        });
        pnlNavAlerts.add(lblAlertNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 5, 21, 20));

        jLabel38.setBackground(new java.awt.Color(242, 60, 60));
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        pnlNavAlerts.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        jLabel39.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(84, 84, 84));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Alerts");
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        pnlNavAlerts.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2, 140, 36));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/bell2.png"))); // NOI18N
        jLabel51.setText("jLabel51");
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });
        pnlNavAlerts.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 40));

        pnlClientNav.add(pnlNavAlerts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 180, 40));

        pnlClientProfile.setMaximumSize(new java.awt.Dimension(180, 250));
        pnlClientProfile.setMinimumSize(new java.awt.Dimension(180, 250));
        pnlClientProfile.setOpaque(false);
        pnlClientProfile.setPreferredSize(new java.awt.Dimension(180, 250));
        pnlClientProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFemaleClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/female-client.png"))); // NOI18N
        lblFemaleClient.setToolTipText("");
        pnlClientProfile.add(lblFemaleClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 150, 150));

        lblMaleClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/male-client.png"))); // NOI18N
        lblMaleClient.setToolTipText("");
        pnlClientProfile.add(lblMaleClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 150, 150));

        lblClientName.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblClientName.setForeground(new java.awt.Color(0, 0, 0));
        lblClientName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClientName.setText("Client N.");
        pnlClientProfile.add(lblClientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 180, -1));

        lblAccountBalance.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblAccountBalance.setForeground(new java.awt.Color(255, 255, 255));
        lblAccountBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAccountBalance.setText("ACCOUNT BALANCE");
        pnlClientProfile.add(lblAccountBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 225, 180, -1));

        lblCurrentBalance.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lblCurrentBalance.setForeground(new java.awt.Color(51, 255, 51));
        lblCurrentBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentBalance.setText("3,000$");
        pnlClientProfile.add(lblCurrentBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 235, 180, 30));

        lblAcctNumTxt.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblAcctNumTxt.setForeground(new java.awt.Color(255, 255, 255));
        lblAcctNumTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcctNumTxt.setText("ACCOUNT #");
        pnlClientProfile.add(lblAcctNumTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 180, -1));

        lblClientAcctNum.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblClientAcctNum.setForeground(new java.awt.Color(0, 0, 0));
        lblClientAcctNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlClientProfile.add(lblClientAcctNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 180, -1));

        pnlClientNav.add(pnlClientProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 290));

        pnlNavServices.setMaximumSize(new java.awt.Dimension(180, 40));
        pnlNavServices.setMinimumSize(new java.awt.Dimension(180, 40));
        pnlNavServices.setOpaque(false);
        pnlNavServices.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlNavServices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavServicesMouseClicked(evt);
            }
        });
        pnlNavServices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setBackground(new java.awt.Color(242, 60, 60));
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setOpaque(true);
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        pnlNavServices.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        jLabel30.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(84, 84, 84));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Services");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        pnlNavServices.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2, 140, 36));

        pnlClientNav.add(pnlNavServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 180, 40));

        pnlNavHistory.setMaximumSize(new java.awt.Dimension(180, 40));
        pnlNavHistory.setMinimumSize(new java.awt.Dimension(180, 40));
        pnlNavHistory.setOpaque(false);
        pnlNavHistory.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlNavHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNavHistoryMouseClicked(evt);
            }
        });
        pnlNavHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(242, 60, 60));
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        pnlNavHistory.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        jLabel35.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(84, 84, 84));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("History");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        pnlNavHistory.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2, 140, 36));

        pnlClientNav.add(pnlNavHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 40));

        pnlCliNavSettings.setMaximumSize(new java.awt.Dimension(180, 40));
        pnlCliNavSettings.setMinimumSize(new java.awt.Dimension(180, 40));
        pnlCliNavSettings.setOpaque(false);
        pnlCliNavSettings.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlCliNavSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCliNavSettingsMouseClicked(evt);
            }
        });
        pnlCliNavSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(242, 60, 60));
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        pnlCliNavSettings.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 6, 40));

        jLabel37.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(84, 84, 84));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Settings");
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        pnlCliNavSettings.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2, 140, 36));

        pnlClientNav.add(pnlCliNavSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 180, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/Logout.png"))); // NOI18N
        jLabel13.setToolTipText("");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        pnlClientNav.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 80, 80));

        pnlBanking.add(pnlClientNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 750));

        pnlClientWindow.setMaximumSize(new java.awt.Dimension(640, 750));
        pnlClientWindow.setMinimumSize(new java.awt.Dimension(640, 750));
        pnlClientWindow.setOpaque(false);
        pnlClientWindow.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlClientWindow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlServices.setMaximumSize(new java.awt.Dimension(640, 750));
        pnlServices.setMinimumSize(new java.awt.Dimension(640, 750));
        pnlServices.setOpaque(false);
        pnlServices.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlServices.setSize(new java.awt.Dimension(640, 750));
        pnlServices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDeposit.setBackground(new java.awt.Color(255, 255, 255));
        txtDeposit.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtDeposit.setBorder(null);
        txtDeposit.setMaximumSize(new java.awt.Dimension(156, 29));
        txtDeposit.setMinimumSize(new java.awt.Dimension(156, 29));
        txtDeposit.setPreferredSize(new java.awt.Dimension(156, 29));
        txtDeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepositKeyTyped(evt);
            }
        });
        pnlServices.add(txtDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 69, 156, 29));

        txtWithdraw.setBackground(new java.awt.Color(255, 255, 255));
        txtWithdraw.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtWithdraw.setBorder(null);
        txtWithdraw.setMaximumSize(new java.awt.Dimension(156, 29));
        txtWithdraw.setMinimumSize(new java.awt.Dimension(156, 29));
        txtWithdraw.setPreferredSize(new java.awt.Dimension(156, 29));
        txtWithdraw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtWithdrawKeyTyped(evt);
            }
        });
        pnlServices.add(txtWithdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 230, 156, 29));

        txtTransfer.setBackground(new java.awt.Color(255, 255, 255));
        txtTransfer.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtTransfer.setBorder(null);
        txtTransfer.setMaximumSize(new java.awt.Dimension(156, 29));
        txtTransfer.setMinimumSize(new java.awt.Dimension(156, 29));
        txtTransfer.setPreferredSize(new java.awt.Dimension(156, 29));
        txtTransfer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTransferKeyTyped(evt);
            }
        });
        pnlServices.add(txtTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 394, 156, 29));

        txtRequest.setBackground(new java.awt.Color(255, 255, 255));
        txtRequest.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtRequest.setBorder(null);
        txtRequest.setMaximumSize(new java.awt.Dimension(156, 29));
        txtRequest.setMinimumSize(new java.awt.Dimension(156, 29));
        txtRequest.setPreferredSize(new java.awt.Dimension(156, 29));
        txtRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRequestActionPerformed(evt);
            }
        });
        txtRequest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRequestKeyTyped(evt);
            }
        });
        pnlServices.add(txtRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 606, 156, 29));

        txtTransferAccount.setBackground(new java.awt.Color(255, 255, 255));
        txtTransferAccount.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtTransferAccount.setBorder(null);
        txtTransferAccount.setMaximumSize(new java.awt.Dimension(295, 29));
        txtTransferAccount.setMinimumSize(new java.awt.Dimension(295, 29));
        txtTransferAccount.setPreferredSize(new java.awt.Dimension(295, 29));
        txtTransferAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTransferAccountKeyTyped(evt);
            }
        });
        pnlServices.add(txtTransferAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 441, 295, 29));

        txtRequestAccount.setBackground(new java.awt.Color(255, 255, 255));
        txtRequestAccount.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtRequestAccount.setBorder(null);
        txtRequestAccount.setMaximumSize(new java.awt.Dimension(295, 29));
        txtRequestAccount.setMinimumSize(new java.awt.Dimension(295, 29));
        txtRequestAccount.setPreferredSize(new java.awt.Dimension(295, 29));
        txtRequestAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRequestAccountKeyTyped(evt);
            }
        });
        pnlServices.add(txtRequestAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 653, 295, 29));

        btnResetDeposit.setBackground(new java.awt.Color(42, 39, 41));
        btnResetDeposit.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnResetDeposit.setForeground(new java.awt.Color(255, 255, 255));
        btnResetDeposit.setText("Reset");
        btnResetDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDepositActionPerformed(evt);
            }
        });
        pnlServices.add(btnResetDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 120, 110, 27));

        btnDeposit.setBackground(new java.awt.Color(42, 39, 41));
        btnDeposit.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnDeposit.setForeground(new java.awt.Color(255, 255, 255));
        btnDeposit.setText("Deposit");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });
        pnlServices.add(btnDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 120, 110, 27));

        btnResetWithdraw.setBackground(new java.awt.Color(42, 39, 41));
        btnResetWithdraw.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnResetWithdraw.setForeground(new java.awt.Color(255, 255, 255));
        btnResetWithdraw.setText("Reset");
        btnResetWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetWithdrawActionPerformed(evt);
            }
        });
        pnlServices.add(btnResetWithdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 280, 110, 27));

        btnWithdraw.setBackground(new java.awt.Color(42, 39, 41));
        btnWithdraw.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnWithdraw.setForeground(new java.awt.Color(255, 255, 255));
        btnWithdraw.setText("Withdraw");
        btnWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawActionPerformed(evt);
            }
        });
        pnlServices.add(btnWithdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 280, 110, 27));

        btnTransfer.setBackground(new java.awt.Color(42, 39, 41));
        btnTransfer.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnTransfer.setForeground(new java.awt.Color(255, 255, 255));
        btnTransfer.setText("Transfer");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });
        pnlServices.add(btnTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 490, 110, 27));

        btnResetTransfer.setBackground(new java.awt.Color(42, 39, 41));
        btnResetTransfer.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnResetTransfer.setForeground(new java.awt.Color(255, 255, 255));
        btnResetTransfer.setText("Reset");
        btnResetTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTransferActionPerformed(evt);
            }
        });
        pnlServices.add(btnResetTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 490, 110, 27));

        btnRequest.setBackground(new java.awt.Color(42, 39, 41));
        btnRequest.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnRequest.setText("Request");
        btnRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestActionPerformed(evt);
            }
        });
        pnlServices.add(btnRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 700, 110, 27));

        btnResetRequest.setBackground(new java.awt.Color(42, 39, 41));
        btnResetRequest.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnResetRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnResetRequest.setText("Reset");
        btnResetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetRequestActionPerformed(evt);
            }
        });
        pnlServices.add(btnResetRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 700, 110, 27));

        btnCheckReqAcct.setBackground(new java.awt.Color(42, 39, 41));
        btnCheckReqAcct.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnCheckReqAcct.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckReqAcct.setText("Check");
        btnCheckReqAcct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckReqAcctActionPerformed(evt);
            }
        });
        pnlServices.add(btnCheckReqAcct, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 652, 110, 31));

        btnCheckTransferAcct.setBackground(new java.awt.Color(42, 39, 41));
        btnCheckTransferAcct.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        btnCheckTransferAcct.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckTransferAcct.setText("Check");
        btnCheckTransferAcct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckTransferAcctActionPerformed(evt);
            }
        });
        pnlServices.add(btnCheckTransferAcct, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 110, 31));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/servicesPanel.png"))); // NOI18N
        jLabel40.setToolTipText("");
        pnlServices.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlClientWindow.add(pnlServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlHistory.setMaximumSize(new java.awt.Dimension(640, 750));
        pnlHistory.setMinimumSize(new java.awt.Dimension(640, 750));
        pnlHistory.setOpaque(false);
        pnlHistory.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlHistory.setSize(new java.awt.Dimension(640, 750));
        pnlHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTblAllTransactions.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTblAllTransactions.setForeground(new java.awt.Color(0, 0, 0));
        lblTblAllTransactions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTblAllTransactions.setText("All Transactions");
        lblTblAllTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTblAllTransactionsMouseClicked(evt);
            }
        });
        pnlHistory.add(lblTblAllTransactions, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 150, 30));

        lblTblDeposits.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTblDeposits.setForeground(new java.awt.Color(0, 0, 0));
        lblTblDeposits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTblDeposits.setText("Deposits");
        lblTblDeposits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTblDepositsMouseClicked(evt);
            }
        });
        pnlHistory.add(lblTblDeposits, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 130, 30));

        lblTblWithdrawals.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTblWithdrawals.setForeground(new java.awt.Color(0, 0, 0));
        lblTblWithdrawals.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTblWithdrawals.setText("Withdrawals");
        lblTblWithdrawals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTblWithdrawalsMouseClicked(evt);
            }
        });
        pnlHistory.add(lblTblWithdrawals, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 130, 30));

        lblTblTransfers.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTblTransfers.setForeground(new java.awt.Color(0, 0, 0));
        lblTblTransfers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTblTransfers.setText("Transfers");
        lblTblTransfers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTblTransfersMouseClicked(evt);
            }
        });
        pnlHistory.add(lblTblTransfers, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 150, 30));

        scrAllTrans.setBackground(new java.awt.Color(20, 200, 200));
        scrAllTrans.setBorder(null);
        scrAllTrans.setForeground(new java.awt.Color(255, 255, 255));
        scrAllTrans.setAlignmentX(0.0F);
        scrAllTrans.setAlignmentY(0.0F);
        scrAllTrans.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        scrAllTrans.setMinimumSize(new java.awt.Dimension(5, 16));

        JTableHeader header = tblAll.getTableHeader();
        header.setForeground(Color.BLACK);
        tblAll.setBackground(new Color(65,150, 249));
        tblAll.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblAll.setForeground(new java.awt.Color(255, 255, 255));
        tblAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "TYPE", "FROM", "TO", "AMOUNT", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAll.setAlignmentX(0.0F);
        tblAll.setAlignmentY(0.0F);
        tblAll.setFocusable(false);
        tblAll.setGridColor(new java.awt.Color(78, 67, 87));
        tblAll.setOpaque(false);
        tblAll.setRowHeight(25);
        tblAll.setSelectionBackground(new java.awt.Color(242, 60, 60));
        tblAll.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblAll.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblAll.getTableHeader().setResizingAllowed(false);
        tblAll.getTableHeader().setReorderingAllowed(false);
        tblAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllMouseClicked(evt);
            }
        });
        scrAllTrans.setViewportView(tblAll);

        pnlHistory.add(scrAllTrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 630, 560));

        lblBig.setToolTipText("");
        lblBig.setMaximumSize(new java.awt.Dimension(640, 750));
        lblBig.setMinimumSize(new java.awt.Dimension(640, 750));
        lblBig.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlHistory.add(lblBig, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        scrDep.setBackground(new java.awt.Color(20, 200, 200));
        scrDep.setBorder(null);
        scrDep.setForeground(new java.awt.Color(255, 255, 255));
        scrDep.setAlignmentX(0.0F);
        scrDep.setAlignmentY(0.0F);
        scrDep.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        scrDep.setMinimumSize(new java.awt.Dimension(5, 16));

        JTableHeader header1 = tblDep.getTableHeader();
        header1.setForeground(Color.BLACK);
        tblDep.setBackground(new Color(65,150, 249));
        tblDep.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblDep.setForeground(new java.awt.Color(255, 255, 255));
        tblDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "TYPE", "FROM", "TO", "AMOUNT", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDep.setAlignmentX(0.0F);
        tblDep.setAlignmentY(0.0F);
        tblDep.setFocusable(false);
        tblDep.setGridColor(new java.awt.Color(78, 67, 87));
        tblDep.setOpaque(false);
        tblDep.setRowHeight(25);
        tblDep.setSelectionBackground(new java.awt.Color(242, 60, 60));
        tblDep.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblDep.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblDep.getTableHeader().setResizingAllowed(false);
        tblDep.getTableHeader().setReorderingAllowed(false);
        tblDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepMouseClicked(evt);
            }
        });
        scrDep.setViewportView(tblDep);

        pnlHistory.add(scrDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 630, 560));

        scrWith.setBackground(new java.awt.Color(20, 200, 200));
        scrWith.setBorder(null);
        scrWith.setForeground(new java.awt.Color(255, 255, 255));
        scrWith.setAlignmentX(0.0F);
        scrWith.setAlignmentY(0.0F);
        scrWith.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        scrWith.setMinimumSize(new java.awt.Dimension(5, 16));

        JTableHeader header5 = tblWith.getTableHeader();
        header5.setForeground(Color.BLACK);
        tblWith.setBackground(new Color(65,150, 249));
        tblWith.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblWith.setForeground(new java.awt.Color(255, 255, 255));
        tblWith.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "TYPE", "FROM", "TO", "AMOUNT", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWith.setAlignmentX(0.0F);
        tblWith.setAlignmentY(0.0F);
        tblWith.setFocusable(false);
        tblWith.setGridColor(new java.awt.Color(78, 67, 87));
        tblWith.setOpaque(false);
        tblWith.setRowHeight(25);
        tblWith.setSelectionBackground(new java.awt.Color(242, 60, 60));
        tblWith.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblWith.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblWith.getTableHeader().setResizingAllowed(false);
        tblWith.getTableHeader().setReorderingAllowed(false);
        tblWith.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWithMouseClicked(evt);
            }
        });
        scrWith.setViewportView(tblWith);

        pnlHistory.add(scrWith, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 630, 560));

        scrTran.setBackground(new java.awt.Color(20, 200, 200));
        scrTran.setBorder(null);
        scrTran.setForeground(new java.awt.Color(255, 255, 255));
        scrTran.setAlignmentX(0.0F);
        scrTran.setAlignmentY(0.0F);
        scrTran.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        scrTran.setMinimumSize(new java.awt.Dimension(5, 16));

        JTableHeader header3 = tblTran.getTableHeader();
        header3.setForeground(Color.BLACK);
        tblTran.setBackground(new Color(65,150, 249));
        tblTran.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblTran.setForeground(new java.awt.Color(255, 255, 255));
        tblTran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "TYPE", "FROM", "TO", "AMOUNT", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTran.setAlignmentX(0.0F);
        tblTran.setAlignmentY(0.0F);
        tblTran.setFocusable(false);
        tblTran.setGridColor(new java.awt.Color(78, 67, 87));
        tblTran.setOpaque(false);
        tblTran.setRowHeight(25);
        tblTran.setSelectionBackground(new java.awt.Color(242, 60, 60));
        tblTran.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblTran.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblTran.getTableHeader().setResizingAllowed(false);
        tblTran.getTableHeader().setReorderingAllowed(false);
        tblTran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTranMouseClicked(evt);
            }
        });
        scrTran.setViewportView(tblTran);

        pnlHistory.add(scrTran, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 630, 560));

        pnlClientWindow.add(pnlHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlClientSettings.setMaximumSize(new java.awt.Dimension(640, 750));
        pnlClientSettings.setMinimumSize(new java.awt.Dimension(640, 750));
        pnlClientSettings.setOpaque(false);
        pnlClientSettings.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlClientSettings.setSize(new java.awt.Dimension(640, 750));
        pnlClientSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnResetCS.setBackground(new java.awt.Color(42, 39, 41));
        btnResetCS.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnResetCS.setForeground(new java.awt.Color(255, 255, 255));
        btnResetCS.setText("Reset");
        btnResetCS.setBorderPainted(false);
        btnResetCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCSActionPerformed(evt);
            }
        });
        pnlClientSettings.add(btnResetCS, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 650, 120, 37));

        btnUpdateClient.setBackground(new java.awt.Color(42, 39, 41));
        btnUpdateClient.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnUpdateClient.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateClient.setText("Update");
        btnUpdateClient.setBorderPainted(false);
        btnUpdateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClientActionPerformed(evt);
            }
        });
        pnlClientSettings.add(btnUpdateClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 650, 160, 37));

        txtEmail1.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtEmail1.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail1.setToolTipText("");
        txtEmail1.setBorder(null);
        txtEmail1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtEmail1.setMaximumSize(new java.awt.Dimension(64, 22));
        pnlClientSettings.add(txtEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 251, 295, 33));

        txtPostalCode1.setBackground(new java.awt.Color(255, 255, 255));
        txtPostalCode1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtPostalCode1.setForeground(new java.awt.Color(0, 0, 0));
        txtPostalCode1.setBorder(null);
        txtPostalCode1.setMaximumSize(new java.awt.Dimension(115, 30));
        txtPostalCode1.setMinimumSize(new java.awt.Dimension(115, 30));
        txtPostalCode1.setPreferredSize(new java.awt.Dimension(115, 30));
        txtPostalCode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPostalCode1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtPostalCode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 461, 148, 33));

        txtFirstName1.setBackground(new java.awt.Color(255, 255, 255));
        txtFirstName1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtFirstName1.setForeground(new java.awt.Color(0, 0, 0));
        txtFirstName1.setToolTipText("");
        txtFirstName1.setBorder(null);
        txtFirstName1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFirstName1.setMaximumSize(new java.awt.Dimension(64, 22));
        txtFirstName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFirstName1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtFirstName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 146, 295, 33));

        txtLastName1.setBackground(new java.awt.Color(255, 255, 255));
        txtLastName1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtLastName1.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName1.setToolTipText("");
        txtLastName1.setBorder(null);
        txtLastName1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLastName1.setMaximumSize(new java.awt.Dimension(64, 22));
        txtLastName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastName1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 196, 295, 33));

        txtDOB1.setBackground(new java.awt.Color(255, 255, 255));
        txtDOB1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtDOB1.setForeground(new java.awt.Color(0, 0, 0));
        txtDOB1.setBorder(null);
        txtDOB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDOB1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtDOB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 301, 295, 33));

        txtStreet1.setBackground(new java.awt.Color(255, 255, 255));
        txtStreet1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtStreet1.setForeground(new java.awt.Color(0, 0, 0));
        txtStreet1.setBorder(null);
        txtStreet1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStreet1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtStreet1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 356, 295, 33));

        txtCity1.setBackground(new java.awt.Color(255, 255, 255));
        txtCity1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtCity1.setForeground(new java.awt.Color(0, 0, 0));
        txtCity1.setBorder(null);
        txtCity1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCity1KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtCity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 406, 295, 33));

        cbSex1.setBackground(new java.awt.Color(42, 39, 41));
        cbSex1.setEditable(true);
        cbSex1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbSex1.setForeground(new java.awt.Color(255, 255, 255));
        cbSex1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "M", "F" }));
        cbSex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSex1ActionPerformed(evt);
            }
        });
        pnlClientSettings.add(cbSex1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 510, 70, 30));

        txtPIN2.setBackground(new java.awt.Color(255, 255, 255));
        txtPIN2.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        txtPIN2.setForeground(new java.awt.Color(0, 0, 0));
        txtPIN2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPIN2.setBorder(null);
        txtPIN2.setMaximumSize(new java.awt.Dimension(115, 30));
        txtPIN2.setMinimumSize(new java.awt.Dimension(115, 30));
        txtPIN2.setPreferredSize(new java.awt.Dimension(115, 30));
        txtPIN2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPIN2KeyTyped(evt);
            }
        });
        pnlClientSettings.add(txtPIN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 567, 148, 33));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/clientSettings.png"))); // NOI18N
        jLabel42.setText("Settings");
        jLabel42.setToolTipText("");
        jLabel42.setMaximumSize(new java.awt.Dimension(640, 750));
        jLabel42.setMinimumSize(new java.awt.Dimension(640, 750));
        jLabel42.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlClientSettings.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlClientWindow.add(pnlClientSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlAlerts.setMaximumSize(new java.awt.Dimension(640, 750));
        pnlAlerts.setMinimumSize(new java.awt.Dimension(640, 750));
        pnlAlerts.setOpaque(false);
        pnlAlerts.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlAlerts.setSize(new java.awt.Dimension(640, 750));
        pnlAlerts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBackground(new java.awt.Color(20, 200, 200));
        jScrollPane5.setBorder(null);
        jScrollPane5.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setAlignmentX(0.0F);
        jScrollPane5.setAlignmentY(0.0F);
        jScrollPane5.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        jScrollPane5.setMinimumSize(new java.awt.Dimension(5, 16));

        JTableHeader header2 = tblReq.getTableHeader();
        header2.setForeground(Color.BLACK);
        tblReq.setBackground(new Color(65,150, 249));
        tblReq.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblReq.setForeground(new java.awt.Color(255, 255, 255));
        tblReq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "DATE", "TYPE", "FROM", "TO", "AMOUNT", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReq.setAlignmentX(0.0F);
        tblReq.setAlignmentY(0.0F);
        tblReq.setFocusable(false);
        tblReq.setGridColor(new java.awt.Color(78, 67, 87));
        tblReq.setOpaque(false);
        tblReq.setRowHeight(25);
        tblReq.setSelectionBackground(new java.awt.Color(242, 60, 60));
        tblReq.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblReq.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblReq.getTableHeader().setResizingAllowed(false);
        tblReq.getTableHeader().setReorderingAllowed(false);
        tblReq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReqMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblReq);

        pnlAlerts.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 50, 630, 490));

        txtTransId.setBackground(new java.awt.Color(255, 255, 255));
        txtTransId.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtTransId.setForeground(new java.awt.Color(0, 0, 0));
        txtTransId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTransId.setBorder(null);
        pnlAlerts.add(txtTransId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 70, 30));

        btnApproveRequest.setBackground(new java.awt.Color(42, 29, 41));
        btnApproveRequest.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnApproveRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnApproveRequest.setText("Approve");
        btnApproveRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveRequestActionPerformed(evt);
            }
        });
        pnlAlerts.add(btnApproveRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 680, 120, -1));

        btnRejectRequest.setBackground(new java.awt.Color(42, 39, 41));
        btnRejectRequest.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnRejectRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnRejectRequest.setText("Reject");
        btnRejectRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectRequestActionPerformed(evt);
            }
        });
        pnlAlerts.add(btnRejectRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 680, 130, -1));

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Transaction #");
        pnlAlerts.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 620, 160, 30));

        jLabel43.setToolTipText("");
        jLabel43.setMaximumSize(new java.awt.Dimension(640, 750));
        jLabel43.setMinimumSize(new java.awt.Dimension(640, 750));
        jLabel43.setPreferredSize(new java.awt.Dimension(640, 750));
        pnlAlerts.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlClientWindow.add(pnlAlerts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 750));

        pnlBanking.add(pnlClientWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 640, 750));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/panelGradient.png"))); // NOI18N
        jLabel22.setToolTipText("");
        pnlBanking.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlMain.add(pnlBanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlAuthorize.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlAuthorize.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlAuthorize.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlAuthorize.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnResetEmployee.setBackground(new java.awt.Color(42, 39, 41));
        btnResetEmployee.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnResetEmployee.setForeground(new java.awt.Color(255, 255, 255));
        btnResetEmployee.setText("Reset");
        btnResetEmployee.setBorderPainted(false);
        btnResetEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetEmployeeActionPerformed(evt);
            }
        });
        pnlAuthorize.add(btnResetEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 590, 120, 37));

        btnAuthorize.setBackground(new java.awt.Color(42, 39, 41));
        btnAuthorize.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnAuthorize.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthorize.setText("Authorize");
        btnAuthorize.setBorderPainted(false);
        btnAuthorize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthorizeActionPerformed(evt);
            }
        });
        pnlAuthorize.add(btnAuthorize, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 590, 150, 37));

        txtAuthFN.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthFN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthFN.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthFN.setBorder(null);
        pnlAuthorize.add(txtAuthFN, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 127, 290, 35));

        txtAuthLN.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthLN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthLN.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthLN.setBorder(null);
        txtAuthLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuthLNActionPerformed(evt);
            }
        });
        txtAuthLN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAuthLNKeyTyped(evt);
            }
        });
        pnlAuthorize.add(txtAuthLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 290, 35));

        txtAuthEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthEmail.setBorder(null);
        pnlAuthorize.add(txtAuthEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 233, 290, 35));

        txtAuthDOB.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthDOB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthDOB.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthDOB.setBorder(null);
        pnlAuthorize.add(txtAuthDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 287, 290, 35));

        txtAuthUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthUsername.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthUsername.setBorder(null);
        txtAuthUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAuthUsernameKeyTyped(evt);
            }
        });
        pnlAuthorize.add(txtAuthUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 389, 290, 35));

        txtAuthPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtAuthPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtAuthPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtAuthPassword.setBorder(null);
        pnlAuthorize.add(txtAuthPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 442, 290, 35));

        cbSexUserAuth.setBackground(new java.awt.Color(42, 39, 41));
        cbSexUserAuth.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbSexUserAuth.setForeground(new java.awt.Color(255, 255, 255));
        cbSexUserAuth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "M", "F" }));
        cbSexUserAuth.setBorder(null);
        cbSexUserAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexUserAuthActionPerformed(evt);
            }
        });
        pnlAuthorize.add(cbSexUserAuth, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 340, 70, 30));

        cbAuthorizationSet.setBackground(new java.awt.Color(42, 39, 41));
        cbAuthorizationSet.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbAuthorizationSet.setForeground(new java.awt.Color(255, 255, 255));
        cbAuthorizationSet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2" }));
        cbAuthorizationSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAuthorizationSetActionPerformed(evt);
            }
        });
        pnlAuthorize.add(cbAuthorizationSet, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 500, 70, 30));

        btnCheckUsername1.setBackground(new java.awt.Color(42, 39, 41));
        btnCheckUsername1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnCheckUsername1.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckUsername1.setText("Check");
        btnCheckUsername1.setBorderPainted(false);
        btnCheckUsername1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckUsername1ActionPerformed(evt);
            }
        });
        pnlAuthorize.add(btnCheckUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 389, 120, 37));

        btnAuthImage.setBackground(new java.awt.Color(42, 39, 41));
        btnAuthImage.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAuthImage.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthImage.setText("Choose Image");
        btnAuthImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthImageActionPerformed(evt);
            }
        });
        pnlAuthorize.add(btnAuthImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 140, 37));

        jLabel48.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlAuthorize.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 820, 70));

        jLabel49.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlAuthorize.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 820, 30));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/authorizationPanel.png"))); // NOI18N
        jLabel23.setToolTipText("");
        jLabel23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel23MouseMoved(evt);
            }
        });
        pnlAuthorize.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));
        pnlAuthorize.add(lblAuthImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 140, 140));

        pnlMain.add(pnlAuthorize, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlSettings.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlSettings.setMinimumSize(new java.awt.Dimension(820, 750));
        pnlSettings.setPreferredSize(new java.awt.Dimension(820, 750));
        pnlSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCheckUsername.setBackground(new java.awt.Color(42, 39, 41));
        btnCheckUsername.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnCheckUsername.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckUsername.setText("Check");
        btnCheckUsername.setBorderPainted(false);
        btnCheckUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckUsernameActionPerformed(evt);
            }
        });
        pnlSettings.add(btnCheckUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 389, 120, 37));

        btnResetSettings.setBackground(new java.awt.Color(42, 39, 41));
        btnResetSettings.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnResetSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnResetSettings.setText("Reset");
        btnResetSettings.setBorderPainted(false);
        btnResetSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSettingsActionPerformed(evt);
            }
        });
        pnlSettings.add(btnResetSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 510, 120, 37));

        btnSaveSettings.setBackground(new java.awt.Color(42, 39, 41));
        btnSaveSettings.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSaveSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveSettings.setText("Save Changes");
        btnSaveSettings.setBorderPainted(false);
        btnSaveSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSettingsActionPerformed(evt);
            }
        });
        pnlSettings.add(btnSaveSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 510, 160, 37));

        txtUserFirstName.setBackground(new java.awt.Color(255, 255, 255));
        txtUserFirstName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserFirstName.setForeground(new java.awt.Color(0, 0, 0));
        txtUserFirstName.setBorder(null);
        txtUserFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserFirstNameActionPerformed(evt);
            }
        });
        txtUserFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserFirstNameKeyTyped(evt);
            }
        });
        pnlSettings.add(txtUserFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 127, 290, 35));

        txtUserLastName.setBackground(new java.awt.Color(255, 255, 255));
        txtUserLastName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserLastName.setForeground(new java.awt.Color(0, 0, 0));
        txtUserLastName.setBorder(null);
        txtUserLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserLastNameKeyTyped(evt);
            }
        });
        pnlSettings.add(txtUserLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 290, 35));

        txtUserEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtUserEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtUserEmail.setBorder(null);
        pnlSettings.add(txtUserEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 233, 290, 35));

        txtUserDOB.setBackground(new java.awt.Color(255, 255, 255));
        txtUserDOB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserDOB.setForeground(new java.awt.Color(0, 0, 0));
        txtUserDOB.setBorder(null);
        pnlSettings.add(txtUserDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 287, 290, 35));

        txtUserUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUserUsername.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtUserUsername.setBorder(null);
        txtUserUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserUsernameKeyTyped(evt);
            }
        });
        pnlSettings.add(txtUserUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 389, 290, 35));

        txtUserPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtUserPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtUserPassword.setBorder(null);
        pnlSettings.add(txtUserPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 442, 290, 35));

        cbUserSex.setBackground(new java.awt.Color(42, 39, 41));
        cbUserSex.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbUserSex.setForeground(new java.awt.Color(255, 255, 255));
        cbUserSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "M", "F" }));
        cbUserSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUserSexActionPerformed(evt);
            }
        });
        pnlSettings.add(cbUserSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 340, 70, 30));
        pnlSettings.add(lblUpdateImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 140, 140));

        btnUpdateImage.setBackground(new java.awt.Color(42, 39, 41));
        btnUpdateImage.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnUpdateImage.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateImage.setText("Upload Image");
        btnUpdateImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateImageActionPerformed(evt);
            }
        });
        pnlSettings.add(btnUpdateImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 140, 37));

        jLabel44.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 204, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("UPDATE WAS SUCCESSFUL");
        jLabel44.setToolTipText("");
        pnlSettings.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 820, 80));

        jLabel45.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 204, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setToolTipText("");
        pnlSettings.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 820, 80));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/settingsPanel.png"))); // NOI18N
        jLabel25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel25MouseMoved(evt);
            }
        });
        pnlSettings.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        jLabel20.setText("jLabel20");
        pnlSettings.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, 70, 40));

        pnlMain.add(pnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlChat.setMaximumSize(new java.awt.Dimension(820, 750));
        pnlChat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);

        txtMsgArea.setEditable(false);
        txtMsgArea.setBackground(new java.awt.Color(255, 255, 255));
        txtMsgArea.setColumns(20);
        txtMsgArea.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtMsgArea.setForeground(new java.awt.Color(0, 0, 0));
        txtMsgArea.setRows(5);
        txtMsgArea.setBorder(null);
        txtMsgArea.setSize(new java.awt.Dimension(320, 110));
        jScrollPane2.setViewportView(txtMsgArea);

        pnlChat.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 80, 404, 490));

        jScrollPane3.setBorder(null);

        txtUserSendMsg.setBackground(new java.awt.Color(255, 255, 255));
        txtUserSendMsg.setColumns(20);
        txtUserSendMsg.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUserSendMsg.setForeground(new java.awt.Color(0, 0, 0));
        txtUserSendMsg.setRows(5);
        txtUserSendMsg.setPreferredSize(new java.awt.Dimension(320, 110));
        txtUserSendMsg.setSize(new java.awt.Dimension(320, 110));
        jScrollPane3.setViewportView(txtUserSendMsg);

        pnlChat.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, 415, 90));

        jScrollPane4.setBorder(null);

        OnlineUsersList.setBackground(new java.awt.Color(255, 255, 255));
        OnlineUsersList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Online", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 0, 13), new java.awt.Color(0, 153, 0))); // NOI18N
        OnlineUsersList.setForeground(new java.awt.Color(51, 102, 255));
        OnlineUsersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                OnlineUsersListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(OnlineUsersList);

        pnlChat.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 200, 250));

        btnSendMessage.setBackground(new java.awt.Color(42, 39, 41));
        btnSendMessage.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSendMessage.setForeground(new java.awt.Color(255, 255, 255));
        btnSendMessage.setText("Send");
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
            }
        });
        pnlChat.add(btnSendMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 120, 40));

        btnGoOnline.setBackground(new java.awt.Color(42, 39, 41));
        btnGoOnline.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnGoOnline.setForeground(new java.awt.Color(255, 255, 255));
        btnGoOnline.setText("Online");
        btnGoOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoOnlineActionPerformed(evt);
            }
        });
        pnlChat.add(btnGoOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, -1, -1));

        btnGoOffline.setBackground(new java.awt.Color(42, 39, 41));
        btnGoOffline.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnGoOffline.setForeground(new java.awt.Color(255, 255, 255));
        btnGoOffline.setText("Offline");
        btnGoOffline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoOfflineActionPerformed(evt);
            }
        });
        pnlChat.add(btnGoOffline, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, -1, -1));

        btnSelectAll.setBackground(new java.awt.Color(42, 39, 41));
        btnSelectAll.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSelectAll.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectAll.setText("Select All");
        btnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectAllActionPerformed(evt);
            }
        });
        pnlChat.add(btnSelectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 160, -1));

        lblSuccess.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        lblSuccess.setForeground(new java.awt.Color(51, 255, 51));
        pnlChat.add(lblSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 240, 50));

        jLabel54.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Select Recepient:");
        pnlChat.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 200, -1));

        jLabel52.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Write Your Message Here:");
        jLabel52.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlChat.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 220, 30));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/chatPanel.png"))); // NOI18N
        pnlChat.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        pnlMain.add(pnlChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 750));

        jPanel1.add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 820, 750));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlNavHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavHomeMouseClicked
        bar(jLabel2);
        window(pnlHome);
    }//GEN-LAST:event_pnlNavHomeMouseClicked

    private void pnlNavHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavHomeMouseEntered
        pnlNavHome.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavHomeMouseEntered

    private void pnlNavHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavHomeMouseExited
        pnlNavHome.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavHomeMouseExited

    private void pnlTurnOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTurnOffMouseClicked
        String i = "mkoihgteazdcvgyhujb096785542skrt";
        try {
            doutMain.writeUTF(i);
            this.dispose();
            new Login();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlTurnOffMouseClicked

    private void pnlTurnOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTurnOffMouseEntered
        pnlTurnOff.setBackground(Color.decode("#f23c3c"));
    }//GEN-LAST:event_pnlTurnOffMouseEntered

    private void pnlTurnOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTurnOffMouseExited
        pnlTurnOff.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlTurnOffMouseExited

    private void pnlNavAccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAccountsMouseClicked
        bar(jLabel5);
        window(pnlAcct);
    }//GEN-LAST:event_pnlNavAccountsMouseClicked

    private void pnlNavAccountsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAccountsMouseEntered
        pnlNavAccounts.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavAccountsMouseEntered

    private void pnlNavAccountsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAccountsMouseExited
        pnlNavAccounts.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavAccountsMouseExited

    private void pnlNavBankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavBankingMouseClicked
        bar(jLabel8);
        window(pnlBankingPIN);
        jLabel46.setText("");
        jLabel47.setText("");

    }//GEN-LAST:event_pnlNavBankingMouseClicked

    private void pnlNavBankingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavBankingMouseEntered
        pnlNavBanking.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavBankingMouseEntered

    private void pnlNavBankingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavBankingMouseExited
        pnlNavBanking.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavBankingMouseExited

    private void pnlNavAuthorizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAuthorizeMouseClicked
        bar(jLabel11);
        window(pnlAuthorize);

    }//GEN-LAST:event_pnlNavAuthorizeMouseClicked

    private void pnlNavAuthorizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAuthorizeMouseEntered
        pnlNavAuthorize.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavAuthorizeMouseEntered

    private void pnlNavAuthorizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAuthorizeMouseExited
        pnlNavAuthorize.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavAuthorizeMouseExited

    private void pnlNavSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavSettingsMouseClicked
        bar(jLabel17);
        window(pnlSettings);
        fillUserFields(this.user);
    }//GEN-LAST:event_pnlNavSettingsMouseClicked

    private void pnlNavSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavSettingsMouseEntered
        pnlNavSettings.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavSettingsMouseEntered

    private void pnlNavSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavSettingsMouseExited
        pnlNavSettings.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavSettingsMouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        bar(jLabel2);
        window(pnlHome);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        bar(jLabel5);
        window(pnlAcct);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        bar(jLabel8);
        window(pnlBankingPIN);
        jLabel46.setText("");
        jLabel47.setText("");
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        bar(jLabel11);
        window(pnlAuthorize);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        bar(jLabel17);
        window(pnlSettings);
        fillUserFields(this.user);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        bar(jLabel2);
        window(pnlHome);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        bar(jLabel5);
        window(pnlAcct);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        bar(jLabel8);
        window(pnlBankingPIN);
        jLabel46.setText("");
        jLabel47.setText("");
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        bar(jLabel11);
        window(pnlAuthorize);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        bar(jLabel17);
        window(pnlSettings);
        fillUserFields(this.user);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        pnlNavHome.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        pnlNavHome.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        pnlNavAccounts.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        pnlNavAccounts.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        pnlNavBanking.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        pnlNavBanking.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        pnlNavAuthorize.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        pnlNavAuthorize.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        pnlNavSettings.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        pnlNavSettings.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        String i = "mkoihgteazdcvgyhujb096785542skrt";
        try {
            doutMain.writeUTF(i);
            this.dispose();
            new Login();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        pnlTurnOff.setBackground(Color.decode("#f23c3c"));
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        pnlNavHome.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        pnlNavHome.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        pnlTurnOff.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        pnlNavAccounts.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        pnlNavAccounts.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        pnlNavBanking.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        pnlNavBanking.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        pnlNavAuthorize.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        pnlNavAuthorize.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        pnlNavSettings.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        pnlNavSettings.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel16MouseExited

    private void cbSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexActionPerformed

    }//GEN-LAST:event_cbSexActionPerformed

    private void btnCreateAcctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAcctActionPerformed
        try {
            if (!txtFirstName.getText().equals("") && !txtLastName.getText().equals("")
                && !txtEmail.getText().equals("") && !txtDOB.getText().equals("")
                && !cbSex.getSelectedItem().toString().equals("") && !txtAcctSearch.getText().equals("")
                && !txtZip.getText().equals("") && !txtStreet.getText().equals("")
                && !txtCity.getText().equals("") && !txtPIN.getText().equals("")) {
                
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(txtDOB.getText());
                 
                doutMain.writeUTF("0###11?+1234@#$%9**!");
                this.scopeOfAction = "ForReal";
            } else {
                lblBtnCAMessage.setText("FAILED ! Fill All Fields! Check Account Number Availability and 4 Digit PIN!");
                lblBtnCAMessage.setForeground(Color.RED);
            }
        } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
                lblBtnCAMessage.setText("DATE FORMAT:     YYYY-MM-DD");
                lblBtnCAMessage.setForeground(Color.RED);
            }

    }//GEN-LAST:event_btnCreateAcctActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetCreateAccount();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        
        if (txtAcctSearch.getText().isEmpty() || txtAcctSearch.getText().length() < 12) {
                JOptionPane.showMessageDialog(this, "\tInvalid Account Number!\n Please Input a 12 Digit Number ");
            } else {
        try {
            doutMain.writeUTF("0###11?+1234@#$%9**!");
            this.scopeOfAction = "JustCheck";
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetEmployeeActionPerformed
        resetAuthorizeAccount();
    }//GEN-LAST:event_btnResetEmployeeActionPerformed

    private void btnAuthorizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthorizeActionPerformed
        try {
            if (!txtAuthFN.getText().equals("") && !txtAuthLN.getText().equals("")
                && !txtAuthEmail.getText().equals("") && txtUserEmail.getText().contains("@")
                && !txtAuthDOB.getText().equals("") && !cbSexUserAuth.getSelectedItem().toString().equals("") 
                && !txtAuthUsername.getText().equals("") && !txtAuthPassword.getText().equals("") 
                && !cbAuthorizationSet.getSelectedItem().toString().equals("") && photo != null) {

                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(txtAuthDOB.getText());
                this.scopeOfAction = "ForReal";
                doutMain.writeUTF("NN34jso#$%sjifr!!3zz");
            } else {
                jLabel48.setForeground(Color.red);
                jLabel48.setText("AUTHORIZATION FAILED!");
                jLabel49.setForeground(Color.red);
                jLabel49.setText("Please fill all fields and check if the username is available");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
                    jLabel49.setForeground(Color.red);
                    jLabel49.setText("Please use YYYY-MM-DD format for the Date of Birth");
                }
    }//GEN-LAST:event_btnAuthorizeActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        bar(jLabel31);
        window(pnlChat);
        btnSendMessage.setVisible(false);
        btnGoOffline.setForeground(Color.red);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseEntered
        pnlNavChat.setBackground(Color.decode("#565255"));

    }//GEN-LAST:event_jLabel28MouseEntered

    private void jLabel28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseExited
        pnlNavChat.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel28MouseExited

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        bar(jLabel31);
        window(pnlChat);
        btnSendMessage.setVisible(false);
        btnGoOffline.setForeground(Color.red);
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseEntered
        pnlNavChat.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel32MouseEntered

    private void jLabel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseExited
        pnlNavChat.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel32MouseExited

    private void pnlNavChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavChatMouseClicked
        bar(jLabel31);
        window(pnlChat);
        btnSendMessage.setVisible(false);
        btnGoOffline.setForeground(Color.red);

    }//GEN-LAST:event_pnlNavChatMouseClicked

    private void pnlNavChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavChatMouseEntered
        pnlNavChat.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_pnlNavChatMouseEntered

    private void pnlNavChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavChatMouseExited
        pnlNavChat.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_pnlNavChatMouseExited

    private void btnResetSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSettingsActionPerformed
        txtUserFirstName.setText("");
        txtUserLastName.setText("");
        txtUserEmail.setText("");
        txtUserDOB.setText("");
        txtUserUsername.setText("");
        txtUserPassword.setText("");
        jLabel44.setText("");
        cbUserSex.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetSettingsActionPerformed

    private void btnSaveSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSettingsActionPerformed
        try {
            if (!txtUserFirstName.getText().equals("") && !txtUserLastName.getText().equals("")
                && !txtUserEmail.getText().equals("") && txtUserEmail.getText().contains("@") 
                && !txtUserDOB.getText().equals("") && !cbUserSex.getSelectedItem().toString().equals("")
                && !txtUserUsername.getText().equals("") && !txtUserPassword.getText().equals("") 
                && txtUserUsername.getText().length() < 30 && txtUserPassword.getText().length() < 30
                && txtUserFirstName.getText().length() < 30 && txtUserLastName.getText().length() < 30
                    && txtUserEmail.getText().length() < 60) {

                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(txtUserDOB.getText());
                this.scopeOfAction = "ForReal";
                doutMain.writeUTF("Na487&!?M00#$%!!-@#%");
            } else {
                jLabel44.setForeground(Color.red);
                jLabel44.setText("UPDATE FAILED!");
                jLabel45.setForeground(Color.red);
                jLabel45.setText("Please fill all fields and check if the username is available");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
                    jLabel45.setForeground(Color.red);
                    jLabel45.setText("Please use YYYY-MM-DD format for the Date of Birth");
                }

    }//GEN-LAST:event_btnSaveSettingsActionPerformed

    private void btnCheckUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckUsernameActionPerformed
        try {
            doutMain.writeUTF("Na487&!?M00#$%!!-@#%");
            this.scopeOfAction = "JustCheck";
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnCheckUsernameActionPerformed

    private void cbSexUserAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexUserAuthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexUserAuthActionPerformed

    private void cbUserSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUserSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUserSexActionPerformed

    private void txtAuthLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuthLNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuthLNActionPerformed

    private void cbAuthorizationSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAuthorizationSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAuthorizationSetActionPerformed

    private void btnResetPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPINActionPerformed
        txtEnterPIN.setText("");
        txtEnterAcctNum.setText("");
    }//GEN-LAST:event_btnResetPINActionPerformed
    // ovde treba da dodas i account #
    private void btnConfirmPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPINActionPerformed
        if(!txtEnterAcctNum.getText().equals("") && txtEnterAcctNum.getText().length() == 12
            && !txtEnterPIN.getText().equals("") && txtEnterPIN.getText().length() == 4) {
            try {
                this.scopeOfAction = "AccessAll";
                doutMain.writeUTF("#1111##!?19890031@$@");
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Enter a 12 digit Account Number \n And a 4 digit PIN!");
        }
    }//GEN-LAST:event_btnConfirmPINActionPerformed

    private void txtEnterPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterPINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterPINActionPerformed

    private void txtEnterPINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterPINKeyTyped
        boolean max = txtEnterPIN.getText().length() > 3;
        char c = evt.getKeyChar();
        if (max) {
            jLabel50.setForeground(Color.red);
            jLabel50.setText("Max PIN Digits!");
            evt.consume();
        }
        if (!Character.isDigit(c)) {
            jLabel50.setForeground(Color.red);
            jLabel50.setText("PIN can only contain Digits!");
            evt.consume();
        }
        jLabel46.setText("");
        jLabel47.setText("");
    }//GEN-LAST:event_txtEnterPINKeyTyped

    private void txtZipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZipKeyTyped
        boolean max = txtZip.getText().length() > 10;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtZipKeyTyped

    private void txtAcctSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcctSearchKeyTyped
        boolean max = txtAcctSearch.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
        txtAcctSearch.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtAcctSearchKeyTyped

    private void txtCityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyTyped
        boolean max = txtCity.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCityKeyTyped

    private void txtEnterAcctNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterAcctNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterAcctNumActionPerformed

    private void txtEnterAcctNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterAcctNumKeyTyped
        boolean max = txtEnterAcctNum.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max) {
            jLabel50.setForeground(Color.red);
            jLabel50.setText("Max Account Number");
            evt.consume();
        }
        if (!Character.isDigit(c)) {
            jLabel50.setForeground(Color.red);
            jLabel50.setText("Account Number can only contain Digits!");
            evt.consume();
        }
        txtEnterAcctNum.setForeground(Color.BLACK);
        jLabel46.setText("");
        jLabel47.setText("");
    }//GEN-LAST:event_txtEnterAcctNumKeyTyped

    private void btnCheckUsername1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckUsername1ActionPerformed
        try {
            doutMain.writeUTF("NN34jso#$%sjifr!!3zz");
            this.scopeOfAction = "JustCheck";
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCheckUsername1ActionPerformed

    private void lblCoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCoverMouseEntered
        lblCover.setVisible(true);
    }//GEN-LAST:event_lblCoverMouseEntered

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        barClient(jLabel27);
        textAdjust(jLabel30);
        windowClient(pnlServices);
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        barClient(jLabel34);
        textAdjust(jLabel35);
        windowClient(pnlHistory);
        tableTabTextAdjust(lblTblAllTransactions);
        setTableView(tblAll);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        barClient(jLabel36);
        textAdjust(jLabel37);
        windowClient(pnlClientSettings);
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        barClient(jLabel38);
        textAdjust(jLabel39);
        windowClient(pnlAlerts);
        setTableView(tblReq);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void pnlNavServicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavServicesMouseClicked
        barClient(jLabel27);
        textAdjust(jLabel30);
        windowClient(pnlServices);
    }//GEN-LAST:event_pnlNavServicesMouseClicked

    private void pnlNavHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavHistoryMouseClicked
        barClient(jLabel34);
        textAdjust(jLabel35);
        windowClient(pnlHistory);
        tableTabTextAdjust(lblTblAllTransactions);
        setTableView(tblAll);
    }//GEN-LAST:event_pnlNavHistoryMouseClicked

    private void pnlCliNavSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCliNavSettingsMouseClicked
        barClient(jLabel36);
        textAdjust(jLabel37);
        windowClient(pnlClientSettings);
    }//GEN-LAST:event_pnlCliNavSettingsMouseClicked

    private void pnlNavAlertsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAlertsMouseClicked
        barClient(jLabel38);
        textAdjust(jLabel39);
        windowClient(pnlAlerts);
        setTableView(tblReq);
    }//GEN-LAST:event_pnlNavAlertsMouseClicked

    private void txtTransferAccountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTransferAccountKeyTyped
        boolean max = txtTransferAccount.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
        txtTransferAccount.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTransferAccountKeyTyped

    private void txtRequestAccountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRequestAccountKeyTyped
        boolean max = txtRequestAccount.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
        txtRequestAccount.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtRequestAccountKeyTyped

    private void txtDepositKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyTyped
        boolean max = txtDeposit.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDepositKeyTyped

    private void txtWithdrawKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWithdrawKeyTyped
        boolean max = txtWithdraw.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtWithdrawKeyTyped

    private void txtTransferKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTransferKeyTyped
        boolean max = txtTransfer.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTransferKeyTyped

    private void txtRequestKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRequestKeyTyped
        boolean max = txtRequest.getText().length() > 11;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRequestKeyTyped

    private void txtFirstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFirstNameKeyTyped

    private void txtLastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtLastNameKeyTyped

    private void txtAuthLNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAuthLNKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAuthLNKeyTyped

    private void txtUserFirstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserFirstNameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUserFirstNameKeyTyped

    private void txtUserLastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserLastNameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUserLastNameKeyTyped

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        bar(jLabel17);
        window(pnlSettings);
        fillUserFields(this.user);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        pnlNavSettings.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        pnlNavSettings.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel17MouseExited

    private void txtUserUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserUsernameKeyTyped
        txtUserUsername.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtUserUsernameKeyTyped

    private void jLabel25MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseMoved
        jLabel44.setText("");
        jLabel45.setText("");
    }//GEN-LAST:event_jLabel25MouseMoved

    private void txtDOBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOBKeyTyped
        lblBtnCAMessage.setText("");
    }//GEN-LAST:event_txtDOBKeyTyped

    private void lblbackaccountMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbackaccountMouseMoved
        lblBtnCAMessage.setText("");
    }//GEN-LAST:event_lblbackaccountMouseMoved

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        bar(jLabel8);
        window(pnlBankingPIN);
        jLabel46.setText("");
        jLabel47.setText("");
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        pnlNavBanking.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        pnlNavBanking.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        pnlNavAccounts.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        pnlNavAccounts.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        pnlNavHome.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        pnlNavHome.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        bar(jLabel2);
        window(pnlHome);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        bar(jLabel5);
        window(pnlAcct);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        bar(jLabel11);
        window(pnlAuthorize);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        pnlNavAuthorize.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        pnlNavAuthorize.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        bar(jLabel31);
        window(pnlChat);
        btnSendMessage.setVisible(false);
        btnGoOffline.setForeground(Color.red);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseEntered
        pnlNavChat.setBackground(Color.decode("#565255"));
    }//GEN-LAST:event_jLabel31MouseEntered

    private void jLabel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseExited
        pnlNavChat.setBackground(Color.decode("#2a2729"));
    }//GEN-LAST:event_jLabel31MouseExited

    private void jLabel23MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseMoved
        jLabel48.setText("");
        jLabel49.setText("");
    }//GEN-LAST:event_jLabel23MouseMoved

    private void txtAuthUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAuthUsernameKeyTyped
        txtAuthUsername.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtAuthUsernameKeyTyped

    private void jLabel33MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseMoved
        jLabel50.setText("");
    }//GEN-LAST:event_jLabel33MouseMoved

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        if (txtDeposit.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Input Deposit Amount!");
            } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "Deposit";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnDepositActionPerformed

    private void btnWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawActionPerformed
        if (txtWithdraw.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Input Withdrawal Amount!");
            
        } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "Withdrawal";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnWithdrawActionPerformed

    private void btnCheckTransferAcctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckTransferAcctActionPerformed
        if (txtTransferAccount.getText().equals("") || txtTransferAccount.getText().length() < 12) {
            JOptionPane.showMessageDialog(this, "\tInvalid Account Number!\n Please Input a 12 Digit Number ");
        } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "TransferCheck";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCheckTransferAcctActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        if (txtTransferAccount.getText().equals("") || txtTransferAccount.getText().length() < 12 || txtTransfer.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "\tPlease put in the 12 digit Account Number\n And the Amount");
            return;
        } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "Transfer";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnCheckReqAcctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckReqAcctActionPerformed
        if (txtRequestAccount.getText().equals("") || txtRequestAccount.getText().length() < 12) {
            JOptionPane.showMessageDialog(this, "\tInvalid Account Number!\n Please Input a 12 Digit Number ");
        } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "RequestCheck";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCheckReqAcctActionPerformed

    private void btnRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestActionPerformed
        if (txtRequestAccount.getText().equals("") || txtRequestAccount.getText().length() < 12 || txtRequest.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "\tPlease put in the 12 digit Account Number\n And the Amount");
            return;
        } else {
            try {
                doutMain.writeUTF("04%23!@4hnx??#$%&ss*");
                this.scopeOfAction = "Request";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRequestActionPerformed

    private void btnResetDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDepositActionPerformed
        txtDeposit.setText("");
    }//GEN-LAST:event_btnResetDepositActionPerformed

    private void btnResetWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetWithdrawActionPerformed
        txtWithdraw.setText("");
    }//GEN-LAST:event_btnResetWithdrawActionPerformed

    private void btnResetTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransferActionPerformed
        txtTransfer.setText("");
        txtTransferAccount.setText("");
    }//GEN-LAST:event_btnResetTransferActionPerformed

    private void btnResetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetRequestActionPerformed
        txtRequest.setText("");
        txtRequestAccount.setText("");
    }//GEN-LAST:event_btnResetRequestActionPerformed

    private void btnResetCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCSActionPerformed
        txtFirstName1.setText("");
        txtLastName1.setText("");
        txtEmail1.setText("");
        txtDOB1.setText("");
        txtStreet1.setText("");
        txtCity1.setText("");
        txtPostalCode1.setText("");
        txtPIN2.setText("");
        cbSex1.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetCSActionPerformed

    private void btnUpdateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClientActionPerformed
        if (!txtFirstName1.getText().equals("") && !txtLastName1.getText().equals("")
            && !txtEmail1.getText().equals("") && !txtDOB1.getText().equals("")
            &&!txtStreet1.getText().equals("") &&!txtCity1.getText().equals("")
            && !txtPostalCode1.getText().equals("") && !cbSex1.getSelectedItem().toString().equals("")
            && txtFirstName1.getText().length() < 30 && txtLastName1.getText().length() < 30
            && txtEmail1.getText().length() < 60 && txtCity1.getText().length() < 30
            && txtStreet1.getText().length() < 100 && txtPostalCode1.getText().length() < 10) {
        try {
            String DOB = txtDOB1.getText();
            Date dob = new SimpleDateFormat("YYYY-MM-dd").parse(DOB);
            
            doutMain.writeUTF("hfk2@$%%@?!?!?#$%ss3");
            
        }   catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    }//GEN-LAST:event_btnUpdateClientActionPerformed

    private void txtPostalCode1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalCode1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPostalCode1KeyTyped

    private void txtFirstName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstName1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstName1KeyTyped

    private void txtLastName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastName1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastName1KeyTyped

    private void txtDOB1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOB1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOB1KeyTyped

    private void txtStreet1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStreet1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreet1KeyTyped

    private void txtCity1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCity1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCity1KeyTyped

    private void cbSex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSex1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSex1ActionPerformed

    private void txtPIN2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPIN2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPIN2KeyTyped

    private void txtStreetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStreetKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreetKeyTyped

    private void txtPINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPINKeyTyped
        boolean max = txtPIN.getText().length() > 3;
        char c = evt.getKeyChar();
        if (max || !Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPINKeyTyped

    private void lblTblAllTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTblAllTransactionsMouseClicked
        tableTabTextAdjust(lblTblAllTransactions);
        setTableView(tblAll);
    }//GEN-LAST:event_lblTblAllTransactionsMouseClicked

    private void lblTblDepositsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTblDepositsMouseClicked
        tableTabTextAdjust(lblTblDeposits);
        setTableView(tblDep);
    }//GEN-LAST:event_lblTblDepositsMouseClicked

    private void lblTblWithdrawalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTblWithdrawalsMouseClicked
        tableTabTextAdjust(lblTblWithdrawals);
        setTableView(tblWith);
    }//GEN-LAST:event_lblTblWithdrawalsMouseClicked

    private void lblTblTransfersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTblTransfersMouseClicked
        tableTabTextAdjust(lblTblTransfers);
        setTableView(tblTran);
    }//GEN-LAST:event_lblTblTransfersMouseClicked

    private void tblAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllMouseClicked
        // TODO add your handling code here:

        DefaultTableModel dtm = (DefaultTableModel) tblAll.getModel();

        String date = dtm.getValueAt(tblAll.getSelectedRow(), 0).toString();
        String type = dtm.getValueAt(tblAll.getSelectedRow(), 1).toString();
        String from = dtm.getValueAt(tblAll.getSelectedRow(), 2).toString();
        String to = dtm.getValueAt(tblAll.getSelectedRow(), 3).toString();
        String amount = dtm.getValueAt(tblAll.getSelectedRow(), 4).toString();
        String status = dtm.getValueAt(tblAll.getSelectedRow(), 5).toString();
    }//GEN-LAST:event_tblAllMouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        barClient(jLabel34);
        textAdjust(jLabel35);
        windowClient(pnlHistory);
        tableTabTextAdjust(lblTblAllTransactions);
        setTableView(tblAll);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        barClient(jLabel27);
        textAdjust(jLabel30);
        windowClient(pnlServices);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void lblAlertNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlertNumberMouseClicked
        barClient(jLabel38);
        textAdjust(jLabel39);
        windowClient(pnlAlerts);
        showRequests();
    }//GEN-LAST:event_lblAlertNumberMouseClicked

    private void txtUserFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserFirstNameActionPerformed

    private void lblCoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCoverMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCoverMouseClicked

    private void btnGoOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoOnlineActionPerformed

        try {
            socket = new Socket("Localhost", 2089);

            dinChat = new DataInputStream(socket.getInputStream());
            doutChat = new DataOutputStream(socket.getOutputStream());

            doutChat.writeUTF(fullName);

            String i = dinChat.readUTF();
            if (i.equals("You Are Alredy Registered....!!")) {
                JOptionPane.showMessageDialog(this, "Your chat is already open");
            } else {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Read().start();
                        btnGoOffline.setForeground(Color.white);
                        btnGoOnline.setForeground(Color.green);
                        btnSendMessage.setVisible(true);
                        lblCover.setVisible(true);
                    }
                });

            }
        } catch (IOException ex ) {
            System.out.println("IO Exception");
        } catch (Exception e) {
            System.out.println("Exception");
        }

    }//GEN-LAST:event_btnGoOnlineActionPerformed

    private void btnGoOfflineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoOfflineActionPerformed
        String i = "mkoihgteazdcvgyhujb096785542AXTY";
        try {
            doutChat.writeUTF(i);
            dlm.clear();
            btnGoOffline.setForeground(Color.red);
            btnGoOnline.setForeground(Color.white);
            btnSendMessage.setVisible(false);
            lblCover.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGoOfflineActionPerformed

    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        try {
            String m = txtUserSendMsg.getText(), mm = m;
            String CID = clientIDFromList;

            if (!clientIDFromList.isEmpty()) {
                // : se stavalja zbog podele tokena
                m = "#4344554@@@@@67667@@" + CID + ":" + mm;
                doutChat.writeUTF(m);
                txtUserSendMsg.setText("");
                txtMsgArea.append("To " + CID + ": " + mm + "\n");
            } else {
                doutChat.writeUTF(m);
                txtUserSendMsg.setText("");
                txtMsgArea.append("To ALL : " + mm + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "User does not exist anymore");
        }
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void OnlineUsersListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_OnlineUsersListValueChanged
        clientIDFromList = (String) OnlineUsersList.getSelectedValue();
    }//GEN-LAST:event_OnlineUsersListValueChanged

    private void btnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectAllActionPerformed
        OnlineUsersList.clearSelection();
        clientIDFromList = "";
    }//GEN-LAST:event_btnSelectAllActionPerformed

    private void txtRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRequestActionPerformed

    private void tblReqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReqMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblReqMouseClicked

    private void pnlNavAlertsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNavAlertsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlNavAlertsMouseEntered

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        barClient(jLabel38);
        textAdjust(jLabel39);
        windowClient(pnlAlerts);
        setTableView(tblReq);
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        barClient(jLabel38);
        textAdjust(jLabel39);
        windowClient(pnlAlerts);
        setTableView(tblReq);
    }//GEN-LAST:event_jLabel51MouseClicked

    private void btnRejectRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectRequestActionPerformed
        try {
                doutMain.writeUTF("&&5vh34m*&%?nd2!!??x");
                this.scopeOfAction = "Deny";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnRejectRequestActionPerformed

    private void btnApproveRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveRequestActionPerformed
        try {
                doutMain.writeUTF("&&5vh34m*&%?nd2!!??x");
                this.scopeOfAction = "Approve";
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnApproveRequestActionPerformed

    private void btnUpdateImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateImageActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            this.photo = Files.readAllBytes(f.toPath());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photo).getImage()
                .getScaledInstance(lblUpdateImage.getWidth(), lblUpdateImage.getHeight(), Image.SCALE_SMOOTH));
            lblUpdateImage.setIcon(imageIcon);
            lblUpdateImage.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnUpdateImageActionPerformed

    private void pnlBankingPINMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBankingPINMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlBankingPINMouseMoved

    private void txtEnterPINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterPINKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!txtEnterAcctNum.getText().equals("") && txtEnterAcctNum.getText().length() == 12
                && !txtEnterPIN.getText().equals("") && txtEnterPIN.getText().length() == 4) {
                try {
                    this.scopeOfAction = "AccessAll";
                    doutMain.writeUTF("#1111##!?19890031@$@");
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Enter a 12 digit Account Number \n And a 4 digit PIN!");
            }
        }
    }//GEN-LAST:event_txtEnterPINKeyPressed

    private void txtAcctSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAcctSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcctSearchActionPerformed

    private void lblCover1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCover1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCover1MouseClicked

    private void lblCover1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCover1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCover1MouseEntered

    private void tblDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDepMouseClicked

    private void tblWithMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWithMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblWithMouseClicked

    private void tblTranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTranMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        lblCover.setVisible(false);
        bar(jLabel8);
        window(pnlBankingPIN);
        txtEnterPIN.setText("");
        txtEnterAcctNum.setText("");
        lblAlertNumber.setText("");
        this.client=null;
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnAuthImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthImageActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            this.photo = Files.readAllBytes(f.toPath());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photo).getImage()
                .getScaledInstance(lblAuthImage.getWidth(), lblAuthImage.getHeight(), Image.SCALE_SMOOTH));
            lblAuthImage.setIcon(imageIcon);
            lblAuthImage.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAuthImageActionPerformed

    public boolean checkExistence(String scopeOfCheck, JTextField textField) {
            try {                
                doutMain.writeUTF(textField.getText());
                doutMain.writeUTF(scopeOfCheck);
                String response = dinMain.readUTF();
                if (response.equalsIgnoreCase("nonexistent") && (!scopeOfCheck.equals("TransferCheck") && !scopeOfCheck.equals("RequestCheck"))) {
                    textField.setForeground(Color.GREEN);
                    JOptionPane.showMessageDialog(this, "Account Number is Available");
                    return true;
                } else if(response.equalsIgnoreCase("continue") && (!scopeOfCheck.equals("TransferCheck") && !scopeOfCheck.equals("RequestCheck"))) {
                    return true;
                } else if(response.equals("exists") && !scopeOfCheck.equals("TransferCheck") && !scopeOfCheck.equals("RequestCheck")) {
                    JOptionPane.showMessageDialog(this, "Account Exists! \n Please choose a different Account Number");
                    textField.setText("");
                    return false;
                } else if (response.equals("exists") && (scopeOfCheck.equals("TransferCheck") || scopeOfCheck.equals("RequestCheck"))) {
                    textField.setForeground(Color.GREEN);
                    textField.setText(textField.getText());
                    return true;
                } else if (response.equals("noAccount") && (scopeOfCheck.equals("TransferCheck") || scopeOfCheck.equals("RequestCheck"))) {
                    textField.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(this, "Account Doesn't Exist!");
                    textField.setText("");
                    return false;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    
    public boolean checkUsername(String scopeOfCheck, JTextField textbox) {
        if (textbox.getText().isEmpty() || textbox.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "\tInvalid Username!\n Username must have be between 1 and 30 Characters long ");
            return false;
        } else {
            try {
                doutMain.writeUTF(textbox.getText());
                doutMain.writeUTF(scopeOfCheck);
                String response = dinMain.readUTF();
                if (response.equalsIgnoreCase("nonexistent")) {
                    textbox.setForeground(Color.GREEN);
                    JOptionPane.showMessageDialog(this, "Username is Available");
                    return true;
                } else if(response.equalsIgnoreCase("continue")) {
                    return true;
                } else if(response.equals("exists")){
                    JOptionPane.showMessageDialog(this, "Username Exists! \n Please choose a different Username");
                    textbox.setText("");
                    return false;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
        return false;
    }
    
    public void createEmployeeProfile(JPanel panel) {
        try {
            sendUser(panel);
            String response = dinMain.readUTF();
            if(panel == pnlAuthorize) {
                if (response.equals("Success")) {
                    jLabel48.setForeground(Color.GREEN);
                    jLabel48.setText("SUCCESSFUL AUTHORIZATION!");
                    resetAuthorizeAccount();
                } else if (response.equals("Fail")) {
                    jLabel48.setForeground(Color.red);
                    jLabel48.setText("AUTHORIZATION FAILED!");
                    jLabel49.setForeground(Color.red);
                    jLabel49.setText("Something Went Terribly Wrong");
                }
            } else if (panel == pnlSettings) {
                if (response.equals("Success")) {
                    doutMain.writeUTF("394ns#$%^&&&!?shu3x?");
                    jLabel44.setForeground(Color.GREEN);
                    jLabel44.setText("SUCCESSFUL UPDATE!");
                } else if (response.equals("Fail")) {
                    jLabel44.setForeground(Color.red);
                    jLabel44.setText("UPDATE FAILED!");
                    jLabel45.setForeground(Color.red);
                    jLabel45.setText("Something Went Terribly Wrong");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createClientProfile(JPanel panel) {
        
        if(panel == pnlAcct) {       
            try {
                sendClient("create");
                String response = dinMain.readUTF();
                if (response.equals("Client-Added")) {
                    resetCreateAccount();
                    lblBtnCAMessage.setText("ACCOUNT WAS SUCCESSFULY CREATED!!!");
                    lblBtnCAMessage.setForeground(Color.GREEN);
                } else if(response.equals("Failed-To-Add-Client")) {
                    lblBtnCAMessage.setText("Oops, Something went Terribly Wrong :(");
                    lblBtnCAMessage.setForeground(Color.RED);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else if (panel == pnlClientSettings) {
            try {
                sendClient("update");
                String response = dinMain.readUTF();
                if (response.equals("Client-Updated")) {
                    lblClientName.setText(txtFirstName1.getText()+" "+txtLastName1.getText().charAt(0)+".");
                   JOptionPane.showMessageDialog(this, "UPDATE SUCCESSFUL!"); 
                } else if(response.equals("Failed-To-Update-Client")) {
                   JOptionPane.showMessageDialog(this, "UPDATE FAILED :( !");  
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    private void sendClient(String action) {
        if(action.equals("create")) {
            try {
                doutMain.writeInt(0);
                doutMain.writeUTF(txtFirstName.getText());
                doutMain.writeUTF(txtLastName.getText());
                doutMain.writeUTF(txtEmail.getText());
                doutMain.writeUTF(txtDOB.getText());
                doutMain.writeUTF(txtStreet.getText());
                doutMain.writeUTF(txtCity.getText());
                doutMain.writeUTF(txtZip.getText());
                doutMain.writeUTF(cbSex.getSelectedItem().toString());
                doutMain.writeUTF(txtAcctSearch.getText());
                doutMain.writeUTF(txtPIN.getText());
                doutMain.writeInt(user.getId());
                doutMain.writeLong(0);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("update")) {
            try {
                doutMain.writeInt(client.getId());
                doutMain.writeUTF(txtFirstName1.getText());
                doutMain.writeUTF(txtLastName1.getText());
                doutMain.writeUTF(txtEmail1.getText());
                doutMain.writeUTF(txtDOB1.getText());
                doutMain.writeUTF(txtStreet1.getText());
                doutMain.writeUTF(txtCity1.getText());
                doutMain.writeUTF(txtPostalCode1.getText());
                doutMain.writeUTF(cbSex1.getSelectedItem().toString());
                doutMain.writeUTF(client.getAccount().getAccountNumber());
                doutMain.writeUTF(txtPIN2.getText());
                doutMain.writeInt(user.getId());
                doutMain.writeLong(client.getAccount().getBalance());
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void sendUser(JPanel panel) {
        if(panel == pnlAuthorize) {
            try {
                doutMain.writeInt(0);
                doutMain.writeUTF(txtAuthUsername.getText());
                doutMain.writeUTF(txtAuthPassword.getText());
                doutMain.writeUTF(txtAuthFN.getText());
                doutMain.writeUTF(txtAuthLN.getText());
                doutMain.writeUTF(txtAuthEmail.getText());
                doutMain.writeUTF(txtAuthDOB.getText());
                doutMain.writeUTF(cbSexUserAuth.getSelectedItem().toString());
                doutMain.writeUTF(cbAuthorizationSet.getSelectedItem().toString());
                doutMain.writeInt(photo.length);
                doutMain.write(photo);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(panel == pnlSettings) {
            try {                
                doutMain.writeInt(user.getId());
                doutMain.writeUTF(txtUserUsername.getText());
                doutMain.writeUTF(txtUserPassword.getText());
                doutMain.writeUTF(txtUserFirstName.getText());
                doutMain.writeUTF(txtUserLastName.getText());
                doutMain.writeUTF(txtUserEmail.getText());
                doutMain.writeUTF(txtUserDOB.getText());
                doutMain.writeUTF(cbUserSex.getSelectedItem().toString());
                doutMain.writeUTF(user.getAuthorization());
                if(photo != null) {
                    doutMain.writeInt(photo.length);
                    doutMain.write(photo);
                } else {
                    doutMain.writeInt(0);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void confirmAccount(String scope) {
        try {
            sendClientBAInfo(scope);
            String status = dinMain.readUTF();
            if (status.equalsIgnoreCase("success")) {
                this.client = receiveClient();
                fillClientSettings(client);
                bar(jLabel8);
                window(pnlBanking);
                windowClient(pnlServices);
                barClient(jLabel27);
                textAdjust(jLabel30);
                lblCover.setVisible(true);
                getAllTransactions();
                if(transactions != null) {
                    getRequestsToTransactions();
                }
            } else {
                txtEnterPIN.setText("");
                txtEnterAcctNum.setText("");
                jLabel46.setForeground(Color.red);
                jLabel46.setText("UNSUCCESSFUL!!!");
                jLabel47.setForeground(Color.red);
                jLabel47.setText("Wrong Account Number or PIN");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendClientBAInfo(String scope) throws IOException {
        if(scope.equals("AccessAll")) {
            doutMain.writeUTF(txtEnterAcctNum.getText());
            doutMain.writeUTF(txtEnterPIN.getText());
            doutMain.writeUTF(scope);
        } else if (scope.equals("AccessUpdate")) {
            doutMain.writeUTF(client.getAccount().getAccountNumber());
            doutMain.writeUTF(client.getAccount().getPin());
            doutMain.writeUTF(scope);
        }
    }

    public Client receiveClient() throws IOException {
        try {
            Account account = receiveAccount();
            int id = dinMain.readInt();
            String firstName = dinMain.readUTF();
            String lastName = dinMain.readUTF();
            String email = dinMain.readUTF();
            String dob = dinMain.readUTF();
            String street = dinMain.readUTF();
            String city = dinMain.readUTF();
            String zip = dinMain.readUTF();
            String sex = dinMain.readUTF();
            int length = dinMain.readInt();
            byte[] photo = null;
            if (length > 0) {
                byte[] message = new byte[length];
                dinMain.readFully(message, 0, message.length); // read the message
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                Client client = new Client(id, firstName, lastName, email, DOB, street, city, zip, sex, message, account);
                return client;
            } else {
                String bb = dinMain.readUTF();
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                Client client = new Client(id, firstName, lastName, email, DOB, street, city, zip, sex, photo, account);
                return client;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account receiveAccount() {
        try {
            int id = dinMain.readInt();
            String accountNumber = dinMain.readUTF();
            String pin = dinMain.readUTF();
            int empId = dinMain.readInt();
            long balance = dinMain.readLong();
            String doc = dinMain.readUTF();
            Date DOC = new SimpleDateFormat("yyyy-MM-dd").parse(doc);
            Account account = new Account(id, accountNumber, pin, empId, balance, DOC);
            return account;
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void performTransaction(String scopeOfAction) {
        switch (scopeOfAction) {
            case "Deposit":
                Deposit(scopeOfAction);
                break;
            case "Withdrawal":
                Withdrawal(scopeOfAction);
                break;
            case "Transfer":
                Transfer(scopeOfAction);
                break;
            case "TransferCheck":
                try {
                    doutMain.writeUTF(scopeOfAction);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                checkExistence(scopeOfAction, txtTransferAccount);
                break;
            case "RequestCheck":
                try {
                    doutMain.writeUTF(scopeOfAction);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                checkExistence(scopeOfAction, txtRequestAccount);
                break;
            case "Request":
                Request(scopeOfAction);
                break;
        }
    }

    public void Deposit(String scopeOfAction) {
        try {
            String accountNumber = lblClientAcctNum.getText();
            String depositAmount = txtDeposit.getText();
            doutMain.writeUTF(scopeOfAction);
            doutMain.writeUTF(accountNumber);
            doutMain.writeUTF(depositAmount);
            doutMain.writeInt(user.getId());
            Client c = receiveClient();
            if (c != null) {
                this.client = c;
                long balance = client.getAccount().getBalance();
                String blns = MessageFormat.format("{0}", balance);
                lblCurrentBalance.setText("$"+blns);
                txtDeposit.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Something Went Terribly Wrong");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Withdrawal (String scopeOfAction) {
        try {
            String accountNumber = lblClientAcctNum.getText();
            String withdrawAmount = txtWithdraw.getText();
            doutMain.writeUTF(scopeOfAction);
            doutMain.writeUTF(accountNumber);
            doutMain.writeUTF(withdrawAmount);
            doutMain.writeInt(user.getId());
            Client c = receiveClient();
            if (c != null) {
                this.client = c;
                long balance = client.getAccount().getBalance();
                String blns = MessageFormat.format("{0}", balance);
                lblCurrentBalance.setText("$"+blns);
                txtWithdraw.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "INSUFFICIENT FUNDS!");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Transfer (String scopeOfAction) {
        try {
            String toAccountNumber = txtTransferAccount.getText();
            doutMain.writeUTF("Transfer");
            doutMain.writeUTF(toAccountNumber);
            doutMain.writeUTF("TransferCheck");
            String exists = dinMain.readUTF();
            if(exists.equals("exists")) {
                doutMain.writeUTF("go");
                doutMain.writeUTF(toAccountNumber);
                doutMain.writeUTF(client.getAccount().getAccountNumber());
                doutMain.writeUTF(txtTransfer.getText());
                doutMain.writeInt(user.getId());
                Client c = receiveClient();
                if(c != null) {
                    this.client = c;
                    long balance = client.getAccount().getBalance();
                    String blns = MessageFormat.format("{0}", balance);
                    lblCurrentBalance.setText("$"+blns);
                    txtWithdraw.setText("");
                    txtTransfer.setText("");
                    txtTransferAccount.setText("");
                } else  {
                    JOptionPane.showMessageDialog(this, "INSUFFICIENT FUNDS!");
                }
            } else if (exists.equals("noAccount")){
                doutMain.writeUTF("stop");
                String rip = dinMain.readUTF();
                JOptionPane.showMessageDialog(this, "Nonexistent Transfer Account!");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Request (String scopeOfAction) {
        try {
            String toAccountNumber = txtRequestAccount.getText();
            doutMain.writeUTF("Request");
            doutMain.writeUTF(toAccountNumber);
            doutMain.writeUTF("RequestCheck");
            String exists = dinMain.readUTF();
            if(exists.equals("exists")) {
                doutMain.writeUTF("go");
                doutMain.writeUTF(toAccountNumber);
                doutMain.writeUTF(client.getAccount().getAccountNumber());
                doutMain.writeUTF(txtRequest.getText());
                doutMain.writeInt(user.getId());
                doutMain.writeInt(client.getId());
                String response = dinMain.readUTF();
                if(response.equals("requested")) {
                    txtRequest.setText("");
                    txtRequestAccount.setText("");
                    JOptionPane.showMessageDialog(this, "REQUEST SENT!");
                } else  {
                    JOptionPane.showMessageDialog(this, "REQUEST NOT SENT! NOT ENOUGH FUNDS");
                }
            } else if (exists.equals("noAccount")){
                doutMain.writeUTF("stop");
                String rip = dinMain.readUTF();
                JOptionPane.showMessageDialog(this, "Nonexistent Request Account!");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class Read extends Thread {

        @Override
        public void run() {
            //citanje i punjenje UL 

            while (true) {
                try {
                    String m = dinChat.readUTF();
                    //odsecanje teksta koji je sluzio za razvrstavanje
                    if (m.contains(":;.,/=")) {
                        m = m.substring(6);
                        dlm.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()) {
                            String u = st.nextToken();
                            if (!fullName.equals(u)) {
                                dlm.addElement(u);
                            }
                        }
                    } else if (m.contains(": Left The Chat!")) {
                    } else if (m.contains("is Online!")) {
                    } else {
                        txtMsgArea.append("" + m + "\n");
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }

    class HandleActions extends Thread {

        @Override
        public void run() {
            //Communication with the MainServer

            while (true) {
                try {
                    String m = dinMain.readUTF();
                    switch (m) {                        
                        case "#1111##!?19890031@$@": // Access Client Banking Account
                            confirmAccount(scopeOfAction);
                            break;
                        case "0###11?+1234@#$%9**!": // Create Client's Bank Profile and Account
                            available = checkExistence(scopeOfAction, txtAcctSearch);
                            if(available && scopeOfAction.equals("ForReal")) {
                                doutMain.writeUTF("go");
                                createClientProfile(pnlAcct);
                            } 
                            doutMain.writeUTF("no");
                            break;
                        case "hfk2@$%%@?!?!?#$%ss3": // Update Client's Bank Profile and Pin
                                createClientProfile(pnlClientSettings);
                            break;
                        case "Na487&!?M00#$%!!-@#%": // Update Employee Info
                            available = checkUsername(scopeOfAction, txtUserUsername);
                            if(available && scopeOfAction.equals("ForReal")) {
                                doutMain.writeUTF("go");
                                createEmployeeProfile(pnlSettings);
                            } 
                            doutMain.writeUTF("no");
                            break;
                        case "NN34jso#$%sjifr!!3zz": // Authorize an Employee
                            available = checkUsername(scopeOfAction, txtAuthUsername);
                            if(available && scopeOfAction.equals("ForReal")) {
                                doutMain.writeUTF("go");
                                createEmployeeProfile(pnlAuthorize);
                            } 
                            doutMain.writeUTF("no");
                            break;
                        case "04%23!@4hnx??#$%&ss*": // Perform Monetary Transaction 
                            performTransaction(scopeOfAction);  
                            break;
                        case "2gs%%f98!@!@$!@fhuhi": // Get User's Workplace Data
                            showPerformance(user.getId())  ;
                            break;
                        case "#%$oal!04m2js$%&??sn": // Load all transactions for a Client 
                            loadTransactions();
                            fillTables();
                            break;
                            
                        case "&&5vh34m*&%?nd2!!??x": // Denying a Request 
                            handleRequest(scopeOfAction);
                            break;
                        case "394ns#$%^&&&!?shu3x?": // Updated User
                            retrieveUserUpdate();
                            break;
                        case "UpdateTransactions": // Thread to Update the Client Window
                            scopeOfAction = "AccessUpdate";
                            doutMain.writeUTF("#1111##!?19890031@$@");
                            break;
                    } 
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }        
    }
    
    public void retrieveUserUpdate() {
        try {
            doutMain.writeInt(user.getId());
            User userReceived = receiveUser();
            setProfile(userReceived);
            fillUserFields(userReceived);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleRequest(String scopeOfAction) {
        try {
            if(scopeOfAction.equals("Deny")) {
                doutMain.writeUTF(scopeOfAction);
                doutMain.writeInt(Integer.parseInt(txtTransId.getText()));
                doutMain.writeInt(user.getId());
                String response = dinMain.readUTF();
                if (response.equalsIgnoreCase("Updated")) {
                    
                } else if (response.equalsIgnoreCase("NotUpdated")) {
                    
                }
            } else if (scopeOfAction.equals("Approve")) {
                doutMain.writeUTF(scopeOfAction);
                doutMain.writeInt(Integer.parseInt(txtTransId.getText()));
                doutMain.writeInt(user.getId());
                String response = dinMain.readUTF();
                if (response.equalsIgnoreCase("Updated")) {

                } else if (response.equalsIgnoreCase("NotUpdated")) {
                    
                }
            }
        }catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    
    public User receiveUser(){
        try {
            int id = dinMain.readInt();
            String username = dinMain.readUTF();
            String password = dinMain.readUTF();
            String firstName = dinMain.readUTF();
            String lastName = dinMain.readUTF();
            String email = dinMain.readUTF();
            String dob = dinMain.readUTF();
            String sex = dinMain.readUTF();
            String authorization = dinMain.readUTF();
            byte[] photo1 = null;
            int length = dinMain.readInt();
            if(length>0) {
                byte[] message = new byte[length];
                dinMain.readFully(message, 0, message.length); // read the message
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                User userReceived = new User(id,username,password,firstName,lastName,email,DOB,sex,authorization,message);
                return userReceived;
            } else {
                String bb = dinMain.readUTF();
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                User userReceived = new User(id,username,password,firstName,lastName,email,DOB,sex,authorization,photo1);
                return userReceived;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void loadTransactions() {
        try {
            doutMain.writeUTF(client.getAccount().getAccountNumber());
            this.transactions = receiveTransactions();       
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Transaction> receiveTransactions(){
        ArrayList<Transaction> array = null;
        try {
            int length = dinMain.readInt();
            array = readTransaction(length);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<Transaction> readTransaction(int length) {
        Transaction transaction = null;
        ArrayList<Transaction> array = new ArrayList<>();
        try {
            for (int i = 0; i < length; i++) {
                int empId = dinMain.readInt();
                int clientId = dinMain.readInt();
                String transType = dinMain.readUTF();
                String fromAcct = dinMain.readUTF();
                String toAcct = dinMain.readUTF();
                int amount = dinMain.readInt();
                String DOT = dinMain.readUTF();
                Date dot = new SimpleDateFormat("yyyy-MM-dd").parse(DOT);
                String status = dinMain.readUTF();
                int id = dinMain.readInt();
                transaction = new Transaction(empId, clientId, transType, fromAcct, toAcct, amount, dot, status, id);
                array.add(transaction);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    } 

    public void bar(JLabel lab) {
        jLabel2.setOpaque(false);
        jLabel5.setOpaque(false);
        jLabel8.setOpaque(false);
        jLabel11.setOpaque(false);
        jLabel17.setOpaque(false);
        jLabel31.setOpaque(false);

        lab.setOpaque(true);
        pnlNavigation.repaint();
    }

    public void barClient(JLabel lab) {
        jLabel27.setOpaque(false);
        jLabel34.setOpaque(false);
        jLabel36.setOpaque(false);
        jLabel38.setOpaque(false);
        lab.setOpaque(true);
        pnlClientNav.repaint();
    }

    public void window(JPanel panel) {
        pnlHome.setVisible(false);
        pnlAcct.setVisible(false);
        pnlAuthorize.setVisible(false);
        pnlBankingPIN.setVisible(false);
        pnlBanking.setVisible(false);
        pnlSettings.setVisible(false);
        pnlChat.setVisible(false);

        panel.setVisible(true);
    }

    public void windowClient(JPanel panel) {
        pnlServices.setVisible(false);
        pnlHistory.setVisible(false);
        pnlClientSettings.setVisible(false);
        pnlAlerts.setVisible(false);

        panel.setVisible(true);
    }

    public void textAdjust(JLabel lab) {
        jLabel30.setFont(new Font("Roboto", Font.PLAIN, 24));
        jLabel30.setForeground(new Color(84, 84, 84));
        jLabel35.setFont(new Font("Roboto", Font.PLAIN, 24));
        jLabel35.setForeground(new Color(84, 84, 84));
        jLabel37.setFont(new Font("Roboto", Font.PLAIN, 24));
        jLabel37.setForeground(new Color(84, 84, 84));
        jLabel39.setFont(new Font("Roboto", Font.PLAIN, 24));
        jLabel39.setForeground(new Color(84, 84, 84));

        lab.setFont(new Font("Roboto", Font.PLAIN, 33));
        lab.setForeground(Color.WHITE);
    }

    public void tableTabTextAdjust(JLabel lab) {
        lblTblAllTransactions.setFont(new Font("Roboto", Font.PLAIN, 14));
        lblTblAllTransactions.setForeground(Color.BLACK);
        lblTblDeposits.setFont(new Font("Roboto", Font.PLAIN, 14));
        lblTblDeposits.setForeground(Color.BLACK);
        lblTblWithdrawals.setFont(new Font("Roboto", Font.PLAIN, 14));
        lblTblWithdrawals.setForeground(Color.BLACK);
        lblTblTransfers.setFont(new Font("Roboto", Font.PLAIN, 14));
        lblTblTransfers.setForeground(Color.BLACK);

        lab.setFont(new Font("Roboto", Font.PLAIN, 20));
        lab.setForeground(Color.WHITE);
    }
    
    public void fillUserFields(User user) {
        char sex = user.getSex().charAt(0);
        txtUserFirstName.setText(user.getFirstName());
        txtUserLastName.setText(user.getLastName());
        txtUserEmail.setText(user.getEmail());
        txtUserDOB.setText(user.getDob());
        txtUserUsername.setText(user.getUsername());
        txtUserPassword.setText(user.getPassword());
        jLabel20.setText(user.getAuthorization());
        if (sex == 'M') {
            cbUserSex.setSelectedIndex(1);
        } else {
            cbUserSex.setSelectedIndex(2);
        }
        if(user.getPhoto() != null) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(user.getPhoto()).getImage()
                .getScaledInstance(lblUpdateImage.getWidth(), lblUpdateImage.getHeight(), Image.SCALE_SMOOTH));
        lblUpdateImage.setIcon(imageIcon);
        }
        jLabel44.setText("");

    }

    public void resetCreateAccount() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtDOB.setText("");
        txtAcctSearch.setText("");
        txtCity.setText("");
        txtZip.setText("");
        cbSex.setSelectedIndex(0);
        txtStreet.setText("");
        txtCity.setText("");
        txtPIN.setText("");
    }

    private void fillClientSettings(Client client) {
        txtFirstName1.setText(client.getFirstName());
        txtLastName1.setText(client.getLastName());
        txtEmail1.setText(client.getEmail());
        txtDOB1.setText(client.getDob());
        txtStreet1.setText(client.getStreet());
        txtCity1.setText(client.getCity());
        txtPostalCode1.setText(client.getZip());
        txtPIN2.setText(client.getAccount().getPin());
        char sex = client.getSex().charAt(0);
        if (sex == 'M') {
            cbSex1.setSelectedIndex(1);
            lblFemaleClient.setVisible(false);
        } else {
            cbSex1.setSelectedIndex(2);
            lblMaleClient.setVisible(false);
        }
        lblClientName.setText(client.getFirstName() + " " + client.getLastName().charAt(0) + ".");
        lblClientAcctNum.setText(client.getAccount().getAccountNumber());
        lblClientId.setText(Integer.toString(client.getId()));
        long balance = client.getAccount().getBalance();
        String blns = MessageFormat.format("{0}", balance);
        lblCurrentBalance.setText("$"+blns);
    }

    private void resetAuthorizeAccount() {
        txtAuthFN.setText("");
        txtAuthLN.setText("");
        txtAuthEmail.setText("");
        txtAuthDOB.setText("");
        txtAuthUsername.setText("");
        txtAuthPassword.setText("");
        cbSexUserAuth.setSelectedIndex(0);
        cbAuthorizationSet.setSelectedIndex(0);
    }

    public void getAllTransactions() {
        
        try {
            doutMain.writeUTF("#%$oal!04m2js$%&??sn");
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void getRequestsToTransactions() {

        int count = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getTransType().equalsIgnoreCase("Request") 
               && transactions.get(i).getToAccount().equalsIgnoreCase(client.getAccount().getAccountNumber())
               && transactions.get(i).getStatus().equalsIgnoreCase("Pending")) 
            {
                count++;
            }
            lblAlertNumber.setText(Integer.toString(count));   
        }
    }
    
    public void setProfile(User user) {
        if (user.getPhoto() == null) {
            if (user.getSex().equals("M")) {
                lblUserImage.setVisible(true);
                lblUserImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/male-icon.png")));
            } else {
                lblUserImage.setVisible(true);
                lblUserImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/female-icon.png")));
            } 
            lblUserIconFNskrt.setText(user.getFirstName() + " " + user.getLastName().charAt(0) + ".");
            if (user.getAuthorization().equals("2")) {
                pnlNavAuthorize.setVisible(false);
            }
        } else if(user.getPhoto() != null){
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(user.getPhoto()).getImage()
                .getScaledInstance(lblUserImage.getWidth(), lblUserImage.getHeight(), Image.SCALE_SMOOTH));
            lblUserImage.setIcon(imageIcon);
            lblUserImage.setVisible(true);
            lblUserIconFNskrt.setText(user.getFirstName() + " " + user.getLastName().charAt(0) + ".");
            if (user.getAuthorization().equals("2")) {
                pnlNavAuthorize.setVisible(false);
            }
        }
        lblHomeWelcome.setText("Welcome, " + user.getFirstName());
    }
    
    public void setTableView(JTable tableName) {
        tblAll.setVisible(false);
        tblDep.setVisible(false);
        tblWith.setVisible(false);
        tblTran.setVisible(false);
        tblReq.setVisible(false);
        
        tableName.setVisible(true);  
    }
    
    public void fillTables() {
        showAllTransactions();
        showDepositTransactions();
        showWithdrawalTransactions();
        showTranserTransactions();
        showRequests();
    }
    
    public void showRequests() {
        DefaultTableModel model = (DefaultTableModel) tblReq.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];

        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getTransType().equalsIgnoreCase("Request") 
               && transactions.get(i).getToAccount().equalsIgnoreCase(client.getAccount().getAccountNumber())
               && transactions.get(i).getStatus().equalsIgnoreCase("Pending")) 
            {
                    row[0] = transactions.get(i).getId();
                    row[1] = transactions.get(i).getDOT();
                    row[2] = transactions.get(i).getTransType();
                    row[3] = transactions.get(i).getFromAccount();
                    row[4] = transactions.get(i).getToAccount();
                    row[5] = transactions.get(i).getAmount();
                    row[6] = transactions.get(i).getStatus();
                    model.addRow(row);
            }
        }
    }
    
    public void showAllTransactions() {
        DefaultTableModel model = (DefaultTableModel) tblAll.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];

        for (int i = 0; i < transactions.size(); i++) {
            row[0] = transactions.get(i).getDOT();
            row[1] = transactions.get(i).getTransType();
            row[2] = transactions.get(i).getFromAccount();
            row[3] = transactions.get(i).getToAccount();
            row[4] = transactions.get(i).getAmount();
            row[5] = transactions.get(i).getStatus();
            model.addRow(row);
        }
    }

    public void showDepositTransactions() {
        DefaultTableModel model = (DefaultTableModel) tblDep.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];

        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getTransType().equalsIgnoreCase("Deposit")) {
                row[0] = transactions.get(i).getDOT();
                row[1] = transactions.get(i).getTransType();
                row[2] = transactions.get(i).getFromAccount();
                row[3] = transactions.get(i).getToAccount();
                row[4] = transactions.get(i).getAmount();
                row[5] = transactions.get(i).getStatus();
                model.addRow(row);
            }
        }
    }

    public void showWithdrawalTransactions() {
        DefaultTableModel model = (DefaultTableModel) tblWith.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];

        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getTransType().equalsIgnoreCase("Withdrawal")) {
                row[0] = transactions.get(i).getDOT();
                row[1] = transactions.get(i).getTransType();
                row[2] = transactions.get(i).getFromAccount();
                row[3] = transactions.get(i).getToAccount();
                row[4] = transactions.get(i).getAmount();
                row[5] = transactions.get(i).getStatus();
                model.addRow(row);
            }
        }
    }

    public void showTranserTransactions() {
        DefaultTableModel model = (DefaultTableModel) tblTran.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];

        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getTransType().equalsIgnoreCase("Transfer")) {
                row[0] = transactions.get(i).getDOT();
                row[1] = transactions.get(i).getTransType();
                row[2] = transactions.get(i).getFromAccount();
                row[3] = transactions.get(i).getToAccount();
                row[4] = transactions.get(i).getAmount();
                row[5] = transactions.get(i).getStatus();
                model.addRow(row);
            }
        }
    }

    public void getUserStats() {
        try {
            doutMain.writeUTF("2gs%%f98!@!@$!@fhuhi");
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void showPerformance(int id) {
        try {
            doutMain.writeInt(id);
            int openedAccounts = dinMain.readInt();
            int performedTransactions = dinMain.readInt();
            lblNumAcctOpen.setText(Integer.toString(openedAccounts));
            lblNumTransPerf.setText(Integer.toString(performedTransactions));
            getRewardsStats();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getRewardsStats() {
        int numOfTrans = Integer.parseInt(lblNumTransPerf.getText());
        if (numOfTrans <= 10) {
            lblRewardLevel.setText("BRONZE");
        } else if (numOfTrans > 10 && numOfTrans <= 20) {
            lblRewardLevel.setText("SILVER");
        } else if (numOfTrans > 20 && numOfTrans <= 30) {
            lblRewardLevel.setText("GOLD");
        } else if (numOfTrans > 30) {
            lblRewardLevel.setText("PLATINUM");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> OnlineUsersList;
    private javax.swing.JButton btnApproveRequest;
    private javax.swing.JButton btnAuthImage;
    private javax.swing.JButton btnAuthorize;
    private javax.swing.JButton btnCheckReqAcct;
    private javax.swing.JButton btnCheckTransferAcct;
    private javax.swing.JButton btnCheckUsername;
    private javax.swing.JButton btnCheckUsername1;
    private javax.swing.JButton btnConfirmPIN;
    private javax.swing.JButton btnCreateAcct;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnGoOffline;
    private javax.swing.JButton btnGoOnline;
    private javax.swing.JButton btnRejectRequest;
    private javax.swing.JButton btnRequest;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetCS;
    private javax.swing.JButton btnResetDeposit;
    private javax.swing.JButton btnResetEmployee;
    private javax.swing.JButton btnResetPIN;
    private javax.swing.JButton btnResetRequest;
    private javax.swing.JButton btnResetSettings;
    private javax.swing.JButton btnResetTransfer;
    private javax.swing.JButton btnResetWithdraw;
    private javax.swing.JButton btnSaveSettings;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelectAll;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JButton btnUpdateClient;
    private javax.swing.JButton btnUpdateImage;
    private javax.swing.JButton btnWithdraw;
    private javax.swing.JComboBox<String> cbAuthorizationSet;
    private javax.swing.JComboBox<String> cbSex;
    private javax.swing.JComboBox<String> cbSex1;
    private javax.swing.JComboBox<String> cbSexUserAuth;
    private javax.swing.JComboBox<String> cbUserSex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAccountBalance;
    private javax.swing.JLabel lblAcctNumTxt;
    private javax.swing.JLabel lblAlertNumber;
    private javax.swing.JLabel lblAuthImage;
    private javax.swing.JLabel lblBig;
    private javax.swing.JLabel lblBtnCAMessage;
    private javax.swing.JLabel lblClientAcctNum;
    private javax.swing.JLabel lblClientId;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblCover;
    private javax.swing.JLabel lblCover1;
    private javax.swing.JLabel lblCurrentBalance;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblFemaleClient;
    private javax.swing.JLabel lblHomeWelcome;
    private javax.swing.JLabel lblMaleClient;
    private javax.swing.JLabel lblNumAcctOpen;
    private javax.swing.JLabel lblNumTransPerf;
    private javax.swing.JLabel lblRewardLevel;
    private javax.swing.JLabel lblSuccess;
    private javax.swing.JLabel lblTblAllTransactions;
    private javax.swing.JLabel lblTblDeposits;
    private javax.swing.JLabel lblTblTransfers;
    private javax.swing.JLabel lblTblWithdrawals;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUpdateImage;
    private javax.swing.JLabel lblUserIconFNskrt;
    private javax.swing.JLabel lblUserImage;
    private javax.swing.JLabel lblbackaccount;
    private javax.swing.JPanel pnlAcct;
    private javax.swing.JPanel pnlAlerts;
    private javax.swing.JPanel pnlAuthorize;
    private javax.swing.JPanel pnlBanking;
    private javax.swing.JPanel pnlBankingPIN;
    private javax.swing.JPanel pnlChat;
    private javax.swing.JPanel pnlCliNavSettings;
    private javax.swing.JPanel pnlClientNav;
    private javax.swing.JPanel pnlClientProfile;
    private javax.swing.JPanel pnlClientSettings;
    private javax.swing.JPanel pnlClientWindow;
    private javax.swing.JPanel pnlHistory;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNavAccounts;
    private javax.swing.JPanel pnlNavAlerts;
    private javax.swing.JPanel pnlNavAuthorize;
    private javax.swing.JPanel pnlNavBanking;
    private javax.swing.JPanel pnlNavChat;
    private javax.swing.JPanel pnlNavHistory;
    private javax.swing.JPanel pnlNavHome;
    private javax.swing.JPanel pnlNavServices;
    private javax.swing.JPanel pnlNavSettings;
    private javax.swing.JPanel pnlNavigation;
    private javax.swing.JPanel pnlServices;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JPanel pnlTurnOff;
    private javax.swing.JPanel pnlUserProfile;
    private javax.swing.JScrollPane scrAllTrans;
    private javax.swing.JScrollPane scrDep;
    private javax.swing.JScrollPane scrTran;
    private javax.swing.JScrollPane scrWith;
    private javax.swing.JTable tblAll;
    private javax.swing.JTable tblDep;
    private javax.swing.JTable tblReq;
    private javax.swing.JTable tblTran;
    private javax.swing.JTable tblWith;
    private javax.swing.JTextField txtAcctSearch;
    private javax.swing.JTextField txtAuthDOB;
    private javax.swing.JTextField txtAuthEmail;
    private javax.swing.JTextField txtAuthFN;
    private javax.swing.JTextField txtAuthLN;
    private javax.swing.JTextField txtAuthPassword;
    private javax.swing.JTextField txtAuthUsername;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCity1;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtDOB1;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEnterAcctNum;
    private javax.swing.JTextField txtEnterPIN;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtFirstName1;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLastName1;
    private javax.swing.JTextArea txtMsgArea;
    private javax.swing.JTextField txtPIN;
    private javax.swing.JTextField txtPIN2;
    private javax.swing.JTextField txtPostalCode1;
    private javax.swing.JTextField txtRequest;
    private javax.swing.JTextField txtRequestAccount;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtStreet1;
    private javax.swing.JTextField txtTransId;
    private javax.swing.JTextField txtTransfer;
    private javax.swing.JTextField txtTransferAccount;
    private javax.swing.JTextField txtUserDOB;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUserFirstName;
    private javax.swing.JTextField txtUserLastName;
    private javax.swing.JTextField txtUserPassword;
    private javax.swing.JTextArea txtUserSendMsg;
    private javax.swing.JTextField txtUserUsername;
    private javax.swing.JTextField txtWithdraw;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables

    public class CustomScrollBarUI extends BasicScrollBarUI {

        private final Dimension d = new Dimension();

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {

                private static final long serialVersionUID = -3592643796245558676L;

                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {

                private static final long serialVersionUID = 1L;

                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color color = null;
            JScrollBar sb = (JScrollBar) c;
            if (!sb.isEnabled() || r.width > r.height) {
                return;
            } else if (isDragging) {
                color = Color.decode("#565255"); // change color
            } else if (isThumbRollover()) {
                color = Color.decode("#565255"); // change color
            } else {
                color = Color.decode("#565255"); // change color
            }
            g2.setPaint(color);
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 5, 7);
            g2.setPaint(Color.decode("#565255"));
            g2.drawRoundRect(r.x, r.y, r.width, r.height, 5, 7);
            g2.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }
    }

    public void scrollerSet() {
        scrAllTrans.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane2.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane3.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane4.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane5.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrDep.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrWith.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrTran.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrAllTrans.getColumnHeader().setFont(new Font("Roboto", Font.PLAIN, 10));
        scrAllTrans.getColumnHeader().setBackground(Color.decode("#2a2729"));
        scrAllTrans.getColumnHeader().setForeground(Color.WHITE);
        scrDep.getColumnHeader().setFont(new Font("Roboto", Font.PLAIN, 10));
        scrDep.getColumnHeader().setBackground(Color.decode("#2a2729"));
        scrDep.getColumnHeader().setForeground(Color.WHITE);
        scrWith.getColumnHeader().setFont(new Font("Roboto", Font.PLAIN, 10));
        scrWith.getColumnHeader().setBackground(Color.decode("#2a2729"));
        scrWith.getColumnHeader().setForeground(Color.WHITE);
        scrTran.getColumnHeader().setFont(new Font("Roboto", Font.PLAIN, 10));
        scrTran.getColumnHeader().setBackground(Color.decode("#2a2729"));
        scrTran.getColumnHeader().setForeground(Color.WHITE);
        tblAll.getTableHeader().setBackground(Color.decode("#3CA6EC"));
        tblAll.setOpaque(false);
        tblAll.setBorder(null);
        tblReq.getTableHeader().setBackground(Color.decode("#3CA6EC"));
        tblReq.setOpaque(false);
        tblReq.setBorder(null);
        tblDep.getTableHeader().setBackground(Color.decode("#3CA6EC"));
        tblDep.setOpaque(false);
        tblDep.setBorder(null);
        tblWith.getTableHeader().setBackground(Color.decode("#3CA6EC"));
        tblWith.setOpaque(false);
        tblWith.setBorder(null);
        tblTran.getTableHeader().setBackground(Color.decode("#3CA6EC"));
        tblTran.setOpaque(false);
        tblTran.setBorder(null);

        ((DefaultTableCellRenderer) tblAll.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tblReq.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tblDep.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tblWith.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tblTran.getDefaultRenderer(Object.class)).setOpaque(false);
        dlm = new DefaultListModel();
        OnlineUsersList.setModel(dlm);
        scrAllTrans.setOpaque(false);
        scrAllTrans.getViewport().setOpaque(false);
        scrAllTrans.setBorder(BorderFactory.createEmptyBorder());
        scrAllTrans.setViewportBorder(BorderFactory.createEmptyBorder());
        scrDep.setOpaque(false);
        scrDep.getViewport().setOpaque(false);
        scrDep.setBorder(BorderFactory.createEmptyBorder());
        scrDep.setViewportBorder(BorderFactory.createEmptyBorder());
        scrWith.setOpaque(false);
        scrWith.getViewport().setOpaque(false);
        scrWith.setBorder(BorderFactory.createEmptyBorder());
        scrWith.setViewportBorder(BorderFactory.createEmptyBorder());
        scrTran.setOpaque(false);
        scrTran.getViewport().setOpaque(false);
        scrTran.setBorder(BorderFactory.createEmptyBorder());
        scrTran.setViewportBorder(BorderFactory.createEmptyBorder());
        jScrollPane5.setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        jScrollPane5.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane5.setViewportBorder(BorderFactory.createEmptyBorder());
    }

}
