package heigvd.res.labo04.config;
import java.io.*;
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

    final private boolean IS_FAKE_SERVER;
    private String smtpServerAddress;
    private String smtpServerPort;
    private int numberOfSenders;
    private int numberOfRecievers;

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

        numberOfSenders = countNumberOfEmailAddresses(SENDERS_FILENAME);
        numberOfRecievers = countNumberOfEmailAddresses(RECIEVERS_FILENAME);

        //Verify if number of email adresses required is enough
        if(numberOfSenders < sendersRequired){
            System.out.println("Not enough senders.");
        }
        if(numberOfRecievers < recieversRequired){
            System.out.println("Not enough recievers.");
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
     * Count the number of email addresses in a file
     * @return the count of emails found
     */
    private int countNumberOfEmailAddresses(String fileName) throws IOException{
        File file = new File(PATH_TO_CONFIG + fileName);
        int linesCounter = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            //Count the number of email addresses
            while(reader.readLine() != null){
                linesCounter++;
            }

            reader.close();

            return linesCounter;
        }catch(IOException e){
            throw new IOException("Can't read file " + fileName);
        }
    }

    /**
     * Default toString
     */
    public String toString(){
        String output = "";
        output += "SMTP Server address and port: " + smtpServerAddress + ":" + smtpServerPort + "\n";
        output += "Number of senders           : " + numberOfSenders + "\n";
        output += "Number of recievers         : " + numberOfRecievers + "\n";

        return output;
    }
}
