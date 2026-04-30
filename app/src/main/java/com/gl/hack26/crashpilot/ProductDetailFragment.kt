package com.gl.hack26.crashpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.gl.hack26.crashpilot.viewmodel.ProductViewModel

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivProduct = view.findViewById<ImageView>(R.id.ivDetailProduct)
        val tvName = view.findViewById<TextView>(R.id.tvDetailName)
        val tvDescription = view.findViewById<TextView>(R.id.tvDetailDescription)
        val btnCrash = view.findViewById<Button>(R.id.btnCrash)

        val productId = arguments?.getInt("PRODUCT_ID", 0) ?: 0
        val productName = arguments?.getString("PRODUCT_NAME")
        val storeInfo = arguments?.getString("STORE_INFO")
        val imageResId = arguments?.getInt("IMAGE_RES", android.R.drawable.ic_menu_report_image) ?: android.R.drawable.ic_menu_report_image

        tvName.text = productName
        tvDescription.text = "This $productName is from $storeInfo. It is highly experimental and might cause your system to crash!"
        
        // Use Coil for random image
        ivProduct.load("https://picsum.photos/seed/${productId}/400/400") {
            crossfade(true)
            placeholder(imageResId)
            error(imageResId)
            transformations(CircleCropTransformation())
        }

        btnCrash.setOnClickListener {
            viewModel.triggerCrash(productId, productName)
        }
    }
}
