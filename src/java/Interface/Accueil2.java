/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

/**
 *
 * @author etu25695
 */
public class Accueil2 extends javax.swing.JPanel {

    /**
     * Creates new form Accueil2
     */
    public Accueil2() {
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

        message = new javax.swing.JLabel();
        ajout = new javax.swing.JButton();
        suppression = new javax.swing.JButton();
        listing = new javax.swing.JButton();
        modification = new javax.swing.JButton();
        statistique = new javax.swing.JButton();
        recherche = new javax.swing.JButton();

        setLayout(null);

        message.setText("Bienvenue dans le software de libiavélo.");
        add(message);
        message.setBounds(100, 10, 193, 14);

        ajout.setText("Ajout");
        ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutActionPerformed(evt);
            }
        });
        add(ajout);
        ajout.setBounds(23, 99, 59, 23);

        suppression.setText("Suppression");
        add(suppression);
        suppression.setBounds(150, 100, 91, 50);

        listing.setText("Listing");
        add(listing);
        listing.setBounds(297, 99, 63, 23);

        modification.setText("Modification");
        modification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificationActionPerformed(evt);
            }
        });
        add(modification);
        modification.setBounds(10, 189, 89, 23);

        statistique.setText("Statistiques");
        add(statistique);
        statistique.setBounds(144, 189, 89, 23);

        recherche.setText("Recherches");
        add(recherche);
        recherche.setBounds(287, 189, 89, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutActionPerformed

    private void modificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajout;
    private javax.swing.JButton listing;
    private javax.swing.JLabel message;
    private javax.swing.JButton modification;
    private javax.swing.JButton recherche;
    private javax.swing.JButton statistique;
    private javax.swing.JButton suppression;
    // End of variables declaration//GEN-END:variables
}