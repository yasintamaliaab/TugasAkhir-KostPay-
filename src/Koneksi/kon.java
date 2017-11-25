package Koneksi;
import java.sql.*;
public class kon {
    static final String C_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kostpay";
    
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection Konek(){ 
        Connection conn = null;
        try{
            Class.forName(C_DRIVER);
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
        return conn;
    }
}
