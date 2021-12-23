package com.yeonnex.lib

suspend fun  doSomething(threadName: String) {
    for ( i in 0..100) {
        println("안녕하세요 $threadName 입니다.")
        delay(10L)
    }
}

fun main(){
    GlobalScope.launch {
        doSomething("멋진 코루틴")
    }
    GlobalScope.launch {
        doSomething("예쁜 코루틴")
    }
    Thread.sleep(1000L)

}