## A small Kotlin app with a JMH benchmark, built with the Gradle Kotlin DSL

All the examples I could find were setup so the benchmark code was in the main sources and run as the main application. 

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

## Resources

https://arbitrary-but-fixed.net/java/algorithms/2019/09/29/jmh-gradle-config.html

https://www.programmersought.com/article/4721643238/

https://www.baeldung.com/java-microbenchmark-harness

https://gist.github.com/mrbald/7bbb5113c3164d5243314da8c28649a1
