
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class Linechart implements ActionListener
{
    JButton b;
    JFrame frame;
    public Linechart() 
    {
        frame = new JFrame();
        frame.setTitle("Line Chart");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);
        
        b = new JButton("Show");
        b.addActionListener(this);
        b.setBounds(20, 50, 100, 50);
        frame.add(b); 
    }
     
    @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                System.out.print("yo");
                String query = "select o_time,diff_time from date";
                
                JDBCCategoryDataset datatset = new JDBCCategoryDataset(connect.dbconnect(), query);
                System.out.print("yo");
                JFreeChart chart = ChartFactory.createLineChart("Time Difference", "Date", "Time", datatset, PlotOrientation.VERTICAL, false, true, true);
//                BarRenderer renderer = null;
//                CategoryPlot plot = null;
//                renderer = new BarRenderer();
                ChartFrame frame1 = new ChartFrame("TIME", chart);
//                JFrame frame1 = new JFrame();
                frame1.setVisible(true);
                frame1.setSize(1300,800);
//                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                System.out.print("yo");
            }catch(Exception ee){}
        
    }
    public static void main(String a[])throws Exception
    {
        Linechart l = new Linechart();
    }
}