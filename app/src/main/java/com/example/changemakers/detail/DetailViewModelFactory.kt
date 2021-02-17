package com.example.changemakers.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.changemakers.network.CmProperty


class DetailViewModelFactory(
    private val cmProperty: CmProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(cmProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
