/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.EnumChucVu;
import com.mycompany.quanlydatban.entity.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Vo Van Tuong
 */
public class NhanVienDAO {
    public NhanVienDAO(){
    }
    public static List<NhanVien> getAllNhanVien() {
      List<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            java.sql.Connection con = Connection.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("SELECT * FROM NhanVien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String email = rs.getString(3);
                String sdt = rs.getString(4);
                String dc = rs.getString(5);
                boolean gt = rs.getBoolean(6);
                LocalDate ns = rs.getDate(7).toLocalDate();
                String cv = rs.getString(8);
                EnumChucVu chucVu = EnumChucVu.valueOf(cv);
                NhanVien nv = new NhanVien(maNV, tenNV, email, sdt, dc, gt, ns, chucVu);
                dsNhanVien.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsNhanVien;
    }

    public boolean themNV(NhanVien nv) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("INSERT INTO NhanVien VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTenNhanVien());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getDiaChi());
            ps.setBoolean(6, nv.getGioiTinh());
            ps.setString(7, nv.getNgaySinh().toString());
            ps.setString(8, nv.getChucVu().toString());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public boolean suaNV(NhanVien nv) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("UPDATE NhanVien SET tenNhanVien=?, email=?, soDienThoai=?, diaChi=?, gioiTinh=?, ngaySinh=?, chucVu=? WHERE maNhanVien=?");
            ps.setString(8, nv.getMaNhanVien());
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getEmail());
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getDiaChi());
            ps.setBoolean(5, nv.getGioiTinh());
            ps.setString(6, nv.getNgaySinh().toString());
            ps.setString(7, nv.getChucVu().toString());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public boolean xoaNV(String maNV) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("DELETE FROM NhanVien WHERE maNhanVien=?");
            ps.setString(1, maNV);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public static NhanVien tim(String maNV) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        NhanVien nv = null;
        try {
            ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien=?");
            ps.setString(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tenNV = rs.getString(2);
                String email = rs.getString(3);
                String sdt = rs.getString(4);
                String dc = rs.getString(5);
                boolean gt = rs.getBoolean(6);
                LocalDate ns = rs.getDate(7).toLocalDate();
                String cv = rs.getString(8);
                EnumChucVu chucVu = EnumChucVu.valueOf(cv);
                nv = new NhanVien(maNV, tenNV, email, sdt, dc, gt, ns, chucVu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nv;
    }

    
}
