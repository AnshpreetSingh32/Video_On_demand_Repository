
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ANSHPREET SINGH
 */
public class user_home extends javax.swing.JFrame {

    String email;
    /**
     * Creates new form user_home
     */
    public user_home(String email) {
        initComponents();
        setSize(500,620);
        setLocationRelativeTo(null);
        ImageIcon ic = new ImageIcon("src/background.jpg");
        Image im = ic.getImage().getScaledInstance(background.getWidth(),background.getHeight(),Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(im));
        this.email = email;
        jLabel1.setText("welcome: "+email);
    }
     void load_movies()
    {
        mainpanel.removeAll();
        mainpanel.repaint();
        if(!tf.getText().equals(""))
        {
        String ans = myClient.search_movies(tf.getText());
        StringTokenizer st = new StringTokenizer(ans,";;");
        int n = st.countTokens();
        JButton[] arr = new JButton[n];
        
        int x=75,y=50;
        for(int i=0;i<n;i++)
        {
            StringTokenizer st1 = new StringTokenizer(st.nextToken(),"$");
            String movie = st1.nextToken();
            String photo = st1.nextToken();
            int id = Integer.parseInt(st1.nextToken());
            
            arr[i] = new JButton(movie);
            arr[i].setBounds(x, y, 225, 100);
            
            ImageIcon ic = new ImageIcon(photo);
            Image im = ic.getImage().getScaledInstance(arr[i].getWidth()/2, arr[i].getHeight()-10, Image.SCALE_SMOOTH);
            arr[i].setIcon(new ImageIcon(im));
            arr[i].setIconTextGap(10);
            
            arr[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   Movie_details obj = new Movie_details(id);
                   obj.setVisible(true);
                }
            });
            
            mainpanel.add(arr[i]);
            mainpanel.repaint();
            y+=120;   
        }
        mainpanel.setPreferredSize(new Dimension(400,n*150));
        }
        else
        {
            mainpanel.removeAll();
            mainpanel.repaint();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        tf.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfCaretUpdate(evt);
            }
        });
        getContentPane().add(tf);
        tf.setBounds(30, 110, 440, 40);

        mainpanel.setPreferredSize(new java.awt.Dimension(418, 348));

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainpanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 170, 410, 330);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Type Movie Name :-");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 200, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Genres");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(150, 530, 180, 50);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 26)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("welcome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 500, 60);

        background.setText("jLabel3");
        getContentPane().add(background);
        background.setBounds(0, 0, 500, 620);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCaretUpdate
        load_movies();
    }//GEN-LAST:event_tfCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        categories_home obj = new categories_home(email);
        obj.setVisible(true);
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
            java.util.logging.Logger.getLogger(user_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user_home("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables
}
