package Developpement.FrontEnd;

import Developpement.BackEnd.FromNumberToText;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

 
public class FNTT extends JFrame {

  private JButton bFNTTRandom;
  private JButton bFNTT;
  private JLabel labelFNTTRandom;
  private JLabel labelFNTT;
  private JLabel labelResultatRandom;
  private JLabel labelResultat;
  private JTextField champ;
  private FromNumberToText fntt;

  public FNTT (){
    
    // initialisation des boutons et du convertisseur
    bFNTTRandom = new JButton("<html><div style='text-align: center;'>Convertir un nombre<br>au hasard</div></html>");
    bFNTT = new JButton("<html><div style='text-align: center;'>Convertir le nombre <br> écrit dans le champ</html>");
    fntt = new FromNumberToText();


    // initialisation des tailles du champs, des bouttons et des textes
    int h = 800;
    int w = 1500;

    int hChamp = h/4;
    int wChamp = w - 10;

    int hBoutonRandom = 75;
    int wBoutonRandom = 175;

    int hLabelFNTTRandom = 10;
    int wLabelFNTTRandom = 700;

    int hLabelFNTT = 10;
    int wLabelFNTT= 700;

    int hLabelResultatRandom = 25;
    int wLabelResultatRandom = wChamp;
    int policeLabelResultatRandom = 16;

    int hLabelResultat = 100;
    int wLabelResultat = wChamp;
    int policeLabelResultat = 16;

    // inistialisation de la fenêtre
    setTitle("From Number To Text");
    setSize(w, h);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);
    
    // initialisation des dimensions et positions du champ, des boutons et des textes
    bFNTTRandom.setBounds(w/2 - wBoutonRandom/2 + w/4, ((int)(0.75*h)) - hBoutonRandom/2, wBoutonRandom, hBoutonRandom);
    
    bFNTT.setBounds(w/2 - wBoutonRandom/2 - w/4, ((int)(0.75*h)) - hBoutonRandom/2, wBoutonRandom, hBoutonRandom);

    champ = new JTextField();
    champ.setBounds(w/2 - wChamp/2, h/4 - hChamp/2, wChamp, hChamp);

    labelFNTTRandom = new JLabel();
    labelFNTTRandom.setBounds(champ.getX(), champ.getY() + champ.getHeight() + hLabelFNTTRandom, wLabelFNTTRandom, hLabelFNTTRandom);

    labelResultatRandom = new JLabel();
    labelResultatRandom.setBounds(champ.getX(), labelFNTTRandom.getY() + labelFNTTRandom.getHeight()/2 + hLabelResultatRandom, wLabelResultatRandom, hLabelResultatRandom);
    labelResultatRandom.setFont(new Font("blabla", Font.ITALIC, policeLabelResultatRandom));

    labelFNTT = new JLabel();
    labelFNTT.setBounds(champ.getX(), labelResultatRandom.getY() + labelResultatRandom.getHeight()/2 + 2*hLabelFNTT, wLabelFNTT, hLabelFNTT);
    
    labelResultat = new JLabel();
    labelResultat.setBounds(champ.getX(), labelFNTT.getY() + labelFNTT.getHeight(), wLabelResultat, hLabelResultat);
    labelResultat.setFont(new Font("blabla", Font.ITALIC, policeLabelResultat));
    

    // initialisation du fonctionnement des boutons 
    bFNTTRandom.addActionListener(e -> {
            long n = fntt.nombreAuHasard();
            String texte = fntt.fromNumberToText(n);
            labelFNTTRandom.setText("Le nombre choisi au hasard était : " + n + ". Voici sa version en texte : ");
            labelResultatRandom.setText(texte);
    });

    bFNTT.addActionListener(e -> {
            try {
                long n = Long.parseLong(champ.getText()); 
                String texte = fntt.fromNumberToText(n); 
                labelFNTT.setText("Le nombre que vous avez choisi de convertir en texte est " + n + ". Voici le resultat : " );
                labelResultat.setText(texte);
            } catch (NumberFormatException ex) {
                labelResultat.setText("<html>Entrée invalide ! Cela peut être dû à plusieurs raisons :<br>-Le fait que la plus grande plage d'entiers en Java (Long) est [-9223372036854775808;9223372036854775807]<br>-Vous avez tapé quelque chose qui n'est pas un nombre/chiffre</html>");

            }
    });
    
    // On ajoute le champ, les boutons et les textes à la fenêtre
    add(bFNTTRandom);
    add(bFNTT);
    add(labelFNTTRandom);
    add(labelFNTT);
    add(labelResultatRandom);
    add(labelResultat);
    add(champ);

    // On rend la fenêtre visible 
    setVisible(true);
  }      
}
