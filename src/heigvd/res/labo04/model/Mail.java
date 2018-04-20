package heigvd.res.labo04.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * This class implements a simple mail (group of sender/recievers, subject, data)
 * @author Christophe Joyet, Patrick Neto
 */
public class Mail{
    private Group  group = new Group();
    private String subject;
    private String data;

    public Mail(){}

    public Mail(Group group, String subject, String data) throws IOException, DataFormatException {
        this.group   = group;
        this.subject = subject;
        this.data    = data;
    }

    public String getEmailAddressSender(){
        return group.getSender().getEmail();
    }
    public String getCC(){
        return group.getCc().getEmail();
    }

    public ArrayList<Person> getEmailAddressReciever(){
        return group.getRecievers();
    }

    public String getData(){
        String emailReciever = "";
        for(int i = 0; i < group.getRecievers().size(); ++i){
            emailReciever += group.getRecievers().get(i).getEmail();
            if(i != group.getRecievers().size() - 1)
                emailReciever += ", ";
        }
        return  "from : " + getEmailAddressSender() + "\n" +
                "Cc : " + getCC() + "\n" +
                "to : " + emailReciever + "\n" +
                "subject : " + subject + "\n\n" + data;
    }

    public int getNumberOfRecievers(){
        return group.getRecievers().size();
    }
}
