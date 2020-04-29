package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Hashing.Hash;
import sample.Classes.Utility.LoanUtils;
import sample.WindowState.Close;
import sample.WindowState.Open;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    @FXML private PasswordField tbPassword;

    @FXML private TextField tbUsername;

    @FXML private Button Login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //PassHash();
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
                JOptionPane.showMessageDialog(null,
                        "Your username or password is incorrect.", "Incorrect", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "error"://if have an exception
                JOptionPane.showMessageDialog(null,
                        "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
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
                if(tbUsername.getText().equals(rs.getString("username")) &&
                        tbPassword.getText().equals( Hash.Decode(rs.getString( "password" )))){
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

    private void PassHash(){
        String sql = "UPDATE Account SET Password = ? WHERE AccountId = ?";  //query string

        try {
            Connection conn = Connect.Link();
            PreparedStatement ps = conn.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Password : " );
            String pass = scanner.next();

            ps.setString(1, Hash.Encode(pass));
            ps.setInt(2, 4);
            ps.executeUpdate();

            System.out.println("Done");
        }

        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
