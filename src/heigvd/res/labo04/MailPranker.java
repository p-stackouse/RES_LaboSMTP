package heigvd.res.labo04;
import heigvd.res.labo04.config.ConfigurationManager;

import java.io.IOException;

public class MailPranker {
    public static void main(String[] args) {
        boolean isFakeServer = false;

        try {
            ConfigurationManager configurationManager = new ConfigurationManager(!isFakeServer, 3, 4);
            System.out.println(configurationManager);
        }catch(IOException e){
            e.printStackTrace();;
        }
    }
}
