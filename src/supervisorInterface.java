import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.text.JTextComponent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class supervisorInterface {

    public static void main(String[] args) {
        JFrame frame = new JFrame("A E M S - Advance Employee Management System");
        frame.setContentPane(new supervisorInterface().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ImageIcon imgIcon = new ImageIcon("src/assets/logo/logo.png");
        Image img = imgIcon.getImage();
        Image newimg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);  // transform it back
        frame.setIconImage(imgIcon.getImage());
    }

    private JPanel main;
    private JTabbedPane superTabs;
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
    private JScrollPane db;

    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aems", "timax", "Masseffect34c1#@");
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public supervisorInterface() {
        connect();

        ImageIcon logoIcon = new ImageIcon("src/assets/logo/logo.png");
        Image image = logoIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
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

        //Add task tab



        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        updateTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskTitle,taskDes,taskDate,taskTime,employeeID;

                taskTitle=txtTaskTitle.getText();
                taskDes= txtTaskDesc.getText();
                taskDate=txtTaskDate.getText();
                taskTime=txtTasktime.getText();
                employeeID=txtEmployee.getText();

                try{
                    pst = con.prepareStatement("UPDATE tasks set taskTite =? ,description= ?,date= ?,time= ?,empID= ?  where empID= ?");
                    pst.setString(1,taskTitle);
                    pst.setString(2,taskDes);
                    pst.setString(3,taskDate);
                    pst.setString(4,taskTime);
                    pst.setString(5,employeeID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated.......");

                    //table_load();
                    txtTaskTitle.setText("");
                    txtTaskDesc.setText("");
                    txtTaskDate.setText("");
                    txtTasktime.setText("");
                    txtEmployee.setText("");
                    txtTaskTitle.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }



            }
        });
        removeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empID;

                empID = txtEmployee.getText();

                try{
                    pst = con.prepareStatement("DELETE from tasks where empID = ?");
                    pst.setString(1,empID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted.......");

                    //table_load();
                    txtTaskTitle.setText("");
                    txtTaskDesc.setText("");
                    txtTaskDate.setText("");
                    txtTasktime.setText("");
                    txtEmployee.setText("");
                    txtTaskTitle.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }


            }
        });
        searchEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    String empid = txtSearch.getText();
                    pst = con.prepareStatement("SELECT taskTitle,description,date,time,empID from tasks where empID=?");
                    pst.setString(1,empid);
                    ResultSet rs =pst.executeQuery();

                    if (rs.next()==true){
                        String taskTitle = rs.getString(1);
                        String taskDes = rs.getString(2);
                        String taskDate = rs.getString(3);
                        String taskTime = rs.getString(4);
                        String employeeID = rs.getString(5);


                        txtTaskTitle.setText(taskTitle);
                        txtTaskDesc.setText(taskDes);
                        txtTaskDate.setText(taskDate);
                        txtTasktime.setText(taskTime);
                        txtEmployee.setText(employeeID);

                    }
                    else {

                        txtTaskTitle.setText("");
                        txtTaskDesc.setText("");
                        txtTaskDate.setText("");
                        txtTasktime.setText("");
                        txtEmployee.setText("");

                        JOptionPane.showMessageDialog(null,"Invalid Employee Number.");

                    }


                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();

                }


            }
        });
        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(0);

            }
        });
        addTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(1);

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(2);

            }
        });
        appoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskTitle,taskDate,taskDesc,taskTime,empID;

                taskTitle= txtTitle.getText();
                taskDate= txtTaskDate.getText();
                taskDesc =txtDescription.getText();
                taskTime = txtTaskTime.getText();
                empID = txtEmpID.getText();

                try{
                    pst = con.prepareStatement("INSERT INTO tasks(taskTitle,description,date,time,empID)VALUES (?,?,?,?,?)");
                    pst.setString(1,taskTitle);
                    pst.setString(2,taskDesc);
                    pst.setString(3,taskDate);
                    pst.setString(4,taskTime);
                    pst.setString(5,empID);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Added.......");
                    //table_load();
                    txtTitle.setText("");
                    txtTaskDate.setText("");
                    txtDescription.setText("");
                    txtTaskTime.setText("");
                    txtEmpID.setText("");
                    txtTitle.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });


           }
}




