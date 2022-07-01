plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    setCompileSdkVersion(Configs.compileSdkVersion)
    defaultConfig {
        setMinSdkVersion(Configs.minSdkVersion)
        setTargetSdkVersion(Configs.targetSdkVersion.toString())
        buildConfigField("int", "DB_VERSION", "1")
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    flavorDimensions += listOf("development")
    productFlavors {
        create("development") {
            buildConfigField(
                "String",
                "REST_URL",
                "\"https://yourapispace.com/api/\""
            )
        }
        create("beta") {
            buildConfigField(
                "String",
                "REST_URL",
                "\"https://yourapispace.com/api/\""
            )
        }
        create("production") {
            buildConfigField(
                "String",
                "REST_URL",
                "\"https://yourapispace.com/api/\""
            )
        }
    }
}

dependencies {
    implementation(project(ProjectRootLibraries.domain))
    implementation(project(ProjectRootLibraries.support))
    requiredLibraries()
    supportLibraries()
    roomLibraries()
    dateTimeLibraries()
    networkLibraries()
    firebaseLibraries()
    dataStoreLibraries()
    implementation(GoogleMiscLibraries.playservices_auth)
    implementation(GoogleMiscLibraries.google_sheets){
        exclude("org.apache.httpcomponents")
    }
    implementation(GoogleMiscLibraries.google_oauth_jetty)
    implementation(GoogleMiscLibraries.google_api_client) {
        exclude("org.apache.httpcomponents")
    }
}