package crazytrendsuk1.cosmetics.crazytrendscarecart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.dao.CartItemDao
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.dao.OrderDao
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.database.converter.Converters
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.entity.CartItemEntity
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MZFCMDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}