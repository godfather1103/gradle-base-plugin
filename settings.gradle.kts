rootProject.name = "gradle-base-plugin"
pluginManagement {
    includeBuild("gradle-base-plugin-main")
}
include(":test-one")
include(":test-two")
project(":test-one").projectDir = file("gradle-base-plugin-test/test-one")
project(":test-two").projectDir = file("gradle-base-plugin-test/test-two")
