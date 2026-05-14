package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SubmitReqForm extends JPanel {

    private JLabel lblTitle, lblType, lblDate, lblAmount, lblReason;
    private JComboBox<String> cmbType;
    private JTextField txtDate, txtAmount, txtReason;
    private JButton btnSubmit, btnBack;

    public SubmitReqForm() {

        setLayout(null);
        setBackground(Color.WHITE);

       
        lblTitle = new JLabel("Submit Request");
        lblTitle.setBounds(150, 10, 200, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle);

        
        lblType = new JLabel("Type:");
        lblType.setBounds(30, 60, 100, 25);
        add(lblType);

        String[] types = {"Leave", "Expense"};
        cmbType = new JComboBox<>(types);
        cmbType.setBounds(120, 60, 150, 25);
        add(cmbType);

        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(30, 100, 100, 25);
        add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(120, 100, 150, 25);
        add(txtDate);

        
        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 140, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(120, 140, 150, 25);
        add(txtAmount);

        
        lblReason = new JLabel("Reason:");
        lblReason.setBounds(30, 180, 100, 25);
        add(lblReason);

        txtReason = new JTextField();
        txtReason.setBounds(120, 180, 150, 25);
        add(txtReason);

        
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(120, 230, 100, 30);
        add(btnSubmit);

       
       
        txtAmount.setEnabled(false);

        cmbType.addActionListener(e -> {
            if (cmbType.getSelectedItem().equals("Expense")) {
                txtAmount.setEnabled(true);
            } else {
                txtAmount.setEnabled(false);
                txtAmount.setText("");
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type = cmbType.getSelectedItem().toString();
                String date = txtDate.getText();
                String amount = txtAmount.getText();
                String reason = txtReason.getText();

                Request req = new Request(type, date, amount, reason, "Pending");
                RequestData.requests.add(req);

                JOptionPane.showMessageDialog(null, "Request Submitted (Pending)");
            }
        });
    }
}