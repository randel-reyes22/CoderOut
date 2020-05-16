package sample.Classes;

import sample.Classes.ConnectDB.Connect;
import sample.Classes.Entities.Customer;
import sample.Classes.Hashing.MessageBox;
import sample.Classes.Interfaces.IPay;

import java.sql.Connection;
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
        //open connection
        Connection conn = Connect.Link();
        
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
            MessageBox.ShowInformation("Payment has been deducted \n " +
                    "to the remaining balance");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            MessageBox.ShowError("An error occurred");
        }
        finally {
            try {
                CheckStatus();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void CheckStatus() {

        String updateStatus = "UPDATE main.Loan SET Status = ? " +
                        "WHERE CustomerFk = ?";

        Connection conn;
        conn = Connect.Link();

        try{
            //find the customer using the customer PK
            for(Customer customer: ObCustomer) {
                if(customer.getCustomer_id() == getCustomer_PK()) {
                    //if balance is equals to 0 mark inactive
                    PreparedStatement ps = conn.prepareStatement( updateStatus );
                    if(customer.getBalance() == 0) {
                        //query 1
                        ps.setString( 1, PAID );
                        ps.setInt( 2, getCustomer_PK() );
                        ps.executeUpdate();

                        //if customer have 0 remaining balance
                        MessageBox.ShowInformation("Customer has 0 remaining balance");
                    }else{ //if balance is > 0
                        ps.setString( 1, UNPAID );
                        ps.setInt( 2, getCustomer_PK() );
                        ps.executeUpdate();
                    }
                    break;
                }
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //if an exception occurs
            MessageBox.ShowError("Cannot update status");
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
