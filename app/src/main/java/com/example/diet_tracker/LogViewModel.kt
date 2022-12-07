package com.example.diet_tracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class LogViewModel : ViewModel() {

    private var calories = 0

    @RequiresApi(Build.VERSION_CODES.O)
    private var date = LocalDate.now()

    private var servings = 0




    // TODO: Implement the ViewModel
}