package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Classes.Entities.Customer;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;
import sample.Classes.PayOut;

import javax.swing.*;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class MakePaymentController implements Initializable {

    @FXML private TextField tbCustomerName;

    @FXML private TextField tbAmountPay;

    @FXML private DatePicker DateGiven;

    //classes
    private final PayOut payOut = new PayOut();
    private final Loan loan = new Loan();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //display the name of the customer
        for(Customer c: LoanUtils.ObCustomer){
            if(c.getCustomer_id() == LoanUtils.getCustomer_PK()){
                tbCustomerName.setText(c.getFirstname() + " " + c.getLastname());
                return;
            }
        }
    }

    private double CheckAmount(){
        try{
            return Double.parseDouble(tbAmountPay.getText());
        }
        catch (InputMismatchException | NumberFormatException ex){
            return -1;
        }
    }

    @FXML
    void MakePay(ActionEvent event) {
        double amount = CheckAmount();

        if(amount > 0 && !String.valueOf(DateGiven.getValue()).isEmpty()){
            //invoke the make payment method
            payOut.Makepayment(String.valueOf(DateGiven.getValue()),amount);

            //invoke ob to refresh the customer data
            loan.GetCustomers();

            //close this window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }else{
            JOptionPane.showMessageDialog(null, "Please input a valid amount or date.");
        }
    }
}
