package com.yeonnex.lib

import java.util.concurrent.atomic.AtomicInteger

// 동시성 프로그래밍
// 동기식 synchronous
// 비동기식 asynchronous


// 쓰레드 생성 방법 : Thread 클래스를 상속받거나 Runnable 인터페이스를 구현
class SimpleThread(private val threadName: String): Thread() {
    companion object {
        var count = AtomicInteger(0)
    }
    override fun run(){
        for (i in 0..99){
            count.incrementAndGet()
            println("안녕하세요 $threadName 입니다 ${count.get()}")
            Thread.sleep(10)
        }
    }
}

class SimpleRunnableThread(private val threadName: String): Runnable { // Runnable 인터페이스
    override fun run(){
        for (i in 0..99){
            println("안녕하세요 $threadName 입니다")
            Thread.sleep(10)
        }
    }
}

fun main(){
    val thread1 = SimpleThread("멋진 스레드")
    val thread2 = SimpleThread("예쁜 스레드")
    thread1.start()
    thread2.start()

    val runnable = SimpleRunnableThread("나도 스레드")
    val thread3 = Thread(runnable)
    thread3.start()
}