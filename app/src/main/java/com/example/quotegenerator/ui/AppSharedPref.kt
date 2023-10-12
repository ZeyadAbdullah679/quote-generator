package com.example.quotegenerator.ui

import android.content.Context
import android.content.SharedPreferences

class AppSharedPref(
    context: Context
) {
    private val key = "ids"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveIdsList(ids: List<String>) {
        val editor = sharedPreferences.edit()
        val idSet = HashSet(ids)
        editor.putStringSet(key, idSet)
        editor.apply()
    }

    fun getIdsList(): List<String> {
        val idSet = sharedPreferences.getStringSet(key, null)
        return idSet?.toList() ?: emptyList()
    }

    fun addIdToList(id: String) {
        val currentIds = getIdsList().toMutableList()
        currentIds.add(id)
        saveIdsList(currentIds)
    }

    fun removeIdFromList(id: String) {
        val currentIds = getIdsList().toMutableList()
        currentIds.remove(id)
        saveIdsList(currentIds)
    }
}