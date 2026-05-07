package com.gl.hack26.crashpilot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        when (productId % 3) {
            0 -> {
                // Realistic ArithmeticException: Rating calculation with zero reviews
                val totalRating = 50
                val reviewCount = 0
                if (reviewCount > 0) {
                    val averageRating = totalRating / reviewCount
                }
            }
            1 -> {
                // Realistic IndexOutOfBoundsException: Accessing a 'featured' item from an empty promotion list
                val promotions = listOf<String>()
                if (promotions.isNotEmpty()) {
                    val featuredPromo = promotions[0]
                }
            }
            else -> {
                // Realistic NumberFormatException: Parsing price from a localized string without proper handling
                val priceString = "$99.99"
                val price = priceString.removePrefix("$").toDoubleOrNull()
            }
        }
    }

    fun triggerListCrash() {
        // Realistic NullPointerException: Repository returns null for an invalid ID, and we force use it
        val product = repository.getProductById(-1)
        val nameLength = product?.product_name?.length
    }
}
