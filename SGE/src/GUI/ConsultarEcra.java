/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import Negocio3.Facade;

/**
 *
 * @author pedro
 */
public class ConsultarEcra extends javax.swing.JFrame {

    Facade facade = new Facade();
    private final int idEleitor;
    private final boolean loginCandidato;
    /**
     * Creates new form ConsultarEcra2
     */
    public ConsultarEcra() {
        initComponents();
        setIcon();
        this.loginCandidato = false;
        this.idEleitor = 0;
        JOptionPane.showMessageDialog(null,"Problema com o seu ID Eleitor");
    }
    
    public ConsultarEcra(int id,boolean lc){
        initComponents();
        setIcon();
        this.idEleitor = id;
        this.loginCandidato = lc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Eleições");
        getContentPane().setLayout(null);

        jLabel2.setText("Consultar Resultados");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(290, 40, 210, 30);

        jLabel3.setText("Escolha uma das seguintes opções");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 100, 240, 30);

        jButton1.setText("Presidencial");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(130, 270, 120, 23);

        jButton2.setText("Assembleia");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(473, 270, 160, 30);

        jLabel4.setText("Região Autonoma");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 320, 120, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-------", "Madeira", "Açores" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(50, 360, 140, 20);

        jButton3.setText("Retroceder");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(490, 410, 120, 23);

        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(630, 410, 110, 23);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/palacio-belem-4.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 140, 290, 110);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/parlamento_europeu_kerodicas.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(380, 140, 350, 110);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/eleicoes.jpg"))); // NOI18N
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 750, 450);

        setSize(new java.awt.Dimension(765, 490));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ConjuntoEleicoes conj = new ConjuntoEleicoes(1,idEleitor,loginCandidato);  
        conj.setVisible(true);  
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String funcao = this.jComboBox1.getSelectedItem().toString();
        int tipo=0;
        if(funcao.equals("-------")) tipo = 2;
        if(funcao.equals("Madeira")) tipo = 3;
        if(funcao.equals("Açores")) tipo = 4;
        if(tipo!=0){
        ConjuntoEleicoes conj = new ConjuntoEleicoes(tipo,idEleitor,loginCandidato);  
        conj.setVisible(true);
        this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,"Erro ao atribuir tipo da eleição");
        }
    
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(this.loginCandidato){
        JanelaPrincipalCandidato jc = new JanelaPrincipalCandidato(idEleitor,loginCandidato);  
        jc.setVisible(true);
        this.dispose();
        }
        else{
            EcraEleitor eleitor = new EcraEleitor(idEleitor,loginCandidato);  
            eleitor.setVisible(true);
            this.dispose();
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarEcra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarEcra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarEcra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarEcra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarEcra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
    private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/pictures/icom.png")));
    }

}