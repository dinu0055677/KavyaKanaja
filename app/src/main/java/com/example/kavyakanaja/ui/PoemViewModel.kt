package com.example.kavyakanaja.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kavyakanaja.model.Poem
import com.example.kavyakanaja.utils.loadPoems
import java.util.*

class PoemViewModel(application: Application) : AndroidViewModel(application) {

    private val poems: List<Poem> = loadPoems(application)

    fun getPoemOfDay(): Poem {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return poems[day % poems.size]
    }

    fun getAllPoems(): List<Poem> = poems
}
