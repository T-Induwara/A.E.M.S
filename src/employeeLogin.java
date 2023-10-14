import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class employeeLogin {
    private JPanel Main;
    private JLabel appTitle;
    private JLabel appDesc;
    private JTextField employeeEmailTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JButton button1;
    private JButton returnButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("employeeLogin");
        frame.setContentPane(new employeeLogin().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public employeeLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
