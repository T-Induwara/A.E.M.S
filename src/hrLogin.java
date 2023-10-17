import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class hrLogin {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JTextField hrUsername;
    private JTextField hrPass;
    private JButton loginBtn;
    private JButton returnBtn;
    private JLabel appDesc;

    public JPanel getMainPanel() {
        return Main;
    }

    public hrLogin() {
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
            loginBtn.setFont(RobotoBlack);
            returnBtn.setFont(RobotoBlack);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        hrUsername.setPreferredSize(new Dimension(400, 40));
        hrPass.setPreferredSize(new Dimension(400, 40));
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("hrLogin");
        frame.setContentPane(new hrLogin().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
