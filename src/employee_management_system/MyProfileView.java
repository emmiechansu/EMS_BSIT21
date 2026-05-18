package employee_management_system;

import javax.swing.*;

public class MyProfileView extends JPanel {

    private JLabel lblmyprof, lblname, lbldept, lblpos;

    public MyProfileView(String username) {

        setLayout(null);
        setSize(680, 500);

        lblmyprof = new JLabel("My Profile");
        lblmyprof.setBounds(20, 20, 200, 30);
        add(lblmyprof);

        lblname = new JLabel("My Name: " + username);
        lblname.setBounds(20, 70, 300, 30);
        add(lblname);

        lbldept = new JLabel("My Department: IT Department");
        lbldept.setBounds(20, 110, 300, 30);
        add(lbldept);

        lblpos = new JLabel("My Position: Employee");
        lblpos.setBounds(20, 150, 300, 30);
        add(lblpos);
    }
}
