/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.dao;

import com.mycompany.quanlydatban.entity.BanAn;
import com.mycompany.quanlydatban.entity.EnumTrangThaiBan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhuy0
 */
public class BanAnDAO {

    public BanAnDAO() {

    }

    public List<BanAn> getAllBan() {
        List<BanAn> dsBan = new ArrayList<>();
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM BanAn");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString(1);
                int sl = rs.getInt(2);
                String trangThai = rs.getString(3);
                EnumTrangThaiBan t = EnumTrangThaiBan.valueOf(trangThai);
                String ghiChu = rs.getString(4);
                BanAn ban = new BanAn(maBan, sl, t, ghiChu);
                dsBan.add(ban);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsBan;

    }

    public boolean themBan(BanAn ban) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("INSERT INTO BanAn VALUES (?,?,?,?)");
            ps.setString(1, ban.getMaBan());
            ps.setInt(2, ban.getSoLuongGhe());
            ps.setString(3, ban.getTrangThai().toString());
            ps.setString(4, ban.getGhiChu());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Connection.closeConnection(con);
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return n > 0;
    }

    public boolean suaBan(BanAn ban) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("UPDATE BanAn SET soLuongGhe=?, trangThai=?, ghiChu=? WHERE maBan=?");
            ps.setString(4, ban.getMaBan());
            ps.setInt(1, ban.getSoLuongGhe());
            ps.setString(2, ban.getTrangThai().toString());
            ps.setString(3, ban.getGhiChu());
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

    public boolean xoaBan(String maBan) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("DELETE FROM BanAn WHERE maBan=?");
            ps.setString(1, maBan);
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

    public BanAn tim(String maBan) {
        java.sql.Connection con = Connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        BanAn ban = null;
        try {
            ps = con.prepareStatement("SELECT * FROM BanAn WHERE maBan=?");
            ps.setString(1, maBan);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sl = rs.getInt(2);
                String trangThai = rs.getString(3);
                EnumTrangThaiBan t = EnumTrangThaiBan.valueOf(trangThai);
                String ghiChu = rs.getString(4);
                ban = new BanAn(maBan, sl, t, ghiChu);
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
        return ban;
    }

}
