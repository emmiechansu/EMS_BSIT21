package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HRProfilePanel extends JPanel {

    JTextField txtName, txtDepartment, txtPosition, txtEmail, txtContact, txtAddress, txtStatus;

    JButton btnUpdate;

    JPanel cardPanel;

    public HRProfilePanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(170, 40, 600, 500);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        JLabel lblTitle = new JLabel("HR Profile");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setBounds(240, 20, 200, 35);

        cardPanel.add(lblTitle);

        JLabel lblName = new JLabel("Full Name");

        lblName.setBounds(60, 90, 120, 25);

        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblName);

        txtName = new JTextField();

        txtName.setBounds(200, 90, 320, 35);

        cardPanel.add(txtName);

        JLabel lblDepartment = new JLabel("Department");

        lblDepartment.setBounds(60, 140, 120, 25);

        lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblDepartment);

        txtDepartment = new JTextField();

        txtDepartment.setBounds(200, 140, 320, 35);

        cardPanel.add(txtDepartment);

        JLabel lblPosition = new JLabel("Position");

        lblPosition.setBounds(60, 190, 120, 25);

        lblPosition.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblPosition);

        txtPosition = new JTextField();

        txtPosition.setBounds(200, 190, 320, 35);

        cardPanel.add(txtPosition);

        JLabel lblEmail = new JLabel("Email");

        lblEmail.setBounds(60, 240, 120, 25);

        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblEmail);

        txtEmail = new JTextField();

        txtEmail.setBounds(200, 240, 320, 35);

        cardPanel.add(txtEmail);

        JLabel lblContact = new JLabel("Contact No.");

        lblContact.setBounds(60, 290, 120, 25);

        lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblContact);

        txtContact = new JTextField();

        txtContact.setBounds(200, 290, 320, 35);

        cardPanel.add(txtContact);

        JLabel lblAddress = new JLabel("Address");

        lblAddress.setBounds(60, 340, 120, 25);

        lblAddress.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblAddress);

        txtAddress = new JTextField();

        txtAddress.setBounds(200, 340, 320, 35);

        cardPanel.add(txtAddress);

        JLabel lblStatus = new JLabel("Status");

        lblStatus.setBounds(60, 390, 120, 25);

        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cardPanel.add(lblStatus);

        txtStatus = new JTextField();

        txtStatus.setBounds(200, 390, 320, 35);

        cardPanel.add(txtStatus);

        btnUpdate = new JButton("Update Profile");

        btnUpdate.setBounds(220, 445, 160, 40);

        btnUpdate.setBackground(new Color(37, 99, 235));

        btnUpdate.setForeground(Color.WHITE);

        btnUpdate.setFocusPainted(false);

        btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 14));

        cardPanel.add(btnUpdate);

        loadProfile();

        btnUpdate.addActionListener(e -> updateProfile());
    }

    private void loadProfile() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT * FROM hr_profiles WHERE user_id=?");

            st.setInt(1, UserSession.userId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                txtName.setText(rs.getString("full_name"));

                txtDepartment.setText(rs.getString("department"));

                txtPosition.setText(rs.getString("position"));

                txtEmail.setText(rs.getString("email"));

                txtContact.setText(rs.getString("contact_no"));

                txtAddress.setText(rs.getString("address"));

                txtStatus.setText(rs.getString("status"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Loading Profile!");
        }
    }

    private void updateProfile() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("UPDATE hr_profiles SET " + "full_name=?, " + "department=?, " + "position=?, " + "email=?, " + "contact_no=?, " + "address=?, " + "status=? " + "WHERE user_id=?");

            st.setString(1, txtName.getText());
            st.setString(2, txtDepartment.getText());
            st.setString(3, txtPosition.getText());
            st.setString(4, txtEmail.getText());
            st.setString(5, txtContact.getText());
            st.setString(6, txtAddress.getText());
            st.setString(7, txtStatus.getText());

            st.setInt(8, UserSession.userId);

            st.executeUpdate();

            JOptionPane.showMessageDialog(this, "HR Profile Updated Successfully!");

            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Updating Profile!");
        }
    }
}