package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeDashboard extends JFrame implements ActionListener {

    JPanel sidebar, contentPanel;

    JButton btnProfile, btnSubmitRequest, btnMyRequests, btnLogout;

    JLabel lblTitle, lblWelcome;
     
    
    String role;

    public EmployeeDashboard(String role) {

        setTitle("Employee Dashboard");
        setSize(900, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 220, 500);
        sidebar.setBackground(new Color(40, 40, 40));

        JLabel logo = new JLabel("EMP");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 22));
        logo.setBounds(80, 20, 100, 30);
        sidebar.add(logo);

        btnProfile = new JButton("My Profile");
        btnProfile.setBounds(20, 100, 180, 35);
        sidebar.add(btnProfile);

        btnSubmitRequest = new JButton("Submit Request");
        btnSubmitRequest.setBounds(20, 150, 180, 35);
        sidebar.add(btnSubmitRequest);

        btnMyRequests = new JButton("My Requests");
        btnMyRequests.setBounds(20, 200, 180, 35);
        sidebar.add(btnMyRequests);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 270, 180, 35);
        sidebar.add(btnLogout);

       
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(220, 0, 680, 500);
        contentPanel.setBackground(new Color(245, 245, 245));

        lblTitle = new JLabel("Welcome Employee");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(30, 20, 300, 40);
        contentPanel.add(lblTitle);

        add(sidebar);
        add(contentPanel);

        
        btnProfile.addActionListener(this);
        btnSubmitRequest.addActionListener(this);
        btnMyRequests.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnProfile) {
    
    }else if (e.getSource() == btnSubmitRequest) {

    contentPanel.removeAll();

    SubmitReqForm form = new SubmitReqForm();
    form.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

    contentPanel.add(form);

    contentPanel.revalidate();
    contentPanel.repaint();
}else if (e.getSource() == btnMyRequests) {

    contentPanel.removeAll();

    MyRequestView reqPanel = new MyRequestView();
    reqPanel.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());

    contentPanel.add(reqPanel);

    contentPanel.revalidate();
    contentPanel.repaint();
}

        else if (e.getSource() == btnLogout) {

            new Login().setVisible(true);
            dispose();
        }
    }
}