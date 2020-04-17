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
public class AssociarCirculoEleitoralDistritos extends javax.swing.JFrame {

    
    private final int id;
    private final int idCabeca;
    /**
     * Creates new form AssociarCirculoEleitoralDistritos2
     */
    
    public void mostradistrito(){
        
        String funcao = this.jList1.getSelectedValue().toString();
        
        switch(funcao){
            case("Viana do Castelo"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/vianadocastelo.gif")));
                break;
            case("Vila Real"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/vilareal.gif")));
                break;
            case("Bragança"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/braganca.gif")));
                break;
            case("Braga"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/braga.gif")));
                break;
            case("Viseu"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/viseu.gif")));
                break;
            case("Porto"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/porto.gif")));
                break;
            case("Guarda"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/guarda.gif")));
                break;
            case("Aveiro"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/aveiro.gif")));
                break;
            case("Leiria"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/leiria.gif")));
                break;
            case("Castelo-Branco"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/castelobranco.gif")));
                break;
            case("Coimbra"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/coimbra.gif")));
                break;
            case("Santarém"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/santarem.gif")));
                break;
            case("Portalegre"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/portalegre.gif")));
                break;
            case("Lisboa"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/lisboa.gif")));
                break;
            case("Évora"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/evora.gif")));
                break;
            case("Beja"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/beja.gif")));
                break;
            case("Faro"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/faro.gif")));
                break;
            case("Setúbal"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/setubal.gif")));
                break;
            case("Madeira"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/madeira.gif")));
                break;
            case("Açores"):
                AssociarCirculoEleitoralDistritos.labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/açores.jpg")));
                break;
            
                
        }
    }
    
    public AssociarCirculoEleitoralDistritos() {
        initComponents();
        setIcon();
        this.id = 0;
        this.idCabeca = 0;
        
    }
    
    public AssociarCirculoEleitoralDistritos(int id,int idCabeca){
        initComponents();
        setIcon();
        this.id = id;
        this.idCabeca = idCabeca;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        labelcirculo = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Eleições");
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Associar a Círculo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 50, 290, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Selecione o circulo eleitoral correspondente ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 150, 340, 17);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Viana do Castelo", "Vila Real", "Bragança", "Braga", "Viseu", "Porto", "Guarda", "Aveiro", "Leiria", "Castelo-Branco", "Coimbra", "Santarém", "Portalegre", "Lisboa", "Évora", "Beja", "Faro", "Setúbal", "Madeira", "Açores" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 190, 230, 130);

        labelcirculo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelcirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/mapa_completo.png"))); // NOI18N
        getContentPane().add(labelcirculo);
        labelcirculo.setBounds(290, 50, 450, 460);

        jButton3.setText("Criar Lista");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(40, 400, 130, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pictures/candidato_back.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 533);

        setSize(new java.awt.Dimension(774, 584));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  
    
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()){
           mostradistrito(); 
        }       
        
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Facade facade = new Facade();
        String funcao = this.jList1.getSelectedValue().toString();
        int r = facade.idEleicaoAberta();
        if(r != -1){
            int cir = facade.getIdCirculo(funcao);
            facade.setCirculoEleitoral(idCabeca, cir);
            facade.putLista(id, id, idCabeca, false, r);
            InscreveAssembleia ia = new InscreveAssembleia(id);
            ia.setVisible(true);
            this.dispose();
        }
        else{
            System.err.println("Mais que eleição em aberto, lista não foi criada");
            InscreveAssembleia ia = new InscreveAssembleia(id);
            ia.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(AssociarCirculoEleitoralDistritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssociarCirculoEleitoralDistritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssociarCirculoEleitoralDistritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssociarCirculoEleitoralDistritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssociarCirculoEleitoralDistritos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel labelcirculo;
    // End of variables declaration//GEN-END:variables
    private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/pictures/icom.png")));
    }

}
