package staff_leave;

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
            con = DriverManager.getConnection("http://thegame.5gbfree.com:2082/salman","thegame","X@sne#Z#lsapup");
        }catch(Exception e){}
        return con;
    }
}