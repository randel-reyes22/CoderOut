package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.Entities.Customer;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class TermConditionController extends CustomersController implements Initializable {

    @FXML private TextField tbCustomerName;

    @FXML private TextField tbModeOfPayment;

    @FXML private TextField tbTerm;

    @FXML private DatePicker DueDate;

    private Loan loan = new Loan();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //display the name of the customer
        for(Customer customer: ObCustomer){
            if(customer.getCustomer_id() == LoanUtils.getCustomer_PK()){
                tbCustomerName.setText(customer.getFirstname() + " " + customer.getLastname());
                return;
            }
        }
    }

    @FXML
    void btnSave(MouseEvent event) {
        loan.AddLoan(tbModeOfPayment.getText(), tbTerm.getText(), DueDate.toString());
    }
}
