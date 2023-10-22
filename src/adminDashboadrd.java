import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class adminDashboadrd {

    private JPanel Main;

    private JLabel appLogo;
    private JButton update;
    private JButton add;
    private JButton view;
    private JLabel appTitle;
    private JTabbedPane adminTabs;
    private JTextField txtName;
    private JTextField txtAddress;
    private JLabel name;
    private JLabel address;
    private JTextField txtGender;
    private JTextField txtNIC;
    private JTextField txtContact;
    private JTextField txtBasic;
    private JTextField txtPosition;
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JLabel gender;
    private JLabel conNo;
    private JLabel nic;
    private JLabel basicSalary;
    private JLabel position;
    private JLabel eMail;
    private JLabel password;
    private JButton addAll;
    private JPanel upOrRemHS;
    private JButton searchEmployeeID;
    private JTextField txtName2;
    private JTextField txtAddress2;

    private JTextField txtGender2;
    private JTextField txtContNo2;
    private JTextField txtNIC2;
    private JTextField txtBasic2;
    private JTextField txtPosition2;
    private JTextField txtEmail2;
    private JButton updateUR;
    private JButton RemoveUR;
    private JLabel nameUR;
    private JLabel addressUR;
    private JLabel genderUR;
    private JLabel ContactUR;
    private JLabel NICUR;
    private JLabel basicUR;
    private JLabel positionUR;
    private JLabel eMailUR;
    private JTable table1;
    private JLabel title;
    private JTextField txtSearchID;
    private JPasswordField txtPassword2;
    private JLabel password2;

    public adminDashboadrd() {

        connect();

        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        //Font linking for Application title
        try {
            Font NicoMoji = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/NicoMoji-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment font1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font1.registerFont(NicoMoji);
            appTitle.setFont(NicoMoji);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        view.setPreferredSize(new Dimension(250, 40));
        add.setPreferredSize(new Dimension(250, 40));
        update.setPreferredSize(new Dimension(250, 40));

        //Add HR or Supervisor
        //Button
        addAll.setPreferredSize(new Dimension(150, 40));

        //Input text areas
        txtName.setPreferredSize(new Dimension(150, 30));
        txtAddress.setPreferredSize(new Dimension(150, 30));
        txtGender.setPreferredSize(new Dimension(150, 30));
        txtContact.setPreferredSize(new Dimension(150, 30));
        txtNIC.setPreferredSize(new Dimension(150, 30));
        txtBasic.setPreferredSize(new Dimension(150, 30));
        txtPosition.setPreferredSize(new Dimension(150, 30));
        txtEmail.setPreferredSize(new Dimension(150, 30));
        txtPassword.setPreferredSize(new Dimension(150, 30));

        //Update/Remove HR or Supervisor
        //Input text areas
        txtSearchID.setPreferredSize(new Dimension(150, 30));
        txtName2.setPreferredSize(new Dimension(150, 30));
        txtAddress2.setPreferredSize(new Dimension(150, 30));
        txtGender2.setPreferredSize(new Dimension(150, 30));
        txtContNo2.setPreferredSize(new Dimension(150, 30));
        txtNIC2.setPreferredSize(new Dimension(150, 30));
        txtBasic2.setPreferredSize(new Dimension(150, 30));
        txtPosition2.setPreferredSize(new Dimension(150, 30));
        txtEmail2.setPreferredSize(new Dimension(150, 30));
        txtPassword2.setPreferredSize(new Dimension(150, 30));


        //Buttons
        searchEmployeeID.setPreferredSize(new Dimension(80, 30));
        updateUR.setPreferredSize(new Dimension(150, 40));
        RemoveUR.setPreferredSize(new Dimension(150, 40));
        password2.setPreferredSize(new Dimension(150, 40));



        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(1);
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(0);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(2);
            }
        });
        addAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Add HR or Supervisor
                String name,gender,nic,position,password,address,email,contNo,salary;


                name = txtName.getText();
                gender= txtGender.getText();
                nic= txtNIC.getText();
                position= txtPosition.getText();
                password= txtPassword.getText();
                address= txtAddress.getText();
                email= txtEmail.getText();
                contNo= txtContact.getText();
                int No = Integer.parseInt(contNo);
                salary= txtBasic.getText();

                try{
                    pst = con.prepareStatement("INSERT INTO employee(name,address,gender,contactNumber,NIC,salary,position,password,email)VALUES (?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,name);
                    pst.setString(2,address);
                    pst.setString(3,gender);
                    pst.setInt(4,No);
                    pst.setString(5,nic);
                    pst.setString(6,salary);
                    pst.setString(7,position);
                    pst.setString(8,password);
                    pst.setString(9,email);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Added.......");
                    //table_load();

                    txtName.setText("");
                    txtGender.setText("");
                    txtNIC.setText("");
                    txtPosition.setText("");
                    txtPassword.setText("");
                    txtAddress.setText("");
                    txtContact.setText("");
                    txtBasic.setText("");
                    txtEmail.setText("");


                    txtName.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }


            }
        });
        searchEmployeeID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try{

                    String empid = txtSearchID.getText();

                    pst = con.prepareStatement("SELECT name,address,gender,contactNumber,NIC,salary,position,password,email from employee where empID=?");
                    pst.setString(1,empid);
                    ResultSet rs =pst.executeQuery();

                    if (rs.next()==true){
                        
                        String nameUR = rs.getString(1);
                        String addressUR = rs.getString(2);
                        String genderUR = rs.getString(3);
                        int contactUR= rs.getInt(4);
                        String NIC = rs.getString(5);
                        String salaryUR = rs.getString(6);
                        String positionUR = rs.getString(7);
                        String passwordUR = rs.getString(8);
                        String emailUR = rs.getString(9);
                        

                        txtName2.setText(nameUR);
                        txtAddress2.setText(addressUR);
                        txtGender2.setText(genderUR);
                        txtContNo2.setText(String.valueOf(contactUR));
                        txtNIC2.setText(NIC);
                        txtBasic2.setText(salaryUR);
                        txtPosition2.setText(positionUR);
                        txtPassword.setText(passwordUR);
                        txtEmail2.setText(emailUR);


                    }
                    else {

                        txtName.setText("");
                        txtGender.setText("");
                        txtNIC.setText("");
                        txtPosition.setText("");
                        txtPassword.setText("");
                        txtAddress.setText("");
                        txtEmail.setText("");
                        txtContact.setText("");
                        txtBasic.setText("");

                        JOptionPane.showMessageDialog(null,"Invalid Employee Number.");

                    }


                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();

                }

            }
        });
        updateUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               String name,gender,nic,position,password,address,email,contNo,salary,empID;

                name=txtName2.getText();
                address= txtAddress2.getText();
                gender=txtGender2.getText();
                contNo=txtContNo2.getText();
                int No = Integer.parseInt(contNo);
                nic=txtNIC2.getText();
                salary= txtBasic2.getText();
                position=txtPosition2.getText();
                password=txtPassword2.getText();
                email=txtEmail2.getText();
                empID=txtSearchID.getText();


                try{
                    pst = con.prepareStatement("UPDATE employee set name =? ,address= ?,gender= ?,contactNumber =? ,NIC= ?,salary= ?,position =? ,password= ?,email= ? where empID= ?");
                    pst.setString(1,name);
                    pst.setString(2,address);
                    pst.setString(3,gender);
                    pst.setString(4,contNo);
                    pst.setString(5,nic);
                    pst.setString(6,salary);
                    pst.setString(7,position);
                    pst.setString(8,password);
                    pst.setString(9,email);
                    pst.setString(10,empID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated.......");

                   // table_load();
                    txtName2.setText("");
                    txtAddress2.setText("");
                    txtGender2.setText("");
                    txtContNo2.setText("");
                    txtNIC2.setText("");
                    txtBasic2.setText("");
                    txtPosition2.setText("");
                    txtPassword2.setText("");
                    txtEmail2.setText("");

                    txtSearchID.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }


            }
        });
        RemoveUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empID;

                empID = txtSearchID.getText();

                try{
                    pst = con.prepareStatement("DELETE from employee where empID= ?");
                    pst.setString(1,empID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted.......");

                    //table_load();
                    txtName2.setText("");
                    txtAddress2.setText("");
                    txtGender2.setText("");
                    txtContNo2.setText("");
                    txtNIC2.setText("");
                    txtBasic2.setText("");
                    txtPosition2.setText("");
                    txtPassword2.setText("");
                    txtEmail2.setText("");

                    txtSearchID.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("adminDashboadrd");
        frame.setContentPane(new adminDashboadrd().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Set the taskbar icon
        ImageIcon imgIcon = new ImageIcon("src/assets/logo/logo.png");
        Image img = imgIcon.getImage();
        Image newimg = img.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);  // transform it back
        frame.setIconImage(imgIcon.getImage());
    }
    Connection con;
    PreparedStatement pst;
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aems", "root","");
            System.out.println("Database connection successful!");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
