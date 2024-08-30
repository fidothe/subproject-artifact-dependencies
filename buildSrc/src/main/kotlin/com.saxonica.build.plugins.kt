package com.saxonica.build

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

class EditorialPlugin : Plugin<Project> {
    override fun apply(project: Project) {
    }
}

class PublishingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
    }
}

abstract class GenerateEdition : DefaultTask() {
    @get:Input
    abstract val edition: Property<String>

    @get:InputFile
    abstract val src: RegularFileProperty

    @get:Internal
    abstract val outputDir : DirectoryProperty

    @OutputFile
    val outputFile: Provider<RegularFile> = outputDir.map { it.file(src.get().asFile.name) }

    @TaskAction
    fun generate() {
        val input = src.get().asFile.readText()
        val output = input.replace("@@REPLACE-ME@@", edition.get())
        outputFile.get().asFile.writeText(output)
    }
}

abstract class GenerateWebsite : DefaultTask() {
    @get:InputFile
    abstract val src: RegularFileProperty

    @get:Internal
    abstract val outputDir: DirectoryProperty

    @OutputFile
    val outputFile: Provider<RegularFile> = outputDir.map { it.file(src.get().asFile.name) }

    @TaskAction
    fun generate() {
        val input = src.get().asFile.readText()
        val output = "<html>${input}</html>"
        outputFile.get().asFile.writeText(output)
    }
}
