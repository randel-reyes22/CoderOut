package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Utility.LoanUtils;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private PasswordField tbPassword;

    @FXML private TextField tbUsername;

    @FXML private Button Login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void AuthLogin(MouseEvent event) {
        switch (Auth())
        {
            case "success": //if login is successful
                Open.Dashboard();
                Close.ThisWindow(event);
                break;

            case "failed"://if login details is incorrect
                JOptionPane.showMessageDialog(null, "Your username or password is incorrect.", "Incorrect", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "error"://if have an exception
                JOptionPane.showMessageDialog(null, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private String Auth(){

        String sql = "SELECT * FROM main.Account";  //query string

        try {
            Connection conn = Connect.Link();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                if(tbUsername.getText().equals(rs.getString("username")) && tbPassword.getText().equals(rs.getString("password"))){
                    //set the session values
                    LoanUtils.sess_firstname = rs.getString("Firstname");
                    LoanUtils.sess_lastname = rs.getString("Lastname");

                    //if login is a success
                    return "success";
                }
            }
            return "failed";
        }

        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return "error";
        }
    }


}
