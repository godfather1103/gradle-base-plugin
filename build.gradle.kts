plugins {
    kotlin("jvm") version "1.4.32"
    id("com.gradle.plugin-publish") version "0.14.0"
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("gradleBasePlugin") {
            id = "com.github.godfather1103.gradle.base.plugin"
            implementationClass = "com.github.godfather1103.GradleBasePlugin"
            displayName = "${property("plugin.displayName")}"
            description = "${property("plugin.description")}"
        }
    }
}

group = "${property("plugin.groupId")}"
version = "${property("plugin.version")}"

pluginBundle {
    website = "https://github.com/godfather1103"
    vcsUrl = "https://github.com/godfather1103/gradle-base-plugin"
    description = "${property("plugin.description")}"
    (plugins){
        "gradleBasePlugin" {
            displayName = "${property("plugin.displayName")}"
            description = "${property("plugin.description")}"
            tags = listOf("clean", "compile", "build")
            version = "1.0"
        }
    }
    mavenCoordinates {
        groupId = "com.github.godfather1103"
        artifactId = "gradle-base-plugin"
        version = "1.0"
        description = "${property("plugin.description")}"
    }
}


