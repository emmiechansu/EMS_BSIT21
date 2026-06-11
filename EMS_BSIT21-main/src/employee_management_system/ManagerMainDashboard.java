package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerMainDashboard extends JFrame implements ActionListener {

    JPanel sidebar, header, contentPanel;

    JButton btnProfile, btnRequests, btnExpenses, btnReports, btnLogout;

    JLabel lblTitle, lblRole;

    String role;

    public ManagerMainDashboard(String role) {

        this.role = role;

        setTitle("Employee Management System - Manager Dashboard");
        setSize(1200, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(240, 242, 245));

        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 250, 700);
        sidebar.setBackground(new Color(15, 23, 42));

        JLabel lblLogo = new JLabel("EMS", SwingConstants.CENTER);
        lblLogo.setBounds(50, 20, 150, 50);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblLogo.setForeground(Color.WHITE);
        sidebar.add(lblLogo);

        lblRole = new JLabel("MANAGER PANEL", SwingConstants.CENTER);
        lblRole.setBounds(50, 65, 150, 25);
        lblRole.setForeground(Color.LIGHT_GRAY);
        sidebar.add(lblRole);

        btnProfile = createButton("Manager Profile", 130);
        btnRequests = createButton("Employee Requests", 190);
        btnExpenses = createButton("Expense Requests", 250);
        btnReports = createButton("Employee Records", 310);
        btnLogout = createButton("Logout", 570);

        sidebar.add(btnProfile);
        sidebar.add(btnRequests);
        sidebar.add(btnExpenses);
        sidebar.add(btnReports);
        sidebar.add(btnLogout);

        header = new JPanel();
        header.setLayout(null);
        header.setBounds(250, 0, 950, 80);
        header.setBackground(Color.WHITE);

        lblTitle = new JLabel("Manager Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setBounds(25, 20, 400, 35);
        header.add(lblTitle);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(250, 80, 950, 620);
        contentPanel.setBackground(new Color(245, 247, 250));

        add(sidebar);
        add(header);
        add(contentPanel);

        btnProfile.addActionListener(this);
        btnRequests.addActionListener(this);
        btnExpenses.addActionListener(this);
        btnReports.addActionListener(this);
        btnLogout.addActionListener(this);

        showHomeDashboard();

        setVisible(true);
    }

    private JButton createButton(String text, int y) {

        JButton btn = new JButton(text);
        btn.setBounds(20, y, 210, 45);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBackground(new Color(30, 41, 59));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private JPanel createCard(String title, String value, int x, int y) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 250, 120);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 20, 200, 25);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblValue = new JLabel(value);
        lblValue.setBounds(20, 55, 200, 40);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));

        card.add(lblTitle);
        card.add(lblValue);

        return card;
    }

    private String getEmployeeCount() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM employees");

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getString(1);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return "0";
    }

    private String getExpenseRequestCount() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM requests WHERE request_type='Expense'");

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getString(1);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return "0";
    }

    private String getPendingRequestCount() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM requests WHERE status='Pending'");

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getString(1);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return "0";
    }

    private void showHomeDashboard() {

        contentPanel.removeAll();

        JLabel lblWelcome = new JLabel("Welcome, " + role);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblWelcome.setBounds(30, 20, 500, 40);
        contentPanel.add(lblWelcome);

        JLabel lblSub = new JLabel("Manage Employee Requests and Records.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSub.setBounds(30, 60, 500, 25);
        contentPanel.add(lblSub);

        contentPanel.add(createCard("Pending Requests", getPendingRequestCount(), 30, 120));

        contentPanel.add(createCard("Expense Requests", getExpenseRequestCount(), 320, 120));

        contentPanel.add(createCard("Employees", getEmployeeCount(), 610, 120));

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnProfile) {

            contentPanel.removeAll();

            ManagerProfilePanel panel = new ManagerProfilePanel();
            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);
        } else if (e.getSource() == btnRequests) {

            contentPanel.removeAll();

            Manager_DB panel = new Manager_DB();
            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);
        } else if (e.getSource() == btnExpenses) {

            contentPanel.removeAll();

            ReimbursementPanel panel = new ReimbursementPanel();
            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);
        } else if (e.getSource() == btnReports) {

            contentPanel.removeAll();

            ReportsPanel panel = new ReportsPanel();
            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);
        } else if (e.getSource() == btnLogout) {

            new Login().setVisible(true);
            dispose();
            return;
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}