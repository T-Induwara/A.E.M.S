import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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
    private JComboBox mfComboBox1;
    private JTextField txtNIC;
    private JTextField txtContact;
    private JTextField txtBasic;
    private JTextField txtPosition;
    private JTextField txtEmail;
    private JTextField txtPassword;
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
    private JLabel tab3Title;
    private JTextField txtSearchID;
    private JPasswordField txtPassword2;
    private JLabel password2;
    private JTable table1;
    private JTextField txtGender;
    private JComboBox hsComboBox1;
    private JLabel gender;
    private JComboBox mfComboBox2;
    private JComboBox hsComboBox2;
    private JLabel tab1Title;
    private JLabel tab2Title;


    private Connection con;
    private PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
    }

    //email validation
    /*
    public boolean isValidEmail(String email) {

        String emailValidation = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailValidation);

    }

     */
    public adminDashboadrd() {

        DBCredentials dbCons = new DBCredentials();
        dbCons.connect();

        //creating an object name as pst using connection class
        con = DBCredentials.getConnection();

        //creating an object name as con using connection class
        pst = DBCredentials.getPreparedStatement();

        table_load();

        //logo added for the interface

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

        //Font linking for Application Tab Titles
        try {
            Font Roboto = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment font2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font2.registerFont(Roboto);
            tab1Title.setFont(Roboto);
            tab2Title.setFont(Roboto);
            tab3Title.setFont(Roboto);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            view.setFont(RobotoBlack);
            add.setFont(RobotoBlack);
            update.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        //main interface buttons

        view.setPreferredSize(new Dimension(250, 40));
        add.setPreferredSize(new Dimension(250, 40));
        update.setPreferredSize(new Dimension(250, 40));

        //Add HR or Supervisor
        //Button
        addAll.setPreferredSize(new Dimension(150, 40));

        //Input text areas
        txtName.setPreferredSize(new Dimension(150, 30));
        txtAddress.setPreferredSize(new Dimension(150, 30));
        mfComboBox1.setPreferredSize(new Dimension(150, 30));
        txtContact.setPreferredSize(new Dimension(150, 30));
        txtNIC.setPreferredSize(new Dimension(150, 30));
        txtBasic.setPreferredSize(new Dimension(150, 30));
        txtPosition.setPreferredSize(new Dimension(150, 30));
        txtEmail.setPreferredSize(new Dimension(150, 30));
        txtPassword.setPreferredSize(new Dimension(150, 30));
        txtGender.setPreferredSize(new Dimension(150, 30));

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


        //add button for main interface
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(1);
            }
        });

        // view button for main interface
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(0);
                table_load();
            }
        });

        //update button for main interface
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminTabs.setSelectedIndex(2);
            }
        });

        //Add details of HR and supervisor in Add tab
        addAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add HR or Supervisor
                try {
                    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    String name,gender,nic,position,password,address,email,contNo,salary,empID;

                    name = txtName.getText();
                    address = txtAddress.getText();
                    gender = txtGender.getText();
                    contNo = txtContact.getText();
                    nic = txtNIC.getText();
                    salary = txtBasic.getText();
                    position = txtPosition.getText();
                    password = txtPassword.getText();
                    email = txtEmail.getText();
                    empID = txtSearchID.getText();

                    if (name.isEmpty() || address.isEmpty() || gender.isEmpty()||contNo.isEmpty() || nic.isEmpty() || position.isEmpty()|| email.isEmpty() ) {

                        JOptionPane.showMessageDialog(null, "Yoy cannot enter values. re-check the text fields...");

                    } else if (!email.matches(emailPattern)) {

                        JOptionPane.showMessageDialog(null, "Please enter a valid email address.");


                    }else if(contNo.matches(".*[a-zA-Z]+.*")){
                        JOptionPane.showMessageDialog(null,"Please check the entered new mobile number!");
                    }
                    else{
                        if(contNo.length() != 10){
                            JOptionPane.showMessageDialog(null,"The mobile number must contain 10 numbers!");
                        }
                        else{
                            int no = Integer.parseInt(contNo);

                            pst = con.prepareStatement("INSERT INTO employee (name ,address ,gender ,contactNumber ,Nic ,salary , position , password , email) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                            pst.setString(1, name);
                            pst.setString(2, address);
                            pst.setString(3, gender);
                            pst.setInt(4, no);
                            pst.setString(5, nic);
                            pst.setString(6, salary);
                            pst.setString(7,position);
                            pst.setString(8,password);
                            pst.setString(9, email);

                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null,"Record added!");
                            txtName.setText(name);
                            txtAddress.setText(address);
                            txtGender.setText(gender);
                            txtContact.setText(contNo);
                            txtNIC.setText(nic);
                            txtBasic.setText(salary);
                            txtPosition.setText(position);
                            txtPassword.setText(password);
                            txtEmail.setText(email);
                        }
                    }

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });

        //search employee details in update or remove tab
        searchEmployeeID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try{

                    String empid = txtSearchID.getText();

                    pst = con.prepareStatement("SELECT name,address,gender,contactNumber,NIC,salary,position,password,email from employee where empID=? AND position='Supervisor'");
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
                        System.out.println("Password is "+passwordUR);
                        String emailUR = rs.getString(9);


                        txtName2.setText(nameUR);
                        txtAddress2.setText(addressUR);
                        txtGender2.setText(genderUR);
                        txtContNo2.setText(String.valueOf(contactUR));
                        txtNIC2.setText(NIC);
                        txtBasic2.setText(salaryUR);
                        txtPosition2.setText(positionUR);
                        txtPassword2.setText(passwordUR);
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

        //update employee details in update or remove tab
        updateUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            try{
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                String name,gender,nic,position,password,address,email,contNo,salary,empID;

                name = txtName2.getText();
                address = txtAddress2.getText();
                gender = txtGender2.getText();
                contNo = txtContNo2.getText();
                nic = txtNIC2.getText();
                salary = txtBasic2.getText();
                position = txtPosition2.getText();
                password = txtPassword2.getText();
                email = txtEmail2.getText();
                empID = txtSearchID.getText();


                //validate tha mandatory fields
                if (name.isEmpty() || address.isEmpty() || gender.isEmpty() || contNo.isEmpty() || nic.isEmpty() || position.isEmpty() || email.isEmpty() ) {

                    JOptionPane.showMessageDialog(null, "You cannot enter values. re-check the text fields...");

                }

                //validate tha email
                if (!email.matches(emailPattern)) {

                    JOptionPane.showMessageDialog(null, "Please enter a valid email address.");


                }

                //validate the Phone number it includes only for number
                else if(contNo.matches(".*[a-zA-Z]+.*")){
                    JOptionPane.showMessageDialog(null,"Please check the entered new mobile number!");
                }

                //validate the length of phone number
                else{
                    if(contNo.length() != 10){
                        JOptionPane.showMessageDialog(null,"The mobile number must contain 10 numbers!");
                    }
                    else{
                        int no = Integer.parseInt(contNo);

                            pst = con.prepareStatement("UPDATE employee SET name=?,address=?,gender=?,contactNumber=?,Nic=?,salary=?, position=?, password=?, email=? WHERE empID = ?");
                            pst.setString(1, name);
                            pst.setString(2, address);
                            pst.setString(3, gender);
                            pst.setInt(4, no);
                            pst.setString(5, nic);
                            pst.setString(6, salary);
                            pst.setString(7,position);
                            pst.setString(8,password);
                            pst.setString(9, email);
                            pst.setString(10,empID);

                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null,"Your details have updated!");
                            txtName2.setText(name);
                            txtAddress2.setText(address);
                            txtGender2.setText(gender);
                            txtContNo2.setText(contNo);
                            txtNIC2.setText(nic);
                            txtBasic2.setText(salary);
                            txtPosition2.setText(position);
                            txtPassword2.setText(password);
                            txtEmail2.setText(email);
                        }
                    }

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //remove employee details in update or remove tab
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

                    table_load();

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

        //gender selection in add tab
        mfComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 = mfComboBox1.getSelectedItem().toString();

                txtGender.setText(value1);

            }
        });

        //HR or supervisor selection in add tab
        hsComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value2 = hsComboBox1.getSelectedItem().toString();

                txtPosition.setText(value2);

            }
        });

        //gender selection in update tab
        mfComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value4 = mfComboBox1.getSelectedItem().toString();

                txtGender2.setText(value4);

            }
        });

        //HR or supervisor selection in update tab
        hsComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value3 = hsComboBox1.getSelectedItem().toString();

                txtPosition2.setText(value3);

            }
        });
    }

    //main function
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

    //table load in view tab

    void table_load()
    {
        try{
            pst = con.prepareStatement("SELECT empID,name,address,gender,contactNumber,NIC,salary,position,email FROM employee");
            ResultSet rs =pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
    }
}
