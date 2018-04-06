
package staff_leave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;

class Staff extends JFrame implements ActionListener
{
    JFrame frame;
    Statement s = null;
    ResultSet rs = null;
    Connection con = null;  
    PreparedStatement ps = null;
        
    JPanel panel,panel1;
    JLabel label_username,label_password,text_user,l4,lbl_heading,error,label_image,label_welcome;
    JButton login_button,register_button;
    JComboBox combo_box;
    JTextField text_username;
    JPasswordField text_password;
    ImageIcon image;
    
     int i=0;
     String username;
     String password;
    
    Staff()
    {
        try
        {
            //UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            s = con.createStatement();
            
            
            Border raisedBorder = BorderFactory.createRaisedBevelBorder();
            Border raisedBorde = BorderFactory.createEmptyBorder();

            frame = new JFrame();
            frame.setLayout(null);
            frame.setSize(900,760);
            frame.setTitle("Staff_Leave");
        
            panel = new JPanel();
            panel.setBounds(400,0,500,760);
            panel.setOpaque(true);
            panel.setBackground(Color.WHITE);
            panel.setBorder(raisedBorder);
            panel.setLayout(null);
            
            panel1 = new JPanel();
            panel1.setBounds(0,0,400,760);
            panel1.setOpaque(true);
            panel1.setBackground(Color.BLACK);
            panel1.setBorder(raisedBorder);
            panel1.setLayout(null);
//            panel.setOpaque(true);
//            panel.setBackground(Color.GRAY);
            label_welcome = new JLabel("<html><b><span style = font-size:25>Welcome!</span></b></html>");
            label_welcome.setOpaque(true);
            label_welcome.setBackground(Color.BLACK);
            label_welcome.setBounds(150, 420, 300, 30);
            
            label_welcome.setForeground(Color.WHITE);
            image = new ImageIcon("img//reg2.png");
            label_image = new JLabel(image);
            label_image.setBounds(50,150,300,250);
            
            lbl_heading = new JLabel("<html><b>--- Log in ---</b></html>");
            lbl_heading.setBorder(raisedBorder);
            lbl_heading.setOpaque(true);
            lbl_heading.setForeground(Color.RED);
            lbl_heading.setFont(new Font("Times new roman",Font.BOLD,45));
            
            label_username = new JLabel("<html><bod<b><span style = 'font-size:12px'>Username  :</span></b></html>");
            label_username.setBorder(raisedBorde);
            text_username = new JTextField(10);
            label_password = new JLabel("<html><bod<b><span style = 'font-size:12px'>Password  :</span></b></html>");
            text_password = new JPasswordField(10);
            text_user = new JLabel("<html><bod<b><span style = 'font-size:12px'>Select User  :</span></b></html>");

            error = new JLabel();
            error.setForeground(Color.RED);
  
            combo_box = new JComboBox();
            combo_box.addItem("Admin");
            combo_box.addItem("Staff");

            login_button = new JButton("<html><b><span style = font-size:16>Login</span></b></html>");
            register_button = new JButton("<html><b><span style = font-size:16>Register</span></b></html>");

            frame.setVisible(true);
            panel.setVisible(true);

            lbl_heading.setBounds(100,80,250,70);
            error.setBounds(160, 200, 300, 30);
            label_username.setBounds(50,200,100,30);
            text_username.setBounds(50,240,350,30);
            label_password.setBounds(50,290,100,30);
            text_password.setBounds(50,330,350,30);
            text_user.setBounds(50,380,100,30);
            combo_box.setBounds(180,380,100,30);
            

            login_button.addActionListener(this);
            register_button.addActionListener(this);
            combo_box.addActionListener(this);

            login_button.setBounds(120,450,100,30);
            register_button.setBounds(250,450,100,30);

            panel.add(lbl_heading);
            panel.add(error);
            panel.add(label_username);
            panel.add(text_username);
            panel.add(label_password);
            panel.add(text_password);
            panel.add(text_user);

            panel.add(login_button);
            panel1.add(label_welcome);
            panel1.add(label_image);
            panel.add(register_button);
            panel.add(combo_box);
            frame.setLocationRelativeTo(null);
            frame.add(panel1);
            frame.add(panel);
        }catch(Exception exc){System.out.print(exc);}
    }  
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if(e.getSource()==login_button)
            {           
                username = text_username.getText();
                password = text_password.getText();                               
                if(username.length()!=0 && password.length()!=0)
                {
                    //JOptionPane.showMessageDialog(null, "Success");
                    String get = (String)combo_box.getSelectedItem();
                    if(get.equals("Admin"))
                    {
                        //JOptionPane.showMessageDialog(null, "admin");
                        if(username.equals("admin") && password.equals("123"))
                        {
                            text_username.setText("");
                            text_password.setText("");
                            
                            new ShowDetails();
                            frame.dispose();
//                            JOptionPane.showMessageDialog(null, "admin login success");
//                            String sql = "select * from date order by id";
//                            rs = s.executeQuery(sql);
//                            panel.add(scroll_pane);
//                            panel.add(exit_button);
//                            while(rs.next())
//                            {
//                                data[i][0] = ""+rs.getInt(1);
//                                data[i][1] = ""+rs.getString(2);
//                                data[i][2] = ""+rs.getString(3);
//                                data[i][3] = ""+rs.getString(4);
//                                i++;
//                            }           
                        }
                        else
                        {
//                            JOptionPane.showMessageDialog(null, "admin login failed");
                            error.setText("Admin's username or password wrong!");
                        }
                    }
                    else
                    {
                        String quer;
                        //JOptionPane.showMessageDialog(null, "staff!");
                        String sql = "select * from staff  where username = '"+username+"' and password = '"+password+"'";
                        rs = s.executeQuery(sql);
                        if(rs.next())
                        {  
                            global_class.uname = username;
                            JOptionPane.showMessageDialog(null, "Logged In Success");
                            inout iq = new inout();
                            frame.dispose();                            
                        }
                        else
                        {
                            //JOptionPane.showMessageDialog(null,"Staff Log In failed");
                            error.setText("Username or password wrong!");
                        }
                    }
                }
                else
                {
                    //JOptionPane.showMessageDialog(null, "Fill in both fields!");
                    error.setText("Both fields should be filled!!");
                }
                
                /*String get = (String)combo_box.getSelectedItem();
                if(get.equals("Admin"))
                {
                    JOptionPane.showMessageDialog(null, "admin");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "staff!");

                }           
                //JOptionPane.showMessageDialog(null, "Fill in both fields!");

                //String username = text_username.getText();
                //String password = text_password.getText();
                
                if(username.length()!=0 && password.length()!=0)
                {
                    JOptionPane.showMessageDialog(null, "Success");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Fill in both fields!");
                }*/               
            }
            else if(e.getSource()==register_button)
            {
                //JOptionPane.showMessageDialog(null, "button 2");
                String get = (String)combo_box.getSelectedItem();
                if(get.equals("Admin"))
                {
                    JOptionPane.showMessageDialog(null, "Only Staff can Register!");
                }
                else if(get.equals("Staff"))
                {
                    Rframes r = new Rframes();
                    frame.dispose();
                }                
            }
            else
            {}
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }   
}
public class Staff_leave
{
    public static void main(String[] args) 
    {
        try
        {
            Staff ss = new Staff(); 
            //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        }
        catch(Exception e){}
    }   

    void setVisible(boolean b) 
    {
        throw new UnsupportedOperationException("\n\n\n\n\n\nNot supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
