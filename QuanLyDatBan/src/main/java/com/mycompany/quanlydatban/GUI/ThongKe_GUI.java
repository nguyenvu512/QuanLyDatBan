/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import com.mycompany.quanlydatban.dao.HoaDonDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Vo Van Tuong
 */
public class ThongKe_GUI extends javax.swing.JPanel {

    private final JDateChooser dateChooser;
    private final JDateChooser datechooserEnd;

    /**
     * Creates new form ThongKe_GUI
     */
    public ThongKe_GUI() {
        initComponents();
        setLayout(new BorderLayout());
        add(jp_TT,BorderLayout.NORTH); 
        
         dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
//        chọn ngày đặt
        dateChooser.setDateFormatString("dd-MM-yyyy"); // Định dạng ngày tháng năm
        dateChooser.setLocale(new Locale("vi", "VN")); // Thiết lập Locale thành tiếng Việt

        // Lấy JTextField của JDateChooser để căn giữa chữ
        JTextField dateField = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        dateField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ
        dateChooser.setBackground(Color.WHITE);
        dateChooser.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));
        dateChooser.setPreferredSize(new Dimension(180, 25));

        // Lấy nút bên cạnh dateChooser và chỉnh màu
        JButton calendarButton = dateChooser.getCalendarButton();
        calendarButton.setBackground(Color.WHITE); // Đặt màu nền là trắng
        calendarButton.setPreferredSize(new Dimension(25, 25)); // Chỉnh kích thước của nút
        calendarButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jp_NBD.setLayout(new BorderLayout());
        jp_NBD.add(dateChooser,BorderLayout.CENTER);
        
//        Ngày kết thúc
         datechooserEnd = new JDateChooser();
        datechooserEnd.setDate(new Date());
        
        datechooserEnd.setDateFormatString("dd-MM-yyyy");
        datechooserEnd.setLocale(new Locale("vi","VN"));
        
        JTextField jtext= ((JTextField) datechooserEnd.getDateEditor().getUiComponent());
        jtext.setHorizontalAlignment(jtext.CENTER);
        datechooserEnd.setBackground(Color.WHITE);
        datechooserEnd.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 14));
        datechooserEnd.setPreferredSize(new Dimension(180, 25));
      
        // Lấy nút bên cạnh dateChooser và chỉnh màu
        JButton calendarButton1 = datechooserEnd.getCalendarButton();
        calendarButton1.setBackground(Color.WHITE); // Đặt màu nền là trắng
        calendarButton1.setPreferredSize(new Dimension(25, 25)); // Chỉnh kích thước của nút
        calendarButton1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jp_NKT.setLayout(new BorderLayout());
        jp_NKT.add(datechooserEnd,BorderLayout.CENTER);
        
        
        PlaceholderSupport placehodersupport = new PlaceholderSupport();
        placehodersupport.addPlaceholder(Jt_tim,"nhập năm cần thống kê");
//        int nam = Integer.parseInt(Jt_tim.getText().trim());
        map =hd_d.doanhThuTheoThang(2024);
        createDataset();
        ten= "Thống Kê Doanh Thu Theo Tháng Năm 2024";
        loai = "Tháng";
         chart = new ChartPanel(createChart());
        chart.setPreferredSize(new java.awt.Dimension(1170, 540));
        jp_BieuDo.setLayout(new BorderLayout());
        jp_BieuDo.add(chart);
        add(jp_BieuDo,BorderLayout.CENTER);
        
        //        
        dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Ngày"))
                {
               LocalDateTime startDay = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
               LocalDateTime endDay = datechooserEnd.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
               String formattedStartDate = startDay.format(formatter);  
               String formattedEndDate = endDay.format(formatter);
               Map<String, Double> map = new HashMap<>();
               map=hd_d.doanhThuTheoNgay(formattedStartDate,formattedEndDate);
               // Tạo dataset cho biểu đồ
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                String ngay = entry.getKey();  // Ngày (dd-MM-yyyy)
                Double doanhThu = entry.getValue();
                dataset.addValue(doanhThu, "Doanh Thu", ngay);  // Chú ý rằng "Doanh Thu" là chuỗi mô tả giá trị y-axis
                }

                        JFreeChart chart = ChartFactory.createBarChart(
                    "Thống Kê Doanh Thu Theo Ngày",  // Tiêu đề biểu đồ
                    "Thời Gian",  // Nhãn trục X
                    "Doanh Thu (VND)",  // Nhãn trục Y
                    dataset,  // Dữ liệu
                    PlotOrientation.VERTICAL,  // Hướng biểu đồ
                    true,  // Hiển thị chú thích
                    true,  // Hiển thị tooltips
                    false  // Không hiển thị URL
                );

                // Tạo ChartPanel từ biểu đồ
                        ChartPanel chartPanel = new ChartPanel(chart);
                        chartPanel.setPreferredSize(new java.awt.Dimension(1170, 540));

                        // Cập nhật giao diện và hiển thị biểu đồ
                        jp_BieuDo.removeAll();
                        jp_BieuDo.setLayout(new BorderLayout());
                        jp_BieuDo.add(chartPanel, BorderLayout.CENTER);
                        jp_BieuDo.revalidate();
                        jp_BieuDo.repaint();
                    }
            }
        });
        
