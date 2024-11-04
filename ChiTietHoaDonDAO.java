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
    private HoaDonDAO hoadonDao;
    
    public ChiTietHoaDonDAO(){
        hoadonDao = new HoaDonDAO();
    }
    
    public static List<ChiTietHoaDon> getDanhSachChiTietHoaDon(String maHoaDon) {
    List<ChiTietHoaDon> danhSachChiTiet = new ArrayList<>();
    // Truy vấn lấy thông tin chi tiết hóa đơn bao gồm thông tin món ăn
    String sql = "SELECT ct.maChiTietHoaDon, ct.maHoaDon, ct.maMonAn, ct.soLuong, " +
                 "m.tenMonAn, m.giaTien " +
                 "FROM ChiTietHoaDon ct " +
                 "JOIN MonAn m ON ct.maMonAn = m.maMonAn " +
                 "WHERE ct.maHoaDon = ?";

    try (java.sql.Connection con = Connection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, maHoaDon);
        ResultSet rs = ps.executeQuery();

        

        while (rs.next()) {
            ChiTietHoaDon chiTiet = new ChiTietHoaDon();
            
            // Thiết lập thông tin cho ChiTietHoaDon
            chiTiet.setMaChiTietHoaDon(rs.getString("maChiTietHoaDon"));
            
            HoaDonThanhToan hoaDon = HoaDonDAO.timHoaDonTheoMa(rs.getString("maHoaDon"));
            chiTiet.setHoaDon(hoaDon);

            MonAn monAn = new MonAn();
            monAn.setMaMonAn(rs.getString("maMonAn"));
            monAn.setTenMonAn(rs.getString("tenMonAn"));
            monAn.setGiaTien(rs.getDouble("giaTien"));
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
        try (java.sql.Connection con = Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
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

    // Cập nhật chi tiết hóa đơn
    public static boolean capNhatChiTietHoaDon(ChiTietHoaDon chiTiet) {
        String sql = "UPDATE ChiTietHoaDon SET maHoaDon = ?, maMonAn = ?, soLuong = ? WHERE maChiTietHoaDon = ?";
        try (java.sql.Connection con = Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, chiTiet.getHoaDon().getMaHoaDon());
            ps.setString(2, chiTiet.getMonAn().getMaMonAn());
            ps.setInt(3, chiTiet.getSoLuong());
            ps.setString(4, chiTiet.getMaChiTietHoaDon());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
