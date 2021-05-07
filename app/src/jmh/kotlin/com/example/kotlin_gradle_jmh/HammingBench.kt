package com.example.kotlin_gradle_jmh

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Threads
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2)
@Fork(2)
@Threads(3)
open class HammingBench {

    @Benchmark
    fun testLoop(): Int {
        return Hamming.computeLoop(
            "supercalifragilisticexpialidocious", "Supercalafragelisticexpialedocioos"
        )
    }

    @Benchmark
    fun testZip(): Int {
        return Hamming.computeZip(
            "supercalifragilisticexpialidocious", "Supercalafragelisticexpialedocioos"
        )
    }
}
