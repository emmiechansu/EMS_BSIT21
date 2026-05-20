package employee_management_system;

import javax.swing.*;
import java.awt.event.*;

public class SubmitReqForm extends JPanel {

    JLabel lblTitle,lblName,lblType,lblDate,lblAmount,lblReason;

    JTextField txtName,txtDate,txtAmount,txtReason;

    JComboBox<String> cmbType;

    JButton btnSubmit;

    public SubmitReqForm() {

        setLayout(null);

        setBounds(0, 0, 700, 500);

        lblTitle = new JLabel("Submit Request");

        lblTitle.setBounds(260, 40, 200, 30);

        add(lblTitle);

        lblName = new JLabel("Employee Name:");

        lblName.setBounds(150, 100, 120, 25);

        add(lblName);

        txtName = new JTextField();
 
        txtName.setBounds(300, 100, 180, 25);

        add(txtName);

        lblType = new JLabel("Request Type:");

        lblType.setBounds(150, 140, 120, 25);

        add(lblType);

        String[] types = {"Leave", "Expense"};

        cmbType = new JComboBox<>(types);

        cmbType.setBounds(300, 140, 180, 25);

        add(cmbType);

        lblDate = new JLabel("Date:");

        lblDate.setBounds(150, 180, 120, 25);

        add(lblDate);

        txtDate = new JTextField();

        txtDate.setBounds(300, 180, 180, 25);

        add(txtDate);

        lblAmount = new JLabel("Amount:");

        lblAmount.setBounds(150, 220, 120, 25);

        add(lblAmount);

        txtAmount = new JTextField();

        txtAmount.setBounds(300, 220, 180, 25);

        add(txtAmount);

        txtAmount.setEnabled(false);

        lblReason = new JLabel("Reason:");

        lblReason.setBounds(150, 260, 120, 25);

        add(lblReason);

        txtReason = new JTextField();

        txtReason.setBounds(300, 260, 180, 25);

        add(txtReason);

        btnSubmit =new JButton("Submit");

        btnSubmit.setBounds(280, 330, 120, 30);

        add(btnSubmit);

        cmbType.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                String selectedType =cmbType.getSelectedItem() .toString();
                
                if(selectedType.equals("Leave")) {

                    txtAmount.setEnabled(false);

                    txtAmount.setText("");
                }

                
                else if(selectedType.equals("Expense")) {

                    txtAmount.setEnabled(true);
                }
            }
        });

        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String name = txtName.getText();

                String type = cmbType.getSelectedItem().toString();

                String date = txtDate.getText();

                String amount = txtAmount.getText();

                String reason = txtReason.getText();

                if(name.isEmpty()|| date.isEmpty()|| reason.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Complete all required fields!");

                    return;
                }

                if(type.equals("Leave")) {

                    amount = "-";
                }

                Request request = new Request(
                                name,
                                type,
                                date,
                                amount,
                                reason,
                                "Pending"
                            );

                RequestData.requests.add(request);

                JOptionPane.showMessageDialog( null, "Request Submitted Successfully!"
                );


                txtName.setText("");

                txtDate.setText("");

                txtAmount.setText("");

                txtReason.setText("");

                cmbType.setSelectedIndex(0);

                txtAmount.setEnabled(false);
            }
        });
    }
}