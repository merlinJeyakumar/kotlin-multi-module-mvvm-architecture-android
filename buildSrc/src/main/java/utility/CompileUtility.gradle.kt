import org.gradle.api.artifacts.Dependency
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

fun DependencyHandler.loadProject(s: String) {
    add("project", s)
}

fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.`testAnnotationProcessor`(dependencyNotation: Any): Dependency? =
    add("testAnnotationProcessor", dependencyNotation)

fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.`kaptTest`(dependencyNotation: Any): Dependency? =
    add("kaptTest", dependencyNotation)

fun DependencyHandler.`kaptAndroidTest`(dependencyNotation: Any): Dependency? =
    add("kaptAndroidTest", dependencyNotation)

fun DependencyHandler.`androidTestAnnotationProcessor`(dependencyNotation: Any): Dependency? =
    add("androidTestAnnotationProcessor", dependencyNotation)