package soft.divan.plugins.mavenPublish


import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

@CacheableTask
abstract class LocalMavenPublishTask : DefaultTask() {

    @get:Input
    abstract val groupId: Property<String>

    @get:Input
    abstract val artifactId: Property<String>

    @get:Input
    abstract val version: Property<String>

    @get:OutputFile
    val outputFile: RegularFileProperty = project.objects.fileProperty()

    init {
        outputFile.convention(
            project.rootProject.layout.projectDirectory
                .file("local-maven-repo/metadata/${groupId.get()}/$}${name}/metadata.txt")
        )

    }

    @TaskAction
    fun publish() {
        val file = outputFile.get().asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            groupId=${groupId.get()}
            artifactId=${artifactId.get()}
            version=${version.get()}
            timestamp=${System.currentTimeMillis()}
            """.trimIndent()
        )
        println("Metadata written to ${file.absolutePath}")
    }
}
