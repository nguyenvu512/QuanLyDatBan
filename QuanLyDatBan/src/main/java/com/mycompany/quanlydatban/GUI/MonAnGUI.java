/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.GUI.PlaceholderSupport;
import com.mycompany.quanlydatban.dao.DanhMucMonAnDAO;
import com.mycompany.quanlydatban.dao.MonAnDAO;
import com.mycompany.quanlydatban.entity.DanhMucMonAn;
import com.mycompany.quanlydatban.entity.MonAn;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER
 */
public class MonAnGUI extends javax.swing.JPanel {

    Color color_bg = Color.decode("#00405d");
    private String urlAnh;
    HashMap<String, DanhMucMonAn> danhMuc;
    private static final String path_xlsx = "D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\excel_monAn";
    private DefaultTableModel dtm;
    private List<MonAn> list = MonAnDAO.getAllMonAn();
    private String path = null;

    /**
     * Creates new form MonAn_GUI
     */
    public MonAnGUI() {
        initComponents();
        Dimension size = new Dimension(1019, 100);
        setSize(size);
        jt_maF.setText(phatSinhMaHD());
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setFont(new Font("JetBrains Mono", 0, 14));
        jTable1.getTableHeader().setBackground(new Color(255, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(25);
        jTable1.setBackground(new Color(255, 255, 255));
        jTable1.setSelectionMode(0);
        jb_hinhAnh.setBackground(color_bg);
        jb_sua.setBackground(color_bg);
        jb_them.setBackground(color_bg);
        jb_tim.setBackground(color_bg);
        jb_xuat.setBackground(color_bg);
        jb_xoa.setBackground(color_bg);
        PlaceholderSupport placeholderSupport = new PlaceholderSupport();
        placeholderSupport.addPlaceholder(jt_tim, " Nhập mã bàn để tìm kiếm");

        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setFont(new Font("JetBrains Mono", 0, 14));
        jTable1.getTableHeader().setBackground(color_bg);
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.setRowHeight(25);
        jTable1.setBackground(new Color(255, 255, 255));
        jTable1.setSelectionMode(0);
        dtm = (DefaultTableModel) jTable1.getModel();
        
        List<DanhMucMonAn> danhMucMonAn = DanhMucMonAnDAO.getDSLoaiSP();
        danhMuc = new HashMap<>();
        jComboBox_DM.setBackground(Color.WHITE);
        for (DanhMucMonAn i : danhMucMonAn) {
            jComboBox_DM.addItem(i.getTenDanhMuc());
            danhMuc.put(i.getTenDanhMuc(), i);
          
        }
        dataToTable();

    }

    public String phatSinhMaHD() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder generatedString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            generatedString.append(characters.charAt(index));
        }
        return generatedString.toString();
    }
    
    public void xuatExcel(List<MonAn> data) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Món ăn");
        String[] headerData = {"Mã món ăn", "Tên món ăn", "Giá tiền", "Danh mục", "Hình ảnh", "Mô tả"};

