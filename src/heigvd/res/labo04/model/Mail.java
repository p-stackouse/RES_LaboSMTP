package heigvd.res.labo04.model;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

public class Mail {
    private Group  group;
    private String subject;
    private String data;
    private int groupSize;

    public Mail(Group group, String subject, String data){
        this.group = group;
        this.subject = subject;
        this.data = data;
        groupSize = group.getVictimRecievers().size();
    }

    public String getEmailAddressSender(){
        return group.getVictimSenders().getEmail();
    }

    public int getSizeGroup(){
        return groupSize;
    }

    public ArrayList<Person> getEmailAddressReciever(){
        return group.getVictimRecievers();
    }

    public String getData(){
        return "from :" + getEmailAddressReciever() + "\n" + "to :" + getEmailAddressReciever().toString() + "\n" +  subject + "\n" + data;
    }

    public Person getCC(){
        return group.getCc();
    }

}
