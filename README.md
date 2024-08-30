# Cross-subproject dependency problems

This repo is a trivial build demonstrating the problem I'm having getting a task in a subproject to depend on the artifacts produced by a task in another subproject.

The core problem is that although the depending task now correctly depends on the paths the dependent task is expected to generate, invoking the depending task does not invoke the dependent task.

So, we have two subprojects with a task in each:

```shell
markdown:generate
website:generate
```

`website:generate` depends upon artifacts created by `markdown:generate`. The artifacts are exposed using a consumable configuration.

The following works:

```shell
./gradlew markdown:clean
./gradlew website:clean
./gradlew markdown:generate
./gradlew website:generate
```

The following ought to work, but doesn't

```shell
./gradlew markdown:clean
./gradlew website:clean
./gradlew website:generate
```

The `website:generate` task is getting the correct path, but is not triggering the creation of the file by `markdown:generate`

The `markdown:orly` task depends on the outputs of `markdown:generate` in the same way, but does correctly trigger the build.
