package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Manager_DB extends JPanel {

    JTable table;

    DefaultTableModel tableModel;

    JButton approveBtn,rejectBtn,refreshBtn;

    public Manager_DB() {

        setLayout(null);

        setBounds(0, 0, 700, 500);

        JLabel lblTitle = new JLabel("Employee Requests");

        lblTitle.setBounds(250, 20, 200, 30);

        add(lblTitle);

        
        tableModel =new DefaultTableModel();

        tableModel.addColumn("Employee");
        tableModel.addColumn("Type");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Reason");
        tableModel.addColumn("Status");

        table =new JTable(tableModel);

        JScrollPane scroll =new JScrollPane(table);

        scroll.setBounds(20, 70, 650, 250);

        add(scroll);

        approveBtn = new JButton("Approve");

        approveBtn.setBounds(150, 350, 120, 35);

        add(approveBtn);

        rejectBtn =new JButton("Reject");

        rejectBtn.setBounds(290, 350, 120, 35);

        add(rejectBtn);

        refreshBtn =new JButton("Refresh");

        refreshBtn.setBounds(430, 350,120, 35);

        add(refreshBtn);

        loadRequests();

        approveBtn.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if(row == -1) {

                    JOptionPane.showMessageDialog( null, "Select Request First");

                    return;
                }

                RequestData.requests.get(row).status= "Approved";

                loadRequests();

                JOptionPane.showMessageDialog(null,"Request Approved");
            }
        });

        rejectBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if(row == -1) {

                    JOptionPane.showMessageDialog( null,"Select Request First");

                    return;
                }

                RequestData.requests.get(row).status= "Rejected";

                loadRequests();

                JOptionPane.showMessageDialog( null, "Request Rejected" );
            }
        });

        refreshBtn.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                loadRequests();
            }
        });
    }

    public void loadRequests() {

        tableModel.setRowCount(0);

        for(Request req :
                RequestData.requests) {

            tableModel.addRow(new Object[] {

                    req.employeeName,
                    req.type,
                    req.date,
                    req.amount,
                    req.reason,
                    req.status
            });
        }
    }
}