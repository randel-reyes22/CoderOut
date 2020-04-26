package sample.Classes;

import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;
import sample.Classes.Interfaces.IAccount;
import sample.Classes.Interfaces.ILoan;
import sample.Classes.Interfaces.IProduct;
import sample.Classes.Utility.LoanUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Loan extends LoanUtils implements IAccount, IProduct, ILoan {

    //connection string
    protected Connection conn = Connect.Link();

    //IAccount methods implementation
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

    //IProduct methods implementation
    @Override
    public void AddProduct() {
        /* get the first most added customer in the linked list */
        Product product = LLProduct.getFirst();

        String insertCustomer = "INSERT INTO main.Product (ProdName, ProdPrice, ProdUnit)" +
                                "VALUES (?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(insertCustomer);
            ps.setString(1, product.getProdName());
            ps.setDouble(2, product.getProdPrice());
            ps.setString(3, product.getProdUnit());
            ps.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Product has been added");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void UpdateProduct() {
        /* get the first most added customer in the linked list */
        Product product = LLProduct.getFirst();

        String updateProduct = "UPDATE main.Product " +
                                "SET ProdName = ?, ProdPrice = ?, ProdUnit = ? " +
                                "WHERE ProductId = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(updateProduct);
            ps.setString(1, product.getProdName());
            ps.setDouble(2, product.getProdPrice());
            ps.setString(3, product.getProdUnit());
            ps.setInt(4, LoanUtils.getProduct_PK());
            ps.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Product has been updated");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //ILoan methods implementation
    @Override
    public boolean AddLoan(String modeOfPayment, String Term, String duedate) {

        String addLoan = "INSERT INTO main.Loan " +
                        "(CustomerFk, ProductFk, PaymentMode, Duedate, Term, Qty)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(addLoan);

            for(TableReceipt tr: ObTableReceipt){
                ps.setInt(1, LoanUtils.getCustomer_PK());
                ps.setInt(2, tr.getId());
                ps.setString(3,modeOfPayment);
                ps.setString(4, duedate);
                ps.setString(5, Term);
                ps.setInt(6, tr.getQty());
                ps.executeUpdate();
            }

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Loan has been saved");
            return true;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
