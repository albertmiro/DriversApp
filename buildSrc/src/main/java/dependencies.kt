object Versions {
    const val androidx_core = "1.1.0"
    const val androidx_appcompat = "1.1.0"
    const val androidx_constraint = "1.1.3"
    const val androidx_junit = "1.1.1"
    const val androidx_recyclerview = "1.0.0"
    const val androidx_cardview = "1.0.0"
    const val okhttp_interceptor = "4.2.2"
    const val koin_version = "2.0.1"
    const val kotlin_version = "1.3.50"
    const val anko_version = "0.10.4"
    const val retrofit_version = "2.4.0"
    const val rx_android_version = "2.1.1"
    const val smoothprogressbar_circular_version = "1.3.0"
    const val google_maps_version = "17.0.0"
    const val espresso_version = "3.2.0"
    const val mockito_kotlin_version = "2.2.0"
    const val leak_canary = "2.0-beta-3"

    const val test_runner_version = "1.0.1"
    const val junit_version = "4.12"
    const val kotlin_test = "1.3.50"
    const val mockito_all = "1.10.19"
    const val mockito_inline = "3.0.0"
    const val mockito_core = "3.1.0"
    const val hamcrest = "1.3"
    const val okhttp_mockwebserver = "4.2.2"
    const val android_core_testing = "1.1.1"

}

object Dependencies {

    //Base
    val kotlin =        "implementation" to "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val appCompat =     "implementation" to "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidxCore =  "implementation" to "androidx.core:core-ktx:${Versions.androidx_core}"
    val constraintLayout = "implementation" to "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint}"
    val androidxJunit = "testImplementation" to "androidx.test.ext:junit:${Versions.androidx_junit}"
    val jUnit =         "testImplementation" to "junit:junit:${Versions.junit_version}"
    val androidxEspresso = "androidTestImplementation" to "androidx.test.espresso:espresso-core:${Versions.espresso_version}"

    //Android libraries
    val recyclerview = "implementation" to "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    val cardview =     "implementation" to "androidx.cardview:cardview:${Versions.androidx_cardview}"


    //Anko
    val anko =          "implementation" to "org.jetbrains.anko:anko:${Versions.anko_version}"
    val ankoCommons =   "implementation" to  "org.jetbrains.anko:anko-commons:${Versions.anko_version}"

    //Retrofit
    val retrofit =      "implementation" to "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofitRx =    "implementation" to "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    val retrofitGson =  "implementation" to "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    //Okhttp Interceptor
    val okhttpInterceptor =
        "implementation" to "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_interceptor}"

    //Google Maps
    val googleMaps =    "implementation" to "com.google.android.gms:play-services-maps:${Versions.google_maps_version}"

    //Smooth Progress Bar
    val progressBar =   "implementation" to "com.github.castorflex.smoothprogressbar:library-circular:${Versions.smoothprogressbar_circular_version}"

    //RxJava2
    val rxJava =        "implementation" to "io.reactivex.rxjava2:rxandroid:${Versions.rx_android_version}"

    //Koin
    val koinViewModel = "implementation" to "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"

    //Tests
    val mockitoAll =    "testImplementation" to "org.mockito:mockito-all:${Versions.mockito_all}"
    val mockitoCore =   "testImplementation" to "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockitoInline = "testImplementation" to "org.mockito:mockito-inline:${Versions.mockito_inline}"
    val kotlinTest =    "testImplementation" to "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin_test}"
    val mockitoKotlin = "testImplementation" to "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"
    val hamcrest =      "testImplementation" to "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    val mockWebServer = "testImplementation" to "com.squareup.okhttp3:mockwebserver:${Versions.okhttp_mockwebserver}"
    val coreTesting =   "testImplementation" to "android.arch.core:core-testing:${Versions.android_core_testing}"
    val testRunner =    "testImplementation" to "com.android.support.test:runner:${Versions.test_runner_version}"
    val testJunit =     "testImplementation" to "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin_version}"

    val leakCanary =    "debugImplementation" to "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"

}

object Buckets {

    val network =
        listOf(
            Dependencies.retrofit,
            Dependencies.retrofitGson,
            Dependencies.retrofitRx,
            Dependencies.okhttpInterceptor
        )

    val rxJava =
        listOf(
            Dependencies.rxJava
        )

    val appLibraries =
        listOf(
            Dependencies.progressBar,
            Dependencies.googleMaps,
            Dependencies.recyclerview,
            Dependencies.cardview
        )

    val baseAndroid =
        listOf(
            Dependencies.kotlin,
            Dependencies.appCompat,
            Dependencies.androidxCore,
            Dependencies.constraintLayout,
            Dependencies.androidxCore,
            Dependencies.androidxJunit,
            Dependencies.androidxEspresso
        )

    val testing =
        listOf(
            Dependencies.mockitoKotlin,
            Dependencies.mockitoCore,
            Dependencies.mockitoAll,
            Dependencies.mockitoInline,
            Dependencies.jUnit,
            Dependencies.kotlinTest,
            Dependencies.hamcrest,
            Dependencies.mockWebServer,
            Dependencies.coreTesting,
            Dependencies.testRunner,
            Dependencies.testJunit
        )

    val koin =
        listOf(
            Dependencies.koinViewModel
        )

    val anko =
        listOf(
            Dependencies.anko,
            Dependencies.ankoCommons
        )

    val tools =
        listOf(
            Dependencies.leakCanary
        )
}