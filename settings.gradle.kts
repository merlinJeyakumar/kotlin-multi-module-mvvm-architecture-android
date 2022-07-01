rootProject.name = "Peace"
include(":app", ":support", ":data", ":domain")
project(":support").projectDir = File(rootDir, "modules/support")
project(":data").projectDir = File(rootDir, "data")
project(":domain").projectDir = File(rootDir, "domain")