package com.github.godfather1103

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import java.io.File

class GradleBasePlugin : Plugin<Project> {
    /**
     * Apply this plugin to the given target object.
     *
     * @param target The target object
     */
    override fun apply(target: Project) {
        target.tasks.getByName("clean").doFirst {
            // 清除maven的生成目录
            var file = File(target.path + "/target")
            if (file.exists()) {
                deleteFileDir(file)
            }
            // 清除日志的目录
            file = File(target.path + "/logs")
            if (file.exists()) {
                deleteFileDir(file)
            }
        }
        target.tasks.withType(JavaCompile::class.java) { task ->
            run {
                task.options.encoding = "UTF-8"
                task.options.compilerArgs.add("-Xlint:none")
            }

        }
    }

    private fun deleteFileDir(f: File) {
        if (f.isDirectory) {
            f.listFiles().forEach { file ->
                deleteFileDir(file)
            }
        }
        f.delete()
    }
}