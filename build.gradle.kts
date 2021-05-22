plugins {
    kotlin("jvm") version "1.4.32" apply false
}

group = "${property("plugin.groupId")}"
version = "${property("plugin.version")}"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}