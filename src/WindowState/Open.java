package WindowState;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Open {

//  open dashboard window
    public static void Dashboard(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Dashboard.fxml"));
        Parent dashboard = null;

        try {
            dashboard = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(dashboard));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

//    open the new loan window
    public static void NewLoan(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/NewLoan.fxml"));
        Parent newLoan = null;

        try {
            newLoan = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(newLoan));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

//  open customers window
    public static void Customers(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Customers.fxml"));
        Parent customer = null;

        try {
            customer = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(customer));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

//  open add customer window
    public static void AddCustomer(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/AddCustomer.fxml"));
        Parent addCustomer = null;

        try {
            addCustomer = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(addCustomer));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

//  open payment window
    public static void Payment(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Payment.fxml"));
        Parent payment = null;

        try {
            payment = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(payment));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

//  open product window
    public static void Product(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Product.fxml"));
        Parent product = null;

        try {
            product = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(product));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

    public static void Reports(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Reports.fxml"));
        Parent report = null;

        try {
            report = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(report));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }

    public static void Login(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Login.fxml"));
        Parent login = null;

        try {
            login = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage transactionWindow = new Stage();
        transactionWindow.setScene(new Scene(login));
        transactionWindow.setResizable(false);
        transactionWindow.show();
    }
}
