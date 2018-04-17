package heigvd.res.labo04.config;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class implements a configuration manager that parses and verifies config files
 * @author Christophe Joyet, Patrick Neto
 *
 */
public class ConfigurationManager {
    final private String FAKE_CONFIG_FILENAME = "mock-config.properties";
    final private String DEFAULT_CONFIG_FILENAME = "config.properties";
    final private String SENDERS_FILENAME = "senders.utf-8";
    final private String RECIEVERS_FILENAME = "recievers.utf-8";
    final private String PATH_TO_CONFIG = "./src/heigvd/res/labo04/config/";
    private final String MESSAGES_FILENAME = "prankMessages.utf-8";


    final private boolean IS_FAKE_SERVER;
    private String smtpServerAddress;
    private String smtpServerPort;
    private int numberOfSenders;
    private int numberOfRecievers;
    private int numberOfMessages;
    private ArrayList<String> sendersEmails;
    private ArrayList<String> recieversEmails;
    private ArrayList<String> mails;
    private boolean isnotEnoughSenders = false;
    private boolean isNotEnoughRecievers = false;
    private boolean isNotEnoughMessages = false;

    /**
     * Constructor of the configuration manager
     * @param isFakeServer
     * @param sendersRequired
     * @param recieversRequired
     * @throws IOException
     */
    public ConfigurationManager(boolean isFakeServer, int sendersRequired, int recieversRequired) throws IOException{
        IS_FAKE_SERVER = isFakeServer;
        String configFileName;

        //Verify if the SMTP server is fake
        if(isFakeServer){
            configFileName = FAKE_CONFIG_FILENAME;
        }else{
            configFileName = DEFAULT_CONFIG_FILENAME;
        }

        loadProperties(configFileName);

        sendersEmails = new ArrayList<String>();
        recieversEmails = new ArrayList<String>();
        mails = new ArrayList<String>();
        numberOfSenders = countNumberOfEmails(SENDERS_FILENAME, true);
        numberOfRecievers = countNumberOfEmails(RECIEVERS_FILENAME, false);
        numberOfMessages = countMessages(MESSAGES_FILENAME);

        //Verify if number of email adresses required is enough
        if(numberOfSenders < sendersRequired){
            isnotEnoughSenders = true;
        }
        if(numberOfRecievers < recieversRequired){
            isNotEnoughRecievers = true;
        }

        System.out.println("NUmber of: " + numberOfMessages);

        if(numberOfMessages < numberOfSenders){
            isNotEnoughMessages = true;
        }
    }

    /**
     * This method permits to load and parse de config file
     */
    private void loadProperties(String fileName) throws IOException {
        try {
            InputStream input = getClass().getResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(input);
            input.close();

            smtpServerAddress = properties.getProperty("smtpServer_address");
            smtpServerPort = properties.getProperty("smtpServer_port");
            numberOfSenders = Integer.parseInt(properties.getProperty("number_of_senders"));
            numberOfRecievers = Integer.parseInt(properties.getProperty("number_of_senders"));
        }
        catch(IOException ioe) {
            throw new IOException("Can't load config file.");
        }
    }

    /**
     * Count the number of email addresses in a file
     * @return the count of emails found
     */
    private int countNumberOfEmails(String fileName, boolean isSenders) throws IOException{
        File file = new File(PATH_TO_CONFIG + fileName);
        int linesCounter = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String email;

            //Count the number of email addresses
            while(true){
                email = reader.readLine();

                //if end of file
                if(email == null)
                    break;

                //Verify if file contains senders or recievers emails
                if(isSenders)
                    sendersEmails.add(email);
                else
                    recieversEmails.add(email);

                linesCounter++;
            }
            reader.close();
            return linesCounter;
        }catch(IOException e){
            throw new IOException("Can't read file " + fileName);
        }
    }

    private int countMessages(String fileName) throws IOException {
        File file = new File(PATH_TO_CONFIG + MESSAGES_FILENAME);
        int numberOfMessages = 0;
        //Read the message file
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line, message = "";

            //read every message
            while(true){
                line = reader.readLine();

                //if end of filename
                if(line == null)
                    break;

                //if end of message
                if(line.contains("==")){
                    mails.add(message);
                    numberOfMessages++;
                    message = "";
                }else{
                    message += line;
                }
            }
            reader.close();

        }catch(IOException e){
            throw new IOException("Can't read file " + MESSAGES_FILENAME);
        }
        return numberOfMessages;
    }

    /**
     * Getters
     */
    public boolean getIsNotEnoughSenders(){
        return isnotEnoughSenders;
    }
    public boolean getIsNotEnoughRecievers(){
        return isNotEnoughRecievers;
    }
    public boolean getIsNotEnoughMessages() {
        return isNotEnoughMessages;
    }
    public List<String> getSendersEmails(){
        return sendersEmails.subList(0, numberOfSenders);
    }
    public List<String> getRecieversEmails(){
        return recieversEmails.subList(0, numberOfRecievers);
    }
    public List<String> getMails(){
        return mails.subList(0, numberOfMessages);
    }

    /**
     * Gets the SMTP server address
     * @return the SMTP server address
     */
    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }

    /**
     * Gets the SMTP server port
     * @return the SMTP server port
     */
    public String getSmtpServerPort(){
        return smtpServerPort;
    }

    /**
     * Default toString
     */
    public String toString(){
        String output = "";
        output += "SMTP Server address and port : " + smtpServerAddress + ":" + smtpServerPort + "\n";
        output += "Number of senders            : " + numberOfSenders + "\n";
        output += "Number of recievers          : " + numberOfRecievers + "\n";

        return output;
    }
}
