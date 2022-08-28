/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.monitoring.system;

import static client.monitoring.system.HomeClient.dout;
import static client.monitoring.system.HomeClient.filePathString;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author acer
 */
public class HomeServer extends javax.swing.JFrame {

    /**
     * Creates new form HomeServer
     */
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream doutMain;
    DataInputStream dinMain;
    public JFileChooser fileChooser;
    public static String PathString;
    DefaultTableModel model;

    public HomeServer() {
        initComponents();
        fileChooser = new JFileChooser();
    }

    public boolean isWin32() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbIp = new javax.swing.JLabel();
        lbPort = new javax.swing.JLabel();
        lbIP = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldFilter = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaStatus = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonLogFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Server");

        jLabel2.setText("PORT:");

        jLabel3.setText("IP:");

        lbIp.setBackground(new java.awt.Color(255, 255, 255));
        lbIp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbPort.setBackground(new java.awt.Color(255, 255, 255));
        lbPort.setText("6000");

        lbIP.setText("None");

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbStatus.setText("Disconnect");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stt", "Time", "Name", "Action", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextFieldFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFilterKeyReleased(evt);
            }
        });

        jButton1.setText("Filter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtAreaStatus.setColumns(20);
        txtAreaStatus.setRows(5);
        jScrollPane3.setViewportView(txtAreaStatus);

        btnBrowse.setText("Browse...");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        jLabel4.setText("Path:");

        jButtonLogFile.setText("Log FIle");
        jButtonLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextFieldFilter))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbIp, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbIP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonLogFile)
                                        .addGap(37, 37, 37)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(lbIp)
                    .addComponent(lbPort)
                    .addComponent(lbIP)
                    .addComponent(btnExit)
                    .addComponent(lbStatus)
                    .addComponent(jButtonLogFile))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(btnBrowse)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public class HandleServer extends Thread {

        public class StartSending extends Thread {

            public String name;
            public DataOutputStream dout;
            public DataInputStream din;

            public StartSending(String name, DataOutputStream dout, DataInputStream din) {
                this.name = name;
                this.dout = dout;
                this.din = din;
            }

            public void run() {
                String str = "";
                while (true) {
                    //str = msg_area.getText();
                    str = "Connection successful!";
                    try {
                        dout.writeUTF(str);
                    } catch (IOException ex) {
                        Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        public class StartReceiving extends Thread {

            public String name;
            public DataOutputStream dout;
            public DataInputStream din;

            public StartReceiving(String name, DataOutputStream dout, DataInputStream din) {
                this.name = name;
                this.dout = dout;
                this.din = din;
            }

            public void run() {

                String str = "";
                //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model = (DefaultTableModel) jTable1.getModel();
                while (true) {
                    try {
                        str = din.readUTF();
                        String[] arrOfStr = str.split("[@]");
                        String a = arrOfStr[0];

                        int number_of_rows = model.getRowCount();
                        if (a.trim().equalsIgnoreCase("--1")) {
                            model.addRow(new Object[]{number_of_rows += 1, arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4]});
                        } else {
                            if (str.equals("Exit")) {
                                txtAreaStatus.setText(txtAreaStatus.getText().trim() + "\n<" + name + ": Disconnected>");
                                String msgTime = (new Date()).toString();
                                model.addRow(new Object[]{number_of_rows += 1, msgTime, name, "LOG_OUT", ""});
                                this.finalize();
                            } else {
                                txtAreaStatus.setText(txtAreaStatus.getText().trim() + "\n" + name + " : " + str);
                            }
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                        JOptionPane.showMessageDialog(null, "Couldnt Stop the thread");
                    }

                }
            }
        }

        public void run() {
            String str;
            //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model = (DefaultTableModel) jTable1.getModel();
            try {
                serverSocket = new ServerSocket(HomeInit.portNumber);
                int number_of_rows = model.getRowCount();
                String msgTime = (new Date()).toString();
                while (true) {
                    socket = serverSocket.accept();
                    dinMain = new DataInputStream(socket.getInputStream());
                    doutMain = new DataOutputStream(socket.getOutputStream());
                    str = dinMain.readUTF();
                    txtAreaStatus.setText(txtAreaStatus.getText() + "\n<" + str + " Connected >");
                    model.addRow(new Object[]{number_of_rows += 1, msgTime, str, "LOG_IN", ""});
                    try {
                        new StartSending(str, doutMain, dinMain).start();
                        new StartReceiving(str, doutMain, dinMain).start();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "What the help");
                    }

                }
            } catch (Exception e) {
                try {
                    JOptionPane.showMessageDialog(null, "Could Not Establish server");
                    HomeInit h = new HomeInit();
                    new HomeServer().setVisible(false);
                    h.setVisible(true);
                } catch (Exception exception) {

                }
            }
        }
    }

    public class startFilter extends Thread {

        public void run() {
            try {
                String queString = jTextFieldFilter.getText().toLowerCase();

                filter(queString);
            } catch (Exception e) {
            }
        }
    }

    public void go() {
        this.setVisible(true);
        lbStatus.setText("started...");
        lbPort.setText("" + HomeInit.portNumber + "");
        HomeServer.PathString = jTextField2.getText().trim();
        new HandleServer().start();
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            lbIP.setText(ip);

        } catch (UnknownHostException ex) {
            Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "All clients will be disconnected.Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == 0) {
            System.exit(0);
        }
        //this.setVisible(false);
        //new HomeInit().setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:

        Path currentRelativePath = Paths.get("");
        String pathString = currentRelativePath.toAbsolutePath().toString();
        jTextField2.setText(pathString);

//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        File f = fileChooser.getSelectedFile();
//        String filename = f.getAbsolutePath();
//        jTextField2.setText(filename);
//        fileChooser.showDialog(this, "Select");
//        File file = fileChooser.getSelectedFile();
//        
//        if(file != null){
//            filePath = file.getPath();
//            if(this.isWin32()){ filePath = filePath.replace("\\", "/"); }
//        }

    }//GEN-LAST:event_btnBrowseActionPerformed

    private void jButtonLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogFileActionPerformed
        // TODO add your handling code here:
        String filePath = PathString + "Data.txt";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                for (int j = 0; j < jTable1.getColumnCount(); j++) {
                    bw.write(jTable1.getValueAt(i, j).toString() + " ");
                }
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButtonLogFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFilterKeyReleased
        // TODO add your handling code here:
        try {
            //new startFilter().start();
            String queString = jTextFieldFilter.getText();

            filter(queString);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jTextFieldFilterKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HomeServer().go();
                } catch (Exception e) {
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonLogFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldFilter;
    private javax.swing.JLabel lbIP;
    private javax.swing.JLabel lbIp;
    private javax.swing.JLabel lbPort;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTextArea txtAreaStatus;
    // End of variables declaration//GEN-END:variables
}
