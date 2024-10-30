/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.dao.NhanVien_DAO;
import com.mycompany.quanlydatban.entity.EnumChucVu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import com.mycompany.quanlydatban.entity.NhanVien;
import com.toedter.calendar.JDateChooser;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Vo Van Tuong
 */
public class NhanVienGUI extends javax.swing.JPanel {

    Color color_bg = Color.decode("#00405d");
    private final JDateChooser dateChooser;

    /**
     * Creates new form NhanVienGUI
     */
    public NhanVienGUI() {
        initComponents();
        setLayout(new BorderLayout());
        add(jp_TT, BorderLayout.NORTH);
        add(jp_MaNV, BorderLayout.CENTER);
        add(jp_Table, BorderLayout.SOUTH);

        bt_Sua.setBackground(color_bg);
        bt_Them.setBackground(color_bg);
        bt_Xoa.setBackground(color_bg);
        bt_XuatFile.setBackground(color_bg);
        bt_Tim.setBackground(color_bg);
        dtm = (DefaultTableModel) jtable.getModel();
        ButtonGroup gb = new ButtonGroup();
        
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
        jp_NgaySinh.setLayout(new BorderLayout());
        jp_NgaySinh.add(dateChooser,BorderLayout.CENTER);
        
        
        gb.add(jrd_Nam);
        gb.add(jrd_Nu);
        
        PlaceholderSupport placehodersupport = new PlaceholderSupport();
        placehodersupport.addPlaceholder(jt_tim,"Nhập mã nhân viên cần tìm");
        
        dataToTable();
        
        
        
    }

