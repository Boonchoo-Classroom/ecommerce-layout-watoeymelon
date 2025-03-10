package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class ProductListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_product_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ✅ Mock Data สำหรับแสดงผลสินค้า
        productList.apply {
            add(ProductModel("Puma Speedcat (Red)", "Galaxy AI  ผู้ช่วยส่วนตัวที่ทำเรื่องยากให้ง่ายไปหมด", 3800.00, R.drawable.a))
            add(ProductModel("Puma Speedcat (Brown)", "สมาร์ทโฟนที่ตอบโจทย์ทุกไลฟ์สไตล์ของคนรักการถ่ายภาพ", 3800.00, R.drawable.b))
            add(ProductModel("Puma Palermo ", "รองเท้าสุดคลาสสิค", 3500.00, R.drawable.c))
            add(ProductModel("PUMA Unisex Short Socks 3 Pack", " ถุงเท้าสุดคลู ", 450.00, R.drawable.e))
            add(ProductModel("เสื้อยืดผู้ชาย Essentials Logo(Cream)", "เสื้อPuma", 900.00, R.drawable.d))
            add(ProductModel("เสื้อยืดผู้ชาย Essentials Logo(Brown)", "เสื้อPuma", 900.00, R.drawable.f))
        }

        // ✅ ตั้งค่า Adapter พร้อม Callback
        adapter = ProductAdapter(productList) { product ->
            toggleFavorite(product)
        }
        recyclerView.adapter = adapter

        return view
    }

    // ✅ ฟังก์ชันจัดการการกดหัวใจ
    private fun toggleFavorite(product: ProductModel) {
        product.isFavorite = !product.isFavorite
        if (product.isFavorite) {
            FavoritesManager.addToFavorites(product) // ✅ เพิ่มสินค้าใน Favorites
        } else {
            FavoritesManager.removeFromFavorites(product) // ✅ ลบออกจาก Favorites
        }
        adapter.notifyDataSetChanged()
    }
}
