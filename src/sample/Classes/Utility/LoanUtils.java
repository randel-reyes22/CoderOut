package sample.Classes.Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import sample.Classes.Entities.Account;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;
import sample.Classes.TableReceipt;

import java.util.LinkedList;

public class LoanUtils {

    //Observable list for the table and displaying
    public static ObservableList<TableReceipt> ObTableReceipt = FXCollections.observableArrayList();
    public static ObservableList<Product> ObProduct = FXCollections.observableArrayList();
    public static ObservableList<Customer> ObCustomer = FXCollections.observableArrayList();

    //linked list for updating and adding
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

    /*this is for classifying if customer
    will be selected or to be updated*/
    public static String Action_classifier;

    //detect if new loan to add product window is open
    public static String Loan_to_product_detect = "NOTACTIVE";

}
