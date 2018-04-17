package heigvd.res.labo04.model;

public class Person {

    private String name;
    private String vorName;

    public Person(String name, String vorName){
        this.name = name;
        this.vorName = vorName;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVorName(String vorName){
        this.vorName = vorName;
    }
}