//        bắt sự kiện cho datechooserend
            datechooserEnd.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Ngày"))
                {
               LocalDateTime startDay = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
               LocalDateTime endDay = datechooserEnd.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
               String formattedStartDate = startDay.format(formatter);  
               String formattedEndDate = endDay.format(formatter);
               Map<String, Double> map = new HashMap<>();
               map=hd_d.doanhThuTheoNgay(formattedStartDate,formattedEndDate);
               // Tạo dataset cho biểu đồ
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                String ngay = entry.getKey();  // Ngày (dd-MM-yyyy)
                Double doanhThu = entry.getValue();
                dataset.addValue(doanhThu, "Doanh Thu", ngay);  // Chú ý rằng "Doanh Thu" là chuỗi mô tả giá trị y-axis
                }

                        JFreeChart chart = ChartFactory.createBarChart(
                    "Thống Kê Doanh Thu Theo Ngày",  // Tiêu đề biểu đồ
                    "Thời Gian",  // Nhãn trục X
                    "Doanh Thu (VND)",  // Nhãn trục Y
                    dataset,  // Dữ liệu
                    PlotOrientation.VERTICAL,  // Hướng biểu đồ
                    true,  // Hiển thị chú thích
                    true,  // Hiển thị tooltips
                    false  // Không hiển thị URL
                );

                // Tạo ChartPanel từ biểu đồ
                        ChartPanel chartPanel = new ChartPanel(chart);
                        chartPanel.setPreferredSize(new java.awt.Dimension(1170, 540));

                        // Cập nhật giao diện và hiển thị biểu đồ
                        jp_BieuDo.removeAll();
                        jp_BieuDo.setLayout(new BorderLayout());
                        jp_BieuDo.add(chartPanel, BorderLayout.CENTER);
                        jp_BieuDo.revalidate();
                        jp_BieuDo.repaint();
                    }
            }
        });
        
        
        
    }
    
  public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                ten,
                loai, "DoanhThu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }
  
  public CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         map = hd_d.doanhThuTheoThang();
        for(Entry<Integer,Double> entry: map.entrySet()){
            dataset.addValue(entry.getValue(),"Doanh Thu",entry.getKey());
        }
        return dataset;
    }
  
   public JFreeChart createChart1() {
        JFreeChart barChart = ChartFactory.createBarChart(
                ten,
                loai, "DoanhThu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }
  
  public CategoryDataset createDataset1() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         map = hd_d.doanhThuTheoThang();
        for(Entry<Integer,Double> entry: map.entrySet()){
            dataset.addValue(entry.getValue(),"Doanh Thu",entry.getKey());
        }
        return dataset;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_TT = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jlb_TT = new javax.swing.JLabel();
        Jt_tim = new javax.swing.JTextField();
        Jbt_XacNhan = new javax.swing.JButton();
        jp_NBD = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jp_NKT = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jp_BieuDo = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống Kê Theo Tháng", "Thống Kê Theo Năm", "Thống Kê Theo Ngày" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlb_TT.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlb_TT.setText("  THỐNG KÊ DOANH THU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jlb_TT, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlb_TT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jt_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jt_timActionPerformed(evt);
            }
        });

        Jbt_XacNhan.setText("Xác nhận");
        Jbt_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbt_XacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_NBDLayout = new javax.swing.GroupLayout(jp_NBD);
        jp_NBD.setLayout(jp_NBDLayout);
        jp_NBDLayout.setHorizontalGroup(
            jp_NBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        jp_NBDLayout.setVerticalGroup(
            jp_NBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Ngày Bắt Đầu");

        javax.swing.GroupLayout jp_NKTLayout = new javax.swing.GroupLayout(jp_NKT);
        jp_NKT.setLayout(jp_NKTLayout);
        jp_NKTLayout.setHorizontalGroup(
            jp_NKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );
        jp_NKTLayout.setVerticalGroup(
            jp_NKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jLabel2.setText("Ngày kết thúc");

        javax.swing.GroupLayout jp_TTLayout = new javax.swing.GroupLayout(jp_TT);
        jp_TT.setLayout(jp_TTLayout);
        jp_TTLayout.setHorizontalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jbt_XacNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jp_NBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_NKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_TTLayout.setVerticalGroup(
            jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_TTLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_TTLayout.createSequentialGroup()
                        .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Jt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Jbt_XacNhan))
                            .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addComponent(jp_NBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(jp_TTLayout.createSequentialGroup()
                        .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp_NKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jp_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jp_TTLayout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 45, Short.MAX_VALUE)))
        );

        add(jp_TT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 90));

        jp_BieuDo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jp_BieuDoLayout = new javax.swing.GroupLayout(jp_BieuDo);
        jp_BieuDo.setLayout(jp_BieuDoLayout);
        jp_BieuDoLayout.setHorizontalGroup(
            jp_BieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        jp_BieuDoLayout.setVerticalGroup(
            jp_BieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        add(jp_BieuDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 920, 300));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
                           
        if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Ngày"))
        {
           
             jp_BieuDo.removeAll();
             jp_BieuDo.revalidate();
             jp_BieuDo.repaint();
           LocalDateTime startDay = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
           LocalDateTime endDay = datechooserEnd.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
           String formattedStartDate = startDay.format(formatter);  
           String formattedEndDate = endDay.format(formatter);
           Map<String, Double> map = new HashMap<>();
           map=hd_d.doanhThuTheoNgay(formattedStartDate,formattedEndDate);
           // Tạo dataset cho biểu đồ
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Double> entry : map.entrySet()) {
            String ngay = entry.getKey();  // Ngày (dd-MM-yyyy)
            Double doanhThu = entry.getValue();
            dataset.addValue(doanhThu, "Doanh Thu", ngay);  // Chú ý rằng "Doanh Thu" là chuỗi mô tả giá trị y-axis
            }
            
                    JFreeChart chart = ChartFactory.createBarChart(
                "Thống Kê Doanh Thu Theo Ngày",  // Tiêu đề biểu đồ
                "Thời Gian",  // Nhãn trục X
                "Doanh Thu (VND)",  // Nhãn trục Y
                dataset,  // Dữ liệu
                PlotOrientation.VERTICAL,  // Hướng biểu đồ
                true,  // Hiển thị chú thích
                true,  // Hiển thị tooltips
                false  // Không hiển thị URL
            );

            // Tạo ChartPanel từ biểu đồ
                    ChartPanel chartPanel = new ChartPanel(chart);
                    chartPanel.setPreferredSize(new java.awt.Dimension(1170, 540));

                    // Cập nhật giao diện và hiển thị biểu đồ
                    jp_BieuDo.setLayout(new BorderLayout());
                    jp_BieuDo.add(chartPanel, BorderLayout.CENTER);
                    jp_BieuDo.revalidate();
                    jp_BieuDo.repaint();
                        }
        else if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Tháng"))
                {
                    map = hd_d.doanhThuTheoThang(2024);
                    ten = "Thống Kê Doanh Thu Theo Tháng Năm 2024";
                    loai = "Tháng";
                    Jt_tim.setEditable(true);
                       createDataset();
                    chart = new ChartPanel(createChart());
                    jp_BieuDo.setLayout(new BorderLayout());
                    jp_BieuDo.removeAll();
                    jp_BieuDo.add(chart);
                    jp_BieuDo.revalidate();
                    jp_BieuDo.repaint();
        }
        else if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Năm"))
        {
            map = hd_d.doanhThuTheoNam();
            ten = "Thống Kê Doanh Thu Theo Năm";
            loai= "Năm"; 
            Jt_tim.setEditable(false);
            
               createDataset();
            chart = new ChartPanel(createChart());
            jp_BieuDo.setLayout(new BorderLayout());
            jp_BieuDo.removeAll();
            jp_BieuDo.add(chart);
            jp_BieuDo.revalidate();
            jp_BieuDo.repaint();
        }
 
     
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void Jbt_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbt_XacNhanActionPerformed
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem().equals("Thống Kê Theo Tháng"))
        {
            if(Jt_tim.getText().equals("nhập năm cần thống kê"))
            {
                map= hd_d.doanhThuTheoThang(2024);
            }
            else {
                int nam = Integer.parseInt(Jt_tim.getText().trim());
                map = hd_d.doanhThuTheoThang(nam);
            }
               ten = "Thống Kê Doanh Thu Theo Tháng "+ Jt_tim.getText().trim();
               loai = "Tháng";
            createDataset();
            chart= new ChartPanel(createChart());
            jp_BieuDo.setLayout(new BorderLayout());
            jp_BieuDo.removeAll();
            jp_BieuDo.add(chart);
            jp_BieuDo.revalidate();
            jp_BieuDo.repaint();
            
        }
        
    }//GEN-LAST:event_Jbt_XacNhanActionPerformed

    private void Jt_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jt_timActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jt_timActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jbt_XacNhan;
    private javax.swing.JTextField Jt_tim;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlb_TT;
    private javax.swing.JPanel jp_BieuDo;
    private javax.swing.JPanel jp_NBD;
    private javax.swing.JPanel jp_NKT;
    private javax.swing.JPanel jp_TT;
    // End of variables declaration//GEN-END:variables

   private HoaDonDAO hd_d = new HoaDonDAO();
    Map<Integer,Double> map = new HashMap<>();
    private String ten;
    private String loai;
    private ChartPanel chart;
//    private DefaultTableModel dtm;
//    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
