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

public class DBCredentials {
    private static Connection con;
    private static PreparedStatement pst;

    public static Connection getConnection(){
        return con;
    }
    public static PreparedStatement getPreparedStatement(){
        return pst;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/aems", "timax","Masseffect34c1#@");
            System.out.println("Database connection successful in secondary class!");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
