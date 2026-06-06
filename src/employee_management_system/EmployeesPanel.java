package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EmployeesPanel extends JPanel
        implements ActionListener {

    JTable table;

    DefaultTableModel tableModel;

    JButton refreshButton, updateButton, deleteButton;

    JTextField idField,
           nameField,
           positionField,
           departmentField,
           contactField,
           performanceField;

    public EmployeesPanel() {

        setLayout(null);
        setBounds(0, 0, 700, 500);

        JLabel lblTitle = new JLabel("Employees");
        lblTitle.setBounds(300, 20, 200, 30);

        add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");
        tableModel.addColumn("Department");
        tableModel.addColumn("Contact No");
        tableModel.addColumn("Performance");
        
        table = new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(30, 60, 400, 250);

        add(scrollPane);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

               int selectedRow = table.getSelectedRow();
               idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
               nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
               positionField.setText(tableModel.getValueAt(selectedRow, 2).toString());
               departmentField.setText(tableModel.getValueAt(selectedRow, 3).toString());
               contactField.setText(tableModel.getValueAt(selectedRow, 4).toString());
               performanceField.setText(tableModel.getValueAt(selectedRow, 5).toString());            }
        });

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(470, 80, 80, 25);

        add(lblId);

        idField = new JTextField();
        idField.setBounds(540, 80, 120, 25);

        add(idField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(470, 130, 80, 25);

        add(lblName);

        nameField = new JTextField();
        nameField.setBounds(540, 130, 120, 25);

        add(nameField);


        JLabel lblPosition = new JLabel("Position:");

        lblPosition.setBounds(470, 180, 80, 25);

        add(lblPosition);

        positionField = new JTextField();
        positionField.setBounds(540, 180,120, 25);
        add(positionField);
        
        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(470, 230, 80, 25);
        add(lblDepartment);

        departmentField = new JTextField();
        departmentField.setBounds(540, 230, 120, 25);
        add(departmentField);

        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(470, 280, 80, 25);
        add(lblContact);

        contactField = new JTextField();
        contactField.setBounds(540, 280, 120, 25);
        add(contactField);

        JLabel lblPerformance = new JLabel("Performance:");
        lblPerformance.setBounds(470, 330, 80, 25);
        add(lblPerformance);

        performanceField = new JTextField();
        performanceField.setBounds(540, 330, 120, 25);
        add(performanceField);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(40, 390,100, 30);
        add(refreshButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 390,100, 30);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(320, 390, 100, 30);
        add(deleteButton);


        refreshButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        loadEmployees();
    }

    public void loadEmployees() {

        tableModel.setRowCount(0);

        for (Employee emp : EmployeeData.employees) {

            tableModel.addRow(new Object[]{
            emp.id,
            emp.name,
            emp.position,
            emp.department,
            emp.contactNo,
            emp.performance
});        }
    }

    public void updateEmployee() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,"Select Employee First");

            return;
        }

        try {

            int id = Integer.parseInt(idField.getText());

            String name =nameField.getText();
 
            String position = positionField.getText();
            
            String department = departmentField.getText();
            
            String contactNo = contactField.getText();
            
            String performance = performanceField.getText();
            
            EmployeeData.employees.get(selectedRow).id = id;

            EmployeeData.employees.get(selectedRow).name = name;

            EmployeeData.employees.get(selectedRow).position = position;
            
            EmployeeData.employees.get(selectedRow).department = department;
            
            EmployeeData.employees.get(selectedRow).contactNo = contactNo;
            
            EmployeeData.employees.get(selectedRow).performance = performance;
            
            loadEmployees();

            JOptionPane.showMessageDialog(this,"Employee Updated");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Enter Valid Data");
        }
    }

    public void deleteEmployee() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Select Employee First");

            return;
        }

        EmployeeData.employees .remove(selectedRow);

        loadEmployees();

        clearFields();

        JOptionPane.showMessageDialog(this, "Employee Deleted");
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

        if (e.getSource() == refreshButton) {

            loadEmployees();
        }

        else if (e.getSource() == updateButton) {

            updateEmployee();
        }

        else if (e.getSource() == deleteButton) {

            deleteEmployee();
        }
    }
}