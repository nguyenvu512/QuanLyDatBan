/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class TaiKhoan_DAO {
    
    public static TaiKhoan timKiem(String tenDangNhap){
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from TaiKhoan where tenDangNhap = ?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, tenDangNhap);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return TaiKhoan.builder()
                        .tenDangNhap(tenDangNhap)
                        .matKhau(rs.getString("matKhau"))
//                        .nhanVien(rs.ge)
                        .build();
            }
        } catch (SQLException e) {
            
        }
        return null;
    }
}
