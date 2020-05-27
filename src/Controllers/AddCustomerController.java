package Controllers;

import javafx.scene.image.ImageView;
import sample.Classes.Tools.MessageBox;
import sample.WindowState.Close;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML private ImageView img_qr_code;

    //
    private final LinkedList<TextField> textFields = new LinkedList<>();
    private final Loan loan = new Loan();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(LoanUtils.Action_classifier.equals("Update")){
            lbTItle.setText("Update Customer");
            btnSave.setText("Update");

            for(Customer c: LoanUtils.ObCustomer){
                if(c.getCustomer_id() == LoanUtils.getCustomer_PK()){
                    tbFirstname.setText(c.getFirstname());
                    tbLastname.setText(c.getLastname());
                    tbMobile.setText(c.getMobile());
                    tbAddress.setText(c.getAddress());
                    img_qr_code.setImage(c.getQrcode());
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
                    //change back to update after add
                    LoanUtils.Action_classifier = "Update";
                    break;
            }

            //after adding clear text fields
            Clear();
            //invoke parent method
            LoanUtils.ObCustomer.clear(); //clear the list of the OB
            super.GetCustomerData(); //then update the list and table
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
                MessageBox.ShowWarning("Supply all fields");
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
        //change back to update before exit
        LoanUtils.Action_classifier = "Update";
        Close.ThisWindow(event);
    }
}
