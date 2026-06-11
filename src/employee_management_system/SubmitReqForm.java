package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubmitReqForm extends JPanel {

    JLabel lblTitle, lblName, lblDate, lblReason;

    JTextField txtName, txtDate, txtReason;

    JButton btnSubmit;

    JPanel cardPanel;

    public SubmitReqForm() {

        setLayout(null);

        setBackground(new Color(245, 247, 250));

        cardPanel = new JPanel();

        cardPanel.setLayout(null);

        cardPanel.setBounds(170, 40, 600, 420);

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        add(cardPanel);

        lblTitle = new JLabel("Leave Request Form");

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setBounds(190, 25, 250, 35);

        cardPanel.add(lblTitle);

        lblName = new JLabel("Employee Name:");

        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblName.setBounds(60, 100, 140, 30);

        cardPanel.add(lblName);

        txtName = new JTextField(UserSession.name);
        txtName.setEditable(false);

        txtName.setBounds(220, 100, 280, 32);

        cardPanel.add(txtName);

        lblDate = new JLabel("Date:");

        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblDate.setBounds(60, 160, 140, 30);

        cardPanel.add(lblDate);

        txtDate = new JTextField();

        txtDate.setBounds(220, 160, 280, 32);

        cardPanel.add(txtDate);

        lblReason = new JLabel("Reason:");

        lblReason.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblReason.setBounds(60, 220, 140, 30);

        cardPanel.add(lblReason);

        txtReason = new JTextField();

        txtReason.setBounds(220, 220, 280, 32);

        cardPanel.add(txtReason);

        btnSubmit = new JButton("SUBMIT REQUEST");

        btnSubmit.setBounds(210, 310, 180, 42);

        btnSubmit.setBackground(new Color(37, 99, 235));

        btnSubmit.setForeground(Color.WHITE);

        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnSubmit.setFocusPainted(false);

        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cardPanel.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String name = txtName.getText();
                String date = txtDate.getText();
                String reason = txtReason.getText();

                if (date.isEmpty() || reason.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Complete all required fields!");

                    return;
                }

                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

                    PreparedStatement st = con.prepareStatement("INSERT INTO requests " + "(employee_id,employee_name, request_type, " + "request_date, amount, reason, status) " + "VALUES (?, ?, ?, ?, ?, ?,?)");

                    st.setInt(1, Integer.parseInt(UserSession.employeeID));

                    st.setString(2, UserSession.name);

                    st.setString(3, "Leave");

                    st.setString(4, date);

                    st.setString(5, "-");

                    st.setString(6, reason);

                    st.setString(7, "Pending");

                    st.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Leave Request Submitted Successfully!");

                    st.close();
                    con.close();

                    txtDate.setText("");
                    txtReason.setText("");

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(null, "Database Error!");
                }
            }
        });
    }
}