import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JFrame;
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
    private JTextField txtTitle1;
    private JTextField txtTaskDate1;
    private JTextField txtEmpID1;
    private JTextField txtDescription1;
    private JTextField txtTaskTime1;
    private JButton appoint;
    private JTable table1;
    private JTextField txtTaskTitle2;
    private JTextField txtTaskDate2;
    private JButton updateTask;
    private JTextField txtTaskDesc2;
    private JTextField txtTaskTime2;
    private JButton removeTask;
    private JButton searchEmployee;
    private JTextField txtSearch2;
    private JLabel taskTitle2;
    private JLabel taskDate;
    private JLabel taskDescription2;
    private JLabel taskTime2;
    private JScrollPane table;
    private JLabel taskTitle1;
    private JLabel taskDescription1;
    private JLabel taskTme1;
    private JLabel taskDate1;
    private JLabel employeeID1;
    private JComboBox AMPM;
    private JComboBox AMPM2;


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
    void table_load()
    {
        try{
            pst = con.prepareStatement("SELECT * FROM tasks");
            ResultSet rs =pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
    }

    public supervisorInterface() {
        connect();
        table_load();

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
        txtTitle1.setPreferredSize(new Dimension(250, 40));
        txtDescription1.setPreferredSize(new Dimension(250, 40));
        txtTaskDate1.setPreferredSize(new Dimension(250, 40));
        txtEmpID1.setPreferredSize(new Dimension(250, 40));
        txtTaskTime1.setPreferredSize(new Dimension(250, 40));

        //Update and Remove tab
        //Buttons
        searchEmployee.setPreferredSize(new Dimension(150, 30));
        taskTitle2.setPreferredSize(new Dimension(150, 30));
        taskDate.setPreferredSize(new Dimension(150, 30));
        taskDescription2.setPreferredSize(new Dimension(150, 30));
        updateTask.setPreferredSize(new Dimension(150, 30));
        removeTask.setPreferredSize(new Dimension(150, 30));

        //Text Fields
        txtSearch2.setPreferredSize(new Dimension(150, 35));
        taskTime2.setPreferredSize(new Dimension(150, 30));
        txtTaskTitle2.setPreferredSize(new Dimension(150, 30));
        txtTaskDate2.setPreferredSize(new Dimension(150, 30));
        txtTaskDesc2.setPreferredSize(new Dimension(150, 30));
        txtTaskTime2.setPreferredSize(new Dimension(150, 30));

        //Add task tab



        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        updateTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskTitle,taskDate,taskDesc,taskTime,empID;

                taskTitle=txtTaskTitle2.getText();
                taskDate= txtTaskDate2.getText();
                taskDesc=txtTaskDesc2.getText();
                taskTime=txtTaskTime2.getText();
                empID=txtSearch2.getText();

                try{
                    pst = con.prepareStatement("UPDATE tasks set taskTitle =? ,description= ?,date= ?,time= ? where empID= ?");
                    pst.setString(1,taskTitle);
                    pst.setString(2,taskDate);
                    pst.setString(3,taskDesc);
                    pst.setString(4,taskTime);
                    pst.setString(5,empID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated.......");

                    table_load();
                    txtTaskTitle2.setText("");
                    txtTaskDate2.setText("");
                    txtTaskDesc2.setText("");
                    txtTaskTime2.setText("");
                    txtSearch2.setText("");

                    txtTaskTitle2.requestFocus();
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

                empID = txtSearch2.getText();

                try{
                    pst = con.prepareStatement("DELETE from tasks where empID = ?");
                    pst.setString(1,empID);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted.......");

                    table_load();


                    txtTaskTitle2.setText("");
                    txtTaskDesc2.setText("");
                    txtTaskDate2.setText("");
                    txtTaskTime2.setText("");
                    txtTaskTitle2.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }


            }
        });
        searchEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    String empid = txtSearch2.getText();
                    pst = con.prepareStatement("SELECT taskTitle,description,date,time from tasks where empID=?");
                    pst.setString(1,empid);
                    ResultSet rs =pst.executeQuery();

                    if (rs.next()==true){
                        String taskTitle = rs.getString(1);
                        String taskDes = rs.getString(2);
                        String taskDate = rs.getString(3);
                        String taskTime = rs.getString(4);


                        txtTaskTitle2.setText(taskTitle);
                        txtTaskDesc2.setText(taskDes);
                        txtTaskDate2.setText(taskDate);

                        txtTaskTime2.setText(taskTime);

                    }
                    else {

                        txtTaskTitle2.setText("");
                        txtTaskDesc2.setText("");
                        txtTaskDate2.setText("");
                        txtTaskTime2.setText("");


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

                taskTitle= txtTitle1.getText();
                taskDate= txtTaskDate1.getText();
                taskDesc = txtDescription1.getText();
                taskTime = txtTaskTime1.getText();
                empID = txtEmpID1.getText();

                try{
                    pst = con.prepareStatement("INSERT INTO tasks(taskTitle,description,date,time,empID)VALUES (?,?,?,?,?)");
                    pst.setString(1,taskTitle);
                    pst.setString(2,taskDesc);
                    pst.setString(3,taskDate);
                    pst.setString(4,taskTime);
                    pst.setString(5,empID);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Added.......");

                    table_load();


                    txtTitle1.setText("");
                    txtTaskDate1.setText("");
                    txtDescription1.setText("");
                    txtTaskTime1.setText("");
                    txtEmpID1.setText("");
                    txtTitle1.requestFocus();
                }

                catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });


        AMPM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 =txtTaskTime1.getText()+AMPM.getSelectedItem().toString();

                txtTaskTime1.setText(value1);
            }
        });
        AMPM2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value2 =txtTaskTime2.getText()+AMPM2.getSelectedItem().toString();

                txtTaskTime2.setText(value2);
            }
        });
    }




    }





