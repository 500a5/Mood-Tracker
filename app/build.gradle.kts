plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("soft.divan.plugins.mavenPublish")
    id("soft.divan.plugins.appConfigPlugin")
}

localMavenPublish {
    groupId = "moodtracker"
    artifactId = "moodtracker"
    version = "0.0.1"
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
