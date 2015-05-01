package Interface;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class Fenêtre extends JFrame {
    private JMenuBar barre;
    private JMenu application, ordreRepa, recherches, stats;
    private JMenuItem quitter, ajout, modif, suppr, rech1, rech2, rech3, meilRepa;
    private JPanel pan;
    private JLabel msg, pic;
    private Container cont;
    
    public Fenêtre(){
        super("Libiavélo");
        setBounds(100, 100, 500, 500);
                
        barre = new JMenuBar();
        setJMenuBar(barre);
        
        
        application = new JMenu("Application");
        application.setMnemonic('A');
        barre.add(application);
        
        ordreRepa = new JMenu("Ordre de Réparation");
        ordreRepa.setMnemonic('O');
        barre.add(ordreRepa);
        
        recherches = new JMenu("Recherches");
        recherches.setMnemonic('R');
        barre.add(recherches);
        
        stats = new JMenu("Statistiques");
        stats.setMnemonic('S');
        barre.add(stats);
        
        quitter = new JMenuItem("Quitter");
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        application.add(quitter);
        
        ajout = new JMenuItem("Ajout");
        ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        ordreRepa.add(ajout);
        
        modif = new JMenuItem("Modification");
        modif.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        ordreRepa.add(modif);
        
        suppr = new JMenuItem("Suppression");
        suppr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        ordreRepa.add(suppr);
        
        rech1 = new JMenuItem("Recherche 1");
        rech1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.CTRL_MASK));
        recherches.add(rech1);
        
        rech2 = new JMenuItem("Recherche 2");
        rech2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.CTRL_MASK));
        recherches.add(rech2);
        
        rech3 = new JMenuItem("Recherche 3");
        rech3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,InputEvent.CTRL_MASK));
        recherches.add(rech3);
        
        meilRepa = new JMenuItem("Meilleur Réparateur");
        meilRepa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        stats.add(meilRepa);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }                   
        });
        

        ActionEcouteur ae = new ActionEcouteur();
        quitter.addActionListener(ae);
        ajout.addActionListener(ae);
        modif.addActionListener(ae);
        suppr.addActionListener(ae);
        rech1.addActionListener(ae);
        rech2.addActionListener(ae);
        rech3.addActionListener(ae);
        meilRepa.addActionListener(ae);
        
        pan = new JPanel();
        pan.setLayout(new FlowLayout());
        pan.setBorder(BorderFactory.createTitledBorder("Accueil"));
        msg = new JLabel("<html>Bienvenue sur le logiciel interne de <br>Libiavelo</html>");
        pic = new JLabel(new ImageIcon("HELLOBOSS.jpg"));
        
        pan.add(msg);
        pan.add(pic);
        
        cont = getContentPane();
        cont.setLayout(new FlowLayout());
        cont.add(pan);
        
        setVisible(true);
    }
    
    private class ActionEcouteur implements ActionListener {
    
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == quitter) {
                System.exit(0);
            }
            if(ae.getSource() == ajout) {
               cont.removeAll();
               cont.add(new Ajout());
               cont.repaint();
               Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == modif) {
                cont.removeAll();
                cont.add(new Modification());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == suppr) {
                cont.removeAll();
                cont.add(new Suppression());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == rech1) {
                cont.removeAll();
                cont.add(new Recherche1());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == rech2) {
                cont.removeAll();
                cont.add(new Recherche2());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == rech3) {
                cont.removeAll();
                cont.add(new Recherche3());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
            if(ae.getSource() == stats) {
                cont.removeAll();
                cont.add(new Statistiques());
                cont.repaint();
                Fenêtre.this.setVisible(true);
            }
        }
    }
}
