package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Login extends JFrame implements ActionListener {

    private JLabel lblTitle, lblSubtitle, lblUser, lblPass, lblRole, lblFooter;
    private JTextField txtUser;
    private JPasswordField psPass;
    private JButton btnLog;
    private JComboBox<String> roleBox;
    private JPanel loginPanel;

    public Login() {

        setTitle("Employee Management System");
        setSize(1200, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(15, 23, 42));

        try {

            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/logo.png"));

            Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            JLabel logoLabel = new JLabel(new ImageIcon(img));
            logoLabel.setBounds(540, 20, 120, 120);

            add(logoLabel);

        } catch (Exception e) {

            JLabel logoText = new JLabel("EMS", SwingConstants.CENTER);

            logoText.setFont(new Font("Segoe UI", Font.BOLD, 40));

            logoText.setForeground(Color.WHITE);

            logoText.setBounds(500, 20, 200, 120);

            add(logoText);
        }

        lblTitle = new JLabel("EMPLOYEE MANAGEMENT SYSTEM", SwingConstants.CENTER);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setBounds(250, 150, 700, 40);

        add(lblTitle);

        lblSubtitle = new JLabel("Portal Login", SwingConstants.CENTER);

        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        lblSubtitle.setForeground(Color.LIGHT_GRAY);

        lblSubtitle.setBounds(350, 190, 500, 25);

        add(lblSubtitle);

        loginPanel = new JPanel();

        loginPanel.setLayout(null);

        loginPanel.setBounds(400, 240, 400, 280);

        loginPanel.setBackground(Color.WHITE);

        loginPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        add(loginPanel);

        lblRole = new JLabel("Role:");

        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblRole.setBounds(40, 40, 100, 25);

        String[] roles = {"HR", "Manager", "Employee"};

        roleBox = new JComboBox<>(roles);

        roleBox.setBounds(150, 40, 200, 35);

        lblUser = new JLabel("Username:");

        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblUser.setBounds(40, 90, 100, 25);

        txtUser = new JTextField();

        txtUser.setBounds(150, 90, 200, 35);

        lblPass = new JLabel("Password:");

        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblPass.setBounds(40, 140, 100, 25);

        psPass = new JPasswordField();

        psPass.setBounds(150, 140, 200, 35);

        btnLog = new JButton("LOGIN");

        btnLog.setBounds(125, 210, 150, 40);

        btnLog.setBackground(new Color(37, 99, 235));

        btnLog.setForeground(Color.WHITE);

        btnLog.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnLog.setFocusPainted(false);

        btnLog.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLog.addActionListener(this);

        loginPanel.add(lblRole);
        loginPanel.add(roleBox);

        loginPanel.add(lblUser);
        loginPanel.add(txtUser);

        loginPanel.add(lblPass);
        loginPanel.add(psPass);

        loginPanel.add(btnLog);

        lblFooter = new JLabel("© 2026 Employee Management System", SwingConstants.CENTER);

        lblFooter.setForeground(Color.WHITE);

        lblFooter.setBounds(350, 600, 500, 20);

        add(lblFooter);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLog) {

            loginUser();
        }
    }

    private void loginUser() {

        String username = txtUser.getText();

        String password = String.valueOf(psPass.getPassword());

        String role = roleBox.getSelectedItem().toString();

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_management", "root", "");

            if (role.equals("Employee")) {

                PreparedStatement st = con.prepareStatement("SELECT e.employee_id, e.full_name, e.position, e.department, e.contact_no, e.performance, e.email, e.address, e.status, ec.first_login FROM employees e INNER JOIN employee_credentials ec ON e.employee_id = ec.employee_id WHERE ec.username=? AND ec.password=?");

                st.setString(1, username);
                st.setString(2, password);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {

                    if (rs.getInt("first_login") == 1) {

                        new ChangePasswordFrame(rs.getInt("employee_id")).setVisible(true);

                        dispose();

                        return;
                    }

                    UserSession.employeeID = String.valueOf(rs.getInt("employee_id"));

                    UserSession.name = rs.getString("full_name");

                    UserSession.position = rs.getString("position");

                    UserSession.department = rs.getString("department");

                    UserSession.contact = rs.getString("contact_no");

                    UserSession.email = rs.getString("email");

                    UserSession.address = rs.getString("address");

                    UserSession.status = rs.getString("status");

                    UserSession.role = "Employee";

                    new EmployeeDashboard(username, role).setVisible(true);

                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Invalid Employee Credentials!");
                }

                rs.close();
                st.close();
            } else {

                PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=? AND role=?");

                st.setString(1, username);
                st.setString(2, password);
                st.setString(3, role);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {

                    UserSession.userId = rs.getInt("user_id");

                    UserSession.username = rs.getString("username");

                    UserSession.role = rs.getString("role");

                    if (role.equals("HR")) {

                        new HRMainDashboard(role).setVisible(true);
                    } else if (role.equals("Manager")) {

                        new ManagerMainDashboard(role).setVisible(true);
                    }

                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Invalid Credentials!");
                }

                rs.close();
                st.close();
            }

            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Database Connection Error!\n" + ex.getMessage());
        }
    }
}