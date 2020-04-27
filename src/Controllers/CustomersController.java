package Controllers;

import WindowState.Close;
import WindowState.Open;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;

import javax.lang.model.element.NestingKind;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @FXML private TextField tbSearchCutomer;

    @FXML private Label lbCaption;

    @FXML private TableView<Customer> CustomerTable;

    @FXML private TableColumn<Customer, Integer> col_id;

    @FXML private TableColumn<Customer, String> col_firstname;

    @FXML private TableColumn<Customer, String> col_lastname;

    @FXML private TableColumn<Customer, String> col_mobile;

    @FXML private TableColumn<Customer, String> col_address;

    @FXML private TableColumn<Customer, Double> col_balance;

    @FXML private TableColumn<Customer, Void> col_update;

    //for
    private final Loan loan = new Loan();
    protected FilteredList<Customer> filteredListCustomer = new FilteredList<>(LoanUtils.ObCustomer, p -> true);
    protected SortedList<Customer> sortedListCustomer = new SortedList<>(filteredListCustomer);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(LoanUtils.Action_classifier.equals("Select")){
            lbCaption.setText("Select customer");
        }

        InitColumns();
        GetCustomerData();
        searchCustomer();
    }

    protected void InitColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    protected void GetCustomerData(){
        //load the customers in the observable list
        loan.GetCustomers();
        CustomerTable.setItems(LoanUtils.ObCustomer);
        ActionButton(); //button
    }

    private void ActionButton(){
        String btnText = null;
        if(LoanUtils.Action_classifier.equals("Select"))
            btnText = "Select";
        else
            btnText = "Update";

        String finalBtnText = btnText;

        Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>> cellFactory = (TableColumn<Customer, Void> param) -> {
            TableCell<Customer, Void> cell = new TableCell<Customer, Void>() {

                private final Button btnAction = new Button(finalBtnText);
                {
                    btnAction.setOnAction((ActionEvent event) -> {
                        Customer customer = getTableView().getItems().get(getIndex());

                        //get the customer id for further execution
                        LoanUtils.setCustomer_PK(customer.getCustomer_id());

                        switch (finalBtnText)
                        {
                            case "Update":
                                try {
                                    //open add customer window to update
                                   Open.AddCustomer();
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Select":
                                try {
                                    //open new loan window to add loan
                                    Open.NewLoan();
                                    //hide this window
                                    ((Node)(event.getSource())).getScene().getWindow().hide();
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnAction);
                    }
                }
            };
            return cell;
        };

        col_update.setCellFactory(cellFactory);
    }

    protected void removeListCustomer(){
        int selectedItem = CustomerTable.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            CustomerTable.getItems().remove(selectedItem);
        }
    }

    protected void searchCustomer() {

        tbSearchCutomer.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListCustomer.setPredicate(customer -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getMobile().contains(lowerCaseFilter)) {
                    return true;
                }else if(customer.getAddress().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else return String.valueOf(customer.getBalance()).contains(lowerCaseFilter);
            });
        });

        sortedListCustomer.comparatorProperty().bind(CustomerTable.comparatorProperty());
        CustomerTable.setItems(sortedListCustomer);
    }

    @FXML
    void OpenAddCustomer(MouseEvent event) {
        LoanUtils.Action_classifier = "Add";
        Open.AddCustomer();
    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }

}
