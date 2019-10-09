object Versions {
    val kotlin_version = "1.3.50"
    val anko_version = "0.10.4"
    val retrofit_version = "2.4.0"
    val rx_android_version = "2.0.2"
    val smoothprogressbar_circular_version = "1.3.0"
    val google_maps_version = "17.0.0"
    val dagger_version = "2.24"

    val espresso_version = "3.2.0"
    val mockito_kotlin_version = "1.1.0"
    val test_runner_version = "1.0.1"
    val junit_version = "4.12"
    val mockito_all = "1.10.19"
    val mockito_core = "2.18.3"
    val hamcrest = "1.3"
    val okhttp_mockwebserver = "3.10.0"
    val android_core_testing = "1.1.1"
}

object Dependencies {

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val appCompat = "androidx.appcompat:appcompat:1.1.0"
    val androidxCore = "androidx.core:core-ktx:1.1.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val androidxJunit = "androidx.test.ext:junit:1.1.1"
    val androidxEspresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"

    //Anko
    val anko = "org.jetbrains.anko:anko:${Versions.anko_version}"
    val ankoCommons = "org.jetbrains.anko:anko-commons:${Versions.anko_version}"

    //Dagger2
    val dagger = "com.google.dagger:dagger:${Versions.dagger_version}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger_version}"
    val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger_version}"
    val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger_version}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"

    //Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    //Google Maps
    val googleMaps = "com.google.android.gms:play-services-maps:${Versions.google_maps_version}"

    //Smooth Progress Bar
    val progressBar =
        "com.github.castorflex.smoothprogressbar:library-circular:${Versions.smoothprogressbar_circular_version}"

    //RxJava2
    val rxJava = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android_version}"

    //Tests
    val jUnit = "junit:junit:${Versions.junit_version}"
    val mockitoAll = "org.mockito:mockito-all:${Versions.mockito_all}"
    val mockitoCore = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockito_kotlin_version}"
    val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp_mockwebserver}"
    val coreTesting = "android.arch.core:core-testing:${Versions.android_core_testing}"
    val testRunner = "com.android.support.test:runner:${Versions.test_runner_version}"
    val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin_version}"

}

object Buckets {

    val retrofit =
        listOf(
            Dependencies.retrofit,
            Dependencies.retrofitGson,
            Dependencies.retrofitRx
        )

    val rxJava =
        listOf(
            Dependencies.rxJava
        )

    val appLibraries =
        listOf(
            Dependencies.progressBar,
            Dependencies.googleMaps
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
            Dependencies.jUnit,
            Dependencies.mockitoAll,
            Dependencies.mockitoCore,
            Dependencies.mockitoKotlin,
            Dependencies.hamcrest,
            Dependencies.mockWebServer,
            Dependencies.coreTesting,
            Dependencies.testRunner,
            Dependencies.testJunit
        )

    val dagger =
        listOf(
            Dependencies.dagger,
            Dependencies.daggerAndroid,
            Dependencies.daggerSupport,
            Dependencies.daggerAndroidProcessor,
            Dependencies.daggerCompiler
        )

    val anko =
        listOf(
            Dependencies.anko,
            Dependencies.ankoCommons
        )
}