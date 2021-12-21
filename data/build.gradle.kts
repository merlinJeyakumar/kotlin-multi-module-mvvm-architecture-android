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
        buildConfigField("String", "REST_URL", "\"https://laravelmlm.herokuapp.com/api/\"")
        buildConfigField("String", "API_KEY", "\"9a95c68a9c6ec61104cd3967dcbb8bd3\"")
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":support"))
    implementation(project(":domain"))
    requiredLibraries()
    supportLibraries()
    roomLibraries()
    dateTimeLibraries()
    networkLibraries()
    firebaseLibraries()
    dataStoreLibraries()
}