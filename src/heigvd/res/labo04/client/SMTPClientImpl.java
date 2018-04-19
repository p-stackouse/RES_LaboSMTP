package heigvd.res.labo04.client;
import heigvd.res.labo04.model.Mail;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static heigvd.res.labo04.protocol.SMTPProtocol.*;

public class SMTPClientImpl implements SMTPClient {
    private static final String NEW_LINE = "\r";
    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter    pr;
    private int          port;
    private String       address;


    public SMTPClientImpl(int port, String address){
        this.port = port;
        this.address = address;
    }


    public void sendMail(Mail mail) throws IOException{
        Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Starting sending mail..");
        clientSocket  = new Socket(address, port);
        Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.INFO, "Obtaining socket...");
        try{
            pr = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            System.out.print("Start Communication\n");

            System.out.println(br.readLine());
            pr.flush();

            String line = "";

            //EHLO
            pr.println(CMD_HELLO + " " + "localhost" + NEW_LINE);
            line = br.readLine();
            pr.flush();

            while (line.startsWith("250-")) {
                line = br.readLine();
                System.out.println(line);
            }

            //MAIL FROM
            pr.println(CMD_FROM + "<" + mail.getEmailAddressSender() + ">" + NEW_LINE);
            line = br.readLine();
            pr.flush();

            //RCPT TO
            for (int i = 0; i < mail.getSizeGroup(); ++i) {
                pr.println(CMD_TO + "<" + mail.getEmailAddressReciever().get(i).getEmail() + ">" + NEW_LINE);
                line = br.readLine();
                pr.flush();
            }

            //RCPT TO
            if (mail.getCC() != null)
                pr.println(CMD_TO + "<" + mail.getCC() + ">" + NEW_LINE);
            line = br.readLine();
            pr.flush();

            //DATA
            pr.println(CMD_DATA + NEW_LINE);
            pr.flush();
            line = br.readLine();

            pr.println(mail.getData() + NEW_LINE);
            pr.flush();

            //END
            pr.println(CMD_END_MESSAGE + NEW_LINE);
            line = br.readLine();
            pr.flush();

            //QUIT
            pr.println(CMD_QUIT + NEW_LINE);
            line = br.readLine();
            pr.flush();
        }catch(Exception ex){
            Logger.getLogger(SMTPClientImpl.class.getName()).log(Level.SEVERE, ex.getMessage());
        }

    }



}
