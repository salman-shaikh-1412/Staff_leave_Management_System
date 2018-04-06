
package staff_leave;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;

class Rframes implements ActionListener
{
    JFrame frame;
    JPanel panel,panel1;
    JButton register,cancle,clear;
    JTextField firstaname_text,course_text,username_text,lastname_text,hint_text;
    JPasswordField password,re_password;
    JLabel heading_label,firstname_label,course_label,username_label,password_label,repassword_label,l6,lastname_label,label_image,image_below,label_hint;
    JComboBox combo_box;
    
    PreparedStatement ps = null;
    Statement s = null;
    ResultSet rs = null;
    Connection con = null;
    
    Rframes()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salman","root","");
            
            
            Border raisedBorder = BorderFactory.createRaisedBevelBorder();
            Border raisedBorde = BorderFactory.createEmptyBorder();
            
            frame = new JFrame();
            //f.setBackground(Color.yellow);
            frame.setTitle("Register");
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
            frame.setSize(1000,760);
            
            panel = new JPanel();
            panel.setBounds(400,0,600,760);
            panel.setLayout(null);
            panel.setBorder(raisedBorder);           
            panel.setBackground(Color.white);
            //p.setBackground(Color.white);

            heading_label = new JLabel("<html><b>--- Register ---</b></html>");
            heading_label.setBorder(raisedBorder);
            heading_label.setOpaque(true);
            heading_label.setForeground(Color.BLUE);
            heading_label.setFont(new Font("Times new roman",Font.BOLD,45));
            
            firstname_label = new JLabel("<html><b><span style = 'font-size:12px'>First_Name</span></b></html>");
            lastname_label = new JLabel("<html><b><span style = 'font-size:12px'>Last_Name</span></b></html>");
            course_label = new JLabel("<html><b><span style = 'font-size:12px'>Course</span></b></html>");
            username_label = new JLabel("<html><b><span style = 'font-size:12px'>Username</span></b></html>");
            password_label = new JLabel("<html><b><span style = 'font-size:12px'>Passsword</span></b></html>");
            repassword_label = new JLabel("<html><b><span style = 'font-size:12px'>Retype Password</span></b></html>");
            l6 = new JLabel("");

            firstaname_text = new JTextField(10);
            lastname_text = new JTextField(10);
            course_text = new JTextField(10);
            username_text = new JTextField(10);
            password = new JPasswordField(10);
            re_password = new JPasswordField(10);

            register = new JButton("<html><b><span style = 'font-size:12px'>Register</span></b></html>");
            cancle = new JButton("<html><b><span style = 'font-size:12px'>Cancle</span></b></html>");
            clear = new JButton("<html><b><span style = 'font-size:12px'>Clear</span></b></html>");

            combo_box = new JComboBox();
            combo_box.addItem("Computer");
            combo_box.addItem("Mechanical");
            combo_box.addItem("Civil");
            combo_box.addItem("Electrical");
            combo_box.addItem("Electronics");

            
            register.addActionListener(this);
            cancle.addActionListener(this);
            clear.addActionListener(this);
            
            heading_label.setBounds(140,50,300,70);
            
            firstname_label.setBounds(30,200,120,30);
            firstaname_text.setBounds(160,200,300,30);
            
            lastname_label.setBounds(30,240,120,30);
            lastname_text.setBounds(160,240,300,30);
            
            course_label.setBounds(30,280,120,30);
            //t2.setBounds(140,240,300,30);
            combo_box.setBounds(160,280,300,30);
 
            username_label.setBounds(30,320,120,30);
            username_text.setBounds(160,320,300,30);
            
            password_label.setBounds(30,360,120,30);
            password.setBounds(160,360,300,30);
            
            repassword_label.setBounds(30,400,120,50);
            re_password.setBounds(160,400,300,30);
            
            label_hint = new JLabel("<html><b><span style = 'font-size:12px'>Enter your hint</span></b></html>");
            label_hint.setBounds(30, 460, 120, 30);
            hint_text = new JTextField(10);
            hint_text.setBounds(160, 460, 300, 30);
            
