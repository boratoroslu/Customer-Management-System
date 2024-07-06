package view;

import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JTextField textField1;
    private User user;
    public DashboardUI(User user){
       this.user = user;
       if (user == null){
           Helper.showMsg("error");
           dispose();
       }
       this.add(container);
       this.setTitle("Müşteri Yönetim Sistemi");
       this.setSize(1000,500);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);
        this.lbl_welcome.setText("Hoşgeldiniz :  "+this.user.getName());
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               LoginUI loginUI = new LoginUI();
            }
        });
    }
}
