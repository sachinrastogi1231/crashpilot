# CrashPilot_Clone - Crash Types Documentation

## Overview
This document describes all 24 different types of realistic, human-like crashes implemented in the CrashPilot_Clone application. These are NOT intentionally created crashes for testing purposes, but rather represent real-world scenarios that developers commonly encounter during app development.

---

## Crash Types (Cases 0-23)

### **Case 0: ArithmeticException - Division by Zero**
**Scenario**: Rating calculation with zero reviews
```kotlin
val totalRating = 50
val reviewCount = 0
val averageRating = totalRating / reviewCount  // Division by zero
```
**Real-world context**: Apps crash when calculating averages without checking denominator, common in analytics/rating systems.

---

### **Case 1: IndexOutOfBoundsException - Empty List Access**
**Scenario**: Accessing featured promotion from empty list
```kotlin
val promotions = listOf<String>()
val featuredPromo = promotions[0]  // Access index 0 on empty list
```
**Real-world context**: Accessing UI elements without checking if list has content, common in banner/carousel displays.

---

### **Case 2: NumberFormatException - String Parsing**
**Scenario**: Parsing price string with wrong format
```kotlin
val priceString = "$99.99"
val price = priceString.toDouble()  // Currency symbol causes parsing error
```
**Real-world context**: Locale-specific price formatting issues when parsing API responses.

---

### **Case 3: NullPointerException - Null Object Access**
**Scenario**: Accessing property of null object
```kotlin
val product = repository.getProductById(-1)
val nameLength = product!!.product_name.length  // product is null
```
**Real-world context**: Repository returns null for invalid IDs, leading to null access crashes.

---

### **Case 4: ConcurrentModificationException - Collection Mutation During Iteration**
**Scenario**: Removing items from list while iterating
```kotlin
val cartItems = mutableListOf("Item1", "Item2", "Item3")
for (item in cartItems) {
    if (item.contains("Item")) {
        cartItems.remove(item)  // Unsafe modification
    }
}
```
**Real-world context**: Common in shopping cart implementations and real-time data filtering.

---

### **Case 5: IllegalArgumentException - Invalid Parameter**
**Scenario**: Negative quantity in purchase logic
```kotlin
val quantity = -5
if (quantity < 0) {
    throw IllegalArgumentException("Quantity cannot be negative: $quantity")
}
```
**Real-world context**: API validation errors when invalid parameters are passed.

---

### **Case 6: NoSuchElementException - Empty Collection Operation**
**Scenario**: Calling first() on empty collection
```kotlin
val emptyList = emptyList<String>()
val firstItem = emptyList.first()  // NoSuchElementException
```
**Real-world context**: Assuming list has elements without checking, common in data aggregation.

---

### **Case 7: ClassCastException - Unsafe Type Casting**
**Scenario**: Direct cast without type checking
```kotlin
val anyValue: Any = "String Value"
val intValue = anyValue as Int  // Cast string to int
val result = intValue * 2
```
**Real-world context**: Casting API responses without proper type validation.

---

### **Case 8: IllegalStateException - Invalid Operation State**
**Scenario**: Performing operation when component not initialized
```kotlin
val isInitialized = false
if (!isInitialized) {
    throw IllegalStateException("Component not initialized. Cannot perform operation.")
}
```
**Real-world context**: Database or SDK not initialized before use.

---

### **Case 9: StringIndexOutOfBoundsException - String Index Out of Bounds**
**Scenario**: Accessing character at invalid index
```kotlin
val productCode = "ABC"
val charAtIndex = productCode[productCode.length]  // Invalid index
```
**Real-world context**: Parsing product codes or tokens with incorrect index assumptions.

---

### **Case 10: UnsupportedOperationException - Unmodifiable Collection**
**Scenario**: Attempting to modify unmodifiable list
```kotlin
val readOnlyList = listOf("A", "B", "C")
val mutableVersion = readOnlyList as MutableList<String>
mutableVersion.add("D")  // Collection doesn't support modification
```
**Real-world context**: RemoteConfig data or immutable API responses treated as mutable.

---

### **Case 11: ArrayIndexOutOfBoundsException - Array Out of Bounds**
**Scenario**: Accessing array beyond its size
```kotlin
val discountArray = intArrayOf(10, 20, 30)
val discount = discountArray[5]  // Array only has 3 elements
```
**Real-world context**: Hardcoded array access without bounds checking.

---

### **Case 12: NullPointerException - Chained Null Access**
**Scenario**: Dereferencing null in chain of operations
```kotlin
val priceData: Map<String, String?>? = null
val finalPrice = priceData?.get("price")?.toInt() ?: 0
val adjustedPrice = finalPrice + 10
val nullValue: String? = null
val length = nullValue!!.length  // Explicit null access
```
**Real-world context**: Unsafe navigation in complex object graphs.

---

### **Case 13: AssertionError - Failed Assertion**
**Scenario**: Assertion condition fails
```kotlin
val expected = 100
val actual = 50
assert(expected == actual) { "Calculation mismatch: expected $expected but got $actual" }
```
**Real-world context**: Logic errors in calculation pipelines.

---

### **Case 14: ArithmeticException - Date/Time Calculation Crash**
**Scenario**: Division by zero in date processing
```kotlin
val dateString = "32/13/2024"  // Invalid date
val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
val date = formatter.parse(dateString)
val milliseconds = date!!.time / 0  // ArithmeticException
```
**Real-world context**: Date formatting with invalid locale data or timezone conversions.

---

