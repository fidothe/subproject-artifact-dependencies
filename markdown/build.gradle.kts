import com.saxonica.build.GenerateEdition

plugins {
    id("java-library")
    id("com.saxonica.build.editorial")
}

val generator = tasks.register<GenerateEdition>("generate") {
    edition = "first"

    src = layout.projectDirectory.file("src/src.md")
    outputDir = layout.buildDirectory.dir(edition)
}

tasks.register("orly") {
    inputs.files(generator.map { it.outputs.files })
}

val srcFiles by configurations.consumable("srcFiles")
srcFiles.outgoing.artifacts(generator.map { it.outputs.files }) { builtBy(generator) }
