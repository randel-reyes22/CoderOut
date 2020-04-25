package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private TableColumn<?, ?> col_prod_id;

    @FXML
    private TableColumn<?, ?> col_delete;

    @FXML
    private TableView<?> PorductTable;

    @FXML
    private TextField tbProdPrice;

    @FXML
    private TableColumn<?, ?> col_update;

    @FXML
    private Button btnSaveProduct;

    @FXML
    private TableColumn<?, ?> col_prod_price;

    @FXML
    private TextField tbProdName;

    @FXML
    private TableColumn<?, ?> col_prod_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }
}
