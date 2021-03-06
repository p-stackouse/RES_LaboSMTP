package heigvd.res.labo04.prank;
import com.sun.media.sound.InvalidDataException;
import heigvd.res.labo04.config.ConfigurationManager;
import heigvd.res.labo04.model.Person;
import heigvd.res.labo04.model.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;

/**
 * This class implements a generator (with a configuration) of prank mails
 * @author Christophe Joyet, Patrick Neto
 */
public class PrankGenerator {
    private final String PRANK_SUBJECT = "IMPORTANT: please read!";
    private final String DEFAULT_CC_MAIL = "patricknet89@gmail.com";

    private ConfigurationManager configManager;
    private ArrayList<Prank> mails;

    public PrankGenerator(int requiredSenders, int requiredRecievers) throws IOException, DataFormatException{
        configManager = new ConfigurationManager(true, requiredSenders, requiredRecievers);

        //Verify if there is enough senders/recievers
        if(configManager.getIsNotEnoughSenders() || configManager.getIsNotEnoughRecievers())
            throw new InvalidDataException("Not enough senders or recievers!");
        if(configManager.getIsNotEnoughMessages())
            throw new InvalidDataException("Not enough messages!");

        //Generate the list of mails
        List<String> sendersEmails = configManager.getSendersEmails(requiredSenders);
        List<String> recieversEmails = configManager.getRecieversEmails(requiredRecievers);

        mails = new ArrayList<Prank>();

        for(String sender : sendersEmails){
            Group group = new Group();
            group.setSender(new Person(sender));

            for(String reciever : recieversEmails){
                group.addReciever(new Person(reciever));
            }
            group.setCc(new Person(DEFAULT_CC_MAIL));

            //Choose a random message in the list
            Random rdmMail = new Random();
            mails.add(new Prank(group, PRANK_SUBJECT, configManager.getMails().get(rdmMail.nextInt(configManager.getMails().size()))));
        }//for(String sender : sendersEmails)
    }

    /**
     * Getters
     */
    public String getSmtpServerAddress(){
        return configManager.getSmtpServerAddress();
    }
    public String getSmtpServerPort(){
        return configManager.getSmtpServerPort();
    }
    public ArrayList<Prank> getMails(){ return mails; }
}
