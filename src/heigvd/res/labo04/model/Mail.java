package heigvd.res.labo04.model;

public class Mail {

    private String to;
    private String from;
    private String data;

    public Mail(String to, String from, String data){
        this.to   = to;
        this.from = from;
        this.data = data;
    }

    public void setTo(String to){
        this.to = to;
    }


    public void setFrom(String from){
        this.from = from;
    }


    public void setData(String data){
        this.data = data;
    }
}
