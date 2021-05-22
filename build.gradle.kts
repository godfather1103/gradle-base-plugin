plugins {
    kotlin("jvm") version "1.4.32" apply false
}

allprojects {
    group = "${property("plugin.groupId")}"
    version = "${property("plugin.version")}"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}