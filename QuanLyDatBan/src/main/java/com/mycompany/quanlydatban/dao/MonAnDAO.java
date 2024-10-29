/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.DanhMucMonAn;
import com.mycompany.quanlydatban.entity.MonAn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhuy0
 */
public class MonAnDAO {

    public static List<MonAn> getAllMonAn() {
        List<MonAn> dsF = new ArrayList<>();
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM MonAn");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maF = rs.getString(1);
                String tenF = rs.getString(2);
                double gia = rs.getDouble(3);
                String moTa = rs.getString(4);
                String hinhAnh = rs.getString(5);
                DanhMucMonAn danhMuc = DanhMucMonAnDAO.getByMaDanhMuc(rs.getString(6));
                MonAn f = new MonAn(maF, tenF, gia, moTa, hinhAnh, danhMuc);
                dsF.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsF;
    }

    public static boolean themMonAn(MonAn f) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("INSERT INTO MonAn VALUES(?,?,?,?,?,?)");
            ps.setString(1, f.getMaMonAn());
            ps.setString(2, f.getTenMonAn());
            ps.setDouble(3, f.getGiaTien());
            ps.setString(4, f.getMoTa());
            ps.setString(5, f.getHinhAnh());
            ps.setString(6, f.getDanhMuc().getMaDanhMuc());
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

    public boolean suaMonAn(MonAn f) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("UPDATE MonAn SET tenMonAn=?, giaTien=?, moTa=?, hinhAnh=?, maDanhMuc=? WHERE maMonAn=?");
            ps.setString(6, f.getMaMonAn());
            ps.setString(1, f.getTenMonAn());
            ps.setDouble(2, f.getGiaTien());
            ps.setString(3, f.getMoTa());
            ps.setString(4, f.getHinhAnh());
            ps.setString(5, f.getDanhMuc().getMaDanhMuc());
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

    public boolean xoaMonAn(String maF) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("DELETE FROM MonAn WHERE maMonAn=?");
            ps.setString(1, maF);
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
    
    public static MonAn timMonAn(String maF){
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        MonAn f = null;
        try {
            ps = con.prepareStatement("SELECT * FROM MonAn WHERE maMonAn=?");
            ps.setString(1, maF);
            rs = ps.executeQuery();
            while(rs.next()){
                String tenMonAn= rs.getString(2);
                double gia = rs.getDouble(3);
                String moTa = rs.getString(4);
                String hinhAnh = rs.getString(5);
                DanhMucMonAn danhMuc = DanhMucMonAnDAO.getByMaDanhMuc(rs.getString(6));
                f = new MonAn(maF, tenMonAn, gia, moTa, hinhAnh, danhMuc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
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
        return f;
    }
    
    public static MonAn timMonAnByTenMon(String maF){
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        MonAn f = null;
        try {
            ps = con.prepareStatement("SELECT * FROM MonAn WHERE tenMonAn=?");
            ps.setString(1, maF);
            rs = ps.executeQuery();
            while(rs.next()){
                String tenMonAn= rs.getString(2);
                double gia = rs.getDouble(3);
                String moTa = rs.getString(4);
                String hinhAnh = rs.getString(5);
                DanhMucMonAn danhMuc = DanhMucMonAnDAO.getByMaDanhMuc(rs.getString(6));
                f = new MonAn(maF, tenMonAn, gia, moTa, hinhAnh, danhMuc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
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
        return f;
    }

}
