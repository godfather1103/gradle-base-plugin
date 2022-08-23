package com.github.godfather1103.listener

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import java.text.SimpleDateFormat
import java.util.*

class TaskTraceListener(private val target: Project) : TaskExecutionListener, BuildListener {

    private var timeMillis: Long = 0L

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

    private var all: Long = 0L

    companion object {
        var flag: Boolean = false
    }

    override fun beforeExecute(task: Task) {
        timeMillis = System.currentTimeMillis()
        println("${sdf.format(Date())} : 任务${task.project.name}:${task.name}开始执行")
    }

    override fun afterExecute(task: Task, state: TaskState) {
        val t = System.currentTimeMillis() - timeMillis
        all += t
        println("${sdf.format(Date())} : 任务${task.project.name}:${task.name}执行完成,耗时:${convertTime(t)}")
    }

    fun convertTime(t: Long): String {
        val hour = t / (60 * 60 * 1000)
        val minute = (t - hour * (60 * 60 * 1000)) / (60 * 1000)
        val second = (t - hour * (60 * 60 * 1000) - minute * (60 * 1000)) / 1000
        val ms = t % 1000
        val sb = StringBuilder()
        if (hour > 0) {
            sb.append("${hour}小时")
        }
        if (minute > 0) {
            sb.append("${minute}分钟")
        }
        if (second > 0) {
            sb.append("${second}秒")
        }
        if (ms > 0 || sb.isEmpty()) {
            sb.append("${ms}毫秒")
        }
        return sb.toString()
    }

    override fun buildStarted(gradle: Gradle) {
    }

    override fun settingsEvaluated(settings: Settings) {
    }

    override fun projectsLoaded(gradle: Gradle) {
    }

    override fun projectsEvaluated(gradle: Gradle) {
    }

    override fun buildFinished(gradle: BuildResult) {
        println("${sdf.format(Date())} : ${target.name}所有任务执行完成,耗时:${convertTime(all)}")
    }
}