import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Automate {
    private String[][] instructions;
    private String[][] automate;
    private int nbl=0;
    private int nbc=0;

    private int nb_instruction;
    private ArrayList<String> S_etats; //tous les etats
    private ArrayList<String> F_etats; //tous les etats finaux
    private ArrayList<String> X_alphabet; //l'alphabet

    public Automate()
    {
        Scanner sc=new Scanner(System.in);
        S_etats=new ArrayList<String>();
        F_etats=new ArrayList<String>();
        X_alphabet=new ArrayList<String>();
        System.out.println("*** CONSTRUCTION DE L'AUTOMATE NON DETERMINISTE SIMPLE ***\n");

        System.out.println("Donnez la taille de l'alphabet : \n");
        int abc=sc.nextInt();
        System.out.println("Donnez l'alphabet : \n");
        for(int i=0;i<abc;i++)
        {
            X_alphabet.add(sc.next());
        }

        System.out.println("Donnez le nombre de tous les etats : \n");
        int nb_etats=sc.nextInt();
        System.out.println("Donnez tous les etats : \n");
        for(int i=0;i<nb_etats;i++)
        {
            S_etats.add(sc.next());
        }

        System.out.println("Donnez le nombre des etats fineaux : \n");
        int nbf=sc.nextInt();
        System.out.println("Donnez tous les etats fineaux : \n");
        for(int i=0;i<nbf;i++)
        {
            F_etats.add(sc.next());
        }

        System.out.println("Donnez le nombre d'instructions :");

        int nb=sc.nextInt();
        int n=0;
        instructions=new String[nb][3];
        for(int i=0;i<nb;i++)
        {
            n=i+1;
            System.out.println("Donnez l'instruction "+n+" :\n");
            for(int j=0;j<3;j++)
            {
                instructions[i][j]=sc.next();
            }
        }
        nb_instruction=nb;

    }

    public void afficher_instru()
    {

        for(int i=0;i<nb_instruction;i++)
        {
            for(int j=0;j<3;j++)
            {
               System.out.print(instructions[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }

    public void ToDeterminist()
    {
        Scanner sc=new Scanner(System.in);

        int abc=0;
        int s=0;
        int nb1=0;
        int nb2=0;

        String alphabet[];
        String etats[];

        abc=X_alphabet.size();

        alphabet=new String[100];
        for(int i=0;i<abc;i++)
        {
            alphabet[i]=X_alphabet.get(i);
        }


        s=S_etats.size();

        etats=new String[100];
        for(int i=0;i<s;i++)
        {
            etats[i]=S_etats.get(i);
        }

        automate=new String[100][100];
        for(int i=0;i<abc+1;i++)
        {
            if(i==0)
            {
                automate[0][i]="/";
            }
            else {
                automate[0][i] = alphabet[i-1];
            }
        }

        for(int i=0;i<s+1;i++)
        {
            if(i==0)
            {
                automate[i][0]="/";
            }
            else{
                automate[i][0]=etats[i-1];
            }
        }

        for(int i=1;i<s+1;i++)
        {
            for(int j=1;j<abc+1;j++) {
                automate[i][j] = "/";
            }
        }
        nbl=s+1;
        nbc=abc+1;
        int s2=s;
        ArrayList<String> listen=new ArrayList<String>();
        for(int i=0;i<nb_instruction;i++)
        {
            nb1=rechercheTab(etats,s,instructions[i][0]);

            nb2=rechercheTab(alphabet,abc,instructions[i][1]);

            if(automate[nb1+1][nb2+1]=="/")
            {
                automate[nb1+1][nb2+1]=instructions[i][2];
            }else
            {
                automate[nb1+1][nb2+1]=automate[nb1+1][nb2+1]+","+instructions[i][2];
                //listen.add(automate[nb1+1][nb2+1]);
            }
        }
        for(int i=1;i<s+1;i++)
        {
            for(int j=1;j<abc+1;j++)
            {
                if(automate[i][j].contains(","))
                {
                    listen.add(automate[i][j]);
                    System.out.println(automate[i][j]+" ,,,,,,,\n");
                }
            }
        }

        afficher_automate();
        /** il faut ajouter les nouveaux etats **/
        ArrayList<String> liste_etats=new ArrayList<String>();
        ArrayList<String> listeseco=new ArrayList<String>();
        ArrayList<String> liste_mots=new ArrayList<String>();


        for(int i=0;i<s;i++) {
            liste_etats.add(etats[i]);
        }

        for(int i=0;i<s;i++) {
            listeseco.add(etats[i]);
        }


        String stat="";


        while(!listen.isEmpty())
        {
            stat=listen.get(0);

            automate[nbl][0]=stat;
            listeseco.add(stat);
            for(int i=1;i<abc+1;i++)
            {
                automate[nbl][i]="/";
            }

            String word="";
            Iterator<String> it=liste_etats.iterator();
            while(it.hasNext())
             {
                 word=it.next();

                if(stat.contains(word))
                {
                    nb1=rechercheTab(etats,s,word);
                    for(int i=1;i<abc+1;i++) {
                        if (automate[nbl ][i]=="/")
                        {
                            if(automate[nb1+1][i]!="/")
                            {
                             automate[nbl][i]=automate[nb1+1][i];

                            }
                        }else
                        {
                            if(automate[nb1+1][i]!="/")
                            {
                                automate[nbl][i]=automate[nbl][i]+","+automate[nb1+1][i];

                            }
                        }
                    }
                }

            }
            for(int i=1;i<abc+1;i++)
            {
                if(!listeseco.contains(automate[nbl][i]))
                {

                    listen.add(automate[nbl][i]);

                }
            }
            listen.remove(stat);

            nbl++;

        }


    }

    public int rechercheTab(String tab[],int t,String mot )
    {
        boolean bool=false;
        int i=0;
        while((!bool)&&(i<t))
        {
            if(mot.compareTo(tab[i])==0)
            {
                bool=true;
            }
            else {
                i++;
            }
        }

      return i;

    }

    public void afficher_automate()
    {
        for(int i=0;i<nbl;i++)
        {
            for(int j=0;j<nbc;j++)
            {
                System.out.print(automate[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }



}
