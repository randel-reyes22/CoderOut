package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Classes.Utility.LoanUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private AreaChart<?, ?> RevenueChart;

    @FXML private Label LoanOut;

    @FXML private Label Revenue;

    @FXML private ImageView Logout;

    @FXML private Label CurrentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sets current user name in the label form
        CurrentUser.setText(LoanUtils.sess_firstname + " " + LoanUtils.sess_lastname);
    }

    @FXML
    void OpenProducts(MouseEvent event) {
        Open.Product();
        Close.ThisWindow(event);
    }

    @FXML
    void OpenNewLoan(MouseEvent event) {
        //change the state of the action classifier
        LoanUtils.Action_classifier = "Select";

        Open.Customers(); /*open customer window to select a customer to begin with a new loan
                            then after selecting open the new loan window*/
        Close.ThisWindow(event);
    }

    @FXML
    void OpenPayment(MouseEvent event) {
        Open.Payment();
        Close.ThisWindow(event);
    }

    @FXML
    void OpenReports(MouseEvent event) {
        Open.Reports();
        Close.ThisWindow(event);
    }

    @FXML
    void OpenCustomers(MouseEvent event) {
        //change the state of the action classifier
        LoanUtils.Action_classifier = "Update";
        Open.Customers();
        Close.ThisWindow(event);
    }

    @FXML
    void Logout(MouseEvent event) {
        Open.Login();
        Close.ThisWindow(event);
    }


}
