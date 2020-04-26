package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Classes.TableReceipt;

import java.net.URL;
import java.util.ResourceBundle;

public class NewLoanController implements Initializable {

    //Utilities
    private ObservableList<TableReceipt> tableReceipts = FXCollections.observableArrayList();

    //table
    @FXML private TableView<TableReceipt> ReceiptTable;

    @FXML private TableColumn<TableReceipt, Integer> col_id;

    @FXML private TableColumn<TableReceipt, String> col_name;

    @FXML private TableColumn<TableReceipt, Double> col_price;

    @FXML private TableColumn<TableReceipt, Integer> col_qty;

    @FXML private TableColumn<TableReceipt, Double> col_total;

    @FXML private TableColumn<TableReceipt, Void> col_delete;

    //table for selection of product
    @FXML private TableView<?> ProductChooseTable;

    @FXML private TableColumn<?, ?> col_select_id;

    @FXML private TableColumn<?, ?> col_select_name;

    @FXML private TableColumn<?, ?> col_select;

    //other stuff
    @FXML private TextField tbProductname;

    @FXML private Spinner<?> prodQty;

    @FXML private TextField tbSearchProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //add the chose product and qty in the table
    @FXML
    void btnAddLoanedProduct(ActionEvent event) {

    }

    //save all added loaned products to the customer assigned
    @FXML
    void btnSave(ActionEvent event) {

    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }
}
