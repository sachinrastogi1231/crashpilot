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

    fun triggerRandomCrash(productId: Int, isDetailView: Boolean = false) {
        val randomValue = (0..100).random()
        
        // 20% chance to crash if in detail view, or 5% chance during list binding
        val crashThreshold = if (isDetailView) 20 else 5
        
        if (randomValue < crashThreshold) {
            when (productId % 4) {
                0 -> {
                    // Realistic ArithmeticException: Rating calculation with zero reviews
                    val totalRating = 50
                    val reviewCount = 0
                    val averageRating = totalRating / reviewCount
                }
                1 -> {
                    // Realistic IndexOutOfBoundsException: Accessing a 'featured' item from an empty promotion list
                    val promotions = listOf<String>()
                    val featuredPromo = promotions[0]
                }
                2 -> {
                    // Realistic NumberFormatException: Parsing price from a localized string without proper handling
                    val priceString = "$99.99"
                    val price = priceString.toDouble() 
                }
                3 -> {
                    // Realistic NullPointerException: Repository returns null for an invalid ID
                    val product = repository.getProductById(-1)
                    val nameLength = product!!.product_name.length
                }
            }
        }
    }

    fun triggerCrash(productId: Int, productName: String?) {
        // Keeping this for compatibility but making it random too
        triggerRandomCrash(productId, true)
    }

    fun triggerListCrash() {
        // Keeping this for compatibility but making it random too
        triggerRandomCrash(0, false)
    }
}
