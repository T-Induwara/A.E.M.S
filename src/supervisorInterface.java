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
    private JComboBox AMPMComboBox;
    private JComboBox AMPM2ComboBox;
    private JLabel tab1Title;
    private JLabel tab2Title;
    private JLabel tab3Title;


    private Connection con;
    private PreparedStatement pst;


    public JPanel getMainPanel() {

        return main;

    }


    void table_load()
    {
        //view appoint task table loading method

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
        DBCredentials dbCons = new DBCredentials();
        dbCons.connect();

        // creating an object name as con using connection class
        con = DBCredentials.getConnection();

        // creating an object name as pst using connection class

        pst = DBCredentials.getPreparedStatement();

        table_load();

        //logo added inti interface

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

        //Font linking for Application Tab Titles
        try {
            Font Roboto = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment font2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font2.registerFont(Roboto);
            tab1Title.setFont(Roboto);
            tab2Title.setFont(Roboto);
            tab3Title.setFont(Roboto);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Font linking for Application btns
        try {
            Font RobotoBlack = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Black.ttf")).deriveFont(20f);
            GraphicsEnvironment font3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font3.registerFont(RobotoBlack);
            viewTask.setFont(RobotoBlack);
            addTasks.setFont(RobotoBlack);
            update.setFont(RobotoBlack);
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


        //view task button in main interface
        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(0);

            }
        });

        //add task button in main interface
        addTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(1);

            }
        });

        //update task button in main interface
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                superTabs.setSelectedIndex(2);

            }
        });

        //tasks adding
        appoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskTitle, taskDate, taskDesc, taskTime, empID;

                // Variable declaration of input fields
                taskTitle = txtTitle1.getText();
                taskDate = txtTaskDate1.getText();
                taskDesc = txtDescription1.getText();
                taskTime = txtTaskTime1.getText();
                empID = txtEmpID1.getText();


                if (taskTitle.isEmpty() || taskDate.isEmpty() || taskTime.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Task Title, Task Date, and Task Time are required fields.");


                    // Check if the employee with the provided empID exists in the database
                }else if (isEmployeeExists(empID)) {

                    // Employee exists, proceed to add the task
                    try {
                        pst = con.prepareStatement("INSERT INTO tasks(taskTitle, description, date, time, empID) VALUES (?, ?, ?, ?, ?)");
                        pst.setString(1, taskTitle);
                        pst.setString(2, taskDesc);
                        pst.setString(3, taskDate);
                        pst.setString(4, taskTime);
                        pst.setString(5, empID);

                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Added.......");
                        table_load();

                        txtTitle1.setText("");
                        txtTaskDate1.setText("");
                        txtDescription1.setText("");
                        txtTaskTime1.setText("");
                        txtEmpID1.setText("");
                        txtTitle1.requestFocus();

                    } catch (SQLException e1) {

                        e1.printStackTrace();
                    }
                } else {

                    // Employee with the provided empID does not exist

                    JOptionPane.showMessageDialog(null, "Employee with ID " + empID + " is wrong employee ID. Please re-check the employee ID. ");
                }
            }

            // Method to check if an employee with the given empID exists in the database
            private boolean isEmployeeExists(String empID) {
                try {
                    pst = con.prepareStatement("SELECT empID FROM employee WHERE empID = ?");
                    pst.setString(1, empID);
                    ResultSet rs = pst.executeQuery();
                    return rs.next(); // If the ResultSet has any rows, the employee exists
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false; // Handle the error as needed
                }
            }
        });


        //Search the tasks
        searchEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //tasks get out from the database

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

        //task updating
        updateTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskTitle,taskDate,taskDesc,taskTime,empID;

                //variable declaration

                taskTitle=txtTaskTitle2.getText();
                taskDate= txtTaskDate2.getText();
                taskDesc=txtTaskDesc2.getText();
                taskTime=txtTaskTime2.getText();
                empID=txtSearch2.getText();

                //update tasks in the database
                try{
                    pst = con.prepareStatement("UPDATE tasks set taskTitle =? ,description= ?,date= ?,time= ? where empID= ?");
                    pst.setString(1,taskTitle);
                    pst.setString(2,taskDesc);
                    pst.setString(3,taskDate);
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

        //removing tasks
        removeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empID;

                empID = txtSearch2.getText();

                //delete tasks in to the database
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

        //ComboBox in addTask tab
        AMPMComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String value1 =txtTaskTime1.getText()+ AMPMComboBox.getSelectedItem().toString();

                txtTaskTime1.setText(value1);
            }
        });

        //ComboBox in Update, Search, Remove tab
        AMPM2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String value2 =txtTaskTime2.getText()+ AMPM2ComboBox.getSelectedItem().toString();

                txtTaskTime2.setText(value2);
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
        Image newimg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);  // transform it back
        frame.setIconImage(imgIcon.getImage());
    }

    }





