
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.UIManager;

public class Time_diff 
{    
    public Time_diff(Connection dbconnect, String query) {
    }
    public static void main(String a[]) throws ParseException
    {
        Statement s = null;
        ResultSet rs = null;
        Connection con = null;  
        PreparedStatement ps = null;
        
        try
        {
//            String d1 = "26/02/2011";
//            String t1 = "12:12:21";
//            String d2 = "27/02/2011";
//            String t2 = "23:15:21";
            
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            s = con.createStatement();
            
            String format =  "yyyy-MM-dd HH:mm:ss.S";           
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            
//            Date date1 = sdf.parse(d1 + " " + t1);
//            Date date2 = sdf.parse(d2 + " " + t2);
//
//            System.out.print("\n"+date1);
//            System.out.print("\n"+date2);
//            
//            long diff = date2.getTime() - date1.getTime();
//      
//            double diff_hour = diff/((double)1000*60*60);
//            
//            System.out.print("\nHour    "+(int)diff_hour);
//            
//            System.out.print("\nMinutes    "+(diff_hour - (int)diff_hour)*60);

            String date1 = null;
            String date2 = null;
            String query = "select * from date where username = 'pallu@gmail.com'"; 
            rs = s.executeQuery(query);
            while(rs.next())
            {
                date1 = rs.getString("o_time");
                date2 = rs.getString("i_time");
            }
                System.out.print("\n"+date1);
                System.out.print("\n"+date2);
            
            Date date11 = sdf.parse(date1);
            Date date22 = sdf.parse(date2);
            
            System.out.print("\n"+date11);
            System.out.print("\n"+date22);
            
            long diff = date22.getTime() - date11.getTime();
            double diff_hour = diff/((double)1000*60*60);
            
            System.out.print("\nHour    "+(int)diff_hour+" : Minutes    "+(diff_hour - (int)diff_hour)*60);
            System.out.print("\nMinutes    "+(diff_hour - (int)diff_hour)*60);
            
        }catch(Exception e){System.out.print(e);}
    }

    private static Date format(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
