package com.kasir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuUtama extends JFrame {
    public MenuUtama() {
        setTitle("Menu Utama");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ICON APLIKASI
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/bara.png"));
        setIconImage(appIcon.getImage());

        setLayout(new GridLayout(2, 2, 10, 10));

        // Tambahkan tombol dengan icon dan teks
        JButton btnMaster = createMenuButton("MASTER", "/icons/gear.png");
        JButton btnLaporan = createMenuButton("LAPORAN", "/icons/report.png");
        JButton btnTransaksi = createMenuButton("TRANSAKSI", "/icons/transaction.png");
        JButton btnLogout = createMenuButton("LOGOUT", "/icons/logout.png");

        // Tambahkan ke frame
        add(btnMaster);
        add(btnLaporan);
        add(btnTransaksi);
        add(btnLogout);

        // Tambahkan aksi jika perlu
        btnLogout.addActionListener((ActionEvent e) -> {
            dispose();
            new MenuUtama().setVisible(true); // kembali ke halaman utama
        });
    }

    private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(getClass().getResource(iconPath)));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBackground(new Color(255, 191, 0)); // orange
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }
}
