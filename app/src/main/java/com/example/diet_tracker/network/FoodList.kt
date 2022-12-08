package com.example.diet_tracker.network

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class FoodList(
    @SerializedName("data")
    var data: List<Food>,

    @SerializedName("fields")
    var fieldList : JSONObject,
)
