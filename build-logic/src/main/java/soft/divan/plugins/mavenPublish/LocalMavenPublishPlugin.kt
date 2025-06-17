package soft.divan.plugins.mavenPublish

import LocalMavenPublishTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class LocalMavenPublishPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("localMavenPublish", LocalMavenPublishExtension::class.java)

        project.tasks.register("localMavenPublish", LocalMavenPublishTask::class.java) {
            it.groupId.set(extension.groupId)
            it.artifactId.set(extension.artifactId)
            it.version.set(extension.version)
        }
    }

}
