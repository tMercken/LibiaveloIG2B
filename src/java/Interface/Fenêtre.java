package Interface;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class Fenêtre extends JFrame {
    private JMenuBar barre;
    private JMenu application;
    private JMenuItem quitter;
    private Accueil accueil = new Accueil();
    private Container cont;
    
    public Fenêtre(){
        super("Libiavélo");
        setBounds(100, 100, 500, 500);
        barre = new JMenuBar();
        setJMenuBar(barre);
        
        
        application = new JMenu("Application");
        
        barre.add(application);
        
        quitter = new JMenuItem("Quitter");
        
        application.add(quitter);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }                   
        });
        
        cont = getContentPane();
        cont.add(accueil);
        
        ActionEcouteur ae = new ActionEcouteur();
        quitter.addActionListener(ae);
        
        
        setVisible(true);
    }
    
    private class ActionEcouteur implements ActionListener {
    
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == quitter) {
                System.exit(0);
            }
        }
    }
}
