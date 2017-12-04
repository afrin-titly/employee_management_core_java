/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author afrin
 */
public class ShowManagerData extends JFrame{
    static JTable table;
    JFrame frame;
    String comboItem;
    
    public ShowManagerData(){
        String columnName[] = {"id","username","password","email","phone","post","joindate","city"};
    
        frame = new JFrame("Database frame result");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 500);
        frame.dispose();
    
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        comboItem = (String) ManagerView1.combo1.getSelectedItem();
        String id,username,password,email,phone,post,joindate,city;
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/artechdb","root","root");
            PreparedStatement ps = con.prepareStatement("select * from emptable where post='" + comboItem + "'");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while(rs.next()){
                id=rs.getString("id");
                username=rs.getString("username");
                password=rs.getString("password");
                email=rs.getString("email");
                phone=rs.getString("phone");
                post=rs.getString("post");
                joindate=rs.getString("joindate");
                city=rs.getString("city");
                model.addRow(new Object[]{id,username, password, email, phone,post,joindate,city});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                frame.add(scroll);
                frame.setVisible(true);
                frame.setSize(400, 300);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
                frame.add(scroll);
                frame.setVisible(true);
                frame.setSize(400, 300);
            } else {
            System.out.println(i + " Records Found");
            frame.add(scroll);
            frame.setVisible(true);
            frame.setSize(400, 300);
            }
            }
        catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }
}