            l6.setBounds(160,160,300,30);
            l6.setBackground(Color.red);
            l6.setForeground(Color.red);
            
            register.setBounds(150,510,90,30);
            cancle.setBounds(270,510,90,30);
            clear.setBounds(390,510,90,30);
            
            panel1 = new JPanel();  
            panel1.setBounds(0,0,400,760);
            panel1.setOpaque(true);
            panel1.setBackground(Color.BLACK);
            panel1.setBorder(raisedBorder);
            panel1.setLayout(null);
            
            ImageIcon image = new ImageIcon("img//6.png");
            label_image = new JLabel(image);
            label_image.setBounds(50,150,300,250);
            
            image_below = new JLabel("<html><b><span style = font-size:30>Register</span></b></html>");
            image_below.setBounds(130, 420, 0200, 40);
            image_below.setOpaque(true);
            image_below.setBackground(Color.BLACK);
            image_below.setForeground(Color.WHITE);
            
            panel.add(heading_label);
            panel.add(firstname_label);
            panel.add(firstaname_text);

            panel.add(lastname_label);
            panel.add(lastname_text);
            
            panel.add(course_label);
            panel.add(combo_box);

            panel.add(username_label);
            panel.add(username_text);

            panel.add(password_label);
            panel.add(password);

            panel.add(repassword_label);
            panel.add(re_password);

            panel.add(label_hint);
            panel.add(hint_text);
            
            panel.add(register);
            panel.add(cancle);
            panel.add(clear);
            
            panel.add(l6);

            panel1.add(label_image);
            panel1.add(image_below);
            
            frame.add(panel);
            frame.add(panel1);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        catch(Exception ex){System.out.print(ex);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if(e.getSource()==register)
            {
                String f_name = firstaname_text.getText();
                String l_name = lastname_text.getText();
                String branch = (String)combo_box.getSelectedItem();
                String u_name = username_text.getText();
                String p_word = password.getText();
                String rp_word = re_password.getText();
                String hint = hint_text.getText();
                             
                boolean status = Validation.email_validate(u_name);
                //boolean flag = Validation.name_validate(f_name, l_name);
                             
                if(f_name.length()!=0 && l_name.length()!=0 && u_name.length()!=0 && p_word.length()!=0)
                {
                    if(p_word.equals(rp_word) && status)
                    {
                        s = con.createStatement();
                        String query = "select * from staff where username = '"+u_name+"'";
                        rs = s.executeQuery(query);
                        //JOptionPane.showMessageDialog(null, u_name);
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(null, "Username already exist!");
                        }
                        else
                        {
                            String sql = "insert into staff(firstname,lastname, branch, username, password,hint) values (?,?,?,?,?,?)";
                            ps = con.prepareStatement(sql);

                            //ps.setString(1,"5");
                            ps.setString(1, f_name);
                            ps.setString(2,l_name);
                            ps.setString(3, branch);                        
                            ps.setString(4, u_name);
                            ps.setString(5, p_word);
                            ps.setString(6, hint);

                            ps.execute();

                            JOptionPane.showMessageDialog(null, "Registered");
                            Staff ss = new Staff();
                            frame.dispose();
                        }
                    }
                    else
                    {
                       //JOptionPane.showInternalMessageDialog(null, "Passwords doesn't mached or bad email!");
                        l6.setText("Passwords doesn't mached or bad email!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                }
            }
            else if(e.getSource()==cancle)
            {
                Staff s = new Staff();
                //f.setVisible(true);
                frame.dispose();
            }
            else if(e.getSource()==clear)
            {
                firstaname_text.setText("");
                lastname_text.setText("");
                course_text.setText("");
                username_text.setText("");
                password.setText("");
                re_password.setText("");
            }
        }catch(Exception ex){System.out.print(ex);}
    }
}
public class Rframe {
    public static void main(String a[])throws Exception
    {
        Rframes f1 = new Rframes();

    }
}