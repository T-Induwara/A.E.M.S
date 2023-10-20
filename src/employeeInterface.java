import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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

    public JPanel getMainPanel() {
        return Main;
    }

    public employeeInterface() {
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

        //Page button actions
        viewEmpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(0);
            }
        });

        upEmpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empTabs.setSelectedIndex(1);
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
