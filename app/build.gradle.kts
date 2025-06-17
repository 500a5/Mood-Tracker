plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("soft.divan.plugins.mavenPublish")
    id("maven-publish")
}



localMavenPublish {
    groupId = "soft.divan.moodtracker"
    artifactId = "moodtracker"
    version = "0.0.1"
}

android {
    namespace = "soft.divan.moodtracker"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {  isDebuggable = true}
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
    
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(projects.core.navigation)

    implementation(projects.core.designsystem)
    implementation(projects.feature.entries)
    implementation(projects.feature.analytics)
    implementation(projects.feature.calendar)
    implementation(projects.feature.more)
    implementation(projects.feature.create)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    // Hilt Jetpack components
    //implementation(libs.hilt.navigation.compose)
    //implementation(libs.androidx.hilt.lifecycle.viewmodel)
}

val groupId = "soft.divan.moodtracker"
val artifactIdBase = "moodtracker"
val versionName = "0.0.1"
val localRepoPath = "${rootProject.projectDir}/local-maven-repo"

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("debugApk") {
                groupId = groupId
                artifactId = "${artifactIdBase}-debug-apk"
                version = versionName

                val debugApk = layout.buildDirectory.file("outputs/apk/debug/app-debug.apk")
                artifact(debugApk.get().asFile) {
                    extension = "apk"
                    builtBy(tasks.named("assembleDebug"))
                }
            }

            register<MavenPublication>("releaseApk") {
                groupId = groupId
                artifactId = "${artifactIdBase}-release-apk"
                version = versionName

                val releaseApk = layout.buildDirectory.file("outputs/apk/release/app-release.apk")
                artifact(releaseApk.get().asFile) {
                    extension = "apk"
                    builtBy(tasks.named("assembleRelease"))
                }
            }

            register<MavenPublication>("debugAab") {
                groupId = groupId
                artifactId = "${artifactIdBase}-debug-aab"
                version = versionName

                val debugAab = layout.buildDirectory.file("outputs/bundle/debug/app-debug.aab")
                artifact(debugAab.get().asFile) {
                    extension = "aab"
                    builtBy(tasks.named("bundleDebug"))
                }
            }

            register<MavenPublication>("releaseAab") {
                groupId = groupId
                artifactId = "${artifactIdBase}-release-aab"
                version = versionName

                val releaseAab = layout.buildDirectory.file("outputs/bundle/release/app-release.aab")
                artifact(releaseAab.get().asFile) {
                    extension = "aab"
                    builtBy(tasks.named("bundleRelease"))
                }
            }
        }

        repositories {
            maven {
                name = "localMaven"
                url = uri(localRepoPath)
            }
        }
    }

    tasks.register("publishDebugApkToLocal") {
        group = "publishing"
        description = "Publishes debug APK to local Maven"
        dependsOn("assembleDebug", "publishDebugApkPublicationToLocalMavenRepository")
    }

    tasks.register("publishReleaseApkToLocal") {
        group = "publishing"
        description = "Publishes release APK to local Maven"
        dependsOn("assembleRelease", "publishReleaseApkPublicationToLocalMavenRepository")
    }

    tasks.register("publishDebugAabToLocal") {
        group = "publishing"
        description = "Publishes debug AAB to local Maven"
        dependsOn("bundleDebug", "publishDebugAabPublicationToLocalMavenRepository")
    }

    tasks.register("publishReleaseAabToLocal") {
        group = "publishing"
        description = "Publishes release AAB to local Maven"
        dependsOn("bundleRelease", "publishReleaseAabPublicationToLocalMavenRepository")
    }

    tasks.register("publishAllArtifactsToLocal") {
        group = "publishing"
        description = "Builds and publishes all APKs and AABs to local Maven"
        dependsOn(
            "publishDebugApkToLocal",
            "publishReleaseApkToLocal",
            "publishDebugAabToLocal",
            "publishReleaseAabToLocal"
        )
    }
}
