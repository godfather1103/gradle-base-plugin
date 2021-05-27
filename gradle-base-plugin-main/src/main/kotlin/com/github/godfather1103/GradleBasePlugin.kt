package com.github.godfather1103

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.language.jvm.tasks.ProcessResources
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2021</p>
 * <p>Company:      https://github.com/godfather1103</p>
 *
 * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
 * 创建时间：2021/5/15 00:44
 * @version 1.0
 * @since 1.0
 */
class GradleBasePlugin : Plugin<Project> {

    abstract class BasePluginExtension {
        abstract val files: Property<String>
        abstract val param: MapProperty<String, String>

        fun getFilterParams(): HashMap<String, String> {
            val map = HashMap<String, String>()
            if (files.getOrElse("").isNotEmpty()) {
                map.putAll(getParamsFromFile(files.get()))
            }
            if (param.getOrElse(HashMap()).isNotEmpty()) {
                map.putAll(param.get())
            }
            return map
        }

        private fun getParamsFromFile(filePath: String): HashMap<String, String> {
            val map = HashMap<String, String>()
            var file = File(filePath)
            if (file.exists() && file.isFile) {
                var p = Properties()
                p.load(FileInputStream(file))
                p.forEach { t, u ->
                    map[t.toString()] = u.toString()
                }
            }
            return map
        }
    }

    override fun apply(target: Project) {
        target.plugins.apply("java")
        /**
         * 在clean的任务中增加清除maven build目录和日志目录的行为
         * */
        target.tasks.getByName("clean").doFirst {
            // 清除maven的生成目录
            var file = File(target.projectDir.absolutePath + "/target")
            if (file.exists()) {
                deleteFileDir(file)
            }
            // 清除日志的目录
            file = File(target.projectDir.absolutePath + "/logs")
            if (file.exists()) {
                deleteFileDir(file)
            }
        }

        /**
         * 增加Java编译的参数
         * */
        target.tasks.withType(JavaCompile::class.java) { task ->
            run {
                task.options.encoding = "UTF-8"
                task.options.compilerArgs.add("-Xlint:none")
            }
        }

        val extension = target.extensions.create("baseExt", BasePluginExtension::class.java)

        target.tasks.withType(ProcessResources::class.java) { task ->
            run {
                task.doFirst {
                    val p = extension.getFilterParams()
                    task.filter(mapOf("tokens" to p), org.apache.tools.ant.filters.ReplaceTokens::class.java)
                }
            }
        }

    }

    /**
     * 递归删除文件目录<BR>
     *
     * @param f 目标文件夹
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/5/15 00:46
     */
    private fun deleteFileDir(f: File) {
        if (f.isDirectory) {
            f.listFiles().forEach { file ->
                deleteFileDir(file)
            }
        }
        f.delete()
    }
}