    public void dataToTable() {
        List<com.mycompany.quanlydatban.entity.NhanVien> list = nv_d.getAllNhanVien();
        for (com.mycompany.quanlydatban.entity.NhanVien nv : list) {
            dtm.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getTenNhanVien(),
                datetimeFormatter(nv.getNgaySinh()),
                nv.getGioiTinh() ? "Nam" : "Nữ",
                nv.getEmail(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getChucVu()
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
        lb_QLNV = new javax.swing.JLabel();
        jp_MaNV = new javax.swing.JPanel();
        jlb_MaNV = new javax.swing.JLabel();
        jt_MaNV = new javax.swing.JTextField();
        jlb_TenNV = new javax.swing.JLabel();
        jt_TenNV = new javax.swing.JTextField();
        jlb_DiaChi = new javax.swing.JLabel();
        jt_DiaChi = new javax.swing.JTextField();
        jlb_Email = new javax.swing.JLabel();
        jt_Email = new javax.swing.JTextField();
        jlb_NgaySinh = new javax.swing.JLabel();
        jlb_GioiTinh = new javax.swing.JLabel();
        jrd_Nam = new javax.swing.JRadioButton();
        jrd_Nu = new javax.swing.JRadioButton();
        jlb_SDT = new javax.swing.JLabel();
        jt_SDT = new javax.swing.JTextField();
        jlb_ChucVu = new javax.swing.JLabel();
        bt_Them = new javax.swing.JButton();
        bt_Xoa = new javax.swing.JButton();
        bt_Sua = new javax.swing.JButton();
        bt_XuatFile = new javax.swing.JButton();
        jcb_chuVu = new javax.swing.JComboBox<>();
        jp_NgaySinh = new javax.swing.JPanel();
        jp_Table = new javax.swing.JPanel();
        jSC_p = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        jt_tim = new javax.swing.JTextField();
        bt_Tim = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(598, 100));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_TT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lb_QLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_QLNV.setText("    QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jp_TTLayout = new javax.swing.GroupLayout(jp_TT);
        jp_TT.setLayout(jp_TTLayout);
        jp_TTLayout.setHorizontalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TTLayout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(lb_QLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(507, Short.MAX_VALUE))
        );
        jp_TTLayout.setVerticalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_QLNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        add(jp_TT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1210, 50));

        jp_MaNV.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlb_MaNV.setText("Mã Nhân Viên");

        jt_MaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_MaNVActionPerformed(evt);
            }
        });

        jlb_TenNV.setText("Tên Nhân Viên");

        jt_TenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_TenNVActionPerformed(evt);
            }
        });

        jlb_DiaChi.setText("Địa chỉ");

        jt_DiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_DiaChiActionPerformed(evt);
            }
        });

        jlb_Email.setText("Email");

        jt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_EmailActionPerformed(evt);
            }
        });

        jlb_NgaySinh.setText("Ngày Sinh");

        jlb_GioiTinh.setText("Giới tính");

        jrd_Nam.setText("Nam");
        jrd_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrd_NamActionPerformed(evt);
            }
        });

        jrd_Nu.setText("Nữ");
        jrd_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrd_NuActionPerformed(evt);
            }
        });

        jlb_SDT.setText("Số Điện Thoại");

        jlb_ChucVu.setText("Chức Vụ");

        bt_Them.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Them.setForeground(new java.awt.Color(255, 255, 255));
        bt_Them.setText("Thêm");
        bt_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemActionPerformed(evt);
            }
        });

        bt_Xoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Xoa.setForeground(new java.awt.Color(255, 255, 255));
        bt_Xoa.setText("Xoá");
        bt_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaActionPerformed(evt);
            }
        });

        bt_Sua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Sua.setForeground(new java.awt.Color(255, 255, 255));
        bt_Sua.setText("Cập Nhật");
        bt_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SuaActionPerformed(evt);
            }
        });

        bt_XuatFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_XuatFile.setForeground(new java.awt.Color(255, 255, 255));
        bt_XuatFile.setText("Xuất file");
        bt_XuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XuatFileActionPerformed(evt);
            }
        });

        jcb_chuVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NHAN_VIEN", "QUAN_LY" }));
        jcb_chuVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_chuVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_NgaySinhLayout = new javax.swing.GroupLayout(jp_NgaySinh);
        jp_NgaySinh.setLayout(jp_NgaySinhLayout);
        jp_NgaySinhLayout.setHorizontalGroup(
            jp_NgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jp_NgaySinhLayout.setVerticalGroup(
            jp_NgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_MaNVLayout = new javax.swing.GroupLayout(jp_MaNV);
        jp_MaNV.setLayout(jp_MaNVLayout);
        jp_MaNVLayout.setHorizontalGroup(
            jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_MaNVLayout.createSequentialGroup()
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jp_MaNVLayout.createSequentialGroup()
                                .addComponent(jlb_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jt_DiaChi))
                            .addGroup(jp_MaNVLayout.createSequentialGroup()
                                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlb_MaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlb_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jt_TenNV)
                                    .addComponent(jt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jp_MaNVLayout.createSequentialGroup()
                                .addComponent(jlb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jt_Email))))
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jp_MaNVLayout.createSequentialGroup()
                                .addComponent(jlb_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jp_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))
                            .addGroup(jp_MaNVLayout.createSequentialGroup()
                                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlb_SDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlb_GioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlb_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jt_SDT)
                                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                                        .addComponent(jrd_Nam)
                                        .addGap(26, 26, 26)
                                        .addComponent(jrd_Nu)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jcb_chuVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(117, 117, 117))
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bt_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(bt_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(bt_XuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jp_MaNVLayout.setVerticalGroup(
            jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_MaNVLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_NgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlb_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jp_NgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlb_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jrd_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrd_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlb_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jt_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jlb_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlb_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_MaNVLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlb_ChucVu, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jcb_chuVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_XuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );

        add(jp_MaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1210, 250));

        jSC_p.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jSC_pAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Giới Tính", "Email", "Số Điện Thoại", "Địa Chỉ", "Chức Vụ"
            }
        ));
        jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableMouseClicked(evt);
            }
        });
        jSC_p.setViewportView(jtable);

        bt_Tim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Tim.setForeground(new java.awt.Color(255, 255, 255));
        bt_Tim.setText("Tìm");
        bt_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_TableLayout = new javax.swing.GroupLayout(jp_Table);
        jp_Table.setLayout(jp_TableLayout);
        jp_TableLayout.setHorizontalGroup(
            jp_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_TableLayout.createSequentialGroup()
                .addContainerGap(854, Short.MAX_VALUE)
                .addComponent(jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Tim)
                .addGap(19, 19, 19))
            .addComponent(jSC_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
        );
        jp_TableLayout.setVerticalGroup(
            jp_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSC_p, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jp_Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 1220, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void jt_MaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_MaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MaNVActionPerformed

    private void jt_TenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_TenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_TenNVActionPerformed

    private void jt_DiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_DiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_DiaChiActionPerformed

    private void jt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_EmailActionPerformed

    private void jrd_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrd_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrd_NamActionPerformed

    private void jrd_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrd_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrd_NuActionPerformed

    private void bt_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaActionPerformed
        // TODO add your handling code here:
        int r = jtable.getSelectedRow();
        if (r != -1) {
            int result = JOptionPane.showOptionDialog(
                    this, // Parent component
                    "Bạn có chắc chắn muốn xoá nhân viên này không?", // Message
                    "Thông báo", // Title
                    JOptionPane.YES_NO_OPTION, // Option type (Yes/No buttons)
                    JOptionPane.QUESTION_MESSAGE, // Message type (icon)
                    null, // Icon (null means default)
                    null, // Possible options (null uses default Yes/No)
                    null // Default option (null means first button, "Yes")
            );
            if (result == JOptionPane.YES_OPTION) {
                NhanVien_DAO nv_d = new NhanVien_DAO();
                if (nv_d.xoaNV(dtm.getValueAt(r, 0).toString())) {
                    dtm.setRowCount(0);
                    dataToTable();
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân để xoá");
    }//GEN-LAST:event_bt_XoaActionPerformed

    private void bt_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SuaActionPerformed
        // TODO add your handling code here:
        String maNV = jt_MaNV.getText();
        NhanVien_DAO nv_d = new NhanVien_DAO();
        NhanVien nv = nv_d.tim(maNV);
        if (nv != null) {
            String teNV = jt_TenNV.getText();
            String sdt = jt_SDT.getText();
            String dC = jt_DiaChi.getText();
            String eM = jt_Email.getText();
//            String ngays = jt_NgaySinh.getText();
//            LocalDate ngaySinh = LocalDate.parse(ngays, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate ngaySinh = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String chVu = jcb_chuVu.getSelectedItem().toString();
            EnumChucVu cV = EnumChucVu.valueOf(chVu);
            Boolean GT = jrd_Nam.isSelected();

            nv.setTenNhanVien(teNV);
            nv.setEmail(eM);
            nv.setNgaySinh(ngaySinh);
            nv.setDiaChi(dC);
            nv.setChucVu(cV);
            nv.setSoDienThoai(sdt);
            nv.setGioiTinh(GT);

            if (nv_d.suaNV(nv)) {
                dtm.setRowCount(0);
                dataToTable();
            }
        }

    }//GEN-LAST:event_bt_SuaActionPerformed


    private void bt_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemActionPerformed
        // TODO add your handling code here:
        String maNV = jt_MaNV.getText();
        String tenNV = jt_TenNV.getText();
        String diaChi = jt_DiaChi.getText();
        String email = jt_Email.getText();
        String SDT = jt_SDT.getText();
        LocalDate ngaySinh = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        boolean phai = true;
        if (jrd_Nam.isSelected()) {
            phai = true;
        } else if (jrd_Nu.isSelected()) {
            phai = false;
        }
        NhanVien nv = new NhanVien(maNV, tenNV, email, SDT, diaChi, phai, ngaySinh, isNhanVien ? EnumChucVu.NHAN_VIEN : EnumChucVu.QUAN_LY);
        NhanVien_DAO nv_d = new NhanVien_DAO();

        if (nv_d.themNV(nv)) {
            dtm.setRowCount(0);
            dataToTable();
        }
    }//GEN-LAST:event_bt_ThemActionPerformed

    private void jcb_chuVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_chuVuActionPerformed
        // TODO add your handling code here:
        int r = jcb_chuVu.getSelectedIndex();
        isNhanVien = r == 0 ? true : false;
    }//GEN-LAST:event_jcb_chuVuActionPerformed

    private void jSC_pAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSC_pAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jSC_pAncestorMoved

    private void jtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableMouseClicked
        // TODO add your handling code here:
        int r = jtable.getSelectedRow();
        if (r != -1&& evt.getClickCount()==1) {
            jt_MaNV.setEditable(false);
            jt_MaNV.setText(dtm.getValueAt(r, 0).toString());
            jt_TenNV.setText(dtm.getValueAt(r, 1).toString());
            String ngay = dtm.getValueAt(r, 2).toString();
            LocalDate localDateNS = LocalDate.parse(ngay, dtf);
            Date date = java.sql.Date.valueOf(localDateNS);
            dateChooser.setDate(date);
           // LocalDate ngaySinh = LocalDate.parse(ngay, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//            jrd_Nu.setSelected(dtm.getValueAt(r,3).toString().equals("Nữ"));
//            jrd_Nam.setSelected(dtm.getValueAt(r,3).toString().equals("Nam"));
            if (dtm.getValueAt(r, 3).toString().equals("Nữ")) {
                jrd_Nu.setSelected(true);
            } else {
                jrd_Nam.setSelected(true);
            }
            jt_Email.setText(dtm.getValueAt(r, 4).toString());
            jt_SDT.setText(dtm.getValueAt(r, 5).toString());
            jt_DiaChi.setText(dtm.getValueAt(r, 6).toString());
            
            
//            jt_NgaySinh.setText(ngay);
            jcb_chuVu.setSelectedItem(dtm.getValueAt(r, 7).toString().trim());
        }
        else{
            jtable.clearSelection();
            jt_MaNV.setEditable(true);
            xoaTrang();
        }
    }//GEN-LAST:event_jtableMouseClicked

    private void bt_XuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XuatFileActionPerformed
        // TODO add your handling code here:
        xuatExcel(nv_d.getAllNhanVien());
    }//GEN-LAST:event_bt_XuatFileActionPerformed

    private void bt_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TimActionPerformed
        // TODO add your handling code here:
        String maNV = jt_tim.getText();
        NhanVien_DAO nv_d = new NhanVien_DAO();
        NhanVien nv = nv_d.tim(maNV);
        if(nv!=null)
        {
            dtm.setRowCount(0);
            dtm.addRow(new Object[]{
                nv.getMaNhanVien(), 
                nv.getTenNhanVien(),
                datetimeFormatter(nv.getNgaySinh()),
                nv.getGioiTinh()?"Nam":"Nữ",
                nv.getEmail(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getChucVu().toString()
                
            });
        } 
         else if(jt_tim.getText().equals(""))
            {
                dtm.setRowCount(0);
                dataToTable();
            }
            else if(nv== null) {
                JOptionPane.showMessageDialog(this,"Không tìm thấy nhân viên này");
        }
}
public void  xoaTrang()
    {
        jt_MaNV.setText("");
        jt_TenNV.setText("");
//        jt_NgaySinh.setText("");
        dateChooser.setDate(new Date());
        jt_SDT.setText("");
        jt_Email.setText("");
        jt_DiaChi.setText("");
        ButtonGroup gp = new ButtonGroup();
        gp.add(jrd_Nam);
        gp.add(jrd_Nu);
        gp.clearSelection();
            
    }//GEN-LAST:event_bt_TimActionPerformed
   public void xuatExcel(List<NhanVien> data) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Data");
    String[] headerData = {"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Email", "Số điện thoại", "Địa chỉ", "Chức vụ"};

    // Header styling
    CellStyle headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setColor(IndexedColors.WHITE.getIndex());
    headerStyle.setFont(headerFont);
    headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Create header row
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < headerData.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headerData[i]);
        cell.setCellStyle(headerStyle);
    }

    // Populate data rows
    for (int i = 0; i < data.size(); i++) {
        NhanVien nv = data.get(i);
        Row row = sheet.createRow(i + 1);

        String[] cellData = {
            nv.getMaNhanVien(),
            nv.getTenNhanVien(),
            datetimeFormatter(nv.getNgaySinh()).toString(),
           
            nv.getGioiTinh() ? "Nam" : "Nữ",
            nv.getEmail(),
            nv.getSoDienThoai(),
            nv.getDiaChi(),
            nv.getChucVu().toString()
        };

        for (int j = 0; j < cellData.length; j++) {
            row.createCell(j).setCellValue(cellData[j]);
        }
    }

    // Auto-size columns
    for (int i = 0; i < headerData.length; i++) {
        sheet.autoSizeColumn(i);
    }

    // Write to file and open
    String fileName = path + "\\dsnv"+UUID.randomUUID().toString()+".xlsx";
    try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
        workbook.write(fileOut);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    try {
        File file = new File(fileName);
        if (file.exists() && Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Sua;
    private javax.swing.JButton bt_Them;
    private javax.swing.JButton bt_Tim;
    private javax.swing.JButton bt_Xoa;
    private javax.swing.JButton bt_XuatFile;
    private javax.swing.JScrollPane jSC_p;
    private javax.swing.JComboBox<String> jcb_chuVu;
    private javax.swing.JLabel jlb_ChucVu;
    private javax.swing.JLabel jlb_DiaChi;
    private javax.swing.JLabel jlb_Email;
    private javax.swing.JLabel jlb_GioiTinh;
    private javax.swing.JLabel jlb_MaNV;
    private javax.swing.JLabel jlb_NgaySinh;
    private javax.swing.JLabel jlb_SDT;
    private javax.swing.JLabel jlb_TenNV;
    private javax.swing.JPanel jp_MaNV;
    private javax.swing.JPanel jp_NgaySinh;
    private javax.swing.JPanel jp_TT;
    private javax.swing.JPanel jp_Table;
    private javax.swing.JRadioButton jrd_Nam;
    private javax.swing.JRadioButton jrd_Nu;
    private javax.swing.JTextField jt_DiaChi;
    private javax.swing.JTextField jt_Email;
    private javax.swing.JTextField jt_MaNV;
    private javax.swing.JTextField jt_SDT;
    private javax.swing.JTextField jt_TenNV;
    private javax.swing.JTextField jt_tim;
    private javax.swing.JTable jtable;
    private javax.swing.JLabel lb_QLNV;
    // End of variables declaration//GEN-END:variables
    private NhanVien_DAO nv_d = new NhanVien_DAO();
    private DefaultTableModel dtm;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private boolean isNhanVien = true;
    private final String path = "D:\\QuanLyDatBan\\QuanLyDatBan\\src\\main\\java\\excel";
}
