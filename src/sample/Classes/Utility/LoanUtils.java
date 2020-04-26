package sample.Classes.Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import sample.Classes.Entities.Account;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;

import java.util.LinkedList;

public class LoanUtils {

    //sorted and filtered list

    //linked list
    public static LinkedList<Account> LLAccount = new LinkedList<>();
    public static LinkedList<Customer> LLCustomer = new LinkedList<>();
    public static LinkedList<Product> LLProduct = new LinkedList<>();

    //for displaying the name of the user
    public static String sess_firstname;
    public static String sess_lastname;

    //primary keys
    protected static int Customer_PK;
    protected static int Product_PK;

    public static int getCustomer_PK() {
        return Customer_PK;
    }

    public static int getProduct_PK() {
        return Product_PK;
    }

    public static void setCustomer_PK(int customer_PK) {
        Customer_PK = customer_PK;
    }

    public static void setProduct_PK(int product_PK) {
        Product_PK = product_PK;
    }

    //Action classifier
    /*this is for classifying if customer
    will be selected or to be updated*/
    public static String Action_classifier;
}
