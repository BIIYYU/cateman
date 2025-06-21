package com.kasir;

import java.util.ArrayList;
import java.util.List;

public class Kasir {
    private List<Product> keranjang = new ArrayList<>();

    public void tambahProduct(Product p) {
        keranjang.add(p);
    }

    public void tampilkanStruk() {
        int Total = 0;
        System.out.println("============ STRUK PEMBELIAN =============");
        for (Product p : keranjang) {
            System.out.println(p);
            Total += p.getHarga();
        }
        System.out.println("-------------------------------------------");
        System.out.println("Total : Rp. " + Total);
    }
}
