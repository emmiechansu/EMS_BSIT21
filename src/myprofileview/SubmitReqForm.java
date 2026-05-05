package myprofileview;

import javax.swing.*;
import java.awt.event.*;

public class SubmitReqForm extends JFrame {

    private JLabel lblTitle, lblType, lblDate, lblAmount, lblReason;
    private JComboBox<String> cmbType;
    private JTextField txtDate, txtAmount, txtReason;
    private JButton btnSubmit;

    public SubmitReqForm() {

        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        lblTitle = new JLabel("Submit Request");
        lblTitle.setBounds(120, 10, 200, 30);
        add(lblTitle);

       
        lblType = new JLabel("Type:");
        lblType.setBounds(30, 50, 100, 25);
        add(lblType);

        String[] types = {"Leave", "Expense"};
        cmbType = new JComboBox<>(types);
        cmbType.setBounds(120, 50, 150, 25);
        add(cmbType);

    
        lblDate = new JLabel("Date:");
        lblDate.setBounds(30, 90, 100, 25);
        add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(120, 90, 150, 25);
        add(txtDate);

   
        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 130, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(120, 130, 150, 25);
        add(txtAmount);

     
        lblReason = new JLabel("Reason:");
        lblReason.setBounds(30, 170, 100, 25);
        add(lblReason);

        txtReason = new JTextField();
        txtReason.setBounds(120, 170, 150, 25);
        add(txtReason);

       
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(140, 220, 100, 30);
        add(btnSubmit);

     
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = cmbType.getSelectedItem().toString();
                String date = txtDate.getText();
                String amount = txtAmount.getText();
                String reason = txtReason.getText();

                JOptionPane.showMessageDialog(null,
                        "Submitted:\nType: " + type +
                        "\nDate: " + date +
                        "\nAmount: " + amount +
                        "\nReason: " + reason);
            }
        });

      
        cmbType.addActionListener(e -> {
            if (cmbType.getSelectedItem().equals("Expense")) {
                txtAmount.setEnabled(true);
            } else {
                txtAmount.setEnabled(false);
                txtAmount.setText("");
            }
        });

        txtAmount.setEnabled(false);
        
        
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 80, 25);
        add(btnBack);

        btnBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        new ButtonMyProfile().setVisible(true);
        dispose();
    }
    });
        
        
        
        
    }
    
   
    public static void main(String[] args) {
       new SubmitReqForm().setVisible(true);
    }
}