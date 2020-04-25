package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @FXML private TextField tbSearchCutomer;

    @FXML private TableView<?> CustomerTable;

    @FXML private TableColumn<?, ?> col_id;

    @FXML private TableColumn<?, ?> col_firstname;

    @FXML private TableColumn<?, ?> col_lastname;

    @FXML private TableColumn<?, ?> col_mobile;

    @FXML private TableColumn<?, ?> col_address;

    @FXML private TableColumn<?, ?> col_balance;

    @FXML private TableColumn<?, ?> Update;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }

}
