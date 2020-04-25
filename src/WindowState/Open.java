package WindowState;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Open {

//  open dashboard window
    public static void Dashboard(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Dashboard.fxml"));
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

//  open customers window
    public static void Customers(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Customers.fxml"));
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

//  open new loan window
    public static void NewLoan(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../NewLoan.fxml"));
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

//  open payment window
    public static void Payment(){
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Payment.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Product.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Reports.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../Login.fxml"));
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
