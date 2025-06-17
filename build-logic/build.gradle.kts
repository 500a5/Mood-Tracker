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
}

gradlePlugin {
    plugins {
        register("LocalMavenPublishPlugin") {
            id = "soft.divan.plugins.mavenPublish"
            implementationClass = "soft.divan.plugins.mavenPublish.LocalMavenPublishPlugin"
        }
    }
}


