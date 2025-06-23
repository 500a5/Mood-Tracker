plugins {
    `java-gradle-plugin`
    alias(libs.plugins.kotlin.jvm)
    `kotlin-dsl`
    id("maven-publish")
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    compileOnly(libs.android.gradlePlugin)
    implementation(gradleKotlinDsl())

    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.com.google.devtools.ksp.gradle.plugin)

}

gradlePlugin {
    plugins {
        register("LocalMavenPublishPlugin") {
            id = "soft.divan.plugins.mavenPublish"
            implementationClass = "soft.divan.plugins.mavenPublish.LocalMavenPublishPlugin"
        }
    }
    plugins {
        register("AppConfigPlugin") {
            id = "soft.divan.plugins.appConfigPlugin"
            implementationClass = "soft.divan.plugins.convention.AppConfigPlugin"
        }
    }
}


