package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Classes.Entities.Product;
import sun.plugin.perf.PluginRollup;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML private TableView<Product> PorductTable;

    @FXML private TableColumn<Product, Integer> col_prod_id;

    @FXML private TableColumn<Product, String> col_prod_name;

    @FXML private TableColumn<Product, Double> col_prod_price;

    @FXML private TableColumn<Product, Void> col_delete;

    @FXML private TableColumn<Product, Void> col_update;

    @FXML private TextField tbProdName;

    @FXML private TextField tbProdPrice;

    @FXML private Button btnSaveProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void InitColumns(){
        col_prod_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        col_prod_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_prod_price.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }
}
