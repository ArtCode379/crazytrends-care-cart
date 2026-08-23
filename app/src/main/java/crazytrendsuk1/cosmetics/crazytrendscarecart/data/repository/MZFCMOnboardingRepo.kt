package crazytrendsuk1.cosmetics.crazytrendscarecart.data.repository

import crazytrendsuk1.cosmetics.crazytrendscarecart.data.datastore.MZFCMOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MZFCMOnboardingRepo(
    private val mzfcmOnboardingStoreManager: MZFCMOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return mzfcmOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            mzfcmOnboardingStoreManager.setOnboardedState(state)
        }
    }
}