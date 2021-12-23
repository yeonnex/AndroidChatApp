package com.yeonnex.chatapp

import android.os.Handler

class AsyncDownThread(private val addr: String, private val handler: Handler) : Thread() {
    override fun run() {
        val result = downloadHtml(addr)
        val message = handler.obtainMessage()
        message.obj = result
        handler.sendMessage(message)
    }
    private fun downloadHtml(addr: String): String {
        return "hello html"
    }
}