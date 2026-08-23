package crazytrendsuk1.cosmetics.crazytrendscarecart.di

import androidx.room.Room
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.database.MZFCMDatabase
import org.koin.dsl.module

private const val DB_NAME = "mzfcm_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = MZFCMDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<MZFCMDatabase>().cartItemDao() }

    single { get<MZFCMDatabase>().orderDao() }
}