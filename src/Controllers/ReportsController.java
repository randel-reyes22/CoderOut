package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import sample.Classes.ConnectDB.Connect;
import sample.Classes.Utility.WeekDates;
import sample.WindowState.Close;
import sample.WindowState.Open;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    //Revenue summary tab
    @FXML private AreaChart<String, Double> RevenueSummary;

    @FXML
    private DatePicker StartDate;

    @FXML
    private DatePicker EndDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void View(ActionEvent event) throws ParseException {
        if(!String.valueOf(EndDate.getValue()).isEmpty()
            && !String.valueOf(StartDate.getValue()).isEmpty())
            RevenueSummary();
    }

    private void RevenueSummary() throws ParseException {
        WeekDates weekDates = new WeekDates();
        String revenueWeek = "SELECT GivenDate ,sum(CollectionAmount) FROM main.Collections " +
                "WHERE GivenDate  = ?";

        //invoke method to gather all dates
        weekDates.GetAllDays(String.valueOf(StartDate.getValue()),
                String.valueOf(EndDate.getValue()));

        //coordinates
        XYChart.Series seriesRevenue= new XYChart.Series();
        seriesRevenue.setName("Revenue");

        for(String dates: WeekDates.dates){
            Connection conn = Connect.Link();
            try {
                PreparedStatement ps = conn.prepareStatement(revenueWeek);
                ps.setString(1, dates);
                ResultSet result = ps.executeQuery();
                result.next();

                seriesRevenue.getData().add(new XYChart.Data(dates,result.getDouble(2)));

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                //if an exception occurs
                JOptionPane.showMessageDialog(null, "An error occurred", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        //after the loop add the data to the chart
        RevenueSummary.getData().addAll(seriesRevenue);
    }

    @FXML
    void GoBack(MouseEvent event) {
        Open.Dashboard();
        Close.ThisWindow(event);
    }

}
