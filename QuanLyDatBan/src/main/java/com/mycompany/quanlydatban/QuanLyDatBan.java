/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.quanlydatban;

import com.mycompany.quanlydatban.GUI.DangNhap_GUI;
import com.mycompany.quanlydatban.GUI.DanhSachBan_GUI;
import com.mycompany.quanlydatban.GUI.MainGUI;
import com.mycompany.quanlydatban.dao.BanAnDAO;
import com.mycompany.quanlydatban.dao.ChiTietHoaDonDAO;
import com.mycompany.quanlydatban.dao.DanhMucMonAnDAO;
import com.mycompany.quanlydatban.dao.HoaDonDAO;
import com.mycompany.quanlydatban.dao.KhachHangDao;
import com.mycompany.quanlydatban.dao.MonAnDAO;
import com.mycompany.quanlydatban.dao.NhanVienDAO;
import com.mycompany.quanlydatban.dao.TaiKhoanDAO;
import com.mycompany.quanlydatban.entity.BanAn;
import com.mycompany.quanlydatban.entity.ChiTietHoaDon;
import com.mycompany.quanlydatban.entity.EnumTrangThaiDatBan;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import com.mycompany.quanlydatban.entity.KhachHang;
import com.mycompany.quanlydatban.entity.MonAn;
import com.mycompany.quanlydatban.entity.NhanVien;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author ACER
 */
//tự động chỉnh format code: alt + shift + f
public class QuanLyDatBan {

    public static void main(String[] args) throws SQLException {
//        KhachHangDao.themKhachHang(new KhachHang("KH001", "Trần Nguyên Vũ", "vu5122003@gmail.com", "039292292", "58 Nguyễn Du"));
        
//        BanAn ban = BanAnDAO.getAllBan().get(0);
//        KhachHang kh = KhachHangDao.getAllKhachHang().get(0);
//        NhanVien nv = NhanVienDAO.getAllNhanVien().get(0);
        
    //    MonAn monan = MonAnDAO.getAllMonAn().get(0);
//        HoaDonDAO.themHoaDon(new HoaDonThanhToan("abc123", ban, nv, kh, "", 500000,LocalDateTime.now(), EnumTrangThaiDatBan.DA_DAT,1000000, null));
        new MainGUI();
//        System.out.println(HoaDonDAO.timHoaDonTheoMa("25f72995-4206-4eb3-87d7-73e5f47a5b52"));
//        new DangNhap_GUI();
//        ChiTietHoaDonDAO.themChiTietHoaDon(new ChiTietHoaDon("2i29eww", HoaDonDAO.getDanhSachHoaDon().get(0),monan, 1 ));
    }
}
