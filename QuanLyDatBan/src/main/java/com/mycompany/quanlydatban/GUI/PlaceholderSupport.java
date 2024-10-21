/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydatban.GUI;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class PlaceholderSupport{
    private Color placeholderColor = Color.GRAY; // Màu chữ cho placeholder
    private Color textColor = Color.BLACK; // Màu chữ khi người dùng nhập

    public PlaceholderSupport() {
    }

    public PlaceholderSupport(Color placeholderColor, Color textColor) {
        this.placeholderColor = placeholderColor;
        this.textColor = textColor;
    }

    public void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(placeholderColor);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(textColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(placeholderColor);
                    textField.setText(placeholder);
                }
            }
        });
    }

    // Tùy chọn: Bạn có thể thêm các phương thức để thay đổi màu chữ và placeholder
    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
    }

    public void setTextColor(Color color) {
        this.textColor = color;
    }
}
