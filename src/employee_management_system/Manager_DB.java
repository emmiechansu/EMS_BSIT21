package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Manager_DB extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton approveBtn, rejectBtn, refreshBtn, viewBtn;

    private JLabel lblEmployeeValue, lblTypeValue, lblDateValue, lblAmountValue, lblReasonValue, lblStatusValue;

    private JPanel tableCard, detailsCard;

    public Manager_DB() {

        setLayout(null);
        setBackground(new Color(245, 247, 250));
        setBounds(0, 0, 950, 620);

        JLabel lblTitle = new JLabel("Employee Requests");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));

        lblTitle.setBounds(30, 20, 300, 40);

        add(lblTitle);

        JLabel lblSubTitle = new JLabel("Review and manage employee requests");

        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblSubTitle.setForeground(Color.GRAY);

        lblSubTitle.setBounds(30, 55, 350, 20);

        add(lblSubTitle);

        tableCard = new JPanel();

        tableCard.setLayout(null);

        tableCard.setBounds(20, 90, 560, 480);

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        add(tableCard);

        JLabel lblTableTitle = new JLabel("Request List");

        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        lblTableTitle.setBounds(20, 15, 200, 30);

        tableCard.add(lblTableTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Employee");
        tableModel.addColumn("Type");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Status");

        table = new JTable(tableModel);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        table.setRowHeight(30);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(20, 60, 520, 375);

        tableCard.add(scrollPane);

        viewBtn = new JButton("View Details");

        viewBtn.setBounds(390, 440, 150, 35);

        viewBtn.setBackground(new Color(37, 99, 235));

        viewBtn.setForeground(Color.WHITE);

        viewBtn.setFocusPainted(false);

        tableCard.add(viewBtn);

        detailsCard = new JPanel();

        detailsCard.setLayout(null);

        detailsCard.setBounds(600, 90, 320, 480);

        detailsCard.setBackground(Color.WHITE);

        detailsCard.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        add(detailsCard);

        JLabel lblDetailsTitle = new JLabel("Request Details");

        lblDetailsTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        lblDetailsTitle.setBounds(20, 15, 200, 30);

        detailsCard.add(lblDetailsTitle);

        JLabel lblEmployee = new JLabel("Employee:");

        lblEmployee.setBounds(20, 70, 100, 25);

        detailsCard.add(lblEmployee);

        lblEmployeeValue = new JLabel("-");

        lblEmployeeValue.setBounds(120, 70, 170, 25);

        detailsCard.add(lblEmployeeValue);

        JLabel lblType = new JLabel("Type:");

        lblType.setBounds(20, 110, 100, 25);

        detailsCard.add(lblType);

        lblTypeValue = new JLabel("-");

        lblTypeValue.setBounds(120, 110, 170, 25);

        detailsCard.add(lblTypeValue);

        JLabel lblDate = new JLabel("Date:");

        lblDate.setBounds(20, 150, 100, 25);

        detailsCard.add(lblDate);

        lblDateValue = new JLabel("-");

        lblDateValue.setBounds(120, 150, 170, 25);

        detailsCard.add(lblDateValue);

        JLabel lblAmount = new JLabel("Amount:");

        lblAmount.setBounds(20, 190, 100, 25);

        detailsCard.add(lblAmount);

        lblAmountValue = new JLabel("-");

        lblAmountValue.setBounds(120, 190, 170, 25);

        detailsCard.add(lblAmountValue);

        JLabel lblReason = new JLabel("Reason:");

        lblReason.setBounds(20, 230, 100, 25);

        detailsCard.add(lblReason);

        lblReasonValue = new JLabel("-");

        lblReasonValue.setBounds(120, 230, 170, 50);

        detailsCard.add(lblReasonValue);

        JLabel lblStatus = new JLabel("Status:");

        lblStatus.setBounds(20, 300, 100, 25);

        detailsCard.add(lblStatus);

        lblStatusValue = new JLabel("-");

        lblStatusValue.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblStatusValue.setBounds(120, 300, 170, 25);

        detailsCard.add(lblStatusValue);

        approveBtn = new JButton("Approve");

        approveBtn.setBounds(60, 335, 200, 40);

        approveBtn.setBackground(new Color(34, 197, 94));

        approveBtn.setForeground(Color.WHITE);

        approveBtn.setFocusPainted(false);

        detailsCard.add(approveBtn);

        rejectBtn = new JButton("Reject");

        rejectBtn.setBounds(60, 385, 200, 40);

        rejectBtn.setBackground(new Color(239, 68, 68));

        rejectBtn.setForeground(Color.WHITE);

        rejectBtn.setFocusPainted(false);

        detailsCard.add(rejectBtn);

        refreshBtn = new JButton("Refresh");

        refreshBtn.setBounds(60, 435, 200, 40);

        refreshBtn.setBackground(new Color(37, 99, 235));

        refreshBtn.setForeground(Color.WHITE);

        refreshBtn.setFocusPainted(false);

        detailsCard.add(refreshBtn);

        loadRequests();

        viewBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(null, "Please select a request first.");

                return;
            }

            lblEmployeeValue.setText(table.getValueAt(row, 1).toString());

            lblTypeValue.setText(table.getValueAt(row, 2).toString());

            lblDateValue.setText(table.getValueAt(row, 3).toString());

            lblAmountValue.setText(table.getValueAt(row, 4).toString());

            int requestId = Integer.parseInt(table.getValueAt(row, 0).toString());

            String reason = getReason(requestId);

            lblReasonValue.setText("<html><body style='width:150px'>" + reason + "</body></html>");

            String status = table.getValueAt(row, 5).toString();

            lblStatusValue.setText(status);

            if (status.equalsIgnoreCase("Approved")) {

                lblStatusValue.setForeground(new Color(34, 197, 94));

            } else if (status.equalsIgnoreCase("Rejected")) {

                lblStatusValue.setForeground(new Color(239, 68, 68));

            } else {

                lblStatusValue.setForeground(new Color(245, 158, 11));
            }
        });

        approveBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(null, "Please select a request.");

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Approve this request?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {

                return;
            }

            try {

                int requestId = Integer.parseInt(table.getValueAt(row, 0).toString());

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

                PreparedStatement st = con.prepareStatement("UPDATE requests " + "SET status=? " + "WHERE request_id=?");

                st.setString(1, "Approved");
                st.setInt(2, requestId);

                st.executeUpdate();

                st.close();
                con.close();

                loadRequests();

                lblStatusValue.setText("Approved");

                lblStatusValue.setForeground(new Color(34, 197, 94));

                JOptionPane.showMessageDialog(null, "Request Approved Successfully");

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(null, "Database Error");
            }
        });

        rejectBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {

                JOptionPane.showMessageDialog(null, "Please select a request.");

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Reject this request?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {

                return;
            }

            try {

                int requestId = Integer.parseInt(table.getValueAt(row, 0).toString());

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

                PreparedStatement st = con.prepareStatement("UPDATE requests " + "SET status=? " + "WHERE request_id=?");

                st.setString(1, "Rejected");
                st.setInt(2, requestId);

                st.executeUpdate();

                st.close();
                con.close();

                loadRequests();

                lblStatusValue.setText("Rejected");

                lblStatusValue.setForeground(new Color(239, 68, 68));

                JOptionPane.showMessageDialog(null, "Request Rejected");

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(null, "Database Error");
            }
        });

        refreshBtn.addActionListener(e -> {
            loadRequests();

            JOptionPane.showMessageDialog(null, "Request list refreshed.");
        });
    }

    private String getReason(int requestId) {

        String reason = "-";

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT reason " + "FROM requests " + "WHERE request_id=?");

            st.setInt(1, requestId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                reason = rs.getString("reason");
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reason;
    }

    public void loadRequests() {
        tableModel.setRowCount(0);
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT * FROM requests");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                tableModel.addRow(new Object[]{rs.getInt("request_id"), rs.getString("employee_name"), rs.getString("request_type"), rs.getString("request_date"), rs.getString("amount"), rs.getString("status")});
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Error Loading Requests!");
        }
    }
}