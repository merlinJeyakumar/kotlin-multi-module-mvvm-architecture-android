import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(s: String) {
    add("implementation", s)
}

fun DependencyHandler.api(s: String) {
    add("api", s)
}

fun DependencyHandler.kapt(s: String) {
    add("kapt", s)
}