import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ReXtOr.GroupChat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ReXtOr.GroupChat"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<KotlinCompile>().configureEach{
        kotlinOptions{
            jvmTarget = "1.8"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }




}
tasks.withType<KotlinCompile>{
    kotlinOptions{
        freeCompilerArgs= listOf("-Xlint:deprecation")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0")

    //Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Ktor
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
    implementation ("io.ktor:ktor-client-core:2.3.4")
    implementation ("io.ktor:ktor-client-cio:2.3.4")
    implementation ("io.ktor:ktor-client-websockets:2.3.0")

    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.2")




}

kapt {
    correctErrorTypes = true
}
