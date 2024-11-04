/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.DanhMucMonAn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class DanhMucMonAnDAO {

    public static List<DanhMucMonAn> getDSLoaiSP() {
        List<DanhMucMonAn> listDanhMuc = new ArrayList<>();
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from DanhMucMonAn;";
            PreparedStatement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ten = rs.getString("tenDanhMuc");
                String ma = rs.getString("maDanhMuc");
                listDanhMuc.add(DanhMucMonAn.builder()
                        .maDanhMuc(ma)
                        .tenDanhMuc(ten)
                        .build()
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listDanhMuc;
    }

    public static DanhMucMonAn getByMaDanhMuc(String ma) {
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "select * from DanhMucMonAn where maDanhMuc=?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, ma);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("tenDanhMuc");
                String ma1 = rs.getString("maDanhMuc");
                return DanhMucMonAn.builder()
                        .maDanhMuc(ma1)
                        .tenDanhMuc(ten)
                        .build();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    public static DanhMucMonAn getByTenDanhMuc(String ten) {
        try {
            java.sql.Connection c = Connection.getConnection();
            String sql = "SELECT * FROM DanhMucMonAn WHERE tenDanhMuc = ?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, ten);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String maDanhMuc = rs.getString("maDanhMuc");
                String tenDanhMuc = rs.getString("tenDanhMuc");
                return DanhMucMonAn.builder()
                        .maDanhMuc(maDanhMuc)
                        .tenDanhMuc(tenDanhMuc)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
