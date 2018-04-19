package heigvd.res.labo04.model;

public class Person {

    private String firstName;
    private String lastName;
    private String email;

    public Person(){}

    public Person(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String email){
        this.firstName = "";
        this.lastName = "";
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){return email;}

    public String toString(){
        String output = "";
        output += "Firstname : " + firstName + "\n";
        output += "Lastname  : " + lastName  + "\n";
        output += "Email     : " + email     + "\n";

        return output;
    }
}
