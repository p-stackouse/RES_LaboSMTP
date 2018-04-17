package heigvd.res.labo04.model;

import java.util.ArrayList;

public class Group {

    private ArrayList<Person> victimRecievers;
    private Person victimSenders;
    private Person cc;

    Group(ArrayList<Person> victimReciever, Person victimSender, Person cc){
        for(int i = 0; i < victimReciever.size(); ++i)
            this.victimRecievers.add(victimReciever.get(i));

        this.victimSenders = victimSender;
        this.cc = cc;
    }

    void addVictim(Person victim){
        victimRecievers.add(victim);
    }

    ArrayList<Person> getVictimRecievers(){
        return victimRecievers;
    }

    Person getVictimSenders(){
        return victimSenders;
    }

    Person getCc(){
        return cc;
    }
}
