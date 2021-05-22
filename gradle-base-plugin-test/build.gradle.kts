buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("$group:${property("plugin.artifactId")}:$version")
    }
}
apply(plugin = "${property("plugin.groupId")}.${property("plugin.artifactId")}")
