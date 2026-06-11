package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class EmployeesPanel extends JPanel implements ActionListener {

    JTable table;

    DefaultTableModel tableModel;

    JButton refreshButton, updateButton, deleteButton;

    JTextField idField, nameField, positionField, departmentField, contactField;

    JComboBox<String> performanceBox;

    JTextArea remarksArea;

    JLabel lblTotal;

    public EmployeesPanel() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        JPanel cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(20, 20, 900, 550);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        JLabel lblTitle = new JLabel("Manage Employees");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setBounds(320, 20, 300, 35);

        cardPanel.add(lblTitle);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");
        tableModel.addColumn("Department");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Performance");
        tableModel.addColumn("Remarks");

        table = new JTable(tableModel);

        table.setRowHeight(30);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(20, 80, 520, 300);

        cardPanel.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                if (row != -1) {

                    idField.setText(tableModel.getValueAt(row, 0).toString());

                    nameField.setText(tableModel.getValueAt(row, 1).toString());

                    positionField.setText(tableModel.getValueAt(row, 2).toString());

                    departmentField.setText(tableModel.getValueAt(row, 3).toString());

                    contactField.setText(tableModel.getValueAt(row, 4).toString());

                    performanceBox.setSelectedItem(tableModel.getValueAt(row, 5).toString());

                    remarksArea.setText(tableModel.getValueAt(row, 6).toString());
                }
            }
        });

        JPanel formPanel = new JPanel();

        formPanel.setLayout(null);

        formPanel.setBounds(560, 80, 300, 360);

        formPanel.setBackground(new Color(248, 250, 252));

        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        cardPanel.add(formPanel);

        JLabel lblId = new JLabel("ID");

        lblId.setBounds(20, 30, 100, 25);

        formPanel.add(lblId);

        idField = new JTextField();

        idField.setBounds(120, 30, 150, 30);

        formPanel.add(idField);

        JLabel lblName = new JLabel("Name");

        lblName.setBounds(20, 70, 100, 25);

        formPanel.add(lblName);

        nameField = new JTextField();

        nameField.setBounds(120, 70, 150, 30);

        formPanel.add(nameField);

        JLabel lblPosition = new JLabel("Position");

        lblPosition.setBounds(20, 110, 100, 25);

        formPanel.add(lblPosition);

        positionField = new JTextField();

        positionField.setBounds(120, 110, 150, 30);

        formPanel.add(positionField);

        JLabel lblDepartment = new JLabel("Department");

        lblDepartment.setBounds(20, 150, 100, 25);

        formPanel.add(lblDepartment);

        departmentField = new JTextField();

        departmentField.setBounds(120, 150, 150, 30);

        formPanel.add(departmentField);

        JLabel lblContact = new JLabel("Contact");

        lblContact.setBounds(20, 190, 100, 25);

        formPanel.add(lblContact);

        contactField = new JTextField();

        contactField.setBounds(120, 190, 150, 30);

        formPanel.add(contactField);

        JLabel lblPerformance = new JLabel("Performance");

        lblPerformance.setBounds(20, 230, 100, 25);

        formPanel.add(lblPerformance);

        String[] ratings = {"Excellent", "Good", "Satisfactory", "Needs Improvement"};

        performanceBox = new JComboBox<>(ratings);

        performanceBox.setBounds(120, 230, 150, 30);

        formPanel.add(performanceBox);

        JLabel lblRemarks = new JLabel("Remarks");

        lblRemarks.setBounds(20, 270, 100, 25);

        formPanel.add(lblRemarks);

        remarksArea = new JTextArea();

        JScrollPane remarksScroll = new JScrollPane(remarksArea);

        remarksScroll.setBounds(120, 270, 150, 60);

        formPanel.add(remarksScroll);

        refreshButton = new JButton("Refresh");

        refreshButton.setBounds(20, 470, 120, 40);

        styleButton(refreshButton);

        cardPanel.add(refreshButton);

        updateButton = new JButton("Update");

        updateButton.setBounds(160, 470, 120, 40);

        styleButton(updateButton);

        cardPanel.add(updateButton);

        deleteButton = new JButton("Delete");

        deleteButton.setBounds(300, 470, 120, 40);

        deleteButton.setBackground(new Color(220, 38, 38));

        deleteButton.setForeground(Color.WHITE);

        deleteButton.setFocusPainted(false);

        cardPanel.add(deleteButton);

        lblTotal = new JLabel("Total Employees: 0");

        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblTotal.setBounds(650, 480, 200, 30);

        cardPanel.add(lblTotal);

        refreshButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        loadEmployees();
    }

    private void styleButton(JButton button) {

        button.setBackground(new Color(37, 99, 235));

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
    }

    public void loadEmployees() {

        tableModel.setRowCount(0);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("SELECT * FROM employees");

            ResultSet rs = st.executeQuery();

            int total = 0;

            while (rs.next()) {

                tableModel.addRow(new Object[]{rs.getInt("employee_id"), rs.getString("full_name"), rs.getString("position"), rs.getString("department"), rs.getString("contact_no"), rs.getString("performance"), rs.getString("remarks")});

                total++;
            }

            lblTotal.setText("Total Employees: " + total);

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Error Loading Employees!");
        }
    }

    public void updateEmployee() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Select Employee First");

            return;
        }

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("UPDATE employees SET " + "full_name=?, " + "position=?, " + "department=?, " + "contact_no=?, " + "performance=?, " + "remarks=? " + "WHERE employee_id=?");

            st.setString(1, nameField.getText());
            st.setString(2, positionField.getText());
            st.setString(3, departmentField.getText());
            st.setString(4, contactField.getText());
            st.setString(5, performanceBox.getSelectedItem().toString());
            st.setString(6, remarksArea.getText());

            st.setInt(7, Integer.parseInt(idField.getText()));

            st.executeUpdate();

            st.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Employee Updated Successfully!");

            loadEmployees();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Update Failed!");
        }
    }

    public void deleteEmployee() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Select Employee First");

            return;
        }

        try {

            int id = Integer.parseInt(idField.getText());

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            PreparedStatement st = con.prepareStatement("DELETE FROM employees " + "WHERE employee_id=?");

            st.setInt(1, id);

            st.executeUpdate();

            PreparedStatement cred = con.prepareStatement("DELETE FROM employee_credentials " + "WHERE employee_id=?");

            cred.setInt(1, id);

            cred.executeUpdate();

            cred.close();
            st.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Employee Deleted Successfully!");

            loadEmployees();

            clearFields();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Delete Failed!");
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

        if (e.getSource() == refreshButton) {

            loadEmployees();

        } else if (e.getSource() == updateButton) {

            updateEmployee();

        } else if (e.getSource() == deleteButton) {

            deleteEmployee();
        }
    }
}