package com.gl.hack26.crashpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl.hack26.crashpilot.adapter.ProductAdapter
import com.gl.hack26.crashpilot.viewmodel.ProductViewModel

class ProductListFragment : Fragment() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.products.observe(viewLifecycleOwner) { productList ->
            val adapter = ProductAdapter(productList) { product ->
                val bundle = Bundle().apply {
                    putInt("PRODUCT_ID", product.id)
                    putString("PRODUCT_NAME", product.product_name)
                    putString("STORE_INFO", product.store_info)
                    putInt("IMAGE_RES", product.imageResId)
                }
                findNavController().navigate(
                    R.id.action_productListFragment_to_productDetailFragment,
                    bundle
                )
            }
            recyclerView.adapter = adapter
        }

        view.findViewById<View>(R.id.btnRefreshProducts).setOnClickListener {
            viewModel.refreshProducts()
        }
    }
}
