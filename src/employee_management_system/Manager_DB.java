package employee_management_system;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Manager_DB extends JPanel {

    JTable table;
    DefaultTableModel tableModel;

    JButton approveBtn, rejectBtn, refreshBtn;

    JLabel lblEmployeeValue;
    JLabel lblTypeValue;
    JLabel lblDateValue;
    JLabel lblAmountValue;
    JLabel lblReasonValue;
    JLabel lblStatusValue;

    public Manager_DB() {

        setLayout(null);
        setBounds(0, 0, 700, 550);

        JLabel lblTitle = new JLabel("Employee Requests");
        lblTitle.setBounds(280, 20, 200, 30);
        add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Employee");
        tableModel.addColumn("Type");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Reason");
        tableModel.addColumn("Status");

        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 70, 650, 250);
        add(scroll);

        JLabel lblDetails = new JLabel("Request Details");
        lblDetails.setBounds(20, 330, 150, 25);
        add(lblDetails);

        JLabel lblEmployee = new JLabel("Employee:");
        lblEmployee.setBounds(20, 360, 80, 25);
        add(lblEmployee);

        lblEmployeeValue = new JLabel("-");
        lblEmployeeValue.setBounds(100, 360, 200, 25);
        add(lblEmployeeValue);

        JLabel lblType = new JLabel("Type:");
        lblType.setBounds(20, 390, 80, 25);
        add(lblType);

        lblTypeValue = new JLabel("-");
        lblTypeValue.setBounds(100, 390, 200, 25);
        add(lblTypeValue);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(320, 360, 80, 25);
        add(lblDate);

        lblDateValue = new JLabel("-");
        lblDateValue.setBounds(400, 360, 200, 25);
        add(lblDateValue);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(320, 390, 80, 25);
        add(lblAmount);

        lblAmountValue = new JLabel("-");
        lblAmountValue.setBounds(400, 390, 200, 25);
        add(lblAmountValue);

        JLabel lblReason = new JLabel("Reason:");
        lblReason.setBounds(20, 420, 80, 25);
        add(lblReason);

        lblReasonValue = new JLabel("-");
        lblReasonValue.setBounds(100, 420, 300, 25);
        add(lblReasonValue);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(320, 420, 80, 25);
        add(lblStatus);

        lblStatusValue = new JLabel("-");
        lblStatusValue.setBounds(400, 420, 200, 25);
        add(lblStatusValue);

        approveBtn = new JButton("Approve");
        approveBtn.setBounds(520, 330, 120, 35);
        add(approveBtn);

        rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(520, 375, 120, 35);
        add(rejectBtn);

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(520, 420, 120, 35);
        add(refreshBtn);

        loadRequests();

        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    int row = table.getSelectedRow();

                    if (row != -1) {

                        Request req = RequestData.requests.get(row);

                        lblEmployeeValue.setText(req.employeeName);
                        lblTypeValue.setText(req.type);
                        lblDateValue.setText(req.date);
                        lblAmountValue.setText(
                                String.valueOf(req.amount));
                        lblReasonValue.setText(req.reason);
                        lblStatusValue.setText(req.status);
                    }
                }
            }
        });

        approveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if (row == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select Request First");

                    return;
                }

                RequestData.requests.get(row).status = "Approved";

                loadRequests();

                lblStatusValue.setText("Approved");

                JOptionPane.showMessageDialog(
                        null,
                        "Request Approved");
            }
        });

        rejectBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if (row == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select Request First");

                    return;
                }

                RequestData.requests.get(row).status = "Rejected";

                loadRequests();

                lblStatusValue.setText("Rejected");

                JOptionPane.showMessageDialog(
                        null,
                        "Request Rejected");
            }
        });

        refreshBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                loadRequests();
            }
        });
    }

    public void loadRequests() {

        tableModel.setRowCount(0);

        for (Request req : RequestData.requests) {

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