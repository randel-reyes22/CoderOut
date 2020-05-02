package sample.Classes;

import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Account;
import sample.Classes.Entities.Customer;
import sample.Classes.Entities.Product;
import sample.Classes.Hashing.Hash;
import sample.Classes.Interfaces.IAccount;
import sample.Classes.Interfaces.ILoan;
import sample.Classes.Interfaces.IProduct;
import sample.Classes.Interfaces.IWallet;
import sample.Classes.TableClasses.HistoryPayment;
import sample.Classes.TableClasses.LoanedProducts;
import sample.Classes.TableClasses.TableReceipt;
import sample.Classes.Utility.LoanUtils;
import sample.Classes.Utility.WeekDates;

import javax.net.ssl.SSLContext;
import javax.swing.*;
import java.sql.*;

public class Loan extends LoanUtils implements IAccount, IProduct, ILoan, IWallet {

    @Override
    public String Login(String username, String password) {
        final String Username = username;
        final String Password = password;

        String sql = "SELECT * FROM main.Account";  //query string

        try {
            Connection conn = Connect.Link();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                if(Username.equals(rs.getString("username")) &&
                        Password.equals( Hash.Decode(rs.getString( "password" )))){
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

    //IAccount methods implementation
    @Override
    public void AddUserAccount() {
        /* get the first most added customer in the linked list */
        Account account = LLAccount.getFirst();

        String insertCustomer = "INSERT INTO Account (Firstname, Lastname, Username, Password)" +
                                "VALUES (?, ?, ?, ?)";
        Connection conn = Connect.Link();
        try{
            PreparedStatement ps = conn.prepareStatement(insertCustomer);
            ps.setString(1, account.getFirstname());
            ps.setString(2, account.getLastname());
            ps.setString(3, account.getUsername());
            ps.setString(4, account.getPassword());
            ps.executeUpdate();

            //if customer is successfully added
            JOptionPane.showMessageDialog(null, "Account has been added");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void AddCustomerAccount() {
        /* get the first most added customer in the linked list */
        Customer customer = LLCustomer.getFirst();

        String insertCustomer = "INSERT INTO main.Customer (Firstname, Lastname, MobileNumber, Address)" +
                                "VALUES (?, ?, ?, ?)";
        Connection conn = Connect.Link();
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
        Connection conn = Connect.Link();
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
        Connection conn = Connect.Link();
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //IProduct methods implementation
    @Override
    public void AddProduct() {
        /* get the first most added customer in the linked list */
        Product product = LLProduct.getFirst();

        Connection conn = Connect.Link();
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void UpdateProduct() {
        /* get the first most added customer in the linked list */
        Product product = LLProduct.getFirst();

        Connection conn = Connect.Link();
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void GetProducts() {
        LoanUtils.ObProduct.clear(); //clear the ob list
        Connection conn = Connect.Link();
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //ILoan methods implementation
    @Override
    public boolean AddLoan(double total, String modeOfPayment, String Term, String duedate) {

        String addLoan = "INSERT INTO main.Loan " +
                        "(CustomerFk, ProductFk, PaymentMode, Duedate, Term, Qty)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        String addBalance = "UPDATE main.Customer SET Balance = Balance + ? WHERE CustomerId = ?;";
        Connection conn = Connect.Link();
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

    @Override
    public void GetPaymentHistory() {
        LoanUtils.ObHistoryPayments.clear(); //clear the ob list
        Connection conn = Connect.Link();
        try{
            String sql = "SELECT DISTINCT [C].CollectionId, [C].CollectionAmount, [C].GivenDate " +
                    "FROM Collections AS C CROSS JOIN Loan AS L " +
                    "WHERE [C].CustomerFk = ? AND [L].Status = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, getCustomer_PK());
            ps.setString(2, PAID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                LoanUtils.ObHistoryPayments.add(new HistoryPayment(rs.getInt("CollectionId"),
                        rs.getDouble("CollectionAmount"),rs.getString("GivenDate")));
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void GetProductsLoaned() {
        LoanUtils.ObLoanedProducts.clear(); //clear the ob list
        Connection conn = Connect.Link();
        try{
            String sql = "SELECT [p].ProdName, [p].ProdPrice, [l].Qty, [l].PaymentMode, [l].Duedate, [l].Term " +
            "FROM Product AS p INNER JOIN Loan L on p.ProductId = L.ProductFk " +
            "WHERE CustomerFk = ? AND [l].Status = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, getCustomer_PK());
            ps.setString(2, UNPAID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                LoanUtils.ObLoanedProducts.add(new LoanedProducts(rs.getString("ProdName"), rs.getDouble("ProdPrice"),
                                            rs.getInt("Qty"), rs.getString("PaymentMode"),
                                            rs.getString("Duedate"), rs.getString("Term")));
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "An error occurred",  "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //IWallet methods implementation
    @Override
    public double TotalRevenueToday() {

        String revenueToday = "SELECT sum(CollectionAmount) FROM main.Collections where GivenDate = ?";
        Connection conn = Connect.Link();
        try{
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public double TotalRevenueThisWeek() {

        String revenueToday = "SELECT sum(CollectionAmount) FROM main.Collections " +
                                "WHERE GivenDate BETWEEN ? AND ?";
        Connection conn = Connect.Link();
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
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
