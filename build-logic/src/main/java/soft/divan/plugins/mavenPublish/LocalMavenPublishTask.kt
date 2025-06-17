import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

@CacheableTask
abstract class LocalMavenPublishTask : DefaultTask() {

    @get:Input
    abstract val groupId: Property<String>

    @get:Input
    abstract val artifactId: Property<String>

    @get:Input
    abstract val version: Property<String>


    @TaskAction
    fun publish() {

        val buildDir = project.buildDir
        val apkOrAabFile = buildDir.resolve("outputs/apk/debug/app-debug.apk") // или aab

        val localMavenDir = File(project.rootDir, "local-maven-repo/${groupId.get().replace('.', '/')}/${artifactId.get()}/${version.get()}")
        localMavenDir.mkdirs()

        val targetFile = File(localMavenDir, "${artifactId.get()}-${version.get()}.apk")
        apkOrAabFile.copyTo(targetFile, overwrite = true)

        println("Published to: ${targetFile.absolutePath}")
    }
}
