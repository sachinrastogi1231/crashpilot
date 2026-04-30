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
            Product(5, "Crashy Item 5", "Alpha Store", android.R.drawable.ic_menu_report_image)
        )
    }
}
