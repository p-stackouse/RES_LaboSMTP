package heigvd.res.labo04.model;

import java.util.ArrayList;

public class Group {

    ArrayList<Person> victimReciever;
    Person            victimSender;

    public Group(ArrayList<Person> victimReciever, Person victimSender){
        for(int i = 0; i < victimReciever.size(); ++i)
            this.victimReciever.add(victimReciever.get(i));

        this.victimSender = victimSender;
    }

    public void addVictim(Person victim){
        victimReciever.add(victim);
    }


}
