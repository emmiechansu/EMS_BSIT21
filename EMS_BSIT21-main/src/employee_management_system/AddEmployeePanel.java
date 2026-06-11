package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployeePanel extends JPanel implements ActionListener {

    JTextField idField;
    JTextField nameField;
    JTextField positionField;
    JTextField departmentField;
    JTextField contactField;

    JComboBox<String> performanceBox;

    JTextArea remarksArea;

    JButton addButton;

    JPanel cardPanel;

    public AddEmployeePanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));
        
        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(100, 20, 750, 560);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)));

        add(cardPanel);

        JLabel lblTitle = new JLabel("Add Employee");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(290, 20, 250, 35);
        cardPanel.add(lblTitle);

        JLabel lblId = new JLabel("Employee ID");
        lblId.setBounds(120, 90, 120, 25);
        cardPanel.add(lblId);

        idField = new JTextField();
        idField.setBounds(260, 90, 300, 35);
        cardPanel.add(idField);

        JLabel lblName = new JLabel("Full Name");
        lblName.setBounds(120, 140, 120, 25);
        cardPanel.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(260, 140, 300, 35);
        cardPanel.add(nameField);

        JLabel lblPosition = new JLabel("Position");
        lblPosition.setBounds(120, 190, 120, 25);
        cardPanel.add(lblPosition);

        positionField = new JTextField();
        positionField.setBounds(260, 190, 300, 35);
        cardPanel.add(positionField);

        JLabel lblDepartment = new JLabel("Department");
        lblDepartment.setBounds(120, 240, 120, 25);
        cardPanel.add(lblDepartment);

        departmentField = new JTextField();
        departmentField.setBounds(260, 240, 300, 35);
        cardPanel.add(departmentField);

        JLabel lblContact = new JLabel("Contact No.");
        lblContact.setBounds(120, 290, 120, 25);
        cardPanel.add(lblContact);

        contactField = new JTextField();
        contactField.setBounds(260, 290, 300, 35);
        cardPanel.add(contactField);

        JLabel lblPerformance = new JLabel("Performance");
        lblPerformance.setBounds(120, 340, 120, 25);
        cardPanel.add(lblPerformance);

        String[] ratings = {
                "Excellent",
                "Good",
                "Satisfactory",
                "Needs Improvement"
        };

        performanceBox = new JComboBox<>(ratings);
        performanceBox.setBounds(260, 340, 300, 35);
        cardPanel.add(performanceBox);

        JLabel lblRemarks = new JLabel("Remarks");
        lblRemarks.setBounds(120, 390, 120, 25);
        cardPanel.add(lblRemarks);
        
        remarksArea = new JTextArea();
        remarksArea.setLineWrap(true);
        remarksArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(remarksArea);
        scrollPane.setBounds(260, 390, 300, 80);
        cardPanel.add(scrollPane);

        addButton = new JButton("Add Employee");

        addButton.setBounds(300, 500, 150, 40);

        addButton.setBackground(
                new Color(37, 99, 235));

        addButton.setForeground(
                Color.WHITE);

        addButton.setFont(
                new Font("Segoe UI", Font.BOLD, 14));

        addButton.setFocusPainted(false);
        cardPanel.add(addButton);
        addButton.addActionListener(this);
    }

    public void addEmployee() {

        try {

            if (idField.getText().isEmpty()
                    || nameField.getText().isEmpty()
                    || positionField.getText().isEmpty()
                    || departmentField.getText().isEmpty()
                    || contactField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idField.getText()
                    );

            String name =
                    nameField.getText();

            String position =
                    positionField.getText();

            String department =
                    departmentField.getText();

            String contactNo =
                    contactField.getText();

            if (contactNo.length() != 11) {
                JOptionPane.showMessageDialog(
                        this,
                        "Contact number must be exactly 11 digits!"
                );
                return;
            }

            if (!contactNo.matches("\\d+")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Contact number must contain only numeric digits!"
                );
                return;
            }

            String performance =
                    performanceBox.getSelectedItem()
                            .toString();

            String remarks =
                    remarksArea.getText();

            Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/emp_management",
                            "root",
                            ""
                    );

            PreparedStatement st =
                    con.prepareStatement(

                            "INSERT INTO employees "
                            + "(employee_id, full_name, position, "
                            + "department, contact_no, performance, remarks) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)"
                    );

            st.setInt(1, id);
            st.setString(2, name);
            st.setString(3, position);
            st.setString(4, department);
            st.setString(5, contactNo);
            st.setString(6, performance);
            st.setString(7, remarks);

            st.executeUpdate();


            String username =
                    name.toLowerCase()
                            .replace(" ", "")
                            + id;

            String password =
                    "Welcome123";

            PreparedStatement cred =
                    con.prepareStatement(

                            "INSERT INTO employee_credentials "
                            + "(employee_id, username, password, first_login) "
                            + "VALUES (?, ?, ?, 1)"
                    );

            cred.setInt(1, id);
            cred.setString(2, username);
            cred.setString(3, password);

            cred.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee Added Successfully!\n\n"
                    + "Username: " + username
                    + "\nPassword: " + password
                    + "\n\nEmployee must change password on first login."
            );

            cred.close();
            st.close();
            con.close();

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID must be numeric!"
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + ex.getMessage()
            );
        }
    }

    public void clearFields() {

        idField.setText("");
        nameField.setText("");
        positionField.setText("");
        departmentField.setText("");
        contactField.setText("");
        performanceBox.setSelectedIndex(0);
        remarksArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addEmployee();
        }
    }
}