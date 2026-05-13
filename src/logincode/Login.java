package logincode;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    private JLabel lblLogin, lblUser, lblPass, lblRole;
    private JPasswordField psPass;
    private JTextField txtUser;
    private JButton btnLog;
    private JComboBox<String> roleBox;

    public Login(){
        setTitle("Login Frame");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblLogin = new JLabel("LOGIN FRAME", SwingConstants.CENTER);
        lblLogin.setBounds(150, 40, 250, 20);
        add(lblLogin);

        lblRole = new JLabel("Role:");
        lblRole.setBounds(150, 80, 100, 25);
        add(lblRole);

        String[] roles = {"HR", "Manager", "Employee"};
        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(220, 80, 150, 25);
        add(roleBox);

        lblUser = new JLabel("Username:");
        lblUser.setBounds(150, 120, 100, 30);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(220, 120, 150, 30);
        add(txtUser);

        lblPass = new JLabel("Password:");
        lblPass.setBounds(150, 165, 100, 30);
        add(lblPass);

        psPass = new JPasswordField();
        psPass.setBounds(220, 165, 150, 30);
        add(psPass);

        btnLog = new JButton("Submit");
        btnLog.setBounds(220, 215, 100, 30);
        add(btnLog);

        btnLog.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLog) {
            String username = txtUser.getText();
            String password = new String(psPass.getPassword());
            String role = (String) roleBox.getSelectedItem();
            //HR Login 
            if (username.equals("HR") && password.equals("12345") && role.equals("HR")) {
                new MainDashboard(role).setVisible(true);
                dispose();
            }
            //Manager Login 
            else if (username.equals("Manager") && password.equals("12345") && role.equals("Manager")) {
                new MainDashboard(role).setVisible(true);
                dispose();
            }
            //Employee Login
            else if (username.equals("Employee") && password.equals("12345") && role.equals("Employee")) {
                new EmployeeDashboard(username, role).setVisible(true);
                dispose();
            }
            //Invalid Login
            else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        }
    }
}
