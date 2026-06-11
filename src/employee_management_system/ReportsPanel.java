package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportsPanel extends JPanel {

    JTable reportTable;

    DefaultTableModel tableModel;

    JLabel lblTotal;

    JPanel cardPanel;

    public ReportsPanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(20, 20, 900, 550);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        JLabel lblTitle = new JLabel("Employee Records");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setBounds(330, 20, 300, 35);

        cardPanel.add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Employee");
        tableModel.addColumn("Position");
        tableModel.addColumn("Department");
        tableModel.addColumn("Contact No");
        tableModel.addColumn("Performance");

        reportTable = new JTable(tableModel);

        reportTable.setRowHeight(30);

        reportTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader header = reportTable.getTableHeader();

        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(reportTable);

        scrollPane.setBounds(25, 80, 850, 350);

        cardPanel.add(scrollPane);

        JPanel totalPanel = new JPanel();

        totalPanel.setLayout(null);

        totalPanel.setBounds(25, 455, 250, 60);

        totalPanel.setBackground(new Color(37, 99, 235));

        cardPanel.add(totalPanel);

        JLabel lblText = new JLabel("Total Employees");

        lblText.setForeground(Color.WHITE);

        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblText.setBounds(15, 8, 200, 20);

        totalPanel.add(lblText);

        lblTotal = new JLabel("0");

        lblTotal.setForeground(Color.WHITE);

        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 22));

        lblTotal.setBounds(15, 25, 200, 30);

        totalPanel.add(lblTotal);

        loadReports();
    }

    public void loadReports() {

        tableModel.setRowCount(0);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT * FROM employees");

            ResultSet rs = st.executeQuery();

            int total = 0;

            while (rs.next()) {

                tableModel.addRow(new Object[]{rs.getInt("employee_id"), rs.getString("full_name"), rs.getString("position"), rs.getString("department"), rs.getString("contact_no"), rs.getString("performance")});

                total++;
            }

            lblTotal.setText(String.valueOf(total));

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error loading employee records!");
        }
    }
}