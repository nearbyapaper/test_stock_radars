package com.example.teststockradars.repository


import com.example.teststockradars.model.PortfolioItem
import android.content.Context
import android.util.Log
import com.example.teststockradars.model.Indices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StocksRepository(private val context: Context) {

    private val gson = Gson()

    fun getIndices(): Indices {
        return try {
            // Read the JSON file from assets
            val jsonString = context.assets.open("indices.json").bufferedReader().use { it.readText() }
            // Define the type for Indices
            val indicesType: Type = object : TypeToken<Indices>() {}.type
            // Deserialize the JSON string into Indices
            gson.fromJson(jsonString, indicesType)
        } catch (e: Exception) {
            Log.e("StocksRepository", "Error while parsing indices.json", e)
            throw e
        }
    }

    fun getPortfolio(): List<PortfolioItem> {
        val gson = Gson() // Initialize Gson
        return try {
            // Read the JSON file
            val jsonString = context.assets.open("portfolio.json").bufferedReader().use { it.readText() }
            // Define the type for the list
            val listType: Type = object : TypeToken<List<PortfolioItem>>() {}.type
            // Deserialize the JSON string into a list
            val portfolioData: List<PortfolioItem> = gson.fromJson(jsonString, listType)
            portfolioData
        } catch (e: Exception) {
            Log.e("StocksRepository", "Error while parsing portfolio.json", e)
            throw e
        }
    }
}
