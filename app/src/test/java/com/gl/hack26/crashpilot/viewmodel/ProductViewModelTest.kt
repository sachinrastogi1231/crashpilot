package com.gl.hack26.crashpilot.viewmodel

import org.junit.Assert.assertFalse
import org.junit.Test

class ProductViewModelTest {
    @Test
    fun refreshProducts_doesNotThrowAndKeepsProductsLoaded() {
        val viewModel = ProductViewModel()

        viewModel.refreshProducts()

        assertFalse(viewModel.products.value.orEmpty().isEmpty())
    }
}
