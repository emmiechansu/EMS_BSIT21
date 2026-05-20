package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HRMainDashboard extends JFrame
        implements ActionListener {

    JPanel sidebar, contentPanel;

    JButton btnProfile, btnAddEmployees,btnEmployees,btnReports,btnLogout;

    JLabel lblWelcome;

    String role;

    public HRMainDashboard(String role) {

        this.role = role;

        setTitle("EMS Profile - " + role);

        setSize(900, 500);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBounds(0, 0, 200, 500);

        sidebar.setBackground(new Color(50, 50, 50));

        btnProfile = new JButton("Profile");
        btnProfile.setBounds(20, 50, 160, 30);
        sidebar.add(btnProfile);

        btnAddEmployees = new JButton("Add Employees");

        btnAddEmployees.setBounds(20, 100, 160, 30);

        sidebar.add(btnAddEmployees);

        btnEmployees = new JButton("Employees");

        btnEmployees.setBounds(20, 150, 160, 30);

        sidebar.add(btnEmployees);

        btnReports = new JButton("Reports");

        btnReports.setBounds(20, 200, 160, 30);

        sidebar.add(btnReports);

        btnLogout = new JButton("Logout");

        btnLogout.setBounds(20, 250, 160, 30);

        sidebar.add(btnLogout);

        add(sidebar);

        contentPanel = new JPanel();

        contentPanel.setLayout(null);

        contentPanel.setBounds(200, 0, 700, 500);

        contentPanel.setBackground(Color.WHITE);

        lblWelcome = new JLabel("Welcome, " + role);

        lblWelcome.setBounds(20, 20,300, 30);

        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));

        contentPanel.add(lblWelcome);

        add(contentPanel);

        btnProfile.addActionListener(this);

        btnAddEmployees.addActionListener(this);

        btnEmployees.addActionListener(this);

        btnReports.addActionListener(this);

        btnLogout.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogout) {

            Login l = new Login();

            l.setVisible(true);

            dispose();
        }
        
        else if (e.getSource() == btnProfile) {

                   contentPanel.removeAll();

        EmployeeProfilePanel profile = new EmployeeProfilePanel();

        profile.setBounds(0,0,contentPanel.getWidth(),contentPanel.getHeight());

        contentPanel.add(profile);

        contentPanel.repaint();

        contentPanel.revalidate();
                }

       

        else if (e.getSource()== btnAddEmployees) {

            contentPanel.removeAll();

            AddEmployeePanel addPanel = new AddEmployeePanel();

            addPanel.setBounds(0, 0,contentPanel.getWidth(),contentPanel.getHeight());

            contentPanel.add(addPanel);

            contentPanel.repaint();

            contentPanel.revalidate();
        }

        else if (e.getSource()
                == btnEmployees) {

            contentPanel.removeAll();

            EmployeesPanel employeesPanel =
                    new EmployeesPanel();

            employeesPanel.setBounds(0, 0,
                    contentPanel.getWidth(),
                    contentPanel.getHeight());

            contentPanel.add(employeesPanel);

            contentPanel.repaint();

            contentPanel.revalidate();
        }

        else if (e.getSource()== btnReports) {

            contentPanel.removeAll();

            ReportsPanel reportsPanel = new ReportsPanel();

            reportsPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(reportsPanel);

            contentPanel.repaint();

            contentPanel.revalidate();
        }
        
        
    }
}