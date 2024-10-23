/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ACER
 */
public class DanhSachBan_GUI extends javax.swing.JPanel {

    /**
     * Creates new form DanhSachBan_GUI
     */
    private int[] SoLuongBan = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12,13,14,15,16}; // Ví dụ số lượng bàn
    private JPanel main = new JPanel();
    Color orderedColor = Color.decode("#6699CC");
    Color emptyColor = Color.decode("#67BF7F");
    Color bgColor = Color.decode("#00405d");


    public DanhSachBan_GUI() {
        initComponents();
        setLayout(new BorderLayout());
        main.setLayout(new GridLayout(0, 5, 10, 10)); // 5 cột, khoảng cách giữa các bàn là 10px
        main.setBackground(Color.WHITE);
        // Tạo và thêm icon cho mỗi bàn vào panel
        for (int i = 0; i < SoLuongBan.length; i++) {
            JButton banButton = new JButton("Bàn " + SoLuongBan[i]);
            banButton.setIcon(new ImageIcon("D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\icon\\ban.png"));

            banButton.setBackground(orderedColor);
            banButton.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
            banButton.setFocusPainted(false); // Tắt hiệu ứng focus
            
            // Đặt chữ ở dưới icon
            banButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            banButton.setHorizontalTextPosition(SwingConstants.CENTER);
    
            main.add(banButton);  // Thêm nút bàn vào panel
        }
        
//      tìm kiếm
        btn_TimKiem.setBackground(bgColor);
        PlaceholderSupport placeholderSupport = new PlaceholderSupport();
        placeholderSupport.addPlaceholder(txtField_TimKiem," Nhập mã bàn để tìm kiếm");
        
        
        JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Chỉ cho phép cuộn dọc
        
        jLabel1.setBackground(orderedColor);
        jLabel3.setBackground(emptyColor);
        
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
        txtField_TimKiem = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));

        jp_chuThich.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("     ");
        jLabel3.setOpaque(true);

        jLabel2.setText(": Đã đặt");

        jLabel1.setOpaque(true);

        jLabel4.setText(": Trống");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("|");

        txtField_TimKiem.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtField_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField_TimKiemActionPerformed(evt);
            }
        });

        btn_TimKiem.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 14)); // NOI18N
        btn_TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

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
                .addGap(32, 32, 32)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(txtField_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_TimKiem)
                .addGap(11, 11, 11))
        );
        jp_chuThichLayout.setVerticalGroup(
            jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_chuThichLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtField_TimKiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_chuThichLayout.createSequentialGroup()
                        .addGroup(jp_chuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_chuThich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_chuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 328, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtField_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtField_TimKiemActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jp_chuThich;
    private javax.swing.JTextField txtField_TimKiem;
    // End of variables declaration//GEN-END:variables
}