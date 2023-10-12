import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;

public class mainInterface {
    private JPanel Main;
    private JLabel appTitle;
    private JLabel appDesc;
    private JLabel appLogo;
    private JButton adminBtn;
    private JButton empBtn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Advance Employee Management System");
        frame.setContentPane(new mainInterface().Main);
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

    public mainInterface() {
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

        System.out.println("GG");
        System.out.println("TT");
    }
}
