package com.gl.hack26.crashpilot.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * CrashSimulator provides realistic, human-like crash scenarios that commonly occur
 * in real-world Android applications. These are NOT intentionally created crashes,
 * but rather scenarios that developers might encounter due to common coding mistakes
 * or edge cases.
 */
@Suppress("UNUSED_VARIABLE", "DIVISION_BY_ZERO", "UNCHECKED_CAST")
object CrashSimulator {

    /**
     * Simulates a race condition crash - common when multiple threads access shared data
     */
    fun simulateRaceConditionCrash() {
        val sharedList = mutableListOf("Item1", "Item2", "Item3")
        Thread {
            // Simulating concurrent modifications
            for (i in 0..10) {
                if (i % 3 == 0) {
                    sharedList.add("NewItem$i")
                }
            }
        }.start()

        // Access list while another thread might be modifying it
        for (item in sharedList) {
            val processedItem = item.uppercase()
        }
    }

    /**
     * Simulates memory leak and OutOfMemoryError crash
     * This would occur when app tries to allocate huge amount of memory
     */
    fun simulateMemoryPressureCrash() {
        try {
            val hugeList = mutableListOf<ByteArray>()
            // Try to allocate large chunks (will likely fail gracefully)
            repeat(1000) {
                hugeList.add(ByteArray(1024 * 1024)) // 1 MB each
            }
        } catch (e: OutOfMemoryError) {
            throw RuntimeException("Memory allocation failed", e)
        }
    }

    /**
     * Simulates common JSON/API parsing error
     */
    fun simulateJsonParsingCrash(jsonString: String) {
        try {
            // Attempting to parse malformed JSON
            val parts = jsonString.split(",")
            val key = parts[0].split(":")[0]
            val value = parts[1].split(":")[1]
            val parsedValue = value.toInt()
        } catch (e: Exception) {
            throw RuntimeException("Failed to parse JSON response", e)
        }
    }

