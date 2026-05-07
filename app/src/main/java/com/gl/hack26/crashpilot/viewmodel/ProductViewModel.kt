package com.gl.hack26.crashpilot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl.hack26.crashpilot.BuildConfig
import com.gl.hack26.crashpilot.Product
import com.gl.hack26.crashpilot.repository.ProductRepository

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = repository.getProducts()
    }

    fun triggerCrash(productId: Int, productName: String?) {
        if (!BuildConfig.DEBUG) return

        when (productId % 3) {
            0 -> {
                // Demo: ArithmeticException guarded against zero divisor
                val totalRating = 50
                val reviewCount = 0
                val averageRating = if (reviewCount != 0) totalRating / reviewCount else 0
            }
            1 -> {
                // Demo: IndexOutOfBoundsException from empty promotions list
                val promotions = listOf<String>()
                val featuredPromo = promotions[0]
            }
            else -> {
                // Demo: NumberFormatException from localized price string
                val priceString = "$99.99"
                val price = priceString.toDouble()
            }
        }
    }

    fun triggerListCrash() {
        if (!BuildConfig.DEBUG) return
        // Demo: NullPointerException from invalid product ID
        val product = repository.getProductById(-1)
        val nameLength = product!!.product_name.length
    }
}
