/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.BanAn;
import com.mycompany.quanlydatban.entity.EnumTrangThaiDatBan;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import com.mycompany.quanlydatban.entity.KhachHang;
import com.mycompany.quanlydatban.entity.KhuyenMai;
import com.mycompany.quanlydatban.entity.MonAn;
import com.mycompany.quanlydatban.entity.NhanVien;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class HoaDonDAO {

    public static List<HoaDonThanhToan> getHoaDonTheoMaBanNgayTrangThai(String maBan, Date date) {
        List<HoaDonThanhToan> danhSachHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonThanhToan WHERE maBan = ? AND CAST(ngayDatBan AS DATE) = ? AND trangThai = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị tham số cho câu lệnh SQL
            ps.setString(1, maBan);
            ps.setDate(2, date);
            ps.setString(3, EnumTrangThaiDatBan.DA_DAT.toString()); // Điều kiện trạng thái là DA_DAT

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonThanhToan hoaDon = new HoaDonThanhToan();

                    hoaDon.setMaHoaDon(rs.getString("maHoaDon"));

                    // Thiết lập các đối tượng liên quan
                    hoaDon.setBanAn(BanAnDAO.tim(rs.getString("maBan")));
                    hoaDon.setNhanVien(NhanVienDAO.tim(rs.getString("maNhanVien")));
                    hoaDon.setKhachHang(KhachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")));

                    hoaDon.setGhiChu(rs.getString("ghiChu"));
                    hoaDon.setTienCoc(rs.getDouble("tienCoc"));

                    // Lấy và chuyển đổi ngày đặt bàn
                    Timestamp timestamp = rs.getTimestamp("ngayDatBan");
                    hoaDon.setNgayDatBan(timestamp != null ? timestamp.toLocalDateTime() : null);

                    hoaDon.setTrangThai(EnumTrangThaiDatBan.valueOf(rs.getString("trangThai")));
                    hoaDon.setTongTien(rs.getDouble("tongTien"));

                    KhuyenMai khuyenMai = KhuyenMaiDao.tim(rs.getString("maKhuyenMai"));
                    hoaDon.setKhuyenMai(khuyenMai);

                    danhSachHoaDon.add(hoaDon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }

    public static List<HoaDonThanhToan> getHoaDonTheoMaBanVaNgay(String maBan, Date date) {
        List<HoaDonThanhToan> danhSachHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonThanhToan WHERE maBan = ? AND CAST(ngayDatBan AS DATE) = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị tham số cho câu lệnh SQL
            ps.setString(1, maBan);
            ps.setDate(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonThanhToan hoaDon = new HoaDonThanhToan();

                    hoaDon.setMaHoaDon(rs.getString("maHoaDon"));

                    // Thiết lập các đối tượng liên quan
                    BanAn banAn = new BanAn();
                    hoaDon.setBanAn(BanAnDAO.tim(rs.getString("maBan")));

                    NhanVien nhanVien = new NhanVien();
                    hoaDon.setNhanVien(NhanVienDAO.tim(rs.getString("maNhanVien")));

                    KhachHang khachHang = new KhachHang();
                    hoaDon.setKhachHang(KhachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")));

                    hoaDon.setGhiChu(rs.getString("ghiChu"));
                    hoaDon.setTienCoc(rs.getDouble("tienCoc"));

                    // Lấy và chuyển đổi ngày đặt bàn
                    Timestamp timestamp = rs.getTimestamp("ngayDatBan");
                    hoaDon.setNgayDatBan(timestamp != null ? timestamp.toLocalDateTime() : null);

                    hoaDon.setTrangThai(EnumTrangThaiDatBan.valueOf(rs.getString("trangThai")));
                    hoaDon.setTongTien(rs.getDouble("tongTien"));

                    KhuyenMai khuyenMai = KhuyenMaiDao.tim(rs.getString("maKhuyenMai"));
                    hoaDon.setKhuyenMai(khuyenMai);

                    danhSachHoaDon.add(hoaDon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    
    public Map<Integer,Double> doanhThuTheoThang()
   {
       Map<Integer,Double> map = new HashMap<>();
       java.sql.Connection con = Connection.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null ;
       
       try {
           ps = con.prepareStatement("SELECT " +
                     "    MONTH(ngayDatBan) AS Thang, " +
                     "    SUM( " +
                     "        CASE " +
                     "            WHEN trangThai = 'DA_THANH_TOAN' THEN tongTien " +
                     "            WHEN trangThai = 'DA_HUY' THEN tienCoc " +
                     "            ELSE 0 " +
                     "        END " +
                     "    ) AS DoanhThu " +
                     "FROM HoaDonThanhToan " +
                     "WHERE YEAR(ngayDatBan) = YEAR(GETDATE()) " +
                     "GROUP BY MONTH(ngayDatBan) " +
                     "ORDER BY Thang");
          rs= ps.executeQuery();
          while(rs.next())
          {
              int month = rs.getInt("thang");
              Double tong = rs.getDouble("doanhthu");
              map.put(month, tong);       
          }
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               if(rs!= null)
                   rs.close();
               if(con!=null)
                   con.close();
               if(ps!=null)
                   ps.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return map;            
   }
   
   public Map<Integer,Double> doanhThuTheoNam()
   {
       Map<Integer,Double> map = new HashMap<>();
       java.sql.Connection con = Connection.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null ;
       String sql = "SELECT YEAR(ngayDatBan) AS nam, "
                   + "SUM(CASE WHEN trangThai = 'DA_THANH_TOAN' THEN tongTien "
                   + "WHEN trangThai = 'DA_HUY' THEN tienCoc "
                   + "ELSE 0 END) AS doanh_Thu "
                   + "FROM HoaDonThanhToan "
                   + "GROUP BY YEAR(ngayDatBan) "
                   + "ORDER BY nam";
       try {
           ps = con.prepareStatement(sql);
          rs= ps.executeQuery();
          while(rs.next())
          {
              int year = rs.getInt("nam");
              Double tong = rs.getDouble("doanh_thu");
              map.put(year, tong);       
          }
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               if(rs!= null)
                   rs.close();
               if(con!=null)
                   con.close();
               if(ps!=null)
                   ps.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return map;            
   }

    public static HoaDonThanhToan timHoaDonTheoMa(String maHoaDon) {
        HoaDonThanhToan hoaDon = null;
        String sql = "SELECT * FROM HoaDonThanhToan WHERE maHoaDon = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hoaDon = new HoaDonThanhToan();
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));

                // Giả sử bạn đã có các phương thức để lấy BanAn, NhanVien, KhachHang, KhuyenMai từ mã
                BanAn banAn = BanAnDAO.tim(rs.getString("maBan")); // Cần thay thế bằng cách lấy bàn thực tế
                hoaDon.setBanAn(banAn);

                NhanVien nhanVien = NhanVienDAO.tim(rs.getString("maNhanVien")); // Cần thay thế bằng cách lấy nhân viên thực tế
                hoaDon.setNhanVien(nhanVien);

                KhachHang khachHang = KhachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")); // Cần thay thế bằng cách lấy khách hàng thực tế
                hoaDon.setKhachHang(khachHang);

                hoaDon.setGhiChu(rs.getString("ghiChu"));
                hoaDon.setTienCoc(rs.getDouble("tienCoc"));
                hoaDon.setNgayDatBan(rs.getTimestamp("ngayDatBan").toLocalDateTime()); // Chuyển từ Timestamp sang LocalDateTime
                hoaDon.setTrangThai(EnumTrangThaiDatBan.valueOf(rs.getString("trangThai"))); // Giả sử TrangThai là enum
                hoaDon.setTongTien(rs.getDouble("tongTien"));

                // Nếu có Khuyến mãi
                KhuyenMai khuyenMai = KhuyenMaiDao.tim(rs.getString("maKhuyenMai"));
                    hoaDon.setKhuyenMai(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }

    public static boolean suaTrangThaiHoaDon(String maHoaDon, EnumTrangThaiDatBan trangThaiMoi) {
        String sql = "UPDATE HoaDonThanhToan SET trangThai = ? WHERE maHoaDon = ?";
        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị tham số cho câu lệnh SQL
            ps.setString(1, trangThaiMoi.toString());
            ps.setString(2, maHoaDon);

            // Thực hiện lệnh cập nhật và trả về kết quả
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean themHoaDon(HoaDonThanhToan f) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("INSERT INTO HoaDonThanhToan VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, f.getMaHoaDon());
            ps.setString(2, f.getBanAn().getMaBan());
            ps.setString(3, f.getNhanVien().getMaNhanVien());
            ps.setString(4, f.getKhachHang().getMaKhachHang());
            ps.setString(5, f.getGhiChu());
            ps.setDouble(6, f.getTienCoc());
            ps.setTimestamp(7, Timestamp.valueOf(f.getNgayDatBan())); // Chuyển LocalDateTime sang Timestamp cho cột dateTime
            ps.setString(8, f.getTrangThai().toString());
            ps.setDouble(9, f.getTongTien());
            ps.setDouble(10, f.getVAT());
            ps.setString(11, f.getKhuyenMai() != null ? f.getKhuyenMai().getMaKhuyenMai() : null);

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public static boolean suaHoaDon(HoaDonThanhToan f) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            String sql = "UPDATE HoaDonThanhToan SET maBan = ?, maNhanVien = ?, maKhachHang = ?, ghiChu = ?, "
                    + "tienCoc = ?, ngayDatBan = ?, trangThai = ?, tongTien = ?, maKhuyenMai = ? "
                    + "WHERE maHoaDon = ?";
            ps = con.prepareStatement(sql);

            // Thiết lập giá trị cho các trường
            ps.setString(1, f.getBanAn().getMaBan());
            ps.setString(2, f.getNhanVien().getMaNhanVien());
            ps.setString(3, f.getKhachHang().getMaKhachHang());
            ps.setString(4, f.getGhiChu());
            ps.setDouble(5, f.getTienCoc());
            ps.setTimestamp(6, Timestamp.valueOf(f.getNgayDatBan())); // Chuyển LocalDateTime sang Timestamp
            ps.setString(7, f.getTrangThai().toString());
            ps.setDouble(8, f.getTongTien());
            ps.setString(9, f.getKhuyenMai() != null ? f.getKhuyenMai().getMaKhuyenMai() : null);

            // Thiết lập mã hóa đơn cho câu lệnh WHERE
            ps.setString(10, f.getMaHoaDon());

            // Thực thi lệnh
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    // Phương thức lấy danh sách hóa đơn
    public static List<HoaDonThanhToan> getDanhSachHoaDon() {
        List<HoaDonThanhToan> danhSachHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonThanhToan";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonThanhToan hoaDon = new HoaDonThanhToan();

                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));

                // Giả định BanAn, NhanVien, KhachHang, KhuyenMai có phương thức setMaBan, setMaNhanVien, setMaKhachHang
                BanAn banAn = new BanAn();
                banAn.setMaBan(rs.getString("maBan"));
                hoaDon.setBanAn(banAn);

                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(rs.getString("maNhanVien"));
                hoaDon.setNhanVien(nhanVien);

                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getString("maKhachHang"));
                hoaDon.setKhachHang(khachHang);

                hoaDon.setGhiChu(rs.getString("ghiChu"));
                hoaDon.setTienCoc(rs.getDouble("tienCoc"));

                Timestamp timestamp = rs.getTimestamp("ngayDatBan");
                hoaDon.setNgayDatBan(timestamp != null ? timestamp.toLocalDateTime() : null);

                hoaDon.setTrangThai(EnumTrangThaiDatBan.valueOf(rs.getString("trangThai")));
                hoaDon.setTongTien(rs.getDouble("tongTien"));

                KhuyenMai khuyenMai = KhuyenMaiDao.tim(rs.getString("maKhuyenMai"));
                    hoaDon.setKhuyenMai(khuyenMai);

                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    
    public void capNhatTrangThaiHoaDon(String maBan,String maHoaDon) throws SQLException{
        String sqlUpdateHoaDon = "UPDATE HoaDonThanhToan SET trangThai = ? WHERE maHoaDon = ?";
        String sqlUpdateBan = "UPDATE BanAn SET trangThai = ? WHERE maBan = ?";
          try (java.sql.Connection con = Connection.getConnection()) {
            con.setAutoCommit(false); // Bắt đầu giao dịch

            // Cập nhật trạng thái hóa đơn
            try (PreparedStatement psHoaDon = con.prepareStatement(sqlUpdateHoaDon)) {
                psHoaDon.setString(1, "DA_THANH_TOAN"); // Cập nhật trạng thái thành "Đã thanh toán"
                psHoaDon.setString(2, maHoaDon);
                psHoaDon.executeUpdate();
            }

            // Cập nhật trạng thái bàn
            try (PreparedStatement psBan = con.prepareStatement(sqlUpdateBan)) {
                psBan.setString(1, "Trống"); // Cập nhật trạng thái bàn thành "Trống"
                psBan.setString(2, maBan);
                psBan.executeUpdate();
            }

            con.commit(); // Xác nhận giao dịch
        } catch (SQLException e) {
            e.printStackTrace();
            // Nếu có lỗi xảy ra, rollback
//            try {
//                if (con != null) {
//                    con.rollback();
//                }
//            } catch (SQLException rollbackEx) {
//                rollbackEx.printStackTrace();
//            }
        }
          
    
    }

    public boolean updateInvoice(HoaDonThanhToan hoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
