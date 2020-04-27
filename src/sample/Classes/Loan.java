package sample.Classes;

import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;
import sample.Classes.Interfaces.IAccount;
import sample.Classes.Interfaces.ILoan;
import sample.Classes.Interfaces.IProduct;
import sample.Classes.Interfaces.IWallet;
import sample.Classes.Utility.LoanUtils;
import sample.Classes.Utility.WeekDates;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loan extends LoanUtils implements IAccount, IProduct, ILoan, IWallet {

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

    @Override
    public void GetCustomers() {
        ObCustomer.clear(); //clear the ob list
        try{
            String sql = "SELECT * FROM main.Customer";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                ObCustomer.add(new Customer(rs.getInt("CustomerId"), rs.getString("Firstname"),
                        rs.getString("Lastname"), rs.getString("MobileNumber"), rs.getString("Address")
                        ,rs.getDouble("Balance")));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
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

    @Override
    public void GetProducts() {
        LoanUtils.ObProduct.clear(); //clear the ob list
        try{
            String sql = "SELECT * FROM main.Product";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                LoanUtils.ObProduct.add(new Product(rs.getInt("ProductId"), rs.getString("ProdName"),
                        rs.getDouble("ProdPrice"), rs.getString("ProdUnit")));
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //ILoan methods implementation
    @Override
    public boolean AddLoan(double total, String modeOfPayment, String Term, String duedate) {

        String addLoan = "INSERT INTO main.Loan " +
                        "(CustomerFk, ProductFk, PaymentMode, Duedate, Term, Qty)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        String addBalance = "UPDATE main.Customer SET Balance = Balance + ? WHERE CustomerId = ?;";

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

            //add the total in the customer remaining balance
            PreparedStatement ps2 = conn.prepareStatement(addBalance);
            ps2.setDouble(1, total);
            ps2.setInt(2, getCustomer_PK());
            ps2.executeUpdate();

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

    //IWallet methods implementation
    @Override
    public double TotalRevenueToday() {

        String revenueToday = "SELECT sum(CollectionAmount) FROM main.Collections where GivenDate = ?";
        try{
            /*conn.*/
            PreparedStatement ps =  conn.prepareStatement(revenueToday);
            ps.setString(1, WeekDates.DateNow());
            ResultSet result = ps.executeQuery();
            result.next();

            return result.getDouble(1);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0.00;
        }
    }

    @Override
    public double TotalRevenueThisWeek() {

        String revenueToday = "SELECT sum(CollectionAmount) FROM main.Collections " +
                                "WHERE GivenDate BETWEEN ? AND ?";
        try{
            PreparedStatement ps =  conn.prepareStatement(revenueToday);
            ps.setString(1, WeekDates.getMonday());
            ps.setString(2, WeekDates.getSunday());
            ResultSet result = ps.executeQuery();
            result.next();

            return result.getDouble(1);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0.00;
        }
    }
}
