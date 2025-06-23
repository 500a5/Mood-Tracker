package soft.divan.plugins.mavenPublish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

class LocalMavenPublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("maven-publish")

        val extension =
            project.extensions.create("localMavenPublish", LocalMavenPublishExtension::class.java)

        val localRepoPath = "${project.rootDir}/local-maven-repo"

        project.afterEvaluate {
            project.extensions.getByType<PublishingExtension>().publications {

                val groupId = extension.groupId.get()
                val artifactId = extension.artifactId.get()
                val version = extension.version.get()

                // Debug APK
                register<MavenPublication>("debugApk") {
                    this.groupId = groupId
                    this.artifactId = "${artifactId}-debug-apk"
                    this.version = version

                    val debugApk = layout.buildDirectory.file("outputs/apk/debug/app-debug.apk")

                    artifact(debugApk) {
                        this.extension = "apk"
                        builtBy(tasks.named("assembleDebug"))
                    }
                }

                // Release APK
                register<MavenPublication>("releaseApk") {
                    this.groupId = groupId
                    this.artifactId = "${artifactId}-release-apk"
                    this.version = version

                    val releaseApk = layout.buildDirectory.file("outputs/apk/release/app-release.apk")
                    artifact(releaseApk) {
                        this.extension = "apk"
                        builtBy(tasks.named("assembleRelease"))
                    }
                }
                // Unsigned Release APK
                register<MavenPublication>("releaseUnsignedApk") {
                    this.groupId = groupId
                    this.artifactId = "${artifactId}-release-unsigned-apk"
                    this.version = version

                    val unsignedApk =
                        layout.buildDirectory.file("outputs/apk/release/app-release-unsigned.apk")
                    artifact(unsignedApk) {
                        this.extension = "apk"
                        builtBy(tasks.named("assembleRelease"))
                    }
                }

                // Debug AAB
                register<MavenPublication>("debugAab") {
                    this.groupId = groupId
                    this.artifactId = "${artifactId}-debug-aab"
                    this.version = version

                    val debugAab = layout.buildDirectory.file("outputs/bundle/debug/app-debug.aab")
                    artifact(debugAab) {
                        this.extension = "aab"
                        builtBy(tasks.named("bundleDebug"))
                    }
                }

                // Release AAB
                register<MavenPublication>("releaseAab") {
                    this.groupId = groupId
                    this.artifactId = "${artifactId}-release-aab"
                    this.version = version

                    val releaseAab = layout.buildDirectory.file("outputs/bundle/release/app-release.aab")
                    artifact(releaseAab) {
                        this.extension = "aab"
                        builtBy(tasks.named("bundleRelease"))
                    }
                }

            }

            project.extensions.getByType<PublishingExtension>().repositories {
                maven {
                    name = "localMaven"
                    url = uri(localRepoPath)
                }
            }
        }

        project.tasks.register("publishDebugApkToLocal") {
            group = "publishing"
            description = "Publishes debug APK to local Maven"
            dependsOn("assembleDebug", "publishDebugApkPublicationToLocalMavenRepository")
        }

        project.tasks.register("publishReleaseApkToLocal") {
            group = "publishing"
            description = "Publishes release APK to local Maven"
            dependsOn("assembleRelease", "publishReleaseApkPublicationToLocalMavenRepository")
        }

        project.tasks.register("publishUnsignedApkToLocal") {
            group = "publishing"
            description = "Publishes unsigned release APK to local Maven"
            dependsOn("assembleRelease", "publishReleaseUnsignedApkPublicationToLocalMavenRepository")
        }

        project.tasks.register("publishDebugAabToLocal") {
            group = "publishing"
            description = "Publishes debug AAB to local Maven"
            dependsOn("bundleDebug", "publishDebugAabPublicationToLocalMavenRepository")
        }

        project.tasks.register("publishReleaseAabToLocal") {
            group = "publishing"
            description = "Publishes release AAB to local Maven"
            dependsOn("bundleRelease", "publishReleaseAabPublicationToLocalMavenRepository")
        }

        project.tasks.register("generateLocalMavenMetadata", LocalMavenPublishTask::class.java) {
            groupId.set(extension.groupId)
            artifactId.set(extension.artifactId)
            version.set(extension.version)
            group = "publishing"
            description = "Generates metadata file for local Maven publication"
        }


        project.tasks.register("publishAllLocalArtifacts") {
            group = "publishing"
            description = "Publishes all APK and AAB artifacts to local Maven"
            dependsOn(
                "publishDebugApkToLocal",
                "publishReleaseApkToLocal",
                "publishUnsignedApkToLocal",
                "publishDebugAabToLocal",
                "publishReleaseAabToLocal",
                "generateLocalMavenMetadata"
            )
        }
    }
}