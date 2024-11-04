/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author ACER
 */
@Data // Tự động sinh getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tự động sinh constructor không tham số
@AllArgsConstructor // Tự động sinh constructor có tham số
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) // tự động thêm private trước mỗi thuộc tính
public class TaiKhoan {
    String tenDangNhap;
    String matKhau;
    NhanVien nhanVien;
    Boolean hoatDong;
}
