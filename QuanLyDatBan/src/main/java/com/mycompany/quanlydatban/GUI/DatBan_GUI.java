/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.dao.ChiTietHoaDonDAO;
import com.mycompany.quanlydatban.dao.DanhMucMonAnDAO;
import com.mycompany.quanlydatban.dao.HoaDonDAO;
import com.mycompany.quanlydatban.dao.KhachHangDao;
import com.mycompany.quanlydatban.dao.MonAnDAO;
import com.mycompany.quanlydatban.dao.NhanVienDAO;
import com.mycompany.quanlydatban.entity.BanAn;
import com.mycompany.quanlydatban.entity.ChiTietHoaDon;
import com.mycompany.quanlydatban.entity.DanhMucMonAn;
import com.mycompany.quanlydatban.entity.EnumTrangThaiDatBan;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import com.mycompany.quanlydatban.entity.KhachHang;
import com.mycompany.quanlydatban.entity.MonAn;
import com.mycompany.quanlydatban.entity.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ACER
 */
public class DatBan_GUI extends javax.swing.JPanel {

    /**
     * Creates new form DatBan_GUI
     */
    JPanel jp_thongTinMonAn = new JPanel();
    JScrollPane scrollPane = new JScrollPane(jp_thongTinMonAn);
    Color color_bg = Color.decode("#00405d");
    HashMap<String, DanhMucMonAn> danhMuc;
    Locale locale = new Locale("vi", "VN");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
    JSpinner spinnerNgayGio;
    DefaultTableModel model_dsMonAn;
    JTable table_dsMonAn;
    private double TONG_TIEN = 0;
    BanAn banAn;
    List<MonAn> listMonAn;
    double vat = 0;
    private double THANH_TIEN = 0;
    MainGUI mainGUI;
    DanhSachBan_GUI danhSachBan_GUI;
    HoaDonThanhToan hoaDonThanhToan;

    public DatBan_GUI(BanAn banAn, HoaDonThanhToan hoaDonThanhToan, MainGUI main, DanhSachBan_GUI danhSachBan_GUI) {
        initComponents();
        this.mainGUI=main;
        this.banAn = banAn;
        this.hoaDonThanhToan = hoaDonThanhToan;
        this.danhSachBan_GUI=danhSachBan_GUI;
        
        jp_thongTinMonAn.setLayout(new GridLayout(0, 3, 10, 10)); // 5 cột, khoảng cách giữa các bàn là 10px

        label_tenBan.setText(banAn.getMaBan());    

        listMonAn = MonAnDAO.getAllMonAn();
        for (int i = 0; i < listMonAn.size(); i++) {
            String tien = currencyFormat.format(listMonAn.get(i).getGiaTien());
            JButton btn_monAn = new JButton("<html>" + listMonAn.get(i).getTenMonAn() + "<br>(" + tien + ")</html>");

            // Tạo ImageIcon từ đường dẫn file ảnh
            ImageIcon icon = new ImageIcon(listMonAn.get(i).getHinhAnh());

            // Lấy ảnh từ ImageIcon và điều chỉnh kích thước để vừa với JLabel
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Hiển thị ảnh trên JLabel
            btn_monAn.setIcon(scaledIcon);
            btn_monAn.setBackground(Color.WHITE);

            btn_monAn.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
            btn_monAn.setFocusPainted(false); // Tắt hiệu ứng focus
            btn_monAn.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N

            // Đặt chữ ở dưới icon
            btn_monAn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn_monAn.setHorizontalTextPosition(SwingConstants.CENTER);

//            sự kiện nhấn nút chọn món 
            MonAn monAn = listMonAn.get(i);
            btn_monAn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Hiển thị hộp thoại nhập số lượng
                    String soLuongStr = JOptionPane.showInputDialog(null, "Nhập số lượng cho món: " + monAn.getTenMonAn(), "Nhập Số Lượng", JOptionPane.PLAIN_MESSAGE);

                    if (soLuongStr != null && !soLuongStr.isEmpty()) {
                        try {
                            int soLuong = Integer.parseInt(soLuongStr); // Chuyển đổi thành số
                            if (soLuong > 0) {
                                boolean monAnDaTonTai = false; // Biến kiểm tra món đã tồn tại

                                // Kiểm tra xem món đã có trong bảng chưa
                                for (int i = 0; i < model_dsMonAn.getRowCount(); i++) {
                                    String tenMonTrongBang = (String) model_dsMonAn.getValueAt(i, 0);
                                    if (tenMonTrongBang.equals(monAn.getTenMonAn())) {
                                        // Món ăn đã tồn tại, cập nhật số lượng
                                        int soLuongCu = (int) model_dsMonAn.getValueAt(i, 1);
                                        model_dsMonAn.setValueAt(soLuongCu + soLuong, i, 1); // Cộng thêm số lượng mới
                                        monAnDaTonTai = true; // Đánh dấu là đã xử lý
                                        break;
                                    }
                                }

                                // Nếu món ăn chưa tồn tại, thêm dòng mới
                                if (!monAnDaTonTai) {
                                    String tien1 = currencyFormat.format(monAn.getGiaTien());
                                    model_dsMonAn.addRow(new Object[]{monAn.getTenMonAn(), soLuong, tien1});
                                }
                                THANH_TIEN += monAn.getGiaTien() * soLuong;
                                vat = THANH_TIEN / 100 * 6;
                                TONG_TIEN = THANH_TIEN + vat;

                            } else {
                                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    label_thanhTien.setText(currencyFormat.format(THANH_TIEN));
                    label_vat.setText(currencyFormat.format(vat));
                    label_tongThanhToan.setText(currencyFormat.format(TONG_TIEN));
                }
            });

            jp_thongTinMonAn.add(btn_monAn);
        }

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.WHITE);

