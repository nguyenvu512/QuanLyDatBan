/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.quanlydatban.dao.ChiTietHoaDonDAO;
import com.mycompany.quanlydatban.entity.ChiTietHoaDon;
import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import com.mycompany.quanlydatban.entity.HoaDonThanhToan;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ACER
 */
public class HoaDonPDFExporter {

    public static void xuatHoaDonPDF(HoaDonThanhToan hoaDon) {
        Document document = new Document();

        // Tạo thư mục "HoaDon" nếu chưa tồn tại
        String folderPath = "HoaDon";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Đường dẫn file trong thư mục "HoaDon"
        String filePath = folderPath + "/HoaDon_" + hoaDon.getMaHoaDon().substring(25) + ".pdf";

        // Định dạng số tiền
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Định dạng font JetBrains Mono từ file .ttf
            String fontPath = "D:\\PTUD\\QuanLyDatBan\\QuanLyDatBan\\QuanLyDatBan\\JetBrainsMono-2.304\\fonts\\ttf\\JetBrainsMono-Regular.ttf";
            BaseFont jetBrainsMonoBase = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTitle = new Font(jetBrainsMonoBase, 18, Font.BOLD);
            Font fontSubTitle = new Font(jetBrainsMonoBase, 14, Font.NORMAL);
            Font fontNormal = new Font(jetBrainsMonoBase, 12, Font.NORMAL);

            // Thông tin Nhà hàng
            Paragraph restaurantName = new Paragraph("NHÀ HÀNG ĐỒNG XANH", fontTitle);
            restaurantName.setAlignment(Element.ALIGN_CENTER);
            document.add(restaurantName);

            Paragraph address = new Paragraph("Địa chỉ: 69 Phạm Ngũ Lão, Quận 1, TP Hồ Chí Minh", fontSubTitle);
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            // Dòng trống
            document.add(new Paragraph("\n"));

            // Tiêu đề hóa đơn
            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Định dạng ngày tháng
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Thông tin hóa đơn
            document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHoaDon().substring(25), fontNormal));
            document.add(new Paragraph("Ngày đặt bàn: " + hoaDon.getNgayDatBan().format(dateTimeFormatter), fontNormal));
            document.add(new Paragraph("Bàn: " + hoaDon.getBanAn().getMaBan(), fontNormal));
            document.add(new Paragraph("Khách hàng: " + hoaDon.getKhachHang().getTenKhachHang(), fontNormal));
            document.add(new Paragraph("Nhân viên: " + hoaDon.getNhanVien().getTenNhanVien(), fontNormal));
            document.add(new Paragraph("-----------------------------------------------------------------------", fontNormal));

            // Bảng chi tiết món ăn
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 1, 2, 2}); // Điều chỉnh độ rộng các cột

            // Header
            addTableHeader(table, "Tên Món", fontNormal);
            addTableHeader(table, "Số Lượng", fontNormal);
            addTableHeader(table, "Đơn Giá", fontNormal);
            addTableHeader(table, "Thành Tiền", fontNormal);

            
            double tongTien = 0;
            // Dữ liệu chi tiết món ăn
            List<ChiTietHoaDon> listCTHD = ChiTietHoaDonDAO.getDanhSachChiTietHoaDon(hoaDon.getMaHoaDon());
            for (ChiTietHoaDon chiTiet : listCTHD) {
                table.addCell(new PdfPCell(new Phrase(chiTiet.getMonAn().getTenMonAn(), fontNormal)));

                PdfPCell soLuongCell = new PdfPCell(new Phrase(String.valueOf((char) chiTiet.getSoLuong()), fontNormal));
                soLuongCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa số lượng
                table.addCell(soLuongCell);

                PdfPCell donGiaCell = new PdfPCell(new Phrase(currencyFormat.format(chiTiet.getMonAn().getGiaTien()), fontNormal));
                donGiaCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Căn phải đơn giá
                table.addCell(donGiaCell);

                double thanhTien = chiTiet.getSoLuong() * chiTiet.getMonAn().getGiaTien();
                PdfPCell thanhTienCell = new PdfPCell(new Phrase(currencyFormat.format(thanhTien), fontNormal));
                thanhTienCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Căn phải thành tiền
                table.addCell(thanhTienCell);
                
                tongTien += thanhTien;
            }
            document.add(table);

            // Tính tổng tiền và khuyến mãi
            double khuyenMai = tongTien /100 * hoaDon.getKhuyenMai().getGiamGia();
            double vat = tongTien*hoaDon.getVAT();
            double tongCongSauKhuyenMai = hoaDon.getTongTien();

            // Thêm thông tin "Tổng tiền"
            Paragraph tongTienParagraph = new Paragraph();
            tongTienParagraph.add(new Chunk("Tổng tiền:     ", fontNormal));
            tongTienParagraph.add(new Chunk(currencyFormat.format(tongTien), fontNormal));
            tongTienParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(tongTienParagraph);

            // Thêm thông tin "Giảm giá"
            Paragraph giamGiaParagraph = new Paragraph();
            giamGiaParagraph.add(new Chunk("Giảm giá:     ", fontNormal));
            giamGiaParagraph.add(new Chunk(currencyFormat.format(khuyenMai), fontNormal));
            giamGiaParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(giamGiaParagraph);
            
            // Thêm thông tin "Giảm giá"
            Paragraph vatParagraph = new Paragraph();
            vatParagraph.add(new Chunk("VAT:     ", fontNormal));
            vatParagraph.add(new Chunk(currencyFormat.format(vat), fontNormal));
            vatParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(vatParagraph);

            // Thêm thông tin "Tổng cộng sau khuyến mãi"
            Paragraph tongCongParagraph = new Paragraph();
            tongCongParagraph.add(new Chunk("Tổng thanh toán:     ", fontNormal));
            tongCongParagraph.add(new Chunk(currencyFormat.format(tongCongSauKhuyenMai), fontNormal));
            tongCongParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(tongCongParagraph);

// Dòng cảm ơn
            Paragraph thankYou = new Paragraph("Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!", fontSubTitle);
            thankYou.setAlignment(Element.ALIGN_CENTER);
            thankYou.setSpacingBefore(20f);
            document.add(thankYou);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        // Mở file PDF sau khi tạo xong
        try {
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table, String headerTitle, Font font) {
        PdfPCell header = new PdfPCell(new Phrase(headerTitle, font));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }
}
