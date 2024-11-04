/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.ChiTietHoaDon;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import com.mycompany.quanlydatban.entity.MonAn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ChiTietHoaDonDAO {

    public static List<ChiTietHoaDon> getDanhSachChiTietHoaDon(String maHoaDon) {
        List<ChiTietHoaDon> danhSachChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                chiTiet.setMaChiTietHoaDon(rs.getString("maChiTietHoaDon"));

                // Giả sử bạn đã có phương thức để lấy HoaDon và MonAn từ maHoaDon và maMonAn
                HoaDonThanhToan hoaDon = HoaDonDAO.timHoaDonTheoMa(rs.getString("maHoaDon")); // Cần thay thế bằng cách lấy hóa đơn thực tế
                chiTiet.setHoaDon(hoaDon);

                MonAn monAn = MonAnDAO.timMonAn(rs.getString("maMonAn"));
                chiTiet.setMonAn(monAn);

                chiTiet.setSoLuong(rs.getInt("soLuong"));

                // Thêm chi tiết hóa đơn vào danh sách
                danhSachChiTiet.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachChiTiet;
    }

    // Thêm chi tiết hóa đơn
    public static boolean themChiTietHoaDon(ChiTietHoaDon chiTiet) {
        String sql = "INSERT INTO ChiTietHoaDon (maChiTietHoaDon, maHoaDon, maMonAn, soLuong) VALUES (?, ?, ?, ?)";
        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, chiTiet.getMaChiTietHoaDon());
            ps.setString(2, chiTiet.getHoaDon().getMaHoaDon());
            ps.setString(3, chiTiet.getMonAn().getMaMonAn());
            ps.setInt(4, chiTiet.getSoLuong());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean xoaChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số trong câu lệnh SQL
            ps.setString(1, maHoaDon);

            // Thực thi câu lệnh xóa và kiểm tra kết quả
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean suaChiTietHoaDon(ChiTietHoaDon chiTiet) {
        String sql = "UPDATE ChiTietHoaDon SET maHoaDon = ?, maMonAn = ?, soLuong = ? WHERE maChiTietHoaDon = ?";

        try (java.sql.Connection con = Connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            ps.setString(1, chiTiet.getHoaDon().getMaHoaDon());
            ps.setString(2, chiTiet.getMonAn().getMaMonAn());
            ps.setInt(3, chiTiet.getSoLuong());
            ps.setString(4, chiTiet.getMaChiTietHoaDon()); // Điều kiện WHERE để xác định chi tiết hóa đơn cần sửa

            // Thực thi lệnh cập nhật và trả về true nếu cập nhật thành công
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
