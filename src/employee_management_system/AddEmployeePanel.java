package employee_management_system;

import javax.swing.*;
import java.awt.event.*;

public class AddEmployeePanel extends JPanel
        implements ActionListener {

    JTextField idField,
            nameField,
            positionField,
            departmentField,
            contactField,
            performanceField;

    JButton addButton;

    public AddEmployeePanel() {

        setLayout(null);
        setBounds(0, 0, 700, 500);

        JLabel lblTitle = new JLabel("Add Employee");
        lblTitle.setBounds(280, 20, 200, 30);
        add(lblTitle);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(180, 80, 100, 25);
        add(lblId);

        idField = new JTextField();
        idField.setBounds(280, 80, 180, 25);
        add(idField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(180, 120, 100, 25);
        add(lblName);

        nameField = new JTextField();
        nameField.setBounds(280, 120, 180, 25);
        add(nameField);

        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setBounds(180, 160, 100, 25);
        add(lblPosition);

        positionField = new JTextField();
        positionField.setBounds(280, 160, 180, 25);
        add(positionField);

        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(180, 200, 100, 25);
        add(lblDepartment);

        departmentField = new JTextField();
        departmentField.setBounds(280, 200, 180, 25);
        add(departmentField);

        JLabel lblContact = new JLabel("Contact No:");
        lblContact.setBounds(180, 240, 100, 25);
        add(lblContact);

        contactField = new JTextField();
        contactField.setBounds(280, 240, 180, 25);
        add(contactField);

        JLabel lblPerformance = new JLabel("Performance:");
        lblPerformance.setBounds(180, 280, 100, 25);
        add(lblPerformance);

        performanceField = new JTextField();
        performanceField.setBounds(280, 280, 180, 25);
        add(performanceField);

        addButton = new JButton("Add Employee");
        addButton.setBounds(260, 340, 150, 30);
        add(addButton);

        addButton.addActionListener(this);
    }

    public void addEmployee() {

        try {

            if (idField.getText().isEmpty()
                    || nameField.getText().isEmpty()
                    || positionField.getText().isEmpty()
                    || departmentField.getText().isEmpty()
                    || contactField.getText().isEmpty()
                    || performanceField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.");
                return;
            }

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText();
            String position = positionField.getText();

            for (Employee emp : EmployeeData.employees) {

                if (emp.getId() == id) {

                    JOptionPane.showMessageDialog(this,
                            "Employee ID already exists.");
                    return;
                }
            }

           String department = departmentField.getText();
           String contactNo = contactField.getText();
           String performance = performanceField.getText();

        Employee emp =
        new Employee(
                id,
                name,
                position,
                department,
                contactNo,
                performance
        );

            EmployeeData.employees.add(emp);

            JOptionPane.showMessageDialog(this,
                    "Employee added successfully!");

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "ID must be a number.");
        }
    }

    public void clearFields() {

        idField.setText("");
        nameField.setText("");
        positionField.setText("");
        departmentField.setText("");
        contactField.setText("");
        performanceField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addEmployee();
        }
    }
}