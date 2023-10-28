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

public class hrLogin {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JTextField hrUsername;
    private JPasswordField hrPass;
    private JButton loginBtn;
    private JButton returnBtn;
    private JLabel appDesc;
    private Connection con;
    private PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
    }

    public hrLogin() {
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

        hrUsername.setPreferredSize(new Dimension(400, 40));
        hrPass.setPreferredSize(new Dimension(400, 40));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hrEmail,hrPassLocal;

                try {
                    hrEmail = hrUsername.getText();
                    //Convert String array into String variable
                    char[] hrPassword = hrPass.getPassword();
                    hrPassLocal = new String(hrPassword);

                    pst = con.prepareStatement("SELECT empID,email,password FROM employee WHERE position = 'HR' AND email = ?");
                    pst.setString(1, hrEmail);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        String empID = rs.getString(1);
                        //passEmpID = Integer.parseInt(empID);
                        //check code
                        System.out.println("My ID in login page is "+empID);
                        String empEmail = rs.getString(2);
                        String empPass = rs.getString(3);
                        System.out.println("Pass is "+empPass);
                        System.out.println("Entered Pass is "+empPass);

                        if(hrPassLocal.equals(empPass)){//Compare user given password and the password in the db
                            //To hide current JPanel
                            Main.setVisible(false);

                            hrInterface hrInterface = new hrInterface();
                            //Assign JPanel of adminInterface.java to the adminMainPanel object
                            JPanel hrPanel = hrInterface.getMainPanel();
                            //Passing empID from login page to employee dashboard

                            mainInterface.frame.setContentPane(hrPanel);
                            mainInterface.frame.validate();
                            mainInterface.frame.repaint();
                            //Pass the employee ID to employee interface from the login interface
                            //employeeInterface.setEmpID(passEmpID);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Password is incorrect!");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please check your email again!");
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
                //To hide current JPanel
                Main.setVisible(false);

                adminInterface adminInterface = new adminInterface();
                //Assign JPanel of adminInterface.java to the adminMainPanel object
                JPanel adminPanel = adminInterface.getMainPanel();

                mainInterface.frame.setContentPane(adminPanel);
                mainInterface.frame.validate();
                mainInterface.frame.repaint();
            }
        });
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - H R Login Page");
        frame.setContentPane(new hrLogin().Main);
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
