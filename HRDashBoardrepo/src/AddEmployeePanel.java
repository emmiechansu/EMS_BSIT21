package employee_management_system;

import javax.swing.*;
import java.awt.event.*;

public class AddEmployeePanel extends JPanel
        implements ActionListener {

    JTextField idField, nameField, positionField;

    JButton addButton;

    public AddEmployeePanel() {

        setLayout(null);
        setBounds(0, 0, 700, 500);

        JLabel lblTitle = new JLabel("Add Employee");
        lblTitle.setBounds(280, 20, 200, 30);
        add(lblTitle);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(180, 100, 100, 25);
        add(lblId);

        idField = new JTextField();
        idField.setBounds(280, 100, 180, 25);
        add(idField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(180, 150, 100, 25);
        add(lblName);

        nameField = new JTextField();
        nameField.setBounds(280, 150, 180, 25);
        add(nameField);

        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setBounds(180, 200, 100, 25);
        add(lblPosition);

        positionField = new JTextField();
        positionField.setBounds(280, 200, 180, 25);
        add(positionField);

        addButton = new JButton("Add Employee");
        addButton.setBounds(260, 270, 150, 30);
        add(addButton);

        addButton.addActionListener(this);
    }

    public void addEmployee() {

        try {

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText();
            String position = positionField.getText();

            Employee emp =
                    new Employee(id, name, position);

            EmployeeData.employees.add(emp);

            JOptionPane.showMessageDialog(this,
                    "Employee Added");

            clearFields();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Enter Valid Data");
        }
    }

    public void clearFields() {

        idField.setText("");
        nameField.setText("");
        positionField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addEmployee();
        }
    }
}