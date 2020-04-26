package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;
import sample.Classes.Loan;
import sample.Classes.Utility.LoanUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML private TableView<Product> ProductTable;

    @FXML private TableColumn<Product, Integer> col_prod_id;

    @FXML private TableColumn<Product, String> col_prod_name;

    @FXML private TableColumn<Product, Double> col_prod_price;

    @FXML private TableColumn<Product, String> col_prod_unit;

    @FXML private TableColumn<Product, Void> col_delete;

    @FXML private TableColumn<Product, Void> col_update;

    @FXML private TextField tbProdName;

    @FXML private TextField tbProdPrice;

    @FXML private TextField tbProdUnit;

    @FXML private Button btnSaveProduct;

    @FXML private TextField tbSearchProduct;

    @FXML private Label lbCaption;

    private Loan loan = new Loan();
    private static String action = "Add";
    private LinkedList<TextField> textFields = new LinkedList<>();

    protected static ObservableList<Product> ObProduct = FXCollections.observableArrayList();
    protected FilteredList<Product> filteredListProduct = new FilteredList<>(ObProduct, p -> true);
    protected SortedList<Product> sortedListProduct = new SortedList<>(filteredListProduct);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InitColumns();
        GetProductData();
        searchProduct();
    }

    private void InitColumns(){
        col_prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        col_prod_name.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        col_prod_price.setCellValueFactory(new PropertyValueFactory<>("prodPrice"));
        col_prod_unit.setCellValueFactory(new PropertyValueFactory<>("prodUnit"));
    }

    protected void GetProductData(){
        ObProduct.clear(); //clear the ob list
        try{
            Connection conn = Connect.Link();
            String sql = "SELECT * FROM main.Product";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                ObProduct.add(new Product(rs.getInt("ProductId"), rs.getString("ProdName"),
                        rs.getDouble("ProdPrice"), rs.getString("ProdUnit")));
            }

            ProductTable.setItems(ObProduct);
            ActionButton(); //button
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ActionButton(){

        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = (TableColumn<Product, Void> param) -> {
            TableCell<Product, Void> cell = new TableCell<Product, Void>() {

                private final Button btnAction = new Button("Update");
                {
                    btnAction.setOnAction((ActionEvent event) -> {
                        Product product = getTableView().getItems().get(getIndex());

                        //get the customer id for further execution
                        LoanUtils.setProduct_PK(product.getProd_id());

                        /*Product details here*/
                        action = "Update";
                        LoadProductDetails();
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

    //save and update product
    @FXML
    void SaveProduct(ActionEvent event) {
        if(!CheckEmptyFields()){
            //add the product to the first node
            LoanUtils.LLProduct.addFirst(new Product(tbProdName.getText(),
                            Double.parseDouble(tbProdPrice.getText()), tbProdUnit.getText()));

            //invoke loan method
            switch (action){
                case "Add": loan.AddProduct();break;
                case "Update" :
                    loan.UpdateProduct(); //invoke method
                    btnSaveProduct.setText("Add"); //change caption
                    lbCaption.setText("Add new product"); //change caption
                    action = "Add"; //change state to Add
                    break;
            }

            Clear(); //clear the text fields
            ObProduct.clear(); //clear the ob product
            GetProductData(); //load it again
            removeListProduct(); //remove selected items
            searchProduct(); //product searchs
        }
    }

    private void removeListProduct(){
        int selectedItem = ProductTable.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            ProductTable.getItems().remove(selectedItem);
        }
    }

    private void searchProduct() {

        tbSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListProduct.setPredicate(product -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getProdName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getProdPrice().toString().contains(lowerCaseFilter)) {
                    return true;
                }else if(product.getProdUnit().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                return false;
            });
        });

        sortedListProduct.comparatorProperty().bind(ProductTable.comparatorProperty());
        ProductTable.setItems(sortedListProduct);
    }

    private boolean CheckEmptyFields(){
        textFields.add(tbProdName);
        textFields.add(tbProdPrice);
        textFields.add(tbProdUnit);

        for(TextField t: textFields){
            if(t.getText().isEmpty())
                return true;
        }

        return false;
    }

    private void Clear(){
        for(TextField t: textFields){
            t.clear();
        }
    }

    private void LoadProductDetails(){
        btnSaveProduct.setText("Update");
        lbCaption.setText("Update product");
        //get the details using the product PK
        for(Product product: ObProduct){
            if(product.getProd_id() == LoanUtils.getProduct_PK()){
                tbProdName.setText(product.getProdName());
                tbProdPrice.setText(String.valueOf(product.getProdPrice()));
                tbProdUnit.setText(product.getProdUnit());
                return;
            }
        }
    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }
}
