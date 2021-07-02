buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("${property("plugin.groupId")}:${property("plugin.artifactId")}:${property("test.plugin.version")}")
    }
}
apply(plugin = "${property("plugin.groupId")}.${property("plugin.artifactId")}")

configure<com.github.godfather1103.ext.BasePluginExtension> {
    addFilterParam("one", "1")
    if (System.getProperty("env") == "ccb") {
        addFilterParam("two", "2")
    }
    setFilterParamFile(project.projectDir.absolutePath + "/filters/a.properties")
    excludeFilter("*.txt")
    excludeFilter("**/*.docx")
    includeFilter("a.txt")
}