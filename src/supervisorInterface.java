import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class supervisorInterface {

    private JPanel main;
    private JTabbedPane updateAndRemove;
    private JButton update;
    private JButton addTasks;
    private JButton viewTask;
    private JLabel appTitle;
    private JLabel appLogo;
    private JTextField txtTitle;
    private JTextField txtDate;
    private JTextField txtEmpID;
    private JTextField txtDescription;
    private JTextField txtTaskTime;
    private JButton appoint;
    private JTable table1;
    private JTextField txtTaskTitle;
    private JTextField txtTaskDate;
    private JTextField txtEmployee;
    private JButton updateTask;
    private JTextField txtTaskDesc;
    private JTextField txtTasktime;
    private JButton removeTask;
    private JButton searchEmployeeButton;
    private JTextField txtSearch;
    private JLabel taskTitle;
    private JLabel taskDate;
    private JLabel employeeID;
    private JLabel taskDes;
    private JLabel taskTime;


    public supervisorInterface() {

        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoIcon = new ImageIcon(newimg);  // transform it back
        appLogo.setIcon(logoIcon);

        //Font linking for Application title
        try {
            Font NicoMoji = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/NicoMoji-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment font1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font1.registerFont(NicoMoji);
            appTitle.setFont(NicoMoji);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Main interface buttons
        viewTask.setPreferredSize(new Dimension(250, 40));
        addTasks.setPreferredSize(new Dimension(250, 40));
        update.setPreferredSize(new Dimension(250, 40));

        //Add task tab
            //Buttons
        appoint.setPreferredSize(new Dimension(250, 40));

            //Text Fields
        txtTitle.setPreferredSize(new Dimension(250, 40));
        txtDescription.setPreferredSize(new Dimension(250, 40));
        txtDate.setPreferredSize(new Dimension(250, 40));
        txtEmpID.setPreferredSize(new Dimension(250, 40));
        txtTaskTime.setPreferredSize(new Dimension(250, 40));

        //Update and Remove tab
            //Buttons
        searchEmployeeButton.setPreferredSize(new Dimension(150, 30));
        taskTitle.setPreferredSize(new Dimension(150, 30));
        taskDate.setPreferredSize(new Dimension(150, 30));
        employeeID.setPreferredSize(new Dimension(150, 30));
        taskDes.setPreferredSize(new Dimension(150, 30));
        updateTask.setPreferredSize(new Dimension(150, 30));
        removeTask.setPreferredSize(new Dimension(150, 30));

            //Text Fields
        txtSearch.setPreferredSize(new Dimension(150, 35));
        taskTime.setPreferredSize(new Dimension(150, 30));
        txtTaskTitle.setPreferredSize(new Dimension(150, 30));
        txtTaskDate.setPreferredSize(new Dimension(150, 30));
        txtEmployee.setPreferredSize(new Dimension(150, 30));
        txtTaskDesc.setPreferredSize(new Dimension(150, 30));
        txtTasktime.setPreferredSize(new Dimension(150, 30));



        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Advance Employee Management System");
        frame.setContentPane(new supervisorInterface().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ImageIcon imgIcon = new ImageIcon("src/assets/logo/logo.png");
        Image img = imgIcon.getImage();
        Image newimg = img.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);  // transform it back
        frame.setIconImage(imgIcon.getImage());
    }


}


