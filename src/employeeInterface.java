import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class employeeInterface {
    private JPanel Main;
    private JLabel appLogo;
    private JButton viewEmpBtn;
    private JButton upEmpBtn;
    private JButton reqLeaveBtn;
    private JButton reqWithdrawBtn;
    private JTabbedPane tabbedPane1;

    public employeeInterface() {
        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        viewEmpBtn.setPreferredSize(new Dimension(250, 40));
        upEmpBtn.setPreferredSize(new Dimension(250, 40));
        reqLeaveBtn.setPreferredSize(new Dimension(250, 40));
        reqWithdrawBtn.setPreferredSize(new Dimension(250, 40));
        viewEmpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
