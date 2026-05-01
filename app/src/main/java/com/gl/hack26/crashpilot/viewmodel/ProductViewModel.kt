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
        if (!BuildConfig.DEBUG) {
            // Crash triggers are only enabled in debug builds
            return
        }
        when (productId % 3) {
            0 -> {
                // ArithmeticException
                val x = 10 / 0
            }
            1 -> {
                // IndexOutOfBoundsException
                val list = listOf(1)
                val y = list[10]
            }
            else -> {
                // Custom RuntimeException
                throw RuntimeException("Intentional Crash in CrashPilot App: $productName triggered it!")
            }
        }
    }

    fun triggerListCrash() {
        if (!BuildConfig.DEBUG) {
            // Crash triggers are only enabled in debug builds
            return
        }
        val nullString: String? = null
        nullString!!.length // Trigger NPE
    }
}
