package Controllers;

import WindowState.Close;
import WindowState.Open;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NewLoanController implements Initializable {

    @FXML
    private TextField tbAddress;

    @FXML
    private TextField tbFisrtname;

    @FXML
    private TextField tbLastname;

    @FXML
    private TextField tbNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }
}
