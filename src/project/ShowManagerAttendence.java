/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ShowManagerAttendence extends JFrame{
    static JTable table;
    JFrame frame;
    String comboItem;
    
    public ShowManagerAttendence(){
        String columnName[] ={"id","username","date"};
        frame=new JFrame("Attendence Data");
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 500);
        frame.dispose();
        
        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        table=new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        
        JScrollPane scroll=new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        comboItem=(String) ManagerView1.combo3.getSelectedItem();
        String id,username,date;
        
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/artechdb","root","root");
            PreparedStatement ps = con.prepareStatement("select * from attendence where date='" + comboItem + "'");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while(rs.next()){
                id=rs.getString("id");
                username=rs.getString("username");
                date=rs.getString("date");
                
                model.addRow(new Object[]{id,username, date});
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
