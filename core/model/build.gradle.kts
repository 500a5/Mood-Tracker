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
