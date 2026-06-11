package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmployeeProfilePanel extends JPanel implements ActionListener {

    JLabel lblTitle;

    JTextField txtName, txtEmployeeID, txtDepartment, txtPosition, txtEmail, txtContact, txtAddress;

    JComboBox<String> cmbStatus;

    JButton btnUpdate;

    JPanel cardPanel;

    public EmployeeProfilePanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(80, 20, 700, 550);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        lblTitle = new JLabel("Personal Information");

        lblTitle.setBounds(250, 20, 270, 35);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));

        cardPanel.add(lblTitle);

        JLabel lblName = new JLabel("Name:");

        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblName.setBounds(60, 90, 140, 30);

        cardPanel.add(lblName);

        txtName = new JTextField(UserSession.name);

        txtName.setBounds(220, 90, 320, 32);

        cardPanel.add(txtName);

        JLabel lblEmployeeID = new JLabel("Employee ID:");

        lblEmployeeID.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblEmployeeID.setBounds(60, 140, 140, 30);

        cardPanel.add(lblEmployeeID);

        txtEmployeeID = new JTextField(UserSession.employeeID);

        txtEmployeeID.setBounds(220, 140, 320, 32);

        cardPanel.add(txtEmployeeID);

        JLabel lblDepartment = new JLabel("Department:");

        lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblDepartment.setBounds(60, 190, 140, 30);

        cardPanel.add(lblDepartment);

        txtDepartment = new JTextField(UserSession.department);

        txtDepartment.setBounds(220, 190, 320, 32);

        cardPanel.add(txtDepartment);

        JLabel lblPosition = new JLabel("Position:");

        lblPosition.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblPosition.setBounds(60, 240, 140, 30);

        cardPanel.add(lblPosition);

        txtPosition = new JTextField(UserSession.position);

        txtPosition.setBounds(220, 240, 320, 32);

        cardPanel.add(txtPosition);

        JLabel lblEmail = new JLabel("Email:");

        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblEmail.setBounds(60, 290, 140, 30);

        cardPanel.add(lblEmail);

        txtEmail = new JTextField(UserSession.email);

        txtEmail.setBounds(220, 290, 320, 32);

        cardPanel.add(txtEmail);

        JLabel lblContact = new JLabel("Contact:");

        lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblContact.setBounds(60, 340, 140, 30);

        cardPanel.add(lblContact);

        txtContact = new JTextField(UserSession.contact);

        txtContact.setBounds(220, 340, 320, 32);

        cardPanel.add(txtContact);

        JLabel lblAddress = new JLabel("Address:");

        lblAddress.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblAddress.setBounds(60, 390, 140, 30);

        cardPanel.add(lblAddress);

        txtAddress = new JTextField(UserSession.address);

        txtAddress.setBounds(220, 390, 320, 32);

        cardPanel.add(txtAddress);

        JLabel lblStatus = new JLabel("Status:");

        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblStatus.setBounds(60, 440, 140, 30);

        cardPanel.add(lblStatus);

        String[] status = {"Active", "On Leave", "Inactive"};

        cmbStatus = new JComboBox<>(status);

        cmbStatus.setBounds(220, 440, 320, 32);

        cmbStatus.setSelectedItem(UserSession.status);

        cardPanel.add(cmbStatus);

        btnUpdate = new JButton("UPDATE PROFILE");

        btnUpdate.setBounds(250, 495, 200, 40);

        btnUpdate.setBackground(new Color(37, 99, 235));

        btnUpdate.setForeground(Color.WHITE);

        btnUpdate.setFocusPainted(false);

        btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 14));

        cardPanel.add(btnUpdate);

        btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("UPDATE employees SET " + "full_name=?, " + "department=?, " + "position=?, " + "email=?, " + "contact_no=?, " + "address=?, " + "status=? " + "WHERE employee_id=?");

            st.setString(1, txtName.getText());

            st.setString(2, txtDepartment.getText());

            st.setString(3, txtPosition.getText());

            st.setString(4, txtEmail.getText());

            st.setString(5, txtContact.getText());

            st.setString(6, txtAddress.getText());

            st.setString(7, cmbStatus.getSelectedItem().toString());

            st.setInt(8, Integer.parseInt(txtEmployeeID.getText()));

            st.executeUpdate();

            st.close();

            con.close();

            UserSession.name = txtName.getText();

            UserSession.employeeID = txtEmployeeID.getText();

            UserSession.department = txtDepartment.getText();

            UserSession.position = txtPosition.getText();

            UserSession.email = txtEmail.getText();

            UserSession.contact = txtContact.getText();

            UserSession.address = txtAddress.getText();

            UserSession.status = cmbStatus.getSelectedItem().toString();

            JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Updating Profile!");
        }
    }
}