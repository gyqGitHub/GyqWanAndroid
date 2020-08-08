package com.hsb.gyqwanandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hsb.gyqwanandroid.data.HomeRepository
import com.hsb.gyqwanandroid.data.source.remote.HomeDataSource

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                HomeRepository(HomeDataSource())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
