
package employee_management_system;

import java.awt.event.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener{
    private JLabel lblLogin,lblUser,lblPass,lblRole;
    private JPasswordField psPass;
    private JTextField txtUser;
    private JButton btnLog;
    private JComboBox<String> roleBox;
    
    public Login(){
        setTitle("Login Frame");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblLogin = new JLabel("LOGIN FRAME", SwingConstants.CENTER);
        lblLogin.setBounds(150, 40, 250, 10);
        add(lblLogin);
        
        txtUser = new JTextField();
        txtUser.setBounds(220, 120, 150, 30);
        add(txtUser);
        
        lblUser = new JLabel("Username:");
        lblUser.setBounds(150, 120, 100, 30);
        add(lblUser);
        
        psPass = new JPasswordField();
        psPass.setBounds(220, 145, 150, 30);
        add(psPass);
        
        lblPass = new JLabel("Password:");
        lblPass.setBounds(150, 145, 100, 30);
        add(lblPass);
        
        lblRole = new JLabel("Role:", SwingConstants.CENTER);
        lblRole.setBounds(150, 60, 250, 20);
        add(lblRole);
        
        String[] roles = {"HR", "Manager", "Employee"};
        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(150, 80, 250, 25);
        add(roleBox);
        
        btnLog = new JButton("Submit");
        btnLog.setBounds(220, 185, 100, 30);
        add(btnLog);
        
        btnLog.addActionListener(this);
                
        

    }

    @Override
public void actionPerformed(ActionEvent e){
    if (e.getSource() == btnLog) {

        String username = txtUser.getText();
        String password = new String(psPass.getPassword());
        String role = (String) roleBox.getSelectedItem();

        
        if (username.equals("HR") && password.equals("12345") && role.equals("HR")) {
            new MainDashboard(role).setVisible(true);
            dispose();
        }

        
        else if (username.equals("Manager") && password.equals("12345") && role.equals("Manager")) {
            new MainDashboard(role).setVisible(true);
            dispose();
        }

       
        else if (username.equals("Employee") && password.equals("12345") && role.equals("Employee")) {
            new EmployeeDashboard (role).setVisible(true);
            dispose();
        }

        
        else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }

   }
}