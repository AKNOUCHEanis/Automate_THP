import java.io.*;

public class Programme {
    public static void main(String Args[]) throws IOException {
        Automate automate = new Automate();
        FichierText fichier=new FichierText();

       // fichier.genererFichier(automate.getInstructions(),automate.getNbInstruction(),automate.getF_etats(),automate.getS0());

        automate.afficher_instru();
      //  automate.ToDeterminist();
      //  automate.afficher_automate();
        automate.reconnaitreMot();
       // fichier.genererFichier(automate.getInstructions(),automate.getNbInstruction(),automate.getF_etats(),automate.getS0());


    }
}
