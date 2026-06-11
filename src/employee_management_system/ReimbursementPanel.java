package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReimbursementPanel extends JPanel {

    JTable reimbursementTable;

    DefaultTableModel tableModel;

    JLabel lblTotal;

    JPanel cardPanel;

    public ReimbursementPanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(30, 20, 880, 560);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        add(cardPanel);

        JLabel lblTitle = new JLabel("Expense Requests");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setBounds(330, 20, 250, 35);

        cardPanel.add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Employee Name");
        tableModel.addColumn("Department");
        tableModel.addColumn("Expense Type");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Status");

        reimbursementTable = new JTable(tableModel);

        reimbursementTable.setRowHeight(30);

        reimbursementTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader header = reimbursementTable.getTableHeader();

        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(reimbursementTable);

        scrollPane.setBounds(20, 80, 840, 380);

        cardPanel.add(scrollPane);

        lblTotal = new JLabel("Total Expense Requests: 0");

        lblTotal.setBounds(20, 490, 300, 25);

        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));

        cardPanel.add(lblTotal);

        loadReimbursements();
    }

    public void loadReimbursements() {

        tableModel.setRowCount(0);

        int totalRequests = 0;

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT e.employee_id, e.full_name, e.department, r.request_type, r.amount, r.status FROM employees e LEFT JOIN requests r ON e.full_name = r.employee_name WHERE r.request_type = 'Expense'");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                totalRequests++;

                tableModel.addRow(new Object[]{rs.getInt("employee_id"), rs.getString("full_name"), rs.getString("department"), rs.getString("request_type"), rs.getString("amount"), rs.getString("status")});
            }

            lblTotal.setText("Total Expense Requests: " + totalRequests);

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Loading Expense Requests!");
        }
    }
}