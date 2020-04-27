package sample.Classes;

import sample.Classes.Interfaces.IPay;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayOut extends Loan implements IPay {

    @Override
    public void Makepayment(String dategiven, double amount) {
        //insert into  Collections
        String collect = "INSERT INTO main.Collections (CustomerFk, CollectionAmount, GivenDate) " +
                         "VALUES (?, ?, ?)";
        //update balance of the customer
        //deduct to the remaining balance of the customer
        String updateBalance = "UPDATE main.Customer SET Balance = Balance - ? " +
                                "WHERE CustomerId = ?";

        try{
            //query 1
            PreparedStatement ps = conn.prepareStatement(collect);
            ps.setInt(1, getCustomer_PK());
            ps.setDouble(2, amount);
            ps.setString(3, dategiven);
            ps.executeUpdate();

            //query two
            PreparedStatement ps2 = conn.prepareStatement(updateBalance);
            ps2.setDouble(1, amount);
            ps2.setInt(2, getCustomer_PK());
            ps2.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Payment has been added");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
