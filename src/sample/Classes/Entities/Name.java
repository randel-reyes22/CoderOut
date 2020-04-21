package sample.Classes.Entities;

public class Name {

    private String firstname;
    private String lastname;

    public Name(){
        //empty constructor
    }

    //for updating and adding
    public Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    //accessors and mutators
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
