package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeDashboard extends JFrame implements ActionListener {

    JPanel sidebar, contentPanel, header;

    JButton btnProfile, btnSubmitRequest, btnExpenseRequest, btnMyRequests, btnLogout;

    JLabel lblTitle;

    JLabel lblWelcome, lblRole;

    String username, role;

    public EmployeeDashboard(String username, String role) {

        this.username = username;
        this.role = role;

        setTitle("Employee Dashboard");
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
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        sidebar.add(lblLogo);

        lblRole = new JLabel("EMPLOYEE PANEL", SwingConstants.CENTER);
        lblRole.setBounds(50, 65, 150, 25);
        lblRole.setForeground(Color.LIGHT_GRAY);
        sidebar.add(lblRole);

        btnProfile = createMenuButton("My Profile", 130);
        btnSubmitRequest = createMenuButton("Leave Request", 190);
        btnExpenseRequest = createMenuButton("Expense Request", 250);
        btnMyRequests = createMenuButton("My Requests", 310);
        btnLogout = createMenuButton("Logout", 570);

        sidebar.add(btnProfile);
        sidebar.add(btnSubmitRequest);
        sidebar.add(btnExpenseRequest);
        sidebar.add(btnMyRequests);
        sidebar.add(btnLogout);

        header = new JPanel();
        header.setLayout(null);
        header.setBounds(250, 0, 950, 80);
        header.setBackground(Color.WHITE);

        lblTitle = new JLabel("Employee Dashboard");
        lblTitle.setBounds(25, 20, 400, 35);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));

        header.add(lblTitle);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(250, 80, 950, 620);
        contentPanel.setBackground(new Color(245, 247, 250));

        showHomeDashboard();

        add(sidebar);
        add(header);
        add(contentPanel);

        btnProfile.addActionListener(this);
        btnSubmitRequest.addActionListener(this);
        btnExpenseRequest.addActionListener(this);
        btnMyRequests.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    private JButton createMenuButton(String text, int y) {

        JButton btn = new JButton(text);
        btn.setBounds(20, y, 210, 45);
        btn.setBackground(new Color(30, 41, 59));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
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

    private void showHomeDashboard() {

        contentPanel.removeAll();

        JLabel lblWelcome = new JLabel("Welcome, " + UserSession.name);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblWelcome.setBounds(30, 20, 400, 40);
        contentPanel.add(lblWelcome);

        JLabel lblSub = new JLabel("Manage your requests and profile.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSub.setBounds(30, 60, 500, 25);
        contentPanel.add(lblSub);

        int leaveCount = 0;
        int expenseCount = 0;

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            int employeeId = Integer.parseInt(UserSession.employeeID);

            PreparedStatement leaveStmt = con.prepareStatement("SELECT COUNT(*) AS total FROM requests WHERE employee_id=? AND request_type='Leave'");
            leaveStmt.setInt(1, employeeId);
            ResultSet leaveRs = leaveStmt.executeQuery();
            if (leaveRs.next()) {
                leaveCount = leaveRs.getInt("total");
            }

            PreparedStatement expenseStmt = con.prepareStatement("SELECT COUNT(*) AS total FROM requests WHERE employee_id=? AND request_type='Expense'");
            expenseStmt.setInt(1, employeeId);
            ResultSet expenseRs = expenseStmt.executeQuery();
            if (expenseRs.next()) {
                expenseCount = expenseRs.getInt("total");
            }

            leaveRs.close();
            expenseRs.close();
            leaveStmt.close();
            expenseStmt.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        contentPanel.add(createCard("Leave Requests", String.valueOf(leaveCount), 30, 120));
        contentPanel.add(createCard("Expense Requests", String.valueOf(expenseCount), 320, 120));
        contentPanel.add(createCard("Total Requests", String.valueOf(leaveCount + expenseCount), 610, 120));

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnProfile) {

            contentPanel.removeAll();
            EmployeeProfilePanel profile = new EmployeeProfilePanel();
            profile.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
            contentPanel.add(profile);
            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource() == btnSubmitRequest) {

            contentPanel.removeAll();
            SubmitReqForm form = new SubmitReqForm();
            form.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
            contentPanel.add(form);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else if (e.getSource() == btnExpenseRequest) {

            contentPanel.removeAll();
            ExpenseReqForm form = new ExpenseReqForm();
            form.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
            contentPanel.add(form);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else if (e.getSource() == btnMyRequests) {

            contentPanel.removeAll();
            MyRequestView reqPanel = new MyRequestView();
            reqPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
            contentPanel.add(reqPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else if (e.getSource() == btnLogout) {

            new Login().setVisible(true);
            dispose();
        }
    }
}