        // Header styling
        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headerData.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerData[i]);
            cell.setCellStyle(headerStyle);
        }

        // Populate data rows
        for (int i = 0; i < data.size(); i++) {
            MonAn ma = data.get(i);
            Row row = sheet.createRow(i + 1);
            String[] cellData = {
                ma.getMaMonAn(),
                ma.getTenMonAn(),
                String.valueOf(ma.getGiaTien()),
                ma.getDanhMuc().getTenDanhMuc(),
                ma.getHinhAnh(),
                ma.getMoTa()
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
        String fileName = path_xlsx + "\\dsma" + UUID.randomUUID().toString() + ".xlsx";
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

    public MonAn timTheoMa(String maMA) {
        for (MonAn ma : list) {
            if (ma.getMaMonAn().equals(maMA)) {
                return ma;
            }

        }
        return null;
    }

    public void dataToTable() {
        for (MonAn ma : list) {
            dtm.addRow(new Object[]{
                ma.getMaMonAn(),
                ma.getTenMonAn(),
                ma.getGiaTien(),
                ma.getDanhMuc().getTenDanhMuc(),
                ma.getMoTa()
            });
        }
    }

    public void xoaTrang() {
        jt_maF.setText(phatSinhMaHD());
        jt_tenF.setText("");
        jt_gia.setText("");
        l_hinhAnh.setIcon(new ImageIcon(""));
        jt_mota.setText("");
        jt_tim.setText("");

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_picture = new javax.swing.JPanel();
        l_hinhAnh = new javax.swing.JLabel();
        jb_hinhAnh = new javax.swing.JButton();
        jp_food = new javax.swing.JPanel();
        l_maF = new javax.swing.JLabel();
        l_moTa = new javax.swing.JLabel();
        l_DM = new javax.swing.JLabel();
        jt_maF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_mota = new javax.swing.JTextArea();
        jt_gia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jComboBox_DM = new javax.swing.JComboBox<>();
        l_gia = new javax.swing.JLabel();
        l_tenF = new javax.swing.JLabel();
        jt_tenF = new javax.swing.JTextField();
        jp_button = new javax.swing.JPanel();
        jb_sua = new javax.swing.JButton();
        jb_xoa = new javax.swing.JButton();
        jb_xuat = new javax.swing.JButton();
        jb_them = new javax.swing.JButton();
        jp_table = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jt_tim = new javax.swing.JTextField();
        jb_tim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1019, 100));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_picture.setBackground(new java.awt.Color(255, 255, 255));
        jp_picture.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        l_hinhAnh.setBackground(new java.awt.Color(255, 255, 255));
        l_hinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jb_hinhAnh.setBackground(new java.awt.Color(22, 78, 180));
        jb_hinhAnh.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 18)); // NOI18N
        jb_hinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        jb_hinhAnh.setText("CHỌN ẢNH");
        jb_hinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_hinhAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_pictureLayout = new javax.swing.GroupLayout(jp_picture);
        jp_picture.setLayout(jp_pictureLayout);
        jp_pictureLayout.setHorizontalGroup(
            jp_pictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_pictureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jp_pictureLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jb_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_pictureLayout.setVerticalGroup(
            jp_pictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_pictureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        add(jp_picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 230, 250));

        jp_food.setBackground(new java.awt.Color(255, 255, 255));
        jp_food.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jp_food.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_maF.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        l_maF.setText("Mã món ăn:");
        jp_food.add(l_maF, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 34, 83, -1));

        l_moTa.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        l_moTa.setText("Mô tả:");
        jp_food.add(l_moTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 83, 30));

        l_DM.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        l_DM.setText("Danh mục:");
        jp_food.add(l_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 83, 30));

        jt_maF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jt_maF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jp_food.add(jt_maF, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 21, 210, 30));

        jt_mota.setColumns(1);
        jt_mota.setRows(3);
        jt_mota.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jScrollPane1.setViewportView(jt_mota);

        jp_food.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 210, 70));

        jt_gia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jp_food.add(jt_gia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 28, 179, 30));

        jComboBox_DM.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jComboBox_DM.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jComboBox_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DMActionPerformed(evt);
            }
        });
        jScrollPane2.setViewportView(jComboBox_DM);

        jp_food.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 180, 30));

        l_gia.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        l_gia.setText("Giá tiền:");
        jp_food.add(l_gia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 83, 30));

        l_tenF.setBackground(new java.awt.Color(255, 255, 255));
        l_tenF.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        l_tenF.setText("Tên món ăn:");
        jp_food.add(l_tenF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 83, 30));

        jt_tenF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jp_food.add(jt_tenF, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 67, 210, 30));

        jp_button.setBackground(new java.awt.Color(255, 255, 255));

        jb_sua.setBackground(new java.awt.Color(22, 78, 180));
        jb_sua.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 18)); // NOI18N
        jb_sua.setForeground(new java.awt.Color(255, 255, 255));
        jb_sua.setText("SỬA");
        jb_sua.setPreferredSize(new java.awt.Dimension(100, 50));
        jb_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_suaActionPerformed(evt);
            }
        });

        jb_xoa.setBackground(new java.awt.Color(22, 78, 180));
        jb_xoa.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 18)); // NOI18N
        jb_xoa.setForeground(new java.awt.Color(255, 255, 255));
        jb_xoa.setText("XÓA");
        jb_xoa.setPreferredSize(new java.awt.Dimension(100, 50));
        jb_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_xoaActionPerformed(evt);
            }
        });

        jb_xuat.setBackground(new java.awt.Color(22, 78, 180));
        jb_xuat.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 18)); // NOI18N
        jb_xuat.setForeground(new java.awt.Color(255, 255, 255));
        jb_xuat.setText("XUẤT FILE");
        jb_xuat.setPreferredSize(new java.awt.Dimension(97, 27));
        jb_xuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_xuatActionPerformed(evt);
            }
        });

        jb_them.setBackground(new java.awt.Color(22, 78, 180));
        jb_them.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 18)); // NOI18N
        jb_them.setForeground(new java.awt.Color(255, 255, 255));
        jb_them.setText("THÊM");
        jb_them.setPreferredSize(new java.awt.Dimension(100, 50));
        jb_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_themActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_buttonLayout = new javax.swing.GroupLayout(jp_button);
        jp_button.setLayout(jp_buttonLayout);
        jp_buttonLayout.setHorizontalGroup(
            jp_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_buttonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_them, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jb_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jb_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jb_xuat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jp_buttonLayout.setVerticalGroup(
            jp_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_buttonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jb_them, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jb_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jb_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jb_xuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jp_food.add(jp_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 709, 50));

        add(jp_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 760, 250));

        jp_table.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã món ăn", "Tên món ăn", "Giá tiền", "Danh mục", "Mô tả"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jt_tim.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jt_tim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jb_tim.setBackground(new java.awt.Color(22, 78, 180));
        jb_tim.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jb_tim.setForeground(new java.awt.Color(255, 255, 255));
        jb_tim.setText("Tìm kiếm");
        jb_tim.setBorder(null);
        jb_tim.setPreferredSize(new java.awt.Dimension(30, 30));
        jb_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_timActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_tableLayout = new javax.swing.GroupLayout(jp_table);
        jp_table.setLayout(jp_tableLayout);
        jp_tableLayout.setHorizontalGroup(
            jp_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_tableLayout.createSequentialGroup()
                .addComponent(jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jb_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(900, Short.MAX_VALUE))
            .addGroup(jp_tableLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_tableLayout.setVerticalGroup(
            jp_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );

        add(jp_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ MÓN ĂN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1000, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_DMActionPerformed

    private void jb_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_timActionPerformed
        // TODO add your handling code here:
        String tenMA = jt_tim.getText();
        MonAnDAO mad = new MonAnDAO();
        MonAn f = mad.timMonAnByTenMon(tenMA);
        if (f != null) {
            dtm.setRowCount(0);
            dtm.addRow(new Object[]{
                f.getMaMonAn(),
                f.getTenMonAn(),
                String.valueOf(f.getGiaTien()),
                f.getDanhMuc().getTenDanhMuc().toString(),
                f.getMoTa()

            });
        } else {
            dtm.setRowCount(0);
            list = MonAnDAO.getAllMonAn();
            dataToTable();
            xoaTrang();
            JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn");
        }
    }//GEN-LAST:event_jb_timActionPerformed

    private void jb_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_suaActionPerformed
        // TODO add your handling code here:
        String tenMA = jt_tenF.getText();
        MonAnDAO mad = new MonAnDAO();
        MonAn f = mad.timMonAnByTenMon(tenMA);
        if (f != null) {
            String ten = jt_tenF.getText();
            double gia = 0;

            try {
                gia = Double.parseDouble(jt_gia.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá tiền không hợp lệ! Vui lòng nhập một số.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return; // Ngưng thực hiện nếu giá không hợp lệ
            }
            DanhMucMonAn dm = DanhMucMonAnDAO.getByTenDanhMuc(jComboBox_DM.getSelectedItem().toString());
            String mota = jt_mota.getText();
            f.setTenMonAn(ten);
            f.setGiaTien(gia);
            f.setDanhMuc(dm);
            f.setHinhAnh(urlAnh);
            f.setMoTa(mota);
            mad.suaMonAn(f);
            dtm.setRowCount(0);
            list = MonAnDAO.getAllMonAn();
            dataToTable();
            xoaTrang();
            JOptionPane.showMessageDialog(this, "Sửa thành công");

        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
        }
    }//GEN-LAST:event_jb_suaActionPerformed

    private void jb_hinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_hinhAnhActionPerformed
        // Hiển thị JFileChooser để chọn ảnh
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        // Nếu người dùng chọn ảnh
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy file ảnh đã chọn
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tạo ImageIcon từ đường dẫn file ảnh
            ImageIcon icon = new ImageIcon(filePath);

            // Lấy ảnh từ ImageIcon và điều chỉnh kích thước để vừa với JLabel
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(l_hinhAnh.getWidth(), l_hinhAnh.getHeight(), Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Hiển thị ảnh trên JLabel
            l_hinhAnh.setIcon(scaledIcon);

            // Lưu đường dẫn ảnh vào biến urlAnh
            urlAnh = filePath;
    }//GEN-LAST:event_jb_hinhAnhActionPerformed
    }
    private void jb_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_themActionPerformed
        try {
            // Lấy các giá trị từ giao diện
            String ma = jt_maF.getText();
            String ten = jt_tenF.getText();
            String mota = jt_mota.getText();
            var danhmuc = (String) jComboBox_DM.getSelectedItem();
            DanhMucMonAn DanhMuc = danhMuc.get(danhmuc);

            double gia;
            try {
                gia = Double.valueOf(jt_gia.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá tiền không hợp lệ! Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi hàm nếu giá không hợp lệ
            }

            // Thêm món ăn vào cơ sở dữ liệu
            MonAnDAO.themMonAn(MonAn.builder()
                    .maMonAn(ma)
                    .tenMonAn(ten)
                    .moTa(mota)
                    .giaTien(gia)
                    .danhMuc(DanhMuc)
                    .hinhAnh(urlAnh)
                    .build()
            );

            // Thông báo thêm thành công
            JOptionPane.showMessageDialog(this, "Thêm món ăn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Thông báo lỗi
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }//GEN-LAST:event_jb_themActionPerformed

    private void jb_xuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_xuatActionPerformed
        // TODO add your handling code here:
        List<MonAn> list = MonAnDAO.getAllMonAn();
        xuatExcel(list);
    }//GEN-LAST:event_jb_xuatActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int r = jTable1.getSelectedRow();
        if (r != -1 && evt.getClickCount() == 1) {
            jt_maF.setEditable(false);
            jt_maF.setText(dtm.getValueAt(r, 0).toString());
            jt_tenF.setText(dtm.getValueAt(r, 1).toString());
            jt_gia.setText(dtm.getValueAt(r, 2).toString());
            jComboBox_DM.setSelectedItem(dtm.getValueAt(r, 3).toString().trim());
            MonAn ma = timTheoMa(dtm.getValueAt(r, 0).toString());
            ImageIcon icon = new ImageIcon(ma.getHinhAnh());
            // Lấy ảnh từ ImageIcon và điều chỉnh kích thước để vừa với JLabel
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(l_hinhAnh.getWidth(), l_hinhAnh.getHeight(), Image.SCALE_SMOOTH);
            // Tạo ImageIcon mới từ ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            // Hiển thị ảnh trên JLabel
            l_hinhAnh.setIcon(scaledIcon);
            jt_mota.setText(dtm.getValueAt(r, 4).toString());

        } else {
            jTable1.clearSelection();
            jt_maF.setEditable(true);
            xoaTrang();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jb_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_xoaActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getSelectedRow();
        if (r != -1) {
            int result = JOptionPane.showOptionDialog(
                    this, // Parent component
                    "Bạn có chắc chắn muốn xoá món ăn này không?", // Message
                    "Thông báo", // Title
                    JOptionPane.YES_NO_OPTION, // Option type (Yes/No buttons)
                    JOptionPane.QUESTION_MESSAGE, // Message type (icon)
                    null, // Icon (null means default)
                    null, // Possible options (null uses default Yes/No)
                    null // Default option (null means first button, "Yes")
            );
            if (result == JOptionPane.YES_OPTION) {
                MonAnDAO mad = new MonAnDAO();
                if (mad.xoaMonAn(dtm.getValueAt(r, 0).toString())) {
                    dtm.setRowCount(0);
                    list = MonAnDAO.getAllMonAn();
                    dataToTable();
                    xoaTrang();
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn để xoá");
    }//GEN-LAST:event_jb_xoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_DM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_hinhAnh;
    private javax.swing.JButton jb_sua;
    private javax.swing.JButton jb_them;
    private javax.swing.JButton jb_tim;
    private javax.swing.JButton jb_xoa;
    private javax.swing.JButton jb_xuat;
    private javax.swing.JPanel jp_button;
    private javax.swing.JPanel jp_food;
    private javax.swing.JPanel jp_picture;
    private javax.swing.JPanel jp_table;
    private javax.swing.JTextField jt_gia;
    private javax.swing.JTextField jt_maF;
    private javax.swing.JTextArea jt_mota;
    private javax.swing.JTextField jt_tenF;
    private javax.swing.JTextField jt_tim;
    private javax.swing.JLabel l_DM;
    private javax.swing.JLabel l_gia;
    private javax.swing.JLabel l_hinhAnh;
    private javax.swing.JLabel l_maF;
    private javax.swing.JLabel l_moTa;
    private javax.swing.JLabel l_tenF;
    // End of variables declaration//GEN-END:variables
}
