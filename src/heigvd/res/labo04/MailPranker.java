package heigvd.res.labo04;
import heigvd.res.labo04.client.SMTPClientImpl;
import heigvd.res.labo04.prank.Prank;
import heigvd.res.labo04.prank.PrankGenerator;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class MailPranker {
    public static void main(String[] args) throws IOException, DataFormatException {
        PrankGenerator prankGenerator = new PrankGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        SMTPClientImpl smtpClient = new SMTPClientImpl(Integer.parseInt(prankGenerator.getSmtpServerPort()), "localhost");

        try{
            for(Prank mail : prankGenerator.getMails())
                smtpClient.sendMail(mail);
        }catch (IOException e){
            e.getStackTrace();
        }


    }
}