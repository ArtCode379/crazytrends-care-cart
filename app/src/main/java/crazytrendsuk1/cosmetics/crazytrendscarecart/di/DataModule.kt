package crazytrendsuk1.cosmetics.crazytrendscarecart.di

import crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository.CartRepository
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository.MZFCMOnboardingRepo
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository.OrderRepository
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        MZFCMOnboardingRepo(
            mzfcmOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}