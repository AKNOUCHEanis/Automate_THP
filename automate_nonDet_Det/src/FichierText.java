import java.io.*;

public class FichierText {


    public void genererFichier() throws IOException {

        PrintWriter f=null;
        FileWriter fichier=new FileWriter("fichier.txt");
        f=new PrintWriter(fichier);

        f.println("zzzzzzzzzz");

        f.close();

        }
}
