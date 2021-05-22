rootProject.name = "gradle-base-plugin-root"
include(":gradle-base-plugin")
include(":gradle-base-plugin-test")

project(":gradle-base-plugin").projectDir = file("gradle-base-plugin-main")
