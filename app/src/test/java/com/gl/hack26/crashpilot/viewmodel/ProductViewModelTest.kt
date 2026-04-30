package com.gl.hack26.crashpilot.viewmodel

import org.junit.Test

class ProductViewModelTest {
    @Test
    fun triggerCrash_doesNotThrowWhenIntentionalCrashesAreDisabled() {
        val viewModel = ProductViewModel().apply {
            intentionalCrashesEnabled = false
        }

        viewModel.triggerCrash(5, "Crashy Item 5")
    }

    @Test
    fun triggerListCrash_doesNotThrowWhenIntentionalCrashesAreDisabled() {
        val viewModel = ProductViewModel().apply {
            intentionalCrashesEnabled = false
        }

        viewModel.triggerListCrash()
    }
}
