package crazytrendsuk1.cosmetics.crazytrendscarecart.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    val quantity: Int,
)