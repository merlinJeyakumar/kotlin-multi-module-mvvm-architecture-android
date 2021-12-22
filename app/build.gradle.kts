plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    setCompileSdkVersion(property("compileSdkVersion") as Int)
    defaultConfig {
        applicationId = "com.nativedevps.mlm"
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        setMinSdkVersion(property("minSdkVersion") as Int)
        setTargetSdkVersion((property("targetSdkVersion")).toString())
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/library_release.kotlin_module")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
    flavorDimensions("development")
    buildTypes {
        getByName("release") {
            buildConfigField("Boolean", "Config", true.toString())
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            buildConfigField("Boolean", "Config", false.toString())
            isDebuggable = true
        }
    }

    productFlavors {
        create("production") {
            applicationId = "com.nativedevps.arch"
        }
        create("development") {
            applicationId = "com.nativedevps.arch.development"
        }
    }
}

dependencies {
    implementation(project(":support"))
    implementation(project(":data"))
    implementation(project(":domain"))
    requiredLibraries()
    roomLibraries()
    supportLibraries()
    imageLoaderLibraries()
    dataStoreLibraries()
    firebaseLibraries()
    thirdPartyLibraries()
    testLibraries()
    testImplementation(kotlin("test"))
}
