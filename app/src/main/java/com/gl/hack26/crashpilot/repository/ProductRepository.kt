package com.gl.hack26.crashpilot.repository

import com.gl.hack26.crashpilot.Product
import com.gl.hack26.crashpilot.R

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Crashy Item 1", "Crashy Store", android.R.drawable.ic_menu_report_image),
            Product(2, "Crashy Item 2", "Crashy Store", android.R.drawable.ic_menu_report_image),
            Product(3, "Crashy Item 3", "Crashy Store", android.R.drawable.ic_menu_report_image),
            Product(4, "Crashy Item 4", "Experimental Lab", android.R.drawable.ic_menu_report_image),
            Product(5, "Crashy Item 5", "Alpha Store", android.R.drawable.ic_menu_report_image),
            Product(6, "Premium Widget", "Gadget Central", android.R.drawable.ic_menu_report_image),
            Product(7, "Eco-Friendly Bottle", "Green Life", android.R.drawable.ic_menu_report_image),
            Product(8, "Wireless Headphones", "Audio Tech", android.R.drawable.ic_menu_report_image),
            Product(9, "Smart Watch", "Timepiece Co.", android.R.drawable.ic_menu_report_image),
            Product(10, "Laptop Stand", "Office Essentials", android.R.drawable.ic_menu_report_image),
            Product(11, "Mechanical Keyboard", "Gamer Haven", android.R.drawable.ic_menu_report_image),
            Product(12, "Gaming Mouse", "Precision Gears", android.R.drawable.ic_menu_report_image),
            Product(13, "USB-C Hub", "Connectivity Lab", android.R.drawable.ic_menu_report_image),
            Product(14, "Desk Lamp", "Bright Solutions", android.R.drawable.ic_menu_report_image),
            Product(15, "Ergonomic Chair", "Comfort Plus", android.R.drawable.ic_menu_report_image)
        )
    }

    fun getProductById(id: Int): Product? {
        return getProducts().find { it.id == id }
    }
}
