plugins {
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