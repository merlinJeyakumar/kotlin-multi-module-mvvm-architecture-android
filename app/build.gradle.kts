plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.sonarqube")
}
apply {
    /*todo: execute groovy gradle file
    * from("$rootDir/example.gradle")
    *  */
}

android {
    setCompileSdkVersion(Configs.compileSdkVersion)
    defaultConfig {
        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = "${Configs.versionName} (${Configs.versionCode})"
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
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
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
            isTestCoverageEnabled = true
        }
    }

    /**
     * configure applicationId
     * derive and generate app_name res from root_project
     **/
    productFlavors {
        create("beta") {
            applicationId = Configs.applicationId
            resValue("string", "app_name", Configs.projectName)
        }
        create("production") {
            applicationId = Configs.applicationId
            resValue("string", "app_name", Configs.projectName)
        }
        create("development") {
            applicationId = "${Configs.applicationId}.development"
            versionNameSuffix = " dev"
            resValue("string", "app_name", "[D] ${Configs.projectName}")
        }
    }

    sonarqube {
        properties {
            property("sonar.projectName", "oneself-droid")
            property("sonar.projectKey", "oneself-droid")
            property("sonar.sourceEncoding", "UTF-8")
            property("sonar.sources", "src/main/java")
            property(
                "sonar.exclusions",
                "**/*Test*/**," + "*.json," + "**/*test*/**," + "**/.gradle/**," + "**/R.class"
            )
        }
    }

    /**
     *Artifact build naming
     **/
    applicationVariants.all {
        outputs.all {
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).apply {
                val projectName = Configs.projectName.replace(" ", "-")
                this.outputFileName =
                    "${name}_${projectName}_${Configs.activeCirculation}_v${Configs.versionName}_${Configs.versionCode}.apk"
        }
    }
}
    dexOptions {
        this.preDexLibraries = true
        this.javaMaxHeapSize = "4G"
    }
}

dependencies {
    implementation(project(ProjectRootLibraries.data))
    implementation(project(ProjectRootLibraries.domain))
    implementation(project(ProjectRootLibraries.support))
    requiredLibraries()
    roomLibraries()
    supportLibraries()
    imageLoaderLibraries()
    dataStoreLibraries()
    firebaseLibraries()
    thirdPartyLibraries()
    testLibraries()
    testImplementation(kotlin("test"))


    //todo: update on buildSrc
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.gms:play-services-auth:17.0.0")
    implementation("com.google.android.gms:play-services-auth-api-phone:17.4.0")
    implementation("androidx.navigation:navigation-fragment:2.3.4")
    implementation("androidx.navigation:navigation-ui:2.3.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("com.alimuzaffar.lib:pinentryedittext:2.0.6")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("com.github.dhaval2404:imagepicker:2.1")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.24")
}

/*todo: jacoco*/