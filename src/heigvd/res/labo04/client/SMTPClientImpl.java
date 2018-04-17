package heigvd.res.labo04.client;

import heigvd.res.labo04.config.ConfigurationManager;
import heigvd.res.labo04.model.Mail;

import java.io.*;
import java.net.Socket;

import static jdk.nashorn.internal.objects.Global.print;

public class SMTPClientImpl implements SMTPClient{

    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter    pr;
    private ConfigurationManager configuration;

    public void sendMail(Mail mail, ConfigurationManager config) throws IOException{

        //creation of serveur
        clientSocket  = new Socket(configuration.getSmtpServerAddress(), configuration.getSmtpServerPort());
        br            = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pr            = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        configuration = config;

        System.out.print("Start Communication\n");

        String line = "";
        System.out.print(br.readLine());


        //EHLO
        print("EHLO " + "something");
        line = br.readLine();







    }



}