package com.example.diet_tracker

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.diet_tracker.network.NutritionixApi
import kotlinx.coroutines.launch


class FoodViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getNutritionixResults()
    }

    private fun getNutritionixResults() {
        viewModelScope.launch {
            try {
                val listResult = NutritionixApi.retrofitService.getResults("cheddar cheese")
                Log.w(ContentValues.TAG, listResult.toString())
                _status.value = "Success: $listResult results retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }


}