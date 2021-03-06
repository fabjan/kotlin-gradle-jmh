## A small Kotlin app with a JMH benchmark, built with the Gradle Kotlin DSL

All the examples I could find were setup so that the benchmark code was
in the main sources and run as the main application, or small `example`
subprojects inside bigger repositories.

My first attempts at making this work was baking my own gradle tasks for
running `jmh`, but I found getting the code generation and the annotation
processor to play ball pretty finnicky. Luckily the [jmh-gradle-plugin]
handles all of this! It also neatly expects the code outside of `main`.

[jmh-gradle-plugin]: https://github.com/melix/jmh-gradle-plugin

### Testing

```shell
$ ./gradlew test
BUILD SUCCESSFUL in 4s
5 actionable tasks: 5 executed
```

```shell
$ ./gradlew smoke
> Task :app:smoke
💃 calculating...distance: 4

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date
```

### Benchmarking

⚠️ This takes several minutes ⚠️

```shell
$ ./gradlew jmh

> Task :app:jmhRunBytecodeGenerator
[ ... ]
Writing out Java source to ~/code/kotlin-gradle-jmh/app/build/jmh-generated-sources and resources to ~/code/kotlin-gradle-jmh/app/build/jmh-generated-resources

> Task :app:jmh
# JMH version: 1.30
# VM version: JDK 15.0.2, OpenJDK 64-Bit Server VM, 15.0.2+7-27
[ ... ]
Do not assume the numbers tell you what you want them to tell.

Benchmark                            (left)              (leftLong)  (right)             (rightLong)   Mode  Cnt      Score        Error   Units
HammingBench.testLoop                     A  counterexcommunication        A  dacryocystoblennorrhea  thrpt    4  70251,274 ±   2361,743  ops/ms
HammingBench.testLoop                     A  counterexcommunication        A  deanthropomorphization  thrpt    4  60238,366 ±  15400,086  ops/ms
[ ... ]
HammingBench.testZipIterAnonGeneric       B  dacryocystosyringotomy        C  dacryocystoblennorrhea  thrpt    4  26198,620 ±  21235,576  ops/ms
HammingBench.testZipIterAnonGeneric       B  dacryocystosyringotomy        C  deanthropomorphization  thrpt    4  27039,246 ±   5246,881  ops/ms
[ ... ]

> Task :app:jmhReport
JMH Report generated, please open: ~/code/kotlin-gradle-jmh/app/build/reports/jmh/index.html

BUILD SUCCESSFUL in 5m 25s
10 actionable tasks: 6 executed, 4 up-to-date
```

## Resources

Here are some of the texts I read trying to figure out how to setup `jmh`
for a Kotlin project using Gradle:

https://blog.morethan.io/jmh-with-gradle-from-easy-to-simple-dc872d57cf7f

https://arbitrary-but-fixed.net/java/algorithms/2019/09/29/jmh-gradle-config.html

https://www.programmersought.com/article/4721643238/

https://www.baeldung.com/java-microbenchmark-harness

https://gist.github.com/mrbald/7bbb5113c3164d5243314da8c28649a1
