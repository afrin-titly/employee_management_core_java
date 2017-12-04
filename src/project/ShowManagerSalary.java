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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author afrin
 */
public class ShowManagerSalary extends JFrame{
    static JTable table;
    JFrame frame;
    String comboItem;
    
    public ShowManagerSalary(){
        String columnName[] = {"id","absent","otherdeduction","overtime","otherbonus","finalsalary"};
    
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
        String id,absent,otherdeduction,overtime,otherbonus,finalsalary;
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/artechdb","root","root");
            PreparedStatement ps = con.prepareStatement("select * from allowance");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while(rs.next()){
                id=rs.getString("id");
                absent=rs.getString("absent");
                otherdeduction=rs.getString("otherdeduction");
                overtime=rs.getString("overtime");
                otherbonus=rs.getString("otherbonus");
                finalsalary=rs.getString("finalsalary");
                
                model.addRow(new Object[]{id,absent, otherdeduction, overtime, otherbonus,finalsalary});
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
