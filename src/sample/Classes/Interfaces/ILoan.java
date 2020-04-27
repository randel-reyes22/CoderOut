package sample.Classes.Interfaces;

import java.sql.SQLException;

public interface ILoan {
    boolean AddLoan(double total, String modeOfPayment, String Term, String duedate) throws SQLException;
    void GetPaymentHistory();
    void GetProductsLoaned();
}
