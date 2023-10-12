import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class mainInterface {
    private JPanel Main;

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainInterface");
        frame.setContentPane(new mainInterface().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public mainInterface() {
        System.out.println("GG");
    }
}
