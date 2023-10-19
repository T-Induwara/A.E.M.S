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
    private JButton searchName;
    private JTextField txtSearch;
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
    private JTextField textField1;

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
        txtSearch.setPreferredSize(new Dimension(150, 30));
        txtName2.setPreferredSize(new Dimension(150, 30));
        txtAddress2.setPreferredSize(new Dimension(150, 30));
        txtGender2.setPreferredSize(new Dimension(150, 30));
        txtContNo2.setPreferredSize(new Dimension(150, 30));
        txtNIC2.setPreferredSize(new Dimension(150, 30));
        txtBasic2.setPreferredSize(new Dimension(150, 30));
        txtPosition2.setPreferredSize(new Dimension(150, 30));
        txtEmail2.setPreferredSize(new Dimension(150, 30));

        //Buttons
        searchName.setPreferredSize(new Dimension(80, 30));
        updateUR.setPreferredSize(new Dimension(150, 40));
        RemoveUR.setPreferredSize(new Dimension(150, 40));


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

            }
        });
        searchName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updateUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        RemoveUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
