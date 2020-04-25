package sample.Classes.Utility;

import sample.Classes.Entities.Account;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;

import java.util.LinkedList;

public class LoanUtils {

    protected static LinkedList<Account> LLAccount = new LinkedList<>();
    protected static LinkedList<Customer> LLCustomer = new LinkedList<>();
    protected static LinkedList<Product> LLProduct = new LinkedList<>();

    //for displaying the name of the user
    public static String sess_firstname;
    public static String sess_lastname;

}
