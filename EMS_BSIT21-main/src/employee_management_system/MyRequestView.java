package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyRequestView extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public MyRequestView() {

        setLayout(null);
        setBackground(new Color(245, 247, 250));
        setBounds(0, 0, 950, 620);

        JLabel lblTitle = new JLabel("My Requests");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(new Color(15, 23, 42));
        lblTitle.setBounds(30, 20, 300, 40);
        add(lblTitle);

        JLabel lblSubtitle = new JLabel("View all submitted leave and expense requests");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setBounds(30, 55, 400, 20);
        add(lblSubtitle);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(30, 100, 890, 480);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        JLabel lblCardTitle = new JLabel("Request History");
        lblCardTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCardTitle.setForeground(new Color(15, 23, 42));
        lblCardTitle.setBounds(20, 15, 250, 30);
        cardPanel.add(lblCardTitle);

        String[] columns = {"Request Type", "Date", "Amount", "Reason", "Status"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(59, 130, 246));
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(15, 23, 42));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 850, 390);
        cardPanel.add(scrollPane);

        loadRequests();
    }

    private void loadRequests() {

        model.setRowCount(0);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT * FROM requests WHERE employee_id=?");

            st.setInt(1, Integer.parseInt(UserSession.employeeID));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("request_type"), rs.getString("request_date"), rs.getString("amount"), rs.getString("reason"), rs.getString("status")});
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Loading Requests!\n" + e.getMessage());
        }
    }
}