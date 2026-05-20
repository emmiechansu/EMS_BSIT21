package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeProfilePanel extends JPanel
        implements ActionListener {

    JLabel lblTitle;

    JTextField txtName, txtEmployeeID, txtDepartment, txtPosition, txtEmail, txtContact, txtAddress;

    JComboBox<String> cmbStatus;

    JButton btnUpdate;

    JPanel cardPanel;

    public EmployeeProfilePanel() {

        setLayout(null);

        setBackground(new Color(240,240,240));

        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(90,20,520,520);

        cardPanel.setBackground(Color.WHITE);

        add(cardPanel);

        lblTitle = new JLabel("EMPLOYEE PROFILE");

        lblTitle.setBounds(140,20,300,30);

        lblTitle.setFont(
                new Font("Arial",
                        Font.BOLD,22));

        cardPanel.add(lblTitle);

        JLabel lblName = new JLabel("Name:");

        lblName.setBounds(50,80,120,25);

        cardPanel.add(lblName);

        txtName = new JTextField(
                UserSession.name
        );

        txtName.setBounds(180,80,250,25);

        cardPanel.add(txtName);

        JLabel lblEmployeeID =
                new JLabel("Employee ID:");

        lblEmployeeID.setBounds(50,120,120,25);

        cardPanel.add(lblEmployeeID);

        txtEmployeeID = new JTextField(
                UserSession.employeeID
        );

        txtEmployeeID.setBounds(180,120,250,25);

        cardPanel.add(txtEmployeeID);

        JLabel lblDepartment =
                new JLabel("Department:");

        lblDepartment.setBounds(50,160,120,25);

        cardPanel.add(lblDepartment);

        txtDepartment = new JTextField(
                UserSession.department
        );

        txtDepartment.setBounds(180,160,250,25);

        cardPanel.add(txtDepartment);

        JLabel lblPosition =
                new JLabel("Position:");

        lblPosition.setBounds(50,200,120,25);

        cardPanel.add(lblPosition);

        txtPosition = new JTextField(
                UserSession.position
        );

        txtPosition.setBounds(180,200,250,25);

        cardPanel.add(txtPosition);

        JLabel lblEmail =
                new JLabel("Email:");

        lblEmail.setBounds(50,240,120,25);

        cardPanel.add(lblEmail);

        txtEmail = new JTextField(
                UserSession.email
        );

        txtEmail.setBounds(180,240,250,25);

        cardPanel.add(txtEmail);

        JLabel lblContact =
                new JLabel("Contact:");

        lblContact.setBounds(50,280,120,25);

        cardPanel.add(lblContact);

        txtContact = new JTextField(
                UserSession.contact
        );

        txtContact.setBounds(180,280,250,25);

        cardPanel.add(txtContact);

        JLabel lblAddress =
                new JLabel("Address:");

        lblAddress.setBounds(50,320,120,25);

        cardPanel.add(lblAddress);

        txtAddress = new JTextField(
                UserSession.address
        );

        txtAddress.setBounds(180,320,250,25);

        cardPanel.add(txtAddress);

        JLabel lblStatus =
                new JLabel("Status:");

        lblStatus.setBounds(50,360,120,25);

        cardPanel.add(lblStatus);

        String[] status = {
                "Active",
                "On Leave",
                "Inactive"
        };

        cmbStatus =
                new JComboBox<>(status);

        cmbStatus.setBounds(180,360,250,25);

        cmbStatus.setSelectedItem(
                UserSession.status
        );

        cardPanel.add(cmbStatus);

        btnUpdate =
                new JButton("Update Profile");

        btnUpdate.setBounds(170,430,180,35);

        cardPanel.add(btnUpdate);

        btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        UserSession.name =
                txtName.getText();

        UserSession.employeeID =
                txtEmployeeID.getText();

        UserSession.department =
                txtDepartment.getText();

        UserSession.position =
                txtPosition.getText();

        UserSession.email =
                txtEmail.getText();

        UserSession.contact =
                txtContact.getText();

        UserSession.address =
                txtAddress.getText();

        UserSession.status =
                cmbStatus.getSelectedItem()
                        .toString();

        JOptionPane.showMessageDialog(this,
                "Profile Updated Successfully!");
    }
}