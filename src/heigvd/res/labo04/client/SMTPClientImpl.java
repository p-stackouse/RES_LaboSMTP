package heigvd.res.labo04.client;
import heigvd.res.labo04.model.Mail;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static heigvd.res.labo04.protocol.SMTPProtocol.*;

/**
 * This interface lists the minimal methods to implement in a SMTP client
 * @author Christophe Joyet, Patrick Neto
 */
public class SMTPClientImpl implements SMTPClient {
    private static final String NEW_LINE = "\r";
    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter    pr;
    private int port;
    private String address;

    public SMTPClientImpl(int port, String address){
        this.port = port;
        this.address = address;
    }

    public void sendMail(Mail mail) throws IOException{
        Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining socket with address: "
                + address + ":" + port);
        clientSocket  = new Socket(address, port);
        try{
            pr = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Starting connection...");
            System.out.println(br.readLine());

            String line = "";

            //EHLO
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Sending EHLO...");
            pr.println(CMD_HELLO + " " + "Hello" + NEW_LINE);
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response EHLO ");
            line = br.readLine();
            System.out.println(line);

            while (line.startsWith("250-")) {
                line = br.readLine();
                System.out.println(line);
            }

            //MAIL FROM
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Sending FROM");
            pr.println(CMD_FROM + "<" + mail.getEmailAddressSender() + ">" + NEW_LINE);
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response FROM " +
                                                                 mail.getEmailAddressSender());
            line = br.readLine();
            System.out.println(line);

            //RCPT TO
            for (int i = 0; i < mail.getNumberOfRecievers(); ++i) {
                Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Sending TO");
                pr.println(CMD_TO + "<" + mail.getEmailAddressReciever().get(i).getEmail() + ">" + NEW_LINE);
                Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response " + CMD_TO + mail.getEmailAddressReciever().get(i).getEmail());
                line = br.readLine();
                System.out.println(line);
            }

            //RCPT TO (Cc)
            if (mail.getCC() != null) {
                pr.println(CMD_TO + "<" + mail.getCC() + ">" + NEW_LINE);
                Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response " + CMD_TO + mail.getCC());
            }
            line = br.readLine();
            System.out.println(line);

            //DATA
            pr.println(CMD_DATA + NEW_LINE);
            pr.flush();
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response " + CMD_DATA +"...");
            line = br.readLine();
            System.out.println(line);
            pr.println(mail.getData() + NEW_LINE);

            //END
            pr.println(CMD_END_MESSAGE + NEW_LINE);
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response " +
                                                                 CMD_END_MESSAGE);
            line = br.readLine();
            System.out.println(line);

            //QUIT
            pr.println(CMD_QUIT + NEW_LINE);
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining server response QUIT");
            line = br.readLine();
            System.out.println(line);
        }catch(Exception ex){
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.SEVERE, "There's wrong happened...");
        }

    }



}
