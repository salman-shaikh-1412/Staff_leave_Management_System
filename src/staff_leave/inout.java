
package staff_leave;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class inouts implements ActionListener
{
    
    JPanel panel;
    JFrame frame;
    JButton Out_button, In_button,update,logout,leave;
    JLabel heading_label, date_label, label_main_head,label_table_head;
    JDateChooser chooser;
    //JComboBox cbranch;
    
    PreparedStatement ps=null;
    ResultSet rs=null;
    Connection con=null;
    Statement s=null;
    DateFormat date_format ;
         
    DefaultTableModel model;
    JTable table;
    JScrollPane scroll_pane,scroll_pane_list;
    
    String format =  "yyyy-MM-dd HH:mm:ss.S";           
    String format1 = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    SimpleDateFormat sdf1 = new SimpleDateFormat(format1);

    inouts() 
    {
        try
        {   
            date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //System.out.print(txt1);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            s = con.createStatement();

            panel = new JPanel();
            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setTitle("Inout Frame");
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
            panel.setBounds(0,0,800,600);
            panel.setLayout(null);
            panel.setBackground(Color.white);

            //branch_label = new JLabel("<html><bod<b><span style = 'font-size:12px'>Branch  :</span></b></html>");
//            cbranch = new JComboBox();
//            cbranch.addItem("Computer");
//            cbranch.addItem("Mechanical");
//            cbranch.addItem("Electrical");
//            cbranch.addItem("Civil");
//            cbranch.addItem("Electronics");
            String query = "select * from staff where username = '"+global_class.uname+"'";
            rs = s.executeQuery(query);
            String fname = null;
            if(rs.next())
            {
                fname = rs.getString("firstname").toUpperCase();
            }
            label_main_head = new JLabel("<html><bod<b><span style = 'font-size:30px'>Welcome "+fname+"</span></b></html>");
            label_main_head.setBounds(200, 20, 400, 50);
            label_main_head.setOpaque(true);
            label_main_head.setForeground(Color.BLUE);
            label_main_head.setBackground(Color.WHITE);
            
            logout = new JButton("Logout");
            logout.setOpaque(true);
            logout.setBackground(Color.RED);
            logout.setForeground(Color.WHITE);
            logout.addActionListener(this);
            
            Out_button = new JButton("OUT");
            In_button = new JButton("IN");

            heading_label = new JLabel("<html><bod<b><span style = 'font-size:16px'>Select your option</span></b></html>");
            date_label = new JLabel();
            //branch_label.setBounds(100, 100, 80, 30);
            //cbranch.setBounds(200, 100, 100, 30);
            logout.setBounds(650, 70, 100, 30);
            Out_button.setBounds(100, 180, 80, 50);
            In_button.setBounds(220, 180, 80, 50);

            Out_button.addActionListener(this);
            In_button.addActionListener(this);

            update = new JButton("<html><b><span style = font-size:14>Update My Information</span></b></html>");
            update.setBounds(360, 180, 200, 50);
            update.addActionListener(this);
            
            heading_label.setBounds(100, 120, 230, 30);
            date_label.setBounds(130,250,180,30);
            
            label_table_head = new JLabel("<html><bod<b><span style = 'font-size:25px'>Your Leave Details</span></b></html>");
            label_table_head.setBounds(250, 290, 300, 40);
            label_table_head.setOpaque(true);
            label_table_head.setBackground(Color.WHITE);
            label_table_head.setForeground(Color.RED);
            
            leave = new JButton("<html><b><span style = font-size:14>Leave</span></b></html>");
            leave.addActionListener(this);
            leave.setBounds(600, 180, 150, 50);
            
            String col[] = {"Out_Time","In_Time","Leave TIme"};
            model = new DefaultTableModel(col, 0);
            table = new JTable(model);
            table.setRowHeight(30);
            scroll_pane = new JScrollPane(table);
            scroll_pane.setBounds(20,350,750,200);
            scroll_pane.setVisible(true);
                      
            chooser = new JDateChooser();
            chooser.setBounds(600, 240, 150, 30);
            panel.add(chooser);
            String quer = "select * from date where username = '"+global_class.uname+"'";
            rs = s.executeQuery(quer);

            while(rs.next())
            {
                //String name = rs.getString("username");

                model.addRow(new String[]
                {
                    rs.getString("o_time"),
                    rs.getString("i_time"),
                    rs.getDouble("diff_time")+" Minutes"
                });
                //System.out.print("\n\tyo"+name);
            }
            panel.add(scroll_pane);
            panel.setVisible(true);
            panel.add(label_table_head);
            panel.add(Out_button);
            panel.add(In_button);
            panel.add(logout);
            panel.add(leave);
            panel.add(heading_label);
            panel.add(update);
            panel.add(date_label);
            panel.add(label_main_head);
            frame.add(panel);

            //panel.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        }catch(Exception ex){}
    }
    public void actionPerformed(ActionEvent e)
    {
        String dt = null,c="b";
        int flag=0;
        try
        {
            if(e.getSource()==Out_button)
            {
                //String get_branch = (String)cbranch.getSelectedItem();
                Date date = new Date();
                dt = sdf.format(date);
                date_label.setText(dt); 
                
                String query;
                String uname = global_class.uname; 
               
                String ch1 = "select * from date where username = '"+uname+"'";   
                rs = s.executeQuery(ch1);
                
               
                int i=1;
                while(rs.next())
                { 
//                      System.out.print("yes");
                        //String n = rs.getString("username");
                        String it = rs.getString("i_time");
                        //System.out.print("\n"+it+"::::::::::::::"+i);

                        if(it.equals("1111-11-11 11:11:11.0"))
                        {
                            c="yes";
                            break;
                        }
                        else
                        {           
                            c="no";
                        }
                        i++;
                    }
                    if(c.equals("yes"))
                    {
                        JOptionPane.showMessageDialog(null, "You first have to in yourself");
                    }
                    else if(c.equals("no"))
                    {
                        String branch_query = "select * from staff where username = '"+uname+"'";
                        rs = s.executeQuery(branch_query);
                        
                        String get_branch;
                        if(rs.next())
                        {
                            get_branch = ""+rs.getString("branch");
                            //System.out.print(uname);
                            query = "insert into date(username,branch,o_time) values(?,?,?)";
                            ps = con.prepareStatement(query);
                            
                            ps.setString(1, global_class.uname);
                            ps.setString(2,get_branch);
                            ps.setString(3, dt);
                            
                            ps.execute();
                            JOptionPane.showMessageDialog(null, "Saved");
                            frame.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "not found");
                        }
                        frame.dispose();
                        Staff staffs = new Staff();
                        //staffs.setVisible(true);
                    }
                    else if(c.equals("b"))
                    {
                        try
                        {
                            String branch_query = "select branch from staff where username = '"+uname+"'";
                            rs = s.executeQuery(branch_query);
                            if(rs.next()){
                            String branch = rs.getString("branch");
                            //JOptionPane.showMessageDialog(null, "user not exist");
                            
                            System.out.print("yeeeeeeeeeeeesssssssssssssssss"+uname);
                            query = "insert into date(username,branch,o_time) values(?,?,?)";
                            ps=con.prepareStatement(query);

                            ps.setString(1, global_class.uname);
                            ps.setString(2,branch);
                            ps.setString(3, dt);

                            ps.execute();
                            JOptionPane.showMessageDialog(null, "Saved");
                            frame.dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "yape error hai!");
                            }
                         }catch(Exception eeeee){}
                        frame.dispose();
                        Staff staffs = new Staff();
                        //staffs.setVisible(true);
                    }
                    else
                    {}


//                    else if(!rs.next())
//                    {
//                        query = "insert into date(username,o_time) values(?,?)";
//                        ps=con.prepareStatement(query);
//                        ps.setString(1, global_class.uname);
//                        ps.setString(2, dt);
//                        ps.execute();
//                        JOptionPane.showMessageDialog(null, "Saved");
//                        frame.dispose();
//                    }
//                    else
//                    {}    
            }
            else if(e.getSource()==In_button)
            {
                Date dates = new Date();
                dt = sdf.format(dates);
                date_label.setText(date_format.format(dates));
                String querys,ch;
                String uname = global_class.uname; 
                ch = "select * from date where username = '"+uname+"'";
                rs = s.executeQuery(ch);

                int i=1;
                String ot=null;
                    while(rs.next())
                    {   
                        String d = rs.getString("i_time");
                        System.out.print("\ntime is ::::::::::::::"+d+"::::::::::::::::::::::"+i);
                        ot = rs.getString("o_time");
                        int fl = rs.getInt("flag");

                        if(!ot.equals("1111-11-11 11:11:11.0") && !d.equals("1111-11-11 11:11:11.0"))
                        {
                            c="yes";
                        }
                        else if(!ot.equals("1111-11-11 11:11:11.0") && d.equals("1111-11-11 11:11:11.0"))
                        {
                            c="no";
                            break;
                        }
                        else
                        {
                        
                        }
                        i++;
                    }
                    if(c.equals("yes"))
                    {
                        JOptionPane.showMessageDialog(null, "you have to first out yourself");
                    }
                    else if(c.equals("no"))
                    {
                        //String get_branch = (String)cbranch.getSelectedItem();
                        
                        String date1 = null;
                        String date2 = null;
                        date1 = ot;
                        date2 = dt;
  
                        Date date11 = sdf.parse(date1);
                        Date date22 = sdf.parse(date2);

                        long diff = date22.getTime() - date11.getTime();
                        double diff_hour = diff/((double)1000*60*60);
                        double diff_mint = (diff_hour - (int)diff_hour)*60;
                        
                        String difference = diff_hour+"."+diff_mint;
                        
                        String update_query = "update date set diff_time = '"+diff_mint+"', i_time = '"+dt+"', flag = '1' where username = '"+uname+"' and flag = '0'";
                        s.execute(update_query);
//                        
//                        querys = "update date set i_time = '"+dt+"' where username = '"+uname+"' and flag='0'";
//                        ps = con.prepareStatement(querys);
//                        ps.execute();
//                        
//                        String queryss = "update date set flag = '1' where username = '"+uname+"'";
//                        ps = con.prepareStatement(queryss);
//                        ps.execute();
//                  
                        JOptionPane.showMessageDialog(null, "Saved");
                        frame.dispose();
                        Staff staffs = new Staff();
                        //staffs.setVisible(true);
                    }

                    else if(c.equals("b"))
                   {
                           JOptionPane.showMessageDialog(null, "You have to first out yourself");
                   }
            }
            else if(e.getSource()==update)
            {
                String quer_code = null;
                String security_code = JOptionPane.showInputDialog("Enter your security code");
                String security_code_query = "select * from staff where username = '"+global_class.uname+"'";
                rs = s.executeQuery(security_code_query);
                
                if(rs.next())
                {
                    quer_code = rs.getString("hint");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User not found!");
                }
                if(security_code.equals(quer_code))
                {
                    Update u = new Update();
                    u.setVisible(true);
                    frame.dispose();
                }else
                {
                    JOptionPane.showMessageDialog(null, "Code not matched!");
                }
            }
            else if(e.getSource()==logout)
            {
                Staff sss = new Staff();
                frame.dispose();
                //sss.setVisible(true);
            }
            else if(e.getSource()==leave)
            {

                String date = sdf.format(chooser.getDateFormatString());
                System.out.print(""+date);
//                Calendar calender = Calendar.getInstance();
//                calender.add(Calendar.DAY_OF_YEAR, 1);
//                Date tom = calender.getTime();
//                String toms = sdf1.format(tom);
//                //JOptionPane.showMessageDialog(null, toms);
//                String query11 = "insert into leave(username, leave_date) values(?, ?)";
//                ps=con.prepareStatement(query11);
//
//                ps.setString(1, global_class.uname);
//                ps.setString(2, toms);
//
//                ps.execute();
                JOptionPane.showMessageDialog(null, "Saved");
            }
            else
            {}
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog(null, exc); 
        }
    }
}

class inout 
{
    public static void main(String a[]) 
    {
        inouts i = new inouts();
    }
}