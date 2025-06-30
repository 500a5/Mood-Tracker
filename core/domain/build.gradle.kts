import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)

}
java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
}
kotlin {
    compilerOptions {
        jvmTarget.set(
            JvmTarget.values().first { it.target == libs.versions.java.get() }
        )
    }
}
dependencies{
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("javax.inject:javax.inject:1")
}