package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainDashboard extends JFrame implements ActionListener {

    JPanel sidebar, contentPanel;

    JButton btnProfile,btnEmployees,btnRequests,btnReports,btnLogout;
  
    JLabel lblWelcome;
    
    String role;

    public ManagerMainDashboard(String role) {
        
        this.role = role;

        setTitle("Employee Dashboard");
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

        lblWelcome = new JLabel("Welcome Employee");
        lblWelcome.setBounds(20, 20, 300, 30);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));

        contentPanel.add(lblWelcome);

        add(sidebar);
        add(contentPanel);

        
        btnProfile.addActionListener(this);
        btnEmployees.addActionListener(this);
        btnRequests.addActionListener(this);
        btnReports.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == btnProfile) {

                        contentPanel.removeAll();

             EmployeeProfilePanel profile = new EmployeeProfilePanel();

             profile.setBounds(0,0,contentPanel.getWidth(),contentPanel.getHeight());

             contentPanel.add(profile);

             contentPanel.repaint();

             contentPanel.revalidate();
        }

        
        else if (e.getSource() == btnEmployees) {

                            contentPanel.removeAll();

                EmployeeCredentialsPanel panel = new EmployeeCredentialsPanel();

                panel.setBounds(0, 0,contentPanel.getWidth(),contentPanel.getHeight());

                contentPanel.add(panel);

                contentPanel.repaint();

                contentPanel.revalidate();
        }

       
        else if (e.getSource() == btnRequests) {

            contentPanel.removeAll();

            Manager_DB managerPanel = new Manager_DB();
            managerPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
            
            contentPanel.add(managerPanel);

            contentPanel.repaint();
            contentPanel.revalidate();
        }

        else if (e.getSource() == btnReports) {

            contentPanel.removeAll();

            ReportsPanel reportsPanel = new ReportsPanel();

            reportsPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

            contentPanel.add(reportsPanel);

            contentPanel.repaint();

            contentPanel.revalidate();
        }

       
        else if (e.getSource() == btnLogout) {

            Login l = new Login();
            l.setVisible(true);

            dispose();
        }
    }
}