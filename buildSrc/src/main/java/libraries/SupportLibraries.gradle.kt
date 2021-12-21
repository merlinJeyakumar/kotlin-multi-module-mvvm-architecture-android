import libraries.Versions

object ProjectRootLibraries {
    const val classpathGradle =
        "com.android.tools.build:gradle:${Configs.classpathGradleVersion}"
    const val classpathKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Configs.kotlinVersion}"
    const val classpathDaggerHiltVersion =
        "com.google.dagger:hilt-android-gradle-plugin:${Configs.classpathDaggerHiltVersion}"
    const val classPathGoogleService =
        "com.google.gms:google-services:${Configs.classpathGoogleServices}"
    const val classPathFirebasePerfs =
        "com.google.firebase:perf-plugin:${Configs.classpathFirebasePerfs}"
    const val classpathCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Configs.classpathCrashlytics}"
}

object RequiredLibraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Configs.kotlinVersion}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"
    const val coroutines_play_services =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"
    const val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.anko_commons}"
    const val lifecycle_extension =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extension}"
    const val multi_dex = "androidx.multidex:multidex:${Versions.multi_dex}"
    const val viewbinding = "com.android.databinding:viewbinding:${Versions.view_binding}"
    const val runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtime_ktx}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    const val hilt_lifecycle_viewmodel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle_viewmodel}"
    const val kapt_hilt_android_compiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_android_compiler}"
    const val kapt_hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler}"
    const val databinding_runtime =
        "androidx.databinding:databinding-runtime:${Versions.databinding_runtime}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object SupportLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val support_design = "com.android.support:design:${Versions.support_design}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val sdp_android = "com.intuit.sdp:sdp-android:${Versions.sdp_android}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val card_view = "androidx.cardview:cardview:${Versions.card_view}"
    const val material_design = "com.google.android.material:material:${Versions.material_design}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val paging_runtime = "androidx.paging:paging-runtime:${Versions.paging_runtime}"
    const val paging_rxjava = "androidx.paging:paging-rxjava3:${Versions.paging_rxjava3}"
    const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val libphonenumber =
        "com.googlecode.libphonenumber:libphonenumber:${Versions.libPhoneNumber}"
    const val work_runtime_ktx = "androidx.work:work-runtime-ktx:${Versions.work_runtime_ktx}"
    const val work_rxjava3 = "androidx.work:work-rxjava3:${Versions.work_rxjava3}"
}

object RxJavaLibraries {
    const val rx_java = "io.reactivex.rxjava3:rxjava:${Versions.rx_java}"
    const val rx_kotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rx_kotlin}"
    const val rx_android = "io.reactivex.rxjava3:rxandroid:${Versions.rx_android}"
    const val rx_data_store = "androidx.datastore:datastore-rxjava3:${Versions.rx_data_store}"
}

object NetworkLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rx_adapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.rx_adapter}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stetho_okhttp3 = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho_okhttp3}"
}

object FirebaseLibraries {
    const val firebase_messaging =
        "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    const val firebase_core = "com.google.firebase:firebase-core:${Versions.firebase_core}"
    const val firebase_analytics =
        "com.google.firebase:firebase-analytics:${Versions.firebase_analytics}"
    const val firebase_config = "com.google.firebase:firebase-config:${Versions.firebase_config}"
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.firebase_crashlytics}"
    const val firebase_authentication =
        "com.google.firebase:firebase-auth:${Versions.firebase_authentication}"
    const val firebase_referral =
        "com.android.installreferrer:installreferrer:${Versions.firebase_referral}"
    const val firebase_dynamic_links =
        "com.google.firebase:firebase-dynamic-links:${Versions.firebase_dynamic_links}"
    const val firebase_database =
        "com.google.firebase:firebase-database:${Versions.firebase_database}"
}

object RoomLibraries {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_runtime}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_ktx}"
    const val room_rxjava3 = "androidx.room:room-rxjava3:${Versions.room_rxjava3}"
    const val kapt_room_compiler = "androidx.room:room-compiler:${Versions.room_runtime}"
}

object DataStoreLibraries {
    const val data_store = "androidx.datastore:datastore:${Versions.data_store}"
    const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.protobuf}"
}

object DateTimeLibraries {
    const val joda_time = "joda-time:joda-time:${Versions.joda_time}"
    const val joda_convert = "org.joda:joda-convert:${Versions.joda_convert}"
}

object ExoPlayerLibraries {
    const val exoplayer_core =
        "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer_core}"
    const val exoplayer_dash =
        "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer_dash}"
    const val exoplayer_ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer_ui}"
    const val magical_exo_player =
        "com.github.HamidrezaAmz:MagicalExoPlayer:${Versions.magical_exo_player}"
}

object ImageLoaderLibraries {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_transformations =
        "jp.wasabeef:glide-transformations:${Versions.glide_transformations}"
    const val glide_integration =
        "com.github.bumptech.glide:okhttp3-integration:${Versions.glide_integration}"
    const val kapt_glide_integration = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTestVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}

object ThirdPartyLibraries {
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}" //html-parser
    const val auto_starter = "com.github.judemanutd:autostarter:${Versions.auto_starter}"
    const val subsampling_scale_image_view = "com.davemorrissey.labs:subsampling-scale-image-view-androidx:${Versions.subsampling_scale_image_view}"
    const val otpview_pinview ="com.github.mukeshsolanki:android-otpview-pinview:2.1.2"
}