package heigvd.res.labo04;
import heigvd.res.labo04.client.SMTPClientImpl;
import heigvd.res.labo04.config.ConfigurationManager;
import heigvd.res.labo04.model.Group;
import heigvd.res.labo04.model.Mail;
import heigvd.res.labo04.model.Person;
import heigvd.res.labo04.prank.PrankGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class MailPranker {
    public static void main(String[] args) throws IOException, DataFormatException {
        Person p1 = new Person("oawhd", "aodawdo", "oaid@gmail.com");
        Person p2 = new Person("qwer", "ycvyxcv", "yxcv@gmail.com");
        Person p3 = new Person("sadfa", "yxc", "hjkh@gmail.com");
        ArrayList<Person> vicitm = new ArrayList<Person>();
        vicitm.add(p1);
        vicitm.add(p2);
        Group group = new Group(p3, p3, p1, p2);
        Mail mail = new Mail(group, "VATEFAIRE", "Bonjour cher maitre, je vous emmerde");

        SMTPClientImpl smtpClient = new SMTPClientImpl(2525, "localhost");

        try{
            smtpClient.sendMail(mail);
        }catch (IOException e){
            e.getStackTrace();
        }


    }
}