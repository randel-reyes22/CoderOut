package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.Entities.Customer;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AddCustomerController extends CustomersController implements Initializable {

    @FXML private TextField tbFirstname;

    @FXML private TextField tbLastname;

    @FXML private TextField tbMobile;

    @FXML private TextField tbAddress;

    @FXML private Button btnSave;

    @FXML private Label lbTItle;

    //
    private LinkedList<TextField> textFields = new LinkedList<>();
    private Loan loan = new Loan();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(LoanUtils.Action_classifier.equals("Update")){
            lbTItle.setText("Update Customer");
            btnSave.setText("Update");

            for(Customer c: ObCustomer){
                if(c.getCustomer_id() == LoanUtils.getCustomer_PK()){
                    tbFirstname.setText(c.getFirstname());
                    tbLastname.setText(c.getLastname());
                    tbMobile.setText(c.getMobile());
                    tbAddress.setText(c.getAddress());
                    return;
                }
            }
        }
    }

    //save the new customer
    @FXML
    void SaveCustomer(MouseEvent event) {
        if(!CheckEmptyFields()){
            LoanUtils.LLCustomer.addFirst(new Customer(tbFirstname.getText(), tbLastname.getText(),
                    tbMobile.getText(), tbAddress.getText()));

            //invoke method from loan class
            switch (LoanUtils.Action_classifier)
            {
                case "Update":
                    loan.UpdateCustomerAccount();
                    break;
                case "Add":
                    loan.AddCustomerAccount();
                    break;
            }

            //after adding clear text fields
            Clear();
            //invoke parent method
            ObCustomer.clear(); //clear the list of the OB
            super.GetCustomerData(); //then update the list and table
            super.removeListCustomer(); //remove selection in filtered and sorted list
            Close.ThisWindow(event); //close this window after
        }
    }

    private boolean CheckEmptyFields(){
        textFields.add(tbFirstname);
        textFields.add(tbLastname);
        textFields.add(tbMobile);
        textFields.add(tbAddress);

        for (TextField t : textFields){
            if(t.getText().isEmpty()){
                return true;
            }
        }

        return false;
    }

    private void Clear(){
        for(TextField t: textFields){
            t.clear();
        }
    }

    @FXML
    void GoBack(MouseEvent event) {
        Close.ThisWindow(event);
    }
}
