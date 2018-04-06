
package staff_leave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Full_Staff_Graph extends JFrame 
{
    Statement s = null;
    ResultSet rs = null,rs1 = null;
    Connection con = null;
    
    public Full_Staff_Graph()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            s = con.createStatement();
            String query = "select username,diff_time from date";
            rs = s.executeQuery(query);
            DefaultPieDataset dataset = new DefaultPieDataset();
            while(rs.next())
            {
                String name = rs.getString("username");
                Double time = rs.getDouble("diff_time");
                dataset.setValue(name, time);
            }
            JFreeChart chart = ChartFactory.createPieChart("time", dataset);
            PiePlot plot = (PiePlot)chart.getPlot();
            ChartFrame frame = new ChartFrame("Pie Chart", chart);
            
            frame.setSize(550, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
                    
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
}
