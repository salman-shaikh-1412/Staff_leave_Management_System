
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class connect 
{
    public static Connection dbconnect()
    {
        Statement s = null;
        ResultSet rs = null;
        Connection con = null;  
        PreparedStatement ps = null;
        try
        {

        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
        }catch(Exception e){}
        return con;
    }
}
