plugins {
    kotlin("jvm") version "1.4.32" apply false
}

subprojects {
    group = "${property("plugin.groupId")}"
    version = "${property("plugin.version")}"
    apply(plugin = "org.jetbrains.kotlin.jvm")
}