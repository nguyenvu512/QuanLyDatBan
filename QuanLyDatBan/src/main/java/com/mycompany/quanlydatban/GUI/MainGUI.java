/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ACER
 */
@Getter
@Setter
public class MainGUI extends javax.swing.JFrame {

    private int currentIndex;
    Color color_bg = Color.decode("#00405d");
    Color selectedColor = Color.decode("#3f729b");

    public MainGUI() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLayout(new BorderLayout());
        add(jp_root, BorderLayout.CENTER);
        add(jp_menu, BorderLayout.WEST);

        labelTenNV.setText("<html><center>Xin chào, Võ Huy Tưởng<br><font size='4'>Nhân viên</font></center></html>");
        labelTenNV.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung trong label

        jp_root.setLayout(new BorderLayout());
        jp_root.add(jp_title, BorderLayout.NORTH);
        jp_root.add(jp_root_center, BorderLayout.CENTER);
        jp_root_center.setLayout(new BorderLayout());
        jp_root_center.add(btn_main_img, BorderLayout.CENTER);
        Dimension size = jp_root_center.getSize(); // Lấy kích thước của jp_panel1
        System.out.println(size.height);

        jp_title.setBackground(color_bg);

        btn_main_img.setFocusPainted(false); // Tắt hiệu ứng khi nút được focus
        btn_main_img.setBorderPainted(false); // Tắt viền khi hover
        btn_main_img.setContentAreaFilled(false); // Tắt nền khi hover        

        menuItem_BanAn.setBackground(color_bg);
        menuItem_TaiKhoan.setBackground(color_bg);
        menuItem_DatBan.setBackground(color_bg);
        menuItem_HoaDon.setBackground(color_bg);
        menuItem_MonAn.setBackground(color_bg);
        menuItem_KhachHang.setBackground(color_bg);
        menuItem_NhanVien.setBackground(color_bg);
        menuItem_ThongKe.setBackground(color_bg);


        menuItem_BanAn.setBorder(new EmptyBorder(0, 80, 0, 0)); // Tạo margin với biên trái
        menuItem_TaiKhoan.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_DatBan.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_HoaDon.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_MonAn.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_KhachHang.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_NhanVien.setBorder(new EmptyBorder(0, 80, 0, 0));
        menuItem_ThongKe.setBorder(new EmptyBorder(0, 80, 0, 0));


        // Gọi hàm để gán sự kiện cho từng nút
        addButtonListeners(menuItem_BanAn);
        addButtonListeners(menuItem_DatBan);
        addButtonListeners(menuItem_HoaDon);
        addButtonListeners(menuItem_MonAn);
        addButtonListeners(menuItem_KhachHang);
        addButtonListeners(menuItem_TaiKhoan);
        addButtonListeners(menuItem_NhanVien);
        addButtonListeners(menuItem_ThongKe);


        // Lấy kích thước màn hình hiện tại
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Thiết lập chiều rộng của jp_menu bằng 1/5 chiều rộng màn hình
        jp_menu.setPreferredSize(new Dimension(screenWidth / 5, screenHeight));
        jp_menu.setBackground(color_bg);

        jp_root.setBackground(new Color(200, 200, 200));  // Màu nền xám nhạt
        jp_root.setPreferredSize(new Dimension((screenWidth * 4) / 5, screenHeight - 150));

        // Các thuộc tính tắt viền của Icon và focus
        logo.setContentAreaFilled(false);  // Tắt màu nền của nút
        logo.setFocusPainted(false);  // Tắt viền khi nút được focus
        logo.setOpaque(false);  // Đảm bảo nút trong suốt
        logo.setBorderPainted(true);  // Hiển thị viền của JButton, nhưng không có viền của Icon

        // Tạo định dạng cho ngày và giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");

        // Tạo Timer để cập nhật thời gian mỗi giây
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thời gian hiện tại
                LocalDateTime now = LocalDateTime.now();
                // Định dạng và đặt thời gian vào JLabel
                timeLabel.setText(now.format(formatter));
            }
        });
        timer.start();

        jp_title.setLayout(new GridLayout(1, 3, 0, 0));

        // Tạo JLabel
        jp_title.add(jLabel1);
        jp_title.add(lable_tenNhaHang); // Thêm label vào panel
        jp_title.add(timeLabel); // Thêm label vào panel
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        jp_menu.setOpaque(true);  // Đảm bảo JPanel không trong suốt

        btn_main_img.setIcon(new javax.swing.ImageIcon("D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\main3.jpg"));
        currentIndex = 0;
        String main[] = new String[]{"D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\main3.jpg",
            "D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\main1.jpg",
            "D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\main2.jpg"
        };
        Timer timer1 = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % 3;
                btn_main_img.setIcon(new ImageIcon(main[currentIndex]));
            }
        });
        timer1.start();
    }
    // Tạo hàm để gán sự kiện hover và chọn cho các JButton

    private void addButtonListeners(JButton button) {
        button.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
        button.setFocusPainted(false); // Tắt hiệu ứng focus

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(selectedColor);  // Đổi màu khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Nếu nút không được chọn, trả về màu nền mặc định
                if (!button.getModel().isSelected()) {
                    button.setBackground(color_bg);  // Màu nền mặc định
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                deselectAllButtons();  // Hủy chọn tất cả các nút khác
                button.getModel().setSelected(true);  // Chọn nút hiện tại
                button.setBackground(selectedColor);  // Đổi màu khi được chọn
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.getModel().isSelected()) {
                    button.setBackground(selectedColor);  // Giữ màu khi nút đang được chọn
                } else {
                    button.setBackground(color_bg);  // Trả về màu nền mặc định nếu không chọn
                }
            }
        });
    }

