/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.dao.BanAnDAO;
import com.mycompany.quanlydatban.dao.HoaDonDAO;
import com.mycompany.quanlydatban.dao.MonAnDAO;
import com.mycompany.quanlydatban.entity.BanAn;
import com.mycompany.quanlydatban.entity.EnumTrangThaiDatBan;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import com.mycompany.quanlydatban.entity.MonAn;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
@AllArgsConstructor
public class DanhSachBan_GUI extends javax.swing.JPanel {

    /**
     * Creates new form DanhSachBan_GUI
     */
    private JPanel main = new JPanel();
    Color emptyColor = Color.decode("#6699CC");
    Color orderedColor = Color.decode("#67BF7F");
    Color bgColor = Color.decode("#00405d");
    public MainGUI mainGUI;
    private List<BanAn> dsBanAn;
    JDateChooser dateChooser = new JDateChooser();
    java.sql.Date dateDatBan;

    public DanhSachBan_GUI(MainGUI mainGUI, java.sql.Date dateDatBan) {
        initComponents();
        this.dateDatBan=dateDatBan;
        dateChooser.setDate(this.dateDatBan);
        setLayout(new BorderLayout());
        main.setLayout(new GridLayout(0, 5, 10, 10)); // 5 cột, khoảng cách giữa các bàn là 10px
        main.setBackground(Color.WHITE);

        dsBanAn = BanAnDAO.getAllBan();
        
        for(BanAn banan : dsBanAn){
            List<HoaDonThanhToan> dsHoaDon1 = HoaDonDAO.getHoaDonTheoMaBanVaNgay(banan.getMaBan(), this.dateDatBan);
                        
//            nếu quá 6 tiếng thì tự động hủy bàn
            for(HoaDonThanhToan hd:dsHoaDon1){
                LocalDateTime ngayDatBan = hd.getNgayDatBan(); // Lấy ngày đặt bàn
                LocalDateTime ngayHienTai = LocalDateTime.now(); // Lấy ngày hiện tại
                
                if (ngayHienTai.isAfter(ngayDatBan.plusHours(1))) {
                    HoaDonDAO.suaTrangThaiHoaDon(hd.getMaHoaDon(), EnumTrangThaiDatBan.DA_HUY);
                }
            }
        }

        // Tạo và thêm icon cho mỗi bàn vào panel
        for (BanAn banAn : dsBanAn) {
            List<HoaDonThanhToan> dsHoaDon = HoaDonDAO.getHoaDonTheoMaBanNgayTrangThai(banAn.getMaBan(), this.dateDatBan);
            JButton banButton = new JButton("<html><div style='text-align: center;'>"
                    + banAn.getMaBan() + "<br>("
                    + "Số lượng ghế: " + banAn.getSoLuongGhe() + ")</div></html>");
            banButton.setIcon(new ImageIcon("D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\icon\\ban.png"));

            if (dsHoaDon.size() == 0) {
                banButton.setBackground(emptyColor);
            } else {
                banButton.setBackground(orderedColor);
            }
            banButton.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
            banButton.setFocusPainted(false); // Tắt hiệu ứng focus
            banButton.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N

            // Đặt chữ ở dưới icon
            banButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            banButton.setHorizontalTextPosition(SwingConstants.CENTER);

            if (dsHoaDon.size() == 0) {
                banButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainGUI.getJp_root_center().removeAll();
                        mainGUI.getJp_root_center().add(new DatBan_GUI(banAn, null, mainGUI, DanhSachBan_GUI.this, dateDatBan.toLocalDate().atStartOfDay()));
                        mainGUI.getJp_root_center().revalidate();
                        mainGUI.getJp_root_center().repaint();

                    }
                });
            } else {
                 banButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new DanhSachDatBanGUI(banAn, mainGUI, dsHoaDon, DanhSachBan_GUI.this);
                    }
                });
            }

            main.add(banButton);  // Thêm nút bàn vào panel
        }

        JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Chỉ cho phép cuộn dọc

        jLabel1.setBackground(orderedColor);
        jLabel3.setBackground(emptyColor);

//        chọn ngày đặt
        dateChooser.setDateFormatString("dd-MM-yyyy"); // Định dạng ngày tháng năm
        dateChooser.setLocale(new Locale("vi", "VN")); // Thiết lập Locale thành tiếng Việt

        // Lấy JTextField của JDateChooser để căn giữa chữ
        JTextField dateField = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        dateField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ
        dateChooser.setBackground(Color.WHITE);
        dateChooser.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));
        dateChooser.setPreferredSize(new Dimension(180, 35));

        // Lấy nút bên cạnh dateChooser và chỉnh màu
        JButton calendarButton = dateChooser.getCalendarButton();
        calendarButton.setBackground(Color.WHITE); // Đặt màu nền là trắng
        calendarButton.setPreferredSize(new Dimension(35, 35)); // Chỉnh kích thước của nút
        calendarButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));

//        sự kiện thay đổi ngày
        dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                java.sql.Date dateDatBan1 = new java.sql.Date(dateChooser.getDate().getTime());
                mainGUI.jp_root_center.removeAll();
                // Tạo và thêm icon cho mỗi bàn vào panel
                mainGUI.jp_root_center.add(new DanhSachBan_GUI(mainGUI,dateDatBan1));
                mainGUI.jp_root_center.revalidate();
                mainGUI.jp_root_center.repaint();
            }
        });
        jp_ktNgayDat.setLayout(new BorderLayout());
        jp_ktNgayDat.add(dateChooser, BorderLayout.CENTER);

        // Thêm JScrollPane vào frame
        add(scrollPane, BorderLayout.CENTER);
        add(jp_chuThich, BorderLayout.NORTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_chuThich = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jp_ktNgayDat = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));

        jp_chuThich.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("     ");
        jLabel3.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel2.setText("Có lịch đặt");

        jLabel1.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel4.setText("Trống");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("|");

        jp_ktNgayDat.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jp_ktNgayDatLayout = new javax.swing.GroupLayout(jp_ktNgayDat);
        jp_ktNgayDat.setLayout(jp_ktNgayDatLayout);
        jp_ktNgayDatLayout.setHorizontalGroup(
            jp_ktNgayDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        jp_ktNgayDatLayout.setVerticalGroup(
            jp_ktNgayDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        jLabel6.setText("Chọn ngày đặt bàn:");

        javax.swing.GroupLayout jp_chuThichLayout = new javax.swing.GroupLayout(jp_chuThich);
        jp_chuThich.setLayout(jp_chuThichLayout);
        jp_chuThichLayout.setHorizontalGroup(
            jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_chuThichLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_ktNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jp_chuThichLayout.setVerticalGroup(
            jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_chuThichLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_chuThichLayout.createSequentialGroup()
                        .addGroup(jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jp_ktNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_chuThich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_chuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 328, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jp_chuThich;
    private javax.swing.JPanel jp_ktNgayDat;
    // End of variables declaration//GEN-END:variables
}
