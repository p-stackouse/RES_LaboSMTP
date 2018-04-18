package heigvd.res.labo04.prank;
import com.sun.media.sound.InvalidDataException;
import heigvd.res.labo04.config.ConfigurationManager;

import java.io.*;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by patrickneto on 17.04.18.
 */
public class PrankGenerator {
    private ConfigurationManager configManager;
    private List<String> mails;
    private List<String> sendersEmails;
    private List<String> recieversEmails;
    private String smtpServerAddress;

    public PrankGenerator(int requiredSenders, int requiredRecievers) throws IOException, DataFormatException{
        configManager = new ConfigurationManager(true, requiredSenders, requiredRecievers);

        //Verify if there is enough senders/recievers
        if(configManager.getIsNotEnoughSenders() || configManager.getIsNotEnoughRecievers())
            throw new InvalidDataException("Not enough senders or recievers!");

        if(configManager.getIsNotEnoughMessages())
            throw new InvalidDataException("Not enough messages!");


        sendersEmails   = configManager.getSendersEmails();
        recieversEmails = configManager.getRecieversEmails();
        mails           = configManager.getMails();


    }

    public List<String> getSendersEmails() {
        return sendersEmails;
    }

    public List<String> getRecieversEmails(){
        return recieversEmails;
    }

    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }



    public String toString(){
        String output = "";
        output += "List of senders              :" + sendersEmails + "\n";
        output += "List of senders              :" + recieversEmails + "\n";
        output += "SMTP server address and port :" + configManager.getSmtpServerAddress() + ":" +
                configManager.getSmtpServerPort()  + "\n";
        output += "List of messages             :" + mails;
        return output;

    }
}
