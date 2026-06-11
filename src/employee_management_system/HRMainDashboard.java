package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HRMainDashboard extends JFrame implements ActionListener {

    JPanel sidebar, headerPanel, contentPanel;

    JButton btnProfile, btnAddEmployees, btnEmployees, btnReports, btnLogout;

    JLabel lblTitle;

    String role;

    public HRMainDashboard(String role) {

        this.role = role;

        setTitle("Employee Management System - HR Dashboard");

        setSize(1200, 700);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        getContentPane().setBackground(new Color(245, 247, 250));

        sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBounds(0, 0, 250, 700);

        sidebar.setBackground(new Color(15, 23, 42));

        add(sidebar);

        JLabel lblLogo = new JLabel("EMS", SwingConstants.CENTER);

        lblLogo.setBounds(50, 20, 150, 50);

        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 30));

        lblLogo.setForeground(Color.WHITE);

        sidebar.add(lblLogo);

        JLabel lblRole = new JLabel("HR PANEL", SwingConstants.CENTER);

        lblRole.setBounds(50, 65, 150, 25);

        lblRole.setForeground(Color.LIGHT_GRAY);

        sidebar.add(lblRole);

        btnProfile = createButton("HR Profile", 130);

        btnAddEmployees = createButton("Add Employee", 190);

        btnEmployees = createButton("Manage Employees", 250);

        btnReports = createButton("Employee Records", 310);

        btnLogout = createButton("Logout", 570);

        sidebar.add(btnProfile);
        sidebar.add(btnAddEmployees);
        sidebar.add(btnEmployees);
        sidebar.add(btnReports);
        sidebar.add(btnLogout);

        headerPanel = new JPanel();

        headerPanel.setLayout(null);

        headerPanel.setBounds(250, 0, 950, 80);

        headerPanel.setBackground(Color.WHITE);

        add(headerPanel);

        lblTitle = new JLabel("HR Dashboard");

        lblTitle.setBounds(25, 20, 500, 35);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));

        headerPanel.add(lblTitle);

        contentPanel = new JPanel();

        contentPanel.setLayout(null);

        contentPanel.setBounds(250, 80, 950, 620);

        contentPanel.setBackground(new Color(245, 247, 250));

        add(contentPanel);

        showHomeDashboard();

        btnProfile.addActionListener(this);
        btnAddEmployees.addActionListener(this);
        btnEmployees.addActionListener(this);
        btnReports.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    private JButton createButton(String text, int y) {

        JButton btn = new JButton(text);

        btn.setBounds(20, y, 210, 45);

        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        btn.setBackground(new Color(30, 41, 59));

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private JPanel createCard(String title, String value, int x, int y) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBounds(x, y, 250, 120);

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JLabel lblCardTitle = new JLabel(title);

        lblCardTitle.setBounds(20, 20, 200, 25);

        lblCardTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        card.add(lblCardTitle);

        JLabel lblValue = new JLabel(value);

        lblValue.setBounds(20, 55, 200, 40);

        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));

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

    private String getDepartmentCount() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT COUNT(DISTINCT department) FROM employees");

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

        lblWelcome.setBounds(30, 20, 400, 40);

        contentPanel.add(lblWelcome);

        JLabel lblSub = new JLabel("Manage employee information and records.");

        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        lblSub.setBounds(30, 60, 500, 25);

        contentPanel.add(lblSub);

        contentPanel.add(createCard("Employees", getEmployeeCount(), 30, 120));

        contentPanel.add(createCard("Departments", getDepartmentCount(), 320, 120));

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogout) {

            new Login().setVisible(true);

            dispose();
        } else if (e.getSource() == btnProfile) {

            contentPanel.removeAll();

            HRProfilePanel panel = new HRProfilePanel();

            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);

            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource() == btnAddEmployees) {

            contentPanel.removeAll();

            AddEmployeePanel panel = new AddEmployeePanel();

            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);

            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource() == btnEmployees) {

            contentPanel.removeAll();

            EmployeesPanel panel = new EmployeesPanel();

            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);

            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource() == btnReports) {

            contentPanel.removeAll();

            ReportsPanel panel = new ReportsPanel();

            panel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(panel);

            contentPanel.repaint();
            contentPanel.revalidate();
        }
    }
}