package com.example.teststockradars.model

import com.google.gson.annotations.SerializedName

data class Indices(

	@SerializedName("data")
	val data: List<DataItem>? = null,

	@SerializedName("last_update")
	val lastUpdate: String? = null
)

data class DataItem(

	@SerializedName("symbol")
	val symbol: String? = null,

	@SerializedName("price")
	val price: Double? = null,

	@SerializedName("change")
	val change: Double? = null,

	@SerializedName("short_name")
	val shortName: String? = null,

	@SerializedName("percent_change")
	val percentChange: Double? = null
)
