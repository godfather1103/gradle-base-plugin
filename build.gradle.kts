plugins {
    kotlin("jvm") version "1.4.32"
    id("com.gradle.plugin-publish") version "0.14.0"
    `java-gradle-plugin`
    `maven-publish`
    signing
}

dependencies {
    implementation(gradleApi())
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "${property("plugin.artifactId")}"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("${property("plugin.artifactId")}")
                description.set("${property("plugin.description")}")
                url.set("https://github.com/godfather1103/gradle-base-plugin")
                licenses {
                    license {
                        name.set("The MIT License (MIT)")
                        url.set("https://mit-license.org/")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("godfather1103")
                        name.set("Jack Chu")
                        email.set("chuchuanbao@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:godfather1103/gradle-base-plugin.git")
                    developerConnection.set("scm:git:git@github.com:godfather1103/gradle-base-plugin.git")
                    url.set("https://github.com/godfather1103/gradle-base-plugin")
                }
            }
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().toUpperCase().contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                username = "${property("ossrhUsername")}"
                password = "${property("ossrhPassword")}"
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
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
            version = "${property("plugin.version")}"
        }
    }
    mavenCoordinates {
        groupId = "${property("plugin.groupId")}"
        artifactId = "${property("plugin.artifactId")}"
        version = "${property("plugin.version")}"
        description = "${property("plugin.description")}"
    }
}


