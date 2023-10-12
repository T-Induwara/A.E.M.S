import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.ImageIcon;
import java.io.File;

public class mainInterface {
    private JPanel Main;
    private JLabel appTitle;
    private JLabel appDesc;
    private JLabel appLogo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainInterface");
        frame.setContentPane(new mainInterface().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public mainInterface() {
        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/NicoMoji-Regular.ttf")).deriveFont(80f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            appTitle.setFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        System.out.println("GG");
        System.out.println("TT");
    }
}
