package heigvd.res.labo04.model;

/**
 * This class implements a typical person
 * @author Christophe Joyet, Patrick Neto
 *
 */
class Person {
    final private String firstName;
    final private String lastName;
    private String email;

    Person(String firstname, String lastName, String email){
        this.firstName = firstname;
        this.lastName = lastName;
        setEmail(email);
    }

    public void setEmail(String firstName){
        if(email.contains("@")){
            this.email = email;
        }else{
            System.out.println("Couldn't add an email. This is not a correct email address");
        }
    }

}
