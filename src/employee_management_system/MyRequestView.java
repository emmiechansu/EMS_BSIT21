package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MyRequestView extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    

    public MyRequestView() {

        setLayout(null);
        setBackground(Color.WHITE);

       
        JLabel title = new JLabel("My Requests");
        title.setBounds(250, 10, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        
        String[] columns = {"Type", "Date", "Amount", "Reason", "Status"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 540, 250);
        add(scrollPane);

        loadRequests();
    }

   
    private void loadRequests() {

        model.setRowCount(0);

        for (Request r : RequestData.requests) {
            model.addRow(new Object[]{
                    r.type,
                    r.date,
                    r.amount,
                    r.reason,
                    r.status
            });
        }
    }
}