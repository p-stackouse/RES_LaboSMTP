package heigvd.res.labo04.protocol;

/**
 * This class implements main commands to use in SMTP Protcol.
 * @author Christophe Joyet, Patrick Neto
 *
 */
public final class SMTPProtocol {
    public final static int DEFAULT_PORT = 2525;

    public final static String CMD_HELLO = "EHLO";
    public final static String CMD_FROM = "MAIL FROM:";
    public final static String CMD_TO = "RCPT TO:";
    public final static String CMD_DATA = "DATA";
    public final static String CMD_END_MESSAGE = ".";
    public final static String CMD_QUIT = "quit";

    public final static String[] SUPPORTED_COMMANDS = new String[]{CMD_HELLO, CMD_FROM, CMD_TO, CMD_DATA, CMD_END_MESSAGE};

}
