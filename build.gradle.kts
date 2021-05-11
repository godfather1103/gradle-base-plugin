plugins {
    kotlin("jvm") version "1.4.32"
    id("com.gradle.plugin-publish") version "0.14.0"
    `java-gradle-plugin`
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("gradleBasePlugin") {
            id = "com.github.godfather1103.gradle.base.plugin"
            implementationClass = "com.github.godfather1103.GradleBasePlugin"
            displayName = "Gradle Base Plugin"
            description = "Gradle Base Plugin"
        }
    }
}

group = "com.github.godfather1103"
version = "1.0"

pluginBundle {
    website = "https://github.com/godfather1103"
    vcsUrl = "https://github.com/godfather1103/gradle-base-plugin"
    description = "Gradle Base Plugin"
    (plugins){
        "gradleBasePlugin" {
            displayName = "Gradle Base Plugin"
            description = "Gradle Base Plugin"
            tags = listOf("clean", "compile", "build")
            version = "1.0"
        }
    }
    mavenCoordinates {
        groupId = "com.github.godfather1103"
        artifactId = "gradle-base-plugin"
        version = "1.0"
        description = "Gradle Base Plugin"
    }
}


