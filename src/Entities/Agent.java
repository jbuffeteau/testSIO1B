package Entities;

import java.util.ArrayList;

public class Agent
{
    private int idAgent;
    private String nomAgent;
    private ArrayList<Client> lesClients;

    public Agent(int idAgent, String nomAgent) {
        this.idAgent = idAgent;
        this.nomAgent = nomAgent;
        this.lesClients = new ArrayList<>();
    }

    public int getIdAgent() {
        return idAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public ArrayList<Client> getLesClients() {
        return lesClients;
    }

    public void AjouterUnClient(Client unClient)
    {
        lesClients.add(unClient);
    }
}


