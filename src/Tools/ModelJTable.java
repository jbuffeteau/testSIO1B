package Tools;

import Entities.Agent;
import Entities.Client;
import Entities.Releve;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    // Cette classe va nous permettre de remplir n'importe quel JTABLE

    private String[] entete; // sert pour les colonnes
    private Object[][] lignes; // sert pour les lignes

    @Override
    public int getRowCount() {
        return lignes.length;
    }

    @Override
    public int getColumnCount() {
        return entete.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignes[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return entete[columnIndex];
    }

    // Classe : plan de construction : squelette
    // Objet : instance d'une classe
    // Méthodes : soit une fonction ; soit une procédure
    // une fonction : elle retourne TOUJOURS une valeur : on indique le type de ce qu'elle retourne
    // une procédure ne retourne RIEN : void

    public void LoadDatasAgents(ArrayList<Agent> desAgents)
    {
        entete = new String[]{"Numéro","Nom"};
        lignes = new Object[desAgents.size()][2];
        int i = 0;
        // Parcourir la collection desAgents
        // afin de remplir le tableau des lignes

        for(Agent agt : desAgents)
        {
            lignes[i][0] = agt.getIdAgent();
            lignes[i][1] = agt.getNomAgent();
            i++;
        }
        // Permet de rafraichir l'interface graphique
        fireTableChanged(null);
    }

    public void LoadDatasClients(ArrayList<Client> desClients)
    {
        entete = new String[]{"Numéro","Nom"};
        lignes = new Object[desClients.size()][2];
        int i = 0;
        for(Client clt : desClients)
        {
            lignes[i][0] = clt.getIdClient();
            lignes[i][1] = clt.getNomClient();
            i++;
        }
        fireTableChanged(null);
    }
    public void LoadDatasReleves(ArrayList<Releve> desReleves)
    {
        entete = new String[]{"Date","Valeur"};
        lignes = new Object[desReleves.size()][2];
        int i = 0;
        for(Releve rel : desReleves)
        {
            lignes[i][0] = rel.getDateReleve();
            lignes[i][1] = rel.getValeurReleve();
            i++;
        }
        fireTableChanged(null);
    }
}
