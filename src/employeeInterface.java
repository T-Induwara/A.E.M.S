import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JFrame;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class employeeInterface {
    JPanel Main;
    private JLabel appLogo;
    private JButton viewEmpBtn;
    private JButton upEmpBtn;
    private JButton reqLeaveBtn;
    private JButton reqWithdrawBtn;
    private JTabbedPane empTabs;
    private JLabel appTitle;
    private JLabel tab1Title;
    private JLabel tab2Title;
    private JLabel tab3Title;
    private JLabel tab4Title;
    private JPanel empProfile;
    private JPanel empUProfile;
    private JPanel empRLeave;
    private JPanel empWLeave;
    private JTextField eNameField;
    private JTextField eEmailField;
    private JTextField eGenField;
    private JTextField eAddField;
    private JTextField eNumField;
    private JButton upDetailsBtn;
    private JTextField rDateField;
    private JTextField rNoteField;
    private JTextField rTimeField;
    private JTextField rInfoField;
    private JButton requestLeaveButton;
    private JTable table1;
    private JButton withLeaveReqBtn;
    private JTextField reqNum;
    private JLabel frmName;
    private JLabel frmAdd;
    private JLabel frmMail;
    private JLabel frmNum;
    private JLabel frmNIC;
    private JLabel frmPosition;
    private JLabel frmGender;
    private int newEmpID;

    Connection con;
    PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
    }

    public void setEmpID(int empID) {
        newEmpID = empID;
    }

    public void autoLoadEmpData(){
        //auto load the emp info
        String empName,empAddress,empMail,empPosition,empGender,empNIC;
        int empNumber;
        try {
            System.out.println("My function first id is "+newEmpID);

            pst = con.prepareStatement("SELECT name,address,gender,contactNumber,nic,position,email FROM employee WHERE empID = ?");
            pst.setInt(1, newEmpID);
            ResultSet rsAuto = pst.executeQuery();

            if(rsAuto.next()==true){
                empName = rsAuto.getString(1);
                empAddress = rsAuto.getString(2);
                empGender = rsAuto.getString(3);
                empNumber = rsAuto.getInt(4);
                empNIC = rsAuto.getString(5);
                empPosition = rsAuto.getString(6);
                empMail = rsAuto.getString(7);

                frmName.setText(empName);
                frmAdd.setText(empAddress);
                frmMail.setText(empMail);
                //Convert contact number received from the database from int datatype to string.
                String eANumber = String.valueOf(empNumber);
                frmNum.setText(eANumber);
                frmNIC.setText(empNIC);
                frmPosition.setText(empPosition);
                frmGender.setText(empGender);
            }
            else{
                JOptionPane.showMessageDialog(null,"Please, contact the company support or HR");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("SQL Error: " + ex.getMessage());
        }
        //auto load code ends from here
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aems", "timax","Masseffect34c1#@");
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

    public employeeInterface() {
        connect();
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

        //Font linking for Application Tab Titles
        try {
            Font Roboto = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment font2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font2.registerFont(Roboto);
            tab1Title.setFont(Roboto);
            tab2Title.setFont(Roboto);
            tab3Title.setFont(Roboto);
            tab4Title.setFont(Roboto);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            viewEmpBtn.setFont(RobotoBlack);
            upEmpBtn.setFont(RobotoBlack);
            reqLeaveBtn.setFont(RobotoBlack);
            reqWithdrawBtn.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        viewEmpBtn.setPreferredSize(new Dimension(250, 40));
        upEmpBtn.setPreferredSize(new Dimension(250, 40));
        reqLeaveBtn.setPreferredSize(new Dimension(250, 40));
        reqWithdrawBtn.setPreferredSize(new Dimension(250, 40));

        //Btn size change for update emp details tab
        upDetailsBtn.setPreferredSize(new Dimension(200, 40));

        //Btn size change for request leave tab
        requestLeaveButton.setPreferredSize(new Dimension(200, 40));

        //Btn size change for withdraw leave request tab
        withLeaveReqBtn.setPreferredSize(new Dimension(200, 40));

        //View employee information tab txtfield sizes
        eNameField.setPreferredSize(new Dimension(250, 40));
        eEmailField.setPreferredSize(new Dimension(250, 40));
        eGenField.setPreferredSize(new Dimension(250, 40));
        eAddField.setPreferredSize(new Dimension(250, 40));
        eNumField.setPreferredSize(new Dimension(250, 40));

        //Update employee information tab txtfield sizes
        rDateField.setPreferredSize(new Dimension(250, 40));
        rInfoField.setPreferredSize(new Dimension(250, 40));
        rNoteField.setPreferredSize(new Dimension(250, 40));
        rTimeField.setPreferredSize(new Dimension(250, 40));

        //Withdraw request tab txtfield sizes
        reqNum.setPreferredSize(new Dimension(250, 40));
        autoLoadEmpData();
        //Page button actions
        viewEmpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(0);

                try {
                    System.out.println("My id is "+newEmpID);

                    pst = con.prepareStatement("SELECT name,address,gender,contactNumber,nic,position,email FROM employee WHERE empID = ?");
                    pst.setInt(1, newEmpID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        String eName = rs.getString(1);
                        String eAdd = rs.getString(2);
                        String eGender = rs.getString(3);
                        int eNum = rs.getInt(4);
                        String eNIC = rs.getString(5);
                        String ePosition = rs.getString(6);
                        String eEmail = rs.getString(7);

                        frmName.setText(eName);
                        frmAdd.setText(eAdd);
                        frmMail.setText(eEmail);
                        //Convert contact number received from the database from int datatype to string.
                        String eNumber = String.valueOf(eNum);
                        frmNum.setText(eNumber);
                        frmNIC.setText(eNIC);
                        frmPosition.setText(ePosition);
                        frmGender.setText(eGender);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please, contact the company support or HR");
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        upEmpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(1);

                try {
                    pst = con.prepareStatement("SELECT name,address,gender,contactNumber,email FROM employee WHERE empID = ?");
                    pst.setInt(1, newEmpID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        String empUName = rs.getString(1);
                        String empUAddress = rs.getString(2);
                        String empUGender = rs.getString(3);
                        int empUNumber = rs.getInt(4);
                        String empUNumString = String.valueOf(empUNumber);
                        String empUMail = rs.getString(5);

                        eNameField.setText(empUName);
                        eAddField.setText(empUAddress);
                        eEmailField.setText(empUMail);
                        eNumField.setText(empUNumString);
                        eGenField.setText(empUGender);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please, contact the company support or HR");
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        reqLeaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(2);
            }
        });
        reqWithdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(3);
            }
        });

        //Update button functionality on Update profile tab
        upDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String empUDName,empUDAddress,empUDGender,empUDMail,empUDNumber;

                    empUDName = eNameField.getText();
                    empUDAddress = eAddField.getText();
                    empUDMail = eEmailField.getText();
                    empUDNumber = eNumField.getText();
                    int empUDNewNumber = Integer.parseInt(empUDNumber);
                    empUDGender = eGenField.getText();

                    pst = con.prepareStatement("UPDATE employee SET name=?,address=?,gender=?,contactNumber=?,email=? WHERE empID = ?");
                    pst.setString(1, empUDName);
                    pst.setString(2, empUDAddress);
                    pst.setString(3, empUDGender);
                    pst.setInt(4, empUDNewNumber);
                    pst.setString(5, empUDMail);
                    pst.setInt(6, newEmpID);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Your details have updated!");

                    eNameField.setText(empUDName);
                    eAddField.setText(empUDAddress);
                    eEmailField.setText(empUDMail);
                    eNumField.setText(empUDNumber);
                    eGenField.setText(empUDGender);

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        requestLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String empRDate,empRTime,empRNote,empRExtra;

                    empRDate = rDateField.getText();
                    empRTime = rTimeField.getText();
                    empRNote = rNoteField.getText();
                    empRExtra = rInfoField.getText();

                    pst = con.prepareStatement("INSERT INTO request(title,description,date,time,empID) VALUES(?,?,?,?,?)");
                    pst.setString(1, empRNote);
                    pst.setString(2, empRExtra);
                    pst.setString(3, empRDate);
                    pst.setString(4, empRTime);
                    pst.setInt(5, newEmpID);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Your request sent successfully!");

                    rDateField.setText("");
                    rTimeField.setText("");
                    rNoteField.setText("");
                    rInfoField.setText("");

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Dashboard");
        frame.setContentPane(new employeeInterface().Main);
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
