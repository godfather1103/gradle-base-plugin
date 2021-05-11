plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.14.0"
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create("gradle-base-plugin") {
            id = "com.github.godfather1103"
            implementationClass = "gradle.base.plugin"
        }
    }
}

group = "com.github.godfather1103"
version = "1.0"

pluginBundle {
    website = "https://github.com/godfather1103"
    vcsUrl = "https://github.com/godfather1103/gradle-base-plugin"
    description = "gradle base plugin"
    (plugins){
        "gradle-base-plugin"{
            displayName = "Gradle Base Plugin"
            tags = listOf("clean", "compile", "build")
            version = "1.0"
        }
    }
    mavenCoordinates {
        groupId = "com.github.godfather1103"
        artifactId = "gradle-base-plugin"
        version = "1.0"
    }
}


