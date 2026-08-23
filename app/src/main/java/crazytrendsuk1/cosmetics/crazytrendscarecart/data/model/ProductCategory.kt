package crazytrendsuk1.cosmetics.crazytrendscarecart.data.model

import androidx.annotation.StringRes
import crazytrendsuk1.cosmetics.crazytrendscarecart.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    SKINCARE(R.string.mzfcm_category_skincare),
    HAIRCARE(R.string.mzfcm_category_haircare),
    BODY(R.string.mzfcm_category_body),
    MAKEUP(R.string.mzfcm_category_makeup),
    FRAGRANCE(R.string.mzfcm_category_fragrance),
}
