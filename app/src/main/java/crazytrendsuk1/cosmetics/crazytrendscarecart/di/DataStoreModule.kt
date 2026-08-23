package crazytrendsuk1.cosmetics.crazytrendscarecart.di

import crazytrendsuk1.cosmetics.crazytrendscarecart.data.datastore.MZFCMOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { MZFCMOnboardingPrefs(androidContext()) }
}