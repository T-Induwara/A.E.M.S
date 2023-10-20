import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hrInterface {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JButton viewEmployee;
    private JTabbedPane addEmployee;
    private JTable table1;
    private JButton updateRemoveButton;
    private JLabel currentEmployee;
    private JLabel empname;
    private JTextField empName;
    private JLabel empGen;
    private JTextField empgen;
    private JPanel position;
    private JTextField pos;
    private JLabel Password;
    private JLabel adress;
    private JTextField empadd;
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
    private JTextField adress2;
    private JLabel contactNumber2;
    private JTextField textField1;
    private JLabel basicsalary;
    private JTextField basicsalary2;
    private JTextField empnic2;
    private JLabel empemail2;
    private JTextField textField2;
    private JButton empupdate;
    private JButton empremove;
    private JButton button1;

    public hrInterface() {
        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);
        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updateRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Dashboard");
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
