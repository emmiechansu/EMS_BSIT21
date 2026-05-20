package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeCredentialsPanel extends JPanel {

    JTable employeeTable;

    DefaultTableModel tableModel;

    public EmployeeCredentialsPanel() {

        setLayout(null);

        setBounds(0, 0, 700, 500);

        setBackground(Color.WHITE);

        JLabel lblTitle =
                new JLabel("Employee Credentials List");

        lblTitle.setBounds(220, 20, 300, 30);

        lblTitle.setFont(
                new Font("Arial",
                        Font.BOLD, 20));

        add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Department");
        tableModel.addColumn("Position");
        tableModel.addColumn("Email");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Address");
        tableModel.addColumn("Status");

        for (Employee emp :
                EmployeeData.employees) {

            tableModel.addRow(new Object[]{

                    emp.id,

                    emp.name,

                    UserSession.department,

                    emp.position,

                    UserSession.email,

                    UserSession.contact,

                    UserSession.address,

                    UserSession.status
            });
        }

        employeeTable =
                new JTable(tableModel);

        employeeTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(employeeTable);

        scrollPane.setBounds(20, 80,
                650, 300);

        add(scrollPane);

        JLabel lblTotal =
                new JLabel(
                        "Total Employees: "
                                + EmployeeData.employees.size()
                );

        lblTotal.setBounds(20, 400,
                250, 30);

        lblTotal.setFont(
                new Font("Arial",
                        Font.BOLD, 14));

        add(lblTotal);
    }
}