        btn_timKiemMonAn.setBackground(color_bg);

        List<DanhMucMonAn> danhMucMonAn = DanhMucMonAnDAO.getDSLoaiSP();
        danhMuc = new HashMap<>();
        for (DanhMucMonAn i : danhMucMonAn) {
            combobox_listMonAn.addItem(i.getTenDanhMuc());
            danhMuc.put(i.getTenDanhMuc(), i);

        }
        combobox_listMonAn.setBackground(Color.WHITE);

        PlaceholderSupport placeholderSupport = new PlaceholderSupport();
        placeholderSupport.addPlaceholder(txt_timKiemMonAn, "Nhập tên món để tìm");

        jp_thongTinMonAnRoot.add(scrollPane, BorderLayout.CENTER);
        jp_thongTinMonAnRoot.add(jPanel2, BorderLayout.NORTH);

        // Tạo Spinner để chọn ngày giờ
        SpinnerDateModel model = new SpinnerDateModel();
        spinnerNgayGio = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerNgayGio, "dd/MM/yyyy | HH:mm:ss");
        spinnerNgayGio.setEditor(editor);
        spinnerNgayGio.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));

        jp_ngayGio.add(spinnerNgayGio, BorderLayout.CENTER);

//        bảng ds món đã đặt
        jp_danhSachMonDat.setLayout(new BorderLayout());
        String columnName[] = {"Tên món ăn", "Số lượng", "Đơn giá"};
        model_dsMonAn = new DefaultTableModel(columnName, 0);
        table_dsMonAn = new JTable(model_dsMonAn);
        table_dsMonAn.setBackground(new Color(255, 255, 255));
        table_dsMonAn.setFont(new java.awt.Font("JetBrains Mono", 0, 14));
        table_dsMonAn.setRowHeight(25); // Thiết lập chiều cao mỗi dòng là 30 pixel
        JScrollPane jScrollPane_dsMonAn = new JScrollPane(table_dsMonAn);
        table_dsMonAn.setOpaque(true);
        // Lấy header của bảng và cài đặt font cho header
        JTableHeader tableHeader = table_dsMonAn.getTableHeader();
        tableHeader.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        tableHeader.setBackground(color_bg);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 25)); // Đặt chiều cao của header là 40 pixel
        // Lấy model của cột để điều chỉnh độ rộng
        table_dsMonAn.getColumnModel().getColumn(0).setPreferredWidth(190); // Cột "Tên món ăn" rộng hơn
        table_dsMonAn.getColumnModel().getColumn(1).setPreferredWidth(110);  // Cột "Số lượng" nhỏ hơn
        table_dsMonAn.getColumnModel().getColumn(2).setPreferredWidth(140); // Cột "Đơn giá" vừa phải

        jp_danhSachMonDat.add(jScrollPane_dsMonAn);

        btn_datBan.setBackground(color_bg);
        btn_huyBan.setBackground(color_bg);
        btn_thanhToan.setBackground(color_bg);
        btn_thoat.setBackground(color_bg);
        
        if(hoaDonThanhToan != null){
            //        gắn thông tin:
        txt_tenKhachHang.setText(hoaDonThanhToan.getKhachHang().getTenKhachHang());
        txt_soDienThoai.setText(hoaDonThanhToan.getKhachHang().getSoDienThoai());
        txt_diaChi.setText(hoaDonThanhToan.getKhachHang().getDiaChi());
        txt_email.setText(hoaDonThanhToan.getKhachHang().getEmail());
        txt_ghiChu.setText(hoaDonThanhToan.getGhiChu());
        txt_tienCoc.setText(currencyFormat.format(hoaDonThanhToan.getTienCoc()));
        // Chuyển đổi LocalDateTime thành Date và gán cho JSpinner
        LocalDateTime ngayDatBan = hoaDonThanhToan.getNgayDatBan(); // Lấy ngày từ hóa đơn
        Date date = Date.from(ngayDatBan.atZone(ZoneId.systemDefault()).toInstant()); // Chuyển đổi
        spinnerNgayGio.setValue(date); // Gán giá trị cho JSpinner
        
        List<ChiTietHoaDon> cthd = ChiTietHoaDonDAO.getDanhSachChiTietHoaDon(hoaDonThanhToan.getMaHoaDon());
            for(ChiTietHoaDon ct:cthd){
                 String tien1 = currencyFormat.format(ct.getMonAn().getGiaTien());
                model_dsMonAn.addRow(new Object[]{ct.getMonAn().getTenMonAn(), ct.getSoLuong(), tien1});
                THANH_TIEN += ct.getMonAn().getGiaTien() * ct.getSoLuong();
                                vat = THANH_TIEN / 100 * 6;
                                TONG_TIEN = THANH_TIEN + vat;

            }
        }
        label_thanhTien.setText(currencyFormat.format(THANH_TIEN));
        label_tongThanhToan.setText(currencyFormat.format(TONG_TIEN));
        label_vat.setText(currencyFormat.format(vat));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_thongTinKhachHang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tenKhachHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        jp_thongTinMonAnRoot = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        combobox_listMonAn = new javax.swing.JComboBox<>();
        txt_timKiemMonAn = new javax.swing.JTextField();
        btn_timKiemMonAn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jp_thongTinDatBan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jp_ngayGio = new javax.swing.JPanel();
        label_tenBan = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tienCoc = new javax.swing.JTextField();
        txt_ghiChu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jp_danhSachMonDat = new javax.swing.JPanel();
        jp_thanhToan = new javax.swing.JPanel();
        label_vat = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        label_thanhTien = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        label_tongThanhToan = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        label_khuyenMai = new javax.swing.JLabel();
        btn_thoat = new javax.swing.JButton();
        btn_huyBan = new javax.swing.JButton();
        btn_datBan = new javax.swing.JButton();
        btn_thanhToan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1019, 100));
        setLayout(null);

        jp_thongTinKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        jp_thongTinKhachHang.setAutoscrolls(true);
        jp_thongTinKhachHang.setFocusCycleRoot(true);
        jp_thongTinKhachHang.setInheritsPopupMenu(true);
        jp_thongTinKhachHang.setName(""); // NOI18N
        jp_thongTinKhachHang.setPreferredSize(new java.awt.Dimension(570, 130));
        jp_thongTinKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel1.setText("Email:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        txt_email.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jPanel1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 30));

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel2.setText("Địa chỉ:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, 30));

        txt_diaChi.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jPanel1.add(txt_diaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 150, 30));

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel3.setText("Tên khách hàng:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        txt_tenKhachHang.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txt_tenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenKhachHangActionPerformed(evt);
            }
        });
        jPanel1.add(txt_tenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, 30));

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel6.setText("Số điện thoại:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, 30));

        txt_soDienThoai.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txt_soDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soDienThoaiActionPerformed(evt);
            }
        });
        jPanel1.add(txt_soDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 150, 30));

        jp_thongTinKhachHang.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 110));

        add(jp_thongTinKhachHang);
        jp_thongTinKhachHang.setBounds(0, 0, 630, 120);

        jp_thongTinMonAnRoot.setBackground(new java.awt.Color(255, 255, 255));
        jp_thongTinMonAnRoot.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách món ăn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12))); // NOI18N
        jp_thongTinMonAnRoot.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 10, 1));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox_listMonAn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        combobox_listMonAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        combobox_listMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_listMonAnActionPerformed(evt);
            }
        });
        jPanel2.add(combobox_listMonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 30));

        txt_timKiemMonAn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jPanel2.add(txt_timKiemMonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 190, 30));

        btn_timKiemMonAn.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        btn_timKiemMonAn.setForeground(new java.awt.Color(255, 255, 255));
        btn_timKiemMonAn.setText("Tìm kiếm");
        btn_timKiemMonAn.setToolTipText("");
        btn_timKiemMonAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_timKiemMonAn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_timKiemMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemMonAnActionPerformed(evt);
            }
        });
        jPanel2.add(btn_timKiemMonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 100, 30));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, 40));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 40));

        jp_thongTinMonAnRoot.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        add(jp_thongTinMonAnRoot);
        jp_thongTinMonAnRoot.setBounds(10, 120, 610, 460);

        jp_thongTinDatBan.setBackground(new java.awt.Color(255, 255, 255));
        jp_thongTinDatBan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đặt bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12))); // NOI18N
        jp_thongTinDatBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_ngayGio.setBackground(new java.awt.Color(255, 255, 255));
        jp_ngayGio.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jp_ngayGio.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jp_ngayGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 210, 30));

        label_tenBan.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        label_tenBan.setText("Bàn 1");
        jPanel3.add(label_tenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -5, -1, -1));

        jLabel8.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel8.setText("Ghi chú:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        txt_tienCoc.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txt_tienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienCocActionPerformed(evt);
            }
        });
        jPanel3.add(txt_tienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 190, 30));

        txt_ghiChu.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txt_ghiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ghiChuActionPerformed(evt);
            }
        });
        jPanel3.add(txt_ghiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 190, 30));

        jLabel10.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel10.setText("Ngày đặt bàn:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        jLabel11.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel11.setText("Tiền cọc:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 30));

        jp_thongTinDatBan.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 370, 140));

        jp_danhSachMonDat.setBackground(new java.awt.Color(255, 255, 255));
        jp_danhSachMonDat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách món đã đặt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12))); // NOI18N
        jp_thongTinDatBan.add(jp_danhSachMonDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 370, 180));

        add(jp_thongTinDatBan);
        jp_thongTinDatBan.setBounds(630, 10, 390, 350);

        jp_thanhToan.setBackground(new java.awt.Color(255, 255, 255));
        jp_thanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đặt bàn - Thanh toán ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12)))); // NOI18N
        jp_thanhToan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_vat.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        label_vat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_vat.setText("0 vnđ");
        jp_thanhToan.add(label_vat, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 130, 20));

        jLabel12.setFont(new java.awt.Font("JetBrains Mono ExtraLight", 2, 12)); // NOI18N
        jLabel12.setText("(Giảm 10% nhân dịp 20/10)");
        jp_thanhToan.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel13.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel13.setText("VAT(6%):");
        jp_thanhToan.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        label_thanhTien.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        label_thanhTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_thanhTien.setText("0 vnđ");
        jp_thanhToan.add(label_thanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 130, 20));

        jLabel14.setFont(new java.awt.Font("JetBrains Mono", 3, 12)); // NOI18N
        jLabel14.setText("Khuyến mãi:");
        jp_thanhToan.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        jLabel15.setText("Tổng thanh toán:");
        jp_thanhToan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 40));

        label_tongThanhToan.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        label_tongThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_tongThanhToan.setText("0 vnđ");
        jp_thanhToan.add(label_tongThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 100, 130, 40));

        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        jLabel16.setText("Thành tiền:");
        jp_thanhToan.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        label_khuyenMai.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        label_khuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_khuyenMai.setText("0 vnđ");
        jp_thanhToan.add(label_khuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 130, -1));

        btn_thoat.setBackground(new java.awt.Color(255, 0, 0));
        btn_thoat.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        btn_thoat.setForeground(new java.awt.Color(255, 255, 255));
        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });
        jp_thanhToan.add(btn_thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 140, 30));

        btn_huyBan.setBackground(new java.awt.Color(255, 153, 51));
        btn_huyBan.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        btn_huyBan.setForeground(new java.awt.Color(255, 255, 255));
        btn_huyBan.setText("Hủy bàn");
        btn_huyBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyBanActionPerformed(evt);
            }
        });
        jp_thanhToan.add(btn_huyBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 140, 30));

        btn_datBan.setBackground(new java.awt.Color(204, 102, 255));
        btn_datBan.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        btn_datBan.setForeground(new java.awt.Color(255, 255, 255));
        btn_datBan.setText("Đặt bàn");
        btn_datBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datBanActionPerformed(evt);
            }
        });
        jp_thanhToan.add(btn_datBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 140, 30));

        btn_thanhToan.setBackground(new java.awt.Color(255, 204, 51));
        btn_thanhToan.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        btn_thanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });
        jp_thanhToan.add(btn_thanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 140, 30));
        jp_thanhToan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 370, -1));

        add(jp_thanhToan);
        jp_thanhToan.setBounds(630, 360, 390, 230);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenKhachHangActionPerformed

    private void btn_timKiemMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemMonAnActionPerformed
        // TODO add your handling code here:
        List<MonAn> listMonAn1 = MonAnDAO.getAllMonAn();
        String tenMon = txt_timKiemMonAn.getText();
        tenMon.toLowerCase();
        MonAn monan = null;

        for (MonAn temp : listMonAn1) {
            if (temp.getTenMonAn().toLowerCase().equals(tenMon)) {
                monan = temp;
            }
        }

        if (monan != null) {
            MonAn monAn = monan;
            jp_thongTinMonAn.removeAll();
            jp_thongTinMonAn.revalidate();
            jp_thongTinMonAn.repaint();

            String tien = currencyFormat.format(monan.getGiaTien());
            JButton btn_monAn = new JButton("<html>" + monan.getTenMonAn() + "<br>(" + tien + ")</html>");

            // Tạo ImageIcon từ đường dẫn file ảnh
            ImageIcon icon = new ImageIcon(monan.getHinhAnh());

            // Lấy ảnh từ ImageIcon và điều chỉnh kích thước để vừa với JLabel
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Hiển thị ảnh trên JLabel
            btn_monAn.setIcon(scaledIcon);
            btn_monAn.setBackground(Color.WHITE);

            btn_monAn.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
            btn_monAn.setFocusPainted(false); // Tắt hiệu ứng focus
            btn_monAn.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N

            // Đặt chữ ở dưới icon
            btn_monAn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn_monAn.setHorizontalTextPosition(SwingConstants.CENTER);
            //action performed
            //TODO something
            //            sự kiện nhấn nút chọn món 
            btn_monAn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Hiển thị hộp thoại nhập số lượng
                    String soLuongStr = JOptionPane.showInputDialog(null, "Nhập số lượng cho món: " + monAn.getTenMonAn(), "Nhập Số Lượng", JOptionPane.PLAIN_MESSAGE);

                    if (soLuongStr != null && !soLuongStr.isEmpty()) {
                        try {
                            int soLuong = Integer.parseInt(soLuongStr); // Chuyển đổi thành số
                            if (soLuong > 0) {
                                boolean monAnDaTonTai = false; // Biến kiểm tra món đã tồn tại

                                // Kiểm tra xem món đã có trong bảng chưa
                                for (int i = 0; i < model_dsMonAn.getRowCount(); i++) {
                                    String tenMonTrongBang = (String) model_dsMonAn.getValueAt(i, 0);
                                    if (tenMonTrongBang.equals(monAn.getTenMonAn())) {
                                        // Món ăn đã tồn tại, cập nhật số lượng
                                        int soLuongCu = (int) model_dsMonAn.getValueAt(i, 1);
                                        model_dsMonAn.setValueAt(soLuongCu + soLuong, i, 1); // Cộng thêm số lượng mới
                                        monAnDaTonTai = true; // Đánh dấu là đã xử lý
                                        break;
                                    }
                                }

                                // Nếu món ăn chưa tồn tại, thêm dòng mới
                                if (!monAnDaTonTai) {
                                    String tien1 = currencyFormat.format(monAn.getGiaTien());
                                    model_dsMonAn.addRow(new Object[]{monAn.getTenMonAn(), soLuong, tien1});
                                }
                                THANH_TIEN += monAn.getGiaTien() * soLuong;
                                vat = THANH_TIEN / 100 * 6;
                                TONG_TIEN = THANH_TIEN + vat;
                            } else {
                                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    label_thanhTien.setText(currencyFormat.format(THANH_TIEN));
                    label_vat.setText(currencyFormat.format(vat));
                    label_tongThanhToan.setText(currencyFormat.format(TONG_TIEN));
                }

            });

            jp_thongTinMonAn.add(btn_monAn);
            jp_thongTinMonAn.revalidate();
            jp_thongTinMonAn.repaint();
        }
    }//GEN-LAST:event_btn_timKiemMonAnActionPerformed

    private void combobox_listMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_listMonAnActionPerformed
        // TODO add your handling code here:
        List<MonAn> listMonAn1 = MonAnDAO.getAllMonAn();
        List<MonAn> listMonAn = new ArrayList<>();
        String danhMuc = combobox_listMonAn.getSelectedItem().toString();
        if (danhMuc.equals("Tất cả")) {
            listMonAn = listMonAn1;
        } else {
            for (MonAn monan : listMonAn1) {
                if (monan.getDanhMuc().getTenDanhMuc().equals(danhMuc)) {
                    listMonAn.add(monan);
                }
            }
        }
        jp_thongTinMonAn.removeAll();
        jp_thongTinMonAn.revalidate();
        jp_thongTinMonAn.repaint();

        for (int i = 0; i < listMonAn.size(); i++) {
            MonAn monAn = listMonAn.get(i);
            String tien = currencyFormat.format(listMonAn.get(i).getGiaTien());
            JButton btn_monAn = new JButton("<html>" + listMonAn.get(i).getTenMonAn() + "<br>(" + tien + ")</html>");

            // Tạo ImageIcon từ đường dẫn file ảnh
            ImageIcon icon = new ImageIcon(listMonAn.get(i).getHinhAnh());

            // Lấy ảnh từ ImageIcon và điều chỉnh kích thước để vừa với JLabel
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã điều chỉnh kích thước
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Hiển thị ảnh trên JLabel
            btn_monAn.setIcon(scaledIcon);
            btn_monAn.setBackground(Color.WHITE);

            btn_monAn.setOpaque(true); // Bắt buộc để màu nền có hiệu lực
            btn_monAn.setFocusPainted(false); // Tắt hiệu ứng focus
            btn_monAn.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14)); // NOI18N

            // Đặt chữ ở dưới icon
            btn_monAn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn_monAn.setHorizontalTextPosition(SwingConstants.CENTER);
            //action performed
            //TODO something
            btn_monAn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Hiển thị hộp thoại nhập số lượng
                    String soLuongStr = JOptionPane.showInputDialog(null, "Nhập số lượng cho món: " + monAn.getTenMonAn(), "Nhập Số Lượng", JOptionPane.PLAIN_MESSAGE);

                    if (soLuongStr != null && !soLuongStr.isEmpty()) {
                        try {
                            int soLuong = Integer.parseInt(soLuongStr); // Chuyển đổi thành số
                            if (soLuong > 0) {
                                boolean monAnDaTonTai = false; // Biến kiểm tra món đã tồn tại

                                // Kiểm tra xem món đã có trong bảng chưa
                                for (int i = 0; i < model_dsMonAn.getRowCount(); i++) {
                                    String tenMonTrongBang = (String) model_dsMonAn.getValueAt(i, 0);
                                    if (tenMonTrongBang.equals(monAn.getTenMonAn())) {
                                        // Món ăn đã tồn tại, cập nhật số lượng
                                        int soLuongCu = (int) model_dsMonAn.getValueAt(i, 1);
                                        model_dsMonAn.setValueAt(soLuongCu + soLuong, i, 1); // Cộng thêm số lượng mới
                                        monAnDaTonTai = true; // Đánh dấu là đã xử lý
                                        break;
                                    }
                                }

                                // Nếu món ăn chưa tồn tại, thêm dòng mới
                                if (!monAnDaTonTai) {
                                    String tien1 = currencyFormat.format(monAn.getGiaTien());
                                    model_dsMonAn.addRow(new Object[]{monAn.getTenMonAn(), soLuong, tien1});
                                }
                                THANH_TIEN += monAn.getGiaTien() * soLuong;
                                vat = THANH_TIEN / 100 * 6;
                                TONG_TIEN = THANH_TIEN + vat;
                            } else {
                                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    label_thanhTien.setText(currencyFormat.format(THANH_TIEN));
                    label_vat.setText(currencyFormat.format(vat));
                    label_tongThanhToan.setText(currencyFormat.format(TONG_TIEN));
                }

            });

            jp_thongTinMonAn.add(btn_monAn);
            jp_thongTinMonAn.revalidate();
            jp_thongTinMonAn.repaint();
        }

    }//GEN-LAST:event_combobox_listMonAnActionPerformed

    private void txt_soDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soDienThoaiActionPerformed

    private void txt_tienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienCocActionPerformed

    private void txt_ghiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ghiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ghiChuActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        
            mainGUI.jp_root_center.removeAll();
            mainGUI.jp_root_center.add(danhSachBan_GUI);
            mainGUI.jp_root_center.revalidate();
            mainGUI.jp_root_center.repaint(); 
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void btn_huyBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_huyBanActionPerformed

    private void btn_datBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datBanActionPerformed
        // TODO add your handling code here:
        // Regex patterns
        String PHONE_REGEX = "^(0)+([0-9]{9})$";
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
        String NUMBER_REGEX = "^[0-9]*\\.?[0-9]+$";

        // Thông tin khách hàng
        String tenKH = txt_tenKhachHang.getText();
        String sdt = txt_soDienThoai.getText();
        String email = txt_email.getText();
        String diaChi = txt_diaChi.getText();
        String maKH = UUID.randomUUID().toString(); // Giả sử hàm tạo mã KH ngẫu nhiên

        // Kiểm tra tính hợp lệ của đầu vào
        if (!sdt.matches(PHONE_REGEX)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            return;
        }
        if (!email.matches(EMAIL_REGEX)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ. Vui lòng nhập lại.");
            return;
        }

        // Tạo đối tượng KhachHang
        KhachHang kh = new KhachHang(maKH, tenKH, email, sdt, diaChi);
        try {
            KhachHangDao.themKhachHang(kh);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm khách hàng.");
            return;
        }

        // Thông tin hóa đơn
        String ghiChu = txt_ghiChu.getText();
        String tienCocStr = txt_tienCoc.getText();

        // Kiểm tra tính hợp lệ của tiền cọc
        if (!tienCocStr.matches(NUMBER_REGEX)) {
            JOptionPane.showMessageDialog(this, "Tiền cọc phải là số hợp lệ.");
            return;
        }
        double tienCoc = Double.parseDouble(tienCocStr);

        // Mã hóa đơn
        String maHD = UUID.randomUUID().toString();
        NhanVien nhanVien = NhanVienDAO.getAllNhanVien().get(0);
        Date date1 = (Date) spinnerNgayGio.getValue();
        LocalDateTime date = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Tạo đối tượng HoaDonThanhToan
        HoaDonThanhToan hoaDon = new HoaDonThanhToan(maHD, banAn, nhanVien, kh, ghiChu, tienCoc, date, EnumTrangThaiDatBan.DA_DAT, tienCoc, null);

        try {
            HoaDonDAO.themHoaDon(hoaDon);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm hóa đơn.");
            return;
        }

        // Thêm chi tiết hóa đơn cho từng món ăn
        for (int i = 0; i < table_dsMonAn.getRowCount(); i++) {
            String tenMon = table_dsMonAn.getValueAt(i, 0).toString();
            int sl = Integer.parseInt(table_dsMonAn.getValueAt(i, 1).toString());
            for (MonAn monan : listMonAn) {
                if (monan.getTenMonAn().equals(tenMon)) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(UUID.randomUUID().toString(), hoaDon, monan, sl);
                    try {
                        ChiTietHoaDonDAO.themChiTietHoaDon(chiTietHoaDon);
                    } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm chi tiết hóa đơn.");
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Đặt bàn thành công!");

    }//GEN-LAST:event_btn_datBanActionPerformed

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_thanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_datBan;
    private javax.swing.JButton btn_huyBan;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton btn_timKiemMonAn;
    private javax.swing.JComboBox<String> combobox_listMonAn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jp_danhSachMonDat;
    private javax.swing.JPanel jp_ngayGio;
    private javax.swing.JPanel jp_thanhToan;
    private javax.swing.JPanel jp_thongTinDatBan;
    private javax.swing.JPanel jp_thongTinKhachHang;
    private javax.swing.JPanel jp_thongTinMonAnRoot;
    private javax.swing.JLabel label_khuyenMai;
    private javax.swing.JLabel label_tenBan;
    private javax.swing.JLabel label_thanhTien;
    private javax.swing.JLabel label_tongThanhToan;
    private javax.swing.JLabel label_vat;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_ghiChu;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_tenKhachHang;
    private javax.swing.JTextField txt_tienCoc;
    private javax.swing.JTextField txt_timKiemMonAn;
    // End of variables declaration//GEN-END:variables
}
