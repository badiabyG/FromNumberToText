package Developpement.BackEnd;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FromNumberToText {

    private Map<Integer, String> unite;
    private Map<Integer, String> dizaines;
    private Map<Integer, String> speciaux;
    private Map<Integer, String> speciauxDigit;
    private Map<Integer, String> grandNombres;


    public FromNumberToText(){
        unite = new HashMap<Integer,String>();
        unite.put(0, "Zero");
        unite.put(1, "Un");
        unite.put(2, "Deux");
        unite.put(3, "Trois");
        unite.put(4, "Quatre");
        unite.put(5, "Cinq");
        unite.put(6, "Six");
        unite.put(7, "Sept");
        unite.put(8, "Huit");
        unite.put(9, "Neuf");

        dizaines = new HashMap<Integer,String>();
        dizaines.put(1, "Dix");
        dizaines.put(2, "Vingt");
        dizaines.put(3, "Trente");
        dizaines.put(4, "Quarante");
        dizaines.put(5, "Cinquante");
        dizaines.put(6, "Soixante");
        dizaines.put(7, "Soixante");
        dizaines.put(8, "Quatre-Vingt");
        dizaines.put(9, "Quatre-Vingt");

        speciaux = new HashMap<Integer,String>();
        speciaux.put(10, "Dix");
        speciaux.put(11, "Onze");
        speciaux.put(12, "Douze");
        speciaux.put(13, "Treize");
        speciaux.put(14, "Quatorze");
        speciaux.put(15, "Quinze");
        speciaux.put(16, "Seize");
        speciaux.put(17, "Dix-Sept");
        speciaux.put(18, "Dix-Huit");
        speciaux.put(19, "Dix-Neuf");

        speciauxDigit = new HashMap<Integer,String>();
        speciauxDigit.put(0, "Dix");
        speciauxDigit.put(1, "Onze");
        speciauxDigit.put(2, "Douze");
        speciauxDigit.put(3, "Treize");
        speciauxDigit.put(4, "Quatorze");
        speciauxDigit.put(5, "Quinze");
        speciauxDigit.put(6, "Seize");
        speciauxDigit.put(7, "Dix-Sept");
        speciauxDigit.put(8, "Dix-Huit");
        speciauxDigit.put(9, "Dix-Neuf");

        grandNombres = new HashMap<Integer, String>();
        grandNombres.put(6, "Million");
        grandNombres.put(9, "Milliard");
        grandNombres.put(12, "Billion");
        grandNombres.put(15, "Billiard");
        grandNombres.put(18, "Trillion");
        grandNombres.put(21, "Trilliard");
        grandNombres.put(24, "Quadrillion");
        grandNombres.put(27, "Quadrilliard");
        grandNombres.put(30, "Quintillion");
        grandNombres.put(33, "Quintillion");
        grandNombres.put(36, "Sextillion");
        grandNombres.put(39, "Sextilliard");
        grandNombres.put(42, "Septillion");
        grandNombres.put(45, "Septilliard");
        grandNombres.put(48, "Octillion");
        grandNombres.put(51, "Octilliard");
        grandNombres.put(54, "Nonillion");
        grandNombres.put(57, "Nonilliard");
        grandNombres.put(60, "Decillion");
        grandNombres.put(63, "Decilliard");
    }

    /**
     * 
     * @param nb un nombre sous forme de chaine de caractère où il faut enlever les 0 devant (s'il y en a)
     * @return le nombre en question sous forme de chaine de caractères qui peut prendre plusieurs formes :
     * - La chaine de caractères vide
     * - La chaine de caractère contenant seulement le caractère 0
     * - Une chaine de caractère non-vide qui contient un nombre avec comme premier chiffre autre chose que 0
     */
    private String debarassageZerosDevant(String nb){
        int i = 0;
        String res = "";
        if (nb.charAt(0) == '0'){
            while (i < nb.length() && nb.charAt(i) == '0') i++;
            for (int x = i+1; x < nb.length(); i++) res += nb.charAt(x);
        }

        if (res.length() > 0) return res;
        else return nb;
    }

    /**
     * 
     * @param indice une puissance de 10
     * @param digit1 le chiffre sur lequel on se trouve actuellement dans l'analyse du nombre
     * @param digitAvant un chiffre. Si i est l'indice de digit1 dans la chaine de caractère contenant le nombre analysé, alors digitAvant est le chiffre se trouvant à l'indice i-1
     * @param digitAvantAvant un chiffre. Si i est l'indice de digit1 dans la chaine de caractère contenant le nombre analysé, alors digitAvantAvant est le chiffre se trouvant à l'indice i-2
     * @param longueur la longueur de la chaine de caractère contenant le nombre analysé
     * @return une chaine de caractère contenant "mille" ou le -illion ou le -illiard nécessaire selon la valeur deu paramètre indice,
     *         si indice n'est pas divisible par 3, on renvoit une chaine de caractère vide
     */
    private String ajoutMilleOuIllionsOuIlliard(int indice, int digit1, int digitAvant, int digitAvantAvant, int longueur){
        if (indice == longueur - 1 ||  digit1 == 1 || digitAvant != 0 || digitAvantAvant != 0){

            if (indice == 3) return "Mille ";

            else if (indice%3 == 0 && 3 < indice && indice <= 63){
                if (digit1 == 1) return grandNombres.get(indice) + " ";
                else return grandNombres.get(indice) + "s ";
            }
        }

        return "";
    }

    
    /**
     * 
     * @param nb un nombre non nul sous forme de string avec comme premier caractère '-'
     * @return une chaine de caractère contenant le nombre mais sans le caractère '-'
     */
    private String debarassageMoins(String nb){
        String res = "";

        for (int i = 1; i < nb.length(); i++){
            res += nb.charAt(i);
        }

        return res;
    }

    /**
     * @param n un entier sous forme de chaine de caractère
     * @return le nombre en toute lettre. Cette fonction devrait pouvoit marcher pour tous les nombres entre -999 decilliards à 999 decilliards
     *         mais la plage des long sur java n'est que [-9223372036854775808;9223372036854775807] donc je suis limité, à voir si c'est reglable dans une version antérieure
     *  Convertit un nombre sen String qui contient le nombre sous forme de lettres au lieu de chiffres.
     */
    public String fromNumberToText(long n){
        String nb = n + "";
        String res = "";
        Boolean negatif = false;

        if (nb.length() > 0 && nb.charAt(0) == '-'){
            nb = debarassageMoins(nb);
            negatif = true;
        }

        nb = debarassageZerosDevant(nb);
        if(nb.compareTo("0") == 0  || nb.compareTo("") == 0) return unite.get(0);

        String resTourDeBoucle = "";
        int indice = 0;
        boolean special = false;
        char caraspec = ' ';
        boolean fin = false;
        char avant = ' ';
        char avantAvant = ' ';
        char courant;
        int digitAvantAvant=0;
        int digitAvant=0;
        int digit1 = 0;
        int digit2 = 0;
        int compteurDizaine = 1;
        int compteurCentaine = 2;
        boolean finiPourLaBoucle = false;
        boolean nb_special = false;
        boolean unPresent = false;
        boolean zeroPresent = false;

        for (int i = nb.length() - 1; i >= 0; i--){

            if (i == 0) fin = true;
            if (!fin) avant = nb.charAt(i-1);
            else avant = '0';
            if (i >= 2) avantAvant = nb.charAt(i-2);
            else avantAvant = '0';
            courant = nb.charAt(i);
            finiPourLaBoucle = false;
            nb_special = false;
            digit1 = Character.getNumericValue(courant);
            digitAvant = Character.getNumericValue(avant);
            digitAvantAvant = Character.getNumericValue(avantAvant);

            if(special){
                digit2 = Character.getNumericValue(caraspec);
                if (digit1 == 1) resTourDeBoucle += speciauxDigit.get(digit2) + " ";
                else resTourDeBoucle += dizaines.get(digit1) + "-" + speciauxDigit.get(digit2) + " ";
                special = false;
                nb_special = true;
            } 

            else if (indice%3 == 0 && i-1 >= 0 && (avant == '7' || avant == '9' || avant == '1')) {
                special = true;
                caraspec = courant;
                finiPourLaBoucle = true;
            }
            
            if (!nb_special && !finiPourLaBoucle){
                if (compteurCentaine == 0){
                    if (digit1 == 1) resTourDeBoucle += "Cent ";
                    else if (digit1 != 0) resTourDeBoucle += unite.get(digit1) + " Cent ";
                }else if ((digit1 != 0)){
                        if (compteurDizaine == 0){
                            if (unPresent && (2 <= digit1 && digit1 < 7)) {
                                resTourDeBoucle += dizaines.get(digit1) + " et ";
                                unPresent = false;
                            }
                            else {
                                if (zeroPresent) resTourDeBoucle += dizaines.get(digit1) + " "; 
                                else resTourDeBoucle += dizaines.get(digit1) + "-"; 
                                zeroPresent = false;
                            }
                        } else {
                            if (!(indice == 3 && digit1 == 1 && digitAvantAvant == 0 && digitAvant == 0)) resTourDeBoucle += unite.get(digit1) + " ";
                            if (digit1 == 1) unPresent = true;
                        }
                } 
                
                if (digit1 == 0) zeroPresent = true;
            }

            resTourDeBoucle += ajoutMilleOuIllionsOuIlliard(indice, digit1, digitAvant, digitAvantAvant, nb.length());

            indice++;
            if (resTourDeBoucle != "") res = resTourDeBoucle + res;
            resTourDeBoucle = "";
            if (compteurDizaine == 0) compteurDizaine = 3;
            if (compteurCentaine == 0) compteurCentaine = 3;
            compteurDizaine--;
            compteurCentaine--;

            
        }

        if (negatif) return "Moins " + res;
        return res;
    }

    /**
     * @return un entier au hasard entre 9223372036854775808 et 9223372036854775807
     *
     */
    public long nombreAuHasard(){
        return new Random().nextLong();
    }
}
