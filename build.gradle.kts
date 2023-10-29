plugins {
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    `kotlin-dsl`
    `maven-publish`
}

buildscript {

    extra["minSdkVersion"] = Configs.minSdkVersion
    extra["compileSdkVersion"] = Configs.compileSdkVersion

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
        //classpath(ProjectRootLibraries.classPathGoogleService)
        //classpath(ProjectRootLibraries.classPathFirebasePerfs)
        classpath(ProjectRootLibraries.classpathCrashlytics)
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://www.jitpack.io")
        jcenter()
        google()
    }
}