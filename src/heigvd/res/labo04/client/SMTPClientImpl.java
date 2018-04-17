package heigvd.res.labo04.client;

import heigvd.res.labo04.config.ConfigurationManager;
import heigvd.res.labo04.model.Mail;
import heigvd.res.labo04.prank.Prank;
import heigvd.res.labo04.prank.PrankGenerator;

import java.io.*;
import java.net.Socket;

import static heigvd.res.labo04.protocol.SMTPProtocol.*;

import static jdk.nashorn.internal.objects.Global.print;

public class SMTPClientImpl implements SMTPClient{

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

        clientSocket  = new Socket(address, port);
        br            = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        pr            = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

        System.out.print("Start Communication\n");

        String line = "";
        System.out.println(br.readLine());

        System.out.println(line);

        //EHLO
        pr.print(CMD_HELLO + " " + "something" + "\r\n");

        line = br.readLine();
        System.out.println(line);

        while (line.startsWith("250-")) {
            line = br.readLine();
            System.out.println(line);
        }

        System.out.println(line);

        //MAIL FROM
        pr.println(CMD_FROM + mail.getEmailAddressSender());
        line = br.readLine();

        System.out.println(line);

        //RCPT TO
        for(int i = 0; i < mail.getSizeGroup(); ++i)
        pr.print(CMD_TO + " " + mail.getEmailAddressReciever().get(i));
        line = br.readLine();

        System.out.println(line);

        //RCPT TO
        if(mail.getCC() != null)
        pr.println(CMD_TO + " " + mail.getCC());
        line = br.readLine();

        //DATA
        pr.println(CMD_DATA);
        line = br.readLine();

        pr.println(mail.getData());
        line = br.readLine();

        //END
        pr.println(".");
        line = br.readLine();






    }



}
