package oop.project;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

class Employee {
    int id;
    String name;
    String position;
}

public class HRDASHBOARD extends JFrame implements ActionListener {

    JTextField idField, nameField, positionField;
    JButton addButton, viewButton, updateButton, deleteButton;
    JTable table;
    DefaultTableModel tableModel;
    JPanel panel;

    Employee[] employee = new Employee[10];
    int employeeCount = 0;

    public HRDASHBOARD() {

        setTitle("HR Dashboard");
        setSize(700, 500);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 20, 120, 250);

        addButton = new JButton("Add");
        addButton.setBounds(10, 20, 100, 30);
        panel.add(addButton);

        viewButton = new JButton("View");
        viewButton.setBounds(10, 60, 100, 30);
        panel.add(viewButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(10, 100, 100, 30);
        panel.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(10, 140, 100, 30);
        panel.add(deleteButton);

        add(panel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(160, 20, 80, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(240, 20, 150, 20);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(160, 50, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(240, 50, 150, 20);
        add(nameField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(160, 80, 80, 20);
        add(positionLabel);

        positionField = new JTextField();
        positionField.setBounds(240, 80, 150, 20);
        add(positionField);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");

        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(160, 130, 500, 220);

        add(scroll);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Enter valid ID");
                return;
            }

            if (employeeCount < employee.length) {
                employee[employeeCount] = new Employee();
                employee[employeeCount].id = id;
                employee[employeeCount].name = nameField.getText();
                employee[employeeCount].position = positionField.getText();
                employeeCount++;
            }
        }

        if (e.getSource() == viewButton) {
            tableModel.setRowCount(0);
            for (int employeeNumber = 0; employeeNumber < employeeCount; employeeNumber++) {
                tableModel.addRow(new Object[]{
                        employee[employeeNumber].id,
                        employee[employeeNumber].name,
                        employee[employeeNumber].position

                });
            }
        }

        if (e.getSource() == updateButton) {
            int id = Integer.parseInt(idField.getText());
            for (int employeeNumber = 0; employeeNumber < employeeCount; employeeNumber++) {
                if (employee[employeeNumber].id == id) {
                    employee[employeeNumber].name = nameField.getText();
                    employee[employeeNumber].position = positionField.getText();

                    break;
                }
            }
        }

        if (e.getSource() == deleteButton) {
            int id = Integer.parseInt(idField.getText());
            for (int employeeNumber = 0; employeeNumber < employeeCount; employeeNumber++) {
                if (employee[employeeNumber].id == id) {
                    for (int nextEmployeeNumber = employeeNumber; nextEmployeeNumber < employeeCount - 1; nextEmployeeNumber++) {
                        employee[nextEmployeeNumber] = employee[nextEmployeeNumber + 1];
                    }
                    employeeCount--;
                    break;
                }
            }
        }
    }
}