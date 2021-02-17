package com.example.changemakers.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CmProperty (
    val id: String,
    val brand: String,
    val name: String,
    val price: Double,
    //@Json(name = "price_sign") val priceSignUrl: String,
    //val currency:String,
    @Json(name = "image_link") val imageLinkUrl: String,
    @Json(name = "product_link") val productLinkUrl: String,
    @Json(name = "website_link") val websiteLinkUrl: String,
    val description: String,
    //val rating: Double,
    //val category:String,
    @Json(name = "product_type") val productType: String,
    //val tag_list:[],
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "product_api_url") val productApiUrl: String,
    @Json(name = "api_featured_image") val apiFeaturedImage: String): Parcelable {
    val isFound
        get() = productType == "foundation"
}
