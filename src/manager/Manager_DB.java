package manager;

import javax.swing.*;
import java.awt.event.*;

public class Manager_DB extends JFrame {

    JList<String> requestList;

    public static DefaultListModel<String> requests =
            new DefaultListModel<>();

    public Manager_DB(){

        setTitle("Manager Dashboard");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setBounds(20,30,120,30);

        JButton employeeBtn = new JButton("Employee List");
        employeeBtn.setBounds(20,70,120,30);

        JButton requestBtn = new JButton("Requests");
        requestBtn.setBounds(20,110,120,30);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(20,150,120,30);

        add(dashboardBtn);
        add(employeeBtn);
        add(requestBtn);
        add(logoutBtn);

        JLabel label = new JLabel("Employee Requests");
        label.setBounds(200,20,200,30);
        add(label);

        JLabel identifierlbl = new JLabel("Name | Type | Status");
        identifierlbl.setBounds(200,50,350,30);
        add(identifierlbl);

        requestList = new JList<>(requests);

        JScrollPane scroll = new JScrollPane(requestList);
        scroll.setBounds(200,80,300,150);
        add(scroll);

        JButton approveBtn = new JButton("Approve");
        approveBtn.setBounds(200,250,100,30);

        approveBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int index = requestList.getSelectedIndex();

                if(index != -1){

                    String updated = requests.get(index)
                            .replace("Pending","Approved");

                    requests.set(index, updated);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Select an employee first.");
                }
            }
        });

        add(approveBtn);

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(320,250,100,30);

        rejectBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int index = requestList.getSelectedIndex();

                if(index != -1){

                    String updated = requests.get(index)
                            .replace("Pending","Rejected");

                    requests.set(index, updated);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Select an employee first.");
                }
            }
        });

        add(rejectBtn);
    }
}