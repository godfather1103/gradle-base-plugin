buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("$group:${property("plugin.artifactId")}:$version")
    }
}
apply(plugin = "${property("plugin.groupId")}.${property("plugin.artifactId")}")

configure<com.github.godfather1103.ext.BasePluginExtension> {
    param.put("one", "1")
    if (System.getProperty("env") == "ccb") {
        param.put("two", "2")
    }
    files.set(project.projectDir.absolutePath + "/filters/a.properties")
    excludeFilterPattern("*.txt")
    excludeFilterPattern("**/*.docx")
    includeFilterPattern("a.txt")
}