package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Employee {
    int id;
    String name;
    String position;

    public String status() {
        return id + " | " + name + " | " + position;
    }
}

public class HRDashboard extends JPanel implements ActionListener {

    JTextField idField, nameField, positionField;
    JTextArea area;
    JButton addBtn, viewBtn, updateBtn, deleteBtn;

    Employee[] emp = new Employee[10];
    int empCount = 0;

    public HRDashboard() {

        setLayout(null);
        setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 80, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 150, 20);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 50, 150, 20);
        add(nameField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(20, 80, 80, 20);
        add(posLabel);

        positionField = new JTextField();
        positionField.setBounds(100, 80, 150, 20);
        add(positionField);

        addBtn = new JButton("Add");
        addBtn.setBounds(20, 120, 90, 30);
        add(addBtn);

        viewBtn = new JButton("View");
        viewBtn.setBounds(120, 120, 90, 30);
        add(viewBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(220, 120, 90, 30);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(320, 120, 90, 30);
        add(deleteBtn);

        area = new JTextArea();
        area.setBounds(20, 170, 390, 200);
        add(area);

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        int id;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (Exception ex) {
            area.setText("Enter valid ID!");
            return;
        }

        if (e.getSource() == addBtn) {
            if (empCount < emp.length) {
                emp[empCount] = new Employee();
                emp[empCount].id = id;
                emp[empCount].name = nameField.getText();
                emp[empCount].position = positionField.getText();
                empCount++;
                area.setText("Employee added!");
            } else {
                area.setText("List full!");
            }
        }

        if (e.getSource() == viewBtn) {
            area.setText("");
            for (int i = 0; i < empCount; i++) {
                area.append(emp[i].status() + "\n");
            }
        }

        if (e.getSource() == updateBtn) {
            boolean found = false;

            for (int i = 0; i < empCount; i++) {
                if (emp[i].id == id) {
                    emp[i].name = nameField.getText();
                    emp[i].position = positionField.getText();
                    found = true;
                    area.setText("Employee updated!");
                    break;
                }
            }

            if (!found) {
                area.setText("Employee not found!");
            }
        }

        if (e.getSource() == deleteBtn) {
            boolean found = false;

            for (int i = 0; i < empCount; i++) {
                if (emp[i].id == id) {

                    for (int j = i; j < empCount - 1; j++) {
                        emp[j] = emp[j + 1];
                    }
                    empCount--;
                    found = true;
                    area.setText("Employee deleted!");
                    break;
                }
            }

            if (!found) {
                area.setText("Employee not found!");
            }
        }
    }
} 