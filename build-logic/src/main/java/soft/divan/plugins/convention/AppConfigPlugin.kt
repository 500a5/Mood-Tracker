package soft.divan.plugins.convention


import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.withId("com.android.application") {
            val versionCatalog = project.the<VersionCatalogsExtension>().named("libs")
            val androidExtension = project.extensions.findByName("android") as BaseAppModuleExtension

            androidExtension.apply {
                namespace = versionCatalog.findVersion("applicationId").get().toString()
                compileSdk = versionCatalog.findVersion("compileSdk").get().toString().toInt()

                defaultConfig {
                    applicationId = versionCatalog.findVersion("applicationId").get().toString()

                    minSdk = versionCatalog.findVersion("minSdk").get().toString().toInt()
                    targetSdk = versionCatalog.findVersion("targetSdk").get().toString().toInt()

                    versionCode =  versionCatalog.findVersion("versionCode").get().toString().toInt()
                    versionName = versionCatalog.findVersion("versionName").get().toString()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                    debug { isDebuggable = true }
                }

                compileOptions {
                    sourceCompatibility =  JavaVersion.toVersion( versionCatalog.findVersion("java").get().toString().toInt())
                    targetCompatibility = JavaVersion.toVersion( versionCatalog.findVersion("java").get().toString().toInt())
                }


                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = versionCatalog.findVersion("java").get().toString()
                    }
                }

                buildFeatures {
                    compose = true
                }

               /*
                composeOptions {
                    kotlinCompilerExtensionVersion = versionCatalog.findVersion("kotlinCompiler").get().toString()
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }*/
            }
        }
    }
}