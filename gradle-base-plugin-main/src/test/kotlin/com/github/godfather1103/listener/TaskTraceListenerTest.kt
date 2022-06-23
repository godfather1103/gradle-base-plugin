package com.github.godfather1103.listener

import org.junit.Test


class TaskTraceListenerTest {
    @Test
    fun doSome() {
        val t: Long = 100000126
        println(TaskTraceListener().convertTime(t))
    }
}