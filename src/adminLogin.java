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

public class adminLogin {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JLabel appDesc;
    private JTextField adminUsername;
    private JPasswordField adminPass;
    private JButton loginBtn;
    private JButton returnBtn;

    private Connection con;
    private PreparedStatement pst;

    public JPanel getMainPanel() {
        return Main;
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

    public adminLogin() {
        connect();

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

        adminUsername.setPreferredSize(new Dimension(400, 40));
        adminPass.setPreferredSize(new Dimension(400, 40));

        adminUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adminUsername.getText().isEmpty()) {
                    adminUsername.setText("Enter your email");
                }
                else{
                    //Nothing happening when non focus
                    adminUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adminUsername.getText().isEmpty()) {
                    adminUsername.setText("Enter your email");
                }
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail,userPass;

                try {
                    userEmail = adminUsername.getText();
                    //Convert String array into String variable
                    char[] userPassword = adminPass.getPassword();
                    userPass = new String(userPassword);

                    pst = con.prepareStatement("SELECT empID,email,password FROM employee WHERE position = 'Admin' AND email = ?");
                    pst.setString(1, userEmail);
                    System.out.println("User email is "+userEmail);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        String empID = rs.getString(1);
                        int passEmpID = Integer.parseInt(empID);
                        //check code
                        System.out.println("My ID in login page is "+passEmpID);
                        String empEmail = rs.getString(2);
                        String empPass = rs.getString(3);
                        System.out.println("Pass is "+empPass);
                        System.out.println("Entered Pass is "+userPass);

                        if(userPass.equals(empPass)){//Compare user given password and the password in the db
                            //To hide current JPanel
                            Main.setVisible(false);

                            adminDashboadrd adminDashboard = new adminDashboadrd();
                            //Assign JPanel of adminInterface.java to the adminMainPanel object
                            JPanel empPanel = adminDashboard.getMainPanel();
                            //Passing empID from login page to employee dashboard

                            mainInterface.frame.setContentPane(empPanel);
                            mainInterface.frame.validate();
                            mainInterface.frame.repaint();
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
        JFrame frame = new JFrame("A E M S - Administrator Login Page");
        frame.setContentPane(new adminLogin().Main);
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
