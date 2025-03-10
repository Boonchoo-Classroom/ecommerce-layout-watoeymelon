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
            add(ProductModel("Samsung Galaxy S25+", "Galaxy AI  ผู้ช่วยส่วนตัวที่ทำเรื่องยากให้ง่ายไปหมด", 36900.00, R.drawable.a))
            add(ProductModel("Xiaomi Redmi Note 14Pro+", "สมาร์ทโฟนที่ตอบโจทย์ทุกไลฟ์สไตล์ของคนรักการถ่ายภาพ", 14990.00, R.drawable.b))
            add(ProductModel("vivo X200", "ดีไซน์ล้ำสมัย สวยหรูหรา รับรองการถ่ายภาพของคุณออกมาสวย", 29999.00, R.drawable.c))
            add(ProductModel("Realme 13+ ", " ดีไซน์สวยทันสมัย หน้าจอใหญ่จุใจ ความละเอียด FHD+ ", 11999.00, R.drawable.d))
            add(ProductModel("Xiaomi Redmi Note 14", "สมาร์ทโฟนที่ตอบโจทย์การถ่ายภาพอย่างแท้จริง", 9999.00, R.drawable.e))
            add(ProductModel("vivo X200 Pro", "ประสบการณ์ที่ลื่นไหล ความจุที่เพิ่มขึ้นช่วยให้สลับระหว่างแอปต่างๆ", 39999.00, R.drawable.f))
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
