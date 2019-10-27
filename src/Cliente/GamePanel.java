package Cliente;

import Server.Jogador;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class GamePanel extends javax.swing.JFrame implements Runnable {

    Player player;
    //Player player2; //
    static List<Player> players = new ArrayList<>();

    Boolean keyRight = false, keyLeft = false, keyUp = false, keyDown = false, keySpace = false;
    Thread t;
    Integer speed = 7;

    /**
     * Creates new form GamePanel
     */
    public GamePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                player.enviarMensagem("r");
                player.setIconRight();
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                player.enviarMensagem("l");
                player.setIconLeft();
                break;
            case KeyEvent.VK_UP:
                keyUp = true;
                player.enviarMensagem("u");
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                player.enviarMensagem("d");
                break;
            case KeyEvent.VK_SPACE:
                keySpace = true;
                player.enviarMensagem("s");
                player.setIconSpace();
                break;
        }

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                player.setIconStopped();
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                player.setIconStopped();
                break;
            case KeyEvent.VK_UP:
                keyUp = false;
                player.setIconStopped();
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                player.setIconStopped();
                break;
            case KeyEvent.VK_SPACE:
                keySpace = false;
                player.setIconStopped();
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened   //////
        // TODO add your handling code here:
        player = new Player();
        player.setup(); //configura o player.
        players.add(player); //

        getContentPane().add(player);
        repaint();
        t = new Thread(this);
        t.start();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GamePanel g = new GamePanel();
                g.setSize(800, 600);
                g.setResizable(false);
                g.setVisible(true);

            }
        });
    }

    @Override
    public void run() {
        while (true) {
            try {
                player.setIconStopped();
                player.receberMensagens();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
