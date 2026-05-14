package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Manager_DB extends JPanel {

    JList<String> requestList;

    String[] requests = {
            "1 - Charles Ynares | Leave | Pending",
            "2 - Matthew Fernandez | Expense | Pending"
    };

    public Manager_DB(){

        setLayout(null);
        setBackground(Color.WHITE);

        JLabel label = new JLabel("Employee Requests");
        label.setBounds(20,20,200,30);
        add(label);

        JLabel identifierlbl = new JLabel("Employee ID | Name | Type | Status");
        identifierlbl.setBounds(20,50,350,30);
        add(identifierlbl);

        requestList = new JList<>(requests);

        JScrollPane scroll = new JScrollPane(requestList);
        scroll.setBounds(20,80,400,150); 
        add(scroll);

        JButton approveBtn = new JButton("Approve");
        approveBtn.setBounds(20,250,100,30);
        add(approveBtn);

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(140,250,100,30);
        add(rejectBtn);

        approveBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int index = requestList.getSelectedIndex();

                if(index != -1){
                    requests[index] = requests[index].replace("Pending","Approved");
                    requestList.setListData(requests);
                }else{
                    JOptionPane.showMessageDialog(null,"Select an employee first.");
                }
            }
        });

        rejectBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int index = requestList.getSelectedIndex();

                if(index != -1){
                    requests[index] = requests[index].replace("Pending","Rejected");
                    requestList.setListData(requests);
                }else{
                    JOptionPane.showMessageDialog(null,"Select an employee first.");
                }
            }
        });
    }
}
