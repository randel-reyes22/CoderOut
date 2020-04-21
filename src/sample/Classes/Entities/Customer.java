package sample.Classes.Entities;

public class Customer extends Name {

    private String mobile;
    private String address;
    private double balance;

    public Customer(){
        //empty constructor
    }

    //for creation and updating the customer details
    public Customer(String firstname, String lastname, String mobile, String address) {
        super(firstname, lastname);
        this.mobile = mobile;
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