// Hàm hủy chọn tất cả các nút
    private void deselectAllButtons() {
        JButton[] buttons = {menuItem_BanAn, menuItem_DatBan, menuItem_HoaDon, menuItem_MonAn, menuItem_KhachHang, menuItem_TaiKhoan, menuItem_NhanVien, menuItem_ThongKe};
        for (JButton btn : buttons) {
            btn.getModel().setSelected(false);
            btn.setBackground(color_bg);  // Đặt lại màu nền mặc định
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jp_menu = new javax.swing.JPanel();
        logo = new javax.swing.JButton();
        labelTenNV = new javax.swing.JLabel();
        menuItem_BanAn = new javax.swing.JButton();
        menuItem_TaiKhoan = new javax.swing.JButton();
        menuItem_DatBan = new javax.swing.JButton();
        menuItem_HoaDon = new javax.swing.JButton();
        menuItem_MonAn = new javax.swing.JButton();
        menuItem_KhachHang = new javax.swing.JButton();
        menuItem_NhanVien = new javax.swing.JButton();
        menuItem_ThongKe = new javax.swing.JButton();
        jp_root = new javax.swing.JPanel();
        btn_main_img = new javax.swing.JButton();
        jp_root_center = new javax.swing.JPanel();
        jp_title = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        lable_tenNhaHang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jp_menu.setBackground(new java.awt.Color(255, 255, 255));
        jp_menu.setForeground(new java.awt.Color(51, 102, 0));
        jp_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon("D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\logo.png")); // NOI18N
        logo.setBorder(null);
        logo.setBorderPainted(false);
        logo.setContentAreaFilled(false);
        logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoActionPerformed(evt);
            }
        });
        jp_menu.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 120));

        labelTenNV.setBackground(new java.awt.Color(22, 78, 180));
        labelTenNV.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelTenNV.setForeground(new java.awt.Color(255, 255, 255));
        labelTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(211, 206, 206)));
        jp_menu.add(labelTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 260, 60));

        menuItem_BanAn.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_BanAn.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_BanAn.setText("Bàn Ăn");
        menuItem_BanAn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 100, 1, 1));
        menuItem_BanAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_BanAn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_BanAn.setIconTextGap(30);
        menuItem_BanAn.setMaximumSize(new java.awt.Dimension(97, 32));
        menuItem_BanAn.setMinimumSize(new java.awt.Dimension(97, 32));
        menuItem_BanAn.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_BanAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuItem_BanAnMouseClicked(evt);
            }
        });
        menuItem_BanAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_BanAnActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_BanAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 260, 50));

        menuItem_TaiKhoan.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_TaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_TaiKhoan.setText("Tài Khoản");
        menuItem_TaiKhoan.setBorder(null);
        menuItem_TaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_TaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_TaiKhoan.setIconTextGap(30);
        menuItem_TaiKhoan.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_TaiKhoanActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 260, 50));

        menuItem_DatBan.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_DatBan.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_DatBan.setText("Đặt Bàn");
        menuItem_DatBan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 90, 1, 1));
        menuItem_DatBan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_DatBan.setIconTextGap(10);
        menuItem_DatBan.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_DatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_DatBanActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_DatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 260, 50));

        menuItem_HoaDon.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_HoaDon.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_HoaDon.setText("Hóa Đơn");
        menuItem_HoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 100, 1, 1));
        menuItem_HoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_HoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_HoaDon.setIconTextGap(30);
        menuItem_HoaDon.setMaximumSize(new java.awt.Dimension(97, 32));
        menuItem_HoaDon.setMinimumSize(new java.awt.Dimension(97, 32));
        menuItem_HoaDon.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_HoaDonActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_HoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 260, 50));

        menuItem_MonAn.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_MonAn.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_MonAn.setText("Món Ăn");
        menuItem_MonAn.setBorder(null);
        menuItem_MonAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_MonAn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_MonAn.setIconTextGap(30);
        menuItem_MonAn.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_MonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_MonAnActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_MonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 260, 50));

        menuItem_KhachHang.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_KhachHang.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_KhachHang.setText("Khách Hàng");
        menuItem_KhachHang.setBorder(null);
        menuItem_KhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_KhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_KhachHang.setIconTextGap(30);
        menuItem_KhachHang.setPreferredSize(new java.awt.Dimension(72, 30));
        menuItem_KhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_KhachHangActionPerformed(evt);
            }
        });
        jp_menu.add(menuItem_KhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 260, 50));

        menuItem_NhanVien.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_NhanVien.setText("Nhân Viên");
        menuItem_NhanVien.setBorder(null);
        menuItem_NhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_NhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_NhanVien.setIconTextGap(30);
        menuItem_NhanVien.setPreferredSize(new java.awt.Dimension(72, 30));
        jp_menu.add(menuItem_NhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 260, 50));

        menuItem_ThongKe.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 18)); // NOI18N
        menuItem_ThongKe.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_ThongKe.setText("Thống Kê");
        menuItem_ThongKe.setBorder(null);
        menuItem_ThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuItem_ThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuItem_ThongKe.setIconTextGap(30);
        menuItem_ThongKe.setPreferredSize(new java.awt.Dimension(72, 30));
        jp_menu.add(menuItem_ThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 260, 50));

        getContentPane().add(jp_menu);
        jp_menu.setBounds(0, 0, 250, 590);

        jp_root.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jp_root.setLayout(new java.awt.BorderLayout());
        jp_root.add(btn_main_img, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jp_root_centerLayout = new javax.swing.GroupLayout(jp_root_center);
        jp_root_center.setLayout(jp_root_centerLayout);
        jp_root_centerLayout.setHorizontalGroup(
            jp_root_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        jp_root_centerLayout.setVerticalGroup(
            jp_root_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jp_root.add(jp_root_center, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jp_root);
        jp_root.setBounds(250, 60, 600, 380);

        jp_title.setBackground(new java.awt.Color(0, 102, 0));
        jp_title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jp_title.setPreferredSize(new java.awt.Dimension(603, 60));

        timeLabel.setBackground(new java.awt.Color(255, 255, 255));
        timeLabel.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setIcon(new javax.swing.ImageIcon("D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\img\\Iynque-Ios7-Style-Clock.24.png")); // NOI18N
        timeLabel.setToolTipText("");
        timeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));
        timeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        timeLabel.setInheritsPopupMenu(false);
        timeLabel.setRequestFocusEnabled(false);
        timeLabel.setVerifyInputWhenFocusTarget(false);

        lable_tenNhaHang.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 3, 20)); // NOI18N
        lable_tenNhaHang.setForeground(new java.awt.Color(255, 255, 255));
        lable_tenNhaHang.setText("WAVE - Nơi hương vị vẫy gọi");
        lable_tenNhaHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lable_tenNhaHang.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jp_titleLayout = new javax.swing.GroupLayout(jp_title);
        jp_title.setLayout(jp_titleLayout);
        jp_titleLayout.setHorizontalGroup(
            jp_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(lable_tenNhaHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jp_titleLayout.setVerticalGroup(
            jp_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_titleLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jp_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_titleLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lable_tenNhaHang, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jp_title);
        jp_title.setBounds(250, 0, 603, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoActionPerformed
        // TODO add your handling code here:
        jp_root_center.removeAll();
        jp_root_center.add(btn_main_img);
        jp_root_center.revalidate();
        jp_root_center.repaint();
    }//GEN-LAST:event_logoActionPerformed

    private void menuItem_BanAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_BanAnActionPerformed
        // TODO add your handling code here:
        jp_root_center.removeAll();
        jp_root_center.add(new BanAn_GUI(), BorderLayout.CENTER);
        jp_root_center.revalidate();
        jp_root_center.repaint();
    }//GEN-LAST:event_menuItem_BanAnActionPerformed

    private void menuItem_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_HoaDonActionPerformed

    private void menuItem_DatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_DatBanActionPerformed
        // TODO add your handling code here:
        jp_root_center.removeAll();
        jp_root_center.add(new DanhSachBan_GUI(this), BorderLayout.CENTER);
        jp_root_center.revalidate();
        jp_root_center.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_DatBanActionPerformed

    private void menuItem_KhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_KhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_KhachHangActionPerformed

    private void menuItem_MonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_MonAnActionPerformed
        jp_root_center.removeAll();
        jp_root_center.add(new MonAnGUI());
        jp_root_center.revalidate();
        jp_root_center.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_MonAnActionPerformed

    private void menuItem_BanAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItem_BanAnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_BanAnMouseClicked

    private void menuItem_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_TaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem_TaiKhoanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_main_img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jp_menu;
    private javax.swing.JPanel jp_root;
    public javax.swing.JPanel jp_root_center;
    private javax.swing.JPanel jp_title;
    private javax.swing.JLabel labelTenNV;
    private javax.swing.JLabel lable_tenNhaHang;
    private javax.swing.JButton logo;
    private javax.swing.JButton menuItem_BanAn;
    private javax.swing.JButton menuItem_DatBan;
    private javax.swing.JButton menuItem_HoaDon;
    private javax.swing.JButton menuItem_KhachHang;
    private javax.swing.JButton menuItem_MonAn;
    private javax.swing.JButton menuItem_NhanVien;
    private javax.swing.JButton menuItem_TaiKhoan;
    private javax.swing.JButton menuItem_ThongKe;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
