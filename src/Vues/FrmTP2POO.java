package Vues;

import Entities.Agent;
import Entities.Client;
import Entities.Releve;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FrmTP2POO extends JFrame{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JTable tblAgents;
    private JTable tblClients;
    private JTable tblReleves;
    private JLabel lblReleve;
    private JLabel lblClients;
    private JLabel lblAgents;
    private JLabel lblConsommation;
    private JTextField txtConsommation;
    private JTextField txtNouveauReleve;
    private JLabel lblNouveauReleve;
    private JLabel lblDate;
    private JPanel pnlDate;
    private JButton btnInserer;

    private JDateChooser dteReleve;

    // On va créer une collection qui va nous permettre
    // de stocker TOUS NOS AGENTS

    // Ici, on déclare notre collection
    ArrayList<Agent> mesAgents;

    ModelJTable mdl;
    Agent agentSelectionne;
    Client clientSelectionne;

    public FrmTP2POO()
    {
        this.setTitle("TP2 - POO");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        // Gestion de la date dans le JPANEL
        dteReleve = new JDateChooser();
        dteReleve.setDateFormatString("yyyy-MM-dd");
        pnlDate.add(dteReleve);

        // Ici, on instancie notre liste d'agents
        mesAgents = new ArrayList<>();

        // Appel de la méthode (ici, c'est une procédure) qui permet de remplir nos données
        RemplirCollection();


        // Remplir le JTABLE avec la liste des agents
        mdl = new ModelJTable();
        mdl.LoadDatasAgents(mesAgents);
        tblAgents.setModel(mdl);


        tblAgents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // Ici, on récupère le numéro de l'agent à partir de la sélection dans le JTABLE des agents
                // On sait que le n° de l'agent se trouve en 1ère colonne : donc indice = à 0
                int numAgent = Integer.parseInt( tblAgents.getValueAt(tblAgents.getSelectedRow(),0).toString());

                //JOptionPane.showMessageDialog(null, numAgent);

                // On va parcourir notre collection dans laquelle on a TOUS NOS agents
                for (Agent agt : mesAgents)
                {
                    if(agt.getIdAgent() == numAgent)
                    {
                        // On a trouvé le bon agent
                        // On va donc récupérer ses clients
                        // On va les afficher dans le JTABLE correspondant

                        mdl = new ModelJTable();
                        mdl.LoadDatasClients(agt.getLesClients());
                        tblClients.setModel(mdl);
                        agentSelectionne = agt;
                        break;
                    }
                }
            }
        });

        tblClients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int numClient = Integer.parseInt( tblClients.getValueAt(tblClients.getSelectedRow(),0).toString());
                for(Client clt : agentSelectionne.getLesClients())
                {
                    if(clt.getIdClient() == numClient)
                    {
                        clientSelectionne = clt;
                       RemplirJTableDesReleves();
                        break;
                    }
                }
            }
        });

        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Vérifier quelques sélections / saisies

                if(tblClients.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client");
                }
                else if(txtNouveauReleve.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Saisir votre nouveau relevé");
                }
                else if(dteReleve.getDate() == null)
                {
                    JOptionPane.showMessageDialog(null, "Choisir une date");
                }
                else if(clientSelectionne != null)
                {
                     if(!clientSelectionne.VerifierValeurNouveauReleve(Integer.parseInt(txtNouveauReleve.getText())))
                {
                    JOptionPane.showMessageDialog(null, "La nouvelle valeur doit être supérieure à la dernière");
                }
                else
                {
                    // TOUT EST OK
                    // Il faut donc maintenant ajouter le nouveau relevé au bon client : le client sélectionné
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String laDate = sdf.format(dteReleve.getDate());

                    Releve leNouveauReleve = new Releve(laDate,Integer.parseInt(txtNouveauReleve.getText()));

                    clientSelectionne.AjouterUnReleve(leNouveauReleve);
                    // Remettre l'interface à jour
                    txtNouveauReleve.setText("");
                    dteReleve.setDate(null);

                    // On recharge le JTABLE des relevés
                    RemplirJTableDesReleves();

                }
                }


            }
        });
    }

    public void RemplirJTableDesReleves()
    {
        mdl = new ModelJTable();
        mdl.LoadDatasReleves(clientSelectionne.getLesReleves());
        tblReleves.setModel(mdl);
        txtConsommation.setText(String.valueOf(clientSelectionne.CalculerConso()));
    }

    public void RemplirCollection()
    {
        // Jeu d'essais
        // Créer nos agents
        Agent agent1 = new Agent(1,"Enzo");
        Agent agent2 = new Agent(2,"Noa");
        Agent agent3 = new Agent(3,"Lilou");

        // Créer nos clients
        Client client1 = new Client(1, "Martin");
        Client client2 = new Client(2, "Alison");
        Client client3 = new Client(3, "Gand");
        Client client4 = new Client(4, "Muller");
        Client client5 = new Client(5, "Fortin");
        Client client6 = new Client(6, "Garnier");
        Client client7 = new Client(7, "Cousinot");

        // Créer nos relevés
        Releve releve1 = new Releve("2021-02-14",345);
        Releve releve2 = new Releve("2021-07-21",987);
        Releve releve3 = new Releve("2021-11-05",1117);
        Releve releve4 = new Releve("2021-08-15",546);
        Releve releve5 = new Releve("2021-11-05",261);
        Releve releve6 = new Releve("2022-01-03",783);
        Releve releve7 = new Releve("2021-04-13",904);
        Releve releve8 = new Releve("2021-09-03",1283);
        Releve releve9 = new Releve("2021-12-23",1846);
        Releve releve10 = new Releve("2021-02-05",371);
        Releve releve11 = new Releve("2021-06-11",613);
        Releve releve12 = new Releve("2021-10-23",1071);
        Releve releve13 = new Releve("2022-01-03",14);

        // On va ajouter à chaque client des relevés
        client1.AjouterUnReleve(releve1);
        client1.AjouterUnReleve(releve2);
        client1.AjouterUnReleve(releve3);

        client2.AjouterUnReleve(releve4);

        client3.AjouterUnReleve(releve5);
        client3.AjouterUnReleve(releve6);

        client4.AjouterUnReleve(releve7);
        client4.AjouterUnReleve(releve8);
        client4.AjouterUnReleve(releve9);

        client5.AjouterUnReleve(releve10);
        client5.AjouterUnReleve(releve11);
        client5.AjouterUnReleve(releve12);

        client6.AjouterUnReleve(releve13);

        // On va ajouter chaque client à nos agents

        agent1.AjouterUnClient(client1);
        agent1.AjouterUnClient(client2);
        agent1.AjouterUnClient(client3);

        agent2.AjouterUnClient(client4);

        agent3.AjouterUnClient(client5);
        agent3.AjouterUnClient(client6);
        agent3.AjouterUnClient(client7);

        mesAgents.add(agent1);mesAgents.add(agent2);mesAgents.add(agent3);

    }
}
