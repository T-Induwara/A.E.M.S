import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class hrInterface {
    private JPanel Main;
    private JLabel appLogo;
    private JButton viewEmployee;
    private JTable empTable;
    private JButton updateRemoveButton;
    private JLabel currentEmployee;
    private JLabel empname;
    private JTextField empName1;
    private JLabel empGen;
    private JTextField empGen1;
    private JPanel position;
    private JTextField empNic2;
    private JLabel Password;
    private JLabel adress;
    private JTextField empAddress;
    private JLabel empcon;
    private JTextField empCon;
    private JLabel empsal;
    private JTextField empSal;
    private JLabel empEmail;
    private JTextField empemail;
    private JPasswordField empPassword;
    private JTextField empSearch;
    private JTextField empName2;
    private JLabel empgen2;
    private JTextField empGen2;
    private JLabel Nic2;
    private JLabel empposition;
    private JTextField empposition2;
    private JLabel empAdress2;
    private JTextField address2;
    private JLabel contactNumber2;
    private JTextField connu;
    private JLabel basicsalary;
    private JTextField basicsalary2;
    private JTextField empnic2;
    private JLabel empemail2;
    private JTextField empemaiL;
    private JButton empupdate;
    private JButton empremove;
    private JButton emopsearch;
    private JLabel addemp;
    private JButton addemployee;
    private JLabel empPos;
    private JButton addEmployee1;
    private JTabbedPane hrTabs;
    private JTextField emppos1;
    private JPasswordField empPassword1;
    private JLabel appTitle;
    private Connection con;
    private PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
    }

    public hrInterface() {
        //DB Connection codes
        DBCredentials dbCons = new DBCredentials();//Creating DB Class object
        dbCons.connect();//Calling connection method in DBCredentials class

        con = DBCredentials.getConnection();//Get con object from the DB Credentials class
        pst = DBCredentials.getPreparedStatement();//Get pst object from the DB Credentials class

        table_load();  //load employee table
        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        //Font linking for Application title
        try {
            Font NicoMoji = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/NicoMoji-Regular.ttf")).deriveFont(40f);
            GraphicsEnvironment font1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font1.registerFont(NicoMoji);
            appTitle.setFont(NicoMoji);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            viewEmployee.setFont(RobotoBlack);
            updateRemoveButton.setFont(RobotoBlack);
            addemployee.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        viewEmployee.setPreferredSize(new Dimension(250, 40));
        updateRemoveButton.setPreferredSize(new Dimension(250, 40));
        addemployee.setPreferredSize(new Dimension(250, 40));

        addEmployee1.setPreferredSize(new Dimension(200, 40));
        empupdate.setPreferredSize(new Dimension(200, 40));
        empremove.setPreferredSize(new Dimension(200, 40));

        viewEmployee.addActionListener(new ActionListener() { //view employee
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try{

                        pst = con.prepareStatement("select empID,name,address,gender,contactNUmber,NIC,salary,position,email from employee");
                        ResultSet rs = pst.executeQuery();

                        empTable.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    catch (SQLException a)
                    {
                        a.printStackTrace();
                    }
                }

            }
        });

        updateRemoveButton.addActionListener(new ActionListener() { //update remove button
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
        addEmployee1.addActionListener(new ActionListener() { //add employee part
            @Override
            public void actionPerformed(ActionEvent e) {

                String empname1, empgen1, Emppos1, Emppassword, EmpAdress, EmpCon, EmpSal, Empemail,empnic;



                empname1=empName1.getText();
                EmpAdress=empAddress.getText();
                empgen1=empGen1.getText();
                EmpCon=empCon.getText();
                empnic=empNic2.getText();
                EmpSal=empSal.getText();
                Emppos1=emppos1.getText();
                Emppassword=empPassword.getText();
                Empemail=empemail.getText();


                try{ // sql exception
                    pst = con.prepareStatement("INSERT INTO employee(name,address,gender,contactNumber,NIC,salary,position,password,email)VALUES (?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,empname1);
                    pst.setString(2,EmpAdress);
                    pst.setString(3,empgen1);
                    pst.setString(4,EmpCon );
                    pst.setString(5,empnic);
                    pst.setString(6,EmpSal);
                    pst.setString(7,Emppos1);
                    pst.setString(8,Emppassword);
                    pst.setString(9,Empemail);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Adedd.......");

                    table_load(); //after update view employee table

                    empName1.setText("");
                    empAddress.setText("");
                    empGen1.setText("");
                    empCon.setText("");
                    empNic2.setText("");
                    empSal.setText("");
                    emppos1.setText("");
                    empPassword.setText("");
                    empemail.setText("");

                    empName1.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace(); //view error
                }


            }
        });




        empremove.addActionListener(new ActionListener() { //employye remove part
            @Override
            public void actionPerformed(ActionEvent e) {


            String empiD;
            empiD=empSearch.getText();
            try { // handle sql excepton

                pst = con.prepareStatement("delete from employee where empiD = ?");
                pst.setString(1,empiD);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");

                table_load();

                empName2.setText("");
                empGen2.setText("");
                empnic2.setText("");
                empposition2.setText("");
                address2.setText("");
                connu.setText("");
                basicsalary2.setText("");
                empemaiL.setText("");


                empSearch.requestFocus();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }


            }
        });
        emopsearch.addActionListener(new ActionListener() { //search employee part
            @Override
            public void actionPerformed(ActionEvent e) {
                try{


                    String empid= empSearch.getText();
                    pst = con.prepareStatement("select name,address,gender,contactNumber,NIC,salary,position,password,email from employee where empID = ?");

                    pst.setString(1, empid);

                    ResultSet rs=pst.executeQuery();

                    if(rs.next()==true) // check there are in return in value
                    {
                        String name = rs.getString(1);
                        String address = rs.getString(2);
                        String gender = rs.getString(3);
                        int contactNumber = rs.getInt(4);
                        String NIC = rs.getString(5);
                        String salary = rs.getString(6);
                        String position = rs.getString(7);
                        String password = rs.getString(8);
                        String email = rs.getString(9);

                        empName2.setText(name);
                        address2.setText(address);
                        empGen2.setText(gender);
                        String number=String.valueOf(contactNumber);
                        connu.setText(number);
                        empnic2.setText(NIC);
                        basicsalary2.setText(salary);
                        empposition2.setText(position);
                        empPassword1.setText(password);
                        empemaiL.setText(email);
                    }
                    else
                    {
                        empName2.setText("");
                        empGen2.setText("");
                        address2.setText("");
                        connu.setText("");
                        empposition2.setText("");
                        connu.setText("");
                        basicsalary2.setText("");
                        empnic2.setText("");
                        empemaiL.setText("");

                        JOptionPane.showMessageDialog(null, "Invalid Employee Number");
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewEmployee.addActionListener(new ActionListener() { //view employee tab
            @Override
            public void actionPerformed(ActionEvent e) {

                hrTabs.setSelectedIndex(0);
                table_load();
            }
        });
        addemployee.addActionListener(new ActionListener() { //add employee tab
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTabs.setSelectedIndex(1);
            }
        });
        updateRemoveButton.addActionListener(new ActionListener() { //update employee tab
            @Override
            public void actionPerformed(ActionEvent e) {
                hrTabs.setSelectedIndex(2);
            }
        });
        empupdate.addActionListener(new ActionListener() { //employee update part
            @Override
            public void actionPerformed(ActionEvent e) {

                String emname,ADDress,gender,contactnumber,nIc,salary,positioN,email,empid,password;

                emname=empName2.getText();
                ADDress=address2.getText();
                gender=empGen2.getText();
                contactnumber=connu.getText();
                nIc=empnic2.getText();
                salary=basicsalary2.getText();
                positioN=empposition2.getText();
                password=empPassword1.getText();
                email=empemaiL.getText();
                empid=empSearch.getText();
                try{ // sql exception

                    pst = con.prepareStatement("update employee set name=?,address=?,gender=?,contactNumber=?,NIC=?,salary=?,position=?,password=?,email=? Where empID=?");

                    pst.setString(1, emname);
                    pst.setString(2 ,ADDress);
                    pst.setString(3,gender );
                    int Number= Integer.parseInt(contactnumber);
                    pst.setInt(4, Number);
                    pst.setString(5,nIc );
                    pst.setString(6,salary );
                    pst.setString(7,positioN );
                    pst.setString(8, password);
                    pst.setString(9,email) ;
                    pst.setString(10,empid) ;



                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");

                    table_load(); // after update view employee table

                    empName2.setText("");
                    address2.setText("");
                    empGen2.setText("");
                    connu.setText("");
                    empnic2.setText("");
                    basicsalary2.setText("");
                    empposition2.setText("");
                    empPassword1.setText("");
                    empemaiL.setText("");

                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
    void table_load() //view employee table
    {


        try{
            pst = con.prepareStatement("SELECT empID,name,address,gender,contactNumber,NIC,salary,position,email FROM employee");
            ResultSet rs =pst.executeQuery();
            empTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }

    }
    public static void main(String[] args) { // main function
        JFrame frame = new JFrame("A E M S - H R Dashboard");
        frame.setContentPane(new hrInterface().Main);
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

}



