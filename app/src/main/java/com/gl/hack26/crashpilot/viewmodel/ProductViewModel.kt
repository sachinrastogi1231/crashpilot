package com.gl.hack26.crashpilot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl.hack26.crashpilot.Product
import com.gl.hack26.crashpilot.repository.ProductRepository
import java.text.SimpleDateFormat
import java.util.Locale

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

    @Suppress("UNUSED_VARIABLE", "DIVISION_BY_ZERO", "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    fun triggerRandomCrash(productId: Int, isDetailView: Boolean = false) {
        // Crash simulations are only enabled in debug builds to prevent production crashes
        if (!com.gl.hack26.crashpilot.BuildConfig.DEBUG) return

        val randomValue = (0..100).random()
        
        // Lower probabilities to ensure app is usable: 10% for detail view, 1% for list binding
        val crashThreshold = if (isDetailView) 10 else 1
        
        if (randomValue < crashThreshold) {
            when (productId % 24) {
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
                    val nameLength = product?.product_name?.length ?: 0
                    if (product == null) throw NullPointerException("Product not found for id -1")
                }
                4 -> {
                    // Realistic ConcurrentModificationException: Modifying list while iterating
                    val cartItems = mutableListOf("Item1", "Item2", "Item3")
                    for (item in cartItems) {
                        if (item.contains("Item")) {
                            cartItems.remove(item) // Unsafe modification during iteration
                        }
                    }
                }
                5 -> {
                    // Realistic IllegalArgumentException: Invalid parameter in API call
                    val quantity = -5 // Negative quantity
                    if (quantity < 0) {
                        throw IllegalArgumentException("Quantity cannot be negative: $quantity")
                    }
                }
                6 -> {
                    // Realistic NoSuchElementException: Getting first from empty collection
                    val emptyList = emptyList<String>()
                    val firstItem = emptyList.first() // NoSuchElementException
                }
                7 -> {
                    // Realistic ClassCastException: Unsafe type casting
                    val anyValue: Any = "String Value"
                    if (productId % 2 == 0) {
                        val intValue = anyValue as Int // Direct cast without type check
                    } else {
                        // Another type of crash to avoid immediate launch crash if possible
                        val list = listOf("A")
                        val item = list[5]
                    }
                }
                8 -> {
                    // Realistic IllegalStateException: Invalid operation in wrong state
                    val isInitialized = false
                    if (!isInitialized) {
                        throw IllegalStateException("Component not initialized. Cannot perform operation.")
                    }
                }
                9 -> {
                    // Realistic StringIndexOutOfBoundsException: Accessing invalid string index
                    val productCode = "ABC"
                    val charAtIndex = productCode[productCode.length] // Invalid index
                }
                10 -> {
                    // Realistic UnsupportedOperationException: Trying unsupported collection operation
                    val readOnlyList = listOf("A", "B", "C")
                    @Suppress("UNCHECKED_CAST")
                    val mutableVersion = readOnlyList as MutableList<String>
                    mutableVersion.add("D") // Attempt to modify unmodifiable list
                }
                11 -> {
                    // Realistic ArrayIndexOutOfBoundsException: Array access out of bounds
                    val discountArray = intArrayOf(10, 20, 30)
                    val discount = discountArray[5] // Invalid index
                }
                12 -> {
                    // Realistic NullPointerException: Chained null access
                    val priceData: Map<String, String?>? = null
                    val finalPrice = priceData?.get("price")?.toInt() ?: 0
                    val adjustedPrice = finalPrice + 10
                    // Simulate accessing null pointer in chain
                    val nullValue: String? = null
                    val length = nullValue!!.length // Force NullPointerException
                }
                13 -> {
                    // Realistic AssertionError: Invalid assertion in calculation logic
                    val expected = 100
                    val actual = 50
                    assert(expected == actual) { "Calculation mismatch: expected $expected but got $actual" }
                }
                14 -> {
                    // Human-like: Date formatting crash with invalid locale data
                    val dateString = "32/13/2024" // Invalid date
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val date = formatter.parse(dateString)
                    val milliseconds = date!!.time / 0 // ArithmeticException after parsing
                }
                15 -> {
                    // Human-like: Enum constant crash
                    val statusCode = "UNKNOWN_STATUS"
                    val enumConstant = ProductStatus.valueOf(statusCode) // IllegalArgumentException
                }
                16 -> {
                    // Human-like: JSON/API parsing failure
                    val jsonResponse = "{\"price\":\"abc\",\"quantity\":2}" // Malformed
                    val priceStr = jsonResponse.split(":")[1]
                    val price = priceStr.toDouble() // NumberFormatException
                }
                17 -> {
                    // Human-like: Division by calculated value
                    val totalReviews = 10
                    val avgRating = 4.5
                    val unusedScore = totalReviews / (totalReviews - 10) // ArithmeticException
                }
                18 -> {
                    // Human-like: Last element access on possibly empty list
                    val scores = mutableListOf<Int>()
                    if (productId % 3 == 0) {
                        scores.add(100)
                    }
                    val lastScore = scores.last() // NoSuchElementException
                }
                19 -> {
                    // Human-like: Map getOrNull edge case
                    val priceMap = mapOf("item1" to 10, "item2" to 20)
                    val keys = priceMap.keys.toList()
                    val thirdKey = keys[3] // IndexOutOfBoundsException
                    val value = priceMap[thirdKey]!!.toString()
                }
                20 -> {
                    // Human-like: String split with wrong count assumption
                    val csvData = "name,price"
                    val parts = csvData.split(",")
                    val id = parts[3].toInt() // IndexOutOfBoundsException
                }
                21 -> {
                    // Human-like: Substring operation crash
                    val code = "AB"
                    val invalidSubstring = code.substring(0, 5) // StringIndexOutOfBoundsException
                }
                22 -> {
                    // Human-like: Type mismatch in collection operations
                    val mixed: List<Any> = listOf(1, "two", 3.0, "four")
                    @Suppress("UNCHECKED_CAST")
                    val numbers = mixed as List<Int>
                    val sum = numbers.map { it * 2 }.sum()
                }
                else -> {
                    // Human-like: Attempting to parse empty/corrupt response
                    val response = ""
                    val lines = response.split("\n")
                    val header = lines[0].split(":")[1] // IndexOutOfBoundsException
                    val headerHash = header.hashCode() / 0
                }
            }
        }
    }

    private enum class ProductStatus {
        AVAILABLE, OUT_OF_STOCK, DISCONTINUED
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
