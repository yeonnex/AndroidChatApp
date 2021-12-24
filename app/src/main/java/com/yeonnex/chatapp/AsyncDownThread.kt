package com.yeonnex.chatapp

import android.os.Handler
import java.io.IOException
import java.net.MalformedURLException

class AsyncDownThread(private val addr: String, private val handler: Handler) : Thread() {
    override fun run() {
        val result = downloadHtml(addr)
        val message = handler.obtainMessage()
        message.obj = result
        handler.sendMessage(message)
    }
    private fun downloadHtml(addr: String): String {
        val html = ""
        try {

        } catch (e:MalformedURLException) {
            e.printStackTrace() // 로그 남기자
        } catch (e: IOException) {
            e.printStackTrace() // 로그 남기자
        }
        return "hello html"
    }
}