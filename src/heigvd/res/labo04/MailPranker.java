package heigvd.res.labo04;
import heigvd.res.labo04.client.SMTPClientImpl;
import heigvd.res.labo04.prank.Prank;
import heigvd.res.labo04.prank.PrankGenerator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 * This main class implements a basic program, generating mail pranks
 * @author Christophe Joyet, Patrick Neto
 */
public class MailPranker {
    public static void main(String[] args) throws IOException, DataFormatException {
        Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Starting application...");

        //Check the presence of two arguments (number of senders and receievers)
        if(args.length == 2){
            PrankGenerator prankGenerator = new PrankGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            SMTPClientImpl smtpClient = new SMTPClientImpl(Integer.parseInt(prankGenerator.getSmtpServerPort()), prankGenerator.getSmtpServerAddress());

            //Send every email of the prank list
            try {
                for (Prank mail : prankGenerator.getMails())
                    smtpClient.sendMail(mail);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        else{
            System.out.println("2 arguments needed (" + args.length + " given)");
        }
    }
}