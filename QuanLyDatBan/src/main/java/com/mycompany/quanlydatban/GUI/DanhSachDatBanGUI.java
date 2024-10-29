/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ACER
 */
public class DanhSachDatBanGUI extends JDialog {

    /**
     * Creates new form DanhSachDatBanGUI
     */
    MainGUI mainGUI;
    DefaultTableModel model_dsDatBan;
    JTable table_dsDatBan;
    Color color_bg = Color.decode("#00405d");
    List<HoaDonThanhToan> dsHoaDon;
    DanhSachBan_GUI danhSachBan_GUI;
    
    public DanhSachDatBanGUI(MainGUI mainGUI, List<HoaDonThanhToan> dsHoaDon, DanhSachBan_GUI danhSachBan_GUI) {
        super(mainGUI, true);
        this.dsHoaDon=dsHoaDon;
        this.danhSachBan_GUI=danhSachBan_GUI;
        initComponents();
        this.mainGUI = mainGUI;
        setSize(new Dimension(600, 300));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedTime1 = dsHoaDon.get(0).getNgayDatBan().format(formatter1);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình hoặc giữa cửa sổ cha
        label_title.setText("Danh sách đặt bàn cho " + dsHoaDon.get(0).getBanAn().getMaBan() + " - " + formattedTime1);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Chỉ đóng JDialog khi nhấn nút tắt

        jp_dsDatBan.setLayout(new BorderLayout());
        String columnName[] = {"Khách hàng","Số điện thoại", "Giờ đặt"};
        model_dsDatBan = new DefaultTableModel(columnName, 0);
        table_dsDatBan = new JTable(model_dsDatBan);
        table_dsDatBan.setBackground(new Color(255, 255, 255));
        table_dsDatBan.setRowHeight(30); // Thiết lập chiều cao mỗi dòng là 30 pixel
        table_dsDatBan.setFont(new java.awt.Font("JetBrains Mono", 0, 15));
        JScrollPane jScrollPane_dsMonAn = new JScrollPane(table_dsDatBan);
        table_dsDatBan.setOpaque(true);
        // Lấy header của bảng và cài đặt font cho header
        JTableHeader tableHeader = table_dsDatBan.getTableHeader();
        tableHeader.setFont(new Font("JetBrains Mono", Font.BOLD, 15));
        tableHeader.setBackground(color_bg);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30)); // Đặt chiều cao của header là 40 pixel
        for (HoaDonThanhToan hd : dsHoaDon) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = hd.getNgayDatBan().format(formatter);
            model_dsDatBan.addRow(new Object[]{
                hd.getKhachHang().getTenKhachHang(),
                hd.getKhachHang().getSoDienThoai(),
                formattedTime
            });
        }
//        sự kiện khi nhấn dòng trên bảng
        table_dsDatBan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table_dsDatBan.getSelectedRow();
                if(row != -1){
                    // Lấy hóa đơn tương ứng từ danh sách dựa trên chỉ số hàng
                    HoaDonThanhToan selectedHoaDon = dsHoaDon.get(row);
                    dispose();
                    mainGUI.requestFocusInWindow();
                    mainGUI.jp_root_center.removeAll();
                    mainGUI.jp_root_center.add(new DatBan_GUI(selectedHoaDon.getBanAn(), selectedHoaDon, mainGUI, danhSachBan_GUI));
                    mainGUI.jp_root_center.revalidate();
                    mainGUI.jp_root_center.repaint();
                }
            }
        });
            

        jp_dsDatBan.add(jScrollPane_dsMonAn, BorderLayout.CENTER);
        
        btn_DatBanMoi.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
        btn_DatBanMoi.setFocusPainted(false); // Tắt hiệu ứng focus
        btn_DatBanMoi.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N
        btn_DatBanMoi.setPreferredSize(new Dimension(130, 35)); // Chiều rộng 100, chiều cao 40
        btn_DatBanMoi.setBackground(color_bg);
        
        btn_thoat.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
        btn_thoat.setFocusPainted(false); // Tắt hiệu ứng focus
        btn_thoat.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N
        btn_thoat.setPreferredSize(new Dimension(130, 35)); // Chiều rộng 100, chiều cao 40
        btn_thoat.setBackground(color_bg);
        
        setLayout(new BorderLayout());
        jp_title.add(label_title);
        add(jp_title, BorderLayout.NORTH);
        add(jp_dsDatBan, BorderLayout.CENTER);
        add(jp_button, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jp_dsDatBan = new javax.swing.JPanel();
        jp_title = new javax.swing.JPanel();
        label_title = new javax.swing.JLabel();
        jp_button = new javax.swing.JPanel();
        btn_DatBanMoi = new javax.swing.JButton();
        btn_thoat = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_dsDatBan.setBackground(new java.awt.Color(255, 255, 255));
        jp_dsDatBan.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jp_dsDatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 530, 160));

        jp_title.setBackground(new java.awt.Color(255, 255, 255));

        label_title.setBackground(new java.awt.Color(255, 255, 255));
        label_title.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setOpaque(true);
        jp_title.add(label_title);

        getContentPane().add(jp_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 40));

        jp_button.setBackground(new java.awt.Color(255, 255, 255));

        btn_DatBanMoi.setForeground(new java.awt.Color(255, 255, 255));
        btn_DatBanMoi.setText("Đặt bàn mới");
        jp_button.add(btn_DatBanMoi);

        btn_thoat.setForeground(new java.awt.Color(255, 255, 255));
        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });
        jp_button.add(btn_thoat);

        getContentPane().add(jp_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 530, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        mainGUI.requestFocusInWindow();
        mainGUI.jp_root_center.removeAll();
//        mainGUI.jp_root_center.add();
        mainGUI.jp_root_center.revalidate();
        mainGUI.jp_root_center.repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_thoatActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DatBanMoi;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jp_button;
    private javax.swing.JPanel jp_dsDatBan;
    private javax.swing.JPanel jp_title;
    private javax.swing.JLabel label_title;
    // End of variables declaration//GEN-END:variables
}
