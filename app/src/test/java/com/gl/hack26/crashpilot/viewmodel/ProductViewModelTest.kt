package com.gl.hack26.crashpilot.viewmodel

import org.junit.Test

class ProductViewModelTest {
    @Test
    fun triggerCrashDoesNotThrowForCrashyItemFive() {
        val viewModel = ProductViewModel()

        viewModel.triggerCrash(5, "Crashy Item 5")
    }
}
