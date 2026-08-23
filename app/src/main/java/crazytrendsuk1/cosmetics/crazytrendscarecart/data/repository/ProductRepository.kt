package crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository

import crazytrendsuk1.cosmetics.crazytrendscarecart.data.model.Product
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            1,
            "Rose Cloud Cleanser",
            "A gentle cream cleanser with rose water and oat lipids that lifts makeup without stripping the skin. Massage onto damp skin for a calm finish.",
            ProductCategory.SKINCARE,
            18.00,
            "https://images.unsplash.com/photo-1556229010-6c3f2c9ca5f8?w=1200",
        ),
        Product(
            2,
            "Vitamin C Glow Serum",
            "A brightening daily serum pairing stable vitamin C with hydrating hyaluronic acid. The silky formula layers beautifully under moisturiser.",
            ProductCategory.SKINCARE,
            26.00,
            "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?w=1200",
        ),
        Product(
            3,
            "Barrier Rescue Cream",
            "Rich ceramides, squalane and panthenol cushion dry skin and support its natural moisture barrier. Made for night-time comfort.",
            ProductCategory.SKINCARE,
            24.00,
            "https://images.unsplash.com/photo-1570194065650-d99fb4b38b1c?w=1200",
        ),
        Product(
            4,
            "Botanical Shine Shampoo",
            "A sulphate-free wash with rosemary and rice protein for a fresh scalp and glossy lengths. Suitable for colour-treated hair.",
            ProductCategory.HAIRCARE,
            16.50,
            "https://images.unsplash.com/photo-1608248543803-ba4f8c70ae0b?w=1200",
        ),
        Product(
            5,
            "Silk Repair Hair Mask",
            "A weekly moisture treatment with argan oil and plant proteins. Leave on for ten minutes to soften stressed ends.",
            ProductCategory.HAIRCARE,
            21.00,
            "https://images.unsplash.com/photo-1527799820374-dcf8d9d4a388?w=1200",
        ),
        Product(
            6,
            "Coconut Body Polish",
            "Fine sugar crystals and coconut oil buff away roughness while leaving skin supple. A sunny scent turns shower time into a ritual.",
            ProductCategory.BODY,
            15.00,
            "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?w=1200",
        ),
        Product(
            7,
            "Velvet Body Lotion",
            "A fast-absorbing shea and almond lotion with a soft powdery finish. Smooth over clean skin to lock in moisture.",
            ProductCategory.BODY,
            17.50,
            "https://images.unsplash.com/photo-1619451334792-150fd785ee74?w=1200",
        ),
        Product(
            8,
            "Soft Focus Blush",
            "A buildable mineral blush that blends from a delicate flush to a richer evening look with a smooth luminous finish.",
            ProductCategory.MAKEUP,
            19.00,
            "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=1200",
        ),
        Product(
            9,
            "Nude Satin Lip Colour",
            "Comfortable satin lipstick in a versatile rose-nude shade. Nourishing oils keep lips feeling soft for hours.",
            ProductCategory.MAKEUP,
            14.00,
            "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=1200",
        ),
        Product(
            10,
            "Amber Bloom Eau de Parfum",
            "A warm, modern blend of bergamot, jasmine and amber woods in a travel-friendly bottle for everyday wear.",
            ProductCategory.FRAGRANCE,
            38.00,
            "https://images.unsplash.com/photo-1541643600914-78b084683601?w=1200",
        ),
        Product(
            11,
            "Mineral Daily SPF 30",
            "A sheer mineral sunscreen with niacinamide that protects while leaving a comfortable satin finish.",
            ProductCategory.SKINCARE,
            22.00,
            "https://images.unsplash.com/photo-1571781926291-c477ebfd024b?w=1200",
        ),
        Product(
            12,
            "Lavender Bath Soak",
            "Mineral-rich salts scented with lavender and cedar help create a soothing evening bath.",
            ProductCategory.BODY,
            13.50,
            "https://images.unsplash.com/photo-1607006483225-31a7609a5d4d?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
