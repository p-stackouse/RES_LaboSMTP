package heigvd.res.labo04.model;
import com.sun.javaws.exceptions.InvalidArgumentException;
import heigvd.res.labo04.prank.PrankGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.DataFormatException;

public class Mail {
    private Group  group;
    private PrankGenerator prankG;
    private String subject;
    private String data;
    private int groupSize;
    private Random sendersAndReciversGenerator = new Random();


    public Mail(Group group, String subject, String data) throws IOException, DataFormatException {
        this.group = group;
        this.subject = subject;
        this.data = data;
        groupSize = group.getVictimRecievers().size();
        prankG = new PrankGenerator(sendersAndReciversGenerator.nextInt(3),sendersAndReciversGenerator.nextInt(3));

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
        String emailReciever = "";
        for(int i = 0; i < group.getVictimRecievers().size(); ++i){
            emailReciever = emailReciever + " " + group.getVictimRecievers().get(i).getEmail();
        }
        return  "from : " + getEmailAddressSender() + "\n" +
                "Cc   : " + getCC().getEmail() + "\n" +
                "to   : " + emailReciever + "\n" +
                "subject : " + subject + "\n\n" + data;
    }

    public Person getCC(){
        return group.getCc();
    }

}
