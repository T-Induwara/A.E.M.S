import javax.swing.*;

public class hrLogin {
    private JPanel Main;
    private JLabel appLogo;
    private JLabel appTitle;
    private JTextField hrUsername;
    private JTextField hrPass;
    private JButton loginBtn;
    private JButton returnBtn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("hrLogin");
        frame.setContentPane(new hrLogin().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
