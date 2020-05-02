package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Hashing.Hash;
import sample.Classes.Loan;
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

    //classes
    private final Loan loan = new Loan();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void AuthLogin(MouseEvent event) {
        switch (loan.Login(tbUsername.getText(), tbPassword.getText()))
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

}
