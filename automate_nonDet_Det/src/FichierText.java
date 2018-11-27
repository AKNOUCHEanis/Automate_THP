import javax.print.attribute.DateTimeSyntax;
import java.io.*;
import java.util.Date;


public class FichierText {
    private Date date;

    public void genererFichier(String instructions[][],int nbl) throws IOException {
        date= new Date();
        PrintWriter f=null;
        FileWriter fichier=new FileWriter("fichier_"+date.getTime()+".txt");
        f=new PrintWriter(fichier);
        String m1="";
        String m2="";
        f.println("Digraph G {");
        for(int i=0;i<nbl;i++)
        {
            m1=instructions[i][0];
            m2=instructions[i][2];
            if(m1.contains(","))
            {
                m1="\""+m1+"\"";
            }
            if(m2.contains(","))
            {
                m2="\""+m2+"\"";
            }

            f.println(m1+" -> "+m2+" [label="+instructions[i][1]+"];");
        }

        f.println("}");

        f.close();

        }
}
