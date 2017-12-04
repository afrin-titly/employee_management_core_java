/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author afrin
 */
public class ShowEmpAttendence extends JFrame {

    JFrame frame1;
    static JTable table;
    PreparedStatement pst;
    Connection con;
    String[] columnNames = {"username", "date"};

    public ShowEmpAttendence() {
        EmployeeLogin e = new EmployeeLogin();
        frame1 = new JFrame("Database Search Result");
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        String username = "";
        String date = "";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/artechdb", "root", "root");
            pst = con.prepareStatement("select * from attendence where id='" + e.key + "'");
            ResultSet rs = pst.executeQuery();
            //pst.executeUpdate();
           
            int i = 0;
            while (rs.next()) {
                username = rs.getString(2);
                date = rs.getString(3);
                model.addRow(new Object[]{username, date});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                frame1.add(scroll);
                frame1.setVisible(false);
                frame1.setSize(400, 300);
            } else if (i == 1) {
                System.out.println(i + " Record Found");
                model.toString();
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400, 300);
            } else {
                System.out.println(i + " Records Found");
                model.toString();
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400, 300);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
//        frame1.add(scroll);
//        frame1.setVisible(true);
//        frame1.setSize(400, 300);
        this.dispose();
        
    }

    public static void main(String[] args) {
        ShowEmpAttendence s = new ShowEmpAttendence();
    }
}
