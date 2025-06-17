package soft.divan.plugins.mavenPublish

import org.gradle.api.provider.Property

interface LocalMavenPublishExtension {
    val groupId: Property<String>
    val artifactId: Property<String>
    val version: Property<String>
}
