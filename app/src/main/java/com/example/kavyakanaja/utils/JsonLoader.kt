package com.example.kavyakanaja.utils

import android.content.Context
import com.example.kavyakanaja.model.Poem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadPoems(context: Context): List<Poem> {
    val json = context.assets.open("poems.json")
        .bufferedReader()
        .use { it.readText() }

    val type = object : TypeToken<List<Poem>>() {}.type
    return Gson().fromJson(json, type)
}
