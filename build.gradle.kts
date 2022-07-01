apply(plugin = "org.sonarqube")

plugins {
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("org.sonarqube") version "3.3"
    `kotlin-dsl`
}

buildscript {

    extra["minSdkVersion"] = Configs.minSdkVersion
    extra["compileSdkVersion"] = Configs.compileSdkVersion
    extra["targetSdkVersion"] = Configs.targetSdkVersion.toString()

    repositories {
        mavenCentral()
        maven(url = "https://www.jitpack.io")
        jcenter()
        google()
    }

    dependencies {
        classpath(ProjectRootLibraries.classpathGradle)
        classpath(ProjectRootLibraries.classpathKotlinGradle)
        classpath(ProjectRootLibraries.classpathDaggerHiltVersion)
        classpath(ProjectRootLibraries.classPathGoogleService)
        classpath(ProjectRootLibraries.classPathFirebasePerfs)
        classpath(ProjectRootLibraries.classpathCrashlytics)
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.3")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://www.jitpack.io")
        maven { url = uri("https://plugins.gradle.org/m2/") }
        jcenter()
        google()
    }
}