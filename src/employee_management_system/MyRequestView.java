package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyRequestView extends JPanel {

    JTable table;

    DefaultTableModel model;

    public MyRequestView() {

        setLayout(null);

        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Requests");

        title.setBounds(250,10,200,30);

        title.setFont(new Font("Arial",Font.BOLD,16));

        add(title);

        String[] columns = {

                "Employee",
                "Type",
                "Date",
                "Amount",
                "Reason",
                "Status"
        };

        model =new DefaultTableModel(columns,0);

        table =new JTable(model);

        JScrollPane scrollPane =new JScrollPane(table);

        scrollPane.setBounds(20,50,640,300);

        add(scrollPane);

        loadRequests();
    }

    public void loadRequests() {

        model.setRowCount(0);

        for(Request r : RequestData.requests) {

            model.addRow(new Object[] {

                    r.employeeName,
                    r.type,
                    r.date,
                    r.amount,
                    r.reason,
                    r.status
            });
        }
    }
}