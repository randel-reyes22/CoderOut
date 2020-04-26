package WindowState;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Open {

//  open dashboard window
    public static void Dashboard(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Dashboard.fxml"));
            Parent dashboard = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(dashboard));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//    open the new loan window
    public static void NewLoan(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/NewLoan.fxml"));
            Parent newLoan = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(newLoan));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//  open customers window
    public static void Customers(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Customers.fxml"));
            Parent customer = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(customer));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//  open add customer window
    public static void AddCustomer(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/AddCustomer.fxml"));
            Parent addCustomer = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(addCustomer));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//  open payment window
    public static void Payment(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Payment.fxml"));
            Parent payment = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(payment));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//  open product window
    public static void Product(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Product.fxml"));
            Parent product = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(product));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void Reports(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Reports.fxml"));
            Parent report = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(report));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void Login(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/Login.fxml"));
            Parent login = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(login));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void TermCondition(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Open.class.getResource("../FXMLFiles/TermCondition.fxml"));
            Parent term_condition = fxmlLoader.load();
            Stage window = new Stage();
            window.setScene(new Scene(term_condition));
            window.setResizable(false);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
