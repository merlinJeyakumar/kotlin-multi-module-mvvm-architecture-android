import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.requiredLibraries() {
    api(RequiredLibraries.kotlinStdLib)
    api(RequiredLibraries.core_ktx)
    api(RequiredLibraries.coroutines_android)
    api(RequiredLibraries.anko)
    api(RequiredLibraries.anko_commons)
    api(RequiredLibraries.coroutines_core)
    api(RequiredLibraries.coroutines_test)
    api(RequiredLibraries.lifecycle_extension)
    api(RequiredLibraries.viewbinding)
    api(RequiredLibraries.multi_dex)
    api(RequiredLibraries.gson)
    api(RequiredLibraries.timber)
    api(RequiredLibraries.runtime_ktx)
    api(RequiredLibraries.hilt_android)
    kapt(RequiredLibraries.kapt_hilt_android_compiler)
    kapt(RequiredLibraries.kapt_hilt_compiler)
    api(RequiredLibraries.databinding_runtime)
    api(RequiredLibraries.viewmodel_ktx)
    api(RequiredLibraries.livedata_ktx)
}

fun DependencyHandler.supportLibraries() {
    implementation(SupportLibraries.appCompat)
    implementation(SupportLibraries.support_design)
    implementation(SupportLibraries.constraintLayout)
    implementation(SupportLibraries.sdp_android)
    implementation(SupportLibraries.ssp_android)
    implementation(SupportLibraries.material)
    implementation(SupportLibraries.recyclerview)
    implementation(SupportLibraries.card_view)
    implementation(SupportLibraries.legacy_support)
    implementation(SupportLibraries.paging_runtime)
    implementation(SupportLibraries.viewpager2)
    implementation(SupportLibraries.activity_ktx)
    implementation(SupportLibraries.fragment_ktx)
    implementation(SupportLibraries.lottie)
    implementation(SupportLibraries.shimmer)
    implementation(SupportLibraries.libphonenumber)
    implementation(SupportLibraries.work_runtime_ktx)
}

fun DependencyHandler.imageLoaderLibraries() {
    implementation(ImageLoaderLibraries.glide)
    implementation(ImageLoaderLibraries.glide_integration)
    implementation(ImageLoaderLibraries.glide_transformations)
    kapt(ImageLoaderLibraries.kapt_glide_integration)
}

fun DependencyHandler.rxJavaLibraries() {
    implementation(RxJavaLibraries.rx_java)
    implementation(RxJavaLibraries.rx_kotlin)
    implementation(RxJavaLibraries.rx_android)
    implementation(SupportLibraries.paging_rxjava)
    implementation(SupportLibraries.work_rxjava3)
}

fun DependencyHandler.networkLibraries() {
    api(NetworkLibraries.retrofit)
    api(NetworkLibraries.rx_adapter)
    api(NetworkLibraries.retrofit_converter_gson)
    api(NetworkLibraries.okhttp)
    api(NetworkLibraries.okhttp_logging_interceptor)
    api(NetworkLibraries.stetho)
    api(NetworkLibraries.stetho_okhttp3)
}

fun DependencyHandler.roomLibraries() {
    implementation(RoomLibraries.room_runtime)
    implementation(RoomLibraries.room_ktx)
    implementation(RoomLibraries.room_rxjava3)
    kapt(RoomLibraries.kapt_room_compiler)
}

fun DependencyHandler.dateTimeLibraries() {
    api(DateTimeLibraries.joda_time)
    api(DateTimeLibraries.joda_convert)
}

fun DependencyHandler.exoPlayerLibraries() {
    implementation(ExoPlayerLibraries.exoplayer_core)
    implementation(ExoPlayerLibraries.exoplayer_dash)
    implementation(ExoPlayerLibraries.exoplayer_ui)
    implementation(ExoPlayerLibraries.magical_exo_player)
}

fun DependencyHandler.dataStoreLibraries() {
    api(DataStoreLibraries.protobuf)
    api(DataStoreLibraries.data_store)
}

fun DependencyHandler.testLibraries() {
    implementation(TestLibraries.espressoCore)
    implementation(TestLibraries.junit)
    implementation(TestLibraries.junitTest)

    androidTestImplementation(TestLibraries.androidTestImplementationRobolectric)
    androidTestAnnotationProcessor(TestLibraries.androidTestAnnotationProcessor)
    kaptAndroidTest(TestLibraries.kaptAndroidTest)
    testAnnotationProcessor(TestLibraries.testAnnotationProcessorHiltAndroidTesting)
    androidTestImplementation(TestLibraries.androidTestImplementationHiltAndroidTesting)
    testImplementation(TestLibraries.testImplementationHiltAndroidTesting)
}

fun DependencyHandler.firebaseLibraries() {
    implementation(FirebaseLibraries.firebase_dynamic_links)
    implementation(FirebaseLibraries.firebase_referral)
    implementation(FirebaseLibraries.firebase_authentication)
    implementation(FirebaseLibraries.firebase_crashlytics)
    implementation(FirebaseLibraries.firebase_config)
    implementation(FirebaseLibraries.firebase_analytics)
    implementation(FirebaseLibraries.firebase_core)
    implementation(FirebaseLibraries.firebase_messaging)
}

fun DependencyHandler.firebaseExternalLibraries() {
    implementation(FirebaseLibraries.firebase_database)
}

fun DependencyHandler.thirdPartyLibraries() {
    implementation(ThirdPartyLibraries.otpview_pinview)
    implementation(ThirdPartyLibraries.indicator)
}

fun DependencyHandler.composeLibraries() {
    implementation("androidx.compose.ui:ui:1.1.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.1")
    // Material Design
    implementation("androidx.compose.material:material:1.1.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
}