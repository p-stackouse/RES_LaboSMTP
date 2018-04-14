package heigvd.res.labo04.client;
import heigvd.res.labo04.model.Mail;

/**
 * This interface lists the minimal methods to implement in a SMTP client
 * @author Christophe Joyet, Patrick Neto
 *
 */
public interface SMTPClient {
    public void sendMail(Mail mail);
}
