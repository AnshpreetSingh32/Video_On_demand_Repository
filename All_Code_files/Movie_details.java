
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;


public class Movie_details extends javax.swing.JFrame {

    int movie_id;
    public Movie_details(int id) {
        initComponents();
        setSize(700, 500);
        setLocationRelativeTo(null);
        ImageIcon ic = new ImageIcon("src/background.jpg");
        Image im = ic.getImage().getScaledInstance(background.getWidth(),background.getHeight(),Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(im));
        movie_nameTF.setEditable(false);
        directorTF.setEditable(false);
        castTF.setEditable(false);
        movie_id = id;
        loadmovie();
    }
    
    void loadmovie()
    {
        String  ans = myClient.moviedetail(movie_id);
        StringTokenizer st = new StringTokenizer(ans, "$");
        String name = st.nextToken();
        String direct = st.nextToken();
        String cast = st.nextToken();
        String photo = st.nextToken();
        String youtube_id = st.nextToken();
        String movie_link = st.nextToken();
        ImageIcon ic = new ImageIcon(photo);
        Image img = ic.getImage().getScaledInstance(photolb.getWidth(), photolb.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img);
        photolb.setIcon(ic1);
        movie_nameTF.setText(name);
        directorTF.setText(direct);
        castTF.setText(cast);
        trailerbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://www.youtube.com/watch?v="+youtube_id);
                    Desktop d = Desktop.getDesktop();
                    d.browse(uri);
                } 
                catch (Exception ex) {
                    
                }  
            }
        });
        
        moviebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f =new File(movie_link);
                    Desktop d = Desktop.getDesktop();
                    d.open(f);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photolb = new javax.swing.JLabel();
        moviebt = new javax.swing.JButton();
        trailerbt = new javax.swing.JButton();
        movie_nameTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        directorTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        castTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(photolb);
        photolb.setBounds(0, 10, 380, 340);

        moviebt.setBackground(new java.awt.Color(204, 204, 204));
        moviebt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moviebt.setText("Play Movie");
        getContentPane().add(moviebt);
        moviebt.setBounds(430, 390, 170, 60);

        trailerbt.setBackground(new java.awt.Color(204, 204, 204));
        trailerbt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        trailerbt.setText("Play Trailer");
        getContentPane().add(trailerbt);
        trailerbt.setBounds(80, 390, 170, 60);

        movie_nameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movie_nameTFActionPerformed(evt);
            }
        });
        getContentPane().add(movie_nameTF);
        movie_nameTF.setBounds(460, 80, 200, 30);

        jLabel3.setText("Director :-");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(400, 190, 80, 30);
        getContentPane().add(directorTF);
        directorTF.setBounds(460, 190, 200, 30);

        jLabel4.setText("Cast :-");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(420, 300, 80, 30);
        getContentPane().add(castTF);
        castTF.setBounds(460, 300, 200, 30);

        jLabel5.setText("Name :-");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(410, 80, 80, 30);

        background.setText("jLabel1");
        getContentPane().add(background);
        background.setBounds(-3, -4, 710, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void movie_nameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movie_nameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movie_nameTFActionPerformed

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
            java.util.logging.Logger.getLogger(Movie_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movie_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movie_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movie_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Movie_details(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JTextField castTF;
    private javax.swing.JTextField directorTF;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField movie_nameTF;
    private javax.swing.JButton moviebt;
    private javax.swing.JLabel photolb;
    private javax.swing.JButton trailerbt;
    // End of variables declaration//GEN-END:variables
}
