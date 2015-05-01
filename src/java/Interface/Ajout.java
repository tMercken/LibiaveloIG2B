package Interface;

import javax.swing.*;
import java.awt.*;

public class Ajout extends JPanel {
        private JPanel pForm, pRem, pBout, pAccueil;

   public Ajout() {
       this.setLayout(new BorderLayout());
       this.setBorder(BorderFactory.createTitledBorder("Nouvel ordre de réparation"));
       pForm = new JPanel();
        pRem = new JPanel();
        pBout = new JPanel();
        this.add(pForm,BorderLayout.NORTH);
        this.add(pRem,BorderLayout.CENTER);
        this.add(pBout,BorderLayout.SOUTH);
        pForm.setLayout(new GridLayout(6,2,10,10));
   } 
}
