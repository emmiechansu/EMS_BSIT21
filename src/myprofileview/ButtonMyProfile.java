package myprofileview;

import javax.swing.*;
import java.awt.event.*;

public class ButtonMyProfile extends JFrame {

    private JButton btnprof, btnreqform;
    

    public ButtonMyProfile() {

        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnprof = new JButton("My Profile");
        btnprof.setBounds(10, 10, 200, 50);
        add(btnprof);

        btnreqform = new JButton("Submit Request Form");
        btnreqform.setBounds(10, 75, 200, 50);
        add(btnreqform);
        
        

        btnprof.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        new MyProfileVieww().setVisible(true);
        dispose();
    }
    });
        
        
        btnreqform.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        new SubmitReqForm().setVisible(true);
        dispose();
    }
    });
        
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 520, 80, 25);
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
        new ButtonMyProfile().setVisible(true);
    }
}


    