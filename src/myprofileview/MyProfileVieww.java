package myprofileview;
import javax.swing.*;
import java.awt.event.*;


public class MyProfileVieww extends JFrame {

    private JLabel lblmyprof, lblname, lbldept, lblpos;

    public MyProfileVieww() {

        setSize(450, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblmyprof = new JLabel("My Profile");
        lblmyprof.setBounds(10, 5, 100, 30);
        add(lblmyprof);

        lblname = new JLabel("My Name:");
        lblname.setBounds(10, 50, 100, 30);
        add(lblname);

        lbldept = new JLabel("My Department:");
        lbldept.setBounds(10, 80, 150, 30);
        add(lbldept);

        lblpos = new JLabel("My Position:");
        lblpos.setBounds(10, 110, 150, 30);
        add(lblpos);
        
        
        
        
        
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 370, 80, 25);
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
    new MyProfileVieww().setVisible(true);
    
    }
}