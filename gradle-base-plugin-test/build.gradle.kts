plugins {
    id("io.github.godfather1103.gradle-base-plugin")
}

baseExt {
    addFilterParam("one", "1")
    if (System.getProperty("env") == "ccb") {
        addFilterParam("two", "2")
    }
    cacheProcessResources {
        false
    }
    setFilterParamFile(project.projectDir.absolutePath + "/filters/a.properties")
    excludeFilter("*.txt")
    excludeFilter("**/*.docx")
    includeFilter("a.txt")
    openRecordTime.set(false)
}