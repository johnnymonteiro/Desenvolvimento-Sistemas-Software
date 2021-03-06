/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dados3.EleicaoDAO;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Negocio3.Facade;

/**
 *
 * @author pedro
 */
public class Login extends javax.swing.JFrame {
    Facade facade = new Facade();
    private int idUtilizador;
    private boolean loginCandidato;
    //Connection conexao = null;
    //PreparedStatement pst = null;
    //ResultSet rs = null;

    public void login() {
        try{
            int id;
            String cc = this.txtCC.getText();
            id = facade.getIdEleitor(cc);
            
            this.idUtilizador = id;
            if(id!=-1){
                String funcao = this.combo.getSelectedItem().toString();
                /*switch(funcao){
                case("Eleitor"):
                EcraEleitor eleitor = new EcraEleitor();
                eleitor.setVisible(true);
                EcraEleitor.lblUsuario.setText(rs.getString(2));
                this.dispose();
                break;
                case("Candidato"):
                JanelaPrincipalCandidato cand = new JanelaPrincipalCandidato();
                cand.setVisible(true);
                this.dispose();
                break;
                case("Administrador"):
                if(admin.equals("1")){
                JanelaAdministrador janadmin = new JanelaAdministrador();
                janadmin.setVisible(true);
                this.dispose();
                }
                else{
                JOptionPane.showMessageDialog(null, "Não tem permissao de administrador!");
                }
                break;
                }*/

                if(funcao.equals("Administrador")){
                    if (facade.isAdmin(id)){
                    JanelaAdministrador janadmin = new JanelaAdministrador();
                    janadmin.setVisible(true);
                    this.dispose();
                    }
                }

                if(funcao.equals("Eleitor")) {
                    EcraEleitor eleitor = new EcraEleitor(id,loginCandidato);
                    eleitor.setVisible(true);
                    //EcraEleitor.lblUsuario.setText(rs.getString(2));
                    this.dispose();
                }
                if(funcao.equals("Candidato")){
                    /*int eleicaoCandidatura = facade.idEleicaoAberta();
                    if(eleicaoCandidatura != -1){
                    facade.loginCandidato(this.txtCC.getText());
                    JanelaPrincipalCandidato cand = new JanelaPrincipalCandidato(id);
                    cand.setVisible(true);
                    this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Nenhuma eleição com período de candidatura em aberto!");
                    }*/
                    
                        
                        facade.loginCandidato(this.txtCC.getText());
                        this.loginCandidato = true;
                        JanelaPrincipalCandidato cand = new JanelaPrincipalCandidato(id,loginCandidato);
                        cand.setVisible(true);
                        this.dispose();
                    
                        
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Cartao de Cidado não existe na BD");
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Não consegue estabelecer conexão a base de dados.");
            // Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Creates new form Login2
     */
    public Login() {
        initComponents();
        
        this.facade = new Facade();
        this.loginCandidato = false;
        this.idUtilizador = 0;
        //conexao = ModuloConexao.conector();
        //System.out.println(conexao);
        if (!facade.connectionIsClosed()) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/acept.png")));
        } else {
             lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/block.png")));
        }
        setIcon();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        combo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCC = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Eleições");
        getContentPane().setLayout(null);

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(510, 380, 90, 23);

        combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eleitor", "Candidato", "Administrador" }));
        getContentPane().add(combo);
        combo.setBounds(40, 320, 140, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nrº de Cartão de Cidadão");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 250, 200, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Bem-vindo ao Gestor de Eleições");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 20, 310, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Login");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 200, 140, 20);
        getContentPane().add(txtCC);
        txtCC.setBounds(280, 250, 130, 30);
        getContentPane().add(lblStatus);
        lblStatus.setBounds(410, 30, 200, 160);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/cne.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(210, 70, 200, 130);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/eleicoes.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 620, 420);

        setSize(new java.awt.Dimension(637, 459));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        login();
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtCC;
    // End of variables declaration//GEN-END:variables
    private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/pictures/icom.png")));
    }
}
