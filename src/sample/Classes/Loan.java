package sample.Classes;

import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Interfaces.IAccount;
import sample.Classes.Interfaces.IProduct;
import sample.Classes.Utility.LoanUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Loan extends LoanUtils implements IAccount, IProduct {

    //connection string
    protected Connection conn = Connect.Link();

    //IAccount implementation
    @Override
    public void AddUserAccount() {

    }

    @Override
    public void AddCustomerAccount() {
        /* get the first most added customer in the linked list */
        Customer customer = LLCustomer.getFirst();

        String insertCustomer = "INSERT INTO main.Customer (Firstname, Lastname, MobileNumber, Address)" +
                                "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(insertCustomer);
            ps.setString(1, customer.getFirstname());
            ps.setString(2, customer.getLastname());
            ps.setString(3, customer.getMobile());
            ps.setString(4, customer.getAddress());
            ps.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Customer has been added");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void UpdateCustomerAccount() {
        /* get the first most added customer in the linked list */
        Customer customer = LLCustomer.getFirst();

        String updateCustomer = "UPDATE main.Customer " +
                "SET Firstname = ?, Lastname = ?, MobileNumber = ?, Address = ?" +
                "WHERE CustomerId = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(updateCustomer);
            ps.setString(1, customer.getFirstname());
            ps.setString(2, customer.getLastname());
            ps.setString(3, customer.getMobile());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, LoanUtils.Customer_PK);
            ps.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Customer has been updated");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //IProduct implementation
    @Override
    public void AddProduct() {

    }

    @Override
    public void UpdateProduct() {

    }
}
