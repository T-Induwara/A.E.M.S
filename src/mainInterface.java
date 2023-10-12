import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainInterface {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton button1;
    private JPanel Main;

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainInterface");
        frame.setContentPane(new mainInterface().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public mainInterface() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("GG clicked!");
            }
        });
    }
}
