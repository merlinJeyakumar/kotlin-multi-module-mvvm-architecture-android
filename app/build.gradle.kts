plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    //id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Configs.applicationId
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        setMinSdkVersion(Configs.minSdkVersion)
        setTargetSdkVersion(Configs.targetSdkVersion.toString())
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        testInstrumentationRunner = "com.nativedevps.expenses.CustomTestRunner"
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
    }

    packagingOptions {
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/main.kotlin_module")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/library_release.kotlin_module")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/*")
    }
    flavorDimensions("development")
    buildTypes {
        getByName("release") {
            buildConfigField("Boolean", "Config", true.toString())
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            buildConfigField("Boolean", "Config", false.toString())
            isDebuggable = true
        }
    }

    productFlavors {
        create("production") {
            applicationId = Configs.applicationId
        }
        create("development") {
            applicationId = "${Configs.applicationId}.development"
        }
    }
    configurations {
        all {
            //exclude("some library")
        }
    }
}

dependencies {
    implementation(project(ProjectRootLibraries.data))
    implementation(project(ProjectRootLibraries.domain))
    implementation(project(ProjectRootLibraries.support))
    implementation(RequiredLibraries.core_ktx)
    implementation(RequiredLibraries.viewmodel_ktx)
    requiredLibraries()
    networkLibraries()
    //roomLibraries()
    supportLibraries()
    imageLoaderLibraries()
    dataStoreLite()
    //implementation(platform(firebase_platform_bom))
    //firebaseLibraries()
    implementation(NavigationLibraries.navigationUi)
    implementation(NavigationLibraries.navigationFragment)
    testLibraries()
    testImplementation(kotlin("test"))
}

kapt {
    correctErrorTypes = true
}