### **Case 15: IllegalArgumentException - Invalid Enum Value**
**Scenario**: Converting unknown string to enum
```kotlin
val statusCode = "UNKNOWN_STATUS"
val enumConstant = ProductStatus.valueOf(statusCode)  // Enum doesn't contain value
```
**Real-world context**: API returns unexpected status values not in enum definition.

---

### **Case 16: NumberFormatException - JSON Parsing**
**Scenario**: Parsing malformed JSON response
```kotlin
val jsonResponse = "{\"price\":\"abc\",\"quantity\":2}"
val priceStr = jsonResponse.split(":")[1]
val price = priceStr.toDouble()  // "abc" cannot be converted to double
```
**Real-world context**: Corrupted or malformed API responses.

---

### **Case 17: ArithmeticException - Denominator Calculation**
**Scenario**: Division by calculated zero
```kotlin
val totalReviews = 10
val avgRating = 4.5
val unusedScore = totalReviews / (totalReviews - 10)  // Direct division by zero
```
**Real-world context**: Complex rating/scoring calculations with edge cases.

---

### **Case 18: NoSuchElementException - Conditional Empty List**
**Scenario**: Accessing last element of conditionally populated list
```kotlin
val scores = mutableListOf<Int>()
if (productId % 3 == 0) {
    scores.add(100)
}
val lastScore = scores.last()  // Crashes when list is empty
```
**Real-world context**: Data processing pipelines with conditional filtering.

---

### **Case 19: IndexOutOfBoundsException - Map Key Access**
**Scenario**: Accessing map with wrong key index
```kotlin
val priceMap = mapOf("item1" to 10, "item2" to 20)
val keys = priceMap.keys.toList()
val thirdKey = keys[3]  // Only 2 keys exist
val value = priceMap[thirdKey]!!.toString()
```
**Real-world context**: Pagination or list slicing assumptions.

---

### **Case 20: IndexOutOfBoundsException - CSV Parsing**
**Scenario**: Accessing wrong column index in CSV data
```kotlin
val csvData = "name,price"
val parts = csvData.split(",")
val id = parts[3].toInt()  // Only 2 columns exist
```
**Real-world context**: Parsing CSV/JSON with wrong column count assumptions.

---

### **Case 21: StringIndexOutOfBoundsException - Substring Operation**
**Scenario**: Substring with invalid indices
```kotlin
val code = "AB"
val invalidSubstring = code.substring(0, 5)  // String length is 2
```
**Real-world context**: Token/code parsing with incorrect length handling.

---

### **Case 22: ClassCastException - Collection Type Mismatch**
**Scenario**: Casting heterogeneous collection to homogeneous
```kotlin
val mixed: List<Any> = listOf(1, "two", 3.0, "four")
val numbers = mixed as List<Int>  // List contains non-integer types
val sum = numbers.map { it * 2 }.sum()
```
**Real-world context**: Type erasure issues in generic collections with mixed types.

---

### **Case 23: IndexOutOfBoundsException - Response Parsing**
**Scenario**: Parsing empty/corrupt response data
```kotlin
val response = ""
val lines = response.split("\n")
val header = lines[0].split(":")[1]  // Both splits out of bounds
val headerHash = header.hashCode() / 0
```
**Real-world context**: Network failures returning empty responses.

---

## Additional Resources

### CrashSimulator Utility Class
A comprehensive utility class (`CrashSimulator.kt`) is also provided with additional realistic crash scenarios:

- **Race Condition**: Concurrent list modifications
- **Memory Pressure**: Out of memory scenarios
- **JSON Parsing**: API response parsing failures
- **Date Formatting**: Timezone and locale issues
- **Weak References**: Garbage collection side effects
- **Fragment Lifecycle**: State inconsistency crashes
- **Resource Not Found**: Missing drawable/layout resources
- **Database Transaction**: Transaction state corruption
- **SharedPreferences**: Data corruption crashes
- **TextWatcher**: Listener removal side effects
- **WorkManager/JobScheduler**: Background task scheduling failures
- **AnimatorListener**: Animation callback crashes
- **HTTP Response**: HTTP response parsing failures
- **Bitmap Operations**: Image loading/scaling crashes
- **Generics Erasure**: Type erasure in collections
- **Service Notifications**: Notification channel issues

---

## How Crashes Are Triggered

1. **ProductDetailFragment**: When viewing product details, there's a 20% chance of a crash
2. **ProductListFragment**: When scrolling through the list, there's a 5% chance of a crash
3. **Random Distribution**: Each crash type is selected based on `productId % 24`, distributing crashes across different product IDs

---

## Testing Tips

- **Detailed View**: Navigate to product details to trigger crashes (20% rate)
- **Scrolling**: Scroll through the product list to trigger crashes (5% rate)
- **Multiple Products**: Use different products to trigger different crash types
- **Firebase Crashlytics**: All crashes are automatically reported to Firebase

---

## Crash Prevention Best Practices

These crashes demonstrate common mistakes that can be prevented by:

1. **Null Safety**: Always check for null before dereferencing
2. **Bounds Checking**: Validate collection/array sizes before access
3. **Type Safety**: Use proper type checking before casting
4. **Data Validation**: Validate API responses and user input
5. **Lifecycle Management**: Respect component lifecycle before operations
6. **Collection Operations**: Avoid modifying collections during iteration
7. **Error Handling**: Wrap parsing and calculation operations in try-catch blocks
8. **Unit Tests**: Test edge cases and boundary conditions

---

**Generated**: May 5, 2026
**Version**: 1.0
**Purpose**: Crash Analysis and Testing

