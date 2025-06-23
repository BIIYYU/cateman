package com.kasir;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("BARA MA'WO");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ICON APLIKASI
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/bara.png"));
        setIconImage(appIcon.getImage());

        // CREATE PANEL UTAMA
        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS));
        panelUtama.setBackground(Color.WHITE);
        panelUtama.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20)); /* padding atas-kiri-bawah-kanan */

        // SET LOGO
        JLabel logoLabel = new JLabel();
        ImageIcon logo = new ImageIcon(getClass().getResource("/bara.png"));
        logoLabel.setIcon(logo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); /* SET RATA TENGAH */

        // TOMBOL LANJUT KE MENU UTAMA
        JButton tombolLanjut = new JButton("Lanjut ke Aplikasi");
        tombolLanjut.setAlignmentX(Component.CENTER_ALIGNMENT);
        tombolLanjut.setBackground(new Color(255, 165, 0)); /* orange */
        tombolLanjut.setForeground(Color.WHITE);
        tombolLanjut.setFocusPainted(false);
        tombolLanjut.setFont(new Font("Arial", Font.BOLD, 14));
        tombolLanjut.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); /* padding dalam tombol */
        tombolLanjut.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tombolLanjut.addActionListener(e -> {
            new MenuUtama().setVisible(true);
            dispose(); /* TUTUP MAIN MENU */
        });

        // WAJIB ! (TAMBAH KEPANEL JIKA TIDAK ADA INI TIDAK TAMPIL)
        panelUtama.add(logoLabel);
        panelUtama.add(Box.createRigidArea(new Dimension(0, 5))); /* JARAK ANTARA LOGO DAN TOMBOL */
        panelUtama.add(tombolLanjut);

        add(panelUtama); /* TAMBAH KE PANEL FRAME */
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LandingPage().setVisible(true);
        });
    }

}
