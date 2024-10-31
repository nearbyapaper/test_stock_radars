package com.example.teststockradars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teststockradars.model.Indices
import com.example.teststockradars.model.PortfolioItem
import com.example.teststockradars.repository.StocksRepository

class StocksViewModel(
    private val repository: StocksRepository
) : ViewModel() {

    private var _indices = MutableLiveData<Indices>()
    val indices: LiveData<Indices>
        get() = _indices

    private var _portfolio = MutableLiveData<List<PortfolioItem>>()
    val portfolio: LiveData<List<PortfolioItem>>
        get() = _portfolio

    fun callIndices(){
        val indicesData = repository.getIndices()
        _indices.postValue(indicesData)
    }

    fun callPortfolio(){
        val portfolioData = repository.getPortfolio()
        _portfolio.postValue(portfolioData)
    }
}