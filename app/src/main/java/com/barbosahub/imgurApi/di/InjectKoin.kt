package com.barbosahub.imgurApi.di

import com.barbosahub.imgurApi.MainApplication.Companion.BASE_URL
import com.barbosahub.imgurApi.network.RetrofitInstance
import com.barbosahub.imgurApi.network.repository.GalleryRepository
import com.barbosahub.imgurApi.ui.home.viewModel.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    // Repositories
    single{ GalleryRepository(BASE_URL) }
    //ViewModels
    viewModel { GalleryViewModel(get()) }
    //Retrofit
    single { RetrofitInstance }
}