package com.gl.hack26.crashpilot.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.gl.hack26.crashpilot.BuildConfig
import com.gl.hack26.crashpilot.Product
import com.gl.hack26.crashpilot.R
import com.gl.hack26.crashpilot.viewmodel.ProductViewModel

class ProductAdapter(
    private val productList: List<Product>,
    private val viewModel: ProductViewModel,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        if (BuildConfig.DEBUG) {
            viewModel.triggerRandomCrash(product.id, isDetailView = false)
        }
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)
        private val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        private val tvStoreInfo: TextView = itemView.findViewById(R.id.tvStoreInfo)

        fun bind(product: Product) {
            tvProductName.text = product.product_name
            tvStoreInfo.text = product.store_info
            
            ivProduct.load("https://picsum.photos/seed/${product.id}/200/200") {
                crossfade(true)
                placeholder(product.imageResId)
                error(product.imageResId)
                transformations(CircleCropTransformation())
            }

            itemView.setOnClickListener { onItemClick(product) }
        }
    }
}
