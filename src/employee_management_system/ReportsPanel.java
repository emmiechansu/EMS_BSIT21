package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportsPanel extends JPanel {

    JTable reportTable;

    DefaultTableModel tableModel;

    public ReportsPanel() {

        setLayout(null);

        setBounds(0,0,700,500);

        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Employee Reports");

        lblTitle.setBounds(250,20,300,30);

        lblTitle.setFont( new Font("Arial",Font.BOLD,20));

        add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Employee");
        tableModel.addColumn("Position");
        tableModel.addColumn("Request");
        tableModel.addColumn("Status");

        reportTable =new JTable(tableModel);

        JScrollPane scrollPane =new JScrollPane(reportTable);

        scrollPane.setBounds(20,70,650,300);

        add(scrollPane);

        loadReports();

        JLabel lblTotal = new JLabel("Total Employees: "+ EmployeeData.employees.size());

        lblTotal.setBounds(20,390,250,25);

        lblTotal.setFont(new Font("Arial",Font.BOLD,14));

        add(lblTotal);
    }

    public void loadReports() {

        tableModel.setRowCount(0);

        for(Employee emp : EmployeeData.employees) {

            String requestType = "No Request";

            String requestStatus ="-";
            
            for(Request req :RequestData.requests) {

                if(req.employeeName.equalsIgnoreCase(emp.name)) {

                    requestType =req.type;

                    requestStatus =req.status;
                }
            }

            tableModel.addRow(new Object[] {

                    emp.id,
                    emp.name,
                    emp.position,
                    requestType,
                    requestStatus
            });
        }
    }
}