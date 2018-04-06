
package staff_leave;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ShowDetails implements ActionListener
{
    
    Statement s = null;
    ResultSet rs = null;
    Connection con = null;  
    PreparedStatement ps = null;
    
    ImageIcon icon;
    JFrame frame;
    JPanel panel,panel1;
    JLabel ldept,staff_name,label_clg;
    JLabel staff_label,table_heading_label,image_label;
    JComboBox combo_box;
    JButton submit,delete,show_records,show_graphic_view,exit,user_profile,logout,all_staff;
    
    public JList<String> staff_list;
//    JComboBox cstaff;
    DefaultTableModel model;
    DefaultListModel<String> l1;
    JTable table;
    JScrollPane scroll_pane,scroll_pane_list;
   
    DateFormat date_format;
    
//    String head[]={"Id","Username","Branch","Out_time","In_time"};
//    String data[][]=new String[20][20];

    public ShowDetails() throws HeadlessException, SQLException 
    {
        try
        {
            //UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            s = con.createStatement();
            
            Border raisedBorder = BorderFactory.createRaisedBevelBorder();
            date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            
            frame = new JFrame(); 
            
            frame.setBackground(Color.yellow);
            frame.setLayout(null);            
            frame.setSize(1000,700);

            panel = new JPanel();
            panel.setBounds(0,0,1000,700);
            panel.setOpaque(true);
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);
                    
            panel1 = new JPanel();
            panel1.setLayout(null);
            panel1.setBackground(Color.WHITE);
            panel1.setBounds(10,350,1000,300);
     
            l1 = new DefaultListModel<>();  
            
            ImageIcon icon1 = new ImageIcon("img//2.jpg");
            label_clg = new JLabel(icon1);
            label_clg.setOpaque(true);
            label_clg.setBounds(100, 10, 150, 150);
            
            logout = new JButton("<html><b><span style = font-size:16>Logout</span></b></html>");
            logout.setOpaque(true);
            logout.setBackground(Color.RED);
            logout.setBorder(raisedBorder);
            logout.setForeground(Color.WHITE);
            logout.setBounds(850,50,80,30);
            logout.addActionListener(this);
            
            table_heading_label = new JLabel("<html><b><span style = font-size:40>Staff Leave Details</span</b></html>");
            table_heading_label.setOpaque(true);
            table_heading_label.setBackground(Color.WHITE);
            table_heading_label.setForeground(Color.BLUE);
            table_heading_label.setBounds(350,30,400,50);
         
            ldept = new JLabel("<html><b><span style = font-size:20>Select Dept  :</span</b></html>");
            ldept.setBounds(100,180,200,30);
           
            combo_box = new JComboBox();
            combo_box.addItem("Computer");
            combo_box.addItem("Mechanical");
            combo_box.addItem("Electrical");
            combo_box.addItem("Electronics");
            combo_box.addItem("Civil");
            
             staff_list = new JList<>(l1);
             staff_list.setFixedCellHeight(30);
             //staff_list.addListSelectionListener(this); 
//             staff_list.setBounds(350, 150, 320, 150);
             scroll_pane_list = new JScrollPane(staff_list);
             scroll_pane_list.setBounds(350, 230, 320, 150);
             
            all_staff = new JButton("<html><b><span style = font-size:16>Show Full Graph</span</b></html>");
            all_staff.setBounds(100, 350, 200, 40);
            all_staff.addActionListener(this);

             submit = new JButton("<html><b><span style = font-size:16>Submit</span</b></html>");
             submit.addActionListener(this);
             submit.setBounds(100, 270, 200, 40);
             
            delete = new JButton("<html><b><span style = font-size:14>Delete Account</span></b></html>");
            delete.addActionListener(this);
            delete.setBounds(100, 310, 200, 40);
             
             show_records = new JButton("<html><b><span style = font-size:16>Show Records</span</b></html>");
             show_records.addActionListener(this);
             show_records.setBounds(700, 230, 200, 40);
             
             user_profile = new JButton("<html><b><span style = font-size:16>Show User Profile</span</b></html>");
             user_profile.addActionListener(this);
             user_profile.setBounds(700, 270, 200, 40);
             
             show_graphic_view = new JButton("<html><b><span style = font-size:16>Show Graphic View</span</b></html>");
             show_graphic_view .addActionListener(this);
             show_graphic_view .setBounds(700, 310, 200, 40);
             
             exit = new JButton("<html><b><span style = font-size:16>Exit</span</b></html>");
             exit.addActionListener(this);
             exit.setBounds(700, 350, 200, 30);
             
            combo_box.setBounds(100,230,200,30);
            
            staff_label = new JLabel("<html><b><span style = font-size:20>Select Staff  :</span></b></html>");
            staff_label.setBounds(350, 180, 150, 30);
            
            String col[] = {"Username","Branch","Out_Time","In_Time","Leave TIme"};
            model = new DefaultTableModel(col, 0);
            table = new JTable(model);
            table.setRowHeight(30);
            scroll_pane = new JScrollPane(table);
            scroll_pane.setBounds(20,100,920,200);
            scroll_pane.setVisible(true);
                      
            panel1.add(scroll_pane);
            panel1.setVisible(true);
                
            panel.add(ldept);
            panel.add(combo_box);
            panel.add(all_staff);
            panel.add(staff_label);
            panel.add(label_clg);
            panel.add(submit);
            panel.add(delete);
            panel.add(user_profile);
            panel.add(table_heading_label);
            panel.add(show_records);
            panel.add(show_graphic_view);
            panel.add(exit);
            panel.add(logout);
            panel.add(scroll_pane_list);
            frame.add(panel);
            panel.add(panel1);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            }catch(Exception e){}

            JOptionPane.showMessageDialog(null, "admin login success");
            
            String sql = "select * from date order by id";
            rs = s.executeQuery(sql);
            panel1.add(scroll_pane);
            
            int i=0;
                while(rs.next())
                {
                    model.addRow(new String[]
                    {
                        rs.getString("username"),
                        rs.getString("Branch"),
                        rs.getString("o_time"),
                        rs.getString("i_time"),
//                        rs.getString("diff_time")+" Minutes"
                    });
                }   
     
            
//            while(rs.next())
//            {
//               table.setValueAt(rs.getString(1), i, 0);
//               table.setValueAt(rs.getString(2), i, 1);
//               table.setValueAt(rs.getString(3), i, 2);
//               table.setValueAt(rs.getString(4), i, 3);
//               table.setValueAt(rs.getString(5), i, 4);
//               i++;
//            }          
    }
        
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            String selected_name = null;
            if(e.getSource()==submit)
            {
                int i=0;
                
                l1.removeAllElements();
//              JOptionPane.showMessageDialog(null, "Done!");
                String selected_item = (String)combo_box.getSelectedItem();
                String query = "Select * from date where branch = '"+selected_item+"'";
                rs = s.executeQuery(query);
                HashSet<String> hash=new HashSet<String>();
                
                while(rs.next())
                {
                    String username = rs.getString("username");
                    hash.add(username);
                }               
                for(String s:hash)
                    l1.addElement(s);
            }

            else if(e.getSource()==delete)
            {
                int choice = JOptionPane.showConfirmDialog(panel, "Do you really want to delete?");
                //JOptionPane.showMessageDialog(null, choice);
                if(choice==0)
                {
                    selected_name = staff_list.getSelectedValue().toString();
                    String query = "delete from staff where username = '"+selected_name+"'";
                    s.execute(query);
                    String query1 = "delete from date where username = '"+selected_name+"'";
                    s.execute(query1);
                    JOptionPane.showMessageDialog(null, "User Deleted Succesfully");
                }
            }
            else if(e.getSource()==show_records)
            {
                DefaultTableModel model1 = new DefaultTableModel(new String[]{"Username","Branch","Out_time","In_time","Leave_Time"},0);
                table.setModel(model1);
                
                selected_name = staff_list.getSelectedValue().toString();
                global_class.select_name = selected_name;
                //System.out.print("11111111111111111"+global_class.select_name);
                String quer = "select * from date where username = '"+selected_name+"'";
                rs = s.executeQuery(quer);
                
                while(rs.next())
                {
                    //String name = rs.getString("username");
                    
                    model1.addRow(new String[]
                    {
                        rs.getString("username"),
                        rs.getString("branch"),
                        rs.getString("o_time"),
                        rs.getString("i_time"),
                        rs.getDouble("diff_time")+" Minutes"
                    });
                    //System.out.print("\n\tyo"+name);
                }                 
                
//                System.out.print("yo");

//                }catch(Exception ee){}
//                int j=0;
//                while(rs.next())
//                {
//                   table.setValueAt(""+rs.getInt(1), j, 0);
//                   table.setValueAt(""+rs.getString(2), j, 1);
//                   table.setValueAt(""+rs.getString(3), j, 2);
//                   table.setValueAt(""+rs.getString(4), j, 3);
//                   table.setValueAt(""+rs.getString(5), j, 4);
//                   j++;
//                }
                
                //icon = new ImageIcon("C:\\Users\\The Empire\\Documents\\NetBeansProjects\\Staff_leave\\salman@gmail.com.jpg");

                
            }
            else if(e.getSource()==show_graphic_view) 
            {
                selected_name = staff_list.getSelectedValue();
                global_class.select_name = selected_name;
                Graph graph = new Graph();
                graph.setVisible(true); 
                
//                selected_name = staff_list.getSelectedValue().toString();
//                String quer = "select * from date where username = '"+selected_name+"'";
//                rs = s.executeQuery(quer);             
//                
//                    staff_name = new JLabel("<html><b><span style = font-size:16>Leave Details Of "+selected_name+" =></span></b></html>");
//                    staff_name.setBounds(50, 750, 100, 30);
//                    frame.add(staff_name);
//                    
//                    String query = "select o_time,diff_time from date where username = '"+selected_name+"'";
//                    JDBCCategoryDataset datatset = new JDBCCategoryDataset(connect.dbconnect(), query);
//                    JFreeChart chart = ChartFactory.createLineChart("Leave_Time", "Date", "Time (Minutes)", datatset, PlotOrientation.VERTICAL, false, true, true);
//                    ChartPanel frame1 = new ChartPanel(chart);
//                    frame.setContentPane(frame1);
//                    frame1.setVisible(true);
//                    frame1.setBounds(450, 400, 850, 300);
            }
            else if(e.getSource()==user_profile)
            {
                //selected_name = null;
                selected_name = staff_list.getSelectedValue();
                global_class.select_name = selected_name;
//                System.out.print("hey!      "+selected_name);
                 User_profile user = new User_profile();
                 user.setVisible(true);
                //icon = new ImageIcon(selected_name+".jpg");
                //image_label = new JLabel(icon);
                //image_label.setBounds(1000,100,200,200);
                //frame.add(image_label);
                
                //JOptionPane.showMessageDialog(null, "yes");
            }
            else if(e.getSource()==logout)
            {
                    Staff sssss = new Staff();
                    //sssss.setVisible(true);
                    frame.dispose();
            }
            else if(e.getSource()==all_staff)
            {
                Full_Staff_Graph f =new Full_Staff_Graph();
                //f.setVisible(true); 
            }
            else
            {
                System.exit(0);
            }
        }catch(Exception ea)
        {
            JOptionPane.showMessageDialog(null, ea);
        }
    }    

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
class ShowDeatails
{
     public static void main(String a[])throws Exception
    {
        ShowDetails username = new ShowDetails();
    } 
}