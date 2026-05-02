package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard extends JFrame implements ActionListener {

    JPanel sidebar, contentPanel;
    JButton btnDashboard, btnEmployees, btnRequests, btnReports, btnLogout;
    JLabel lblWelcome;

    String role;

    
    public MainDashboard(String role) {

        this.role = role;

        setTitle("EMS Dashboard - " + role);
        setSize(900, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 200, 500);
        sidebar.setBackground(new Color(50, 50, 50));

        btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(20, 50, 160, 30);
        sidebar.add(btnDashboard);

        btnEmployees = new JButton("Employees");
        btnEmployees.setBounds(20, 100, 160, 30);
        sidebar.add(btnEmployees);
        
        btnRequests = new JButton("Requests");
        btnRequests.setBounds(20, 150, 160, 30);
        sidebar.add(btnRequests);
        
        btnReports = new JButton("Reports");
        btnReports.setBounds(20, 200, 160, 30);
        sidebar.add(btnReports);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 250, 160, 30);
        sidebar.add(btnLogout);

        
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 700, 500);
        contentPanel.setBackground(Color.WHITE);

        lblWelcome = new JLabel("Welcome, " + role);
        lblWelcome.setBounds(20, 20, 400, 30);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(lblWelcome);

        add(sidebar);
        add(contentPanel);

        
        applyRolePermissions();
        btnLogout.addActionListener(this);
        btnEmployees.addActionListener(this);
        btnRequests.addActionListener(this);
    }

    
    private void applyRolePermissions() {

        if (role.equalsIgnoreCase("HR")) {
            btnEmployees.setEnabled(true);
            btnRequests.setEnabled(true);
            btnReports.setEnabled(true);

        } else if (role.equalsIgnoreCase("Manager")) {
            btnEmployees.setEnabled(false);
            btnRequests.setEnabled(true);
            btnReports.setEnabled(true);

        } else if (role.equalsIgnoreCase("Employee")) {
            btnEmployees.setEnabled(false);
            btnReports.setEnabled(false);
            btnRequests.setEnabled(true);
        }
    }
        @Override
     public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnLogout) {
        Login l = new Login();
        l.setVisible(true);
        dispose();

    } else if (e.getSource() == btnEmployees) {

        contentPanel.removeAll();

        HRDashboard hr = new HRDashboard();
        hr.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

        contentPanel.add(hr);

        contentPanel.repaint();
        contentPanel.revalidate();
    } else if (e.getSource() == btnRequests) {

    contentPanel.removeAll();

    Manager_DB managerPanel = new Manager_DB();
    managerPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

    contentPanel.add(managerPanel);

    contentPanel.repaint();
    contentPanel.revalidate();
}
    }
}