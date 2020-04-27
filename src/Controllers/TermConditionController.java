package Controllers;

import sample.WindowState.Close;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.Entities.Customer;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;

import javax.swing.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TermConditionController extends NewLoanController implements Initializable {

    @FXML private TextField tbCustomerName;

    @FXML private TextField tbModeOfPayment;

    @FXML private TextField tbTerm;

    @FXML private DatePicker DueDate;

    //classes
    private final Loan loan = new Loan();
    private final double Total = totalAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Term " + Total);
        //display the name of the customer
        for(Customer customer: LoanUtils.ObCustomer){
            if(customer.getCustomer_id() == LoanUtils.getCustomer_PK()){
                tbCustomerName.setText(customer.getFirstname() + " " + customer.getLastname());
                return;
            }
        }
    }

    @FXML
    void btnSave(MouseEvent event) {

        if(!CheckEmptyFields() && !String.valueOf(DueDate.getValue()).isEmpty()) {
            boolean stats = loan.AddLoan(Total ,tbModeOfPayment.getText(), tbTerm.getText(), String.valueOf(DueDate.getValue()));
            if (stats) {
                Close.ThisWindow(event);
            }
        }
    }

    private boolean CheckEmptyFields(){
        LinkedList<TextField> textFields = new LinkedList<>();
        textFields.add(tbCustomerName);
        textFields.add(tbModeOfPayment);
        textFields.add(tbTerm);

        for(TextField t: textFields){
            if(t.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Supply all fields");
                return true;
            }
        }
        return false;
    }
}
