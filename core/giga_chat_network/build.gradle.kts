import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

val CLIENT_ID: String = project.rootProject
    .file("local.properties")
    .inputStream()
    .use { Properties().apply { load(it) } }
    .getProperty("CLIENT_ID") ?: ""

val CLIENT_SECRET: String = project.rootProject
    .file("local.properties")
    .inputStream()
    .use { Properties().apply { load(it) } }
    .getProperty("CLIENT_SECRET") ?: ""


android {
    namespace = "soft.divan.moodtracker.core.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "CLIENT_ID", "\"$CLIENT_ID\"")

        buildConfigField("String", "CLIENT_SECRET", "\"$CLIENT_SECRET\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }

        debug {

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }

    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(projects.core.domain)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.gson)
    implementation(libs.androidx.tracing.ktx)

}