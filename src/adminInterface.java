import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;

public class adminInterface {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JButton superBtn;
    private JButton hrBtn;
    private JButton sysadBtn;
    private JLabel appDesc;

    public static void main(String[] args) {
        JFrame frame = new JFrame("adminInterface");
        frame.setContentPane(new adminInterface().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public adminInterface() {
        superBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
