
package oop.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lourd Leal Cruz
 */


public class EMPLOYEESPANEL extends JPanel
        implements ActionListener {

    JTable table;

    DefaultTableModel tableModel;

    JButton refreshButton, updateButton, deleteButton;

    JTextField idField, nameField, positionField;

    public EMPLOYEESPANEL() {

        setLayout(null);
        setBounds(0, 0, 700, 500);

        JLabel lblTitle = new JLabel("Employees");
        lblTitle.setBounds(300, 20, 200, 30);

        add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");

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
            }
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

        refreshButton = new JButton("Refresh");

        refreshButton.setBounds(40, 340,100, 30);

        add(refreshButton);

        updateButton = new JButton("Update");

        updateButton.setBounds(180, 340,100, 30);

        add(updateButton);

        deleteButton = new JButton("Delete");

        deleteButton.setBounds(320, 340, 100, 30);

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
                    emp.position
            });
        }
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

            EmployeeData.employees.get(selectedRow).id = id;

            EmployeeData.employees.get(selectedRow).name = name;

            EmployeeData.employees.get(selectedRow).position = position;

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
