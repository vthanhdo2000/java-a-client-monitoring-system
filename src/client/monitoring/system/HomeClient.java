/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.monitoring.system;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class HomeClient extends javax.swing.JFrame {

    /**
     * Creates new form HomeClient
     */
    static Socket socket;
    static DataInputStream din;
    static DataOutputStream dout;
    static String ip;
    static String serverAddr;
    static int port;
    static String path;
    static String filePathString;

    public HomeClient() {
        initComponents();
    }

    class HandleClient extends Thread {

        HomeClient client;

        public HandleClient(HomeClient cclient) {
            this.client = client;
        }

        public void run() {
            try {
                //s=new Socket(ip,ClientHome.portNumber); 
                //InetAddress a=new InetAddress(publicIp);
                try {
                    serverAddr = ClientConnection.ip;
                    port = ClientConnection.portNumber;
                    socket = new Socket(InetAddress.getByName(serverAddr), port);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Connect fail!!!, Please Try again", "Error in Connection", JOptionPane.ERROR_MESSAGE);
                    client.setVisible(false);
                    new ClientConnection().setVisible(true);
                }

                //socket = new Socket(InetAddress.getByName(serverAddr), ClientConnection.portNumber, InetAddress.getByName(ip), ClientConnection.portNumber);
                din = new DataInputStream(socket.getInputStream());
                dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(ClientConnection.name);
                String str = "";
                while (str != "Exit") {
                    str = din.readUTF();
                    if (str == "Exit") {
                        txtAreaSend.setText(str);
                    } else {
                        txtAreaSend.setText(str);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connect fail!!!, Please Try again", "Error in Connection", JOptionPane.ERROR_MESSAGE);
                client.setVisible(false);
                new ClientConnection().setVisible(true);
            }
        }
    }

    class Monitoring extends Thread {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        public void run() {
            try (WatchService service = FileSystems.getDefault().newWatchService()) {
                Map<WatchKey, Path> keyMap = new HashMap<>();

                Path path = Paths.get(jTextField1.getText()); //path
                keyMap.put(path.register(service,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.OVERFLOW), path);

                WatchKey watchKey;

                do {
                    watchKey = service.take();
                    Path eventDir = keyMap.get(watchKey);

                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        Path eventPath = (Path) event.context();

                       
                        int number_of_rows = model.getRowCount();
                        String msgTime = (new Date()).toString();
                        model.addRow(new Object[]{number_of_rows+=1, msgTime, lbNameClient.getText(), kind, eventPath });
                        
                        String sendServerData = "--1@" + msgTime + "@" + lbNameClient.getText() + "@"+ kind + "@" + eventPath; 
                        dout.writeUTF(sendServerData);
                     
                    }
                } while (watchKey.reset());

            } catch (Exception e) {
            }
        }
    }

    public void go() {
        this.setVisible(true);
        ip = ClientConnection.ip;
        lbNameClient.setText(ClientConnection.name);
        Path currentRelativePath = Paths.get("");
        String pathString = currentRelativePath.toAbsolutePath().toString();
        jTextField1.setText(pathString);
        new HandleClient(this).start();

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
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaSend = new javax.swing.JTextArea();
        lbNameClient = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Client:");

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txtAreaSend.setColumns(20);
        txtAreaSend.setRows(5);
        jScrollPane1.setViewportView(txtAreaSend);

        lbNameClient.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbNameClient.setText("None");

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
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setText("Path:");

        jButton1.setText("Action");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnExit)
                    .addComponent(lbNameClient)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == 0) {
            try {
                // TODO add your handling code here:
                dout.writeUTF("Exit");
                // this.setVisible(false);
                // new HomeInit().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(HomeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Monitoring().start();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(HomeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeClient().go();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbNameClient;
    private javax.swing.JTextArea txtAreaSend;
    // End of variables declaration//GEN-END:variables
}
