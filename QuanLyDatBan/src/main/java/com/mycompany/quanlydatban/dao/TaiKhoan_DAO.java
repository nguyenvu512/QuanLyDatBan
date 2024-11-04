/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.NhanVien;
import com.mycompany.quanlydatban.entity.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class TaiKhoan_DAO {
    
    public static boolean taoTaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien) {
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, maNhanVien, hoatDong) VALUES (?, ?, ?, ?)";
            PreparedStatement st = c.prepareStatement(sql);

            // Thiết lập các giá trị cho câu lệnh SQL
            st.setString(1, tenDangNhap);
            st.setString(2, matKhau);
            st.setString(3, nhanVien.getMaNhanVien()); // Giả sử NhanVien có phương thức getMaNhanVien()
            st.setBoolean(4, true); // Mặc định hoatDong là true

            // Thực thi câu lệnh INSERT
            int rowsInserted = st.executeUpdate();

            // Kiểm tra xem có thêm dòng nào vào bảng không
            return rowsInserted > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để dễ debug
        }
        return false; // Trả về false nếu có lỗi
}
    
    public static boolean xoaTaiKhoan(String tenDangNhap) {
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "update TaiKhoan set hoatDong = 0 where tenDangNhap = ?";
            PreparedStatement st = c.prepareStatement(sql);

            // Thiết lập các giá trị cho câu lệnh SQL
            st.setString(1, tenDangNhap);
            int rowsInserted = st.executeUpdate();

            // Kiểm tra xem có thêm dòng nào vào bảng không
            return rowsInserted > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console để dễ debug
        }
        return false; // Trả về false nếu có lỗi
}

    
    public static TaiKhoan timKiem(String tenDangNhap){
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from TaiKhoan where tenDangNhap = ? and hoatDong = 1";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, tenDangNhap);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanVien nhanVien1 = NhanVienDAO.tim(rs.getString("maNhanVien"));
                return TaiKhoan.builder()
                        .tenDangNhap(tenDangNhap)
                        .matKhau(rs.getString("matKhau"))
                        .nhanVien(nhanVien1)
                        .hoatDong(rs.getBoolean("hoatDong"))
                        .build();
            }
        } catch (SQLException e) {
            
        }
        return null;
    }
    
    public static TaiKhoan timKiemByTenDangNhap(String tenDangNhap){
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from TaiKhoan where tenDangNhap = ? ";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, tenDangNhap);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanVien nhanVien1 = NhanVienDAO.tim(rs.getString("maNhanVien"));
                return TaiKhoan.builder()
                        .tenDangNhap(tenDangNhap)
                        .matKhau(rs.getString("matKhau"))
                        .nhanVien(nhanVien1)
                        .hoatDong(rs.getBoolean("hoatDong"))
                        .build();
            }
        } catch (SQLException e) {
            
        }
        return null;
    }
    
    public static boolean doiMatKhau(String tenDangNhap, String matKhauMoi) {
    try {
        java.sql.Connection c = Connection.getConnection();
        String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE tenDangNhap = ? and hoatDong = 1";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, matKhauMoi); // Lưu ý: hãy mã hóa mật khẩu trước khi lưu vào CSDL, ví dụ bằng BCrypt
        st.setString(2, tenDangNhap);
        
        int rowsUpdated = st.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi xảy ra
}

    
    public static TaiKhoan timKiemByMaNV(String maNV){
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from TaiKhoan where maNhanVien = ? and hoatDong=1";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, maNV);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanVien nhanVien1 = NhanVienDAO.tim(rs.getString("maNhanVien"));
                return TaiKhoan.builder()
                        .tenDangNhap(rs.getString("tenDangNhap"))
                        .matKhau(rs.getString("matKhau"))
                        .nhanVien(nhanVien1)
                        .hoatDong(rs.getBoolean("hoatDong"))
                        .build();
            }
        } catch (SQLException e) {
            
        }
        return null;
    }
}
