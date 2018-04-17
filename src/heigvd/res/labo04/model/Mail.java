package heigvd.res.labo04.model;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

public class Mail {
    private ArrayList<String> to;
    private String from;
    private ArrayList<String> cc;
    private String subject;
    private String data;

    public Mail(String from, String data, String subject, String... to){
        //Verify if parameter isn't a nullpointer
        if(to != null){
            for(String reciever: to)
                setTo(reciever);

        }else {
            throw new NullPointerException("Error in Mail construction: no reciever!");
        }

        setData(data);
        setSubject(subject);
    }

    public void setTo(String to){
        if(to != "")
            this.to.add(to);
    }

    public void setFrom(String from){
        if(from != "")
            this.from = from;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }
}
