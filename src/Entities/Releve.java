package Entities;

public class Releve
{
    private String dateReleve;
    private int valeurReleve;

    public Releve(String uneDate, int uneValeur)
    {
        dateReleve = uneDate;
        valeurReleve = uneValeur;
    }

    public int getValeurReleve()
    {
        return valeurReleve;
    }
    public String getDateReleve()
    {
        return dateReleve;
    }
}


