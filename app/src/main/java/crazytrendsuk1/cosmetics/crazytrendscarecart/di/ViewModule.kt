package crazytrendsuk1.cosmetics.crazytrendscarecart.di

import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.AppViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.CartViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.CheckoutViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.MZFCMOnboardingVM
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.OrderViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.ProductDetailsViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.ProductViewModel
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.MZFCMSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        MZFCMSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        MZFCMOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}