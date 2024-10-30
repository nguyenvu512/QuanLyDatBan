/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.dao.KhuyenMai_Dao;
import com.mycompany.quanlydatban.entity.KhuyenMai;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Vo Van Tuong
 */
public class KhuyenMai_GUI extends javax.swing.JPanel {

    /**
     * Creates new form KhuyenMai_GUI
     */
     Color color_bg = Color.decode("#00405d");
    private final JDateChooser dateChooser;
    private final JDateChooser datechooserEnd;
    private KhuyenMai_Dao km_d;
    public KhuyenMai_GUI() {
        initComponents();
        setLayout(new BorderLayout());
        add(jp_TT,BorderLayout.NORTH);
        add(jp_ThongTin,BorderLayout.CENTER);
        add(jp_Table,BorderLayout.SOUTH);
        jb_ThemKM.setBackground(color_bg);
        jb_SuaKM.setBackground(color_bg);
        
        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
//        chọn ngày đặt
        dateChooser.setDateFormatString("dd-MM-yyyy"); // Định dạng ngày tháng năm
        dateChooser.setLocale(new Locale("vi", "VN")); // Thiết lập Locale thành tiếng Việt

        // Lấy JTextField của JDateChooser để căn giữa chữ
        JTextField dateField = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        dateField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ
        dateChooser.setBackground(Color.WHITE);
        dateChooser.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));
        dateChooser.setPreferredSize(new Dimension(180, 25));

        // Lấy nút bên cạnh dateChooser và chỉnh màu
        JButton calendarButton = dateChooser.getCalendarButton();
        calendarButton.setBackground(Color.WHITE); // Đặt màu nền là trắng
        calendarButton.setPreferredSize(new Dimension(25, 25)); // Chỉnh kích thước của nút
        calendarButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jp_NgayBatDau.setLayout(new BorderLayout());
        jp_NgayBatDau.add(dateChooser,BorderLayout.CENTER);
        
         datechooserEnd = new JDateChooser();
        datechooserEnd.setDate(new Date());
        
        datechooserEnd.setDateFormatString("dd-MM-yyyy");
        datechooserEnd.setLocale(new Locale("vi","VN"));
        
        JTextField jtext= ((JTextField) datechooserEnd.getDateEditor().getUiComponent());
        jtext.setHorizontalAlignment(jtext.CENTER);
        datechooserEnd.setBackground(Color.WHITE);
        datechooserEnd.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));
        datechooserEnd.setPreferredSize(new Dimension(180, 25));
      
        // Lấy nút bên cạnh dateChooser và chỉnh màu
        JButton calendarButton1 = datechooserEnd.getCalendarButton();
        calendarButton1.setBackground(Color.WHITE); // Đặt màu nền là trắng
        calendarButton1.setPreferredSize(new Dimension(25, 25)); // Chỉnh kích thước của nút
        calendarButton1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jp_NgayKetThuc.setLayout(new BorderLayout());
        jp_NgayKetThuc.add(datechooserEnd,BorderLayout.CENTER);
        dtm = (DefaultTableModel) Jtable.getModel();
        datatoTable();
        
    
        
    }
    
    public void  datatoTable(){
         KhuyenMai_Dao km_d = new KhuyenMai_Dao();
        List<KhuyenMai> list = km_d.getAllKhuyenMai();
        for(KhuyenMai km: list)
        {
            dtm.addRow(new Object[]{
                km.getMaKhuyenMai(),
                km.getTenKhuyenMai(),
                datetimeFormatter(km.getNgayBatDau()).toString(),
                datetimeFormatter(  km.getNgayKetThuc()).toString(),
                km.getGiamGia()
            });
        }
    }
    
   public String datetimeFormatter(LocalDate date) {
    String current = dtf.format(date);
    return current;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_TT = new javax.swing.JPanel();
        jlb_TT = new javax.swing.JLabel();
        jp_ThongTin = new javax.swing.JPanel();
        jlb_MaKM = new javax.swing.JLabel();
        jt_MaKM = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jt_tenKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jt_MucKM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jb_ThemKM = new javax.swing.JButton();
        jb_SuaKM = new javax.swing.JButton();
        jp_NgayBatDau = new javax.swing.JPanel();
        jp_NgayKetThuc = new javax.swing.JPanel();
        jp_Table = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_TT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlb_TT.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlb_TT.setText("QUẢN LÝ KHUYẾN MÃI");

        javax.swing.GroupLayout jp_TTLayout = new javax.swing.GroupLayout(jp_TT);
        jp_TT.setLayout(jp_TTLayout);
        jp_TTLayout.setHorizontalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TTLayout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(jlb_TT)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jp_TTLayout.setVerticalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlb_TT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jp_TT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 40));

        jp_ThongTin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlb_MaKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlb_MaKM.setText("Mã Khuyến Mãi         :");

        jt_MaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_MaKMActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên Khuyến Mãi        :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mức Khuyến Mãi (%) :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày Bắt Đầu  :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Kết Thúc :");

        jb_ThemKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jb_ThemKM.setForeground(new java.awt.Color(255, 255, 255));
        jb_ThemKM.setText("Thêm");
        jb_ThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ThemKMActionPerformed(evt);
            }
        });

        jb_SuaKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jb_SuaKM.setForeground(new java.awt.Color(255, 255, 255));
        jb_SuaKM.setText("Cập Nhật");
        jb_SuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_SuaKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_NgayBatDauLayout = new javax.swing.GroupLayout(jp_NgayBatDau);
        jp_NgayBatDau.setLayout(jp_NgayBatDauLayout);
        jp_NgayBatDauLayout.setHorizontalGroup(
            jp_NgayBatDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        jp_NgayBatDauLayout.setVerticalGroup(
            jp_NgayBatDauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_NgayKetThucLayout = new javax.swing.GroupLayout(jp_NgayKetThuc);
        jp_NgayKetThuc.setLayout(jp_NgayKetThucLayout);
        jp_NgayKetThucLayout.setHorizontalGroup(
            jp_NgayKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jp_NgayKetThucLayout.setVerticalGroup(
            jp_NgayKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_ThongTinLayout = new javax.swing.GroupLayout(jp_ThongTin);
        jp_ThongTin.setLayout(jp_ThongTinLayout);
        jp_ThongTinLayout.setHorizontalGroup(
            jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ThongTinLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlb_MaKM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ThongTinLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jb_ThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(jb_SuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(398, Short.MAX_VALUE))
                    .addGroup(jp_ThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jt_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_MucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_tenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jp_NgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jp_NgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(76, 76, 76))))
        );
        jp_ThongTinLayout.setVerticalGroup(
            jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlb_MaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jt_MaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jp_NgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_NgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jt_tenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jt_MucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_ThemKM)
                    .addComponent(jb_SuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        add(jp_ThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1150, 170));

        Jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Mức Khuyến Mãi (%)"
            }
        ));
        Jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Jtable);

        javax.swing.GroupLayout jp_TableLayout = new javax.swing.GroupLayout(jp_Table);
        jp_Table.setLayout(jp_TableLayout);
        jp_TableLayout.setHorizontalGroup(
            jp_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        jp_TableLayout.setVerticalGroup(
            jp_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        add(jp_Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1150, 510));
    }// </editor-fold>//GEN-END:initComponents

    private void jt_MaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_MaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MaKMActionPerformed

    private void jb_ThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ThemKMActionPerformed
        // TODO add your handling code here:
        String maKM = jt_MaKM.getText();
        String tenKM =jt_tenKM.getText();
        String mucKm= jt_MucKM.getText();
        Double mucKM = Double.valueOf(mucKm);
        LocalDate nBD = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate nKT = datechooserEnd.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
         KhuyenMai KM = new KhuyenMai(maKM, tenKM, nBD, nKT, mucKM);
         km_d = new KhuyenMai_Dao();
         if(km_d.them(KM))
         {
             dtm.setRowCount(0);
             datatoTable();
         }
    }//GEN-LAST:event_jb_ThemKMActionPerformed

    private void jb_SuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_SuaKMActionPerformed
        // TODO add your handling code here:
        String maKm = jt_MaKM.getText();
        KhuyenMai_Dao km_d = new KhuyenMai_Dao();
        KhuyenMai KM = km_d.tim(maKm);
        if(KM!= null)
        {
            String tenKM = jt_tenKM.getText();
            LocalDate NBD = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDate NKT = datechooserEnd.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            Double mucGiam = Double.valueOf(jt_MucKM.getText());
            
            KM.setTenKhuyenMai(tenKM);
            KM.setNgayBatDau(NBD);
            KM.setNgayKetThuc(NKT);
            KM.setGiamGia(mucGiam);
            
            if(km_d.suaKM(KM))
            {
                dtm.setRowCount(0);
                datatoTable();
            }
        }
        
    }//GEN-LAST:event_jb_SuaKMActionPerformed

    private void JtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableMouseClicked
        // TODO add your handling code here:
        int n = Jtable.getSelectedRow();
        if(n!=-1 && evt.getClickCount()== 1)
        {
            jt_MaKM.setEditable(false);
            jt_MaKM.setText(dtm.getValueAt(n, 0).toString());
            jt_tenKM.setText(dtm.getValueAt(n,1 ).toString());
            String NBT = dtm.getValueAt(n, 2).toString();
            LocalDate localdateNBD = LocalDate.parse(NBT, dtf);
            Date date = java.sql.Date.valueOf(localdateNBD);
            dateChooser.setDate(date);
            
            String NKT = dtm.getValueAt(n, 3).toString();
            LocalDate localdateNKT = LocalDate.parse(NKT, dtf);
            Date ngay = java.sql.Date.valueOf(localdateNKT);
            datechooserEnd.setDate(ngay);
            
            jt_MucKM.setText(dtm.getValueAt(n, 4).toString());
        }
        else
        {
            Jtable.clearSelection();
            jt_MaKM.setEditable(true);
            xoaTrang();
        }
    }//GEN-LAST:event_JtableMouseClicked

    public void xoaTrang(){
        jt_MaKM.setText("");
        jt_tenKM.setText("");
        dateChooser.setDate(new Date());
        datechooserEnd.setDate(new Date());
        jt_MucKM.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_SuaKM;
    private javax.swing.JButton jb_ThemKM;
    private javax.swing.JLabel jlb_MaKM;
    private javax.swing.JLabel jlb_TT;
    private javax.swing.JPanel jp_NgayBatDau;
    private javax.swing.JPanel jp_NgayKetThuc;
    private javax.swing.JPanel jp_TT;
    private javax.swing.JPanel jp_Table;
    private javax.swing.JPanel jp_ThongTin;
    private javax.swing.JTextField jt_MaKM;
    private javax.swing.JTextField jt_MucKM;
    private javax.swing.JTextField jt_tenKM;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel dtm;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}