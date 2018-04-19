package heigvd.res.labo04.model;

import java.util.ArrayList;

public class Group {

    private Person sender;
    private ArrayList<Person> recievers;
    private Person cc;

    public Group(){
        sender = new Person();
        recievers = new ArrayList<Person>();
        cc = new Person();
    }

    public Group( Person victimSender, Person cc, Person ... victimReciever){
        this();

        for(int i = 0; i < victimReciever.length; ++i)
            this.recievers.add(victimReciever[i]);

        this.sender = victimSender;
        this.cc = cc;
    }


    public ArrayList<Person> getRecievers(){
        return recievers;
    }
    public Person getSender(){ return sender; }
    public Person getCc(){
        return cc;
    }

    public void setSender(Person sender){
        if(sender != null){
            this.sender = sender;
        }
    }
    public void setCc(Person cc){
        if(cc != null){
            this.cc = cc;
        }
    }

    public void addReciever(Person reciever){
        if(reciever != null){
            recievers.add(reciever);
        }
    }

    public String toString(){
        return sender + " " + recievers + " " + cc;
    }
}
