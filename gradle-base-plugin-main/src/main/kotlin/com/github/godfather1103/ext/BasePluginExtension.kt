package com.github.godfather1103.ext

import org.gradle.api.Project
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty
import java.io.File
import java.io.FileInputStream
import java.util.*

open class BasePluginExtension(project: Project) {

    /**
     * filter参数值读取的配置文件（properties）
     * */
    val files: Property<String> = project.objects.property(String::class.java)

    /**
     * filter参数值对
     * */
    val param: MapProperty<String, String> = project.objects.mapProperty(String::class.java, String::class.java)

    /**
     * 需要跳过filter的资源文件的Pattern
     * */
    val excludeFilterPatterns: SetProperty<String> = project.objects.setProperty(String::class.java)

    /**
     * 需要进行filter的资源文件的Pattern
     * */
    val includeFilterPatterns: SetProperty<String> = project.objects.setProperty(String::class.java)

    /**
     * 获取filter对应的参数值对<BR>
     *
     * @return 参数值对
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:12
     */
    fun getFilterParams(): HashMap<String, String> {
        val map = HashMap<String, String>()
        if (files.get().isNotEmpty()) {
            map.putAll(getParamsFromFile(files.get()))
        }
        if (param.get().isNotEmpty()) {
            map.putAll(param.get())
        }
        return map
    }

    /**
     * 是否过滤所有<BR>
     *
     * @return 是/否
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:14
     */
    fun isFilterAll(): Boolean {
        return excludeFilterPatterns.get().isEmpty() && includeFilterPatterns.get().isEmpty()
    }

    /**
     * 添加要跳过filter的Pattern<BR>
     *
     * @param pattern 相关pattern（ant-style）
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:15
     */
    fun excludeFilterPattern(pattern: String) {
        excludeFilterPatterns.add(pattern)
    }

    /**
     * 添加要filter的Pattern<BR>
     *
     * @param pattern 相关pattern（ant-style）
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:15
     */
    fun includeFilterPattern(pattern: String) {
        includeFilterPatterns.add(pattern)
    }

    /**
     * 添加filter的参数值对<BR>
     *
     * @param key 主键
     * @param value 值
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:16
     */
    fun addParam(key: String, value: String) {
        param.put(key, value)
    }

    /**
     * 设置读取配置项的配置文件<BR>
     *
     * @param filePath 文件
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:16
     */
    fun setFiles(filePath: String) {
        files.set(filePath)
    }

    /**
     * 从配置文件中获取相关配置项<BR>
     *
     * @param filePath 文件
     * @return 配置项列表
     * @author 作者: Jack Chu E-mail: chuchuanbao@gmail.com
     * 创建时间：2021/7/1 13:16
     */
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