package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class DashboardUI extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fld_f_customer_name;
    private JComboBox cmb_customer_type;
    private JButton btn_customer_filter;
    private JButton btn_customer_new;
    private JButton btn_customer_filter_reset;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private User user;
    private  CustomerController customerController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private JPopupMenu popup_customer = new JPopupMenu();
    public DashboardUI(User user){
       this.user = user;
       this.customerController = new CustomerController();
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
        loadCustomerTable(null);
        loadCustomerPopupMenu();
    }
    private void loadCustomerPopupMenu(){
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        this.popup_customer.add("Güncelle").addActionListener(e -> {
                int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0).toString());
            System.out.println(selectId);

        });
        this.popup_customer.add("Sil").addActionListener(e -> {
            System.out.println("Sil Tıklandı");
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);
    }
    private  void loadCustomerTable(ArrayList<Customer> customers){
        Object[] columnCustomer = new Object[]{"ID","Müşteri Adı","Tipi","Telefon","E-posta","Adres"};

        if(customers == null){
            customers = this.customerController.findAll();
        }
        //Tablo sıfırlama -- Böylelikle arama yaptığımızda aynı tablo sürekli alt alta gelmeyecek.
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);
        this.tmdl_customer.setColumnIdentifiers(columnCustomer);
        for (Customer customer : customers){
            Object[] rowObject =
                    {customer.getId(),
                    customer.getName(),
                    customer.getType(),
                    customer.getPhone(),
                    customer.getMail(),
                    customer.getAddress()
            };
            this.tmdl_customer.addRow(rowObject);
        }

        this.tbl_customer.setModel(tmdl_customer);
        this.tbl_customer.getTableHeader().setReorderingAllowed(false);
        this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_customer.setEnabled(false);
    }
}
