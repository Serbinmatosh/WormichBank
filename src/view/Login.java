package view;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author serbinmatosh
 */
public class Login extends javax.swing.JFrame {

    Socket socket;

    public Login() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        btnCancel = new javax.swing.JButton();
        btnSignIn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 515));
        setMinimumSize(new java.awt.Dimension(400, 515));
        setPreferredSize(new java.awt.Dimension(400, 515));
        setSize(new java.awt.Dimension(400, 515));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtUsername.setToolTipText("");
        txtUsername.setBorder(null);
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 201, 228, 43));

        jPasswordField1.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField1.setBorder(null);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 261, 228, 43));

        btnCancel.setBackground(new java.awt.Color(42, 39, 41));
        btnCancel.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 330, 110, 33));

        btnSignIn.setBackground(new java.awt.Color(42, 39, 41));
        btnSignIn.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSignIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSignIn.setText("Sign In");
        btnSignIn.setBorder(null);
        btnSignIn.setBorderPainted(false);
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });
        getContentPane().add(btnSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 330, 110, 33));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder/Login.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed

        System.exit(0);

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed
        if (!txtUsername.getText().equalsIgnoreCase("") && !jPasswordField1.getText().equals("")) {
            accept();
        } else {
            JOptionPane.showMessageDialog(this, "Username or Password Cannot be Empty");
        }
    }//GEN-LAST:event_btnSignInActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtUsername.getText().equalsIgnoreCase("") && !jPasswordField1.getText().equals("")) {
                accept();
            } else {
                JOptionPane.showMessageDialog(this, "Username or Password Cannot be Empty");
            }
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSignIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    public void accept() {
        String username = txtUsername.getText();
        String password = jPasswordField1.getText();
        try {
            socket = new Socket("Localhost", 2089);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            dout.writeUTF(username);
            dout.writeUTF(password);

            String i = din.readUTF();
            if (i.equals("You Are Alredy Registered....!!")) {
                JOptionPane.showMessageDialog(this, "You are already logged in somewhere...");
            } else if (i.equals("Error!")) {
                JOptionPane.showMessageDialog(this, "Wrong Username or Password");
                txtUsername.setText("");
                jPasswordField1.setText("");
            } else {
                dispose();
                User user = receiveUser(socket);
                new MainFrame(user, socket);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User receiveUser(Socket socket) throws IOException {
        try {
            DataInputStream din2 = new DataInputStream(socket.getInputStream());

            int id = din2.readInt();
            String username = din2.readUTF();
            String password = din2.readUTF();
            String firstName = din2.readUTF();
            String lastName = din2.readUTF();
            String email = din2.readUTF();
            String dob = din2.readUTF();
            String sex = din2.readUTF();
            String authorization = din2.readUTF();
            byte[] photo = null;
            int length = din2.readInt();
            if (length > 0) {
                byte[] message = new byte[length];
                din2.readFully(message, 0, message.length); // read the message
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                User user = new User(id, username, password, firstName, lastName, email, DOB, sex, authorization, message);
                return user;
            } else {
                String bb = din2.readUTF();
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                User user = new User(id, username, password, firstName, lastName, email, DOB, sex, authorization, photo);
                return user;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
