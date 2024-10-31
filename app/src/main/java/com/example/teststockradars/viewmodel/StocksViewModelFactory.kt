package com.example.teststockradars.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teststockradars.repository.StocksRepository

class StocksViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StocksViewModel::class.java)) {
            return StocksViewModel(
                repository = StocksRepository(context)
            ) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}