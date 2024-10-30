/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.KhuyenMai;
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
public class KhuyenMaiDao {
    public KhuyenMaiDao (){
        
    }
    
    public List<KhuyenMai> getAllKhuyenMai()
    {
        List<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            java.sql.Connection con = Connection.getConnection();
            PreparedStatement ps= null;
            ps = con.prepareStatement("SELECT * FROM KhuyenMai");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String maKM = rs.getString(1);
                String teKM = rs.getString(2);
                LocalDate ngayBD = rs.getDate(3).toLocalDate();
                LocalDate ngayKT= rs.getDate(4).toLocalDate();
                Double mucGiam = rs.getDouble(5);
                KhuyenMai KM = new KhuyenMai(maKM, teKM, ngayBD, ngayKT, mucGiam);
                dsKhuyenMai.add(KM);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }
    
    public boolean them (KhuyenMai km)
    {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n=0;
        try {
            ps = con.prepareStatement("INSERT INTO KhuyenMai VALUES(?,?,?,?,?)");
            ps.setString(1, km.getMaKhuyenMai());
            ps.setString(2, km.getTenKhuyenMai());
            ps.setString(3, km.getNgayBatDau().toString());
            ps.setString(4,km.getNgayKetThuc().toString());
            ps.setDouble(5, km.getGiamGia());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
    public boolean suaKM(KhuyenMai Km)
    {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n=0;
        try {
            ps = con.prepareStatement("UPDATE KhuyenMai SET tenKhuyenMai=? ,ngayBatDau = ? , ngayketThuc = ?, giamGia=? WHERE maKhuyenMai=?");
            ps.setString(1, Km.getTenKhuyenMai());
            ps.setString(2, Km.getNgayBatDau().toString());
            ps.setString(3, Km.getNgayKetThuc().toString());
            ps.setDouble(4, Km.getGiamGia());
            ps.setString(5, Km.getMaKhuyenMai());
            n= ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
     return n>0;   
    }
    
    public KhuyenMai tim(String maKM){
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs= null;
        KhuyenMai km = null;
        try {
            ps = con.prepareStatement("SELECT * FROM KhuyenMai WHERE maKhuyenMai=?");
            ps.setString(1, maKM);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String tenKhuyenMai = rs.getString(2);
                LocalDate nBD = rs.getDate(3).toLocalDate();
                LocalDate nKT = rs.getDate(4).toLocalDate();
                Double mucGiam = rs.getDouble(5);
                km = new KhuyenMai(maKM, tenKhuyenMai, nBD, nKT, mucGiam);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return km;
    }
        
}
