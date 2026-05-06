# CrashPilot_Clone - Usage Guide

## Quick Start

### Viewing Crashes in Action

The crash system works automatically:
1. **Open the app** - CrashPilot_Clone loads the product list
2. **Navigate** - Scroll through products or view details
3. **Random crashes** - Crashes occur randomly based on probability
4. **Firebase reports** - Each crash is automatically sent to Firebase Crashlytics

---

## How It Works

### Automatic Crash Triggering

**In ProductListFragment:**
```kotlin
// When list items are bound to the UI (5% chance per item)
viewModel.triggerRandomCrash(product.id, isDetailView = false)
```

**In ProductDetailFragment:**
```kotlin
// When viewing product details (20% chance per view)
viewModel.triggerRandomCrash(productId, isDetailView = true)
```

### Crash Selection

Each product has an ID (1-15). The crash type is determined by:
```kotlin
when (productId % 24) {
    0 -> ArithmeticException (division by zero)
    1 -> IndexOutOfBoundsException
    2 -> NumberFormatException
    ... (and so on up to case 23)
}
```

This means:
- Product ID 1: Will crash with case 1
- Product ID 25: Will crash with case 1 (25 % 24 = 1)
- Product ID 100: Will crash with case 4 (100 % 24 = 4)

---

## Using CrashSimulator Directly

The `CrashSimulator` utility can be used directly for testing specific crashes:

### Example 1: Simulate JSON Parsing Crash
```kotlin
import com.gl.hack26.crashpilot.util.CrashSimulator

// In your activity or fragment
CrashSimulator.simulateJsonParsingCrash("{\"price\":\"abc\"}")
```

### Example 2: Test Date Formatting Crash
```kotlin
CrashSimulator.simulateDateFormatCrash("32/13/2024")
```

### Example 3: Test HTTP Response Parsing
```kotlin
CrashSimulator.simulateHttpResponseCrash("Status: 500\nError")
```

### Example 4: Test Bitmap Operations
```kotlin
CrashSimulator.simulateBitmapCrash(width = 0, height = 0)
```

### Example 5: Test Race Conditions
```kotlin
CrashSimulator.simulateRaceConditionCrash()
```

---

## Crash Categories

### Data Structure Crashes (4 crashes)
- **IndexOutOfBoundsException**: Array/List access
- **ArrayIndexOutOfBoundsException**: Array bounds
- **StringIndexOutOfBoundsException**: String index
- **NoSuchElementException**: Empty collection

### Type & Casting Crashes (3 crashes)
- **ClassCastException**: Unsafe casting
- **IllegalArgumentException**: Wrong type argument
- **NumberFormatException**: String to number conversion

### Null & State Crashes (3 crashes)
- **NullPointerException**: Null object access
- **IllegalStateException**: Invalid state operation
- **AssertionError**: Assertion failure

### Collection & Iteration (2 crashes)
- **ConcurrentModificationException**: Unsafe iteration
- **UnsupportedOperationException**: Immutable collection

### Calculation & Logic (2 crashes)
- **ArithmeticException**: Division by zero
- **ArithmeticException (variations)**: Calculated zero division

### API & Parsing (3 crashes)
- **NumberFormatException**: Malformed JSON
- **IllegalArgumentException**: Invalid enum
- **Multiple exceptions**: Response parsing

### Advanced Scenarios (17 crashes)
Via **CrashSimulator** utility

---

## Firebase Crashlytics Integration

All crashes are automatically captured and reported to Firebase:

### Viewing Crashes in Firebase Console

1. **Open Firebase Console** → Your CrashPilot_Clone project
2. **Go to Crashlytics**
3. **See Live Issues** - All crashes appear in real-time
4. **Click on a crash** to see:
   - Stack trace
   - Affected users
   - Device information
   - App version
   - System logs

### Example Crash Report in Firebase
```
Issue: ArithmeticException / by zero
First Seen: Today, 2:34 PM
Affected Versions: 1.0 (1 build)
Number of Events: 2
Affected Users: 1
Crash Rate: High

Stack Trace:
at com.gl.hack26.crashpilot.viewmodel.ProductViewModel
    .triggerRandomCrash(ProductViewModel.kt:38)
at com.gl.hack26.crashpilot.ProductDetailFragment
    .onViewCreated(ProductDetailFragment.kt:42)
...
```

---

## Testing Scenarios

### Test Scenario 1: Trigger Multiple Different Crashes
**Steps:**
1. Open app
2. View Product 1 (case 1)
3. Go back and view Product 2 (case 2)
4. Go back and view Product 3 (case 3)
5. Keep viewing different products
6. Check Firebase for different crash types

**Expected Result:** Different exceptions in Crashlytics

### Test Scenario 2: Test High Crash Rate
**Steps:**
1. Open app
2. Quickly scroll through entire list multiple times
3. Rapidly open and close product details
4. Check Firebase reports

**Expected Result:** Many crash reports in Firebase

