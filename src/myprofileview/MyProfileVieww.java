package myprofileview;
import javax.swing.*;

public class MyProfileVieww extends JPanel {
private JLabel lblmyprof, lblname, lbldept, lblpos;
    
    public MyProfileVieww(String username) {

        setLayout(null);
        setSize(680, 500);

        lblmyprof = new JLabel("Profile");
        lblmyprof.setBounds(20, 20, 200, 30);
        add(lblmyprof);

        lblname = new JLabel("Name: " + username);
        lblname.setBounds(20, 70, 300, 30);
        add(lblname);

        lbldept = new JLabel("Department: IT Department");
        lbldept.setBounds(20, 110, 300, 30);
        add(lbldept);

        lblpos = new JLabel("Position: Employee");
        lblpos.setBounds(20, 150, 300, 30);
        add(lblpos);
    }
}