package com.kasir;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.sql.*;

public class KasirApp extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private java.util.List<Product> keranjang = new ArrayList<>();
    private JLabel totalLabel;

    public KasirApp() {
        setTitle("BARA MA'WO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // untuk posisi ditengah layar

        // ICON APLIKASI
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/bara.png"));
        setIconImage(appIcon.getImage());

        // // Daftar produk
        // Product[] products = {
        // new Product("Nasi Goreng", 15000),
        // new Product("Teh Manis", 5000),
        // new Product("Ayam Bakar", 20000)
        // };

        java.util.List<Product> daftarProduk = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produk");

            while (rs.next()) {
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                daftarProduk.add(new Product(nama, harga));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menarik produk dari databse");
            e.printStackTrace();
        }

        // Combo box untuk pilih produk
        JComboBox<Product> comboProduk = new JComboBox<>(daftarProduk.toArray(new Product[0]));
        JButton tambahButton = new JButton("Tambah ke Keranjang");
        JButton clearButton = new JButton("Bersihkan Keranjang");

        // Table model
        model = new DefaultTableModel(new Object[] { "Produk", "Harga" }, 0);
        table = new JTable(model);

        // Label total
        totalLabel = new JLabel("Total: Rp0");

        // Tombol cetak struk
        JButton cetakStrukButton = new JButton("Cetak Struk");

        // Panel atas
        JPanel topPanel = new JPanel();
        topPanel.add(comboProduk);
        topPanel.add(tambahButton);
        topPanel.add(clearButton);

        // Panel bawah
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(cetakStrukButton, BorderLayout.EAST);

        // Tambah ke keranjang
        tambahButton.addActionListener(e -> {
            Product selected = (Product) comboProduk.getSelectedItem();
            if (selected != null) {
                keranjang.add(selected);
                model.addRow(new Object[] { selected.getNama(), selected.getHarga() });
                updateTotal();
            }
        });

        clearButton.addActionListener(e -> {
            keranjang.clear();
            model.setRowCount(0);
            updateTotal();
        });

        // Cetak struk
        cetakStrukButton.addActionListener(e -> {
            StringBuilder struk = new StringBuilder("===== STRUK =====\n");
            int total = 0;
            for (Product p : keranjang) {
                struk.append(p.toString()).append("\n");
                total += p.getHarga();
            }
            struk.append("----------------\n");
            struk.append("Total: Rp").append(total);

            JOptionPane.showMessageDialog(this, struk.toString());
        });

        // Layout utama
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateTotal() {
        int total = keranjang.stream().mapToInt(Product::getHarga).sum();
        totalLabel.setText("Total: Rp" + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KasirApp().setVisible(true);
        });
    }

}
