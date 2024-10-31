package com.example.teststockradars.model

import com.google.gson.annotations.SerializedName

data class PortfolioItem(
	@SerializedName("all")
	val all: Double? = null,

	@SerializedName("image_plan")
	val imagePlan: String? = null,

	@SerializedName("withdrawable_point")
	val withdrawablePoint: Double? = null,

	@SerializedName("image_plan_bg")
	val imagePlanBg: String? = null,

	@SerializedName("cost")
	val cost: Double? = null,

	@SerializedName("pending_point")
	val pendingPoint: Double? = null,

	@SerializedName("enable")
	val enable: Int? = null,

	@SerializedName("change")
	val change: Double? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("plan_id")
	val planId: String? = null,

	@SerializedName("order")
	val order: Int? = null
)
