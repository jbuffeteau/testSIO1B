package Entities;

import java.util.ArrayList;

public class Client
{
    private int idClient;
    private String nomClient;
    private ArrayList<Releve> lesReleves;

    public Client(int idClient, String nomClient)
    {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.lesReleves = new ArrayList<>();
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public ArrayList<Releve> getLesReleves() {
        return lesReleves;
    }

    public void AjouterUnReleve(Releve unReleve)
    {
        lesReleves.add(unReleve);
    }

    public int CalculerConso()
    {
        int conso = 0;
        if(lesReleves.size() == 1)
        {
            conso = lesReleves.get(0).getValeurReleve();
        }
        else if(lesReleves.size() > 1)
        {
            conso = lesReleves.get(lesReleves.size()-1).getValeurReleve() - lesReleves.get(lesReleves.size()-2).getValeurReleve();
        }
        return conso;
    }

    public boolean VerifierValeurNouveauReleve(int nouvelleValeurDuReleve)
    {
        boolean ok = false;

        if(lesReleves.size() >= 1)
        {
            if(nouvelleValeurDuReleve > lesReleves.get(lesReleves.size() - 1).getValeurReleve())
            {
                ok = true;
            }
        }
        else if(nouvelleValeurDuReleve > 0)
        {
            ok = true;
        }

        return ok;
    }
}




