/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Negocio3.Facade;

/**
 *
 * @author pedro
 */
public class JanelaPrincipalCandidato extends javax.swing.JFrame {
    Facade facade = new Facade();
    private final int id;
    private final boolean loginCandidato;
    /**
     * Creates new form JanelaPrincipalCandidato2
     */
    public JanelaPrincipalCandidato(int id, boolean lc) {
        initComponents();
        this.id = id;
        this.loginCandidato = lc;
        setIcon();
        if(facade.candidatoJaExiste(id)){
            this.jLabel6.setText(facade.getNomeCandidato(id));
        }
        else {
            this.jLabel6.setText(facade.getNomeEleitor(id));
        }
        
    }

    public JanelaPrincipalCandidato()  {
       initComponents();
       setIcon();
       this.id = 0;
       this.loginCandidato = true;
       this.jLabel6.setText(facade.getNomeCandidato(id));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Eleições");
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Candidatar-se em");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 140, 130, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Presidencial", "Assembleia" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(190, 140, 130, 20);

        jButton1.setText("Avançar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(190, 170, 130, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Consultar Resultados");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 360, 180, 17);

        jButton2.setText("Escolher");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(200, 360, 100, 23);

        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(525, 430, 120, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Candidato ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 10, 100, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Ji7xEJe.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(410, 130, 230, 230);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(140, 10, 510, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/candidato_back.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 680, 480);

        setSize(new java.awt.Dimension(693, 515));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ConsultarEcra consul = new ConsultarEcra(this.id,loginCandidato);  
        consul.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Facade facade = new Facade();
        
        int r = facade.idEleicaoAberta();
        
        int tipoEleicao = facade.getTipoEleicao(r);
        String funcao = this.jComboBox1.getSelectedItem().toString(); 
        if(!facade.candidatoJaExiste(id)) {
        //if(r != -1){
        if(funcao.equals("Presidencial")){
                if(tipoEleicao ==1){
                PreRequesitos pre = new PreRequesitos(true,id);  
                pre.setVisible(true);
                this.dispose();
                }
                else {
                    if(tipoEleicao==-1){
                        JOptionPane.showMessageDialog(null,"Não se encontra nenhuma eleição aberta!"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Eleição é para assembleia"); 
                    }
                }
        }
        else {
            if(funcao.equals("Assembleia")){
                if(tipoEleicao<5&&tipoEleicao>1){
                        InscreveAssembleia ia = new InscreveAssembleia(id);
                        ia.setVisible(true);
                        this.dispose();
                        }
                else {
                    if(tipoEleicao==-1){
                        JOptionPane.showMessageDialog(null,"Não se encontra nenhuma eleição aberta!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Eleição aberta é Presidencial"); 
                    }
                }
            }
        }

        }
        else {
            JOptionPane.showMessageDialog(null,"Candidato já está inscrito nesta eleição!"); 
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

  
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
            java.util.logging.Logger.getLogger(JanelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new JanelaPrincipalCandidato().setVisible(true);
                //System.out.printf("%d\n",this.id);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
    private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/pictures/icom.png")));
    }
}