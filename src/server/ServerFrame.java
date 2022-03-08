/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


/**
 *
 * @author serbinmatosh
 */
public class ServerFrame extends javax.swing.JFrame {

    /**
     * Creates new form ServerFrame
     */
    public ServerFrame() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void write(String line) {
        txtArea.append(line);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        lblRunning = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 300));

        mainPanel.setBackground(new java.awt.Color(42, 39, 41));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRunning.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblRunning.setForeground(new java.awt.Color(72, 117, 202));
        lblRunning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRunning.setText("The Server is Up and Running  .  .  .");
        mainPanel.add(lblRunning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 40));

        jScrollPane2.setBackground(new java.awt.Color(42, 39, 41));
        jScrollPane2.setBorder(null);

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(42, 39, 41));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtArea.setForeground(new java.awt.Color(77, 190, 101));
        txtArea.setRows(5);
        jScrollPane2.setViewportView(txtArea);

        mainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 900, 250));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRunning;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