    /**
     * Simulates date/time formatting crash with locale issues
     */
    fun simulateDateFormatCrash(dateString: String) {
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val date = formatter.parse(dateString)
            // Simulating timezone conversion issue
            val milliseconds = date!!.time / 0 // ArithmeticException
        } catch (e: Exception) {
            throw RuntimeException("Date formatting error", e)
        }
    }

    /**
     * Simulates weak reference cleanup issue
     */
    fun simulateWeakReferenceCrash() {
        var tempObject: String? = "Temporary Data"
        System.gc() // Force garbage collection
        // Reference might be cleared, causing null pointer
        val length = tempObject!!.length
    }

    /**
     * Simulates Fragment lifecycle state inconsistency crash
     */
    fun simulateFragmentLifecycleCrash() {
        var isFragmentVisible = false
        var onDestroyCalled = true

        // Attempting to perform operation after fragment destroyed
        if (!onDestroyCalled) {
            throw IllegalStateException("Fragment has been destroyed")
        }
        if (isFragmentVisible) {
            // This would never execute but represents common lifecycle bug
            val data = "View Data"
        }
    }

    /**
     * Simulates resource not found crash (common with drawable/layout resources)
     */
    fun simulateResourceNotFoundCrash(context: Context, resourceId: Int) {
        try {
            val resource = context.resources.getResourceName(resourceId)
            // Attempting to load non-existent resource
            val density = context.resources.displayMetrics.density
            // Attempt to access invalid resource ID
            @Suppress("DiscouragedApi")
            val dimension = context.resources.getIdentifier("nonexistent_resource", "dimen", context.packageName)
            if (dimension <= 0) {
                throw RuntimeException("Resource not found: nonexistent_resource")
            }
        } catch (e: Exception) {
            throw RuntimeException("Resource not found: $resourceId", e)
        }
    }

    /**
     * Simulates Realm/Database corruption crash during transaction
     */
    fun simulateDatabaseTransactionCrash() {
        var transactionStarted = false
        try {
            transactionStarted = true
            // Simulating database write failure
            val writeCount = 0
            val division = 100 / writeCount // ArithmeticException during transaction
        } finally {
            // Attempting cleanup on corrupted transaction
            if (transactionStarted) {
                // Database might be in inconsistent state
            }
        }
    }

    /**
     * Simulates SharedPreferences corruption crash
     */
    fun simulatePreferencesCorruptionCrash(context: Context) {
        try {
            val prefs = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            val serializedData = prefs.getString("complex_object", null)

            // Attempting to deserialize corrupted data
            val parts = serializedData!!.split("|")
            val userId = parts[5].toInt() // IndexOutOfBoundsException if data corrupted
        } catch (e: Exception) {
            throw RuntimeException("SharedPreferences corruption detected", e)
        }
    }

    /**
     * Simulates EditText text watcher crash from removed listener
     */
    fun simulateTextWatcherCrash() {
        // Common crash when TextWatcher is removed but still referenced
        val listeners = mutableListOf<String>("OnTextChange", "BeforeTextChange")

        for (listener in listeners) {
            if (listener == "OnTextChange") {
                listeners.remove(listener) // Unsafe iteration
            }
        }
    }

    /**
     * Simulates WorkManager/JobScheduler crash
     */
    fun simulateSchedulerCrash() {
        val jobQueue = mutableListOf<String>()
        var jobId = 0

        if (jobId < 0) {
            throw IllegalArgumentException("Job ID cannot be negative")
        }

        if (jobQueue.isEmpty()) {
            val job = jobQueue[0] // IndexOutOfBoundsException
        }
    }

    /**
     * Simulates AnimatorListener callback crash
     */
    fun simulateAnimationCrash() {
        val animationState: String? = null

        when {
            animationState == "RUNNING" -> {
                val duration = animationState.length // NullPointerException
            }
            animationState == "COMPLETED" -> {
                // Callback might fire after fragment destroyed
            }
            else -> {
                throw IllegalStateException("Unknown animation state: $animationState")
            }
        }
    }

    /**
     * Simulates retrofit/OkHttp response parsing crash
     */
    fun simulateHttpResponseCrash(responseBody: String) {
        try {
            val statusLines = responseBody.split("\n")
            val statusCode = statusLines[0].toInt()

            // Attempting to parse error response
            val errorMessage = statusLines[2] // Might not exist
            val timestamp = System.currentTimeMillis() / 0 // ArithmeticException
        } catch (e: Exception) {
            throw RuntimeException("Failed to parse HTTP response", e)
        }
    }

    /**
     * Simulates bitmap loading/scaling crash
     */
    fun simulateBitmapCrash(width: Int, height: Int) {
        try {
            // Common crash with invalid dimensions
            val area = width * height
            val byteSize = area * 4 // RGBA

            if (width == 0 || height == 0) {
                throw IllegalArgumentException("Invalid bitmap dimensions: $width x $height")
            }

            // Attempting to allocate bitmap with bad dimensions
            val scale = width / 0 // ArithmeticException
        } catch (e: Exception) {
            throw RuntimeException("Bitmap allocation failed", e)
        }
    }

    /**
     * Simulates TypeToken/Generics erasure crash
     */
    fun simulateGenericsErasureCrash() {
        val objectList: Any = listOf("item1", 2, "item3")

        // Type checking fails due to generics erasure
        @Suppress("UNCHECKED_CAST")
        val intList = (objectList as List<Int>)

        // This will fail at runtime
        val sum = intList.map { it * 2 }.sum()
    }

    /**
     * Simulates Service/foreground notification crash
     */
    fun simulateServiceNotificationCrash() {
        val channelId: String? = null
        val notificationId = -1

        if (notificationId < 0) {
            throw IllegalArgumentException("Notification ID must be positive")
        }

        // Missing notification channel on Android 8+
        val channel = channelId ?: throw RuntimeException("Notification channel not initialized")
    }
}


