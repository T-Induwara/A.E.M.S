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

public class employeeLogin {
    private JPanel Main;
    private JLabel appTitle;
    private JLabel appDesc;
    private JTextField empUsername;
    private JPasswordField empPass;
    private JButton loginBtn;
    private JButton returnBtn;
    private JLabel appLogo;
    int passEmpID = 2;
    private Connection con;
    private PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
    }

    public employeeLogin() {
        //DB Connection codes
        DBCredentials dbCons = new DBCredentials();//Creating DB Class object
        dbCons.connect();//Calling connection method in DBCredentials class

        con = DBCredentials.getConnection();//Get con object from the DB Credentials class
        pst = DBCredentials.getPreparedStatement();//Get pst object from the DB Credentials class

        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        //Font linking for Application title
        try {
            Font NicoMoji = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/NicoMoji-Regular.ttf")).deriveFont(80f);
            GraphicsEnvironment font1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font1.registerFont(NicoMoji);
            appTitle.setFont(NicoMoji);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application description
        try {
            Font Roboto = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment font2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font2.registerFont(Roboto);
            appDesc.setFont(Roboto);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            loginBtn.setFont(RobotoBlack);
            returnBtn.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        empUsername.setPreferredSize(new Dimension(400, 40));
        empPass.setPreferredSize(new Dimension(400, 40));

        empUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (empUsername.getText().isEmpty()) {
                    empUsername.setText("Enter your employee email");
                }
                else{
                    //Nothing happening when non focus
                    empUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (empUsername.getText().isEmpty()) {
                    empUsername.setText("Enter your employee email");
                }
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail,userPass;

                try {
                    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    userEmail = empUsername.getText();
                    //Convert String array into String variable
                    char[] userPassword = empPass.getPassword();
                    userPass = new String(userPassword);

                    if(!userEmail.matches(emailPattern)){
                        JOptionPane.showMessageDialog(null,"Invalid email address!");
                    }
                    else{
                        pst = con.prepareStatement("SELECT empID,email,password FROM employee WHERE position = 'Employee' AND email = ?");
                        pst.setString(1, userEmail);
                        ResultSet rs = pst.executeQuery();

                        if(rs.next()==true){
                            String empID = rs.getString(1);
                            passEmpID = Integer.parseInt(empID);
                            //check code
                            System.out.println("My ID in login page is "+passEmpID);
                            String empEmail = rs.getString(2);
                            String empPass = rs.getString(3);
                            System.out.println("Pass is "+empPass);
                            System.out.println("Entered Pass is "+userPass);

                            if(userPass.equals(empPass)){//Compare user given password and the password in the db
                                //To hide current JPanel
                                Main.setVisible(false);

                                employeeInterface employeeInterface = new employeeInterface();
                                //Assign JPanel of adminInterface.java to the adminMainPanel object
                                JPanel empPanel = employeeInterface.getMainPanel();
                                //Passing empID from login page to employee dashboard

                                mainInterface.frame.setContentPane(empPanel);
                                mainInterface.frame.validate();
                                mainInterface.frame.repaint();
                                //Pass the employee ID to employee interface from the login interface
                                employeeInterface.setEmpID(passEmpID);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Password is incorrect!");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Please check your email again!");
                        }
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setVisible(false);

                mainInterface mainInterface = new mainInterface();
                //Assign JPanel of adminInterface.java to the adminMainPanel object
                JPanel mainPanel = mainInterface.getMainPanel();

                mainInterface.frame.setContentPane(mainPanel);
                mainInterface.frame.validate();
                mainInterface.frame.repaint();
            }
        });
    }

    //Return loggedIN empID
    public int getEmpID(){
        return passEmpID;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Employee Login Page");
        frame.setContentPane(new employeeLogin().Main);
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
