package sample.Classes.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static void Link(){
        Connection con = null;
        try
        {
            String url = "jdbc:sqlite:Loan.db";
            con = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
