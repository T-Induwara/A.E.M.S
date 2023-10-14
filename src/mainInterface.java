import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class mainInterface {
    private static JFrame frame;
    private JPanel Main;
    private JLabel appTitle;
    private JLabel appDesc;
    private JLabel appLogo;
    private JButton adminBtn;
    private JButton empBtn;

    public static void main(String[] args) {
        mainInterface mainInterface = new mainInterface();
        mainInterface.frame = new JFrame("A E M S - Advance Employee Management System");
        mainInterface.frame.setContentPane(mainInterface.Main);
        mainInterface.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainInterface.frame.pack();
        mainInterface.frame.setVisible(true);

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

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            adminBtn.setFont(RobotoBlack);
            empBtn.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Set the size of adminBtn and empBtn
        adminBtn.setPreferredSize(new Dimension(200, 40));
        empBtn.setPreferredSize(new Dimension(200, 40));

        System.out.println("GG");
        System.out.println("TT");
        adminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To hide current JPanel
                Main.setVisible(false);

                adminInterface adminInterface = new adminInterface();
                //Assign JPanel of adminInterface.java to the adminMainPanel object
                JPanel adminMainPanel = adminInterface.getMainPanel();

                frame.setContentPane(adminMainPanel);
                frame.validate();
                frame.repaint();
            }
        });
    }

}