### Test Scenario 3: Verify List vs Detail Probability
**Steps:**
1. Note: List binding = 5% chance, Detail view = 20% chance
2. Scroll list 100 times, count crashes
3. Open details 50 times, count crashes
4. Detail view should have higher crash rate

**Expected Result:** Detail crashes occur more frequently

### Test Scenario 4: Test Specific Crash Type
**Steps:**
1. Find which product ID gives desired crash
   - Product 4: ConcurrentModificationException
   - Product 8: IllegalStateException
   - Product 15: Enum crash
2. Repeatedly view that product detail
3. Observe same crash type repeating
4. Verify in Firebase

**Expected Result:** Same crash repeated for same product

---

## Emulating Different Devices

### Test on Low Memory Device
- Use a low-end emulator with 512MB RAM
- Memory pressure crashes will trigger more frequently

### Test on Different API Levels
- Test on Android 6.0 (API 23)
- Test on Android 10+ (API 29+)
- Some crashes may behave differently by API

### Test with Network Simulation
- Enable network throttling in Android Studio
- Timeout scenarios will be hit more often

---

## Advanced: Customizing Crash Behavior

### Modify Crash Probability

Edit `ProductViewModel.kt`:
```kotlin
// Change from 20% detail, 5% list to different values
val crashThreshold = if (isDetailView) 50 else 15  // 50% and 15%
```

### Add New Crash Type

Edit `ProductViewModel.kt` and expand when statement:
```kotlin
when (productId % 26) {  // Changed from 24 to 26
    // ... existing cases ...
    24 -> {
        // Your new crash here
        throw YourCustomException("Custom crash scenario")
    }
    25 -> {
        // Another custom crash
    }
}
```

### Disable Crashes Temporarily

Comment out trigger in fragments:
```kotlin
// viewModel.triggerRandomCrash(productId, isDetailView = true)
```

### Trigger Specific Crash Manually

In activity/fragment:
```kotlin
// Force a specific crash type
val product1 = reposito.getProductById(1)  // productId=1 → case 1
val product8 = repository.getProductById(8)  // productId=8 → case 8
```

---

## Debugging Crashes

### View Stack Trace Locally

When crash occurs, logcat shows:
```
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.gl.hack26.crashpilot, PID: 1234
    java.lang.ArithmeticException: / by zero
        at com.gl.hack26.crashpilot.viewmodel.ProductViewModel
            .triggerRandomCrash(ProductViewModel.kt:38)
        at ...
```

### Enable Verbose Logging

In Build Variants, select Debug build to see full logs

### Use Debugger

1. Set breakpoint before crash point
2. Run with debugger
3. Step through code to see exact cause

---

## Common Questions

### Q: Why did my crash not report to Firebase?
**A:** 
- Requires internet connection
- Firebase must be properly configured
- Check google-services.json is in app folder
- Check Firebase console has project

### Q: Can I see crashes before they happen?
**A:** 
- Yes, check Firebase Crashlytics "Issues" tab
- View "Alerts" to get notified of new crashes automatically

### Q: How do I test a specific crash?
**A:** 
- Calculate productId: (desiredCase) or (desiredCase + 24) or (desiredCase + 48)...
- View that product's detail page

### Q: Can I share crash data?
**A:** 
- Yes, Firebase console has export/sharing features
- Share crash reports with your team

---

## Best Practices for Testing

1. **Use Clean Device/Emulator**
   - Clear app data before testing
   - Fresh Firebase session each time

2. **Test Multiple Times**
   - Crashes are random, test multiple times to see patterns
   - Some crashes might not trigger due to probability

3. **Monitor Firebase**
   - Keep Firebase console open during testing
   - Watch crashes appear in real-time

4. **Document Results**
   - Take screenshots of crashes
   - Note crash patterns observed
   - Compare with expected behavior

5. **Test Edge Cases**
   - Test on different devices
   - Test with different locales
   - Test with different timezones

---

## Support & Troubleshooting

### Crash Not Triggering?
- Check probability: 5% list, 20% detail (1 in 5-20 chance)
- Try multiple times, it's random
- View Firebase to confirm crashes exist
- Check logcat for error messages

### Firebase Not Showing Crashes?
- Ensure internet connectivity
- Check Firebase project is correctly configured
- Verify google-services.json exists
- Check Firebase plugin in build.gradle

### Need to Modify Crashes?
- Edit ProductViewModel.kt for main crashes (cases 0-23)
- Edit CrashSimulator.kt for utility crashes
- Remember to rebuild after changes

---

## Next Steps

1. **Analyze Patterns** - Run the app and collect crash data
2. **Share Data** - Export crash reports from Firebase
3. **Improve Detection** - Use crash data to improve error handling
4. **Test Fixes** - Disable crashes and verify fixes work

---

**Document Version**: 1.0
**Last Updated**: May 5, 2026
**For**: CrashPilot_Clone Android Project

