package com.example.kavyakanaja.utils

import android.content.Context

fun getAudioRes(context: Context, name: String): Int {
    return context.resources.getIdentifier(name.substringBefore("."), "raw", context.packageName)
}
