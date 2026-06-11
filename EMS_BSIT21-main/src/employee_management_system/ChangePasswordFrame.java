package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ChangePasswordFrame extends JFrame {

    private JPasswordField txtPassword;

    private JButton btnSave;

    private int employeeId;

    public ChangePasswordFrame(int employeeId) {

        this.employeeId = employeeId;

        setTitle("Change Password");

        setSize(500, 320);

        setLayout(null);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(40, 30, 400, 220);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220),1));

        add(cardPanel);

        JLabel lblTitle = new JLabel("Create New Password");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD,22));

        lblTitle.setBounds(90,20,250,30);

        cardPanel.add(lblTitle);

        JLabel lblInfo = new JLabel("Please change your default password");

        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN,12));

        lblInfo.setForeground(Color.GRAY);

        lblInfo.setBounds(85,50,250,20);

        cardPanel.add(lblInfo);

        JLabel lblPassword = new JLabel("New Password");

        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN,14));

        lblPassword.setBounds(40,95,120,25);

        cardPanel.add(lblPassword);

        txtPassword = new JPasswordField();

        txtPassword.setBounds(160,95,180,35);

        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN,14));

        cardPanel.add(txtPassword);

        btnSave = new JButton("Save Password");

        btnSave.setBounds(120,160,160,40);

        btnSave.setBackground(new Color(37,99,235));

        btnSave.setForeground(Color.WHITE);

        btnSave.setFocusPainted(false);

        btnSave.setFont(new Font("Segoe UI", Font.BOLD,14));

        cardPanel.add(btnSave);

        btnSave.addActionListener(e -> savePassword());
    }

    private void savePassword() {

        String password = String.valueOf(txtPassword.getPassword());

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter a password!");

            return;
        }

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("UPDATE employee_credentials " + "SET password=?, first_login=0 " + "WHERE employee_id=?");

            st.setString(1, password);

            st.setInt(2, employeeId);

            st.executeUpdate();

            JOptionPane.showMessageDialog(this, "Password Updated Successfully!");

            st.close();

            con.close();

            dispose();

            new Login().setVisible(true);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Updating Password!");
        }
    }
}