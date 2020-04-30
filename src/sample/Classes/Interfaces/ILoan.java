package sample.Classes.Interfaces;

import java.sql.SQLException;

public interface ILoan {
    /*This will add loan to a particular customer
    * then insert it to the loan table*/
    boolean AddLoan(double total, String modeOfPayment, String Term, String duedate) throws SQLException;

    /*This will get the unpaid payment history of a particular customer*/
    void GetPaymentHistory();

    /*THis will get the products loaned of a particular customer*/
    void GetProductsLoaned();
}
