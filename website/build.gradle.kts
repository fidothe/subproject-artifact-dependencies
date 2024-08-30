import com.saxonica.build.GenerateWebsite

plugins {
    id("java-library")
    id("com.saxonica.build.publishing")
}

val srcFilesBucket by configurations.dependencyScope("srcFilesBucket")
val srcFiles by configurations.resolvable("srcFiles") {
    extendsFrom(srcFilesBucket)
}

dependencies {
    srcFilesBucket(project(mapOf("path" to ":markdown",
        "configuration" to "srcFiles")))
}

val generator = tasks.register<GenerateWebsite>("generate") {
    src = project.provider<File> { srcFiles.first() }
    outputDir = layout.buildDirectory
}

