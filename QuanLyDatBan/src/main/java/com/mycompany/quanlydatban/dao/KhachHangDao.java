/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.DanhMucMonAn;
import com.mycompany.quanlydatban.entity.KhachHang;
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
public class KhachHangDao {
    
    public static List<KhachHang> getAllKhachHang() {
        List<KhachHang> dsF = new ArrayList<>();
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM KhachHang");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maF = rs.getString(1);
                String tenF = rs.getString(2);
                String gia = rs.getString(3);
                String moTa = rs.getString(4);
                String hinhAnh = rs.getString(5);
                KhachHang f = new KhachHang(maF, tenF, gia, moTa, hinhAnh);
                dsF.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsF;
    }
    
    public static boolean themKhachHang(KhachHang f) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("INSERT INTO KhachHang VALUES(?,?,?,?,?)");
            ps.setString(1, f.getMaKhachHang());
            ps.setString(2, f.getTenKhachHang());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getSoDienThoai());
            ps.setString(5, f.getDiaChi());
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
    public static KhachHang timKhachHangTheoMa(String maBan) {
    KhachHang danhSachKhachHang=null;
    java.sql.Connection con = Connection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maBan);
        rs = ps.executeQuery();

        while (rs.next()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang(rs.getString("maKhachHang"));
            khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
            khachHang.setEmail(rs.getString("email"));
            khachHang.setSoDienThoai(rs.getString("soDienThoai"));
            khachHang.setDiaChi(rs.getString("diaChi"));
            // Thêm các thuộc tính khác nếu cần
            
            danhSachKhachHang=khachHang;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Đóng ResultSet và PreparedStatement
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Đóng kết nối
        Connection.closeConnection(con);
    }
    return danhSachKhachHang;
}

}
