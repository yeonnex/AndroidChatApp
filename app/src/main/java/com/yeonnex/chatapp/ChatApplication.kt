package com.yeonnex.chatapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class ChatApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}
class  PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("chat", 0)

    fun getString(key: String, defValue: String): String{
        return prefs.getString(key, defValue).toString()
    }
    fun setString(key: String, str:String){
        prefs.edit().putString(key, str).apply()
    }
}